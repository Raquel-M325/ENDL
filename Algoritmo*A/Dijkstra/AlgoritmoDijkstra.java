package Algoritmo.A.Dijkstra;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import Algoritmo.A.Correcao;

public class AlgoritmoDijkstra implements InterfaceDijkstra{
    private PriorityQueue<No> filaPrioridade;

    public AlgoritmoDijkstra() {
        this.filaPrioridade = new PriorityQueue<>();
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

    public List<No> recuperarCaminho(No no) throws Correcao {

        List<No> caminho = new ArrayList<>();

        while (no != null) {
            caminho.add(0, no);
            no = no.getPassoAnterior();
        }

        return caminho;
    }

    public List<No> findCaminho(No inicio, No destino){
        inicio.set
    }

    public boolean isEmpty(){
        return filaPrioridade.isEmpty();
    }

}