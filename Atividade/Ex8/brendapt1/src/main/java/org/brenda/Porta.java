package org.brenda;

public class Porta {
    private String cor;
    private String estado;
    private String destino;

    public Porta(String cor, String estado, String destino) {
        this.cor = cor;
        this.estado = estado;
        this.destino = destino;
    }

    public void abrir() {
        this.estado = "Aberta";
    }
}
