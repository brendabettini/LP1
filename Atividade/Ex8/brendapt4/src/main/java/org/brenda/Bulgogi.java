package org.brenda;

public class Bulgogi {
    private String corte;
    private int tempoDeMarinada;
    private double temperatura;

    public Bulgogi(String corte, int tempoDeMarinada, double temperatura) {
        this.corte = corte;
        this.tempoDeMarinada = tempoDeMarinada;
        this.temperatura = temperatura;
    }

    public void marinar() {
        System.out.println("Carne marinada por " + tempoDeMarinada + " minutos!");
    }

    public void grelhar() {
        System.out.println("Carne grelhada a " + temperatura + " graus!");
    }

    public void comer() {
        System.out.println("Bulgogi comido, delicioso!");
    }
}
