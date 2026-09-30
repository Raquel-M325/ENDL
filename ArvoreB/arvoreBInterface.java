package ArvoreB;

public interface arvoreBInterface{
    String mostrar(No node) throws Correcao;
    void insert(Object o);
    void remove(Object o) throws Correcao;
    No find(Object o) throws Correcao;
    boolean isEmpty();

}