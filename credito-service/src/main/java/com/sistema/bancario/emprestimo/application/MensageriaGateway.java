package com.sistema.bancario.emprestimo.application;

import com.sistema.bancario.emprestimo.domain.RetornoSimulacaoArea;

public interface MensageriaGateway {
    void enviarMensagem(RetornoSimulacaoArea retornoSimulacaoArea);
}
