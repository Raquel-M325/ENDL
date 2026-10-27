package Grafos.ListaAdjacencia;
import java.util.ArrayList;

public class Vertice{
    private Object elemento;
    private ArrayList<Aresta> adjacencias;

    public Vertice() {
        this.elemento = null;
        this.adjacencias = new ArrayList<>(); 
    }

    public void setElement(Object elemento) {
        this.elemento = elemento;
    }

    public Object getElement() {
        return this.elemento;
    }

    public void setAdjacencias(ArrayList<Aresta> adjacencias) {
        this.adjacencias = adjacencias;
    }

    public ArrayList<Aresta> getAdjacencias() {
        return this.adjacencias;
    }


}
