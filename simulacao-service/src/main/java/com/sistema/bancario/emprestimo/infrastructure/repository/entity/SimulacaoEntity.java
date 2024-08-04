package com.sistema.bancario.emprestimo.infrastructure.repository.entity;

import com.sistema.bancario.emprestimo.domain.StatusSimulacao;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "emprestimo_simulacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimulacaoEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String idSimulacao;

    private String idConta;

    private String email;

    private BigDecimal valorSolicitado;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusSimulacao status;

    private List<String> areasSolicitadasParaSimulacao;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "simulacao", cascade = CascadeType.ALL)
    private List<RetornoSimulacaoAreaEntity> retornosSimulacaoAreas;
}
