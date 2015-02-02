package com.dudu.datastructure;

import com.javamex.classmexer.MemoryUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tangsicheng
 * @version 1.0
 * @since 1.0
 */
public class TreiMemoryTest {


    private int dataSize = 500000;

    private int keyLength = 10;

    @Test
    public void testMemoryUsageVSHashMap() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Trie<Integer> trie = new Trie<Integer>(Alphabet.BASE64);
        for (int i = 0; i < dataSize; i++) {
                String temp = RandomStringUtils.randomAlphabetic(keyLength);
                map.put(temp,i);
                trie.put(temp,i);
        }

        System.out.printf( "memory hashMap size is %d, use %fmb: \n",map.size(),MemoryUtil.deepMemoryUsageOf(map)/1024.0/1024.0);
        System.out.printf( "memory trie size is %d, use %fmb: \n",map.size(), MemoryUtil.deepMemoryUsageOf(trie)/1024.0/1024.0);
        




    }


}
