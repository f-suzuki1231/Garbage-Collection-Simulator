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
            Pointer pointer = new Pointer(new MSObject(null, computer), computer);
            if (i % 3 == 0)
                pointer.pointTo(-1);
        }
        MSObject triger = new MSObject(null, computer);

        computer.reportMemory();

    }
}
