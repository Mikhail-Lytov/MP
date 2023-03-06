public class MyStack {
    private int size = 6;
    private int top;
    private int[] array;

    public MyStack(){
        array = new int[size];
        top = -1;
    }

    public void push(int v){
        array[++top] = v;
    }

    public int pop(){
        if(top - 1 == -1){
            top = -1;
            return array[top + 1];
        }
        return array[--top];
    }
    public int getSize(){
        return size;
    }

    public int peek(){
        return array[top];
    }

    public boolean isEmpty(){
        if(top == -1){
            return true;
        }else {
            return false;
        }
    }

}
