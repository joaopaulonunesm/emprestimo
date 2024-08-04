package com.sistema.bancario.emprestimo.infrastructure.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.sistema.bancario.emprestimo.infrastructure.repository.entity.SimulacaoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SimulacaoRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public void save(SimulacaoEntity entity) {
        dynamoDBMapper.save(entity);
    }

    public SimulacaoEntity getById(String idSimulacao) {
        return dynamoDBMapper.load(SimulacaoEntity.class, idSimulacao);
    }
}
