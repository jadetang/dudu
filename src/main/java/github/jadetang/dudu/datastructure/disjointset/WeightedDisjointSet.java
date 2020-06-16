package github.jadetang.dudu.datastructure.disjointset;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by jadetang on 15-4-4.
 */
public class WeightedDisjointSet<T> extends AbstractQuickDisjointSet<T> {

    private int[] sz;

    public WeightedDisjointSet() {
        super();
        sz = new int[DEFAULT_SIZE + 1];
        Arrays.fill(sz, 0);
    }


    public WeightedDisjointSet(Set<T> set) {
        super(set);
        sz = new int[set.size() + 1];
        Arrays.fill(sz, 1);
        sz[0] = 0;
    }

    @Override
    public void connect(T p, T q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        } else {
            if (sz[rootP] < sz[rootQ]) {
                id[rootP] = rootQ;
                sz[rootP] += sz[rootQ];
            } else {
                id[rootQ] = rootP;
                sz[rootQ] += sz[rootP];
            }
            count--;
        }

    }

    @Override
    public int find(T p) {
        int index = id[indexOf(p)];
        //save all the index along and make all the id[index] to the root
        //after finding the root.
        List<Integer> tempList = new LinkedList<>();
        while (index != id[index]) {
            tempList.add(index);
            index = id[index];
        }
        for (Integer i : tempList) {
            id[i] = index;
        }
        return index;
    }

    @Override
    public void add(T t) {
        super.add(t);
        if (size > sz.length - 1) {
            sz = Arrays.copyOf(sz, sz.length * 2 - 1);
        }
        sz[size] = 1;
    }
}
