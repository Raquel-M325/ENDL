package Algoritmo.A.A;
import java.util.List;
import Algoritmo.A.Correcao;
import java.util.PriorityQueue;
import java.util.ArrayList;

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

    //precisa ter o calculo de quanto falta percorrer
    public int calcularH(No no, No destino){
        return Math.abs(no.getLinha() - destino.getLinha()) + Math.abs(no.getColuna() - destino.getColuna()); 
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
  
        
        return null;
    }

    public boolean isEmpty(){
        return filaPrioridade.isEmpty();
    }

}