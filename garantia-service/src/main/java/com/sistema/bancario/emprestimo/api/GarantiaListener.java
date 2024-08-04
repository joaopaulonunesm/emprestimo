package com.sistema.bancario.emprestimo.api;

import com.amazon.sqs.javamessaging.message.SQSTextMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistema.bancario.emprestimo.domain.Simulacao;
import com.sistema.bancario.emprestimo.application.GarantiaUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GarantiaListener {

    private final ObjectMapper objectMapper;

    private final GarantiaUseCase garantiaUseCase;

    @JmsListener(destination = "${sqs.garantia}")
    public void garantiaListener(SQSTextMessage textMessage) throws Exception {
        if (!textMessage.getStringProperty("evento").equalsIgnoreCase("analise-simulacao-emprestimo-solicitada")) {
            return;
        }

        log.info("Recebendo mensagem para analise de garantia. Mensagem={}", textMessage.getText());

        Simulacao simulacao = objectMapper.readValue(textMessage.getText(), Simulacao.class);

        garantiaUseCase.analisar(simulacao);

    }
}
