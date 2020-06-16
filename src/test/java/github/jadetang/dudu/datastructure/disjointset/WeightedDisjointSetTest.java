package github.jadetang.dudu.datastructure.disjointset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class WeightedDisjointSetTest {

    private WeightedDisjointSet<Integer> dset;
    private Set<Integer> data = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private WeightedDisjointSet<Integer> defaultSet;

    @Before
    public void setUp() {
        dset = new WeightedDisjointSet<>(data);
        defaultSet = new WeightedDisjointSet<>();
    }


    @Test
    public void testConnect() {
        dset.connect(1, 10);
        dset.connect(1, 2);
        dset.connect(2, 3);
        assertTrue(dset.isConnect(1, 3));
    }

    @Test
    public void testFind() {
        dset.connect(1, 10);
        dset.connect(2, 10);
        dset.add(11);
        dset.connect(1, 11);
        assertEquals(dset.find(11), dset.find(10));
    }


    @Test
    public void testToString() {
        dset.connect(1, 10);
        dset.connect(2, 3);
        dset.connect(3, 4);
        dset.connect(6, 7);
        dset.connect(8, 9);
        assertEquals(dset.count, dset.collect().size());
    }

    @Test
    public void defaultsizeTest() {
        for (int i = 1; i <= 1000; i++) {
            defaultSet.add(i);
        }
        assertEquals(1000, defaultSet.count());
        // assertEquals(1000,defaultSet.size);
        for (int i = 1; i <= 1000; i += 2) {
            defaultSet.connect(i, i + 1);
        }
        assertEquals(500, defaultSet.count());
        for (int i = 1; i <= 1000; i += 2) {
            assertTrue(defaultSet.isConnect(i, i + 1));
        }
        for (int i = 2; i < 1000; i += 2) {
            defaultSet.connect(i, i + 1);
        }
        for (int i = 2; i <= 1000; i += 2) {
            assertTrue(defaultSet.isConnect(1, i));
        }
    }
}