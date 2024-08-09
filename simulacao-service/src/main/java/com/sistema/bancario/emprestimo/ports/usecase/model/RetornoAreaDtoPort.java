package com.sistema.bancario.emprestimo.ports.usecase.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class RetornoAreaDtoPort {
    private String area;
    private String statusRetornoArea;
    private String motivo;
    private LocalDateTime dataHora;
}
