package Algoritmo.A;

import java.util.List;
import Algoritmo.Correcao;

public interface InterfaceEstrela {

    void insertNo(No no);
    No removeMenorNo() throws Correcao;
    int calcularH(No no, No destino);
    List<No> recuperarCaminho(No no);
    List<No> findCaminho(No inicio, No destino);
    boolean isEmpty();
}