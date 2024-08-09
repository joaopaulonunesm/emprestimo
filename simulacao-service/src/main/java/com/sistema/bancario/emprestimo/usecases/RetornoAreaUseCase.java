package com.sistema.bancario.emprestimo.usecases;

import com.sistema.bancario.emprestimo.domain.RetornoSimulacaoArea;
import com.sistema.bancario.emprestimo.domain.Simulacao;
import com.sistema.bancario.emprestimo.domain.StatusRetornoArea;
import com.sistema.bancario.emprestimo.ports.usecase.RetornoAreaPort;
import com.sistema.bancario.emprestimo.ports.notificacao.SimulacaoNotificacaoPort;
import com.sistema.bancario.emprestimo.ports.persistence.SimulacaoPeristencePort;
import com.sistema.bancario.emprestimo.ports.usecase.model.RetornoAreaDtoPort;
import com.sistema.bancario.emprestimo.ports.notificacao.SimulacaoNotificacaoDtoPort;
import com.sistema.bancario.emprestimo.ports.persistence.SimulacaoPersistenceDtoPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RetornoAreaUseCase implements RetornoAreaPort {

    private final SimulacaoPeristencePort simulacaoPeristencePort;
    private final SimulacaoNotificacaoPort simulacaoNotificacaoPort;

    @Override
    public void processarRetornoArea(String idSimulacao, RetornoAreaDtoPort retornoAreaDtoPort) {
        SimulacaoPersistenceDtoPort simulacaoPersistenceDtoPort = simulacaoPeristencePort.buscarPorId(idSimulacao).orElseThrow(() -> new RuntimeException("Retorno de área para uma simulação que não existe!"));

        Simulacao simulacao = simulacaoPersistenceDtoPortToDomain(simulacaoPersistenceDtoPort);
        RetornoSimulacaoArea retornoSimulacaoArea = retornoAreaDtoPortToDomain(retornoAreaDtoPort);

        if (!simulacao.ehAreaSolicitadaParaSimulacao(retornoSimulacaoArea)) {
            log.info("Area retornou uma analise que não foi solicitada. Area: {}", retornoSimulacaoArea.getArea());
            return;
        }

        simulacao.processarRetornoArea(retornoSimulacaoArea);

        if (simulacao.ehUltimoRetornoDeArea()) {
            simulacao.processarUltimoRetornoDeArea();
        }

        simulacaoPeristencePort.salvar(domainToPersistencePort(simulacao));

        if (simulacao.ehUltimoRetornoDeArea()) {
            simulacaoNotificacaoPort.notificar(domainToNotificacaoPort(simulacao), "analise-simulacao-emprestimo-concluida");
        }

        log.info("Retorno da area processado com sucesso concluida! Simulação={}", simulacao);
    }

    private SimulacaoNotificacaoDtoPort domainToNotificacaoPort(Simulacao simulacao) {
        return null;
    }

    private SimulacaoPersistenceDtoPort domainToPersistencePort(Simulacao simulacao) {
        return null;
    }

    private Simulacao simulacaoPersistenceDtoPortToDomain(SimulacaoPersistenceDtoPort simulacaoPersistenceDtoPort) {
        return Simulacao.builder()
                .id(simulacaoPersistenceDtoPort.getId())
                .idConta(simulacaoPersistenceDtoPort.getIdConta())
                .email(simulacaoPersistenceDtoPort.getEmail())
                .valorSolicitado(simulacaoPersistenceDtoPort.getValorSolicitado())
                .dataHora(simulacaoPersistenceDtoPort.getDataHora())
                .status(simulacaoPersistenceDtoPort.getStatus())
                .areasSolicitadasParaSimulacao(simulacaoPersistenceDtoPort.getAreasSolicitadasParaSimulacao())
                .retornosSimulacaoAreas(simulacaoPersistenceDtoPort.getRetornosSimulacaoAreas())
                .build();
    }

    private RetornoSimulacaoArea retornoAreaDtoPortToDomain(RetornoAreaDtoPort retornoAreaDtoPort) {
        return RetornoSimulacaoArea.builder()
                .area(retornoAreaDtoPort.getArea())
                .statusRetornoArea(StatusRetornoArea.valueOf(retornoAreaDtoPort.getStatusRetornoArea()))
                .motivo(retornoAreaDtoPort.getMotivo())
                .dataHora(retornoAreaDtoPort.getDataHora())
                .build();
    }
}
