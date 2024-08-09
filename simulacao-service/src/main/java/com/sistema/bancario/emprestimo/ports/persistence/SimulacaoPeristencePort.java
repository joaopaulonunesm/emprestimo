package com.sistema.bancario.emprestimo.ports.persistence;

import java.util.Optional;

public interface SimulacaoPeristencePort {
    void salvar(SimulacaoPersistenceDtoPort simulacao);

    Optional<SimulacaoPersistenceDtoPort> buscarPorId(String id);
}
