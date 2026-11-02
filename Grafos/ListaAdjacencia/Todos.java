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
                        if (a.getFim() != v){
                            a.getFim().getAdjacencias().remove(a);
                        }
                    } else {
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

        if (a == null){
            throw new Correcao("Aresta não encontrada");
        }

        boolean encontrouAresta = false;

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i] != null){

                int j = 0;

                while (j < vertices[i].getAdjacencias().size()){
                    Aresta arestaAtual = vertices[i].getAdjacencias().get(j);

                    if (a == arestaAtual){
                        vertices[i].getAdjacencias().remove(a);
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
            if (vertices[i] == v){
                throw new Correcao("Vertice já existe");
            }
        }

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

        //faço a verificacao dos vertices se existe dentro da lista
        boolean encontrarV1 = false;
        boolean encontrarV2 = false;

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i] == v1){
                encontrarV1 = true;
            }

            if (vertices[i] == v2){
                encontrarV2 = true;
            }
        }

        if (encontrarV1 == false || encontrarV2 == false){
            throw new Correcao("Vertices não encontrados");
        }

        if (!((a.getOrigem() == v1 && a.getFim() == v2) ||
              (a.getOrigem() == v2 && a.getFim() == v1))){
            throw new Correcao("Vertices não correspondem à aresta");
        }

        int j = 0;

        //verifico se a aresta ja existe na lista de adjacencias dos vertices
        while (j < v1.getAdjacencias().size()){
            if (v1.getAdjacencias().get(j) == a){
                throw new Correcao("Aresta já existe");
            }

            j++;
        }

        j = 0;

        while (j < v2.getAdjacencias().size()){
            if (v2.getAdjacencias().get(j) == a){
                throw new Correcao("Aresta já existe");
            }

            j++;
        }

        a.setDirecionada(false);

        v1.getAdjacencias().add(a);

        if (v1 != v2){
            v2.getAdjacencias().add(a);
        }
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

        boolean encontrarV1 = false;
        boolean encontrarV2 = false;
        boolean encontrarArestaV1 = false;
        boolean encontrarArestaV2 = false;

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i] == v1){
                encontrarV1 = true;
            }

            if (vertices[i] == v2){
                encontrarV2 = true;
            }
        }

        if (encontrarV1 == false || encontrarV2 == false){
            throw new Correcao("Vertices não encontrados");
        }

        int j = 0;

        while (j < v1.getAdjacencias().size()){
            if (v1.getAdjacencias().get(j) == a){
                encontrarArestaV1 = true;
            }

            j++;
        }

        j = 0;

        while (j < v2.getAdjacencias().size()){
            if (v2.getAdjacencias().get(j) == a){
                encontrarArestaV2 = true;
            }

            j++;
        }

        if (encontrarArestaV1 == false || encontrarArestaV2 == false){
            return false;
        }

        if (a.getDirecionada() == true){
            return a.getOrigem() == v1 && a.getFim() == v2;
        }

        if (a.getOrigem() == v1 && a.getFim() == v2){
            return true;
        } else if (a.getOrigem() == v2 && a.getFim() == v1){
            return true;
        } else {
            return false;
        }
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
            if (vertices[i] != null){

                int j = 0;

                while (j < vertices[i].getAdjacencias().size()){
                    if (vertices[i].getAdjacencias().get(j) == a){
                        Vertice[] finais = new Vertice[2];
                        finais[0] = a.getOrigem();
                        finais[1] = a.getFim();
                        return finais;
                    }

                    j++;
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
            if (vertices[i] != null){

                int j = 0;

                while (j < vertices[i].getAdjacencias().size()){

                    if (vertices[i].getAdjacencias().get(j) == a){
                        vertices[i].getAdjacencias().get(j).setElement(elemento);
                        return;
                    }

                    j++;
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
            if (vertices[i] != null){

                int j = 0;

                while (j < vertices[i].getAdjacencias().size()){
                    if (vertices[i].getAdjacencias().get(j) == a){
                        return a.getDirecionada();
                    }

                    j++;
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

        boolean encontrarV1 = false;
        boolean encontrarV2 = false;

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i] == v1){
                encontrarV1 = true;
            }

            if (vertices[i] == v2){
                encontrarV2 = true;
            }
        }

        if (encontrarV1 == false || encontrarV2 == false){
            throw new Correcao("Vertices não encontrados");
        }

        if (a.getOrigem() != v1 || a.getFim() != v2){
            throw new Correcao("Vertices não correspondem à direção da aresta");
        }

        int j = 0;

        while (j < v1.getAdjacencias().size()){
            if (v1.getAdjacencias().get(j) == a){
                throw new Correcao("Aresta já existe");
            }

            j++;
        }

        j = 0;

        while (j < v2.getAdjacencias().size()){
            if (v2.getAdjacencias().get(j) == a){
                throw new Correcao("Aresta já existe");
            }

            j++;
        }

        a.setDirecionada(true);

        v1.getAdjacencias().add(a);

        if (v1 != v2){
            v2.getAdjacencias().add(a);
        }
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

        boolean encontrarV = false;

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i] == v){
                encontrarV = true;
            }
        }

        if (encontrarV == false){
            throw new Correcao("Vertice não encontrado");
        }

        int j = 0;
        int grau = 0;
        ArrayList<Aresta> contadas = new ArrayList<Aresta>();

        while (j < v.getAdjacencias().size()){
            Aresta a = v.getAdjacencias().get(j);

            if (!contadas.contains(a)){
                contadas.add(a);
                grau++;
            }

            j++;
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

        boolean encontrarV = false;

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i] == v){
                encontrarV = true;
            }
        }

        if (encontrarV == false){
            throw new Correcao("Vertice não encontrado");
        }

        int grauEntrada = 0;
        int j = 0;

        while (j < v.getAdjacencias().size()){
            Aresta a = v.getAdjacencias().get(j);

            if (a.getDirecionada() == true && v == a.getFim()){
                grauEntrada++;
            }

            j++;
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

        boolean encontrarV = false;

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i] == v){
                encontrarV = true;
            }
        }

        if (encontrarV == false){
            throw new Correcao("Vertice não encontrado");
        }

        int j = 0;
        int grauSaida = 0;

        while (j < v.getAdjacencias().size()){
            Aresta a = v.getAdjacencias().get(j);

            if (a.getDirecionada() == true && v == a.getOrigem()){
                grauSaida++;
            }

            j++;
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

        boolean encontrarV = false;

        for (int i = 0; i < vertices.length; i++){
            if (vertices[i] == v){
                encontrarV = true;
            }
        }

        if (encontrarV == false){
            throw new Correcao("Vertice não encontrado");
        }

        ArrayList<Aresta> contadas = new ArrayList<Aresta>();

        int j = 0;

        while (j < v.getAdjacencias().size()){
            Aresta a = v.getAdjacencias().get(j);

            if (!contadas.contains(a)){
                contadas.add(a);
            }

            j++;
        }

        return contadas;
    }
}
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
package Grafos.ListaAdjacencia;


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


}package Grafos.ListaAdjacencia;

