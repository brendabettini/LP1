package org.brenda;

public class BatataFrita {
    private int quantidade;
    private double temperatura;
    private boolean crocancia;

    public BatataFrita(int quantidade, double temperatura, boolean crocancia) {
        this.quantidade = quantidade;
        this.temperatura = temperatura;
        this.crocancia = crocancia;
    }

    public void fritar() {
        System.out.println("Batata frita!");
    }

    public void salgar() {
        System.out.println("Batata salgada!");
    }

    public void servir() {
        System.out.println("Batata servida!");
    }
}