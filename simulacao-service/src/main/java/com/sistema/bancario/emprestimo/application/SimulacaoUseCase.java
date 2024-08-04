package com.sistema.bancario.emprestimo.application;

import com.sistema.bancario.emprestimo.api.model.SimulacaoRequest;
import com.sistema.bancario.emprestimo.api.model.SimulacaoResponse;
import com.sistema.bancario.emprestimo.application.gateway.MensageriaGateway;
import com.sistema.bancario.emprestimo.application.gateway.SimulacaoGateway;
import com.sistema.bancario.emprestimo.application.mapper.SimulacaoMapper;
import com.sistema.bancario.emprestimo.domain.RetornoSimulacaoArea;
import com.sistema.bancario.emprestimo.domain.Simulacao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimulacaoUseCase {

    private final AreaUseCase areaUseCase;
    private final SimulacaoGateway simulacaoGateway;
    private final MensageriaGateway mensageriaGateway;

    public SimulacaoResponse solicitarSimulacao(SimulacaoRequest simulacaoRequest) {
        log.info("Processando solicitação de simulação de emprestimo. SimulacaoRequest={}", simulacaoRequest);

        Simulacao simulacao = SimulacaoMapper.toDomain(simulacaoRequest);

        List<String> areasSolicitadas = areaUseCase.getResponsaveisPelasAnalises();
        simulacao.definirAreasSolicitadas(areasSolicitadas);

        simulacao = simulacaoGateway.salvar(simulacao);

        mensageriaGateway.enviarMensagem(simulacao, "analise-simulacao-emprestimo-solicitada");

        SimulacaoResponse simulacaoResponse = SimulacaoMapper.toRetorno(simulacao);

        log.info("Solicitação de simulação de emprestimo processada. SimulacaoResponse={}", simulacaoResponse);
        return simulacaoResponse;
    }

    public SimulacaoResponse buscarSimulacao(String id) {

        Optional<Simulacao> simulacao = simulacaoGateway.buscarPorId(id);

        if (simulacao.isEmpty()) {
            return null;
        }

        return SimulacaoMapper.toRetorno(simulacao.get());
    }

    public void processarRetornoArea(RetornoSimulacaoArea retornoSimulacaoArea) {

        Simulacao simulacao = simulacaoGateway.buscarPorId(retornoSimulacaoArea.getIdSimulacao())
                .orElseThrow(() -> new RuntimeException("Retorno de área para uma simulação que não existe!"));

        if (!simulacao.areaFoiSolicitadaParaAnalise(retornoSimulacaoArea)) {
            log.info("Area retornou uma analise que não foi solicitada.");
            return;
        }

        simulacao.processarRetornoArea(retornoSimulacaoArea);

        if (simulacao.ehUltimoRetornoDeArea()) {
            simulacao.processarUltimoRetornoDeArea();
        }

        simulacao = simulacaoGateway.salvar(simulacao);

        if (simulacao.ehUltimoRetornoDeArea()) {
            mensageriaGateway.enviarMensagem(simulacao, "analise-simulacao-emprestimo-concluida");
        }

        log.info("Retorno da area processado com sucesso concluida! Simulação={}", simulacao);
    }
}
