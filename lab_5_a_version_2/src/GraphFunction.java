import java.util.*;

public class GraphFunction {

    public static class PassInDeep extends Graph {
        PassInDeep(int index) {
            super();
            passInDeep(index);
        }

        PassInDeep(int index, int sizeGraph) {
            super(sizeGraph);
            //passInDeep_(index);
        }

        public void passInDeep_(int index) {
            System.out.println(vertexList[index].getName());
            vertexList[index].setVisible(true);
            myStack.push(index);

            while (!myStack.isEmpty()) {
                int neigh = check(myStack.peek());

                if (neigh == -1) {
                    neigh = myStack.pop();
                } else {
                    System.out.println(vertexList[neigh].getName());
                    vertexList[neigh].setVisible(true);
                    myStack.push(neigh);
                }
            }
            for (int i = 0; i < curN; i++) {
                vertexList[i].setVisible(true);
            }

        }
    }


    public static class BFC extends Graph{
        BFC(int index){
            super();
        }
        BFC(int index, int size){
            super(size);

        }
        public void bfc(int index){
            System.out.println(vertexList[index].getName());
            vertexList[index].setVisible(true);
            myStack.push(index);

            while (!myStack.isEmpty()){
                int neigh = check(myStack.peek());
                int root = myStack.peek();

                if(neigh == -1){
                    neigh = myStack.pop();
                }else {
                    //int root = neigh;
                    while (neigh != -1) {
                        System.out.println(vertexList[neigh].getName());
                        vertexList[neigh].setVisible(true);
                        if(neigh != root) {
                            myStack.push(neigh);
                        }
                        neigh = check(root);
                    }
                }
            }
        }
    }

    public static class Dijkstra extends  Graph{

        Dijkstra(){
            super();
        }
        Dijkstra(int sizeGraph){
            super(sizeGraph);
        }

        public void dijkstra(char start, char finish) {
            int[] route = new int[maxN];
            int start_int = Arrays.binarySearch(listCur, start);
            int finish_int = Arrays.binarySearch(listCur, finish);

            String path = String.valueOf(listCur[start_int]);

            String element = "";

            vertexList[start_int].setVisible(true);
            myStack.push(start_int);
            MyStack list = new MyStack(maxN * maxN);


            int left = 0;
            int right = 0;
            int root = 0;
            int neigh = root;
            while (neigh != finish_int) {
                root = neigh;
                neigh = check(root);

                if (neigh == -1) {
                    myStack.pop();
                } else {
                    while (neigh != -1) {
                        left += mas[root][neigh];
                        element = String.valueOf(listCur[neigh]);

                        list.push(neigh);
                        vertexList[neigh].setVisible(true);

                        if ((neigh = check(root)) != -1) {
                            right += mas[root][neigh];
                            vertexList[neigh].setVisible(true);
                            if (right < left) {
                                element = String.valueOf(listCur[neigh]);
                                path += "->" + element;
                                list.pop();
                                list.push(neigh);
                                left = right;
                            }
                        } else {
                            path += "->" + element;
                            right = left;
                        }
                        neigh = check(root);
                    }
                }
                if(!list.isEmpty()) {
                    neigh = list.pop();
                    for (int i = 0; !list.isEmpty(); i++) {
                        vertexList[list.pop()].setVisible(false);
                    }
                }
            }
            System.out.println(path + "   size: " + left);


        }
    }

    public static class Prima extends  Graph{
        private int sum = 0;
        Prima(){
            super();
        }
        Prima(int SizeGraph){
            super(SizeGraph);
        }


        public void minPath(int start){
            int[] arr = new int[maxN];
            arr = comparison(start);
            System.out.print(super.listCur[arr[0]]);
            for(int i = 0; i < arr.length - 1;i++){
                System.out.print("->" + listCur[arr[i+1]]);
            }
            System.out.println(" длина = " + sum );
        }
        /*Алгоритм Прима
        Мы создаем отдельную функцию, куда всегда переходит один стартовый элемент
                ( то что у нас написано)
        Он возвращает минимум из тех что есть
        Потом отправляется следующее ребро и оно возражает минимум
        Если этот минимум меньше, то его берем за лучший исход,
        После прогонки ставим этот минимум элемент как пройденым и добавляем в массив, вот*/

        private  int[] comparison(int start){ //тут хранится элементы, которые уже точно в списке
            //потом через  цикл мы отправляем их в функцию, чтобы найти минимальный путь до какой-нибудь вершины
            int[] arr = new int[maxN];
            int size = 0; // количество элементов в строке
            int minFromRoot = 1000000;
            ModelStep step ;
            int sheet = 0;
            int root = -1;

            Arrays.fill(arr,-1);
            vertexList[size].setVisible(true);
            arr[size++] = start;


            while (size < arr.length){
                for (int i = 0; i < size; i++){
                    step = bypass(arr[i]);
                    if(step.getLen() < minFromRoot){
                        minFromRoot = step.getLen();
                        sheet = step.getSheet();
                        root = i;
                    }
                    for (int j = 0; j < vertexList.length; j++) {
                        if (!check_arr(arr, j)) {
                            vertexList[j].setVisible(false);
                        }
                    }
                }
                arr[size++] = sheet;
                sum += minFromRoot;
                vertexList[sheet].setVisible(true);
                minFromRoot = 10000;
                System.out.println(listCur[arr[root]] + "->" + listCur[arr[size-1]] );
            }
            return arr;

        }
        private ModelStep bypass(int root){
            int neigh = -2;
            ModelStep step = new ModelStep(-1,10000);
            while ((neigh = check(root)) != -1){
                vertexList[neigh].setVisible(true);
                if(mas[root][neigh] < step.getLen()){
                    step.setLen(mas[root][neigh]);
                    step.setSheet(neigh);
                }
            }
            return step;
        }
        private boolean check_arr(int[] arr, int value){
            for(int element : arr){
                if(element == value){
                    return true;
                }
            }
            return false;
        }
    }

