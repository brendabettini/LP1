package org.brenda;

public class Hamburguer {
    private String tamanho;
    private String ingredientes;
    private int calorias;

    public Hamburguer(String tamanho, String ingredientes, int calorias) {
        this.tamanho = tamanho;
        this.ingredientes = ingredientes;
        this.calorias = calorias;
    }

    public void montar() {
        System.out.println("Hamburguer montado!");
    }

    public void aquecer() {
        System.out.println("Hamburguer aquecido!");
    }

    public void comer() {
        System.out.println("Delicioso hamburguer comido!");
    }
}
