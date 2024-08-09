package com.sistema.bancario.emprestimo.adapter.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class SimulacaoResponse {

    private String id;
    private String idConta;
    private String email;
    private BigDecimal valorSolicitado;
    private LocalDateTime dataHora;
    private String status;
    private List<String> areasSolicitadasParaSimulacao;
    private List<RetornoSimulacaoArea> retornosSimulacaoAreas;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class RetornoSimulacaoArea {
        private String area;
        private String statusRetornoArea;
        private String motivo;
        private LocalDateTime dataHora;
    }
}
