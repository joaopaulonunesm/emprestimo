package com.sistema.bancario.emprestimo.ports.notificacao;

public interface SimulacaoNotificacaoPort {
    void notificar(SimulacaoNotificacaoDtoPort simulacaoNotificacaoDtoPort, String evento);
}
