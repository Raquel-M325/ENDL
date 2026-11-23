package Algoritmo.Dijkstra;


public class No implements Comparable<No> {
    private int linha, coluna;
    private int G;
    private No passoAnterior;

    public No(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.G = 0;
        this.passoAnterior = null;
    }
    
    @Override
    public int compareTo(No outroNo) {
        return Integer.compare(this.G, outroNo.G);
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public int getG() {
        return G;
    }

    public void setG(int G) {
        this.G = G;
    }

    public No getPassoAnterior() {
        return passoAnterior;
    }

    public void setPassoAnterior(No passoAnterior) {
        this.passoAnterior = passoAnterior;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        No outro = (No) obj;

        return linha == outro.linha && coluna == outro.coluna;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(linha, coluna);
    }
}
