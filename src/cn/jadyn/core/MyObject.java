package cn.jadyn.core;

import java.util.List;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class MyObject extends Computer {

    private Object object;

    private Pointer[] fields;

    private int address = -1;

    public MyObject(Object object, Pointer[] fields) throws MaxMemoryException {
        this.object = object;
        this.address = memory.allocate(this);
        this.fields = fields;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public Pointer[] getFields() {
        return fields;
    }
}
