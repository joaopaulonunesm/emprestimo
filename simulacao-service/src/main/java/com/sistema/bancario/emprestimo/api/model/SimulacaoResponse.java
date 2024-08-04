package com.sistema.bancario.emprestimo.api.model;

import com.sistema.bancario.emprestimo.domain.RetornoSimulacaoArea;
import com.sistema.bancario.emprestimo.domain.StatusSimulacao;
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
    private StatusSimulacao status;
    private List<String> areasSolicitadasParaSimulacao;
    private List<RetornoSimulacaoArea> retornosSimulacaoAreas;
}
