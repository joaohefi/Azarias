package com.example.azarias;

public class Ponto {
    protected int idPonto;
    private String tagPonto;
    private int estadoPonto;

    public Ponto(int idPonto, String tagPonto, int estadoPonto) {
        this.idPonto = idPonto;
        this.tagPonto = tagPonto;
        this.estadoPonto = estadoPonto;
    }

    public int getIdPonto() {
        return idPonto;
    }

    public void setIdPonto(int idPonto) {
        this.idPonto = idPonto;
    }

    public String getTagPonto() {
        return tagPonto;
    }

    public void setTagPonto(String tagPonto) {
        this.tagPonto = tagPonto;
    }

    public int getEstadoPonto() {
        return estadoPonto;
    }

    public void setEstadoPonto(int estadoPonto) {
        this.estadoPonto = estadoPonto;
    }
}
