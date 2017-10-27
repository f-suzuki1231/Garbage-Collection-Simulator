package cn.jadyn.marksweep;

import cn.jadyn.core.Memory;
import cn.jadyn.core.MyObject;
import cn.jadyn.core.Pointer;
import cn.jadyn.core.Strategy;

import java.util.Iterator;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class MarkSweepStrategy implements Strategy {


    @Override
    public int execute(Memory memory) {
        Iterator iterator = memory.getStaticArea().iterator();
        while (iterator.hasNext()) {
            Pointer p = (Pointer) iterator.next();
            mark(p);
        }
        return sweep(memory);
    }

    private void mark(Pointer pointer) {
        MSObject msObject = (MSObject) pointer.getObject();
        if (msObject == null)
            return;
        if (!msObject.isMark()) {
            msObject.setMark(true);
            Pointer[] fields = msObject.getFields();
            if (fields != null) {
                for (Pointer field : fields) {
                    mark(field);
                }
            }
        }
    }

    private int sweep(Memory memory) {
        MyObject[] heap = memory.getHeap();
        for (int i = 0; i < heap.length; i++) {
            MSObject msObject = (MSObject) heap[i];
            if (msObject.isMark())
                msObject.setMark(false);
            else
                heap[i] = null;
        }
        return compact(memory, heap);
    }

    private int compact(Memory memory, MyObject[] heap) {
        // compact
        MyObject[] newHeap = new MyObject[memory.getSize()];
        int count = 0;
        for (MyObject myObject : heap) {
            if (myObject != null) {
                newHeap[count++] = myObject;
                myObject.setAddress(count);
            }

        }
        memory.setHeap(newHeap);
        return count;
    }
}
