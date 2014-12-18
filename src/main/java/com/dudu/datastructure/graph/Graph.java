package com.dudu.datastructure.graph;

/**
 * A Graph
 */
public interface Graph<T> {
    /**
     * number of vertices
     * @return
     */

    public int vertices();

    /**
     * number of edges
     * @return
     */
    public int edges();

    public void addEdge(T from, T to);

    /**
     * vertices adjacent to v
     * @param v
     * @return
     */
    public Iterable<T> adj(T v);

}
