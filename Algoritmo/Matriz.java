
package Algoritmo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Matriz {

    private int[][] matriz;
    private int[] inicio;
    private List<int[]> saidas;

    public Matriz(int linhas, int colunas) {
        this.matriz = new int[linhas][colunas];
        this.saidas = new ArrayList<>();
    }

    public Matriz(String arquivo) throws IOException {

        List<String> linhas = Files.readAllLines(Path.of(arquivo));

        matriz = new int[linhas.size()][];
        saidas = new ArrayList<>();

        for (int i = 0; i < linhas.size(); i++) {

            String linha = linhas.get(i).trim();

            matriz[i] = new int[linha.length()];

            for (int j = 0; j < linha.length(); j++) {

                int valor = Character.getNumericValue(linha.charAt(j));

                matriz[i][j] = valor;

                if (valor == 2) {
                    inicio = new int[]{i, j};
                }

                if (valor == 3) {
                    saidas.add(new int[]{i, j});
                }
            }
        }
    }

    public void mostrarMatriz() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public int[] getInicio() {
        return inicio;
    }

    public List<int[]> getSaidas() {
        return saidas;
    }
}
