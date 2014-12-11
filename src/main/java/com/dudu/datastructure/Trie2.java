package com.dudu.datastructure;

import java.util.*;

/**
 * @author tangsicheng
 * @version 1.0
 * @since 1.0
 */
public class Trie2<V> extends AbstractMap<String, V> implements NavigableMap<String, V>, Cloneable, java.io.Serializable {

    private Alphabet alphabet;
    private static int R;
    private Entry<V> root;
    private int size;


    public Trie2() {
        this(Alphabet.ASCII);
    }

    /**
     * assign custom Alphabet for better space performance.
     *
     * @param alphabet
     */
    public Trie2(Alphabet alphabet) {
        this.alphabet = alphabet;
        R = alphabet.R();
    }


    @Override
    public V put(String key, V value) {
        validKey(key);
        if (root == null) root = new Entry<V>(null);
        if (value == null) return remove(key);
        return put(root, key, value, 0);
    }

    private V put(Entry<V> x, String key, V value, int d) {
        if (key.length() == d) {
            V oldValue = x.value;
            x.value = value;
            x.key = key;
            size++;
            return oldValue;
        }
        int index = alphabet.toIndex(key.charAt(d));
        if (x.next[index] == null) {
            x.next[index] = new Entry<V>(x);
        }
        return put(x.next[index], key, value, d + 1);
    }

    private void validKey(Object key) {
        if (key == null) throw new NullPointerException("tire do not permit null key");
    }

    private Entry<V> successor(Entry<V> x){
        Entry<V> candidate  = successorOfSubTrie(x);
        return candidate !=null ? candidate: successorOfSibling(x);
    }


    private Entry<V> successorOfSubTrie(Entry<V> x) {
        if (x == null) {
            return null;
        }
        for (int i = 0; i < R; i++) {
            if (x.next[i] != null && x.next[i].isString()) {
                return x.next[i];
            } else {
                Entry<V> candidate =  successorOfSubTrie(x.next[i]);
                if(candidate != null){
                    return candidate;
                }
            }
        }
        return null;
    }

    private Entry<V> successorOfSibling(Entry<V> x) {
        if (x.parent == null) {
            return null;
        } else {
            int beginIndex = indexOf(x.parent.next, x)+1;
            for (int i = beginIndex; i < x.parent.next.length; i++) {
                if(x.parent.next[i] != null && x.parent.next[i].isString()){
                    return x.parent.next[i];
                }else {
                    Entry<V> candidate = successorOfSubTrie(x.parent.next[i]);
                    if(candidate != null){
                        return candidate;
                    }
                }
            }
        }
        return successorOfSibling(x.parent);
    }


    private int indexOf(Entry<V>[] array, Entry<V> o) {
        int i = 0;
        while (array[i] != o) {
            i++;
        }
        return i;
    }


    public static void main(String[] args) {
        Trie2<String> set = new Trie2<String>(Alphabet.LOWERCASE);
        set.put("a","a");
        set.put("ab","ab");
        set.put("abaa","abaa");
        set.put("abade","abade");
        set.put("abab","abab");
        set.put("abd","abd");
        set.put("abc","abc");
        set.put("abac","abac");
        set.put("abac","abac");

        Entry<String> next = set.successor(set.root);
        while(next !=null){
            System.out.println(next);
            next = set.successor(next);
        }
        System.out.println("huge break through!");
       /* while (set.iterator().hasNext()){
            System.out.println(set.iterator().next());
        }*/

    }


    static class Entry<V> implements Map.Entry<String, V> {
        String key;
        V value;
        Entry<V>[] next;
        Entry<V> parent;

        public boolean isString() {
            return key != null;
        }

        public Entry(Entry<V> parent) {
            this.parent = parent;
            next = (Entry<V>[]) new Entry[R];
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    @Override
    public Set<Map.Entry<String, V>> entrySet() {
        return null;
    }

    @Override
    public Map.Entry<String, V> lowerEntry(String key) {
        return null;
    }

    @Override
    public String lowerKey(String key) {
        return null;
    }

    @Override
    public Map.Entry<String, V> floorEntry(String key) {
        return null;
    }

    @Override
    public String floorKey(String key) {
        return null;
    }

    @Override
    public Map.Entry<String, V> ceilingEntry(String key) {
        return null;
    }

    @Override
    public String ceilingKey(String key) {
        return null;
    }

    @Override
    public Map.Entry<String, V> higherEntry(String key) {
        return null;
    }

    @Override
    public String higherKey(String key) {
        return null;
    }

    @Override
    public Map.Entry<String, V> firstEntry() {
        return null;
    }

    @Override
    public Map.Entry<String, V> lastEntry() {
        return null;
    }

    @Override
    public Map.Entry<String, V> pollFirstEntry() {
        return null;
    }

    @Override
    public Map.Entry<String, V> pollLastEntry() {
        return null;
    }

    @Override
    public NavigableMap<String, V> descendingMap() {
        return null;
    }

    @Override
    public NavigableSet<String> navigableKeySet() {
        return null;
    }

    @Override
    public NavigableSet<String> descendingKeySet() {
        return null;
    }

    @Override
    public NavigableMap<String, V> subMap(String fromKey, boolean fromInclusive, String toKey, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableMap<String, V> headMap(String toKey, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableMap<String, V> tailMap(String fromKey, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super String> comparator() {
        return null;
    }

    @Override
    public SortedMap<String, V> subMap(String fromKey, String toKey) {
        return null;
    }

    @Override
    public SortedMap<String, V> headMap(String toKey) {
        return null;
    }

    @Override
    public SortedMap<String, V> tailMap(String fromKey) {
        return null;
    }

    @Override
    public String firstKey() {
        return null;
    }

    @Override
    public String lastKey() {
        return null;
    }


}
