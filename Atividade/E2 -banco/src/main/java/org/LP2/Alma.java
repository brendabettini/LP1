package org.LP2;

public class Alma {
    private Integer id;
    private String estado;
    private int temperatura;
    private int conexao;

    public Alma() { }

    public Alma(Integer id, String estado, int temperatura, int conexao) {
        this.id = id;
        this.estado = estado;
        this.temperatura = temperatura;
        this.conexao = conexao;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getTemperatura() { return temperatura; }
    public void setTemperatura(int temperatura) { this.temperatura = temperatura; }

    public int getConexao() { return conexao; }
    public void setConexao(int conexao) { this.conexao = conexao; }

    public void despertar() {
        this.estado = "Desperta";
    }
}
