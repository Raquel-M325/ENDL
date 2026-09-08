package AVL;

import java.util.Scanner;

public class teste {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        arvoreAVL arvore = new arvoreAVL();

        int opcao;

        do {

            System.out.println();
            System.out.println("========== ÁRVORE AVL ==========");
            System.out.println("1 - Inserir nó");
            System.out.println("2 - Remover nó");
            System.out.println("3 - Buscar nó");
            System.out.println("4 - Mostrar árvore");
            System.out.println("5 - Verificar se é AVL");
            System.out.println("0 - Sair");
            System.out.println("================================");
            System.out.print("Digite uma opção: ");

            opcao = entrada.nextInt();

            switch (opcao) {

                case 1:

                    System.out.print("Digite a chave: ");
                    int chaveInserir = entrada.nextInt();

                    System.out.print("Digite o elemento: ");
                    String elementoInserir = entrada.next();

                    No novo = new No(
                        elementoInserir,
                        chaveInserir
                    );

                    arvore.insert(
                        novo,
                        elementoInserir
                    );

                    System.out.println(
                        "Nó inserido com sucesso!"
                    );

                    break;

                case 2:

                    if (arvore.isEmpty()) {

                        System.out.println(
                            "Árvore vazia."
                        );

                        break;
                    }

                    System.out.print(
                        "Digite a chave do nó a remover: "
                    );

                    int chaveRemover = entrada.nextInt();

                    No remover = new No(
                        null,
                        chaveRemover
                    );

                    try {

                        arvore.remove(remover);

                        System.out.println(
                            "Nó removido com sucesso!"
                        );

                    } catch (Correcao erro) {

                        System.out.println(
                            erro.getMessage()
                        );
                    }

                    break;

                case 3:

                    if (arvore.isEmpty()) {

                        System.out.println(
                            "Árvore vazia."
                        );

                        break;
                    }

                    System.out.print(
                        "Digite a chave do nó a buscar: "
                    );

                    int chaveBusca = entrada.nextInt();

                    No busca = new No(
                        null,
                        chaveBusca
                    );

                    try {

                        No encontrado =
                            arvore.find(busca);

                        System.out.println(
                            "Nó encontrado!"
                        );

                        System.out.println(
                            "Chave: "
                            + encontrado.getChave()
                        );

                        System.out.println(
                            "Elemento: "
                            + encontrado.getElement()
                        );

                        System.out.println(
                            "Fator de balanceamento: "
                            + arvore.balancing(encontrado)
                        );

                    } catch (Correcao erro) {

                        System.out.println(
                            erro.getMessage()
                        );
                    }

                    break;

                case 4:

                    if (arvore.isEmpty()) {

                        System.out.println(
                            "Árvore vazia."
                        );

                        break;
                    }

                    try {

                        System.out.println();
                        System.out.println(
                            "========== ÁRVORE =========="
                        );

                        System.out.println(
                            arvore.mostrar(
                                arvore.getRoot()
                            )
                        );

                    } catch (Correcao erro) {

                        System.out.println(
                            erro.getMessage()
                        );
                    }

                    break;

                case 5:

                    if (arvore.isAVL()) {

                        System.out.println(
                            "A árvore é AVL."
                        );

                    } else {

                        System.out.println(
                            "A árvore não é AVL."
                        );
                    }

                    break;

                case 0:

                    System.out.println(
                        "Programa encerrado."
                    );

                    break;

                default:

                    System.out.println(
                        "Opção inválida."
                    );
            }

        } while (opcao != 0);

        entrada.close();
    }
}
