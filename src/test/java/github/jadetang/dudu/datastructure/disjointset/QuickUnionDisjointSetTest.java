package github.jadetang.dudu.datastructure.disjointset;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class QuickUnionDisjointSetTest {

    private QuickUnionDisjointSet<Integer> dset;
    private Set<Integer> data = Sets.newHashSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Before
    public void setUp() throws Exception{
        dset = new QuickUnionDisjointSet<>(data);
    }


    @Test
    public void testConnect() throws Exception {
        dset.connect(1,10);
        dset.connect(1,2);
        Assert.assertTrue(dset.isConnect(2,10));

    }

    @Test
    public void testFind() throws Exception {
        dset.connect(1,10);
        dset.connect(2,10);
        Assert.assertEquals(dset.find(2),dset.find(10));

    }


}