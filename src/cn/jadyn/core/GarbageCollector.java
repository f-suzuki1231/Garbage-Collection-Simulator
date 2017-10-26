package cn.jadyn.core;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public interface GarbageCollector {

    int gc(MyObject[] heap, int heapPointer);
}
