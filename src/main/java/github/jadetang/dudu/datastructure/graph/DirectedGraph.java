package github.jadetang.dudu.datastructure.graph;

import java.util.*;

/**
 * A directed graph without parallel edge
 */
public class DirectedGraph<T> implements Graph<T> {


    private int vertexNum;

    private int edgeNum;

    private Map<T, Set<T>> graph = new HashMap<T, Set<T>>();

    /**
     * create a empty graph
     */
    public DirectedGraph() {
    }


    @Override
    public int vertices() {
        return vertexNum;
    }

    @Override
    public int edges() {
        return edgeNum;
    }

    @Override
    public boolean addEdge(T from, T to) {
        if (!containVertex(from) || !containVertex(to)) {
            throw new NullPointerException();
        }
        boolean newEdge = getAdjacentSet(from).add(to);
        if(newEdge) edgeNum++;
        return newEdge;
    }

    private boolean containVertex(T vertex) {
        return graph.containsKey(vertex);
    }


    private Set<T> getAdjacentSet(T vertex){
        return graph.get(vertex);
    }


    @Override
    public boolean addVertex(T vertex) {
        if(containVertex(vertex)){return false;}
        Set<T> vertices = new HashSet<T>();
        Set<T> previous = graph.put(vertex,vertices);
        assert previous == null;
        vertexNum++;
        return true;
    }

    @Override
    /**
     * vertices adjacent to v,throw NullPointException if the vertex is not in the graph
     * @param v
     * @return
     */
    public Iterable<T> adj(T v) {
        return new AdjacentIterable(v);
    }

    @Override
    public Set<T> dfs(T start) {
        if(!containVertex(start))throw new NullPointerException();
        Set<T> result = new HashSet<T>();
        dfs(start,result);
        return result;
    }


    public Set<T> dfs(Iterable<T> starts){
        Set<T> result = new HashSet<T>();
        for(T start:starts){
            if (!containVertex(start)) throw new NullPointerException();
            dfs(start, result);
        }
        return result;
    }

    private void dfs(T v, Set<T> result) {
        result.add(v);
        for(T vertex: adj(v)){
            if(!result.contains(vertex)){
                dfs(vertex,result);
            }
        }
    }

    private final class AdjacentIterable implements Iterable<T> {

        private T v;

        private AdjacentIterable(T vertex){
            v = vertex;
        }

        @Override
        public Iterator<T> iterator() {
            return new VertexIterator(v);
        }
    }

    private final class VertexIterator implements Iterator<T> {

        private Iterator<T> innerIt;
        private VertexIterator(T v){
            innerIt = getAdjacentSet(v).iterator();
        }

        @Override
        public boolean hasNext() {
            return innerIt.hasNext();
        }

        @Override
        public T next() {
            return innerIt.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
