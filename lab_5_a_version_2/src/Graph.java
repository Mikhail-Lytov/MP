import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {

    public int maxN = 6;
    public int[][] mas;
    public Vertex[] vertexList;
    public char[] listCur;
    public int curN;
    public MyStack myStack = new MyStack();

    public Graph(){
        initelizetion();
    }
    public Graph(int maxN){
        this.maxN = maxN;
        myStack = new MyStack(maxN);
        initelizetion();

    }
    private void initelizetion(){
        vertexList = new Vertex[maxN];
        mas =   new int[maxN][maxN];
        curN = 0;
        listCur = new char[maxN];
        for(int i = 0; i < maxN;i++){
            Arrays.fill(mas[i], 0);
        }
    }
    public void addVertex(char name){
        listCur[curN] = name;
        vertexList[curN++] = new Vertex(name);
    }
    public void addEdge(char name_start,char name_finish, int len ){
        int start = Arrays.binarySearch(listCur,name_start);
        int finish = Arrays.binarySearch(listCur,name_finish);
        mas[start][finish] = len;

    }
    public int check(int v){
        for(int i = 0; i < curN;i++){
            if(mas[v][i] > 0 && vertexList[i].isVisible == false){
                return i;
            }
        }
        return -1;
    }
    public void deleteEdge(int start, int finish){
        mas[start][finish] = 0;
    }
    public List<Integer> passInDeep(int index){
        List<Integer> arr = new ArrayList<>();
        //System.out.println(vertexList[index].getName());
        vertexList[index].isVisible = true;
        arr.add(index);
        myStack.push(index);


        while (!myStack.isEmpty()){
            int neigh = check(myStack.peek());

            if(neigh == -1){
                neigh = myStack.pop();
            }
            else {
               // System.out.println(vertexList[neigh].getName());
                vertexList[neigh].isVisible = true;
                arr.add(neigh);
                myStack.push(neigh);
            }
        }
        for (int i = 0; i < curN; i++){
            vertexList[i].isVisible = false;
        }
        return arr;

    }



    public class Vertex {
        private char name;
        private boolean isVisible;
        private int vertexNumber;

        public Vertex(char name) {
            this.name = name;
            this.isVisible = false;
            this.vertexNumber = -1;
        }

        public Vertex() {
        }

        public char getName() {
            return name;
        }

        public void setName(char name) {
            this.name = name;
        }

        public boolean isVisible() {
            return isVisible;
        }

        public void setVisible(boolean visible) {
            isVisible = visible;
        }

        public int getVertexNumber() {
            return vertexNumber;
        }

        public void setVertexNumber(int vertexNumber) {
            this.vertexNumber = vertexNumber;
        }

        public void copy(Vertex original){
            name = original.getName();
            isVisible = original.isVisible();
        }

    }
}