    public static class Kraskala extends Graph{
        private Edge[] edges;

        Kraskala(){
            super();
        }
        Kraskala(int sizeGraph){
            super(sizeGraph);
        }



        public void kraskala() {
            List<Edge> edges = new ArrayList<>();
            int[] vertex = new int[maxN];
            Edge[] arrEdge = new Edge[maxN - 1];
            int sizeEdge = -1;
            Arrays.fill(arrEdge,new Edge());
            Arrays.fill(vertex,-1);
            int size = 0;
            int sizeEdgesList = 0;
            for(int i = 0; i < maxN; i++){
                for(int j = size; j < maxN; j++){
                    if(mas[i][j] != 0){
                        Edge edge = new Edge(i,j,mas[i][j]);
                        edges.add(edge);
                    }
                }
                size++;
            }
            size = -1;
            Collections.sort(edges);
            Graph graph = new Graph(maxN);
            for(int i = 0; i <listCur.length; i++){
                graph.addVertex(listCur[i]);
            }
            while (sizeEdge < maxN - 2){
                Edge edge = edges.get(sizeEdgesList++);
                boolean flag_start = check_arr(vertex,edge.start);
                boolean flag_finish = check_arr(vertex,edge.finish);
                if(!flag_start && !flag_finish){
                    vertex[++size] = edge.start;
                    vertex[++size] = edge.finish;
                    arrEdge[++sizeEdge] = edge;
                    edges.remove(--sizeEdgesList);
                    boolean flag = false;
                    int descendant = 0;
                    while (!flag && descendant < edges.size()){
                        Edge edgeDescendant = edges.get(descendant++);
                        if(edgeDescendant.start == edge.start || edgeDescendant.start == edge.finish ||
                                edgeDescendant.finish == edge.finish || edgeDescendant.finish == edge.start){
                            arrEdge[++sizeEdge] = edgeDescendant;
                            flag = true;
                            if(!check_arr(vertex, edgeDescendant.finish)) {
                                vertex[++size] = edgeDescendant.finish;
                            }
                            if(!check_arr(vertex, edgeDescendant.start)) {
                                vertex[++size] = edgeDescendant.start;
                            }
                            edges.remove(--descendant);
                        }

                    }
                }else if(!flag_start && flag_finish){
                    vertex[++size] = edge.start;
                    arrEdge[++sizeEdge] = edge;
                    edges.remove(--sizeEdgesList);
                } else if (flag_start && !flag_finish) {
                    vertex[++size] = edge.finish;
                    arrEdge[++sizeEdge] = edge;
                    edges.remove(--sizeEdgesList);
                }
            }
            this.edges = arrEdge;
        }

        @Override
        public String toString() {
            String line = "";
            int sum = 0;
            for(int i = 0; i < edges.length; i++){
                line += listCur[edges[i].start] + "->" + listCur[edges[i].finish] + " len=" + edges[i].len + "\n";
                sum += edges[i].len;
            }
            line += "sum_len = " + sum;
            return line;
        }

        private Edge bridge(Graph graph, List<Edge> edges, int sizeEdges){
            boolean flag= false;
            while (!flag){
                Edge edge = edges.get(sizeEdges++);
                graph.addEdge(listCur[edge.start],listCur[edge.finish],edge.len);
                graph.addEdge(listCur[edge.finish],listCur[edge.start],edge.len);

                if(graph.passInDeep(0).size() == maxN){
                    return edge;
                }else {
                    graph.deleteEdge(edge.start,edge.finish);
                    graph.deleteEdge(edge.finish,edge.start);
                }
            }
            return new Edge();
        }

        private boolean check_arr(int[] arr, int value){
            for(int element : arr){
                if(element == value){
                    return true;
                }
            }
            return false;
        }


        private class Edge implements Comparable<Edge> {
            private int start;
            private int finish;
            private int len;

            public int getStart() {
                return start;
            }

            public void setStart(int start) {
                this.start = start;
            }

            public int getFinish() {
                return finish;
            }

            public void setFinish(int finish) {
                this.finish = finish;
            }

            public int getLen() {
                return len;
            }

            public void setLen(int len) {
                this.len = len;
            }

            public Edge(){
                this.start = -1;
                this.finish = -1;
                this.len = -1;
            }
            public Edge(int start, int finish, int len) {
                this.start = start;
                this.finish = finish;
                this.len = len;
            }

            @Override
            public int compareTo(Edge o) {
                return this.len - o.len;
            }
        }
    }
}

