package org.brenda;

public class Refrigerante {
    private String sabor;
    private double volume;
    private boolean gelo;

    public Refrigerante(String sabor, double volume, boolean gelo) {
        this.sabor = sabor;
        this.volume = volume;
        this.gelo = gelo;
    }

    public void gelar() {
        System.out.println("Refrigerante gelado!");
    }

    public void abrir() {
        System.out.println("Refrigerante aberto!");
    }

    public void beber() {
        System.out.println("Refrigerante bebido!");
    }
}