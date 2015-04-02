package github.jadetang.dudu.datastructure.graph;

import java.util.Set;

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

    /**
     * add a edge between two node of graph,return true if success,return false if
     * there is already a edge between these vertices. throw nullpoint exception if
     * the node do not exist in graph
     * @param from
     * @param to
     * @return
     */
    public boolean addEdge(T from, T to);

    /**
     * add a vertex to a graph, return true if success, return false if the vertex already
     * exist in graph
     * @param vertex
     * @return
     */
    public boolean addVertex(T vertex);


    /**
     * vertices adjacent to v
     * @param v
     * @return
     */
    public Iterable<T> adj(T v);


    /**
     * run a deep first search staring at a given vertex,throw NullPointerException
     * if the vertex is not in the graph
     * @param start
     * @return
     */
    public Set<T> dfs(T start);


    public Set<T> dfs(Iterable<T> starts);



}
