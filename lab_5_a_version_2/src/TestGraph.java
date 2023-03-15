public class TestGraph {

    public static void main(String[] args) {

        GraphFunction.BFC graph = new GraphFunction.BFC(0,6);

        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');

        graph.addEdge('A', 'B', 1);
        graph.addEdge('B', 'C', 1);
        graph.addEdge('C', 'D', 1);
        graph.addEdge('A', 'E', 1);
        graph.addEdge('E', 'F', 1);

        graph.bfc(0);


        GraphFunction.Dijkstra dijkstra = new GraphFunction.Dijkstra(4);

        dijkstra.addVertex('A');
        dijkstra.addVertex('B');
        dijkstra.addVertex('C');
        dijkstra.addVertex('D');

        dijkstra.addEdge('A', 'B', 10);
        dijkstra.addEdge('A', 'C', 5);
        dijkstra.addEdge('B', 'C', 6);
        dijkstra.addEdge('C', 'D', 3);
        dijkstra.addEdge('B', 'D', 4);

        dijkstra.dijkstra('A', 'D');



        System.out.println("Прима");
        GraphFunction.Prima prima = new GraphFunction.Prima(6);

        prima.addVertex('A');
        prima.addVertex('B');
        prima.addVertex('C');
        prima.addVertex('D');
        prima.addVertex('E');
        prima.addVertex('F');
        //prima.addVertex('G'); для сложного
        //prima.addVertex('H');

       /* prima.mas = new int[][]{{0, 7, 8, 6, 0, 0, 0, 0}, {7, 0, 2, 0, 11, 0, 0, 0}, {8, 2, 0, 7, 10, 3, 5, 0}, {6, 0, 7, 0, 0, 0, 10, 0}, {0, 11, 10, 0, 0, 6, 0, 19}, {0, 0, 3, 0, 6, 0, 12, 7}, {0, 0, 5, 10, 0, 12, 0, 4}, {0, 0, 0, 0, 19, 7, 4, 0}};

        prima.minPath(0);*/ // - сложный пример(работает)

        prima.addEdge('A','B',7);
        prima.addEdge('B','A',7);
        prima.addEdge('A','C',8);
        prima.addEdge('C','A',8);
        prima.addEdge('B','C',11);
        prima.addEdge('C','B',11);
        prima.addEdge('B','D',2);
        prima.addEdge('D','B',2);
        prima.addEdge('C','E',9);
        prima.addEdge('E','C',9);
        prima.addEdge('C','D',6);
        prima.addEdge('D','C',6);
        prima.addEdge('D','E',11);
        prima.addEdge('E','D',11);
        prima.addEdge('E','F',10);
        prima.addEdge('F','E',10);
        prima.addEdge('D','F',9);
        prima.addEdge('F','D',9);

        prima.minPath(4);

        //красивыф пример

    }



}
