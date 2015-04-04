package github.jadetang.dudu.datastructure.disjointset;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class WeightedDisjointSetTest {

    private WeightedDisjointSet<Integer> dset;
    private Set<Integer> data = Sets.newHashSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Before
    public void setUp() throws Exception {
        dset = new WeightedDisjointSet<>(data);
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
        Assert.assertEquals(dset.find(2), dset.find(10));
    }


    @Test
    public void testToString() throws Exception{
        dset.connect(1,10);
        dset.connect(2,3);
        dset.connect(3,4);
        dset.connect(6,7);
        dset.connect(8,9);
        Assert.assertEquals(dset.count,dset.collect().size());
        System.out.println(dset);
    }
}