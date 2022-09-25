package deque;

public interface Deque<T> {
    void addFirst(T item);

    void addLast(T item);

    T removeFirst();

    T removeLast();

    T get(int index);

    int size();

    void printDeque();

    default boolean isEmpty() {
        return size() == 0;
    }
}