public class Correcao extends RuntimeException{
    public Correcao(String erro){
        super(erro);
    }
}
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
    int grau(Vertice v) throws Correcao;
    int grauEntrada(Vertice v) throws Correcao;
    int grauSaida(Vertice v) throws Correcao;
    ArrayList<Aresta> arestasIncidentes(Vertice v) throws Correcao;
}
package Grafos.ListaAdjacencia;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Teste {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        Grafo grafo = new Grafo(10);

        Vertice[] vertices = new Vertice[10];
        Aresta[] arestas = new Aresta[20];

        int opcao = -1;
        int quantidadeVertices = 0;
        int quantidadeArestas = 0;

        do {
            System.out.println("\n===== MENU DO GRAFO =====");
            System.out.println("1 - Inserir vertice");
            System.out.println("2 - Inserir aresta");
            System.out.println("3 - Remover vertice");
            System.out.println("4 - Remover aresta");
            System.out.println("5 - Verificar adjacencia");
            System.out.println("6 - Consultar vertice oposto");
            System.out.println("7 - Consultar vertices finais");
            System.out.println("8 - Substituir elemento de vertice");
            System.out.println("9 - Substituir elemento de aresta");
            System.out.println("10 - Verificar se o grafo esta vazio");
            System.out.println("11 - Verificar se aresta e direcionada");
            System.out.println("12 - Listar vertices");
            System.out.println("13 - Listar arestas");
            System.out.println("14 - Grau do vertice");
            System.out.println("15 - Grau de entrada do vertice");
            System.out.println("16 - Grau de saida do vertice");
            System.out.println("17 - Arestas incidentes ao vertice");
            System.out.println("18 - Mostrar grafo");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = -1;

            try {
                opcao = entrada.nextInt();
                entrada.nextLine();

                switch (opcao) {

                    case 1: {
                        if (quantidadeVertices >= vertices.length) {
                            System.out.println("Limite de vertices do teste atingido.");
                            break;
                        }

                        System.out.print("Digite o elemento do vertice: ");
                        String elemento = entrada.nextLine();

                        Vertice v = new Vertice();
                        v.setElement(elemento);

                        grafo.insertVertice(v);
                        vertices[quantidadeVertices] = v;

                        System.out.println("Vertice inserido com sucesso. Indice: "
                                + quantidadeVertices);

                        quantidadeVertices++;
                        break;
                    }

                    case 2: {
                        if (quantidadeArestas >= arestas.length) {
                            System.out.println("Limite de arestas do teste atingido.");
                            break;
                        }

                        System.out.print("Indice do vertice de origem: ");
                        int origem = entrada.nextInt();

                        System.out.print("Indice do vertice de destino: ");
                        int destino = entrada.nextInt();
                        entrada.nextLine();

                        if (origem < 0 || origem >= vertices.length
                                || destino < 0 || destino >= vertices.length
                                || vertices[origem] == null
                                || vertices[destino] == null) {
                            System.out.println("Indice invalido ou vertice inexistente.");
                            break;
                        }

                        System.out.print("Elemento da aresta: ");
                        String elemento = entrada.nextLine();

                        System.out.print("Aresta direcionada? (s/n): ");
                        String resposta = entrada.nextLine();

                        Aresta a = new Aresta();
                        a.setElement(elemento);

                        a.setOrigem(vertices[origem]);
                        a.setFim(vertices[destino]);

                        if (resposta.equalsIgnoreCase("s")) {
                            grafo.insertArestaDirecionada(a, vertices[origem], vertices[destino]);
                        } else {
                            grafo.insertAresta(a, vertices[origem], vertices[destino]);
                        }

                        arestas[quantidadeArestas] = a;

                        System.out.println("Aresta inserida com sucesso. Indice: "
                                + quantidadeArestas);

                        quantidadeArestas++;
                        break;
                    }

                    case 3: {
                        System.out.print("Indice do vertice a remover: ");
                        int indice = entrada.nextInt();
                        entrada.nextLine();

                        if (indice < 0 || indice >= vertices.length
                                || vertices[indice] == null) {
                            System.out.println("Vertice inexistente.");
                            break;
                        }

                        Vertice removido = vertices[indice];
                        grafo.removeVertice(removido);
                        vertices[indice] = null;

                        for (int k = 0; k < arestas.length; k++) {
                            if (arestas[k] != null
                                    && (arestas[k].getOrigem() == removido
                                    || arestas[k].getFim() == removido)) {
                                arestas[k] = null;
                            }
                        }

                        System.out.println("Vertice removido com sucesso.");
                        break;
                    }

                    case 4: {
                        System.out.print("Indice da aresta a remover: ");
                        int indice = entrada.nextInt();
                        entrada.nextLine();

                        if (indice < 0 || indice >= arestas.length
                                || arestas[indice] == null) {
                            System.out.println("Aresta inexistente.");
                            break;
                        }

                        grafo.removeAresta(arestas[indice]);
                        arestas[indice] = null;

                        System.out.println("Aresta removida com sucesso.");
                        break;
                    }

                    case 5: {
                        System.out.print("Indice do primeiro vertice: ");
                        int i = entrada.nextInt();

                        System.out.print("Indice do segundo vertice: ");
                        int j = entrada.nextInt();

                        System.out.print("Indice da aresta: ");
                        int k = entrada.nextInt();
                        entrada.nextLine();

                        if (i < 0 || i >= vertices.length
                                || j < 0 || j >= vertices.length
                                || k < 0 || k >= arestas.length
                                || vertices[i] == null
                                || vertices[j] == null
                                || arestas[k] == null) {
                            System.out.println("Vertice ou aresta inexistente.");
                            break;
                        }

                        System.out.println("Sao adjacentes: "
                                + grafo.isAdjacent(vertices[i], vertices[j], arestas[k]));
                        break;
                    }

                    case 6: {
                        System.out.print("Indice do vertice: ");
                        int i = entrada.nextInt();

                        System.out.print("Indice da aresta: ");
                        int j = entrada.nextInt();
                        entrada.nextLine();

                        if (i < 0 || i >= vertices.length
                                || j < 0 || j >= arestas.length
                                || vertices[i] == null || arestas[j] == null) {
                            System.out.println("Vertice ou aresta inexistente.");
                            break;
                        }

                        Vertice oposto = grafo.oposto(vertices[i], arestas[j]);
                        System.out.println("Vertice oposto: " + oposto.getElement());
                        break;
                    }

                    case 7: {
                        System.out.print("Indice da aresta: ");
                        int i = entrada.nextInt();
                        entrada.nextLine();

                        if (i < 0 || i >= arestas.length || arestas[i] == null) {
                            System.out.println("Aresta inexistente.");
                            break;
                        }

                        Vertice[] finais = grafo.finalVertices(arestas[i]);

                        System.out.println("Vertice de origem: "
                                + finais[0].getElement());
                        System.out.println("Vertice de destino: "
                                + finais[1].getElement());
                        break;
                    }

                    case 8: {
                        System.out.print("Indice do vertice: ");
                        int i = entrada.nextInt();
                        entrada.nextLine();

                        if (i < 0 || i >= vertices.length || vertices[i] == null) {
                            System.out.println("Vertice inexistente.");
                            break;
                        }

                        System.out.print("Novo elemento: ");
                        String elemento = entrada.nextLine();

                        grafo.substituirVertice(vertices[i], elemento);
                        System.out.println("Vertice atualizado.");
                        break;
                    }

                    case 9: {
                        System.out.print("Indice da aresta: ");
                        int i = entrada.nextInt();
                        entrada.nextLine();

                        if (i < 0 || i >= arestas.length || arestas[i] == null) {
                            System.out.println("Aresta inexistente.");
                            break;
                        }

                        System.out.print("Novo elemento: ");
                        String elemento = entrada.nextLine();

                        grafo.substituirAresta(arestas[i], elemento);
                        System.out.println("Aresta atualizada.");
                        break;
                    }

                    case 10:
                        System.out.println("Grafo vazio: " + grafo.isEmpty());
                        break;

                    case 11: {
                        System.out.print("Indice da aresta: ");
                        int i = entrada.nextInt();
                        entrada.nextLine();

                        if (i < 0 || i >= arestas.length || arestas[i] == null) {
                            System.out.println("Aresta inexistente.");
                            break;
                        }

                        System.out.println("Direcionada: " + grafo.isDirected(arestas[i]));
                        break;
                    }

                    case 12: {
                        Vertice[] lista = grafo.vertices();

                        for (int i = 0; i < lista.length; i++) {
                            if (lista[i] != null) {

                                int indice = -1;
                                for (int k = 0; k < vertices.length; k++) {
                                    if (vertices[k] == lista[i]) {
                                        indice = k;
                                    }
                                }

                                System.out.println("Vertice " + indice + ": "
                                        + lista[i].getElement());
                            }
                        }
                        break;
                    }

                    case 13: {
                        Vertice[] lista = grafo.vertices();
                        ArrayList<Aresta> vistas = new ArrayList<Aresta>();

                        for (int i = 0; i < lista.length; i++) {
                            if (lista[i] != null) {
                                for (Aresta a : lista[i].getAdjacencias()) {
                                    if (!vistas.contains(a)) {
                                        vistas.add(a);

                                        int indice = -1;
                                        for (int k = 0; k < arestas.length; k++) {
                                            if (arestas[k] == a) {
                                                indice = k;
                                            }
                                        }

                                        System.out.println("Aresta " + indice
                                                + " [" + a.getElement() + "]: "
                                                + a.getOrigem().getElement()
                                                + (a.getDirecionada() ? " -> " : " -- ")
                                                + a.getFim().getElement());
                                    }
                                }
                            }
                        }

                        if (vistas.isEmpty()) {
                            System.out.println("Nao ha arestas.");
                        }
                        break;
                    }

                    case 14: {
                        System.out.print("Indice do vertice: ");
                        int i = entrada.nextInt();
                        entrada.nextLine();

                        if (i < 0 || i >= vertices.length || vertices[i] == null) {
                            System.out.println("Vertice inexistente.");
                            break;
                        }

                        System.out.println("Grau: " + grafo.grau(vertices[i]));
                        break;
                    }

                    case 15: {
                        System.out.print("Indice do vertice: ");
                        int i = entrada.nextInt();
                        entrada.nextLine();

                        if (i < 0 || i >= vertices.length || vertices[i] == null) {
                            System.out.println("Vertice inexistente.");
                            break;
                        }

                        System.out.println("Grau de entrada: " + grafo.grauEntrada(vertices[i]));
                        break;
                    }

                    case 16: {
                        System.out.print("Indice do vertice: ");
                        int i = entrada.nextInt();
                        entrada.nextLine();

                        if (i < 0 || i >= vertices.length || vertices[i] == null) {
                            System.out.println("Vertice inexistente.");
                            break;
                        }

                        System.out.println("Grau de saida: " + grafo.grauSaida(vertices[i]));
                        break;
                    }

                    case 17: {
                        System.out.print("Indice do vertice: ");
                        int i = entrada.nextInt();
                        entrada.nextLine();

                        if (i < 0 || i >= vertices.length || vertices[i] == null) {
                            System.out.println("Vertice inexistente.");
                            break;
                        }

                        ArrayList<Aresta> incidentes = grafo.arestasIncidentes(vertices[i]);

                        if (incidentes.isEmpty()) {
                            System.out.println("Nenhuma aresta incidente.");
                        }

                        for (Aresta a : incidentes) {

                            int indice = -1;
                            for (int k = 0; k < arestas.length; k++) {
                                if (arestas[k] == a) {
                                    indice = k;
                                }
                            }

                            System.out.println("Aresta " + indice + " [" + a.getElement() + "]");
                        }
                        break;
                    }

                    case 18: {
                        System.out.println("\n===== REPRESENTACAO DO GRAFO =====");

                        boolean encontrouVertice = false;

                        for (int i = 0; i < vertices.length; i++) {
                            if (vertices[i] != null) {
                                encontrouVertice = true;

                                Vertice v = vertices[i];

                                System.out.println("\nVertice " + i + " ["
                                        + v.getElement() + "]:");

                                if (v.getAdjacencias().isEmpty()) {
                                    System.out.println("  Sem arestas.");
                                } else {

                                    for (Aresta a : v.getAdjacencias()) {

                                        if (a.getDirecionada() == true) {

                                            if (a.getOrigem() == v) {
                                                System.out.println("  -> "
                                                        + a.getFim().getElement()
                                                        + " [" + a.getElement() + "]");
                                            } else {
                                                System.out.println("  <- "
                                                        + a.getOrigem().getElement()
                                                        + " [" + a.getElement() + "]");
                                            }

                                        } else {

                                            Vertice oposto;

                                            if (a.getOrigem() == v) {
                                                oposto = a.getFim();
                                            } else {
                                                oposto = a.getOrigem();
                                            }

                                            System.out.println("  -- "
                                                    + oposto.getElement()
                                                    + " [" + a.getElement() + "]");
                                        }
                                    }
                                }
                            }
                        }

                        if (encontrouVertice == false) {
                            System.out.println("Grafo vazio.");
                        }

                        break;
                    }

                    case 0:
                        System.out.println("Encerrando o programa...");
                        break;

                    default:
                        System.out.println("Opcao invalida.");
                }

            } catch (Correcao e) {
                System.out.println("Erro: " + e.getMessage());

            } catch (InputMismatchException e) {
                entrada.nextLine();
                System.out.println("Entrada invalida. Digite um numero.");
            }

        } while (opcao != 0);

        entrada.close();
    }
}
