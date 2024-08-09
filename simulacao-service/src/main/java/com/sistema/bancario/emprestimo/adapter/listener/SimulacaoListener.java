package com.sistema.bancario.emprestimo.adapter.listener;

import com.amazon.sqs.javamessaging.message.SQSTextMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.bancario.emprestimo.adapter.listener.model.RetornoSimulacaoAreaSqsMessage;
import com.sistema.bancario.emprestimo.ports.usecase.RetornoAreaPort;
import com.sistema.bancario.emprestimo.ports.usecase.model.RetornoAreaDtoPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimulacaoListener {

    private final ObjectMapper objectMapper;
    private final RetornoAreaPort retornoAreaPort;

    @JmsListener(destination = "${sqs.simulacao}")
    public void simulacaoListener(SQSTextMessage textMessage) throws Exception {
        if (!textMessage.getStringProperty("evento").equalsIgnoreCase("analise-simulacao-emprestimo-concluida-pela-area")) {
            return;
        }

        log.info("Recebendo mensagem de retorno das areas solicitadas. Mensagem={}", textMessage.getText());

        RetornoSimulacaoAreaSqsMessage retornoSimulacaoAreaSqsMessage = objectMapper.readValue(textMessage.getText(), RetornoSimulacaoAreaSqsMessage.class);

        RetornoAreaDtoPort retornoAreaDtoPort = toInputPort(retornoSimulacaoAreaSqsMessage);

        retornoAreaPort.processarRetornoArea(retornoSimulacaoAreaSqsMessage.getIdSimulacao(), retornoAreaDtoPort);
    }

    private RetornoAreaDtoPort toInputPort(RetornoSimulacaoAreaSqsMessage retornoSimulacaoAreaSqsMessage) {
        return RetornoAreaDtoPort.builder()
                .area(retornoSimulacaoAreaSqsMessage.getArea())
                .statusRetornoArea(retornoSimulacaoAreaSqsMessage.getStatusRetornoArea())
                .motivo(retornoSimulacaoAreaSqsMessage.getMotivo())
                .dataHora(retornoSimulacaoAreaSqsMessage.getDataHora())
                .build();
    }
}
