package com.dudu.datastructure;

import java.util.Map;

/**
 * Created by jadetang on 14-12-12.
 */
public interface Trie<V> extends Map<String,V> {

    /**
     * Returns the string in the Trie that is the longest prefix of <tt>query</tt>,
     * or <tt>null</tt>, if no such string.
     * @param query the query string
     * @throws NullPointerException if <tt>query</tt> is <tt>null</tt>
     * @return the string in the set that is the longest prefix of <tt>query</tt>,
     *     or <tt>null</tt> if no such string
     */
    public String longestPrefixOf(String query);

    /**
     * Returns all of the keys in the Trie that start with <tt>prefix</tt>.
     * @param prefix the prefix
     * @return all of the keys in the Trie that start with <tt>prefix</tt>,
     */
    public Iterable<String> keysWithPrefix(String prefix);

    /**
     * Returns all of the Entries in the Trie associating with the keys that start with <tt>prefix</tt>.
     * @param prefix the prefix
     * @return all of the Entries in the Trie that start with <tt>prefix</tt>,
     */
    public Iterable<Entry<String,V>> entryWithPrefix(String prefix);

    /**
     * Returns all of the Values in the Trie associating with the keys that start with <tt>prefix</tt>.
     * @param prefix the prefix
     * @return all of the Values in the Trie that start with <tt>prefix</tt>,
     */
    public Iterable<V> valuesWithPrefix(String prefix);

}
