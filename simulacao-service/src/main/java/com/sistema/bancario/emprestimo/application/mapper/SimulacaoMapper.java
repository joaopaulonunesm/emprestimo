package com.sistema.bancario.emprestimo.application.mapper;

import com.sistema.bancario.emprestimo.api.model.SimulacaoRequest;
import com.sistema.bancario.emprestimo.api.model.SimulacaoResponse;
import com.sistema.bancario.emprestimo.domain.Simulacao;

public class SimulacaoMapper {

    public static Simulacao toDomain(SimulacaoRequest simulacaoRequest) {
        return new Simulacao(simulacaoRequest.getIdConta(), simulacaoRequest.getEmail(), simulacaoRequest.getValorSolicitado());
    }

    public static SimulacaoResponse toRetorno(Simulacao simulacao) {
        return SimulacaoResponse.builder()
                .id(simulacao.getId())
                .idConta(simulacao.getIdConta())
                .email(simulacao.getEmail())
                .valorSolicitado(simulacao.getValorSolicitado())
                .dataHora(simulacao.getDataHora())
                .status(simulacao.getStatus())
                .areasSolicitadasParaSimulacao(simulacao.getAreasSolicitadasParaSimulacao())
                .retornosSimulacaoAreas(simulacao.getRetornosSimulacaoAreas())
                .build();
    }
}
