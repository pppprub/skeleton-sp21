package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
    items = (T[]) new Object[8];
    nextFirst = 3;
    nextLast = 4;
    size = 0;
    }

    public void addFirst(T item){
        if(IsFull()){
            resize();
        }
        items[nextFirst] = item;
        //if (nextFirst == nextLast){
        if(nextFirst == 0){
            nextFirst = nextFirst + items.length;
        }
        nextFirst--;
        size++;
    }

    public void addLast(T item){
        if(IsFull()){
            resize();
        }
        items[nextLast] = item;
        if(nextLast == items.length) {
            nextLast = nextLast - items.length;
        }
        nextLast++;
        size++;
    }

    public boolean IsFull(){
        if(items.length == size){
            return true;
        }else {
            return false;
        }
    }

    public void resize(){

    }

}
