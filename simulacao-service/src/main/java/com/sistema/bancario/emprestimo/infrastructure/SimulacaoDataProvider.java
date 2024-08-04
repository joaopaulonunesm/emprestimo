package com.sistema.bancario.emprestimo.infrastructure;

import com.sistema.bancario.emprestimo.application.gateway.SimulacaoGateway;
import com.sistema.bancario.emprestimo.infrastructure.repository.mapper.SimulacaoEntityMapper;
import com.sistema.bancario.emprestimo.domain.Simulacao;
import com.sistema.bancario.emprestimo.infrastructure.repository.entity.SimulacaoEntity;
import com.sistema.bancario.emprestimo.infrastructure.repository.SimulacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SimulacaoDataProvider implements SimulacaoGateway {

    private final SimulacaoRepository simulacaoRepository;

    @Transactional
    @Override
    public Simulacao salvar(Simulacao simulacao) {

        SimulacaoEntity simulacaoEntity = SimulacaoEntityMapper.toEntity(simulacao);

        simulacaoEntity = simulacaoRepository.save(simulacaoEntity);

        return SimulacaoEntityMapper.toDomain(simulacaoEntity);
    }

    @Override
    public Optional<Simulacao> buscarPorId(String id) {
        Optional<SimulacaoEntity> simulacaoEntity = simulacaoRepository.findById(id);

        if (simulacaoEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(SimulacaoEntityMapper.toDomain(simulacaoEntity.get()));
    }
}
