package com.sistema.bancario.emprestimo.api;

import com.amazon.sqs.javamessaging.message.SQSTextMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.bancario.emprestimo.domain.Simulacao;
import com.sistema.bancario.emprestimo.application.RiscoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RiscoListener {

    private final ObjectMapper objectMapper;

    private final RiscoUseCase riscoUseCase;

    @JmsListener(destination = "${sqs.risco}")
    public void riscoListener(SQSTextMessage textMessage) throws Exception {
        if (!textMessage.getStringProperty("evento").equalsIgnoreCase("analise-simulacao-emprestimo-solicitada")) {
            return;
        }

        log.info("Recebendo mensagem para analise de risco. Mensagem={}", textMessage.getText());

        Simulacao simulacao = objectMapper.readValue(textMessage.getText(), Simulacao.class);

        riscoUseCase.analisar(simulacao);
    }
}
