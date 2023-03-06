public class TestGraph {

    public static void main(String[] args) {

        Graph graph = new Graph();

        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');

        graph.addEdge('A','B',1);
        graph.addEdge('B','C',1);
        graph.addEdge('C','D',1);
        graph.addEdge('A','E',1);
        graph.addEdge('E','F',1);

        graph.passInDeep(2);


        GraphFunction.Tarina tarina = new GraphFunction.Tarina();

        tarina.addVertex('A');
        tarina.addVertex('B');
        tarina.addVertex('C');
        tarina.addVertex('D');
        tarina.addVertex('E');
        tarina.addVertex('F');


        System.out.println("Алгоритм Тарина");

        tarina.addEdge('A','B',1);
        tarina.addEdge('B','C',1);
        tarina.addEdge('C','D',1);
        tarina.addEdge('A','E',1);
        tarina.addEdge('E','F',1);

        tarina.sort();

    }
}
