import java.util.*;

public class GraphRealization {
    protected int[][] Floyd_Warshell(int[][] arr){
        for(int k = 0; k < arr.length; ++k) {
            for (int i = 0; i < arr.length; ++i) {
                for (int j = 0; j < arr.length; j++) {
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }

            for (int i = 0; i < arr.length; ++i) {
                if (arr[i][i] < 0) {
                    System.out.println("Существует цикл отрицательного веса");
                    k = arr.length;
                }

            }
        }
        return arr;
    }

    protected void search_in_depth(boolean[][] matrix){
        boolean[] visit = new boolean[matrix.length];
        
    }
    public class SearchInDepth{
        private final int MAX_VERTS = 10;
        private Vertex vertexArray[]; //массив вершин
        private int adjMat[][]; // матрица смежности
        private int nVerts; // текущее количество вершин
        private Stack<Integer> stack;

        public SearchInDepth() { // инициализация внутрених полей
            vertexArray = new Vertex[MAX_VERTS];
            adjMat = new int[MAX_VERTS][MAX_VERTS];
            nVerts = 0;
            for (int j = 0; j < MAX_VERTS; j++) {
                for (int k = 0; k < MAX_VERTS; k++) {
                    adjMat[j][k] = 0;
                }
            }
            stack = new Stack<>();
        }

        public void addVertex(char lab) {
            vertexArray[nVerts++] = new Vertex(lab);
        }

        public void addEdge(int start, int end) {
            adjMat[start][end] = 1;
            adjMat[end][start] = 1;
        }

        public void displayVertex(int v) {
            System.out.println(vertexArray[v].getLabel());
        }

        public void dfs() { // обход в глубину
            vertexArray[0].setWasVisited(true); // берётся первая вершина
            displayVertex(0);
            stack.push(0);

            while (!stack.empty()) {
                int v = getAdjUnvisitedVertex(stack.peek()); // вынуть индекс смежной веришины, еckи есть 1, нету -1
                if (v == -1) { // если непройденных смежных вершин нету
                    stack.pop(); // элемент извлекается из стека
                }
                else {
                    vertexArray[v].setWasVisited(true);
                    displayVertex(v);
                    stack.push(v); // элемент попадает на вершину стека
                }
            }

            for (int j = 0; j < nVerts; j++) {  // сброс флагов
                vertexArray[j].wasVisited = false;
            }

        }

        private int getAdjUnvisitedVertex(int v) {
            for (int j = 0; j < nVerts; j++) {
                if (adjMat[v][j] == 1 && vertexArray[j].wasVisited == false) {
                    return j; //возвращает первую найденную вершину
                }
            }
            return -1;
        }

    }

    class SearchInWidth{
        private final int MAX_VERTS = 10;
        private Vertex vertexList[]; //массив вершин
        private int adjMat[][]; // матрица смежности
        private int nVerts; // текущее количество вершин
        private Queue<Integer> queue;

        public SearchInWidth() {
            vertexList = new Vertex[MAX_VERTS];
            adjMat = new int[MAX_VERTS][MAX_VERTS];
            nVerts = 0;
            for (int j = 0; j < MAX_VERTS; j++) {
                for (int k = 0; k < MAX_VERTS; k++) {  // заполнение матрицы смежности нулями
                    adjMat[j][k] = 0;
                }
            }
            queue = new PriorityQueue<>();
        }
        public void addVertex(char lab) {
            vertexList[nVerts++] = new Vertex(lab);
        }

        public void addEdge(int start, int end) {
            adjMat[start][end] = 1;
            adjMat[end][start] = 1;
        }

        public void displayVertex(int v) {
            System.out.println(vertexList[v].getLabel());
        }

        public void bfc() { // обход в глубину
            vertexList[0].setWasVisited(true);
            displayVertex(0);
            queue.add(0);
            int v2;

            while (!queue.isEmpty()) {
                int v = queue.remove();

                while((v2 = getAdjUnvisitedVertex(v))!=-1) {// цикл будет работать, пока все смежные вершины не будут найденны, и не будут добавлены в очередь
                    vertexList[v2].wasVisited =true;
                    displayVertex(v2);
                    queue.add(v2);
                }
            }

            for (int j = 0; j < nVerts; j++) {  // сброс флагов
                vertexList[j].wasVisited = false;
            }

        }

        private int getAdjUnvisitedVertex(int v) {
            for (int j = 0; j < nVerts; j++) {
                if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false) {
                    return j; //возвращает первую найденную вершину
                }
            }
            return -1;
        }

    }
    public class Dekstra {
        private final int MAX_VERTS = 10;// максимальное количество вершин
        private final int INFINITY = 100000000; // это число у нас будет служить в качестве "бесконечности"
        private VertexInDekstra vertexList[]; // список вершин
        private int relationMatrix[][]; // матрица связей вершин
        private int countOfVertices; // текущее количество вершин
        private int countOfVertexInTree; // количество рассмотренных вершин в дереве
        private List<Path> shortestPaths; // список данных кратчайших путей
        private int currentVertex; // текущая вершина
        private int startToCurrent; //расстояние до currentVertex

