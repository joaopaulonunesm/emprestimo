package com.sistema.bancario.emprestimo.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AreaUseCase {

    public List<String> getResponsaveisPelasAnalises() {
        // TODO parametros que viriam de um outro serviço ou algum portal admin
        return List.of("CREDITO", "GARANTIA", "RISCO");
    }
}
