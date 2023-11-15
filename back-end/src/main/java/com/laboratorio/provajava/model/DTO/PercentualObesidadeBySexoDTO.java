package com.laboratorio.provajava.model.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PercentualObesidadeBySexoDTO {
    private BigDecimal percentualObesosMasc;
    private BigDecimal percentualObesosFem;

    public void setPercentual(String sexo, BigDecimal percentual) {
        if ("Masculino".equals(sexo)) {
            this.percentualObesosMasc = percentual;
        } else if ("Feminino".equals(sexo)) {
            this.percentualObesosFem = percentual;
        }
    }
}
