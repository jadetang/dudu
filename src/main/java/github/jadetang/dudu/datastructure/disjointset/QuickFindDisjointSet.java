package github.jadetang.dudu.datastructure.disjointset;

import java.util.Set;

/**
 * Created by jadetang on 15-4-4.
 */
@Deprecated
public class QuickFindDisjointSet<T> extends AbstractQuickDisjointSet<T> {

    /**
     * construct a disjoint set from a set
     */
    public QuickFindDisjointSet(Set<T> set) {
        super(set);
    }

    @Override
    public void connect(T p, T q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId) {
            return;
        } else {
            for (int i = 0; i < id.length; i++) {
                if (id[i] == pId) {
                    id[i] = qId;
                }
            }
            count--;
        }
    }

    @Override
    public int find(T p) {
        return id[indexOf(p)];
    }

}
