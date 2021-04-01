package com.jvm;

import java.util.HashSet;
import java.util.Set;

/**
 * -Xms64m -Xmx64m
 * -XX:+PrintHeapAtGC
 * -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author zhangqiang
 * @since 2021/4/1 13:44
 */
public class JVMCommonOptions {

    public static void main(String[] args) {
        int capacity = 10000000;
        Set<Long> set = new HashSet<Long>(capacity);
        for (long i = 0L; i < capacity; i++) {
            set.add(i);
        }
        assert set.contains(1L);
    }

}
