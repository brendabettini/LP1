package org.brenda;

public class XicaraCafe {
    private String cor;
    private double capacidade;
    private String conteudo;

    public XicaraCafe(String cor, double capacidade, String conteudo) {
        this.cor = cor;
        this.capacidade = capacidade;
        this.conteudo = conteudo;
    }

    public void colocarOlho() {
        System.out.println("Olho colocado na xícara.");
    }
}
