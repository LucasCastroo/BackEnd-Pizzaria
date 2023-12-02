package br.unitins.tp1.pizzaria.model;

import jakarta.persistence.Entity;

@Entity
public class Bebida extends Item {
    private Integer ml;

    public Integer getMl() {
        return ml;
    }

    public void setMl(Integer ml) {
        this.ml = ml;
    }
}
