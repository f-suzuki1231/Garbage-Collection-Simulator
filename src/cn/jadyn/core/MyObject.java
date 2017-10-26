package cn.jadyn.core;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class MyObject extends Computer {

    private Object object;

    private boolean mark;

    private int address = -1; // -1 represents null

    public MyObject(Object object) throws MaxMemoryException {
        this.object = object;
        this.address = memory.allocate(this);
    }

    public int getAddress() {
        return address;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }
}
