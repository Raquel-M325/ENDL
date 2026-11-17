package Algoritmo.A.A;
import Algoritmo.A.Correcao;

public interface InterfaceEstrela{
    void insertNo(No no);
    No removeMenorNo() throws Correcao;
    int calcularH(No no, No destino);
    List<No> recuperarCaminho(No no) throws Correcao;
    List<No> findCaminho(No inicio, No destino);
    boolean isEmpty();
}