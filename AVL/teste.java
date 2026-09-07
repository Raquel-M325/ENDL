package AVL;

public class teste {
    public static void main(String[] args) {

        arvore arvore = new arvore();

        // Criando os nós
        No no1 = new No("10", 10);
        No no2 = new No("20", 20);
        No no3 = new No("30", 30);

        // Inserindo
        arvore.insert(no1, "10");
        arvore.insert(no2, "20");
        arvore.insert(no3, "30");

        // Verificando
        System.out.println("Tamanho: " + arvore.size());
        System.out.println("É AVL? " + arvore.isAVL());
        System.out.println("Raiz: " + arvore.getRoot().getChave());
        System.out.println("Altura: " + arvore.height());
        System.out.println("Balanceamento: " + arvore.balancing());

        System.out.println("Árvore: " + arvore.mostrar(arvore.getRoot()));
    }
}
