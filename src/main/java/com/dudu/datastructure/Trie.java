package com.dudu.datastructure;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.*;

/**
 * Implement Trie,accept null value,do not accept null key and the key shoulld be string
 */
public class Trie<V> extends AbstractMap<String, V> implements Map<String, V> {
    private Alphabet alphabet;
    private int R;
    private TrieEntry<V> root;
    private int size;


    public Trie(Alphabet alphabet) {
        this.alphabet = alphabet;
        R = alphabet.R();
    }


    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public V put(String key, V value) {
        validKey(key);
        if (root == null) root = new TrieEntry<V>(null, null, null);
        if (value == null) return remove(key);
        return put(root, key, value, 0);
    }

    @Override
    public V get(Object key) {
        validKey(key);
        return get(root, (String) key, 0).value;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public V remove(Object key) {
        validKey(key);
        return remove(root, (String) key, 0);
    }

    private V remove(TrieEntry<V> x, String key, int d) {
        if (x == null) return null;
        if (key.length() == d) {
            V oldValue = x.value;
            x.value = null;
            decrSize();
            nullEntryRecursive(x);
            return oldValue;
        }
        int index = alphabet.toIndex(key.charAt(d));
        return remove(x.next[index], key, d + 1);
    }

    /**
     * null a trieEntry if it's value is null and it's a leaf of the trie
     * after null this trieEntry, continue to null its parent recursively
     *
     * @param x
     */
    private void nullEntryRecursive(TrieEntry<V> x) {
        if (x == null) return;
        TrieEntry<V> parent = x.parent == null ? null : x.parent;
        if (isLeaf(x)) {
            x.next = null;
            nullEntryRecursive(parent);
        } else {
            return;
        }
    }

    /**
     * null a entry field
     *
     * @param x
     */
    private void nullEntry(TrieEntry<V> x) {
        x.key = null;
        x.value = null;
        x.next = null;
        x.parent = null;
    }

    private boolean isLeaf(TrieEntry<V> x) {
        if (x != null) {
            if (x.next != null) {
                for (int i = 0; i < x.next.length; i++) {
                    if (x.next[i] != null) {
                        if (x.next[i].key != null && x.next[i].value != null) {
                            return false;
                        }
                    }
                }
            }

        }
        return true;

    }


    @Override
    public Set<String> keySet() {
        return keysSetWithPrefix("");
    }

    public Set<String> keysSetWithPrefix(String pre) {
        Set<String> tempEntrySet = new HashSet<String>();
        collectKeys(get(root, pre, 0), pre, tempEntrySet);
        return ImmutableSet.copyOf(tempEntrySet);
    }

    @Override
    public Set<Entry<String, V>> entrySet() {
        return entrySetWithPrefix("");
    }

    public Set<Entry<String, V>> entrySetWithPrefix(String pre) {
        Set<Entry<String, V>> tempEntrySet = new HashSet<Entry<String, V>>();
        collectEntries(get(root, pre, 0), pre, tempEntrySet);
        return ImmutableSet.copyOf(tempEntrySet);
    }

    public Collection<V> values() {
        return valuesWithPrefix("");
    }

    public Collection<V> valuesWithPrefix(String pre) {
        List<V> tempValueList = new LinkedList<V>();
        collectValues(get(root, pre, 0), pre, tempValueList);
        return ImmutableList.copyOf(tempValueList);
    }


    private V put(TrieEntry<V> x, String key, V value, int d) {
        if (key.length() == d) {
            V oldValue = x.value;
            x.value = value;
            x.key = key;
            incrSize();
            return oldValue;
        }
        int index = alphabet.toIndex(key.charAt(d));
        if (x.next == null) {
            x.next = (TrieEntry<V>[]) new TrieEntry[R];
        }
        if (x.next[index] == null) {
            x.next[index] = new TrieEntry<V>(null, null, x);
        }
        return put(x.next[index], key, value, d + 1);
    }

    private TrieEntry<V> get(TrieEntry<V> node, String key, int d) {
        if (node == null) return null;
        if (key.length() == d) return node;
        int index = alphabet.toIndex(key.charAt(d));
        return get(node.next[index], key, d + 1);
    }


    private void collectEntries(TrieEntry<V> x, String pre, Set<Entry<String, V>> entriesSet) {
        if (x == null) return;
        if (x.value != null) entriesSet.add(x);
        for (char c = 0; c < R; c++) {
            collectEntries(x.next[c], pre + c, entriesSet);
        }
    }

    private void collectKeys(TrieEntry<V> x, String pre, Set<String> keySet) {
        if (x == null) return;
        if (x.value != null) keySet.add(x.getKey());
        for (char c = 0; c < R; c++) {
            collectKeys(x.next[c], pre + c, keySet);
        }
    }

    private void collectValues(TrieEntry<V> x, String pre, List<V> tempValueList) {
        if (x == null) return;
        if (x.value != null) tempValueList.add(x.getValue());
        for (char c = 0; c < R; c++) {
            collectValues(x.next[c], pre + c, tempValueList);
        }
    }


    private void incrSize() {
        size++;
    }

    private void decrSize() {
        assert size > 0;
        size--;
    }

    /**
     * check the key is not null and is String
     */
    private void validKey(Object key) {
        if (key == null) throw new NullPointerException("tire do not permit null key");
    }


    class TrieEntry<V> implements Map.Entry<String, V> {
        String key;
        V value;
        TrieEntry<V>[] next;
        TrieEntry<V> parent;


        TrieEntry(String key, V value, TrieEntry<V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
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


}
