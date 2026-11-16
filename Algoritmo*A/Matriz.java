public class Matriz {

    private int[][] matriz;

    public Matriz(int linhas, int colunas) {
        this.matriz = new int[linhas][colunas];
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
}