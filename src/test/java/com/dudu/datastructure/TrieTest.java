package com.dudu.datastructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Random;
import java.util.Set;

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
        trie.put("55514", 3);
        trie.put("511111", 3);
        Set<Map.Entry<String,Integer>> entrySet = trie.entrySet();
        Assert.assertEquals(5,entrySet.size());
        for(Map.Entry<String,Integer> e:entrySet){
            System.out.println(e);
        }

        Set<Map.Entry<String,Integer>> entrySet2 = trie.entrySetWithPrefix("5");
        Assert.assertEquals(2,entrySet2.size());
    }

    @Test
    public void testKeySet() throws Exception{
        trie.put("2",3);
        trie.put("22",3);
        trie.put("22344",3);
        trie.put("55514", 3);
        trie.put("511111", 3);
        Set<String> keySet = trie.keySet();
        Assert.assertEquals(5,keySet.size());
        Set<String> keySet2 = trie.keysSetWithPrefix("22");
        Assert.assertEquals(2,keySet2.size());
    }

    @Test
    public void testValues() throws Exception{
        trie.put("2",1);
        trie.put("22",2);
        trie.put("22344",3);
        trie.put("55514", 4);
        trie.put("511111", 5);
        for(Integer v:trie.values()){
            System.out.println(v);
        }
    }



   /* @Test(expected = ConcurrentModificationException.class)
    public void testFailFast(){
        trie.put("1",3);
        trie.put("22",3);
        trie.put("34444",3);
        trie.put("55514",3);
        trie.put("511111", 3);
        Iterator<Map.Entry<String,Integer>> it = trie.entrySet().iterator();
        while(it.hasNext()){
            it.next();
            trie.remove("1");
        }
    }

    @Test
    public void testValues(){
        trie.put("1",3);
        trie.put("22",3);
        trie.put("34444",3);
        trie.put("55514",3);
        trie.put("511111", 3);
    }*/

}
