package com.sistema.bancario.emprestimo.usecases;

import com.sistema.bancario.emprestimo.domain.Simulacao;
import com.sistema.bancario.emprestimo.ports.notificacao.SimulacaoNotificacaoPort;
import com.sistema.bancario.emprestimo.ports.persistence.SimulacaoPeristencePort;
import com.sistema.bancario.emprestimo.ports.usecase.SimulacaoPort;
import com.sistema.bancario.emprestimo.ports.usecase.model.SimulacaoInputDtoPort;
import com.sistema.bancario.emprestimo.ports.notificacao.SimulacaoNotificacaoDtoPort;
import com.sistema.bancario.emprestimo.ports.usecase.model.SimulacaoOutputDtoPort;
import com.sistema.bancario.emprestimo.ports.persistence.SimulacaoPersistenceDtoPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimulacaoUseCase implements SimulacaoPort {

    private final AreaUseCase areaUseCase;
    private final SimulacaoPeristencePort simulacaoPeristencePort;
    private final SimulacaoNotificacaoPort simulacaoNotificacaoPort;

    @Override
    public SimulacaoOutputDtoPort solicitarSimulacao(SimulacaoInputDtoPort simulacaoInputDtoPort) {
        log.info("Processando solicitação de simulação de emprestimo. SimulacaoRequest={}", simulacaoInputDtoPort);

        Simulacao simulacao = new Simulacao(simulacaoInputDtoPort.getIdConta(), simulacaoInputDtoPort.getEmail(), simulacaoInputDtoPort.getValorSolicitado());

        List<String> areasSolicitadas = areaUseCase.getResponsaveisPelasAnalises();
        simulacao.definirAreasSolicitadas(areasSolicitadas);

        simulacaoPeristencePort.salvar(domainToPersistencePort(simulacao));

        simulacaoNotificacaoPort.notificar(domainToNotificacaoPort(simulacao), "analise-simulacao-emprestimo-solicitada");

        log.info("Solicitação de simulação de emprestimo processada. Simulacao={}", simulacao);
        return domainToOutputPort(simulacao);
    }

    @Override
    public SimulacaoOutputDtoPort buscarPorId(String idSimulacao) {
        Optional<SimulacaoPersistenceDtoPort> simulacao = simulacaoPeristencePort.buscarPorId(idSimulacao);

        if (simulacao.isEmpty()) {
            return null;
        }

        return simulacaoPersistenceDtoPortToOutputPort(simulacao.get());
    }

    private SimulacaoOutputDtoPort simulacaoPersistenceDtoPortToOutputPort(SimulacaoPersistenceDtoPort simulacaoPersistenceDtoPort) {
        return null;
    }


    private SimulacaoOutputDtoPort domainToOutputPort(Simulacao simulacao) {
        return null;
    }

    private SimulacaoNotificacaoDtoPort domainToNotificacaoPort(Simulacao simulacao) {
        return null;
    }

    private SimulacaoPersistenceDtoPort domainToPersistencePort(Simulacao simulacao) {
        return null;
    }
}
