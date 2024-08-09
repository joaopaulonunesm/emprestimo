package com.sistema.bancario.emprestimo.adapter.producer;

import com.sistema.bancario.emprestimo.ports.notificacao.SimulacaoNotificacaoPort;
import com.sistema.bancario.emprestimo.domain.Simulacao;
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
public class SnsService implements SimulacaoNotificacaoPort {

    @Value("${sns.emprestimo}")
    private String snsEmprestimo;

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Override
    public void notificar(Simulacao simulacao, String evento) {
        try {
            Map<String, Object> headers = new HashMap<>();
            headers.put("evento", evento);
            notificationMessagingTemplate.convertAndSend(snsEmprestimo, simulacao, headers);
            log.info("Mensagem enviada ao tópico com sucesso. Simulacao={}", simulacao);
        } catch (Exception exception) {
            log.error("Erro ao enviar mensagem ao tópico.", exception);
        }
    }
}
