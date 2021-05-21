package br.com.zupacademy.samara.transacoes.transacao;

import br.com.zupacademy.samara.transacoes.cartao.Cartao;
import br.com.zupacademy.samara.transacoes.cartao.CartaoRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransacaoListener {
    private final Logger logger = LoggerFactory.getLogger(TransacaoListener.class);

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private CartaoRepository cartaoRepository;

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(TransacaoMessage transacaoMessage) {
        logger.info("Transacao {}", transacaoMessage.toModel());

        Optional<Cartao> cartaoOptional = cartaoRepository.findByNumero(transacaoMessage.getCartao().getId());
        Transacao transacao = transacaoMessage.toModel();

        if (cartaoOptional.isEmpty()) {
            transacaoRepository.save(transacao);
            return;
        }

        Cartao cartaoEncontrado = cartaoOptional.get();
        transacao.setCartao(cartaoEncontrado);
        transacaoRepository.save(transacao);
    }
}
