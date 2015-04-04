package github.jadetang.dudu.datastructure.trie;

import java.util.Set;

/**
 * Created by jadetang on 15-2-2.
 */
public interface TSet extends Set<String> {

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

}
