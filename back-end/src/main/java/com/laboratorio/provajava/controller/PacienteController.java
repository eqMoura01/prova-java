package com.laboratorio.provajava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laboratorio.provajava.model.Paciente;
import com.laboratorio.provajava.model.DTO.ImcResultDTO;
import com.laboratorio.provajava.model.DTO.PacienteByEstadoDTO;
import com.laboratorio.provajava.model.DTO.PercentualObesidadeBySexoDTO;
import com.laboratorio.provajava.model.DTO.TipoSanguineoIdadeDTO;
import com.laboratorio.provajava.model.DTO.TipoSanguineoQuantidadeDTO;
import com.laboratorio.provajava.services.interfaces.PacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> save(@RequestBody @Valid Paciente paciente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.pacienteService.save(paciente));
    }

    // Salva todos os registros de uma lista (JSON)
    @PostMapping("/list")
    public void saveAll(@RequestBody List<Paciente> listaPacientes) {
        for (Paciente paciente : listaPacientes) {
            this.pacienteService.save(paciente);
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listAll() {
        return ResponseEntity.ok(this.pacienteService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> searchById(@PathVariable Long id) {
        return ResponseEntity.ok(this.pacienteService.searchById(id));
    }

    // Conta quantos registros tem pra cada estado.
    @GetMapping("/estados")
    public ResponseEntity<List<PacienteByEstadoDTO>> countPacientesByEstado() {
        return ResponseEntity.ok(this.pacienteService.countPacientesByEstado());
    }

    @GetMapping("/imc")
    public ResponseEntity<List<ImcResultDTO>> calculateImc() {
        return ResponseEntity.ok(this.pacienteService.calculateImc());
    }

    @GetMapping("/mediaIdadeSanguinea")
    public ResponseEntity<List<TipoSanguineoIdadeDTO>> calcularMediaIdadePorTipoSanguineo() {
        return ResponseEntity.ok(this.pacienteService.calcularMediaIdadePorTipoSanguineo());
    }

    @GetMapping("/qtdDoadorTipoSangue")
    public ResponseEntity<List<TipoSanguineoQuantidadeDTO>> contarDoadoresPorTipoSanguineo() {
        return ResponseEntity.ok(this.pacienteService.contarDoadoresPorTipoSanguineo());
    }

    @GetMapping("/percentualObesoSexo")
    public ResponseEntity<PercentualObesidadeBySexoDTO> calcularPercentualObesidade() {
        return ResponseEntity.ok(this.pacienteService.calcularPercentualObesidade());
    }

    @PutMapping
    ResponseEntity<Paciente> update(@RequestBody @Valid Paciente paciente) {
        return ResponseEntity.ok(this.pacienteService.update(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.pacienteService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
