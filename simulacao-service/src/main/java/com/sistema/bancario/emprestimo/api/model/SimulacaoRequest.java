package com.sistema.bancario.emprestimo.api.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class SimulacaoRequest {
    private String idConta;
    private String email;
    private BigDecimal valorSolicitado;
}
