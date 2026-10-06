package ArvoreB;

public interface arvoreBInterface{
    No find(Object o) throws Correcao;
    void insert(Object o);
    void remove(Object o) throws Correcao;
    String mostrar(No node);
}