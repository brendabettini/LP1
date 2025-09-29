package org.brenda;

public class Olhos {
    private String cor;
    private boolean aberto;
    private int nitidez;

    public Olhos(String cor, boolean aberto, int nitidez) {
        this.cor = cor;
        this.aberto = aberto;
        this.nitidez = nitidez;
    }

    public void ver() {
        if (aberto) {
            System.out.println("Vendo com nitidez " + nitidez);
        } else {
            System.out.println("Olhos fechados.");
        }
    }
}