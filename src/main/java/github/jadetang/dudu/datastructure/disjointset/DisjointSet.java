package github.jadetang.dudu.datastructure.disjointset;

/**
 * Created by jadetang on 15-4-4.
 */
public interface DisjointSet<T> {

    /**
     * add connection between p and q
     *
     * @param p
     * @param q
     */
    public void connect(T p, T q);

    /**
     * component identifier for p (0 to N-1)
     *
     * @param p
     * @return
     */
    public int find(T p);

    /**
     * return true if p and q are in the same component
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isConnect(T p, T q);

    /**
     * number of components
     *
     * @return
     */
    public int count();


    /**
     * add an element to the disjoint set throw IllegalArgumentException if the element is already in the set
     */
    public void add(T t);

}
