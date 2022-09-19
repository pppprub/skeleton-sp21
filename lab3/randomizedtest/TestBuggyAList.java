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
}
