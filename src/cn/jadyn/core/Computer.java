package cn.jadyn.core;

import cn.jadyn.marksweep.MarkSweepStrategy;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class Computer {

    protected Memory memory;

    public Computer(Strategy strategy) throws WrongStrategyException {
        if (strategy == null)
            throw new WrongStrategyException();
        else
            this.memory = new Memory(strategy);
    }

    public Computer() {
        this.memory = new Memory(new MarkSweepStrategy());
    }

    public void reportMemory(){
        System.out.println(memory.getHeapPointer());
    }

}
