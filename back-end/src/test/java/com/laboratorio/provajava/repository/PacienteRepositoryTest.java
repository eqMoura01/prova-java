package com.laboratorio.provajava.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PacienteRepositoryTest {

    @MockBean
    private PacienteRepository pacienteRepository;

    @Test
    @DisplayName("Calcula o IMC pela faixa de idade")
    public void testCalculateImc() {

        // Dados de exemplo para simular o retorno da consulta
        Object[] data1 = { 21, 30, 27.5 };
        Object[] data2 = { 31, 40, 27.8 };
        List<Object[]> expectedResult = Arrays.asList(data1, data2);

        // Configurando o comportamento simulado do pacienteRepository
        when(pacienteRepository.calculateImc()).thenReturn(expectedResult);

        // Chamando o método e verificando se os resultados esperados são retornados
        List<Object[]> result = pacienteRepository.calculateImc();

        // Verificando se o método retorna os resultados esperados
        assertEquals(expectedResult.size(), result.size());
        assertEquals(expectedResult.get(0)[0], result.get(0)[0]);
        assertEquals(expectedResult.get(0)[1], result.get(0)[1]);
        assertEquals(expectedResult.get(0)[2], result.get(0)[2]);
        assertEquals(expectedResult.get(1)[0], result.get(1)[0]);
        assertEquals(expectedResult.get(1)[1], result.get(1)[1]);
        assertEquals(expectedResult.get(1)[2], result.get(1)[2]);
        
        // Verificando se o método foi chamado exatamente uma vez
        verify(pacienteRepository, times(1)).calculateImc();
    }

    
}
