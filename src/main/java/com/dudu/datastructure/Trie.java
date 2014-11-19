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
    public V put(String key, V value) {
        if (key == null) throw new UnsupportedOperationException("key should not be null");
        if (value == null) return remove(key);
        return put(root, key, value, 0);
    }

    @Override
    public V get(Object key) {
        if (isValidKey(key)) {
            return get(root, (String) key, 0);
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

    private V get(TrieNode<V> node, String key, int d) {
        if (node == null) return null;
        if (key.length() == d) return node.value;
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
            return oldValue;
        }
        int index = alphabet.toIndex(key.charAt(d));
        return remove(x.next[index], key, d + 1);
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
        return null;
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
