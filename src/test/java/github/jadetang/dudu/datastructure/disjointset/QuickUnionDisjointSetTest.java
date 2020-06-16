package github.jadetang.dudu.datastructure.disjointset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QuickUnionDisjointSetTest {

    private QuickUnionDisjointSet<Integer> dset;
    private final Set<Integer> data = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Before
    public void setUp() {
        dset = new QuickUnionDisjointSet<>(data);
    }

    @Test
    public void testConnect() {
        dset.connect(1, 10);
        dset.connect(1, 2);
        assertTrue(dset.isConnect(2, 10));
    }

    @Test
    public void testFind() {
        dset.connect(1, 10);
        dset.connect(2, 10);
        assertEquals(dset.find(2), dset.find(10));
    }

}