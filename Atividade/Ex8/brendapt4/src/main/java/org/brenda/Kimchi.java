package org.brenda;

public class Kimchi {
    private String tipo;
    private int picancia;
    private int fermentacao;

    public Kimchi(String tipo, int picancia, int fermentacao) {
        this.tipo = tipo;
        this.picancia = picancia;
        this.fermentacao = fermentacao;
    }

    public void fermentar() {
        System.out.println("Kimchi fermentado por " + fermentacao + " dias!");
    }

    public void temperar() {
        System.out.println("Kimchi temperado!");
    }

    public void provar() {
        System.out.println("Kimchi provado, picância nível " + picancia + "!");
    }
}
