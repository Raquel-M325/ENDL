
package Grafos;

import java.util.Scanner;

public class Teste {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        Grafo grafo = new Grafo(10);

        Vertice[] vertices = new Vertice[10];
        Aresta[] arestas = new Aresta[20];

        int opcao;
        int quantidadeVertices = 0;
        int quantidadeArestas = 0;

        do {
            System.out.println("\n===== MENU DO GRAFO =====");
            System.out.println("1 - Inserir vertice");
            System.out.println("2 - Inserir aresta");
            System.out.println("3 - Remover vertice");
            System.out.println("4 - Remover aresta");
            System.out.println("5 - Verificar adjacencia");
            System.out.println("6 - Consultar vertice oposto");
            System.out.println("7 - Consultar vertices finais");
            System.out.println("8 - Substituir elemento de vertice");
            System.out.println("9 - Substituir elemento de aresta");
            System.out.println("10 - Verificar se o grafo esta vazio");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = entrada.nextInt();
            entrada.nextLine();

            try {
                switch (opcao) {

                    case 1: {
                        if (quantidadeVertices >= vertices.length) {
                            System.out.println("Limite de vertices do teste atingido.");
                            break;
                        }

                        System.out.print("Digite o elemento do vertice: ");
                        String elemento = entrada.nextLine();

                        Vertice v = new Vertice();
                        v.setElement(elemento);

                        grafo.insertVertice(v);
                        vertices[quantidadeVertices] = v;

                        System.out.println("Vertice inserido com sucesso. Indice: "
                                + quantidadeVertices);

                        quantidadeVertices++;
                        break;
                    }

                    case 2: {
                        if (quantidadeArestas >= arestas.length) {
                            System.out.println("Limite de arestas do teste atingido.");
                            break;
                        }

                        System.out.print("Indice do vertice de origem: ");
                        int origem = entrada.nextInt();

                        System.out.print("Indice do vertice de destino: ");
                        int destino = entrada.nextInt();
                        entrada.nextLine();

                        if (origem < 0 || origem >= vertices.length
                                || destino < 0 || destino >= vertices.length
                                || vertices[origem] == null
                                || vertices[destino] == null) {
                            System.out.println("Indice invalido ou vertice inexistente.");
                            break;
                        }

                        System.out.print("Elemento da aresta: ");
                        String elemento = entrada.nextLine();

                        System.out.print("Aresta direcionada? (s/n): ");
                        String resposta = entrada.nextLine();

                        Aresta a = new Aresta();
                        a.setElement(elemento);
                        a.setOrigem(vertices[origem]);
                        a.setFim(vertices[destino]);
                        a.setDirecionada(resposta.equalsIgnoreCase("s"));

                        grafo.insertAresta(a, vertices[origem], vertices[destino]);
                        arestas[quantidadeArestas] = a;

                        System.out.println("Aresta inserida com sucesso. Indice: "
                                + quantidadeArestas);

                        quantidadeArestas++;
                        break;
                    }

                    case 3: {
                        System.out.print("Indice do vertice a remover: ");
                        int indice = entrada.nextInt();
                        entrada.nextLine();

                        if (indice < 0 || indice >= vertices.length
                                || vertices[indice] == null) {
                            System.out.println("Vertice inexistente.");
                            break;
                        }

                        grafo.removeVertice(vertices[indice]);
                        vertices[indice] = null;

                        System.out.println("Vertice removido com sucesso.");
                        break;
                    }

                    case 4: {
                        System.out.print("Indice da aresta a remover: ");
                        int indice = entrada.nextInt();
                        entrada.nextLine();

                        if (indice < 0 || indice >= arestas.length
                                || arestas[indice] == null) {
                            System.out.println("Aresta inexistente.");
                            break;
                        }

                        grafo.removeAresta(arestas[indice]);
                        arestas[indice] = null;

                        System.out.println("Aresta removida com sucesso.");
                        break;
                    }

                    case 5: {
                        System.out.print("Indice do primeiro vertice: ");
                        int i = entrada.nextInt();

                        System.out.print("Indice do segundo vertice: ");
                        int j = entrada.nextInt();

                        System.out.print("Indice da aresta: ");
                        int k = entrada.nextInt();
                        entrada.nextLine();

                        if (i < 0 || i >= vertices.length
                                || j < 0 || j >= vertices.length
                                || k < 0 || k >= arestas.length
                                || vertices[i] == null
                                || vertices[j] == null
                                || arestas[k] == null) {
                            System.out.println("Vertice ou aresta inexistente.");
                            break;
                        }

                        System.out.println("Sao adjacentes: "
                                + grafo.isAdjacent(vertices[i], vertices[j], arestas[k]));
                        break;
                    }

                    case 6: {
                        System.out.print("Indice do vertice: ");
                        int i = entrada.nextInt();

                        System.out.print("Indice da aresta: ");
                        int j = entrada.nextInt();
                        entrada.nextLine();

                        if (i < 0 || i >= vertices.length
                                || j < 0 || j >= arestas.length
                                || vertices[i] == null || arestas[j] == null) {
                            System.out.println("Vertice ou aresta inexistente.");
                            break;
                        }

                        Vertice oposto = grafo.oposto(vertices[i], arestas[j]);
                        System.out.println("Vertice oposto: " + oposto.getElement());
                        break;
                    }

                    case 7: {
                        System.out.print("Indice da aresta: ");
                        int i = entrada.nextInt();
                        entrada.nextLine();

                        if (i < 0 || i >= arestas.length || arestas[i] == null) {
                            System.out.println("Aresta inexistente.");
                            break;
                        }

                        Vertice[] finais = grafo.finalVertices(arestas[i]);

                        System.out.println("Vertice de origem: "
                                + finais[0].getElement());
                        System.out.println("Vertice de destino: "
                                + finais[1].getElement());
                        break;
                    }

                    case 8: {
                        System.out.print("Indice do vertice: ");
                        int i = entrada.nextInt();
                        entrada.nextLine();

                        if (i < 0 || i >= vertices.length || vertices[i] == null) {
                            System.out.println("Vertice inexistente.");
                            break;
                        }

                        System.out.print("Novo elemento: ");
                        String elemento = entrada.nextLine();

                        grafo.substituirVertice(vertices[i], elemento);
                        System.out.println("Vertice atualizado.");
                        break;
                    }

                    case 9: {
                        System.out.print("Indice da aresta: ");
                        int i = entrada.nextInt();
                        entrada.nextLine();

                        if (i < 0 || i >= arestas.length || arestas[i] == null) {
                            System.out.println("Aresta inexistente.");
                            break;
                        }

                        System.out.print("Novo elemento: ");
                        String elemento = entrada.nextLine();

                        grafo.substituirAresta(arestas[i], elemento);
                        System.out.println("Aresta atualizada.");
                        break;
                    }

                    case 10:
                        System.out.println("Grafo vazio: " + grafo.isEmpty());
                        break;

                    case 0:
                        System.out.println("Encerrando o programa...");
                        break;

                    default:
                        System.out.println("Opcao invalida.");
                }

            } catch (Correcao e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);

        entrada.close();
    }
}
