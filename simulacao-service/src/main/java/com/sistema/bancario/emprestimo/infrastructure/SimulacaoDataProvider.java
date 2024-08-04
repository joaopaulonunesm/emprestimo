package com.sistema.bancario.emprestimo.infrastructure;

import com.sistema.bancario.emprestimo.application.gateway.SimulacaoGateway;
import com.sistema.bancario.emprestimo.infrastructure.repository.entity.RetornoSimulacaoAreaEntity;
import com.sistema.bancario.emprestimo.infrastructure.repository.entity.SimulacaoEntity;
import com.sistema.bancario.emprestimo.infrastructure.repository.SimulacaoRepository;
import com.sistema.bancario.emprestimo.infrastructure.repository.mapper.SimulacaoEntityMapper;
import com.sistema.bancario.emprestimo.domain.Simulacao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimulacaoDataProvider implements SimulacaoGateway {

    private final SimulacaoRepository simulacaoRepository;

    @Transactional
    @Override
    public Simulacao salvar(Simulacao simulacao) {

        SimulacaoEntity simulacaoEntity = SimulacaoEntityMapper.toEntity(simulacao);

        simulacaoRepository.save(simulacaoEntity);

        return SimulacaoEntityMapper.toDomain(simulacaoEntity);
    }

    @Override
    public Optional<Simulacao> buscarPorId(String id) {
        SimulacaoEntity simulacaoEntity = simulacaoRepository.getById(id);

        if (simulacaoEntity == null) {
            return Optional.empty();
        }

        return Optional.of(SimulacaoEntityMapper.toDomain(simulacaoEntity));
    }
}
