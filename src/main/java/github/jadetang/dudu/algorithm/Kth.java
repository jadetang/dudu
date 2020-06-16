package github.jadetang.dudu.algorithm;

import java.util.Random;

/**
 * @author tangsicheng
 * @version 1.0
 * @since 1.0
 */
public class Kth {

    private Kth() {
    }

    /**
     * find the kth smallest element of a array,return null if the param k is out of the arrange of array
     */
    public static Comparable kth(Comparable[] array, int k) {
        k--;
        shuffle(array);
        int lo = 0;
        int hi = array.length - 1;
        while (hi > lo) {
            int j = partition(array, lo, hi);
            if (j == k) {
                return array[k];
            } else if (j > k) {
                hi = j - 1;
            } else if (j < k) {
                lo = j + 1;
            }
        }
        return array[k];
    }

    /**
     * patition a array
     *
     * @param array
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(Comparable[] array, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = array[lo];
        while (true) {
            while (less(array[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, array[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exchange(array, i, j);
        }
        exchange(array, lo, j);
        return j;
    }

    private static void exchange(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static boolean less(Comparable o1, Comparable o2) {
        int cmp = o1.compareTo(o2);
        return cmp < 0;
    }


    /**
     * shuffle a array
     *
     * @param array
     */

    private static void shuffle(Object[] array) {
        int N = array.length;
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            int r = i + random.nextInt(N - i);
            Object temp = array[i];
            array[i] = array[r];
            array[r] = temp;
        }
    }


}
