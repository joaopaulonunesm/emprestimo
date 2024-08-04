package com.sistema.bancario.emprestimo.infrastructure;

import com.sistema.bancario.emprestimo.application.MensageriaGateway;
import com.sistema.bancario.emprestimo.domain.RetornoSimulacaoArea;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class SNSService implements MensageriaGateway {

    @Value("${sns.emprestimo}")
    private String snsEmprestimo;

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Override
    public void enviarMensagem(RetornoSimulacaoArea retornoSimulacaoArea) {
        try {
            Map<String, Object> headers = new HashMap<>();
            headers.put("evento", "analise-simulacao-emprestimo-concluida-pela-area");
            notificationMessagingTemplate.convertAndSend(snsEmprestimo, retornoSimulacaoArea, headers);
            log.info("Mensagem enviada ao tópico com sucesso. SimulacaoRetornoArea={}", retornoSimulacaoArea);
        } catch (Exception exception) {
            log.error("Erro ao enviar mensagem ao tópico.", exception);
        }
    }
}
