public class GraphFunction  {

    public static class Tarina extends Graph {
        private Vertex[] white;
        private Vertex[] grew;
        private Vertex[] black;

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


                if(neigh == -1){
                    neigh = myStack.pop();
                    black[size].setName(white[index].getName());
                    black[size++].setVisible(true);
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


}
