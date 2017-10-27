package cn.jadyn.core;

/**
 * Created by liangjiateng on 2017/10/27.
 */
public class Field extends Pointer{


    public Field(MyObject object, Computer computer) throws NoMemoryException {
        this.computer = computer;
        computer.memory.addField(this);
        if (object != null) {
            this.address = object.getAddress();
        }
    }


}
