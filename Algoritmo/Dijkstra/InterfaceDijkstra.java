package Algoritmo.Dijkstra;
import java.util.List;
import Algoritmo.Correcao;

public interface InterfaceDijkstra{
    void insertNo(No no);
    No removeMenorNo() throws Correcao;
    List<No> recuperarCaminho(No no);
    List<No> findCaminho(No inicio, No destino);
    boolean isEmpty();
}