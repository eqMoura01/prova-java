package com.laboratorio.provajava.services.interfaces;

import java.util.List;

import com.laboratorio.provajava.model.Paciente;
import com.laboratorio.provajava.model.DTO.ImcResultDTO;
import com.laboratorio.provajava.model.DTO.PacienteByEstadoDTO;
import com.laboratorio.provajava.model.DTO.PercentualObesidadeBySexoDTO;
import com.laboratorio.provajava.model.DTO.TipoSanguineoIdadeDTO;
import com.laboratorio.provajava.model.DTO.TipoSanguineoQuantidadeDTO;

public interface PacienteService extends DefaultCrud<Paciente> {

    List<PacienteByEstadoDTO> countPacientesByEstado();

    List<ImcResultDTO> calculateImc();

    List<TipoSanguineoIdadeDTO> calcularMediaIdadePorTipoSanguineo();

    List<TipoSanguineoQuantidadeDTO> contarDoadoresPorTipoSanguineo();

    PercentualObesidadeBySexoDTO calcularPercentualObesidade();
}
