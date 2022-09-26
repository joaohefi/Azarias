package com.example.azarias;

public class Central {
    protected int idCentral;
    private String tagCentral;
    private int estadoCentral;
    private boolean conexao;

    public Central(int idCentral, String tagCentral, int estadoCentral, boolean conexao) {
        this.idCentral = idCentral;
        this.tagCentral = tagCentral;
        this.estadoCentral = estadoCentral;
        this.conexao = conexao;
    }

    private void atualizarPonto(){

    }

    private void dispararFogo(){

    }

    public int getIdCentral() {
        return idCentral;
    }

    public void setIdCentral(int idCentral) {
        this.idCentral = idCentral;
    }

    public String getTagCentral() {
        return tagCentral;
    }

    public void setTagCentral(String tagCentral) {
        this.tagCentral = tagCentral;
    }

    public int getEstadoCentral() {
        return estadoCentral;
    }

    public void setEstadoCentral(int estadoCentral) {
        this.estadoCentral = estadoCentral;
    }

    public boolean isConexao() {
        return conexao;
    }

    public void setConexao(boolean conexao) {
        this.conexao = conexao;
    }
}
