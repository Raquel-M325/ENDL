package Grafos;
import java.util.ArrayList;

public class Grafo implements InterfaceGrafo{
    private Vertice[] vertices;
    private ArrayList<Aresta>[][] arestas;
    private int capacidade, size;

    public Grafo(int capacidade) {
        this.capacidade = capacidade;
        this.size = 0;
        this.vertices = new Vertice[capacidade];
        this.arestas = new ArrayList[capacidade][capacidade];

        //Por ser uma matriz de arestas, então precisa definir o limite pela capacidade
        for (int i = 0; i < capacidade; i++){
            for (int j = 0; j < capacidade; j++){
                this.arestas[i][j] = new ArrayList<Aresta>();
            }
        }
        
    }

    public void removeVertice(Vertice v) {
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (v == null){
            throw new Correcao("Vertice não encontrado");
        }


        for (int i = 0; i < arestas.length; i++){
            for (int j = 0; j < arestas[i].length; j++){
                
            }
        }


        for (int i = 0; i < vertices.length; i++){
            if (vertices[i] == v){
                vertices[i] = null;
                size--;
            }
        }
        

    }

    public void removeAresta(Aresta a) {
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        boolean encontrouAresta = false;

        //verifica a existencia da aresta no grafo, caso exista, remove a aresta
        for (int i = 0; i < arestas.length; i++){
            for (int j = 0; j < arestas[i].length; j++){
                if (arestas[i][j].remove(a)){
                    encontrouAresta = true;
                }
            }
        }

        if (!encontrouAresta){
            throw new Correcao("Aresta não encontrada");
        }
    }

    public void insertVertice(Vertice v) {
        if (v == null){
            throw new Correcao("Vertice não encontrado");
        }

        //vertice entra no grafo e vê um vazio para ser colocado na lista
        for (int i = 0; i < vertices.length; i++){
            if (vertices[i] == null){
                vertices[i] = v;
                size++;
                return;
            }
        }

        throw new Correcao("Grafo cheio");

    }

    public void insertAresta(Aresta a, Vertice v1, Vertice v2) {
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (v1 == null || v2 == null){
            throw new Correcao("Vertice não encontrado");
        }

        

    }

    public boolean isAdjacent(Vertice v1, Vertice v2, Aresta a) {
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (v1 == null || v2 == null){
            throw new Correcao("Vertice não encontrado");
        }

        if (a == null){
            throw new Correcao("Aresta não encontrada");
        }

        return a.getOrigem() == v1 && a.getFim() == v2;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Vertice oposto(Vertice v, Aresta a) {
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (v == null){
            throw new Correcao("Vertice não encontrado");
        }

        if (a == null){
            throw new Correcao("Aresta não encontrada");
        }

        if (a.getOrigem() == v){
            return a.getFim();

        } 
        
        else if (a.getFim() == v){
            return a.getOrigem();
        } 
        
        else {
            throw new Correcao("Vertice não é oposto da aresta");
        } 

    }

    public Vertice[] finalVertices(Aresta a) {

    }

    public void substituirVertice(Vertice v, Object elemento) {
        
    }

    public void substituirAresta(Aresta a, Object elemento) {
        
    }
}
