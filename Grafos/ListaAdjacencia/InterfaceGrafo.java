package Grafos.ListaAdjacencia;

import java.util.ArrayList;

public interface InterfaceGrafo{
    
    void removeVertice(Vertice v) throws Correcao;
    void removeAresta(Aresta a) throws Correcao;
    void insertVertice(Vertice v) throws Correcao;
    void insertAresta(Aresta a, Vertice v1, Vertice v2) throws Correcao;
    boolean isAdjacent(Vertice v1, Vertice v2, Aresta a) throws Correcao;
    boolean isEmpty();
    Vertice oposto(Vertice v, Aresta a) throws Correcao;
    Vertice[] finalVertices(Aresta a) throws Correcao;
    void substituirVertice(Vertice v, Object elemento) throws Correcao;
    void substituirAresta(Aresta a, Object elemento) throws Correcao;
    boolean isDirected(Aresta a) throws Correcao;
    void insertArestaDirecionada(Aresta a, Vertice v1, Vertice v2) throws Correcao;
    Vertice[] vertices() throws Correcao;
    ArrayList<Aresta>[][] arestas() throws Correcao;
    int grau(Vertice v) throws Correcao;
    int grauEntrada(Vertice v) throws Correcao;
    int grauSaida(Vertice v) throws Correcao;
    ArrayList<Aresta> arestasIncidentes(Vertice v) throws Correcao;
}