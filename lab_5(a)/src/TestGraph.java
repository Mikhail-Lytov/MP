public class TestGraph {
    public static void main(String[] args) {

        System.out.println("Алгоритм Флойда-Уоршела");
        Graph graph = new Graph();
        graph.Floyd_Warshell();
        System.out.println(graph);

        System.out.println("Поиск в глубину");
        graph.DFC();

        System.out.println("Поиск в ширину");
        graph.BFC();

        System.out.println("алгоритм Дейкстра") ;
        graph.Dekstra();

        System.out.println("алгоритм Крускалач") ;
        graph.Kruskal();

        System.out.println("Примс");
        graph.Prims();
    }
}
