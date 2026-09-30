package ArvoreB;

public interface arvoreABP {
    int size();
    boolean isEmpty();
    boolean isRoot();
    void verificarAntesInsert(No node, Object o);
    No verificarAntesRemove(No node) throws Correcao;
    No find(No node) throws Correcao;
    No getRoot();
    String mostrar(No node) throws Correcao;
}