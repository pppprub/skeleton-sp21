package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max(Comparator<T> c) {
        int size = size();
        if (size == 0) {
            return null;
        } else {
            T max = get(0);
            for (int i = 1; i < size; i++) {
                T current_value = get(i);
                if (c.compare(current_value,max) > 0) { //chapter 4.3, comparator
                    max = current_value;
                }
            }
            return max;
        }
    }

    public T max(){
        return max(comparator);
    }
}
