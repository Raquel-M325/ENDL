package AVL;

public class teste {

    public static void main(String[] args) {

        arvoreAVL arvore = new arvoreAVL();

        // Criando os nós
        No no1 = new No("10", 10);
        No no2 = new No("20", 20);
        No no3 = new No("30", 30);

        // Inserindo
        arvore.insert(no1, "10");
        arvore.insert(no2, "20");
        arvore.insert(no3, "30");

        // Verificando a árvore após as inserções
        System.out.println("===== APÓS INSERÇÕES =====");
        System.out.println("Tamanho: " + arvore.size());
        System.out.println("É AVL? " + arvore.isAVL());
        System.out.println("Raiz: " + arvore.getRoot().getChave());
        System.out.println("Altura: " + arvore.height());
        System.out.println("Balanceamento da raiz: " + arvore.balancing());
        System.out.println("Árvore: " + arvore.mostrar(arvore.getRoot()));

        // Testando busca
        System.out.println("\n===== BUSCA =====");

        No busca = new No("20", 20);

        try {
            No encontrado = arvore.find(busca);
            System.out.println("Nó encontrado: " + encontrado.getChave());

        } catch (Correcao erro) {
            System.out.println(erro.getMessage());

        }

        // Testando remoção
        System.out.println("\n===== REMOÇÃO =====");

        No remover = new No("30", 30);

        try {
            No removido = arvore.remove(remover);
            System.out.println("Nó removido: " + removido.getChave());

        } catch (Correcao erro) {
            System.out.println(erro.getMessage());
        }

        // Verificando novamente
        System.out.println("\n===== APÓS REMOÇÃO =====");
        System.out.println("Tamanho: " + arvore.size()); 
        System.out.println("É AVL? " + arvore.isAVL());
        System.out.println("Raiz: " + arvore.getRoot().getChave());
        System.out.println("Altura: " + arvore.height());
        System.out.println("Balanceamento da raiz: " + arvore.balancing());
        System.out.println("Árvore: " + arvore.mostrar(arvore.getRoot()));
    }
}