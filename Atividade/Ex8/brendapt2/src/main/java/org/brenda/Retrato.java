package org.brenda;

public class Retrato {
    private String tema;
    private String material;
    private String dimensoes;

    public Retrato(String tema, String material, String dimensoes) {
        this.tema = tema;
        this.material = material;
        this.dimensoes = dimensoes;
    }

    public void fotografar() {
        System.out.println("O retrato foi fotografado.");
    }
}
