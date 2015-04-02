package github.jadetang.dudu.datastructure.graph;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author tangsicheng
 * @version 1.0
 * @since 1.0
 */
public class DirectedGraphTest {

    private Graph<Integer> g;


    @Before
    public void setUp() throws Exception{
        g = new DirectedGraph<Integer>();
    }


    @Test
    public void testVertices() throws Exception {
        int size = 10;
        for (int i = 0; i < size; i++) {
            g.addVertex(i);
        }
        Assert.assertEquals(10, g.vertices());
    }

    @Test
    public void testEdges() throws Exception {
        int size = 10;
        for (int i = 0; i < size; i++) {
            g.addVertex(i);
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i!=j){
                    g.addEdge(i,j);
                }
            }
        }
        Assert.assertEquals((size * (size - 1)), g.edges());
    }

    @Test
    public void testAddEdge() throws Exception {
        int size = 10;
        for (int i = 0; i < size; i++) {
            g.addVertex(i);
        }
        Assert.assertTrue(g.addEdge(0,1));
        Assert.assertFalse(g.addEdge(0,1));
    }

    @Test
    public void testAdj() throws Exception {
        int size = 10;
        for (int i = 0; i < size; i++) {
            g.addVertex(i);
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i!=j){
                    g.addEdge(i,j);
                }
            }
        }
        int count = 0;
        for(Integer i:g.adj(size/2)){
            count++;
            System.out.println(i);
        }
        Assert.assertEquals(size-1,count);
    }

    @Test
    public void testDfs() throws Exception{
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        Assert.assertEquals(4, g.dfs(1).size());
        Assert.assertEquals(2, g.dfs(3).size());
        Assert.assertEquals(1,g.dfs(2).size());
    }

    @Test
    public void testDfs2() throws Exception{
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addEdge(1, 2);
        g.addEdge(1,3);
        g.addEdge(3,4);




        Assert.assertEquals(3,g.dfs(Arrays.asList(2,3)).size());
    }
}
