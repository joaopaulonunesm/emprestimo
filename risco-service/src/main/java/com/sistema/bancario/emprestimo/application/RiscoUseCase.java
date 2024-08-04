package com.sistema.bancario.emprestimo.application;

import com.sistema.bancario.emprestimo.domain.Simulacao;
import com.sistema.bancario.emprestimo.domain.RetornoSimulacaoArea;
import com.sistema.bancario.emprestimo.domain.StatusRetornoArea;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiscoUseCase {

    private final MensageriaGateway mensageriaGateway;

    public void analisar(Simulacao simulacao) {
        log.info("Analisando Risco para Simulação={}", simulacao);

        RetornoSimulacaoArea retornoSimulacaoArea = RetornoSimulacaoArea.builder()
                .idSimulacao(simulacao.getId())
                .area("RISCO")
                .statusRetornoArea(StatusRetornoArea.APROVADO)
                .dataHora(LocalDateTime.now())
                .build();

        mensageriaGateway.enviarMensagem(retornoSimulacaoArea);

        log.info("Analise concluida! Simulação={}", simulacao);
    }
}
