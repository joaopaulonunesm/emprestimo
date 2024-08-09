package com.sistema.bancario.emprestimo.ports.usecase.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class SimulacaoInputDtoPort {
    private String idConta;
    private String email;
    private BigDecimal valorSolicitado;
}
