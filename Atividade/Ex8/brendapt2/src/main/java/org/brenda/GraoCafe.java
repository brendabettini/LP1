package org.brenda;

public class GraoCafe {
    private String origem;
    private String torra;
    private int quantidade;

    public GraoCafe(String origem, String torra, int quantidade) {
        this.origem = origem;
        this.torra = torra;
        this.quantidade = quantidade;
    }

    public void posicionar() {
        System.out.println("O grão foi posicionado.");
    }
}
