package com.sistema.bancario.emprestimo.adapter.persistence;

import com.sistema.bancario.emprestimo.adapter.persistence.repository.SimulacaoRepository;
import com.sistema.bancario.emprestimo.adapter.persistence.repository.entity.SimulacaoEntity;
import com.sistema.bancario.emprestimo.adapter.persistence.repository.mapper.SimulacaoEntityMapper;
import com.sistema.bancario.emprestimo.ports.persistence.SimulacaoPeristencePort;
import com.sistema.bancario.emprestimo.ports.persistence.SimulacaoPersistenceDtoPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimulacaoDataProvider implements SimulacaoPeristencePort {

    private final SimulacaoRepository simulacaoRepository;

    @Transactional
    @Override
    public void salvar(SimulacaoPersistenceDtoPort simulacaoPersistenceDtoPort) {
        SimulacaoEntity simulacaoEntity = SimulacaoEntityMapper.toEntity(simulacaoPersistenceDtoPort);
        simulacaoRepository.save(simulacaoEntity);
    }

    @Override
    public Optional<SimulacaoPersistenceDtoPort> buscarPorId(String id) {
        SimulacaoEntity simulacaoEntity = simulacaoRepository.getById(id);

        if (simulacaoEntity == null) {
            return Optional.empty();
        }

        return Optional.of(SimulacaoEntityMapper.toOutput(simulacaoEntity));
    }
}
