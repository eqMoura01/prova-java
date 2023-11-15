package com.laboratorio.provajava.model;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.laboratorio.provajava.utils.LocalDateDeserializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String cpf;
    @NotNull
    private String rg;
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate data_nasc;
    @NotNull
    private String sexo;
    @NotNull
    private String mae;
    private String pai;
    @NotNull
    private String cep;
    @NotNull
    private String endereco;
    @NotNull
    private int numero;
    @NotNull
    private String cidade;
    @NotNull
    private String estado;
    private String telefone_fixo;
    @NotNull
    private String celular;
    @NotNull
    private Double altura;
    @NotNull
    private Double peso;
    @NotNull
    private String tipo_sanguineo;
}
