package RubroNegro;

public interface arvoreRubroInterface extends arvoreABP {
    No rotationEsq(No node);
    No rotationDir(No node);
    int alturaPreto(No node);
    String mostrar(No node) throws Correcao;

}