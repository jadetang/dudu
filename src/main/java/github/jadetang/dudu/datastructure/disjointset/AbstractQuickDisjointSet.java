package github.jadetang.dudu.datastructure.disjointset;

import com.google.common.base.Optional;
import com.google.common.collect.*;

import java.util.*;

/**
 * Created by jadetang on 15-4-4.
 */
public abstract class AbstractQuickDisjointSet<T> implements DisjointSet<T> {


    protected int id[];
    protected Map<T, Integer> indexMap = new HashMap();
    protected int count= 0;
    protected int size = 0;
    protected static int DEFAULT_SIZE = 10;

    public AbstractQuickDisjointSet(Set<T> set) {
        Set<T> tempSet = new HashSet<>(set);
        count = tempSet.size();
        size = tempSet.size();
        id = new int[tempSet.size() + 1];
        int index = 1;
        for (T t : tempSet) {
            id[index] = index;
            indexMap.put(t, index);
            index++;
        }
    }

    public AbstractQuickDisjointSet(){
        id = new int[DEFAULT_SIZE+1];
    }

    @Override
    public boolean isConnect(T p, T q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }


    @Override
    public void add(T t) {
        if (indexMap.keySet().contains(t)) {
            throw new IllegalArgumentException("the element " + t + " is already in the set");
        } else {
            if (isFull()) {
                resize();
            }
            indexMap.put(t,++size);
            id[size] = size;
            count++;
        }
    }

    private boolean isFull() {
        return size == id.length -1 ;
    }

    private void resize() {
        id = Arrays.copyOf(id, id.length * 2 - 1);
    }


    protected int indexOf(T p) {
        Integer index = indexMap.get(p);
        return index != null ? index : -1;
    }

    public List<Set<T>> collect() {
        Map<Integer, Set<T>> collector = new HashMap<>();
        for (T key : indexMap.keySet()) {
            int index = find(key);
            Optional<Set<T>> set = Optional.fromNullable(collector.get(index));
            if (set.isPresent()) {
                set.get().add(key);
            } else {
                Set<T> temp = Sets.newHashSet();
                temp.add(key);
                collector.put(index, temp);
            }
        }
        return Lists.newLinkedList(collector.values());
    }

    @Override
    public String toString() {
        return collect().toString();
    }


}
