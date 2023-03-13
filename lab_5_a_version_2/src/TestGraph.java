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




    }



}
