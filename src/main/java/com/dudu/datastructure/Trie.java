package com.dudu.datastructure;


import java.util.*;

/**
 * Implement Trie
 */
public class Trie<V> extends AbstractMap<String, V> implements Map<String, V> {
    private Alphabet alphabet;
    private int R;
    private TrieNode<V> root;
    private int size;

    private void incrSize() {
        size++;
    }

    private void decrSize() {
        assert size > 0;
        size--;
    }


    public Trie(Alphabet alphabet) {
        this.alphabet = alphabet;
        R = alphabet.R();
        root = new TrieNode<V>();
    }

    @Override
    public boolean containsKey(Object key){
        return get(key) !=null;
    }

    @Override
    public V put(String key, V value) {
        if (key == null) throw new UnsupportedOperationException("key should not be null");
        if (value == null) return remove(key);
        return put(root, key, value, 0);
    }

    @Override
    public V get(Object key) {
        if (isValidKey(key)) {
            return get(root, (String) key, 0).value;
        }
        return null;
    }

    /**
     * check the key is not null and is String
     */
    private boolean isValidKey(Object key) {
        return key != null && key instanceof String;
    }

    @Override
    public int size() {
        return size;
    }

    private TrieNode<V> get(TrieNode<V> node, String key, int d) {
        if (node == null) return null;
        if (key.length() == d) return node;
        int index = alphabet.toIndex(key.charAt(d));
        return get(node.next[index], key, d + 1);
    }


    @Override
    public V remove(Object key) {
        if (isValidKey(key)) {
            return remove(root, (String) key, 0);
        }
        return null;
    }

    private V remove(TrieNode<V> x, String key, int d) {
        if (x == null) return null;
        if (key.length() == d) {
            V oldValue = x.value;
            x.value = null;
            decrSize();
            if (isLeaf(x)) x = null;
            return oldValue;
        }
        int index = alphabet.toIndex(key.charAt(d));
        return remove(x.next[index], key, d + 1);
    }

    private boolean isLeaf(TrieNode<V> x) {
        if (x.next == null) return true;
        for (int i = 0; i < x.next.length; i++) {
            if (x.next[i] != null) return false;
        }
        return true;
    }

    private V put(TrieNode<V> x, String key, V value, int d) {
        if (key.length() == d) {
            V oldValue = x.value;
            x.value = value;
            incrSize();
            return oldValue;
        }
        int index = alphabet.toIndex(key.charAt(d));
        if (x.next[index] == null) {
            x.next[index] = new TrieNode<V>();
        }
        return put(x.next[index], key, value, d + 1);
    }

    @Override
    public Set<Entry<String, V>> entrySet() {
        return  EntriesWithPrefix("");
    }

    public Set<Entry<String, V>> EntriesWithPrefix(String pre) {
        Set<Entry<String, V>> entriesSet = new HashSet<Entry<String, V>>();
        collectEntries(get(root,pre,0),pre,entriesSet);
        return entriesSet;
    }

    private void collectEntries(TrieNode<V> x, String pre, Set<Entry<String, V>> entriesSet) {
        if(x==null) return;
        if(x.value !=null) entriesSet.add(new TrieEntry<V>(pre,x.value));
        for(char c= 0; c<R; c++){
            collectEntries(x.next[c],pre+c,entriesSet);
        }
    }

    static class TrieEntry<V> implements Map.Entry<String, V> {
        String key;
        V value;

        TrieEntry(String key,V value){
            this.key = key;
            this.value = value;
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
    }


    @SuppressWarnings("unchecked")
    private class TrieNode<V> {
        V value;
        TrieNode<V>[] next = (TrieNode<V>[]) new TrieNode[R];

        TrieNode() {
        }

 /*       TrieNode(V value) {
            this.value = value;
        }*/
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(null, "one");
        map.put(null, "two");
        String x = map.get(null);
        System.out.println(x);
    }
}
