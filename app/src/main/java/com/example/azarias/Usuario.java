package com.example.azarias;

public class Usuario {
    private int idUser;
    private String senha;
    private int numEmerg;

    public Usuario(int idUser, String senha, int numEmerg) {
        this.idUser = idUser;
        this.senha = senha;
        this.numEmerg = numEmerg;
    }

    private void cadastrarCentral(){

    }

    private void consultarPontos(){

    }

    private void ligar(){

    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNumEmerg() {
        return numEmerg;
    }

    public void setNumEmerg(int numEmerg) {
        this.numEmerg = numEmerg;
    }
}
