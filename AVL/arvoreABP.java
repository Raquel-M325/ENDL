package AVL;

public interface arvoreABP {
    int size();
    boolean isEmpty();
    boolean isRoot();
    void insert(No node, Object o);
    No remove(No node) throws Correcao;
    No find(No node) throws Correcao;
    No getRoot();
    int height() throws Correcao;
    String mostrar(No node) throws Correcao;
}