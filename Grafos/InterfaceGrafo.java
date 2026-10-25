package Grafos;

import java.util.ArrayList;

public interface InterfaceGrafo{
    
    void removeVertice(Vertice v);
    void removeAresta(Aresta a);
    void insertVertice(Vertice v);
    void insertAresta(Aresta a, Vertice v1, Vertice v2);
    boolean isAdjacent(Vertice v1, Vertice v2, Aresta a);
    boolean isEmpty();
    Vertice oposto(Vertice v, Aresta a);
    Vertice[] finalVertices(Aresta a);
    void substituirVertice(Vertice v, Object elemento);
    void substituirAresta(Aresta a, Object elemento);
    boolean isDirected(Aresta a);
    void insertArestaDirecionada(Aresta a, Vertice v1, Vertice v2);
    Vertice[] vertices();
    ArrayList<Aresta>[][] arestas();
    int grau(Vertice v);
    int grauEntrada(Vertice v);
    int grauSaida(Vertice v);
    ArrayList<Aresta>[][] arestasIncidentes(Vertice v);
}