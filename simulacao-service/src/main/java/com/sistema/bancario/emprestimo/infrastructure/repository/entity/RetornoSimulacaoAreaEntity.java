package com.sistema.bancario.emprestimo.infrastructure.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "emprestimo_retorno_simulacao_area")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetornoSimulacaoAreaEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String idRetornoSimulacaoArea;

    @ManyToOne
    @JoinColumn(name = "id_simulacao")
    private SimulacaoEntity simulacao;

    private String area;

    private String status;

    private String motivo;

    private LocalDateTime dataHora;
}
