package cn.jadyn.core;

import java.util.List;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class MyObject {

    private Pointer[] fields;

    private int address = -1;

    private Computer computer;

    public MyObject(Pointer[] fields, Computer computer) throws MaxMemoryException, NoMemoryException {
        if (computer == null)
            throw new NoMemoryException();
        this.address = computer.memory.allocate(this);
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
