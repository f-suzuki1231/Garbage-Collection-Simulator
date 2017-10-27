package cn.jadyn.core;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class Pointer extends Computer {

    private int address = -1;

    public Pointer(MyObject object) {
        if (object != null) {
            this.address = object.getAddress();
            memory.addPointer(this);
        }
    }

    public int getAddress() {
        return address;
    }

    public MyObject getObject() {
        return memory.getHeap()[address];
    }

    protected void pointTo(int address) {
        this.address = address;
    }

    protected void pointTo(MyObject object) {
        if (object != null)
            this.address = object.getAddress();
        else
            this.address = -1;
    }
}
