package github.jadetang.dudu.datastructure.trie;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;


public class HashTrieTest {
    HashTrie<Integer> trie;

    @Before
    public void setUp() throws Exception {
        trie = new HashTrie<Integer>();
    }

    @Test
    public void testPut() throws Exception {
        trie.put("1", 1);
        trie.put("2", new Random().nextInt());
        assertEquals(2, trie.size());
    }

    @Test
    public void testGet() throws Exception {
        trie.put("1", 1);
        trie.put("2", 2);
        trie.put("3", 3);
        trie.put("332", 3);
        assertEquals(new Integer(1), trie.get("1"));
        assertEquals(new Integer(2), trie.get("2"));
        assertEquals(new Integer(3), trie.get("3"));
        assertEquals(new Integer(3), trie.get("332"));
    }

    @Test
    public void testSize() throws Exception {
        trie.put("1", 1);
        trie.put("2", 2);
        trie.put("3", 3);
        assertEquals(3, trie.size());
    }

    @Test
    public void testRemove() throws Exception {
        trie.put("1", 3);
        trie.put("12", 3);
        assertEquals(2, trie.size());
        assertNull(trie.remove("2"));
        assertEquals(new Integer(3), trie.remove("1"));
        assertEquals(1, trie.size());
        trie.put("123", 3);
        assertEquals(2, trie.size());
    }

    @Test
    public void testRemove2() throws Exception {
        trie.put("1", 2);
        trie.put("123", 2);
        trie.put("12345", 2);
        trie.put("12343", 1);
        assertEquals(new Integer(2), trie.remove("12345"));
        assertEquals(null, trie.remove("12345"));
        assertEquals(new Integer(1), trie.remove("12343"));
        assertEquals(null, trie.remove("12343"));
        assertNull(trie.get("12343"));
        assertEquals(new Integer(2), trie.remove("1"));
        assertEquals(new Integer(2), trie.get("123"));
        assertEquals(new Integer(2), trie.remove("123"));
    }


    @Test
    public void testRemove3() throws Exception {
        trie.put("1", 2);
        trie.put("123", 2);
        trie.put("12345", 2);
        trie.put("12343", 1);
        assertEquals(4, trie.size());
        trie.remove("1234");
        assertEquals(4, trie.size());
    }

    @Test
    public void testContainsValue() throws Exception {
        trie.put("122", 3);
        trie.put("1333", 3);
        trie.put("122", 3);
        trie.put("1224", 3);
        trie.put("1521", 3);
        trie.put("4441", 3);
        trie.put("12", 3);
        trie.put("1333", 3);
        trie.put("3", 3);
        trie.put("23", 3);
        trie.put("1", 3);
        System.out.println(trie.entrySet());
        assertEquals(trie.size(), trie.entrySet().size());
        assertTrue(trie.containsValue(3));
        assertFalse(trie.containsValue("aaa"));
    }

    @Test
    public void testSize2() {
        trie.put("one1", 1);
        trie.put("one2", 2);
        trie.put("one3", 3);
        trie.put("one4", 4);
        trie.put("one5", 5);
        trie.put("one6", 6);
        trie.put("one7", 7);
        trie.put("one7", 8);
        Assert.assertEquals(7, trie.size());
    }


    @Test
    public void testContainsKey() throws Exception {
        trie.put("122", 3);
        trie.put("1333", 3);
        trie.put("122", 3);
        trie.put("1224", 3);
        trie.put("1521", 3);
        trie.put("4441", 3);
        trie.put("12", 3);
        trie.put("1333", 3);
        trie.put("3", 3);
        trie.put("23", 3);
        trie.put("1", 3);
        Assert.assertTrue(trie.containsKey("1"));
        Assert.assertFalse(trie.containsKey("2"));
    }


