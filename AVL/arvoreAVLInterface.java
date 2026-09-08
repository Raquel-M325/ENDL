package AVL;

public interface arvoreAVLInterface extends arvoreABP {
    boolean isAVL();
    int balancing(No node);
    No rotationEsq(No node);
    No rotationDir(No node);
}    