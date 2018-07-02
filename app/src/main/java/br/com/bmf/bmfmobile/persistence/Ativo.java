package br.com.bmf.bmfmobile.persistence;

public class Ativo {
    private String codigo;
    private Float valor;

    public Ativo(String codigo, Float valor) {
        this.codigo = codigo;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
