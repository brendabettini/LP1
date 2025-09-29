package org.brenda;

import java.util.Date;

public class Jornal {
    private String veiculo;
    private Date data;
    private int paginas;

    public Jornal(String veiculo, Date data, int paginas) {
        this.veiculo = veiculo;
        this.data = data;
        this.paginas = paginas;
    }

    public void amassar() {
        System.out.println("O jornal foi amassado.");
    }
}