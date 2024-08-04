package com.sistema.bancario.emprestimo.api;

import com.amazon.sqs.javamessaging.message.SQSTextMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.bancario.emprestimo.application.SimulacaoUseCase;
import com.sistema.bancario.emprestimo.domain.RetornoSimulacaoArea;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimulacaoListener {

    private final ObjectMapper objectMapper;

    private final SimulacaoUseCase simulacaoUseCase;

    @JmsListener(destination = "${sqs.simulacao}")
    public void simulacaoListener(SQSTextMessage textMessage) throws Exception {
        if (!textMessage.getStringProperty("evento").equalsIgnoreCase("analise-simulacao-emprestimo-concluida-pela-area")) {
            return;
        }

        log.info("Recebendo mensagem de retorno das areas solicitadas. Mensagem={}", textMessage.getText());

        RetornoSimulacaoArea retornoSimulacaoArea = objectMapper.readValue(textMessage.getText(), RetornoSimulacaoArea.class);

        simulacaoUseCase.processarRetornoArea(retornoSimulacaoArea);

    }
}
