package com.sistema.bancario.emprestimo.application;

import com.sistema.bancario.emprestimo.domain.RetornoSimulacaoArea;
import com.sistema.bancario.emprestimo.domain.Simulacao;

public interface MensageriaGateway {
    void enviarMensagem(RetornoSimulacaoArea retornoSimulacaoArea);
}
