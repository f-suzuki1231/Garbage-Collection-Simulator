package cn.jadyn;

import cn.jadyn.core.*;
import cn.jadyn.marksweep.MSObject;

public class Main {

    public static void main(String[] args) throws MaxMemoryException, NoMemoryException {
        Computer computer = new Computer();
        Pointer p1 = computer.createPointer(null);
        MSObject obj1 = (MSObject) computer.createObject(MSObject.class, null);
        p1.pointTo(obj1);
        MSObject obj2 = (MSObject) computer.createObject(MSObject.class, null);
        Pointer p3 = computer.createPointer(null);
        MSObject obj3 = (MSObject) computer.createObject(MSObject.class, null);
        p3.pointTo(obj3);
        MSObject obj4 = (MSObject) computer.createObject(MSObject.class, null);
        Field[] fields =new Field[1];
        Field name = computer.createField(obj4);
        fields[0] = name;
        obj1.setFields(fields);

        computer.gc();
        computer.reportMemory();

    }
}
