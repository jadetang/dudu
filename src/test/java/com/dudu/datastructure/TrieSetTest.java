package com.dudu.datastructure;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TrieSetTest {

    TrieSet t;
    List<String> list = Lists.newArrayList("one","two","three");

    @Before
    public void setUp() throws Exception{
        t = new TrieSet();
    }

    @Test
    public void testAdd() throws Exception {
        t.add("x");
        Assert.assertTrue(t.add("y"));
        Assert.assertFalse(t.add("x"));
    }

    @Test
    public void testAddAll() throws Exception{
        t.addAll(list);
    }

    @Test
    public void testIterator() throws Exception {
        t.add("a");
        t.add("b");
        t.add("c");
        t.add("e");
        t.add("f");
        t.add("g");
        for (String x:t){
            System.out.println(x);
        }

    }

    @Test
    public void testSize() throws Exception {

    }

    @Test
    public void testLongestPrefixOf() throws Exception {

    }

    @Test
    public void testKeysWithPrefix() throws Exception {

    }
}