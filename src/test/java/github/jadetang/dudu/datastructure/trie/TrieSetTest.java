package github.jadetang.dudu.datastructure.trie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TrieSetTest {

    TrieSet trie;

    @Before
    public void setUp() {
        trie = new TrieSet();
    }

    @Test
    public void testAdd() {
        trie.add("x");
        assertTrue(trie.add("y"));
        assertFalse(trie.add("x"));
    }

    @Test
    public void testAddAll() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        trie.addAll(list);
        assertEquals(list.size(), trie.size());
    }

    @Test
    public void testIterator() {
        trie.add("a");
        trie.add("b");
        trie.add("c");
        trie.add("e");
        trie.add("f");
        trie.add("g");
        StringBuilder sb = new StringBuilder();
        for (String x : trie) {
            sb.append(x);
        }
        assertEquals("abcefg", sb.toString());
    }

    @Test
    public void testSize() {
        trie.add("a");
        trie.add("a");
        assertEquals(1, trie.size());
    }
}