package github.jadetang.dudu.datastructure.disjointset;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by jadetang on 15-4-4.
 */
public class WeightedDisjointSet<T> extends AbstractQuickDisjointSet<T> {

    private int[]size;


    public WeightedDisjointSet(Set<T> set) {
        super(set);
        size = new int[set.size()];
        Arrays.fill(size,1);
    }

    @Override
    public void connect(T p, T q) {
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ ){
            return;
        }else {
            if(size[rootP]<size[rootQ]){
                id[rootP] = rootQ;
                size[rootP] += size[rootQ];
            }else{
                id[rootQ] = rootP;
                size[rootQ] += size[rootP];
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
        while (index != id[index]){
            tempList.add(index);
            index = id[index];
        }
        for(Integer i : tempList){
            id[i] = index;
        }
        return index;
    }
}
