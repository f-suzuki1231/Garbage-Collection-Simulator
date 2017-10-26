package cn.jadyn.core;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class Computer {

    protected Memory memory;

    public Computer() {
        this.memory = new Memory();
    }

    public void run(Task task){
        task.content();
    }
}
