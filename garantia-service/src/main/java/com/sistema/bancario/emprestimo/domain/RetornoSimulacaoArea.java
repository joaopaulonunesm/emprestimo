package com.sistema.bancario.emprestimo.domain;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class RetornoSimulacaoArea {

    private String id;
    private String idSimulacao;
    private String area;
    private StatusRetornoArea statusRetornoArea;
    private String motivo;
    private LocalDateTime dataHora;
}
