class Date<I,K extends Comparable>  {
    private I item;
    private K key;

    public Date(I item, K key) {
        this.item = item;
        this.key = key;
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



    //@Override
    public int compareTo(Date o) {
        return this.key.compareTo(o.key);
    }
}