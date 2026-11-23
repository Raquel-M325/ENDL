
package Algoritmo;

import java.util.List;

import Algoritmo.A.No;
import Algoritmo.A.AlgoritmoEstrela;
import Algoritmo.Dijkstra.AlgoritmoDijkstra;

public class Principal {

    public static void main(String[] args) throws Exception {

        Matriz matriz = new Matriz("labirinto.dat");

        System.out.println("===== LABIRINTO =====");
        matriz.mostrarMatriz();

        int[][] labirinto = matriz.getMatriz();

        int[] posicaoInicio = matriz.getInicio();

        List<int[]> saidas = matriz.getSaidas();


        // ================= DIJKSTRA =================

        AlgoritmoDijkstra dijkstra = new AlgoritmoDijkstra(labirinto);

        System.out.println("\n===== ALGORITMO DIJKSTRA =====");

        long tempoInicioDijkstra = System.nanoTime();

        List<Algoritmo.Dijkstra.No> caminhoDijkstra = null;

        for (int[] saida : saidas) {

            Algoritmo.Dijkstra.No inicio =
                    new Algoritmo.Dijkstra.No(posicaoInicio[0], posicaoInicio[1]);

            Algoritmo.Dijkstra.No destino =
                    new Algoritmo.Dijkstra.No(saida[0], saida[1]);

            List<Algoritmo.Dijkstra.No> caminho =
                    dijkstra.findCaminho(inicio, destino);

            // Guarda o menor caminho encontrado
            if (caminho != null) {

                if (caminhoDijkstra == null ||
                    caminho.size() < caminhoDijkstra.size()) {

                    caminhoDijkstra = caminho;
                }
            }
        }

        long tempoFimDijkstra = System.nanoTime();

        if (caminhoDijkstra != null) {

            System.out.println("Caminho encontrado pelo Dijkstra:");

            for (Algoritmo.Dijkstra.No no : caminhoDijkstra) {
                System.out.println("(" + no.getLinha() + ", " + no.getColuna() + ")");
            }

            System.out.println("Total de passos: " + (caminhoDijkstra.size() - 1));

        } else {
            System.out.println("Nenhuma saída foi encontrada.");
        }

        System.out.println("Tempo de execução: " +
                (tempoFimDijkstra - tempoInicioDijkstra) + " nanossegundos");


        // ================= A* =================

        AlgoritmoEstrela estrela = new AlgoritmoEstrela(labirinto);

        System.out.println("\n===== ALGORITMO A* (A ESTRELA) =====");

        long tempoInicioEstrela = System.nanoTime();

        List<No> caminhoEstrela = null;

        for (int[] saida : saidas) {

            No destino = new No(saida[0], saida[1]);

            // Cria um novo início para cada tentativa
            No novoInicio = new No(posicaoInicio[0], posicaoInicio[1]);

            List<No> caminho = estrela.findCaminho(novoInicio, destino);

            // Guarda o menor caminho encontrado
            if (caminho != null) {

                if (caminhoEstrela == null ||
                    caminho.size() < caminhoEstrela.size()) {

                    caminhoEstrela = caminho;
                }
            }
        }

        long tempoFimEstrela = System.nanoTime();

        if (caminhoEstrela != null) {

            System.out.println("Caminho encontrado pelo A*:");

            for (No no : caminhoEstrela) {
                System.out.println("(" + no.getLinha() + ", " + no.getColuna() + ")");
            }

            System.out.println("Total de passos: " + (caminhoEstrela.size() - 1));

        } else {
            System.out.println("Nenhuma saída foi encontrada.");
        }

        System.out.println("Tempo de execução: " +
                (tempoFimEstrela - tempoInicioEstrela) + " nanossegundos");

    }
}
