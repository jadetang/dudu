package com.dudu.datastructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Created by Administrator on 14-11-19.
 */
public class TrieTest {

    Trie<Integer> trie;
    @Before
    public void setUp() throws Exception{
        trie = new Trie<Integer>(Alphabet.ASCII);
    }

    @Test
    public void testPut() throws Exception {
        trie.put("1",1);
        trie.put("2", new Random().nextInt());

    }

    @Test
    public void testGet() throws Exception {
        trie.put("1",1);
        trie.put("2",2);
        trie.put("3",3);
        Assert.assertEquals(new Integer(1), trie.get("1"));
        Assert.assertEquals(new Integer(2), trie.get("2"));
        Assert.assertEquals(new Integer(3),trie.get("3"));
    }

    @Test
    public void testSize() throws Exception {

    }

    @Test
    public void testRemove() throws Exception {
        trie.put("1",3);
        Assert.assertEquals(1,trie.size());
        Assert.assertNull(trie.remove("2"));
        Assert.assertEquals(new Integer(3),trie.remove("1"));
        Assert.assertEquals(0,trie.size());
    }
}
