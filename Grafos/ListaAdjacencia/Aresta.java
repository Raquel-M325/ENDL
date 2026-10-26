package Grafos;


public class Aresta{
    private Object elemento;
    private Vertice origem;
    private Vertice fim;
    private boolean direcionada;

    public Aresta() {
        this.elemento = null;
        this.origem = null;
        this.fim = null;
        this.direcionada = false;
    }

    public void setElement(Object elemento) {
        this.elemento = elemento;
    }

    public Object getElement() {
        return this.elemento;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    public Vertice getOrigem() {
        return this.origem;
    }   

    public void setFim(Vertice fim) {
        this.fim = fim;
    }

    public Vertice getFim() {
        return this.fim;
    }

    public void setDirecionada(boolean direcionada) {
        this.direcionada = direcionada;
    }

    public boolean getDirecionada() {
        return this.direcionada;
    }


}