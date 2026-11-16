package Algoritmo.A;

public class No {
    private int linha, coluna;
    private int g, h, f;
    private No passoAnterior;

    public No(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.g = 0;
        this.h = 0;
        this.f = g + h;
        this.passoAnterior = null;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public No getPassoAnterior() {
        return passoAnterior;
    }

    public void setPassoAnterior(No passoAnterior) {
        this.passoAnterior = passoAnterior;
    }
}
