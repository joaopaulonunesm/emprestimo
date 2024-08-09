package com.sistema.bancario.emprestimo.ports.notificacao;

import com.sistema.bancario.emprestimo.domain.RetornoSimulacaoArea;
import com.sistema.bancario.emprestimo.domain.StatusSimulacao;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class SimulacaoNotificacaoDtoPort {

    private String id;
    private String idConta;
    private String email;
    private BigDecimal valorSolicitado;
    private LocalDateTime dataHora;
    private StatusSimulacao status;
    private List<String> areasSolicitadasParaSimulacao;
    private List<RetornoSimulacaoArea> retornosSimulacaoAreas;
}
