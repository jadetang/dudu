package github.jadetang.dudu.datastructure.trie;

import java.util.*;

/**
 * @author tangsicheng
 * @version 1.0
 * @since 1.0
 */
public class HashTrie<V> extends AbstractMap<String, V> implements Trie<V> {


    static class Entry<V> implements Map.Entry<String, V> {

        String key;
        V value;
        HashMap<Character, Entry<V>> children;
        Entry<V> parent;

        public Entry(Entry<V> parent) {
            this.parent = parent;
            children = new HashMap<Character, Entry<V>>();
        }


        @Override
        public String getKey() {
            return key;
        }

        private boolean noEmpty() {
            return key != null;
        }

        private boolean isEmpty() {
            return !noEmpty();
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


    private int size = 0;
    private Entry<V> root;


    public HashTrie() {
        size = 0;
        root = new Entry<V>(null);
    }

    @Override
    public String longestPrefixOf(String query) {
        int length = longestPrefixOf(root, query, 0, 0);
        return length == 0 ? null : query.substring(0, length);
    }

    private int longestPrefixOf(Entry<V> x, String query, int d, int length) {
        if (x == null) return length;
        if (x.noEmpty()) length = d;
        if (query.length() == d) return length;
        char index = query.charAt(d);
        return longestPrefixOf(x.children.get(index), query, d + 1, length);
    }

    @Override
    public Iterable<String> keysWithPrefix(String prefix) {
        List<String> result = new LinkedList<String>();
        for (Map.Entry<String,V> v: entryWithPrefix(prefix)){
            result.add(v.getKey());
        }
        return result;
    }

    @Override
    public Iterable<Map.Entry<String, V>> entryWithPrefix(String prefix) {
        Entry<V> startEntry = getEntry(root,prefix,0);
        return entryWithStart(startEntry);
    }

    @Override
    public Iterable<V> valuesWithPrefix(String prefix) {
        List<V> result = new LinkedList<V>();
        for (Map.Entry<String,V> v: entryWithPrefix(prefix)){
            result.add(v.getValue());
        }
        return result;
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
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    /*@Override
    public boolean containsValue(Object value) {
        return false;
    }*/

    @Override
    public V get(Object key) {
        if (key == null || !(key instanceof String)) {
            return null;
        } else {
            String stringKey = (String) key;
            if (stringKey.length() == 0) {
                return null;
            } else {
                Entry<V> candidate = getEntry(root, (String) key, 0);
                return candidate != null ? candidate.value : null;
            }
        }
    }

    private Entry<V> getEntry(Entry<V> x, String key, int d) {
        if (x == null) return null;
        if (key.length() == d) return x;
        char index = key.charAt(d);
        return getEntry(x.children.get(index), key, d + 1);
    }

    @Override
    public V put(String key, V value) {
        checkStrKey(key);
        if (value == null) return remove(key);
        if (root == null ) root = new Entry<V>(null);
        return put(root, key, value, 0);
    }

    private V put(Entry<V> x, String key, V value, int d) {
        if (key.length() == d) {
            V oldValue = x.value;
            x.value = value;
            if (oldValue != x.value && x.isEmpty()) {
                size++;
            }
            x.key = key;
            return oldValue;
        } else {
            char index = key.charAt(d);
            Entry<V> oldEntry = x.children.get(index);
            if (oldEntry == null) {
                x.children.put(index, new Entry<V>(x));
            }
            return put(x.children.get(index), key, value, d + 1);
        }
    }

    private void checkStrKey(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("Do not accept empty string");
        }
    }

    @Override
    public V remove(Object key) {
        if (key == null || !(key instanceof String)) {
            return null;
        } else {
            String stringKey = (String) key;
            if (stringKey.length() == 0) {
                return null;
            } else {
                return removeEntry(root, stringKey, 0);
            }
        }
    }

    private V removeEntry(Entry<V> x, String key, int d) {
        if (x == null) return null;
        if (key.length() == d) {
            V oldValue = x.value;
            x.value = null;
            if (x.noEmpty()) {
                x.key = null;
                size--;
                nullEntryRecursive(x);
            }
            return oldValue;
        }
        char index = key.charAt(d);
        return removeEntry(x.children.get(index), key, d + 1);
    }

    private void nullEntryRecursive(Entry<V> x) {
        if (x == null || x == root) {
            return;
        }
        if (x.children.isEmpty() && x.isEmpty()) {
            x.children = null;
            Iterator<Entry<V>> it = x.parent.children.values().iterator();
            while (it.hasNext()) {
                if (x == it.next()) {
                    it.remove();
                    break;
                }
            }
            assert !x.parent.children.containsKey(x);
        }
        if (x.parent != null) {
            nullEntryRecursive(x.parent);
        }

    }


    @Override
    public void clear() {
        size = 0;
        root = null;
    }

   /* @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }*/
    @Override
    public Set<Map.Entry<String, V>> entrySet() {
        return entryWithStart(root);
    }


    private Set<Map.Entry<String, V>> entryWithStart(Entry<V> start) {
        HashSet<Map.Entry<String, V>> collector = new HashSet<Map.Entry<String, V>>();
        entryWithStartHelper(start, collector);
        return collector;
    }

    private void entryWithStartHelper(Entry<V> x, HashSet<Map.Entry<String, V>> collector) {
        if (x == null) {
            return;
        } else {
            if (x.noEmpty()) {
                collector.add(x);
            }
            for (Entry<V> v : x.children.values()) {
                entryWithStartHelper(v, collector);
            }
        }
    }
}
