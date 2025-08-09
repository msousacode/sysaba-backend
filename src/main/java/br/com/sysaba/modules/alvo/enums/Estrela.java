package br.com.sysaba.modules.alvo.enums;

public enum Estrela {

    POSITIVA("positiva"),
    NEGATIVA("negativa");

    public String tipo;

    Estrela(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
