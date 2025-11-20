package org.LP2;

public class Olhos {
    private Integer id;
    private String cor;
    private boolean aberto;
    private int nitidez;

    public Olhos() { }

    public Olhos(Integer id, String cor, boolean aberto, int nitidez) {
        this.id = id;
        this.cor = cor;
        this.aberto = aberto;
        this.nitidez = nitidez;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public boolean isAberto() { return aberto; }
    public void setAberto(boolean aberto) { this.aberto = aberto; }

    public int getNitidez() { return nitidez; }
    public void setNitidez(int nitidez) { this.nitidez = nitidez; }

    public void ver() {
        if (!aberto) {
            this.aberto = true;
        }
    }
}
