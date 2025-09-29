package org.brenda;

public class Alma {
    private String estado;
    private int temperatura;
    private int conexao;

    public Alma(String estado, int temperatura, int conexao) {
        this.estado = estado;
        this.temperatura = temperatura;
        this.conexao = conexao;
    }

    public void despertar() {
        this.estado = "Desperta";
    }
}