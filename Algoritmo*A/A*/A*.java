package Algoritmo.A.A;
import java.util.List;
import Algoritmo.A.Correcao;
import java.util.PriorityQueue;

public class AlgoritmoEstrela implements InterfaceEstrela{
    private PriorityQueue<No> filaPrioridade;

    public AlgoritmoEstrela() {
        this.filaPrioridade = new PriorityQueue<>();
    }
    
    public void insertNo(No no){
        filaPrioridade.add(no);
    }

    public No removeMenorNo() throws Correcao{
        if (isEmpty()){
            throw new Correcao("Não há nós na lista para remover");
        }

        return filaPrioridade.poll();
    }


    public int calcularH(No no, No destino){
        return 0;
    }

    public List<No> recuperarCaminho(No no) throws Correcao{
        if (isEmpty()){
            throw new Correcao("Nem houve caminho para recuperar");
        }
        
        return null;
    }

    public List<No> findCaminho(No inicio, No destino) throws Correcao{
        if (isEmpty()){
            throw new Correcao("Lista vazia para encontrar caminho");
        }
        
        return null;
    }

    public boolean isEmpty(){
        return filaPrioridade.isEmpty();
    }

}