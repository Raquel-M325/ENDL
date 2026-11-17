package Algoritmo.A.Dijkstra;
import java.util.List;
import Algoritmo.A.Correcao;

public interface InterfaceDijkstra{
    void insertNo(No no);
    No removeMenorNo() throws Correcao;
    List<No> recuperarCaminho(No no) throws Correcao;
    List<No> findCaminho(No inicio, No destino);
    boolean isEmpty();
}