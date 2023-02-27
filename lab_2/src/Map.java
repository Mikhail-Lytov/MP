
public class Map<T extends Comparable<T>> implements Comparable<T> {

    private Node<T> root = new Node<T>();

    public void add_element(Data<T> data){
        insert(root, data);

    }
    public void print(){
        output(this.root);

    }

    public Map() {
    }


    public Map(Map copy){
        Copy(root, copy.root);
    }

    public void clear(){
        if(root.right_descendant != null){
            clear_element(root.right_descendant);
        }
        if (root.left_descendant != null) {
            clear_element(root.left_descendant);
        }
        root = null;
        System.gc();//сборщик мусора
    }
    public boolean checking_emptiness(){
        if(root.data.getItem() == null){
            return true;//пусто
        }else return false; //не пусто
    }

    private void Copy(Node<T> root,Node<T> root_copy){
        if(root_copy != null) {
            Comparable item = root_copy.data.getItem();
            root.data.setItem((T) item);
            Comparable key = root_copy.data.getKey();
            root.data.setKey((T) key);
            if (root_copy.left_descendant != null) {
                Node<T> node = new Node<>();
                root.left_descendant = node;
                Copy(node,root_copy.left_descendant);

            }else root.left_descendant = null;
            if (root_copy.right_descendant != null) {
                Node<T> node = new Node<>();
                root.right_descendant = node;
                Copy(node,root_copy.right_descendant);
            }else root.right_descendant = null;
        }
    }
    private Data search_element(Node<T> node, T key) throws DataError{

        if(node.data.getKey().compareTo(key) == 0) {
            return node.data;

        }else if (key.compareTo(node.data.getKey()) > 0 && node.right_descendant == null ) {
            throw new DataError("В дереве нету такого ключа");
        } else if (key.compareTo(node.data.getKey()) > 0) {
            return search_element(node.right_descendant, key);
        } else if (key.compareTo(node.data.getKey()) < 0 && node.left_descendant == null) {
            throw new DataError("В дереве нету такого ключа");
        } else if (key.compareTo(node.data.getKey()) < 0) {
            return search_element(node.left_descendant, key);

        }
        throw new DataError("В дереве нету такого ключа");

    }
    private boolean search(Node<T> node, T key){
        if(node.data.getKey() == key){
            return true;
        } else if (key.compareTo(node.data.getKey()) > 0 && node.right_descendant == null ) {
            return false;
        } else if (key.compareTo(node.data.getKey()) > 0) {
            return search(node.right_descendant,key);
        } else if (key.compareTo(node.data.getKey()) < 0 && node.left_descendant == null ) {
            return false;
        } else if (key.compareTo(node.data.getKey()) < 0) {
            return search(node.left_descendant,key);
        }

        return false;
    }
    public Data search_element(T key) throws DataError{
        if(root == null){
            throw new DataError("В дереве нету такого ключа");
        }
        return search_element(root, key);

    }
    public boolean search(T key){
        if(root == null){
            return false;
        }
        return search(root,key);
    }

    private void clear_element(Node<T> root){
        if(root.right_descendant != null){
            clear_element(root.right_descendant);
        }
        if (root.left_descendant != null) {
            clear_element(root.left_descendant);
        }
        root = null;

    }

    private void TurnLeft(Node<T> root) { //Левый поворот
        Node<T> leftSubTree, leftSubTreeRightSubtree;
        leftSubTree = root.left_descendant;
        leftSubTreeRightSubtree = leftSubTree.right_descendant;

        leftSubTree.right_descendant = root;
        root.left_descendant = leftSubTreeRightSubtree;
        root = leftSubTree;
        SetBalance(root.right_descendant);
        SetBalance(root);
    }

    private void TurnRight(Node<T> root){ // правый поворот
        Node<T> leftSubtree, leftSubtreeRightSubtree;
        leftSubtree = root.left_descendant;
        leftSubtreeRightSubtree = leftSubtree.right_descendant;

        leftSubtree.right_descendant = root;
        root.left_descendant = leftSubtreeRightSubtree;
        root = leftSubtree;
        SetBalance(root.right_descendant);
        SetBalance(root);
    }

    private void insert(Node<T> root, Data<T> data) {//добвление в уузел;

        if(root.data.getItem() == null){
            root.data.setItem(data.getItem());
            root.data.setKey(data.getKey());
            root.balance = 0;
            root.left_descendant = null;
            root.right_descendant = null;
        } else{
            if(data.compareTo(root.data.getKey()) > 0){ // data.getKey() > root.data.getKey()
                if(root.right_descendant == null){
                    Node<T> node = new Node<T>();
                    root.right_descendant = node;
                }
                insert(root.right_descendant, data);
                if(Height(root.right_descendant) - Height(root.left_descendant) > 1) // если баланс АВЛ дерева нарушен
                {//выполняем вращение
                    if(Height(root.right_descendant) < Height(root.right_descendant.left_descendant))// если были еще проблемы в првом поддереве
                    {
                        TurnRight(root.right_descendant);//то предварительно поворачивем правое поддерево
                    }
                    TurnLeft(root); //поворот дерева в лево
                }

            } else if (data.compareTo(root.data.getKey()) < 0){
                if(root.left_descendant == null){
                    Node<T> node = new Node<T>();
                    root.left_descendant = node;
                }
                insert(root.left_descendant, data);
                if(Height(root.left_descendant) - Height(root.right_descendant) > 1){
                    if(Height(root.left_descendant.left_descendant) < Height(root.left_descendant.right_descendant)){
                        TurnLeft(root.left_descendant);
                    }
                    TurnRight(root);
                }
            }
            SetBalance(root);//Пересчитываем значения баланса
        }

    }

    private void output(Node<T> p){
        if(p != null){
            output(p.left_descendant);
            System.out.println(p.data.getKey() + ":" + p.data.getItem() + " ");
            output(p.right_descendant);
        }
    }

    private int Height(Node<T> root) { //Вычилсяем высоту дерева
        if (root == null) return 0; // если дерево пусто
        int hLef = Height(root.left_descendant); // считаем высоту левого поддерева
        int hRight = Height(root.right_descendant); // считаем высоту правого поддерева
        if (hLef > hRight){
            return hLef + 1;
        }else return hRight + 1;
    }
    private void SetBalance(Node<T> root){ // Нахождения значения баланса для текущего узла
        if(root != null){
            root.balance = Height(root.right_descendant) - Height(root.left_descendant);
        }
    }

    @Override
    public int compareTo(T o) {
        return 0;
    }


    class Node<T extends Comparable<T>> {
        private Data<T> data = new Data<T>();
        private int balance;
        private Node<T> left_descendant;
        private Node<T> right_descendant ; // ссылка на правое поддерево




    }


}
