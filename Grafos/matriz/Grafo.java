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

    public void removeVertice(Vertice v) throws Correcao {
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        if (v == null){
            throw new Correcao("Vertice não encontrado");
        }

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i] == v){

                //preciso tirar aresta que nao vai ter mais ligacao
                for (int j = 0; j < arestas.length; j++){
                    arestas[i][j].clear();
                    arestas[j][i].clear();
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

        for (int i = 0; i < arestas.length; i++){
            for (int j = 0; j < arestas[i].length; j++){
                if (vertices[i] == v1 && vertices[j] == v2){  
                    
                    //precisa definir origem e fim
                    a.setOrigem(v1);
                    a.setFim(v2);

                    if (a.getDirecionada() == true){
                        arestas[i][j].add(a);
                        return;
                    }

                    //sao do mesmo sentido
                    else{
                        arestas[i][j].add(a);

                        //se for laço (i == j), não pode adicionar duas vezes na mesma célula
                        if (i != j){
                            arestas[j][i].add(a);
                        }
                        return;
                    }
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

        for (int i = 0; i < arestas.length; i++){
            for (int j = 0; j < arestas[i].length; j++){
                if (vertices[i] == v1 && vertices[j] == v2){
                    if (arestas[i][j].contains(a)){
                        return true;
                    } else {
                        return false;
                    }
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

        //ele retorna um array de vertice que é seu origem e fim que pertencem a mesma aresta
        for (int i = 0; i < arestas.length; i++){
            for (int j = 0; j < arestas[i].length; j++){
                if (arestas[i][j].contains(a)){
                    return new Vertice[]{a.getOrigem(), a.getFim()};
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

        for (int i = 0; i < arestas.length; i++){
            for (int j = 0; j < arestas[i].length; j++){
                if (arestas[i][j].contains(a)){
                    a.setElement(elemento);
                    return;
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

        for (int i = 0; i < arestas.length; i++){
            for (int j = 0; j < arestas[i].length; j++){
                if (arestas[i][j].contains(a)){
                    return a.getDirecionada();
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

        for (int i = 0; i < arestas.length; i++){
            for (int j = 0; j < arestas[i].length; j++){
                if (vertices[i] == v1 && vertices[j] == v2){

                    //só altera a aresta depois de confirmar que os vertices existem
                    a.setDirecionada(true);
                    a.setOrigem(v1);
                    a.setFim(v2);

                    arestas[i][j].add(a);
                    return;
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

    public ArrayList<Aresta>[][] arestas() throws Correcao{
        if (isEmpty()){
            throw new Correcao("Grafo vazio");
        }

        return arestas;
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