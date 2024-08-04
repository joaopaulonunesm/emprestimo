package com.sistema.bancario.emprestimo.application.gateway;

import com.sistema.bancario.emprestimo.domain.Simulacao;

public interface MensageriaGateway {
    void enviarMensagem(Simulacao dadosSimulacao, String evento);
}
