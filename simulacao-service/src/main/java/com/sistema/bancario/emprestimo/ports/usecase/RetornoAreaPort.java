package com.sistema.bancario.emprestimo.ports.usecase;

import com.sistema.bancario.emprestimo.ports.usecase.model.RetornoAreaDtoPort;

public interface RetornoAreaPort {
    void processarRetornoArea(String idSimulacao, RetornoAreaDtoPort retornoAreaDtoPort);
}
