package com.sistema.bancario.emprestimo.adapter.web;

import com.sistema.bancario.emprestimo.adapter.web.model.SimulacaoRequest;
import com.sistema.bancario.emprestimo.adapter.web.model.SimulacaoResponse;
import com.sistema.bancario.emprestimo.ports.usecase.SimulacaoPort;
import com.sistema.bancario.emprestimo.ports.usecase.model.SimulacaoInputDtoPort;
import com.sistema.bancario.emprestimo.ports.usecase.model.SimulacaoOutputDtoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/simulacoes")
@RequiredArgsConstructor
public class EmprestimoController {

    private final SimulacaoPort simulacaoPort;

    @PostMapping
    public ResponseEntity<SimulacaoResponse> solicitarSimulacao(@RequestBody SimulacaoRequest simulacaoRequest) {
        SimulacaoInputDtoPort simulacaoInputDtoPort = SimulacaoInputDtoPort.builder()
                .idConta(simulacaoRequest.getIdConta())
                .email(simulacaoRequest.getEmail())
                .valorSolicitado(simulacaoRequest.getValorSolicitado())
                .build();

        SimulacaoOutputDtoPort simulacao = simulacaoPort.solicitarSimulacao(simulacaoInputDtoPort);

        SimulacaoResponse simulacaoResponse = toResponse(simulacao);

        return ResponseEntity.ok(simulacaoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimulacaoResponse> statusSimulacao(@PathVariable("id") String idSimulacao) {
        SimulacaoOutputDtoPort simulacao = simulacaoPort.buscarPorId(idSimulacao);
        return ResponseEntity.ok(toResponse(simulacao));
    }

    private SimulacaoResponse toResponse(SimulacaoOutputDtoPort simulacao) {
        return SimulacaoResponse.builder()
                .id(simulacao.getId())
                .idConta(simulacao.getIdConta())
                .email(simulacao.getEmail())
                .valorSolicitado(simulacao.getValorSolicitado())
                .dataHora(simulacao.getDataHora())
                .status(simulacao.getStatus().name())
                .areasSolicitadasParaSimulacao(simulacao.getAreasSolicitadasParaSimulacao())
                .retornosSimulacaoAreas(
                        simulacao.getRetornosSimulacaoAreas().stream()
                                .map(retornoSimulacaoArea -> SimulacaoResponse.RetornoSimulacaoArea.builder()
                                        .area(retornoSimulacaoArea.getArea())
                                        .statusRetornoArea(retornoSimulacaoArea.getStatusRetornoArea().name())
                                        .motivo(retornoSimulacaoArea.getMotivo())
                                        .dataHora(retornoSimulacaoArea.getDataHora())
                                        .build())
                                .collect(Collectors.toList())
                )
                .build();
    }
}
