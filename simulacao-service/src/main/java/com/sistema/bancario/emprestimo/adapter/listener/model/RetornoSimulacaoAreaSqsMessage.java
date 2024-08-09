package com.sistema.bancario.emprestimo.adapter.listener.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class RetornoSimulacaoAreaSqsMessage {

    private String idSimulacao;
    private String area;
    private String statusRetornoArea;
    private String motivo;
    private LocalDateTime dataHora;
}
