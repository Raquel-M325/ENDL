package Algoritmo.Dijkstra;

import java.util.List;
import Algoritmo.Correcao;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.ArrayList;


public class AlgoritmoDijkstra implements InterfaceDijkstra{
    private PriorityQueue<No> filaPrioridade;
    private int[][] map;

    public AlgoritmoDijkstra(int[][] map) {
        this.filaPrioridade = new PriorityQueue<>();
        this.map = map;

    }

    public void insertNo(No no){
        filaPrioridade.add(no);
    }

    public No removeMenorNo() throws Correcao{
        if (isEmpty()){
            throw new Correcao("Lista vazia para remover nó");
        }

        return filaPrioridade.poll();
    }

    public List<No> recuperarCaminho(No no){

        List<No> caminho = new ArrayList<>();

        while (no != null) {
            caminho.add(0, no);
            no = no.getPassoAnterior();
        }

        return caminho;
    }

    public List<No> findCaminho(No inicio, No destino){

        inicio.setG(0);
        inicio.setPassoAnterior(null);

        filaPrioridade.clear();
        filaPrioridade.add(inicio);

        int[][] movimentos = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        Set<No> visitados = new HashSet<>();

        while (!filaPrioridade.isEmpty()) {

            No atual = filaPrioridade.poll();

            if (visitados.contains(atual)) {
                continue;
            }

            if (atual.equals(destino)) {
                return recuperarCaminho(atual);
            }

            visitados.add(atual);

            for (int[] movimento : movimentos) {

                int novaLinha = atual.getLinha() + movimento[0];
                int novaColuna = atual.getColuna() + movimento[1];

                // Verifica se a posição está dentro da matriz
                if (novaLinha < 0 || novaLinha >= map.length || novaColuna < 0 || novaColuna >= map[0].length) {
                    continue;
                }

                // Verifica se a posição é um obstáculo
                if (map[novaLinha][novaColuna] == 1) {
                    continue;
                }

                No vizinho = new No(novaLinha, novaColuna);

                // Ignora nós que já foram visitados
                if (visitados.contains(vizinho)) {
                    continue;
                }

                // Calcula os custos do vizinho
                vizinho.setG(atual.getG() + 1);

                // Registra de onde veio
                vizinho.setPassoAnterior(atual);

                filaPrioridade.add(vizinho);
            }
        }

        // Retorna null se não encontrar caminho
        return null;
    }
    

    public boolean isEmpty(){
        return filaPrioridade.isEmpty();
    }

}