    @Test
    public void testEntrySet() throws Exception {
        trie.put("122", 3);
        trie.put("1333", 3);
        trie.put("122", 3);
        trie.put("1224", 3);
        trie.put("1521", 3);
        trie.put("4441", 3);
        trie.put("12", 3);
        trie.put("1333", 3);
        trie.put("3", 3);
        trie.put("23", 3);
        trie.put("1", 3);
        for (Map.Entry<String, Integer> entry : trie.entrySet()) {
            System.out.print(entry);
        }
        trie.entrySet().clear();
        System.out.println();
        for (Map.Entry<String, Integer> entry : trie.entrySet()) {
            System.out.print(entry);
        }
    }


    @Test
    public void testEntrySet2() throws Exception {
        trie.put("1", 3);
        trie.put("22", 3);
        trie.put("34444", 3);
        trie.put("55514", 3);
        trie.put("511111", 3);
        Set<Map.Entry<String, Integer>> entrySet = trie.entrySet();
        Assert.assertEquals(5, entrySet.size());
        for (Map.Entry<String, Integer> e : entrySet) {
            System.out.println(e);
        }
    }


    @Test
    public void testKeySet() throws Exception {
        trie.put("2", 3);
        trie.put("22", 3);
        trie.put("22344", 3);
        trie.put("55514", 3);
        trie.put("511111", 3);
        Set<String> keySet = trie.keySet();
        Assert.assertEquals(5, keySet.size());
        for (String key : trie.keySet()) {
            System.out.println(key);
        }
    }

    @Test
    public void testValues() throws Exception{
        trie.put("1",1);
        trie.put("2",2);
        trie.put("4",3);
        trie.put("3", 4);
        trie.put("5", 5);
        for(Integer v:trie.values()){
            System.out.println(v);
        }
        Assert.assertEquals(5,trie.values().size());
        trie.values().clear();
        Assert.assertEquals(0,trie.size());
    }

    @Test
    public void testClear() throws Exception{
        trie.put("2",1);
        trie.put("22",2);
        trie.put("22344",3);
        trie.put("55514", 4);
        trie.put("511111", 5);
        Assert.assertEquals(5, trie.size());
        trie.clear();
        Assert.assertEquals(0, trie.size());
        trie.put("2", 1);
        Assert.assertEquals(1, trie.size());
    }



    @Test
    public void testNotFailFast(){
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
        trie.remove("no exsitting");
        Assert.assertEquals(4,trie.size());
    }

    @Test
    public void testLongestPrefixKey(){
        trie = new HashTrie<Integer>();
        trie.put("she",1);
        trie.put("shel",2);
        trie.put("shellx",2);
        String x = trie.longestPrefixOf("shell");
        Assert.assertEquals("shel",x);
        Assert.assertNull(trie.longestPrefixOf("xxxxx"));
    }

    @Test
    public void testEntrySetWithPrefix(){
        trie = new HashTrie<Integer>();
        trie.put("she",1);
        trie.put("shel",2);
        trie.put("shellx",2);
        for(Map.Entry<String,Integer> entry: trie.entryWithPrefix("shell")){
            System.out.println(entry);
        }
        //Assert.assertNull(trie.get("she"));
        Assert.assertEquals(2,((Set)trie.entryWithPrefix("shel")).size());

    }


    @Test
    public void testValueSetWithPreifx(){
        trie.put("one1",1);
        trie.put("one2",2);
        trie.put("one3",3);
        trie.put("one4",4);
        trie.put("one5",5);
        trie.put("one6",6);
        trie.put("one7",7);
        for(Integer i:trie.valuesWithPrefix("one")){
            Assert.assertTrue(trie.containsValue(i));
            System.out.println(i);
        }
    }

    @Test
    public void testKeySetWithPrefix(){
        trie.put("one1",1);
        trie.put("one2",2);
        trie.put("one3",3);
        trie.put("one4",4);
        trie.put("one5",5);
        trie.put("one6",6);
        trie.put("one7",7);
        for(String i:trie.keysWithPrefix("one")){
            Assert.assertTrue(trie.containsKey(i));
            System.out.println(i);
        }
    }

}