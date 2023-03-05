import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Graph extends GraphRealization {
    private int[][] matrix ={
            {0,3,2,1000000},
            {1000000,0,6,-10},
            {1000000,8,0,1000000},
            {1000000,1,9,0}
    };
    private Graph graph;


    public void Floyd_Warshell() {
        matrix = super.Floyd_Warshell(matrix);
    }

    public void DFC(){
        SearchInDepth graph = new SearchInDepth();
        graph.addVertex('A'); //0
        graph.addVertex('B'); //1
        graph.addVertex('C'); //2
        graph.addVertex('D'); //3
        graph.addVertex('E'); //4
        graph.addVertex('F'); //5
        graph.addVertex('G'); //6

        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,3);
        graph.addEdge(1,4);
        graph.addEdge(3,5);
        graph.addEdge(5,6);

        System.out.println("Visits: ");
        graph.dfs();
    }

    public void Dekstra(){
        Dekstra graph = new Dekstra();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');
        graph.addVertex('G');

        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 2, 5);
        graph.addEdge(0, 3, 7);
        graph.addEdge(1, 4, 6);
        graph.addEdge(2, 4, 4);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 5, 3);
        graph.addEdge(4, 6, 6);
        graph.addEdge(5, 6, 4);

        System.out.println("Элементы имеют кратчайшие пути из точки A: ");
        graph.path();
        graph.clean();
    }
    public void BFC(){
        SearchInWidth graph = new SearchInWidth();
        graph.addVertex('A'); //0
        graph.addVertex('B'); //1
        graph.addVertex('C'); //2
        graph.addVertex('D'); //3
        graph.addVertex('E'); //4
        graph.addVertex('F'); //5
        graph.addVertex('G'); //6

        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,3);
        graph.addEdge(1,4);
        graph.addEdge(3,5);
        graph.addEdge(5,6);

        System.out.println("Visits: ");
        graph.bfc();
    }

    public void Kruskal(){
        // тройка (u, v, w) представляет собой неориентированное ребро из
        // вершина `u` в вершину `v` с весом `w`
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 7), new Edge(1, 2, 8), new Edge(0, 3, 5),
                new Edge(1, 3, 9), new Edge(1, 4, 7), new Edge(2, 4, 5),
                new Edge(3, 4, 15), new Edge(3, 5, 6), new Edge(4, 5, 8),
                new Edge(4, 6, 9), new Edge(5, 6, 11));

        // общее количество узлов в Graph (от 0 до 6)
        int n = 7;

        // построить Graph
        List<Edge> e = DisjointSet.runKruskalAlgorithm(edges, n);
        System.out.println(e);
    }
    public void Prims(){
        Prims p = new Prims();

        int num_nodes = 6; // Nodes (0, 1, 2, 3, 4, 5)

        List<List<NodeCost>> graph_1 = new ArrayList<>(num_nodes);
        for (int i=0; i < num_nodes; i++) {
            graph_1.add(new ArrayList<>());
        }

        // Node 0
        Collections.addAll(graph_1.get(0), new NodeCost(1, 4), new NodeCost(2, 1), new NodeCost(3, 5));
        // Node 1
        Collections.addAll(graph_1.get(1), new NodeCost(0, 4), new NodeCost(3, 2), new NodeCost(4, 3),
                new NodeCost(5, 3));
        // Node 2
        Collections.addAll(graph_1.get(2), new NodeCost(0, 1), new NodeCost(3, 2), new NodeCost(4, 8));
        // Node 3
        Collections.addAll(graph_1.get(3), new NodeCost(0, 5), new NodeCost(1, 2), new NodeCost(2, 2),
                new NodeCost(4, 1));
        // Node 4
        Collections.addAll(graph_1.get(4), new NodeCost(1, 3), new NodeCost(2, 8), new NodeCost(3, 1),
                new NodeCost(5, 4));
        // Nod 5
        Collections.addAll(graph_1.get(5), new NodeCost(1, 3), new NodeCost(4, 4));

        // Начните добавлять узлы в минимальное связующее дерево с 0 в качестве исходного узла
        System.out.println("Стоимость минимального связующего дерева на графике 1 : " + p.Find_MST(0, graph_1));

        // Исходящие ребра из узла:<стоимость, смежный узел> на графике 2.
        num_nodes = 7; // Nodes (0, 1, 2, 3, 4, 5, 6)

        List<List<NodeCost>> graph_2 = new ArrayList<>(num_nodes);
        for (int i=0; i < num_nodes; i++) {
            graph_2.add(new ArrayList<>());
        }

        // Node 0
        Collections.addAll(graph_2.get(0), new NodeCost(1, 1), new NodeCost(2, 2), new NodeCost(3, 1),
                new NodeCost(4, 1), new NodeCost(5, 2), new NodeCost(6, 1));
        // Node 1
        Collections.addAll(graph_2.get(1), new NodeCost(0, 1), new NodeCost(2, 2), new NodeCost(6, 2));
        // Node 2
        Collections.addAll(graph_2.get(2), new NodeCost(0, 2), new NodeCost(1, 2), new NodeCost(3, 1));
        // Node 3
        Collections.addAll(graph_2.get(3), new NodeCost(0, 1), new NodeCost(2, 1), new NodeCost(4, 2));
        // Node 4
        Collections.addAll(graph_2.get(4), new NodeCost(0, 1), new NodeCost(3, 2), new NodeCost(5, 2));
        // Node 5
        Collections.addAll(graph_2.get(5), new NodeCost(0, 2), new NodeCost(4, 2), new NodeCost(6, 1));
        // Node 6
        Collections.addAll(graph_2.get(6), new NodeCost(0, 1), new NodeCost(1, 2), new NodeCost(5, 1));


        // Начните добавлять узлы в минимальное связующее дерево с 0 в качестве исходного узла
        System.out.println("Стоимость минимального связующего дерева на графике 2 : " + p.Find_MST(0, graph_2));
    }


    @Override
    public String toString() {
        String line = "";
        for (int i = 0; i < matrix.length; i++){
            line += Arrays.toString(matrix[i]) +"\n";
        }
        return line;
    }
}
