package br.com.zupacademy.samara.transacoes.transacao;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @GetMapping("/{numero}")
    public ResponseEntity consultarCompras(@PathVariable("numero") String numero) {
        Pageable paginacao = PageRequest.of(0, 10);
        Page<Transacao> transacoes = transacaoRepository.findByCartaoNumeroOrderByEfetivadaEmDesc(numero, paginacao);
        if(transacoes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<TransacaoResponse> transacoesResponse = transacoes
                .stream()
                .map(TransacaoResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(transacoesResponse);
    }
}
