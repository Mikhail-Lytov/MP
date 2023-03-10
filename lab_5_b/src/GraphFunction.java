import java.util.Arrays;

public class GraphFunction  {

    public static class Tarina extends Graph {
        private Vertex[] white;
        private Vertex[] grew;
        private Vertex[] black;

        public Vertex[] getBlack() {
            return black;
        }

        private int size = 0;

        public Tarina(int maxN){
            super(maxN);
            ininelizationTarina();
        }
        public Tarina(){
            super();
            ininelizationTarina();
        }
        private void ininelizationTarina(){
            white = vertexList;
            grew = new Vertex[maxN];
            black = new Vertex[maxN];
        }

// Берем первый элемент массива
        // помещаем его в black и что мы там были
        // смещеаемся на потомка, потомок grew

        public void sort(){
            for(int i = 0; i < white.length; i++){
                grew[i] = new Vertex(white[i].getName());
                black[i] = new Vertex(white[i].getName());
            }
            sortTarinas(0);
        }
        private void sortTarinas(int index) {
            white[index].setVisible(true);
            myStack.push(index);
            grew[index].setVisible(true);
            grew[index].setName(white[index].getName());

            int neigh = index;
            while (!myStack.isEmpty()){
                index = neigh;
                neigh = check(myStack.peek());


                if(neigh == -1 ){

                    neigh = myStack.pop();
                    if(size < white.length) {
                        black[size].setName(white[index].getName());
                        black[size++].setVisible(true);
                    }
                }else{
                    if(grew[neigh].isVisible() == true){
                        neigh = myStack.pop();
                        black[size].setName(white[neigh].getName());
                        black[size++].setVisible(true);

                    }else if(grew[neigh].isVisible() != true){
                        grew[neigh].setVisible(true);
                        white[neigh].setVisible(true);
                        myStack.push(neigh);
                    }
                }

            }
            for (int i =0; i < black.length; i++){
                System.out.println(black[i].getName() + " ");
            }

        }
    }

    public static class Fleury extends Tarina{
        private int[] list_transition = new int[maxN];
        private int[] listWillCome = new int[maxN - 1];
        public Fleury(){
            super();
        }
        public void printEulerCycle(){
            super.sort();

            Vertex[] black = super.getBlack();
            Vertex[] vertices = new Vertex[super.getBlack().length];
            for(int i = 0; i <vertices.length; i++){
                vertices[i] = new Vertex(black[black.length - i - 1].getName());
            }
            int size_transition = 1;
            int size_listWillCome = listWillCome.length;
            list_transition[0] = Arrays.binarySearch(listCur, vertices[0].getName());
            for(int i = 1; i < vertices.length; i++){
                listWillCome[i-1] = Arrays.binarySearch(listCur, vertices[i].getName());
            }
            System.out.println(Arrays.toString(listWillCome));
            while (size_listWillCome > 0) {
                int size = 0;
                for (int i = 0; i < size_transition; i++) {
                    for (int j = 0; j < size_listWillCome; j++) {
                        if (mas[list_transition[i]][listWillCome[j]] != 0) {
                            size++;
                        }
                    }
                }
                if (size < 2) { // size < 2
                    System.out.println(listCur[list_transition[size_transition - 1]] + "->" + listCur[listWillCome[0]] + "-Это мост");
                }
                list_transition[size_transition++] = listWillCome[0];
                listWillCome = Arrays.copyOfRange(listWillCome, 1, listWillCome.length);
                size_listWillCome--;
            }


        }
    }

    public static class Kosaraju extends Graph {

        Kosaraju() {
            super();
        }

        Kosaraju(int size) {
            super(size);
        }

        public void passInDeep(int index) {
            int number = 0;
            System.out.println(vertexList[index].getName());
            vertexList[index].setVisible(true);
            vertexList[index].setVertexNumber(number++);
            myStack.push(index);

            while (!myStack.isEmpty()) {
                int neigh = check(myStack.peek());

                if (neigh == -1) {
                    neigh = myStack.pop();
                    vertexList[neigh].setVertexNumber(number++);

                } else {
                    System.out.println(vertexList[neigh].getName());
                    vertexList[neigh].setVisible(true);
                    vertexList[neigh].setVertexNumber(number++);
                    myStack.push(neigh);
                }
            }
        }


        public void invertion() {
            Vertex[] vertex_invertion = sort(vertexList);
            Graph graph = new Graph(maxN);
            for (int i = 0; i < listCur.length; i++){
                graph.addVertex(listCur[i]);
            }
            for(int i = 0; i < maxN; i++){
                for (int j = 0; j < maxN; j++){
                    if(mas[i][j] > 0){
                        graph.mas[j][i] = mas[i][j];
                    }
                }
            }
            for (int i = 0; i < vertex_invertion.length; i++){
                searchEuler(graph, i);
                System.out.println();
                //тут что-то лругое
            }
        }

        private void searchEuler(Graph graph, int index){
            int[] listEuler = new int[1];
            MyStack myStack1 = new MyStack(maxN * maxN);
            listEuler[0] = index;
            graph.vertexList[index].setVisible(true);
            myStack1.push(index);
            boolean flag = false;
            while (true){
                int neigh = graph.check(myStack1.peek());

                if(neigh == -1){
                    if(myStack1.peek() == listEuler[0] && flag){
                        printCycle(Arrays.copyOf(listEuler, listEuler.length - 1));
                        break;
                    }else if((neigh = graph.check(myStack1.pop())) != -1){
                        graph.vertexList[myStack1.peek()].setVisible(false);
                        listEuler = Arrays.copyOf(listEuler, listEuler.length - 1);
                    }else break;
                }else {
                    //добавляем в стек для вывода
                    flag = true;
                    listEuler = Arrays.copyOf(listEuler, listEuler.length + 1);
                    listEuler[listEuler.length - 1] = neigh;
                    graph.vertexList[neigh].setVisible(true);
                    myStack1.push(neigh);
                }
            }
            for (int i = 0; i < curN; i++){
                graph.vertexList[i].setVisible(false);
            }
        }
        private void printCycle(int[] arr){
            System.out.println("Алгоритм Косарайю");
            for(int i = 0; i < arr.length; i++){
                if(i + 1 < arr.length){
                    System.out.print(listCur[arr[i]] + "->");
                }else System.out.println(listCur[arr[i]] + " -цикл Эйлера");
            }
        }

        private Vertex[] sort(Vertex[] original){
            Vertex[] arr = new Vertex[vertexList.length];
            int size = 0;
            int element_index = 0;
            Vertex element = new Vertex();
            while (size < vertexList.length ){
                for(int i = 0; i < vertexList.length; i++){
                    if(element.getVertexNumber() < original[i].getVertexNumber()){
                        element.setVertexNumber(original[i].getVertexNumber());
                        element.setName(original[i].getName());
                        element.setVisible(original[i].isVisible());
                        element_index = i;
                    }
                }
                arr[size++] = element;
                original[element_index].setVertexNumber(-1);
                element = new Vertex();
            }

            return arr;
        }

    }
}
