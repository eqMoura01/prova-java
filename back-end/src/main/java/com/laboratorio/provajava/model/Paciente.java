package com.laboratorio.provajava.model;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.laboratorio.provajava.utils.LocalDateDeserializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String nome;
    private String cpf;
    private String rg;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate data_nasc;
    private String sexo;
    private String mae;
    private String pai;
    private String cep;
    private String endereco;
    private int numero;
    private String cidade;
    private String estado;
    private String telefone_fixo;
    private String celular;
    private Double altura;
    private Double peso;
    private String tipo_sanguineo;
}
