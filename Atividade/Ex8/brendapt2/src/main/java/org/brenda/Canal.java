package org.brenda;

public class Canal {
    private String localizacao;
    private double profundidade;
    private double largura;

    public Canal(String localizacao, double profundidade, double largura) {
        this.localizacao = localizacao;
        this.profundidade = profundidade;
        this.largura = largura;
    }

    public void receberObra() {
        System.out.println("O canal está recebendo obra.");
    }
}