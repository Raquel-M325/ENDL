package Algoritmo.A.Dijkstra;


public class No implements Comparable<No> {
    private int linha, coluna;
    private int custo;
    private No passoAnterior;

    public No(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.custo = 0;
        this.passoAnterior = null;
    }
    
    @Override
    public int compareTo(No outroNo) {
        return Integer.compare(this.custo, outroNo.custo);
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public No getPassoAnterior() {
        return passoAnterior;
    }

    public void setPassoAnterior(No passoAnterior) {
        this.passoAnterior = passoAnterior;
    }
}
