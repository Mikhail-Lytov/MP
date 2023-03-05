import java.util.*;


// Класс для хранения ребра Graph
class Edge
{
    int src, dest, weight;

    public Edge(int src, int dest, int weight)
    {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + src + ", " + dest + ", " + weight + ")";
    }
}

// Класс для представления непересекающегося множества
class DisjointSet {
    Map<Integer, Integer> parent = new HashMap<>();

    // выполняем операцию MakeSet
    public void makeSet(int n) {
        // создаем `n` непересекающихся наборов (по одному на каждую вершину)
        for (int i = 0; i < n; i++) {
            parent.put(i, i);
        }
    }

    // Находим корень множества, которому принадлежит элемент `k`
    private int find(int k) {
        // если `k` это корень
        if (parent.get(k) == k) {
            return k;
        }

        // повторяем для родителя, пока не найдем корень
        return find(parent.get(k));
    }

    // Выполняем объединение двух подмножеств
    private void union(int a, int b) {
        // находим корень множеств, в которые входят элементы `x` и `y`
        int x = find(a);
        int y = find(b);

        parent.put(x, y);
    }

    // Функция построения MST с использованием алгоритма Крускала
    public static List<Edge> runKruskalAlgorithm(List<Edge> edges, int n) {
        // сохраняет ребра, присутствующие в MST
        List<Edge> MST = new ArrayList<>();

        // Инициализировать класс `DisjointSet`.
        // создаем набор синглетонов для каждого элемента юниверса.
        DisjointSet ds = new DisjointSet();
        ds.makeSet(n);

        int index = 0;

        // сортируем ребра по возрастанию веса
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));

        // MST содержит ровно `V-1` ребер
        while (MST.size() != n - 1) {
            // рассмотрим следующее ребро с минимальным весом из Graph
            Edge next_edge = edges.get(index++);

            // находим корень множеств, к которым две конечные точки
            // вершины следующего ребра принадлежат
            int x = ds.find(next_edge.src);
            int y = ds.find(next_edge.dest);

            // если обе конечные точки имеют разных родителей, они принадлежат
            // различные связанные компоненты и могут быть включены в MST
            if (x != y) {
                MST.add(next_edge);
                ds.union(x, y);
            }
        }

        return MST;
    }
}