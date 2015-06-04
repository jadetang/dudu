package github.jadetang.dudu.datastructure.disjointset;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class WeightedDisjointSetTest {

    private WeightedDisjointSet<Integer> dset;
    private Set<Integer> data = Sets.newHashSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private WeightedDisjointSet<Integer> defaultSet;

    @Before
    public void setUp() throws Exception {
        dset = new WeightedDisjointSet<>(data);
        defaultSet = new WeightedDisjointSet<>();
    }


    @Test
    public void testConnect() throws Exception {
        dset.connect(1, 10);
        dset.connect(1, 2);
        dset.connect(2, 3);
        Assert.assertTrue(dset.isConnect(1, 3));

    }

    @Test
    public void testFind() throws Exception {
        dset.connect(1, 10);
        dset.connect(2, 10);
        dset.add(11);
        dset.connect(1, 11);
        Assert.assertEquals(dset.find(11), dset.find(10));
    }


    @Test
    public void testToString() throws Exception {
        dset.connect(1, 10);
        dset.connect(2, 3);
        dset.connect(3, 4);
        dset.connect(6, 7);
        dset.connect(8, 9);
        Assert.assertEquals(dset.count, dset.collect().size());
        System.out.println(dset);
    }

    @Test
    public void defaultsizeTest() {
        for (int i = 1; i <= 1000; i++) {
            defaultSet.add(i);
        }
        Assert.assertEquals(1000, defaultSet.count());
        // Assert.assertEquals(1000,defaultSet.size);
        for (int i = 1; i <= 1000; i += 2) {
            defaultSet.connect(i, i + 1);
        }
        Assert.assertEquals(500, defaultSet.count());
        for (int i = 1; i <= 1000; i += 2) {
            Assert.assertTrue(defaultSet.isConnect(i, i + 1));
        }
        for (int i = 2; i < 1000; i += 2) {
            defaultSet.connect(i, i + 1);
        }
        for (int i = 2; i <=1000 ; i +=2) {
            Assert.assertTrue(defaultSet.isConnect(1,i));
        }
    }
}