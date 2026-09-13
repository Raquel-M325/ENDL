package RubroNegro;

public interface arvoreRubroInterface extends arvoreABP {
    boolean isRubroNegro();
    int balancing(No node);
    No rotationEsq(No node);
    No rotationDir(No node);
}    