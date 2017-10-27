package cn.jadyn.core;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class Pointer {

    private int address = -1;
    private Computer computer;

    public Pointer(MyObject object, Computer computer) throws NoMemoryException {
        this.computer = computer;
        if(computer == null){
            throw new NoMemoryException();
        }
        if (object != null) {
            this.address = object.getAddress();
            computer.memory.addPointer(this);
        }
    }

    public int getAddress() {
        return address;
    }

    public MyObject getObject() {
        MyObject myObject;
        try {
            myObject = computer.memory.getHeap()[address];
        } catch (Exception e) {
            return null;
        }
        return myObject;
    }

    public void pointTo(int address) {
        this.address = address;
    }

    public void pointTo(MyObject object) {
        if (object != null)
            this.address = object.getAddress();
        else
            this.address = -1;
    }
}
