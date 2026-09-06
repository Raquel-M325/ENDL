package AVL;

public interface arvoreAVL {
    int size();
    boolean isEmpty();
    boolean isAVL();
    boolean isRoot();;
    void insert(No node, Object o);
    No remove(No node);
    No find(No node);
    No getRoot();
    int balancing(No node);
    int height(No node);
    No rotationEsq(No node);
    No rotationDir(No node);
}