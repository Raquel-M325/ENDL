public interface arvoreAVL {
    int size();
    boolean isEmpty();
    boolean isAVL();
    boolean isRoot();;
    void insert(No node, Object o);
    No remove();
    No find();
    No getRoot();
    int balancing(No node);
    int height(No node);
    No rotationEsq(No node);
    No rotationDir(No node);
}