package com.sistema.bancario.emprestimo.infrastructure.repository;

import com.sistema.bancario.emprestimo.infrastructure.repository.entity.SimulacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulacaoRepository extends JpaRepository<SimulacaoEntity, String> {
}
