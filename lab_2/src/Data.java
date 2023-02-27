public class Data<T extends Comparable<T>> implements Comparable<T> {
    private T item = null;
    private T key = null;


    public Data() {
    }

    public Data(T item, T key) {
        this.item = item;
        this.key = key;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public void set(T item, T key){
        this.item = item;
        this.key = key;
    }
    @Override
    public String toString() {
        return item.toString() + ": " + key.toString();
    }

    @Override
    public int compareTo(T o) {
        int result = this.key.compareTo(o);
        return result;
    }
}
