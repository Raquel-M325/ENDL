package RubroNegro;

import java.util.InputMismatchException; //CORREÇÃO 1: necessario para tratar letras digitadas no lugar de numeros
import java.util.Scanner;

public class teste {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        arvoreRubro arvore = new arvoreRubro();

        int opcao = -1; //CORREÇÃO 1: precisa iniciar com valor, pois agora ela pode nao ser lida se der erro

        do {

            System.out.println();
            System.out.println("========== ÁRVORE RubroNegro ==========");
            System.out.println("1 - Inserir nó");
            System.out.println("2 - Remover nó");
            System.out.println("3 - Buscar nó");
            System.out.println("4 - Mostrar árvore");
            System.out.println("0 - Sair");
            System.out.println("================================");
            System.out.print("Digite uma opção: ");

            //CORREÇÃO 1: se digitar letra onde é numero, o nextInt lançava InputMismatchException e o programa fechava
            try {

                opcao = entrada.nextInt();

                switch (opcao) {

                    case 1:

                        System.out.print("Digite a chave: ");
                        int chaveInserir = entrada.nextInt();

                        //CORREÇÃO 2: nao deixa inserir chave repetida (a busca ja existia, so nao era usada aqui)
                        No existente = null;

                        if (!arvore.isEmpty()) {

                            try {
                                existente = arvore.find(new No(null, chaveInserir));
                            } catch (Correcao erro) {
                                existente = null; //nao achou, entao pode inserir
                            }
                        }

                        if (existente != null) {

                            System.out.println(
                                "Já existe um nó com essa chave!"
                            );

                            break;
                        }

                        System.out.print("Digite o elemento: ");
                        String elementoInserir = entrada.next();

                        No novo = new No(
                            elementoInserir,
                            chaveInserir
                        );

                        try {

                            arvore.verificarAntesInsert(
                                novo,
                                elementoInserir
                            );

                            System.out.println(
                                "Nó inserido com sucesso!"
                            );

                        } catch (Correcao erro) {

                            System.out.println(
                                erro.getMessage()
                            );
                        }

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

                            arvore.verificarAntesRemove(remover);

                            System.out.println(
                                "Nó removido com sucesso!"
                            );

                        } catch (Correcao erro) {

                            //agora, se a chave nao existir, cai aqui com "Nó não encontrado"
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
                                "Fator de altura Preto: "
                                + arvore.alturaPreto(encontrado)
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

            } catch (InputMismatchException erroEntrada) { //CORREÇÃO 1

                entrada.nextLine(); //descarta o que foi digitado errado para nao travar em loop

                System.out.println(
                    "Entrada inválida! Digite apenas números inteiros."
                );
            }

        } while (opcao != 0);

        entrada.close();
    }
}