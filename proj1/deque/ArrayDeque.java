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
    public T remove(int index){
        T remove_value = items[index];
        items[index] = null;
        size--;
        return remove_value;
    }

    public T removeFirst(){
        if (size > 0){
            int index_Fast;
            if(nextFirst != items.length){
            index_Fast = nextLast + 1;
            }else{
                index_Fast = 0;
            }
            nextFirst = index_Fast;
            return remove(index_Fast);
        }else{
            return null;
        }

    }

    public T removeLast(){
        if (size > 0){
            int index_Last;
            if(nextLast != 0){
                index_Last = nextLast - 1;
            }else{
                index_Last = items.length;
            }
            nextLast = index_Last;
            return remove(index_Last);
        }else{
            return null;
        }

    }

    public boolean IsFull(){
        if(items.length == size){
            return true;
        }else {
            return false;
        }
    }

    public int size(){
        return this.size;
    }
    public void resize(){

    }

}
