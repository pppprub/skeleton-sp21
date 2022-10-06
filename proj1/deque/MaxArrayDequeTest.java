package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    @Test
    /* Add large number of elements to MaxArrayDequ; check if max() function is correct. */
    public void MaxArrayDequeTest_case1() {
        //overwrite the compare function
        Comparator<Integer> comp = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else if (o1 < o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<>(comp);
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }
        System.out.println("The max value is " + lld1.max());
        assertEquals("Should have the same value", 999999, lld1.max(), 0.0);
    }
}
