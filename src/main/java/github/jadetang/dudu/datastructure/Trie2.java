package github.jadetang.dudu.datastructure;

import java.util.*;

/**
 * @author tangsicheng
 * @version 1.0
 * @since 1.0
 */
public class Trie2<V> extends AbstractMap<String, V> implements Map<String, V>, Cloneable, java.io.Serializable {

    private Alphabet alphabet;
    private static int R;
    private Entry<V> root;
    private int size;


    private transient EntrySet entrySet = null;
    private transient int modCount = 0;
    private transient volatile Set<String> keySet = null;
    private transient volatile Collection<V> values = null;


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
            modCount++;
            return oldValue;
        }
        int index = alphabet.toIndex(key.charAt(d));
        if (x.next[index] == null) {
            x.next[index] = new Entry<V>(x);
        }
        return put(x.next[index], key, value, d + 1);
    }


    public Iterator<String> keyIterator() {
        return new KeyIterator(getFirstEntry());
    }

    final class KeyIterator extends PrivateEntryIterator {
        KeyIterator(Entry<V> first) {
            super(first);
        }
        public String next() {
            return nextEntry().key;
        }
    }


    @Override
    public V get(Object key) {
        validKey(key);
        Entry<V> candidate = getEntry(root, (String) key, 0);
        return candidate != null ? candidate.value : null;
    }

    private Entry<V> getEntry(Entry<V> x, String key, int d) {
        if (x == null) return null;
        if (key.length() == d) return x;
        int index = alphabet.toIndex(key.charAt(d));
        return getEntry(x.next[index], key, d + 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V remove(Object key) {
        validKey(key);
        return removeEntry(root, (String) key, 0);
    }


    private V removeEntry(Entry<V> x, String key, int d) {
        if (x == null) return null;
        if (key.length() == d) {
            V oldValue = x.value;
            x.value = null;
            if (x.isString()) {
                x.key = null;
                size--;
                modCount++;
                nullEntryRecursive(x);
            }
            return oldValue;
        }
        int index = alphabet.toIndex(key.charAt(d));
        return removeEntry(x.next[index], key, d + 1);
    }


    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    private void nullEntryRecursive(Entry<V> x) {
        if (x == null) return;
        //Entry<V> parent = x.parent == null ? null : x.parent;
        if (isLeaf(x) && !x.isString()) {
            x.next = null;
            x.parent.next[indexOf(x.parent.next, x)] = null;
        }
        if (x.parent != null) {
            nullEntryRecursive(x.parent);
        }
    }

    private boolean isLeaf(Entry<V> x) {
        if (x != null) {
            for (int i = 0; i < x.next.length; i++) {
                if (x.next[i] != null) {
                    return false;
                }
            }
        }
        return true;
    }


    private void validKey(Object key) {
        if (key == null) throw new NullPointerException("tire do not permit null key");
    }

    private Entry<V> successor(Entry<V> x) {
        Entry<V> candidate = successorOfSubTrie(x);
        return candidate != null ? candidate : successorOfSibling(x);
    }

    private Entry<V> predecessor(Entry<V> x) {
        Entry<V> candidate = predecessorOfSibling(x);
        return candidate != null ? candidate : predecessorOfParentTrie(x);
    }

    private Entry<V> predecessorOfParentTrie(Entry<V> x) {
        if (x == root) {
            return null;
        }
        if (x.parent.isString()) {
            return x.parent;
        } else {
            return predecessorOfSibling(x.parent);
        }
    }

    private Entry<V> predecessorOfSibling(Entry<V> x) {
        if (x == root) {
            return null;
        } else {
            int endIndex = indexOf(x.parent.next, x);
            for (int i = endIndex - 1; i >= 0; i--) {
                if (x.parent.next[i] != null && x.parent.next[i].isString()) {
                    return x.parent.next[i];
                } else {
                    Entry<V> candidate = predecessorOfSubTrie(x.parent.next[i]);
                    if (candidate != null) {
                        return candidate;
                    }
                }
            }
            return predecessorOfParentTrie(x.parent);
        }
    }

    private Entry<V> predecessorOfSubTrie(Entry<V> x) {
        if (x == null) {
            return null;
        }
        for (int i = R - 1; i >= 0; i--) {
            if (x.next[i] != null && x.next[i].isString()) {
                return x.next[i];
            } else {
                Entry<V> candidate = predecessorOfSubTrie(x.next[i]);
                if (candidate != null) {
                    return candidate;
                }
            }
        }
        return null;
    }

    private Entry<V> successorOfSubTrie(Entry<V> x) {
        if (x == null) {
            return null;
        }
        for (int i = 0; i < R; i++) {
            if (x.next[i] != null && x.next[i].isString()) {
                return x.next[i];
            } else {
                Entry<V> candidate = successorOfSubTrie(x.next[i]);
                if (candidate != null) {
                    return candidate;
                }
            }
        }
        return null;
    }

    private Entry<V> successorOfSibling(Entry<V> x) {
        if (x == root) {
            return null;
        } else {
            int beginIndex = indexOf(x.parent.next, x) + 1;
            for (int i = beginIndex; i < x.parent.next.length; i++) {
                if (x.parent.next[i] != null && x.parent.next[i].isString()) {
                    return x.parent.next[i];
                } else {
                    Entry<V> candidate = successorOfSubTrie(x.parent.next[i]);
                    if (candidate != null) {
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
        Trie2<String> trie = new Trie2<String>(Alphabet.LOWERCASE);
       /* TreeSet<String> treeSet = new TreeSet<String>();
        for (int i = 0; i < 100; i++) {
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            int size = random.nextInt(20);
            for (int j = 0; j < size; j++) {
                char c = Alphabet.LOWERCASE.toChar(random.nextInt(26));
                sb.append(c);
            }
            trie.put(sb.toString(), sb.toString());
            treeSet.add(sb.toString());
        }*/

     /*   Entry<String> next = trie.successor(trie.root);
        while (next != null) {
            System.out.println(next.getKey());
            next = trie.successor(next);
        }
        System.out.println("---------------------");
        for (String x : treeSet) {
            System.out.println(x);
        }*/

        trie.put("a", "a");
        trie.put("aba", "a");
        trie.put("abc", "a");
        trie.put("abef", "a");
        trie.put("ac", "a");
        trie.put("ad", "a");

        Entry<String> next = trie.successor(trie.root);
        while (next != null) {
            System.out.println(next.getKey());
            next = trie.successor(next);
        }
        System.out.println("-------------------------");
        Entry<String> last = trie.successor(trie.root);
        for (int i = 0; i < trie.size() - 1; i++) {
            last = trie.successor(last);
        }
        System.out.println(last);
        System.out.println("-------------------------");
        for (Entry<String> x = trie.predecessor(last); x != null; x = trie.predecessor(x)) {
            System.out.println(x);
        }

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
        EntrySet es = entrySet;
        return (es != null) ? es : (entrySet = new EntrySet());
    }

    static final boolean valEquals(Object o1, Object o2) {
        return (o1 == null ? o2 == null : o1.equals(o2));
    }

    abstract class PrivateEntryIterator<T> implements Iterator<T> {
        Entry<V> next;
        Entry<V> lastReturned;
        int expectedModCount;

        PrivateEntryIterator(Entry<V> first) {
            expectedModCount = modCount;
            lastReturned = null;
            next = first;
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Entry<V> nextEntry() {
            Entry<V> e = next;
            if (e == null)
                throw new NoSuchElementException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            next = successor(e);
            lastReturned = e;
            return e;
        }

        final Entry<V> prevEntry() {
            Entry<V> e = next;
            if (e == null)
                throw new NoSuchElementException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            next = predecessor(e);
            lastReturned = e;
            return e;
        }

        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            // deleted entries are replaced by their successors
            removeEntry(root, lastReturned.getKey(), 0);
            expectedModCount = modCount;
            lastReturned = null;
        }
    }


    final class EntryIterator extends PrivateEntryIterator<Map.Entry<String, V>> {
        EntryIterator(Entry<V> first) {
            super(first);
        }

        public Map.Entry<String, V> next() {
            return nextEntry();
        }
    }

    class EntrySet extends AbstractSet<Map.Entry<String, V>> {

        @Override
        public Iterator<Map.Entry<String, V>> iterator() {
            return new EntryIterator(getFirstEntry());
        }

        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<String, V> entry = (Map.Entry<String, V>) o;
            V value = entry.getValue();
            Entry<V> p = getEntry(root, entry.getKey(), 0);
            return p != null && valEquals(p.getValue(), value);
        }

        public boolean remove(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<String, V> entry = (Map.Entry<String, V>) o;
            V value = entry.getValue();
            Entry<V> p = getEntry(root, entry.getKey(), 0);
            if (p != null && valEquals(p.getValue(), value)) {
                removeEntry(root, p.getKey(), 0);
                return true;
            }
            return false;
        }

        public int size() {
            return Trie2.this.size();
        }

        public void clear() {
            Trie2.this.clear();
        }
    }

    @Override
    public boolean containsValue(Object value) {
        for (Entry<V> e = getFirstEntry(); e != null; e = successor(e))
            if (valEquals(value, e.value))
                return true;
        return false;
    }


    public Collection<V> values() {
        Collection<V> vs = values;
        return (vs != null) ? vs : (values = new Values());
    }

    public Set<String> keySet() {
        Set<String> ks = keySet;
        return (ks != null ? ks : (keySet = new KeySet()));
    }

    private final class KeySet extends AbstractSet<String> {
        public Iterator<String> iterator() {
            return new KeyIterator(getFirstEntry());
        }
        public int size() {
            return size;
        }
        public boolean contains(Object o) {
            return containsKey(o);
        }
        public boolean remove(Object o) {
            return Trie2.this.remove(o) != null;
        }
        public void clear() {
            Trie2.this.clear();
        }
    }


    final class ValueIterator extends PrivateEntryIterator<V> {
        ValueIterator(Entry<V> first) {
            super(first);
        }

        public V next() {
            return nextEntry().value;
        }
    }

    private Entry<V> getFirstEntry() {
        return successor(root);
    }


    class Values extends AbstractCollection<V> {
        public Iterator<V> iterator() {
            return new ValueIterator(getFirstEntry());
        }

        public int size() {
            return Trie2.this.size();
        }

        public boolean contains(Object o) {
            return Trie2.this.containsValue(o);
        }

        public boolean remove(Object o) {
            for (Entry<V> e = getFirstEntry(); e != null; e = successor(e)) {
                if (valEquals(e.getValue(), o)) {
                    removeEntry(root, e.getKey(), 0);
                    return true;
                }
            }
            return false;
        }

        public void clear() {
            Trie2.this.clear();
        }
    }



}