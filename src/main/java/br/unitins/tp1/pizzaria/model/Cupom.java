package br.unitins.tp1.pizzaria.model;

import jakarta.persistence.Entity;

@Entity
public class Cupom extends DefaultEntity {
    private String codigo;
    private Double desconto;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
}
