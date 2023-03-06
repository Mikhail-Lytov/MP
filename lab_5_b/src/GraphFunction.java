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

}
