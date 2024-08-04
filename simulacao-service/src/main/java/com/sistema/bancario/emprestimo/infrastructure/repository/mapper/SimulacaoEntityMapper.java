package com.sistema.bancario.emprestimo.infrastructure.repository.mapper;

import com.sistema.bancario.emprestimo.domain.Simulacao;
import com.sistema.bancario.emprestimo.domain.RetornoSimulacaoArea;
import com.sistema.bancario.emprestimo.domain.StatusRetornoArea;
import com.sistema.bancario.emprestimo.infrastructure.repository.entity.RetornoSimulacaoAreaEntity;
import com.sistema.bancario.emprestimo.infrastructure.repository.entity.SimulacaoEntity;
import org.springframework.util.CollectionUtils;

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
                .dataHora(simulacaoEntity.getDataHora())
                .status(simulacaoEntity.getStatus())
                .areasSolicitadasParaSimulacao(simulacaoEntity.getAreasSolicitadasParaSimulacao())
                .retornosSimulacaoAreas(mapRetornosAreas(simulacaoEntity))
                .build();
    }

    public static SimulacaoEntity toEntity(Simulacao simulacao) {
        SimulacaoEntity simulacaoEntity = SimulacaoEntity.builder()
                .idSimulacao(simulacao.getId())
                .idConta(simulacao.getIdConta())
                .email(simulacao.getEmail())
                .valorSolicitado(simulacao.getValorSolicitado())
                .dataHora(simulacao.getDataHora())
                .status(simulacao.getStatus())
                .areasSolicitadasParaSimulacao(simulacao.getAreasSolicitadasParaSimulacao())
                .build();

        simulacaoEntity.setRetornosSimulacaoAreas(mapRetornosSimulacaoAreas(simulacao, simulacaoEntity));

        return simulacaoEntity;
    }

    private static List<RetornoSimulacaoArea> mapRetornosAreas(SimulacaoEntity simulacaoEntity) {
        return CollectionUtils.isEmpty(simulacaoEntity.getRetornosSimulacaoAreas()) ? new ArrayList<>() :
                simulacaoEntity.getRetornosSimulacaoAreas().stream()
                        .map(retornoSimulacaoArea -> RetornoSimulacaoArea.builder()
                                .id(retornoSimulacaoArea.getIdRetornoSimulacaoArea())
                                .idSimulacao(retornoSimulacaoArea.getSimulacao().getIdSimulacao())
                                .area(retornoSimulacaoArea.getArea())
                                .statusRetornoArea(StatusRetornoArea.valueOf(retornoSimulacaoArea.getStatus()))
                                .motivo(retornoSimulacaoArea.getMotivo())
                                .dataHora(retornoSimulacaoArea.getDataHora())
                                .build())
                        .collect(Collectors.toList());
    }

    private static List<RetornoSimulacaoAreaEntity> mapRetornosSimulacaoAreas(Simulacao simulacao, SimulacaoEntity simulacaoEntity) {
        return CollectionUtils.isEmpty(simulacao.getRetornosSimulacaoAreas()) ? new ArrayList<>() :
                simulacao.getRetornosSimulacaoAreas().stream()
                        .map(retornoArea -> RetornoSimulacaoAreaEntity.builder()
                                .idRetornoSimulacaoArea(retornoArea.getId())
                                .area(retornoArea.getArea())
                                .status(retornoArea.getStatusRetornoArea().name())
                                .motivo(retornoArea.getMotivo())
                                .simulacao(simulacaoEntity)
                                .dataHora(retornoArea.getDataHora())
                                .build())
                        .collect(Collectors.toList());
    }
}
