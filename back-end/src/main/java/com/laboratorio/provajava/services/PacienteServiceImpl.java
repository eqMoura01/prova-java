package com.laboratorio.provajava.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratorio.provajava.model.Paciente;
import com.laboratorio.provajava.model.DTO.ImcResultDTO;
import com.laboratorio.provajava.model.DTO.PacienteByEstadoDTO;
import com.laboratorio.provajava.model.DTO.PercentualObesidadeBySexoDTO;
import com.laboratorio.provajava.model.DTO.TipoSanguineoIdadeDTO;
import com.laboratorio.provajava.model.DTO.TipoSanguineoQuantidadeDTO;
import com.laboratorio.provajava.repository.PacienteRepository;
import com.laboratorio.provajava.services.interfaces.PacienteService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Paciente save(Paciente object) {
        return this.pacienteRepository.save(object);
    }

    @Override
    public List<Paciente> saveAll(List<Paciente> list) {
        return this.pacienteRepository.saveAll(list);
    }

    @Override
    public List<Paciente> list() {
        return this.pacienteRepository.findAll();
    }

    @Override
    public Paciente searchById(Long id) {
        Optional<Paciente> searchedPaciente = this.pacienteRepository.findById(id);

        if (searchedPaciente.isEmpty()) {
            throw new EntityNotFoundException("Paciente não encontrado");
        }

        return searchedPaciente.get();
    }

    @Override
    public Paciente update(Paciente object) {
        Paciente paciente = searchById(object.getId());

        if (Objects.nonNull(object)) {
            BeanUtils.copyProperties(object, paciente, "id");
            save(object);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        searchById(id);
        this.pacienteRepository.deleteById(id);
    }

    public List<PacienteByEstadoDTO> countPacientesByEstado() {

        List<Object[]> resultList = this.pacienteRepository.countPacientesByEstado();

        List<PacienteByEstadoDTO> result = resultList.stream()
                .map(array -> {
                    PacienteByEstadoDTO pacienteByEstadoDTO = new PacienteByEstadoDTO();
                    pacienteByEstadoDTO.setEstado((String) array[0]);
                    pacienteByEstadoDTO.setQtdPacientes((Long) array[1]);

                    return pacienteByEstadoDTO;
                }).collect(Collectors.toList());

        return result;
    }

    @Override
    public List<ImcResultDTO> calculateImc() {

        List<Object[]> result = this.pacienteRepository.calculateImc();

        List<ImcResultDTO> imcResults = result.stream()
                .map(array -> {
                    ImcResultDTO imcResultDTO = new ImcResultDTO();
                    imcResultDTO.setFaixaIdadeInicio((int) array[0]);
                    imcResultDTO.setFaixaIdadeFim((int) array[1]);
                    imcResultDTO.setImcMedio((double) array[2]);
                    return imcResultDTO;
                })
                .collect(Collectors.toList());

        return imcResults;
    }

    @Override
    public List<TipoSanguineoIdadeDTO> calcularMediaIdadePorTipoSanguineo() {

        List<Object[]> result = this.pacienteRepository.calcularMediaIdadePorTipoSanguineo();

        List<TipoSanguineoIdadeDTO> tsiResult = result.stream()
                .map(array -> {
                    TipoSanguineoIdadeDTO tipoSanguineoIdadeDTO = new TipoSanguineoIdadeDTO();
                    tipoSanguineoIdadeDTO.setTipoSanguineo((String) array[0]);

                    // Convertendo BigDecimal para Integer de maneira segura
                    BigDecimal mediaIdadeDecimal = (BigDecimal) array[1];
                    tipoSanguineoIdadeDTO.setMediaIdade(mediaIdadeDecimal.intValue());

                    return tipoSanguineoIdadeDTO;
                })
                .collect(Collectors.toList());

        return tsiResult;
    }

    @Override
    public List<TipoSanguineoQuantidadeDTO> contarDoadoresPorTipoSanguineo() {
        List<Object[]> result = this.pacienteRepository.contarDoadoresPorTipoSanguineo();

        List<TipoSanguineoQuantidadeDTO> tsqResult = result.stream()
                .map(array -> {
                    TipoSanguineoQuantidadeDTO tipoSanguineoQuantidadeDTO = new TipoSanguineoQuantidadeDTO();
                    tipoSanguineoQuantidadeDTO.setTipoReceptor((String) array[0]);
                    tipoSanguineoQuantidadeDTO.setQuantidadeDoadores((Long) array[1]);

                    return tipoSanguineoQuantidadeDTO;
                })
                .collect(Collectors.toList());

        return tsqResult;
    }

    public Boolean verificaSePodeDoar(Long id) {
        Paciente paciente = this.searchById(id);

        int idade = calculaIdade(paciente);

        if (idade >= 16 && idade <= 69) {
            return true;
        } else {
            return false;
        }
    }

    private int calculaIdade(Paciente paciente) {
        LocalDate currentDate = LocalDate.now();
        int anoNasc = paciente.getData_nasc().getYear();
        int mesNasc = paciente.getData_nasc().getMonthValue();
        int diaNasc = paciente.getData_nasc().getDayOfMonth();

        int idade = currentDate.getYear() - anoNasc;

        if (mesNasc > currentDate.getMonthValue()) {
            idade--;
        } else if (mesNasc == currentDate.getMonthValue() && diaNasc > currentDate.getDayOfMonth()) {
            idade--;
        }

        return idade;
    }

    // @Override
    // public PercentualObesidadeBySexoDTO calcularPercentualObesidade() {
    // List<Object[]> resultList =
    // this.pacienteRepository.calcularPercentualObesidade();

    // PercentualObesidadeBySexoDTO percentualObesidadeDTO = new
    // PercentualObesidadeBySexoDTO();

    // if (!resultList.isEmpty()) {
    // Object[] result = resultList.get(0);

    // if (result.length == 2) {
    // percentualObesidadeDTO.setPercentualObesosMasc(((Number)
    // result[0]).doubleValue());
    // percentualObesidadeDTO.setPercentualObesosFem(((Number)
    // result[1]).doubleValue());
    // }
    // }

    // return percentualObesidadeDTO;
    // }

    @Override
    public PercentualObesidadeBySexoDTO calcularPercentualObesidade() {
        List<Object[]> resultList = this.pacienteRepository.calcularPercentualObesidade();

        PercentualObesidadeBySexoDTO percentualObesidadeDTO = new PercentualObesidadeBySexoDTO();

        for (Object[] result : resultList) {
            String sexo = (String) result[0];
            BigDecimal percentual = (BigDecimal) result[3];
            percentualObesidadeDTO.setPercentual(sexo, percentual);
        }

        return percentualObesidadeDTO;
    }

}
