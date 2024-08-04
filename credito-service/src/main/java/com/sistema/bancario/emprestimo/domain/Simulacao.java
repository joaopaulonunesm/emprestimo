package com.sistema.bancario.emprestimo.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Simulacao {

    private String id;
    private String idConta;
    private String email;
    private BigDecimal valorSolicitado;
    private LocalDateTime dataHora;
    private String status;
    private List<String> areasSolicitadasParaSimulacao;
    private List<RetornoSimulacaoArea> retornosSimulacaoAreas;
}
