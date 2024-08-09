package com.sistema.bancario.emprestimo.domain;

import lombok.*;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Simulacao {

    private String id;
    private String idConta;
    private String email;
    private BigDecimal valorSolicitado;
    private LocalDateTime dataHora;
    private StatusSimulacao status;
    private List<String> areasSolicitadasParaSimulacao;
    private List<RetornoSimulacaoArea> retornosSimulacaoAreas;

    public Simulacao(String idConta, String email, BigDecimal valorSolicitado) {
        this.id = UUID.randomUUID().toString();
        this.idConta = idConta;
        this.email = email;
        this.valorSolicitado = valorSolicitado;
        this.dataHora = LocalDateTime.now();
        this.status = StatusSimulacao.EM_ANALISE;
        this.areasSolicitadasParaSimulacao = new ArrayList<>();
        this.retornosSimulacaoAreas = new ArrayList<>();
    }

    public void definirAreasSolicitadas(List<String> areasSolicitadas) {
        if (!CollectionUtils.isEmpty(areasSolicitadas)) {
            this.areasSolicitadasParaSimulacao = areasSolicitadas;
        }
    }

    public void processarRetornoArea(RetornoSimulacaoArea retornoSimulacaoArea) {
        retornosSimulacaoAreas.stream()
                .filter(retornoArea -> retornoArea.getArea().equalsIgnoreCase(retornoSimulacaoArea.getArea()))
                .findFirst()
                .ifPresent(retornoArea -> new RuntimeException("Retorno de área já processado!"));

        retornosSimulacaoAreas = Optional.ofNullable(retornosSimulacaoAreas).orElseGet(ArrayList::new);
        retornosSimulacaoAreas.add(retornoSimulacaoArea);
    }

    public void processarUltimoRetornoDeArea() {
        boolean teveAlgumRetornoRejeitado = retornosSimulacaoAreas.stream()
                .map(retorno -> retorno.getStatusRetornoArea())
                .anyMatch(status -> StatusRetornoArea.REJEITADO.equals(status));

        this.status = teveAlgumRetornoRejeitado ? StatusSimulacao.REJEITADO : StatusSimulacao.APROVADO;
    }

    public boolean ehUltimoRetornoDeArea() {
        return areasSolicitadasParaSimulacao.size() == retornosSimulacaoAreas.size();
    }

    public boolean ehAreaSolicitadaParaSimulacao(RetornoSimulacaoArea retornoSimulacaoAreaSqsMessage) {
        return areasSolicitadasParaSimulacao.contains(retornoSimulacaoAreaSqsMessage.getArea());
    }
}
