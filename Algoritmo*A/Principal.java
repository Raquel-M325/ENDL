public class Principal {

    public static void main(String[] args) {

        Matriz matriz = new Matriz(5, 5);

        matriz.mostrarMatriz();

        int[][] labirinto = matriz.getMatriz();

        AlgoritmoEstrela estrela = new AlgoritmoEstrela(labirinto);
        AlgoritmoDijkstra dijkstra = new AlgoritmoDijkstra(labirinto);

    }
}