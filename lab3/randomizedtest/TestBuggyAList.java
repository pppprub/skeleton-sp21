package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> a = new AListNoResizing();
        BuggyAList<Integer> b = new BuggyAList<>();
        for(int num = 1; a.size() <= 3; num++){
            a.addLast(num);
            b.addLast(num);
            assertEquals(a.size(),b.size());
        };
        for(int index = 1; a.size()>0; index++){
            assertEquals(a.removeLast(),b.removeLast());
        }
    }
    @Test
    public void ramdomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                assertEquals(L.size(),B.size());
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size_L = L.size();
                int size_B = B.size();
                assertEquals(size_L,size_B);
                System.out.println("sizeL: " + size_L + "sizeB: " + size_B);
            }else  {
                if (L.size() > 0){
                    if(operationNumber == 2){
                       int last_L =  L.getLast();
                        int last_B =  B.getLast();
                        assertEquals(last_L,last_B);
                        System.out.println("LastL: " + last_L+"LastB: " + last_B);}
                else if (operationNumber == 3){
                            int rmv_last_L =  L.removeLast();
                            int rmv_last_B =  B.removeLast();
                            assertEquals(rmv_last_L,rmv_last_B);
                            System.out.println("removeLast(L: " + rmv_last_L + "B: " + rmv_last_B + ")");
                    }
                }
            }
        }
    }
}
