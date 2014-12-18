package com.dudu.datastructure.graph;

import com.google.common.collect.HashMultimap;

/**
 * A directed graph without parallel edge
 */
public class DirectedGraph<T> implements Graph<T> {


    private int vertices;

    private int edges;

    private HashMultimap<T,T> adj;

    /**
     * create a empty graph
     */
    public DirectedGraph(){
        adj = HashMultimap.create();
    }

    /**
     * create a empty graph with expected vertices number
     */
    public DirectedGraph(int expectedVertices,int expectedEdgePervertex){
        adj = HashMultimap.create(expectedVertices,expectedEdgePervertex);

    }




    @Override
    public int vertices() {
        return adj.keySet().size();
    }

    @Override
    public int edges() {
        return edges;
    }

    @Override
    public void addEdge(T from, T to) {

    }

    @Override
    public Iterable<T> adj(T v) {
        return null;
    }
}
