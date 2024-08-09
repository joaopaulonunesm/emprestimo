package com.sistema.bancario.emprestimo.ports.usecase;

import com.sistema.bancario.emprestimo.ports.usecase.model.SimulacaoInputDtoPort;
import com.sistema.bancario.emprestimo.ports.usecase.model.SimulacaoOutputDtoPort;

public interface SimulacaoPort {
    SimulacaoOutputDtoPort solicitarSimulacao(SimulacaoInputDtoPort simulacaoInputDtoPort);

    SimulacaoOutputDtoPort buscarPorId(String idSimulacao);
}
