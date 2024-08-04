package com.sistema.bancario.emprestimo.domain;

import lombok.*;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Simulacao {

    private static final long serialVersionUID = -1L;

    private String id;
    private String idConta;
    private String email;
    private BigDecimal valorSolicitado;
    private LocalDateTime dataHora;
    private StatusSimulacao status;
    private List<String> areasSolicitadasParaSimulacao;
    private List<RetornoSimulacaoArea> retornosSimulacaoAreas;
}
