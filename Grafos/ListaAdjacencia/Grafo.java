package Grafos.ListaAdjacencia;
import java.util.ArrayList;

public class Grafo implements InterfaceGrafo{
    private Vertice[] vertices;
    private int capacidade, size;

    public Grafo(int capacidade) {
        this.capacidade = capacidade;
        this.size = 0;
        this.vertices = new Vertice[capacidade];
        
    }

    public void removeVertice(Vertice v) throws Correcao {
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (v == null){
            throw new Correcao("Vertice não encontrado");
        }

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i] == v){

                //preciso tirar adjacencias que nao vai ter mais ligacao
                //preciso pegar a quantidade, não as posições
                int j = 0;

                while (j < v.getAdjacencias().size()){
                    Aresta a = v.getAdjacencias().get(j);
                    
                    if (a.getOrigem() == v){
                        a.getFim().getAdjacencias().remove(a);
                    } else{
                        a.getOrigem().getAdjacencias().remove(a);
                    }

                    v.getAdjacencias().remove(a);
                }

                vertices[i] = null;
                size--;
                return;
            }

        }
        
        throw new Correcao("Vertice não encontrado");
    }

    public void removeAresta(Aresta a) throws Correcao {
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        boolean encontrouAresta = false;

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i] != null){

                int j = 0;

                while (j < vertices[i].getAdjacencias().size()){
                    Aresta arestaAtual = vertices[i].getAdjacencias().get(j);    

                    if (a == arestaAtual){

                        if(a.getDirecionada() == true){
                            a.getOrigem().getAdjacencias().remove(a);
                        } else {
                            a.getOrigem().getAdjacencias().remove(a);
                            
                            if (a.getOrigem() != a.getFim()){
                                a.getFim().getAdjacencias().remove(a);
                            }
                        }

                        encontrouAresta = true;

                    } else {
                        j++;
                    }
                }
            }
        }

        if (!encontrouAresta){
            throw new Correcao("Aresta não encontrada");
        }
    }

    public void insertVertice(Vertice v) throws Correcao {
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

    public void insertAresta(Aresta a, Vertice v1, Vertice v2) throws Correcao {
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (v1 == null || v2 == null){
            throw new Correcao("Vertice não encontrado");
        }

        if (a == null){
            throw new Correcao("Aresta não encontrada");
        }


        for (int i = 0; i < vertices.length; i++){
            if (vertices[i] != null){

                int j = 0;

                while (j < vertices[i].getAdjacencias().size()){
                    if (a == vertices[i].getAdjacencias().get(j)){
                        throw new Correcao("Aresta já existe");
                    }

                    j++;

                }
            }
        }

        throw new Correcao("Vertices não encontrados");
    }

    public boolean isAdjacent(Vertice v1, Vertice v2, Aresta a) throws Correcao {
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (v1 == null || v2 == null){
            throw new Correcao("Vertice não encontrado");
        }

        if (a == null){
            throw new Correcao("Aresta não encontrada");
        }

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i]){

                int j = 0;

                while (j < vertices[i].getAdjacencias().size()){

                }
            }
        }


        throw new Correcao("Vertices não encontrados");
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Vertice oposto(Vertice v, Aresta a) throws Correcao {
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

    public Vertice[] finalVertices(Aresta a) throws Correcao {
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (a == null){
            throw new Correcao("Aresta não encontrada");
        }

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i]){

                int j = 0;

                while (j < vertices[i].getAdjacencias().size()){
                    
                }
            }
        }

        throw new Correcao("Aresta não encontrada");

    }

    public void substituirVertice(Vertice v, Object elemento) throws Correcao {
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (v == null){
            throw new Correcao("Vertice não encontrado");
        }

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i] == v){
                vertices[i].setElement(elemento);
                return;
            }
        }

        throw new Correcao("Vertice não encontrado");
    }

    public void substituirAresta(Aresta a, Object elemento) throws Correcao {
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (a == null){
            throw new Correcao("Aresta não encontrada");
        }

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i]){

                int j = 0;

                while (j < vertice[i].getAdjacencias().size()){

                }
            }
        }

        throw new Correcao("Aresta não encontrada");
    
    }

    public boolean isDirected(Aresta a) throws Correcao{
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (a == null){
            throw new Correcao("Aresta não encontrada");
        }

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i]){

                int j = 0;

                while (j < vertices[i].getAdjacencias().size()){

                }
            }
        }

        throw new Correcao("Aresta não encontrada");
    }

    public void insertArestaDirecionada(Aresta a, Vertice v1, Vertice v2) throws Correcao{
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (a == null){
            throw new Correcao("Aresta não encontrada");
        }

        if (v1 == null || v2 == null){
            throw new Correcao("Vertice não encontrado");
        }

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i]){

                int j = 0;

                while (j < vertices[i].getAdjacencias().size()){

                }
            }
        }


        throw new Correcao("Vertices não encontrados");
    }

    public Vertice[] vertices() throws Correcao{
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        return vertices;
    }

    public int grau(Vertice v) throws Correcao{
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (v == null){
            throw new Correcao("Vertice não encontrado");
        }

        int grau = 0;
        ArrayList<Aresta> contadas = new ArrayList<Aresta>();

        for (int i = 0; i < arestas.length; i++){
            for (int j = 0; j < arestas[i].length; j++){
                for (Aresta a : arestas[i][j]){
                    if (v == a.getOrigem() || v == a.getFim()){
                        if (!contadas.contains(a)){

                            //laço conta 2 no grau
                            if (a.getOrigem() == a.getFim()){
                                grau += 2;
                            } else {
                                grau++;
                            }
                            contadas.add(a);
                        }
                                  
                    }
                }
            }
        }

        return grau;
    }
    
    public int grauEntrada(Vertice v) throws Correcao{
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (v == null){
            throw new Correcao("Vertice não encontrado");
        }

        int grauEntrada = 0;

        for (int i = 0; i < arestas.length; i++){
            for (int j = 0; j < arestas[i].length; j++){
                for (Aresta a : arestas[i][j]){
                   
                    if (v == a.getFim() && a.getDirecionada() == true){
                        grauEntrada++;

                    }
                }
            }
        }

        return grauEntrada;
    }

    public int grauSaida(Vertice v) throws Correcao{
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (v == null){
            throw new Correcao("Vertice não encontrado");
        }

        int grauSaida = 0;

        for (int i = 0; i < arestas.length; i++){
            for (int j = 0; j < arestas[i].length; j++){
                for (Aresta a : arestas[i][j]){
                    if (a.getDirecionada() == true && v == a.getOrigem()){
                        grauSaida++;
                    }
                }
            }
        }
        
        return grauSaida;
    }

    public ArrayList<Aresta> arestasIncidentes(Vertice v) throws Correcao{
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (v == null){
            throw new Correcao("Vertice não encontrado");
        }

        ArrayList<Aresta> contadas = new ArrayList<Aresta>();

        for (int i = 0; i < arestas.length; i++){
            for (int j = 0; j < arestas[i].length; j++){
                for (Aresta a : arestas[i][j]){
                    if (v == a.getOrigem() || v == a.getFim()){
                        if (!contadas.contains(a)){
                            contadas.add(a);
                        }
                    }
                }
            }
        }

        return contadas;
    }

}