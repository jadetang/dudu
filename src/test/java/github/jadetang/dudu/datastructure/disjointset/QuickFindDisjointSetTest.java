package github.jadetang.dudu.datastructure.disjointset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class QuickFindDisjointSetTest {

    private QuickFindDisjointSet<Integer> dset;
    private Set<Integer> data = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Before
    public void setUp() {
        dset = new QuickFindDisjointSet<>(data);
    }


    @Test
    public void testFind() {
        dset.connect(1, 10);
        assertEquals(dset.find(1), dset.find(10));
    }

    @Test
    public void testIsConnect() {
        dset.connect(1, 10);
        dset.connect(1, 5);
        assertTrue(dset.isConnect(5, 10));
    }

    @Test
    public void testCount() {
        assertEquals(data.size(), dset.count());
        dset.connect(1, 10);
        assertEquals(data.size() - 1, dset.count());
    }
}