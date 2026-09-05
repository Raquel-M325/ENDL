public interface arvoreAVL {
    int size();
    boolean isEmpty();
    boolean isAVL();
    boolean isRoot();
    boolean isfilhoDir();
    boolean isfilhoEsq();
    void insert(No node);
    No remove();
    No find();
    No getfilhoDir();
    No getfilhoEsq();
    No getRoot();
    int balancing(No node);
    int height(No node);
    No rotationEsq(No node);
    No rotationDir(No node);
}