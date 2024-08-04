package com.sistema.bancario.emprestimo.application.gateway;

import com.sistema.bancario.emprestimo.domain.Simulacao;

import java.util.Optional;

public interface SimulacaoGateway {
    Simulacao salvar(Simulacao simulacao);
    Optional<Simulacao> buscarPorId(String id);
}
