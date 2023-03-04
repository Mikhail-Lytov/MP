import java.util.Arrays;

public class BtreeMap<I,K>  {
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
         Data data = new Data(key, item);
       //  insert_element(root,data,1);

    }

    @Override
    public String toString() {
        return Arrays.toString(root.descendants.arr);
    }

    /*private void insert_element(Node node, Data data, int i){
        System.out.println(node.descendants.arr.length);
        if(node.descendants.arr[i] == null){
             Node element = new Node(this.size);
             node.descendants.arr[i] = element;
             node.descendants.arr[i].data = data;
         }else {
             if(i + 1 < size){
                 insert_element(node, data, i+1);
             }else{
                 // пересборка дерева
             }
         }
    }
*/

    private void turn_right(Node root, int i){ // пересобрать ветку справо, а то есть когда элемент следующий больше
        Node node = new Node(this.size);
        int z = 0;
        for (int j = i; j < size + 2; j ++){
            node.descendants.arr[z].data = root.descendants.arr[j].data;
            z += 1;
            root.descendants.arr[j].data = null;
        }
        root.descendants.arr[size + 1] = node;
    }

    private void  turn_left()




    class Node<I,K>{
        Data<I,K> data = new Data<>();
        Descendants<I,K> descendants ;

        @Override
        public String toString() {
            return data.key + " : " + data.item;
        }
        public Node(int size){
            descendants = new Descendants<>(size);
        }
    }

    class Data<I,K>{
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
    }
    class Descendants<I,K>  {
        Node[] arr; // Node[0](ссылка на узел левый); Mode[size+1] ссылка на правый узел

        public Descendants(int size){
            arr = new Node[size + 2];
        }



        /*
        Node<I,K> leftDescendants;
        Node<I,K> rightDescendants;
*/
    }
    // Последний элемент массива ссылается на новый узел
}
