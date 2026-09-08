package AVL;

public class teste {
    public static void main(String[] args) {

        arvoreAVL arvore = new arvoreAVL();

        No no1 = new No("10", 10);
        No no2 = new No("5", 5);
        No no3 = new No("15", 15);
        No no4 = new No("2", 2);
        No no5 = new No("8", 8);
        No no6 = new No("22", 22);

        arvore.insert(no1, "10");
        arvore.insert(no2, "5");
        arvore.insert(no3, "15");
        arvore.insert(no4, "2");
        arvore.insert(no5, "8");
        arvore.insert(no6, "22");

        System.out.println("===== 1 - ÁRVORE INICIAL =====");
        System.out.println(arvore.mostrar(arvore.getRoot()));


        No no7 = new No("25", 25);

        arvore.insert(no7, "25");

        System.out.println("===== 2 - APÓS INSERIR 25 =====");
        
        System.out.println(arvore.mostrar(arvore.getRoot()));


        System.out.println("===== 3 - APÓS ROTAÇÃO À ESQUERDA =====");
        System.out.println(arvore.mostrar(arvore.getRoot()));


        No remover = new No("5", 5);

        try {

            arvore.remove(remover);

        } catch (Correcao erro) {

            System.out.println(erro.getMessage());

        }

        System.out.println("===== 4 - APÓS REMOVER 5 =====");
        System.out.println(arvore.mostrar(arvore.getRoot()));

    }

}