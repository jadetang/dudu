package com.dudu.datastructure;

import java.util.*;

/**
 * Implement Trie,accept null value,do not accept null key and the key shoulld be string
 */
public class Trie<V> extends AbstractMap<String, V> implements Map<String, V> {
    private Alphabet alphabet;
    private int R;
    private TrieEntry<V> root;
    private int size;
    private transient Set<Map.Entry<String, V>> entrySet = null;
    private transient int modCount = 0;

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
        root = new Trie<V>.TrieEntry<V>(null);
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public V put(String key, V value) {
        validKey(key);
        if (value == null) return remove(key);
        return put(root, key, value, 0);
    }

    @Override
    public V get(Object key) {
        validKey(key);
        return getEntry(root, (String) key, 0).value;
    }

    /**
     * check the key is not null and is String
     */
    private void validKey(Object key) {
        if (key == null) throw new NullPointerException("tire do not permit null key");
        //  if (!(key instanceof String)) throw new ClassCastException("tire only accept String as key");
    }

    @Override
    public int size() {
        return size;
    }

    private TrieEntry<V> getEntry(TrieEntry<V> node, String key, int d) {
        if (node == null) return null;
        if (key.length() == d) return node;
        int index = alphabet.toIndex(key.charAt(d));
        return getEntry(node.next[index], key, d + 1);
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
            if (isLeaf(x)) x = null;
            modCount++;
            return oldValue;
        }
        int index = alphabet.toIndex(key.charAt(d));
        return remove(x.next[index], key, d + 1);
    }

    private boolean isLeaf(TrieEntry<V> x) {
        return x.next == null;
    }

    private V put(TrieEntry<V> x, String key, V value, int d) {
        if (key.length() == d) {
            V oldValue = x.value;
            x.value = value;
            x.key = key;
            incrSize();
            modCount++;
            return oldValue;
        }
        int index = alphabet.toIndex(key.charAt(d));
        if (x.next == null) {
            x.next = (TrieEntry<V>[]) new TrieEntry[R];
        }
        if (x.next[index] == null) {
            x.next[index] = new TrieEntry<V>(x);
        }
        return put(x.next[index], key, value, d + 1);
    }

    @Override
    public Set<Entry<String, V>> entrySet() {
        Set<Map.Entry<String, V>> es = entrySet;
        return (es != null) ? es : (entrySet = new EntrySet(""));
    }

    public Set<Entry<String, V>> entrySet(String prefix) {
        if ("".equals(prefix)) {
            Set<Map.Entry<String, V>> es = entrySet;
            return (es != null) ? es : (entrySet = new EntrySet(""));
        } else {
            return new EntrySet(prefix);
        }

    }

    abstract class PrivateEntryIterator<T> implements Iterator<T> {
        TrieEntry<V> next;
        TrieEntry<V> lastReturned;
        int expectedModCount;

        PrivateEntryIterator(TrieEntry<V> first) {
            expectedModCount = modCount;
            next = findMostLeft(first);
        }

        public final boolean hasNext() {
            return next != null;
        }

        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            Trie.this.remove(lastReturned);
            expectedModCount = modCount;
            lastReturned = null;
        }

        public final TrieEntry<V> nextTrieEntry() {
            if (lastReturned == root) return null;
            TrieEntry<V> e = next;
            if (e == null)
                throw new NoSuchElementException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            next = nextTrieEntry(e);
            lastReturned = e;
            return e;
        }

        private TrieEntry<V> nextTrieEntry(TrieEntry<V> e) {
            TrieEntry<V> mostSubLeft = findMostLeft(e);
            if (mostSubLeft != null) {
                return mostSubLeft;
            } else {
                int index = indexOf(e.parent.next, e);
                TrieEntry<V> nextSibling = nextNotNullEntryInArray(e.parent.next, index + 1);
                if (nextSibling == null) {
                    return e.parent;
                } else {
                    return nextTrieEntry(nextSibling);
                }

            }

        }

        private TrieEntry<V> findMostLeft(TrieEntry<V> e) {
            if (e == null) return null;
            if (e.next == null && e != lastReturned) return e;
            TrieEntry<V> firstLeftSubEntry = nextNotNullEntryInArray(e.next, 0);
            TrieEntry<V> firstLeftSubSubEntry = nextNotNullEntryInArray(firstLeftSubEntry.next, 0);
            if (firstLeftSubSubEntry == null) {
                return firstLeftSubEntry;
            } else {
                return findMostLeft(firstLeftSubSubEntry);
            }
        }

        private int indexOf(TrieEntry<V>[] next, TrieEntry<V> e) {
            for (int i = 0; i < next.length; i++) {
                if (next[i] == e) return i;
            }
            return -1;
        }

        private TrieEntry<V> nextNotNullEntryInArray(TrieEntry<V>[] trieEntries, int beginIndex) {
            for (int i = beginIndex; i < trieEntries.length; i++) {
                if (trieEntries[i] != null && trieEntries[i].value != null) return trieEntries[i];
            }
            return null;
        }

    }

    final class TrieEntryIterator extends PrivateEntryIterator {
        TrieEntryIterator(TrieEntry<V> first) {
            super(first);
        }

        @Override
        public TrieEntry<V> next() {
            return nextTrieEntry();
        }
    }

    final class ValueIterator extends PrivateEntryIterator<V> {
        ValueIterator(TrieEntry<V> first) {
            super(first);
        }

        @Override
        public V next() {
            return nextTrieEntry().value;
        }
    }

    final class KeyIterator extends PrivateEntryIterator<String> {
        KeyIterator(TrieEntry<V> first) {
            super(first);
        }

        public String next() {
            return nextTrieEntry().key;
        }
    }

    private final class EntrySet extends AbstractSet<Map.Entry<String, V>> {

        private final String prefix;

        EntrySet(String prefix) {
            this.prefix = prefix;
        }


        public Iterator<Map.Entry<String, V>> iterator() {
            return new TrieEntryIterator(getEntry(root, prefix, 0));
        }

        public boolean contains(Object o) {
            if (!(o instanceof TrieEntry)) return false;
            TrieEntry<V> entry = (TrieEntry<V>) o;
            String key = entry.getKey();
            TrieEntry<V> p = getEntry(root, key, 0);
            return p != null && isPrefix(prefix, key);
        }

        /**
         * check a string is prefix of another string, "" is every
         * other string's prefix;
         */
        private boolean isPrefix(String prefix, String key) {
            if ("".equals(prefix) && key != null) return true;
            if (prefix == null || key == null) return false;
            return prefix.equals(key.substring(prefix.length()));
        }

        public boolean remove(Object o) {
            if (!(o instanceof TrieEntry)) return false;
            TrieEntry<V> entry = (TrieEntry<V>) o;
            V value = entry.getValue();
            String key = entry.getKey();
            TrieEntry<V> p = getEntry(entry, entry.getKey(), 0);
            if (p != null && valEquals(p.getValue(), value) && isPrefix(prefix, key)) {
                remove(p);
                return true;
            }
            return false;
        }

        public int size() {
            return Trie.this.size();
        }

        public void clear() {
            Trie.this.clear();
        }
    }


    static final boolean valEquals(Object o1, Object o2) {
        return (o1 == null ? o2 == null : o1.equals(o2));
    }

    public class TrieEntry<V> implements Map.Entry<String, V> {
        String key;
        V value;
        TrieEntry<V> parent = null;
        TrieEntry<V>[] next = null;


        TrieEntry(TrieEntry<V> parent) {
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


    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(null, "one");
        map.put(null, "two");
        String x = map.get(null);
        System.out.println(x);
    }
}
