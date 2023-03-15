import java.util.Arrays;

public class GraphFunction {

    public static class PassInDeep extends Graph {
        PassInDeep(int index) {
            super();
            passInDeep(index);
        }

        PassInDeep(int index, int sizeGraph) {
            super(sizeGraph);
            passInDeep(index);
        }

        public void passInDeep(int index) {
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
        private int[] comparison(int start){
            int[] arr = new int[maxN];
            Arrays.fill(arr,-1);
            int size = 0;
            int root = start;
            int neigh = root;
            int min_len = 10000000;
            int sheet = root;
            boolean flag = false;
            vertexList[neigh].setVisible(true);
            arr[size] = neigh;
            myStack.push(neigh);
            while (!myStack.isEmpty()){
                root = sheet;
                neigh = check(root);
                if(neigh == -1){
                    root = myStack.pop();
                }
                while ((neigh = check(root)) != -1){
                    flag = true;
                    vertexList[neigh].setVisible(true);
                    if(mas[root][neigh]  < min_len) {
                        min_len = mas[root][neigh];
                        sheet = neigh;
                    }

                }
                if(flag) {
                    myStack.push(sheet);
                    sum += min_len;
                    min_len = 100000;
                    flag = false;
                    arr[++size] = sheet;
                    for (int i = 0; i < vertexList.length; i++) {
                        if (!check_arr(arr, i)) {
                            vertexList[i].setVisible(false);
                        }
                    }
                }

            }
            return arr;
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
}

