package github.jadetang.dudu.datastructure.disjointset;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class QuickFindDisjointSetTest {

    private QuickFindDisjointSet<Integer> dset;
    private Set<Integer> data = Sets.newHashSet(1,2,3,4,5,6,7,8,9,10);

    @Before
    public void setUp() throws Exception{
        dset = new QuickFindDisjointSet<Integer>(data);
    }


    @Test
    public void testFind() throws Exception {
        dset.connect(1,10);
        Assert.assertEquals(dset.find(1),dset.find(10));
    }

    @Test
    public void testIsConnect() throws Exception {
        dset.connect(1,10);
        dset.connect(1,5);
        Assert.assertTrue(dset.isConnect(5,10));
    }

    @Test
    public void testCount() throws Exception {
        Assert.assertEquals(data.size(),dset.count());
        dset.connect(1, 10);
        Assert.assertEquals(data.size()-1,dset.count());

    }
}