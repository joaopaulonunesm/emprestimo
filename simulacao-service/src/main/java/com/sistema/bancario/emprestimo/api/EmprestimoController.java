package com.sistema.bancario.emprestimo.api;

import com.sistema.bancario.emprestimo.api.model.SimulacaoRequest;
import com.sistema.bancario.emprestimo.api.model.SimulacaoResponse;
import com.sistema.bancario.emprestimo.application.SimulacaoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimo/v1")
@RequiredArgsConstructor
public class EmprestimoController {

    private final SimulacaoUseCase simulacaoUseCase;

    @PostMapping("/simular")
    public ResponseEntity<SimulacaoResponse> passarMaquininha(@RequestBody SimulacaoRequest simulacaoRequest) {
        return ResponseEntity.ok(simulacaoUseCase.solicitarSimulacao(simulacaoRequest));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<SimulacaoResponse> statusSimulacao(@PathVariable("id") String id) {
        return ResponseEntity.ok(simulacaoUseCase.buscarSimulacao(id));
    }
}
