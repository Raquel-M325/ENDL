package ArvoreB;

import java.util.Scanner;

public class teste{
    public static void main(String[] args){

        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite o grau mínimo da Árvore B: ");
        int grau = entrada.nextInt();

        arvoreB arvore = new arvoreB(grau);

        int opcao;

        do{
            System.out.println("\n========== ÁRVORE B ==========");
            System.out.println("1 - Inserir chave");
            System.out.println("2 - Remover chave");
            System.out.println("3 - Buscar chave");
            System.out.println("4 - Mostrar árvore");
            System.out.println("0 - Sair");
            System.out.print("Digite uma opção: ");

            opcao = entrada.nextInt();

            switch(opcao){

                case 1:
                    System.out.print("Digite a chave: ");
                    int chaveInserir = entrada.nextInt();

                    arvore.insert(chaveInserir);

                    System.out.println("Chave inserida!");
                    break;

                case 2:
                    System.out.print("Digite a chave a remover: ");
                    int chaveRemover = entrada.nextInt();

                    try{
                        arvore.remove(chaveRemover);
                        System.out.println("Chave removida!");
                    }
                    catch(Correcao erro){
                        System.out.println(erro.getMessage());
                    }

                    break;

                case 3:
                    System.out.print("Digite a chave a buscar: ");
                    int chaveBusca = entrada.nextInt();

                    try{
                        No encontrado = arvore.find(chaveBusca);

                        if (encontrado == null){
                            System.out.println("Chave não encontrada.");
                        } else {
                            System.out.println("Chave encontrada!");
                            System.out.println("Chaves do nó: " + arvore.mostrar(encontrado));
                        }
                    }
                    catch(Correcao erro){
                        System.out.println(erro.getMessage());
                    }

                    break;

                case 4:
                    System.out.println("Árvore:");
                    System.out.println(arvore.mostrar(arvore.root));
                    break;

                case 0:
                    System.out.println("Programa encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        }while(opcao != 0);

        entrada.close();
    }
}