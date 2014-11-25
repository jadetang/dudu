package com.dudu.datastructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static java.util.Map.Entry;

/**
 * Created by Administrator on 14-11-19.
 */
public class TrieTest {

    Trie<Integer> trie;
    @Before
    public void setUp() throws Exception{
        trie = new Trie<Integer>(Alphabet.DECIMAL);
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
        trie.put("12",3);
        Assert.assertEquals(2,trie.size());
        Assert.assertNull(trie.remove("2"));
        Assert.assertEquals(new Integer(3),trie.remove("1"));
        Assert.assertEquals(1,trie.size());
        trie.put("123",3);
        Assert.assertEquals(2,trie.size());
    }
    @Test
    public void testContainsValue() throws Exception{
        trie.put("1",3);
        Assert.assertTrue(trie.containsValue(3));
        Assert.assertFalse(trie.containsValue("aaa"));
    }


    @Test
    public void testContainsKey()throws Exception{
        trie.put("1",3);
        Assert.assertTrue(trie.containsKey("1"));
//        Assert.assertFalse(trie.containsKey(1));
    }

    @Test
    public void testEntrySet() throws Exception{
        trie.put("1",3);
        trie.put("22",3);
        trie.put("34444",3);
        trie.put("55514",3);
        trie.put("511111", 3);
        Assert.assertEquals(5, trie.entrySet().size());
        trie.remove("511111");
        Assert.assertEquals(4, trie.entrySet().size());

    }

    @Test(expected = ConcurrentModificationException.class)
    public void testFailFast(){
        trie.put("1",3);
        trie.put("22",3);
        trie.put("34444",3);
        trie.put("55514",3);
        trie.put("511111", 3);
        Iterator<Entry<String,Integer>> it = trie.entrySet().iterator();
        while(it.hasNext()){
            it.next();
            trie.remove("1");
        }
    }

    @Test
    public void testValues1(){
        trie.put("1",1);
        trie.put("12",2);
        trie.put("23",3);
        trie.put("2344",4);
        Set<Entry<String,Integer>> allSets = trie.entrySet();
        System.out.println(allSets);
    }



}