        public Dekstra() {
            vertexList = new VertexInDekstra[MAX_VERTS]; // матрица смежности
            relationMatrix = new int[MAX_VERTS][MAX_VERTS];
            countOfVertices = 0;
            countOfVertexInTree = 0;
            for (int i = 0; i < MAX_VERTS; i++) {// матрица смежности заполняется
                for (int k = 0; k < MAX_VERTS; k++) { // бесконечными расстояниями
                    relationMatrix[i][k] = INFINITY; // задания значений по умолчанию
                    shortestPaths = new ArrayList<>();// задается пустым
                }
            }
        }

        public void addVertex(char lab) {// задание новых вершин
            vertexList[countOfVertices++] = new VertexInDekstra(lab);
        }

        public void addEdge(int start, int end, int weight) {
            relationMatrix[start][end] = weight; // задание ребер между вершинами, с весом между ними
        }

        public void path() { // выбор кратчайшего пути
            //  задание данных для стартовой вершины
            int startTree = 0; // стартуем с вершины 0
            vertexList[startTree].setInTree(true); // включение в состав дерева первого элемента
            countOfVertexInTree = 1;

            // заполнение коротких путей для вершин смежных с стартовой
            for (int i = 0; i < countOfVertices; i++) {
                int tempDist = relationMatrix[startTree][i];
                Path path = new Path(tempDist);
                path.getParentVertices().add(0);// первым родительским элементом, будет всегда стартовая вершина
                shortestPaths.add(path);
            }
            // пока все вершины не окажутся в дереве
            while (countOfVertexInTree < countOfVertices) { // выполняем, пока количество вершин в дереве не сравняется с общим количеством вершин
                int indexMin = getMin();//получаем индекс вершины с наименшей дистанцией, из вершин еще не входящих в дерево
                int minDist = shortestPaths.get(indexMin).getDistance();// минимальная дистанция вершины, из тек которые ещё не в дереве

                if (minDist == INFINITY) {
                    System.out.println("В графе пристувствуют недостижимые вершины");
                    break;// в случае если остались непройденными только недостижимые вершины, мы выходим из цикла
                } else {
                    currentVertex = indexMin; // переводим указатель currentVert к текущей вершине
                    startToCurrent = shortestPaths.get(indexMin).getDistance();// задаем дистанцию к текущей вершине
                }

                vertexList[currentVertex].setInTree(true);  //включение текущей вершины в дерево
                countOfVertexInTree++; // увеличиваем счетчик вершин в дереве
                updateShortestPaths(); // обновление списка кратчайших путей
            }

            displayPaths(); // выводим в консоль результаты
        }

        public void clean() { // очиска дерева
            countOfVertexInTree = 0;
            for (int i = 0; i < countOfVertices; i++) {
                vertexList[i].setInTree(false);
            }
        }

