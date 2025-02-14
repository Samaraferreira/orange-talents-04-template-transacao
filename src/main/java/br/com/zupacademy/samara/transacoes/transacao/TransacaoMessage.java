package br.com.zupacademy.samara.transacoes.transacao;

import br.com.zupacademy.samara.transacoes.cartao.CartaoResponse;
import br.com.zupacademy.samara.transacoes.estabelecimento.EstabelecimentoResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoMessage {

    private String id;
    private BigDecimal valor;
    private EstabelecimentoResponse estabelecimento;
    private CartaoResponse cartao;
    private LocalDateTime efetivadaEm;

    @Deprecated
    public TransacaoMessage() {
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoResponse getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoResponse getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public Transacao toModel() {
        return new Transacao(id, valor, estabelecimento.toModel(), cartao.toModel(), efetivadaEm);
    }
}
