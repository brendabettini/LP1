package org.brenda;

public class Japchae {
    private String tipoMacarrao;
    private String vegetais;
    private String molho;

    public Japchae(String tipoMacarrao, String vegetais, String molho) {
        this.tipoMacarrao = tipoMacarrao;
        this.vegetais = vegetais;
        this.molho = molho;
    }

    public void fritar() {
        System.out.println("Macarrão frito com vegetais!");
    }

    public void salgar() {
        System.out.println("Japchae salgado!");
    }

    public void servir() {
        System.out.println("Japchae servido!");
    }
}