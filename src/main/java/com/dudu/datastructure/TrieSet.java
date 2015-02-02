package com.dudu.datastructure;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by jadetang on 15-2-2.
 */
public class TrieSet extends AbstractSet<String> implements TSet, Cloneable, java.io.Serializable {

    private transient TrieMap<Object> trieMap;

    private static final Object PRESENT = new Object();

    public TrieSet() {
        trieMap = new TrieMap<Object>();
    }

    public TrieSet(Alphabet alphabet) {
        trieMap = new TrieMap<Object>(alphabet);
    }

    public TrieSet(Collection<? extends String> c) {
        trieMap = new TrieMap<Object>();
        addAll(c);
    }

    public TrieSet(Alphabet alphabet, Collection<? extends String> c) {
        trieMap = new TrieMap<Object>(alphabet);
        addAll(c);
    }

    @Override
    public boolean add(String e) {
        return trieMap.put(e, PRESENT) == null;
    }

    @Override
    public Iterator<String> iterator() {
        return trieMap.keySet().iterator();
    }

    @Override
    public int size() {
        return trieMap.size();
    }

    @Override
    public boolean contains(Object key) {
        return trieMap.containsKey(key);
    }

    @Override
    public boolean remove(Object o) {
        return trieMap.remove(o) !=null;
    }

    @Override
    public void clear() {
        trieMap.clear();
    }

    @Override
    public String longestPrefixOf(String query) {
        return trieMap.longestPrefixOf(query);
    }

    @Override
    public Iterable<String> keysWithPrefix(String prefix) {
        return trieMap.keysWithPrefix(prefix);
    }
}
