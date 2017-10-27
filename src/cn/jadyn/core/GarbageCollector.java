package cn.jadyn.core;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class GarbageCollector {

    public int gc(Memory memory, Strategy strategy) {
        return strategy.execute(memory);
    }
}
