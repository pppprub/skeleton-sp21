package deque;

import net.sf.saxon.functions.PositionAndLast;

import java.security.SecureRandom;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }

    public void addFirst(T item) {
//        if (IsFull()) {
//            resize();
//        }
        items[nextFirst] = item;
        //if (nextFirst == nextLast){
        if (nextFirst == 0) {
            nextFirst = nextFirst + items.length - 1;
        } else {
            nextFirst--;
        }
        size++;
        resize();
    }

    public void addLast(T item) {
//        if (IsFull()) {
//            resize();
//        }
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = nextLast - items.length + 1;
        } else {
            nextLast++;
        }
        size++;
        resize();
    }

    public T remove(int index) {
        T remove_value = items[index];
        items[index] = null;
        size--;
        resize();
        return remove_value;
    }

    private int get_Last_index() {
        int index_Last;
        if (nextLast != 0) {
            index_Last = nextLast - 1;
        } else {
            index_Last = items.length - 1;
        }
        return index_Last;
    }

    private int get_First_index() {
        int index_First;
        if (nextFirst != items.length - 1) {
            index_First = nextFirst + 1;
        } else {
            index_First = 0;
        }
        return index_First;
    }

    public T removeFirst() {
        if (size > 0) {
            int index_First = get_First_index();
//            if(nextFirst != items.length - 1){
//            index_First = nextFirst + 1;
//            }else{
//                index_First = 0;
//            }
            nextFirst = index_First;
            return remove(index_First);
        } else {
            return null;
        }

    }

    public T removeLast() {
        if (size > 0) {
            int index_Last = get_Last_index();
//            if(nextLast != 0){
//                index_Last = nextLast - 1;
//            }else{
//                index_Last = items.length - 1;
//            }
            nextLast = index_Last;
            return remove(index_Last);
        } else {
            return null;
        }

    }

    public T get(int index) {
        if (index >= this.size || index < 0) {
            return null;
        } else {
            int currentNode = index + get_First_index();
            if (currentNode >= items.length) {
                currentNode = currentNode - items.length;
            }
            return items[currentNode];
        }
    }

    public void printDeque() {
        if (size > 0) {
            int Current_index = get_First_index();
            for (int index = 0; index < this.size; index++) {
                System.out.println(items[Current_index] + " ");
                if (Current_index == items.length - 1) {
                    Current_index = 0;
                } else {
                    Current_index = Current_index + 1;
                }
            }
        }
        System.lineSeparator();
    }

    public boolean IsFull() {
        if (items.length == size) {
            return true;
        } else {
            return false;
        }
    }

    //    public boolean isEmpty() {
//        return this.size == 0;
//    }
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPosition;

        public ArrayDequeIterator() {
//            wizPosition = get_First_index();
            wizPosition = 0;
        }

        public boolean hasNext() {
            return wizPosition < size;
        }

        public T next() {
            T ReturnItem = items[wizPosition];
            wizPosition += 1;
            return ReturnItem;
        }
    }

    public int size() {
        return this.size;
    }

    public int length() {
        return items.length;
    }

    private float Usage() {
        return (float) size / items.length;
    }

    public void resize() {
        if (Usage() > 0.75) {
            resize_To(items.length * 2);
        } else if (Usage() < 0.25 && items.length >= 16) {
            resize_To(items.length / 2);
        }
    }

    private void resize_To(int Storage) {
        T[] Newitems = (T[]) new Object[Storage];
        int First_index = get_First_index();
        int Last_index = get_Last_index();
        if (First_index > Last_index) {
            System.arraycopy(items, First_index, Newitems, 4, items.length - First_index);
            System.arraycopy(items, 0, Newitems, 4 + items.length - First_index, Last_index + 1);
        } else {
            System.arraycopy(items, First_index, Newitems, 4, size);
        }
        items = Newitems;
        nextFirst = 3;
        nextLast = nextFirst + size + 1;
    }

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
