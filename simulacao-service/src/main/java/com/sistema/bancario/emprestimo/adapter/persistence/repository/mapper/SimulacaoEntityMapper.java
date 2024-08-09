package com.sistema.bancario.emprestimo.adapter.persistence.repository.mapper;

import com.sistema.bancario.emprestimo.adapter.persistence.repository.entity.RetornoSimulacaoAreaEntity;
import com.sistema.bancario.emprestimo.adapter.persistence.repository.entity.SimulacaoEntity;
import com.sistema.bancario.emprestimo.domain.RetornoSimulacaoArea;
import com.sistema.bancario.emprestimo.domain.StatusRetornoArea;
import com.sistema.bancario.emprestimo.domain.StatusSimulacao;
import com.sistema.bancario.emprestimo.ports.persistence.SimulacaoPersistenceDtoPort;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimulacaoEntityMapper {

    public static SimulacaoPersistenceDtoPort toOutput(SimulacaoEntity simulacaoEntity) {
        return SimulacaoPersistenceDtoPort.builder()
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

    public static SimulacaoEntity toEntity(SimulacaoPersistenceDtoPort simulacaoPersistenceDtoPort) {
        return SimulacaoEntity.builder()
                .idSimulacao(simulacaoPersistenceDtoPort.getId())
                .idConta(simulacaoPersistenceDtoPort.getIdConta())
                .email(simulacaoPersistenceDtoPort.getEmail())
                .valorSolicitado(simulacaoPersistenceDtoPort.getValorSolicitado())
                .dataHora(simulacaoPersistenceDtoPort.getDataHora().toString())
                .status(simulacaoPersistenceDtoPort.getStatus().name())
                .areasSolicitadasParaSimulacao(simulacaoPersistenceDtoPort.getAreasSolicitadasParaSimulacao())
                .retornosSimulacaoAreas(mapRetornosSimulacaoAreas(simulacaoPersistenceDtoPort))
                .build();
    }

    private static List<RetornoSimulacaoAreaEntity> mapRetornosSimulacaoAreas(SimulacaoPersistenceDtoPort simulacaoPersistenceDtoPort) {
        return CollectionUtils.isEmpty(simulacaoPersistenceDtoPort.getRetornosSimulacaoAreas()) ? new ArrayList<>() :
                simulacaoPersistenceDtoPort.getRetornosSimulacaoAreas().stream()
                        .map(retornoArea -> RetornoSimulacaoAreaEntity.builder()
                                .area(retornoArea.getArea())
                                .status(retornoArea.getStatusRetornoArea().name())
                                .motivo(retornoArea.getMotivo())
                                .dataHora(retornoArea.getDataHora().toString())
                                .build())
                        .collect(Collectors.toList());
    }
}
