package com.laboratorio.provajava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.laboratorio.provajava.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

        @Query("SELECT p.estado, COUNT(p) FROM Paciente p GROUP BY p.estado")
        List<Object[]> countPacientesByEstado();

        @Query("SELECT " +
                        "FLOOR((DATEDIFF(CURRENT_DATE, p.data_nasc) - 1) / 365 / 10) * 10 + 11 AS faixaIdadeInicio, " +
                        "FLOOR((DATEDIFF(CURRENT_DATE, p.data_nasc)) / 365 / 10) * 10 + 20 AS faixaIdadeFim, " +
                        "AVG(p.peso / (p.altura * p.altura)) AS imcMedio " +
                        "FROM Paciente p " +
                        "WHERE DATEDIFF(CURRENT_DATE, p.data_nasc) >= 365 * 11 " +
                        "GROUP BY faixaIdadeInicio, faixaIdadeFim " +
                        "ORDER BY faixaIdadeInicio")
        List<Object[]> calculateImc();

        @Query(value = "SELECT tipo_sanguineo, " +
                        "ROUND(AVG(DATEDIFF(CURDATE(), data_nasc) / 365)) AS media_idade " +
                        "FROM paciente " +
                        "GROUP BY tipo_sanguineo", nativeQuery = true)
        List<Object[]> calcularMediaIdadePorTipoSanguineo();

        @Query(nativeQuery = true, value = "SELECT receptor.tipo_sanguineo AS tipo_receptor, COUNT(DISTINCT doador.id) AS quantidade_doadores "
                        +
                        "FROM paciente receptor " +
                        "JOIN paciente doador ON ( " +
                        "(receptor.tipo_sanguineo = 'A+' AND doador.tipo_sanguineo IN ('A+', 'A-', 'O+', 'O-')) OR " +
                        "(receptor.tipo_sanguineo = 'A-' AND doador.tipo_sanguineo IN ('A-', 'O-')) OR " +
                        "(receptor.tipo_sanguineo = 'B+' AND doador.tipo_sanguineo IN ('B+', 'B-', 'O+', 'O-')) OR " +
                        "(receptor.tipo_sanguineo = 'B-' AND doador.tipo_sanguineo IN ('B-', 'O-')) OR " +
                        "(receptor.tipo_sanguineo = 'AB+' AND doador.tipo_sanguineo IN ('A+', 'B+', 'O+', 'AB+', 'A-', 'B-', 'O-', 'AB-')) OR "
                        +
                        "(receptor.tipo_sanguineo = 'AB-' AND doador.tipo_sanguineo IN ('A-', 'B-', 'O-', 'AB-')) OR " +
                        "(receptor.tipo_sanguineo = 'O+' AND doador.tipo_sanguineo IN ('O+', 'O-')) OR " +
                        "(receptor.tipo_sanguineo = 'O-' AND doador.tipo_sanguineo IN ('O-')) " +
                        ") " +
                        "GROUP BY receptor.tipo_sanguineo " +
                        "ORDER BY receptor.tipo_sanguineo;")
        List<Object[]> contarDoadoresPorTipoSanguineo();

        @Query(value = "SELECT sexo, COUNT(*) as total_pacientes, " +
                        "SUM(CASE WHEN imc > 30 THEN 1 ELSE 0 END) as total_obesos, " +
                        "(SUM(CASE WHEN imc > 30 THEN 1 ELSE 0 END) / COUNT(*)) * 100 as percentual_obesos " +
                        "FROM (SELECT sexo, peso / (altura * altura) as imc FROM paciente) as subquery " +
                        "GROUP BY sexo", nativeQuery = true)
        List<Object[]> calcularPercentualObesidade();

}