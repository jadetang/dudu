package github.jadetang.dudu.datastructure.disjointset;

import java.util.Set;

/**
 * Created by jadetang on 15-4-4.
 */
public class QuickUnionDisjointSet<T> extends AbstractQuickDisjointSet<T> {

    public QuickUnionDisjointSet(Set<T> set) {
        super(set);
    }

    @Override
    public void connect(T p, T q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        } else {
            id[pRoot] = qRoot;
            count--;
        }
    }

    @Override
    public int find(T p) {
        int index = id[indexOf(p)];
        while (index != id[index]) {
            index = id[index];
        }
        return index;
    }


}
