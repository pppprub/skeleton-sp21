package deque;

import com.sun.tools.internal.xjc.api.TypeAndAnnotation;

public class LinkedListDeque<T> {
    private int size = 0;
    private TNode sentinel;
    public LinkedListDeque(){
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    public LinkedListDeque(T item){
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        this.addFirst(item);
    }
    public class TNode {
        public T item;
        public TNode prev;
        public TNode next;
        public TNode(T i, TNode p, TNode n){
            item = i;
            prev = p;
            next = n;
        }
    }

    public void addFirst(T item){
        TNode FirstNode = new TNode(item, sentinel, sentinel.next);
        sentinel.next.prev = FirstNode;
        sentinel.next = FirstNode;
        size++;
    }

    public void addLast(T item){
        TNode LastNode =  new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = LastNode;
        sentinel.prev = LastNode;
        size++;
    }
    
    public boolean isEmpty(){};
    public int size(){};
    public void printDeque(){}

    public T removeFirst(){
        T FirstValue = get(0);
        if (FirstValue != null){
            TNode FirstNode = sentinel.next;
            FirstNode.next.prev = sentinel;
            sentinel.next = FirstNode.next;
            size--;
        }
        return FirstValue;
    }

    public T removeLast(){
//        T LastValue = get(this.size-1); //Not used since the get() will scan the entire list to find the last item.
        if (sentinel.prev != sentinel){
            TNode LastNode =  sentinel.prev;
            LastNode.prev.next = sentinel;
            sentinel.prev = LastNode.prev;
            size--;
            return LastNode.item;
        }else{
        return null;
        }
    }
    public T get(int index){
        if(index >= this.size || index < 0){
            return null;
        }else {
            TNode currentNode = sentinel;
            for(int i = 0; i< index; i++){
                currentNode = currentNode.next;
            }
            return currentNode.item;
        }
    }

}
