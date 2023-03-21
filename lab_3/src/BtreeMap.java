import java.util.Arrays;
import java.util.Date;

public class BtreeMap<I,K extends Comparable>{
    private int size;
    private Node<I,K> root;

    private BtreeMap(){

    }
    BtreeMap(int size) throws SizeError{
        if(size <= 2){
            throw new SizeError("Размер меньше трех");
        }else {
            this.size = size;
            this.root = new Node<>(this.size);
        }

    }

    public void insert(K key,I item){
        Data data = new Data(item, key);
        //  insert_element(root,data,1);

    }
    private void addElement(Date date, Node root){
        int i = 1;
        while (true){

            if(root.descendants.arr[i] == null) {
                if (root.descendants.top == size) {
                    //перестройка
                } else if (root.descendants.top + 1 == size) {
                    //перестройка
                } else{
                    // вставить в эту ячейку
                }
            }else if(date.compareTo(root.descendants.arr[i].data) > 0){
                i++;
            } else if (date.compareTo(root.descendants.arr[i].data) < 0) {

            }
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(root.descendants.arr);
    }


    public void addElement(K key,I item){
        Data data = new Data(item, key);
        insert(root,data);
    }


    private void insert(Node root, Data data){
        for(int i = 1; i < size + 1; i++){
               if(root.descendants.arr[i] == null){
                   root.descendants.arr[i] = new Node<>(size);
                   root.descendants.arr[i].data = data;
                   break;
               }else if (root.descendants.arr[i].data.getKey().compareTo(data.key) < 0){
                        if(i+1 == size + 1 ){
                            insert(root.descendants.arr[size + 1], data);
                            break;
                        }
               }else if (root.descendants.arr[i].data.getKey().compareTo(data.key) > 0){
                   System.out.println("меньше");
                   break;
               }
        }
    }





    private class MyStack{
        Node[] nodes;
        int top = -1;
        MyStack(int size){
            nodes = new Node[size];
        }
        public void push(Node node){
            nodes[++top] = node;
        }
        public Node pull(){
            return nodes[top];
        }
        public Node clone(){
            return nodes[top--];
        }
    }
    private class Node<I,K extends Comparable>{
        Data<I,K> data = new Data<I,K>();
        Descendants<I,K> descendants ;

        @Override
        public String toString() {
            return data.key + " : " + data.item;
        }
        public Node(int size){
            descendants = new Descendants<>(size);
        }
    }

    private class Data<I,K extends Comparable> extends Date {
        private I item;
        private K key;

        public Data(I item, K key) {
            this.item = item;
            this.key = key;
        }

        public Data() {
        }

        public I getItem() {
            return item;
        }

        public void setItem(I item) {
            this.item = item;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }


        public int compareTo(Data o) {
            int result = this.key.compareTo((K) o.key);
            return 0;
        }


    }
    private class Descendants<I,K>  {
        private Node[] arr; // Node[0](ссылка на узел левый); Mode[size+1] ссылка на правый узел
        private int top = 1;
        public Descendants(int size){
            arr = new Node[size + 2];
        }
        public Node element(int i){
            return arr[i];
        }

        public int getTop() {
            return top;
        }
        public void setElement(Node node){
            arr[top++] = node;
        }

        /*
        Node<I,K> leftDescendants;
        Node<I,K> rightDescendants;
*/
    }
    // Последний элемент массива ссылается на новый узел
}
