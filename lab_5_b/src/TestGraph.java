public class TestGraph {

    public static void main(String[] args) {

   /*     Graph graph = new Graph();

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
*/

        GraphFunction.Tarina tarina = new GraphFunction.Tarina();

        tarina.addVertex('A');
        tarina.addVertex('B');
        tarina.addVertex('C');
        tarina.addVertex('D');
        tarina.addVertex('E');
        tarina.addVertex('F');


        System.out.println("Алгоритм Тарина");

        tarina.addEdge('B','A',1);
        tarina.addEdge('A','C',1);
        tarina.addEdge('A','B',1);
        tarina.addEdge('B','C',1);
        tarina.addEdge('C','A',1);
        tarina.addEdge('C','B',1);
        tarina.addEdge('C','D',1);
        tarina.addEdge('D','C',1);
        tarina.addEdge('D','E',1);
        tarina.addEdge('D','C',1);
        tarina.addEdge('D','E',1);
        tarina.addEdge('D','F',1);
        tarina.addEdge('E','D',1);
        tarina.addEdge('E','F',1);
        tarina.addEdge('F','D',1);
        tarina.addEdge('F','E',1);
        //tarina.addEdge('F','A',1);
        //tarina.addEdge('A','F',1);

        tarina.sort();

        System.out.println("Поиск мостов");

        GraphFunction.Fleury fleury = new GraphFunction.Fleury();

        fleury.addVertex('A');
        fleury.addVertex('B');
        fleury.addVertex('C');
        fleury.addVertex('D');
        fleury.addVertex('E');
        fleury.addVertex('F');


        fleury.addEdge('B','A',1);
        fleury.addEdge('A','C',1);
        fleury.addEdge('A','B',1);
        fleury.addEdge('B','C',1);
        fleury.addEdge('C','A',1);
        fleury.addEdge('C','B',1);
        fleury.addEdge('C','D',1);
        fleury.addEdge('D','C',1);
        fleury.addEdge('D','E',1);
        fleury.addEdge('D','C',1);
        fleury.addEdge('D','E',1);
        fleury.addEdge('D','F',1);
        fleury.addEdge('E','D',1);
        fleury.addEdge('E','F',1);
        fleury.addEdge('F','D',1);
        fleury.addEdge('F','E',1);
        //fleury.addEdge('F','A',1);
        //fleury.addEdge('A','F',1);

        fleury.printEulerCycle();

        GraphFunction.Kosaraju kosaraju = new GraphFunction.Kosaraju(5);

        kosaraju.addVertex('A');
        kosaraju.addVertex('B');
        kosaraju.addVertex('C');
        kosaraju.addVertex('D');
        kosaraju.addVertex('E');


        kosaraju.addEdge('A','B',1);
        kosaraju.addEdge('B','C',1);
        kosaraju.addEdge('C','D',1);
        kosaraju.addEdge('D','B',1);
        kosaraju.addEdge('A','E',1);
        kosaraju.addEdge('E','D',1);

        kosaraju.passInDeep(0);
        kosaraju.invertion();
    }
}
