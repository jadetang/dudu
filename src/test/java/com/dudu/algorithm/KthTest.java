package com.dudu.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author tangsicheng
 * @version 1.0
 * @since 1.0
 */
public class KthTest {

    private Integer[] array = randomArray(1000);
    private Integer[] orderArray = orderArrary(10);

    private Integer[] randomArray(int i) {
        Integer[] array = new Integer[i];
        Random random = new Random();
        for (int j = 0; j < i; j++) {
            array[j] = random.nextInt(i);
        }
        return array;
    }

    private Integer[] orderArrary(int size){
        Integer[] array = new Integer[size];
        for (int j = 0; j < size; j++) {
            array[j] = j;
        }
        return array;
    }

    @Test
    public void testKth() throws Exception {
        int k = 22;
        Integer result = (Integer) Kth.kth(array, k);
        Arrays.sort(array);
        Assert.assertEquals(result, array[k - 1]);
    }
}
