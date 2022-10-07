package deque;


import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private int size = 0;
    private TNode sentinel;

    public Iterator<T> iterator() {
        return new LListDequeIterator();
    }

    private class LListDequeIterator implements Iterator<T> {
        private TNode wizPosition;

        public LListDequeIterator() {
//            wizPosition = get_First_index();
            wizPosition = sentinel.next;
        }

        public boolean hasNext() {
            return !isEmpty() && wizPosition != sentinel;
        }

        public T next() {
            T ReturnItem = wizPosition.item;
            wizPosition = wizPosition.next;
            return ReturnItem;
        }
    }

    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(T item) {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        this.addFirst(item);
    }

    public class TNode {
        public T item;
        public TNode prev;
        public TNode next;

        public TNode(T i, TNode p, TNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    public void addFirst(T item) {
        TNode FirstNode = new TNode(item, sentinel, sentinel.next);
        sentinel.next.prev = FirstNode;
        sentinel.next = FirstNode;
        size++;
    }

    public void addLast(T item) {
        TNode LastNode = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = LastNode;
        sentinel.prev = LastNode;
        size++;
    }

//    public boolean isEmpty() {
//        return this.size == 0;
//    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        TNode Node = sentinel;
        for (int index = 0; index < this.size; index++) {
            System.out.println(Node.item + " ");
            Node = Node.next;
        }
        System.lineSeparator();
    }

    public T removeFirst() {
        T FirstValue = get(0);
        if (FirstValue != null) {
            TNode FirstNode = sentinel.next;
            FirstNode.next.prev = sentinel;
            sentinel.next = FirstNode.next;
            size--;
        }
        return FirstValue;
    }

    public T removeLast() {
//        T LastValue = get(this.size-1); //Not used since the get() will scan the entire list to find the last item.
        if (sentinel.prev != sentinel) {
            TNode LastNode = sentinel.prev;
            LastNode.prev.next = sentinel;
            sentinel.prev = LastNode.prev;
            size--;
            return LastNode.item;
        } else {
            return null;
        }
    }

    public T get(int index) {
        if (index >= this.size || index < 0) {
            return null;
        } else {
            TNode currentNode = sentinel;
            for (int i = 0; i <= index; i++) {
                currentNode = currentNode.next;
            }
            return currentNode.item;
        }
    }

    public T getRecursive(int index) {
        if (index >= this.size || index < 0) {
            return null;
        } else {
            return getRecursiveHelper(index, sentinel.next);
        }
    }

    private T getRecursiveHelper(int index, TNode Node) {
        if (index == 0) {
            return Node.item;
        } else {
            return getRecursiveHelper(index - 1, Node.next);
        }
    }

    //    public Iterator<T> iterator(){
//    }
//    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Deque) {
            Deque<T> o_downcast = (Deque<T>) o;
            if (this.size != o_downcast.size()) {
                return false;
            } else {
                for (int index = 0; index < this.size; index++) {
                    if (this.get(index) != o_downcast.get(index)) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }

}
