package org.LP2;

public class Porta {

    private Integer id;    
    private String cor;
    private String estado;
    private String destino;

    public Porta(String cor, String estado, String destino) {
        this.cor = cor;
        this.estado = estado;
        this.destino = destino;
    }

    public Porta(Integer id, String cor, String estado, String destino) {
        this.id = id;
        this.cor = cor;
        this.estado = estado;
        this.destino = destino;
    }

    public void abrir() {
        this.estado = "aberta";
        System.out.println("A porta foi aberta e leva para " + destino + ".");
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Porta{" +
                "id=" + id +
                ", cor='" + cor + '\'' +
                ", estado='" + estado + '\'' +
                ", destino='" + destino + '\'' +
                '}';
    }
}
