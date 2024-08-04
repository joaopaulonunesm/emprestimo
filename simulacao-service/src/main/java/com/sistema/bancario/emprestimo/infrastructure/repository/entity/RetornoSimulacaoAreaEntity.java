package com.sistema.bancario.emprestimo.infrastructure.repository.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamoDBDocument
public class RetornoSimulacaoAreaEntity {

    @DynamoDBAttribute(attributeName = "area")
    private String area;

    @DynamoDBAttribute(attributeName = "status")
    private String status;

    @DynamoDBAttribute(attributeName = "motivo")
    private String motivo;

    @DynamoDBAttribute(attributeName = "data_hora")
    private String dataHora;
}
