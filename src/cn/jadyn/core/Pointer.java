package cn.jadyn.core;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class Pointer extends Computer {

    private int address;

    private MyObject object;

    public Pointer(MyObject object) {
        if (object != null) {
            this.object = object;
            address = object.getAddress();
        }
    }

    public int getAddress() {
        return address;
    }

    public MyObject getObject() {
        return object;
    }

    public void pointTo(MyObject object) {
        this.object = object;
        if (object == null)
            this.address = -1;
        else
            this.address = object.getAddress();
    }
}
