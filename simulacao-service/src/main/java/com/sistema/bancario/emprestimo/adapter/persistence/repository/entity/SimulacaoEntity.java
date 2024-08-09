package com.sistema.bancario.emprestimo.adapter.persistence.repository.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamoDBTable(tableName = "tb_simulacao")
public class SimulacaoEntity {

    @DynamoDBHashKey(attributeName = "id_simulacao")
    private String idSimulacao;

    @DynamoDBAttribute(attributeName = "id_conta")
    private String idConta;

    @DynamoDBAttribute(attributeName = "email")
    private String email;

    @DynamoDBAttribute(attributeName = "valor_solicitado")
    private BigDecimal valorSolicitado;

    @DynamoDBAttribute(attributeName = "data_hora")
    private String dataHora;

    @DynamoDBAttribute(attributeName = "status")
    private String status;

    @DynamoDBAttribute(attributeName = "areas_solicitadas_para_simulacao")
    private List<String> areasSolicitadasParaSimulacao;

    @DynamoDBAttribute(attributeName = "retornos_simulacao_areas")
    private List<RetornoSimulacaoAreaEntity> retornosSimulacaoAreas;
}