        private int getMin() {
            int minDist = INFINITY; // за точку старта взята "бесконечная" длина
            int indexMin = 0;
            for (int i = 1; i < countOfVertices; i++) {// для каждой вершины
                if (!vertexList[i].isInTree() && shortestPaths.get(i).getDistance() < minDist) { // если вершина ещё не ве дереве и её растояние меньше старого минимума
                    minDist = shortestPaths.get(i).getDistance(); // задаётся новый минимум
                    indexMin = i; // обновление индекса вершины содержащую минимаьную дистанцию
                }
            }
            return indexMin; //возвращает индекс вершины с наименшей дистанцией, из вершин еще не входящих в дерево
        }

        private void updateShortestPaths() {
            int vertexIndex = 1; // стартовая вершина пропускается
            while (vertexIndex < countOfVertices) { // перебор столбцов

                if (vertexList[vertexIndex].isInTree()) { // если вершина column уже включена в дерево, она пропускается
                    vertexIndex++;
                    continue;
                }
                // вычисление расстояния для одного элемента sPath
                // получение ребра от currentVert к column
                int currentToFringe = relationMatrix[currentVertex][vertexIndex];
                // суммирование всех расстояний
                int startToFringe = startToCurrent + currentToFringe;
                // определение расстояния текущего элемента vertexIndex
                int shortPathDistance = shortestPaths.get(vertexIndex).getDistance();

                // сравнение расстояния через currentVertex с текущим расстоянием в вершине с индексом vertexIndex
                if (startToFringe < shortPathDistance) {// если меньше, то у вершины под индексом vertexIndex будет задан новый кратчайший путь
                    List<Integer> newParents = new ArrayList<>(shortestPaths.get(currentVertex).getParentVertices());//создаём копию списка родителей вершины currentVert
                    newParents.add(currentVertex);// задаём в него и currentVertex как предыдущий
                    shortestPaths.get(vertexIndex).setParentVertices(newParents); // соохраняем новый маршут
                    shortestPaths.get(vertexIndex).setDistance(startToFringe); // соохраняем новую дистанцию
                }
                vertexIndex++;
            }
        }

        private void displayPaths() { // метод для вывода кратчайших путей на экран
            for (int i = 0; i < countOfVertices; i++) {
                System.out.print(vertexList[i].getLabel() + " = ");
                if (shortestPaths.get(i).getDistance() == INFINITY) {
                    System.out.println("0");
                } else {
                    String result = shortestPaths.get(i).getDistance() + " (";
                    List<Integer> parents = shortestPaths.get(i).getParentVertices();
                    for (int j = 0; j < parents.size(); j++) {
                        result += vertexList[parents.get(j)].getLabel() + " -> ";
                    }
                    System.out.println(result + vertexList[i].getLabel() + ")");
                }
            }
        }
    }
    private  class VertexInDekstra {
        private char label;
        private boolean isInTree;

        public VertexInDekstra(char label) {
            this.label = label;
            this.isInTree = false;
        }

        public char getLabel() {
            return label;
        }

        public void setLabel(char label) {
            this.label = label;
        }

        public boolean isInTree() {
            return isInTree;
        }

        public void setInTree(boolean inTree) {
            isInTree = inTree;
        }
    }

    private class Path { // объект данного класса содержащий расстояние и предыдущие и пройденные вершины
        private int distance; // текущая дистанция от начальной вершины
        private List<Integer> parentVertices; // текущий родитель вершины

        public Path(int distance) {
            this.distance = distance;
            this.parentVertices = new ArrayList<>();
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public List<Integer> getParentVertices() {
            return parentVertices;
        }

        public void setParentVertices(List<Integer> parentVertices) {
            this.parentVertices = parentVertices;
        }
    }
    private class Vertex {
        private char label;  // метка А например
        public boolean wasVisited;

        public Vertex(final char label) {
            this.label = label;
            wasVisited = false;
        }

        public char getLabel() {
            return this.label;
        }

        public boolean isWasVisited() {
            return this.wasVisited;
        }

        public void setWasVisited(final boolean wasVisited) {
            this.wasVisited = wasVisited;
        }
    }


}
