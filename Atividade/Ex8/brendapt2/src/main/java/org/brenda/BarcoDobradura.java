package org.brenda;

public class BarcoDobradura {
    private String cor;
    private String material;
    private boolean dobrado;

    public BarcoDobradura(String cor, String material, boolean dobrado) {
        this.cor = cor;
        this.material = material;
        this.dobrado = dobrado;
    }

    public void exibirFlutuando() {
        if (dobrado) System.out.println("O barco está flutuando!");
        else System.out.println("O barco não está dobrado.");
    }
}