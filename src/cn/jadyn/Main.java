package cn.jadyn;

import cn.jadyn.core.Computer;
import cn.jadyn.core.MaxMemoryException;
import cn.jadyn.core.NoMemoryException;
import cn.jadyn.core.Pointer;
import cn.jadyn.marksweep.MSObject;

public class Main {

    public static void main(String[] args) throws MaxMemoryException, NoMemoryException {
        Computer computer = new Computer();
        for (int i = 0; i < 128; i++) {
            Pointer[] fields = new Pointer[2];
            MSObject namestr = (MSObject) computer.createObject(MSObject.class, null);
            Pointer name = computer.createPointer(namestr);
            MSObject ageint = (MSObject) computer.createObject(MSObject.class, null);
            Pointer age = computer.createPointer(ageint);
            fields[0] = name;
            fields[1] = age;
            MSObject msObject = (MSObject) computer.createObject(MSObject.class, fields);
            Pointer pointer = computer.createPointer(msObject);
            if (i % 3 == 0)
                pointer.pointTo(-1);
        }


        computer.reportMemory();

    }
}
