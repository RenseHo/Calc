package net.houwing.model;

import org.springframework.stereotype.Component;


public enum Actie {
    SOM("+"),
    VERSCHIL("-"),
    VERMENIGVULDIGING("*");

    public String operator;

    Actie(String actieOperator) {
        this.operator = actieOperator;
    }

    public String getOperator() {
        return operator;
    }
}
