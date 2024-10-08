package com.sistema.bancario.emprestimo.domain;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class RetornoSimulacaoArea  {
    private String area;
    private StatusRetornoArea statusRetornoArea;
    private String motivo;
    private LocalDateTime dataHora;
}
