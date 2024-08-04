package com.sistema.bancario.emprestimo.infrastructure.repository.mapper;

import com.sistema.bancario.emprestimo.domain.Simulacao;
import com.sistema.bancario.emprestimo.domain.RetornoSimulacaoArea;
import com.sistema.bancario.emprestimo.domain.StatusRetornoArea;
import com.sistema.bancario.emprestimo.domain.StatusSimulacao;
import com.sistema.bancario.emprestimo.infrastructure.repository.entity.RetornoSimulacaoAreaEntity;
import com.sistema.bancario.emprestimo.infrastructure.repository.entity.SimulacaoEntity;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimulacaoEntityMapper {

    public static Simulacao toDomain(SimulacaoEntity simulacaoEntity) {
        return Simulacao.builder()
                .id(simulacaoEntity.getIdSimulacao())
                .idConta(simulacaoEntity.getIdConta())
                .email(simulacaoEntity.getEmail())
                .valorSolicitado(simulacaoEntity.getValorSolicitado())
                .dataHora(LocalDateTime.parse(simulacaoEntity.getDataHora()))
                .status(StatusSimulacao.valueOf(simulacaoEntity.getStatus()))
                .areasSolicitadasParaSimulacao(simulacaoEntity.getAreasSolicitadasParaSimulacao())
                .retornosSimulacaoAreas(mapRetornosAreas(simulacaoEntity))
                .build();
    }

    public static SimulacaoEntity toEntity(Simulacao simulacao) {
        return SimulacaoEntity.builder()
                .idSimulacao(simulacao.getId())
                .idConta(simulacao.getIdConta())
                .email(simulacao.getEmail())
                .valorSolicitado(simulacao.getValorSolicitado())
                .dataHora(simulacao.getDataHora().toString())
                .status(simulacao.getStatus().name())
                .areasSolicitadasParaSimulacao(simulacao.getAreasSolicitadasParaSimulacao())
                .retornosSimulacaoAreas(mapRetornosSimulacaoAreas(simulacao))
                .build();
    }

    private static List<RetornoSimulacaoArea> mapRetornosAreas(SimulacaoEntity simulacaoEntity) {
        return CollectionUtils.isEmpty(simulacaoEntity.getRetornosSimulacaoAreas()) ? new ArrayList<>() :
                simulacaoEntity.getRetornosSimulacaoAreas().stream()
                        .map(retornoSimulacaoArea -> RetornoSimulacaoArea.builder()
                                .area(retornoSimulacaoArea.getArea())
                                .statusRetornoArea(StatusRetornoArea.valueOf(retornoSimulacaoArea.getStatus()))
                                .motivo(retornoSimulacaoArea.getMotivo())
                                .dataHora(LocalDateTime.parse(retornoSimulacaoArea.getDataHora()))
                                .build())
                        .collect(Collectors.toList());
    }

    private static List<RetornoSimulacaoAreaEntity> mapRetornosSimulacaoAreas(Simulacao simulacao) {
        return CollectionUtils.isEmpty(simulacao.getRetornosSimulacaoAreas()) ? new ArrayList<>() :
                simulacao.getRetornosSimulacaoAreas().stream()
                        .map(retornoArea -> RetornoSimulacaoAreaEntity.builder()
                                .area(retornoArea.getArea())
                                .status(retornoArea.getStatusRetornoArea().name())
                                .motivo(retornoArea.getMotivo())
                                .dataHora(retornoArea.getDataHora().toString())
                                .build())
                        .collect(Collectors.toList());
    }
}
