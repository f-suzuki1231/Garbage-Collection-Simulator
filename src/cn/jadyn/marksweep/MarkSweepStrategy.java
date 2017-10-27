package cn.jadyn.marksweep;

import cn.jadyn.core.*;

import java.util.Iterator;
import java.util.List;

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
        if (pointer == null)
            return;
        MSObject msObject = (MSObject) pointer.getObject();
        if (msObject == null)
            return;
        if (!msObject.isMark()) {
            msObject.setMark(true);
            Field[] fields = msObject.getFields();
            if (fields != null) {
                for (Field field : fields) {
                    mark(field);
                }
            }
        }
    }

    private int sweep(Memory memory) {
        MyObject[] heap = memory.getHeap();
        for (int i = 0; i < memory.getHeapPointer(); i++) {
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
        //Todo: Pointer address bug
        MyObject[] newHeap = new MyObject[memory.getSize()];
        int count = 0;
        for (int i = 0; i < memory.getHeapPointer(); i++) {
            MyObject myObject = heap[i];
            if (myObject != null) {
                for (Pointer pointer : memory.getStaticArea()) {
                    if (pointer.getAddress() == i)
                        pointer.pointTo(count);
                }
                for (Field field : memory.getFieldsArea()) {
                    if (field.getAddress() == i)
                        field.pointTo(count);
                }
                myObject.setAddress(count);
                newHeap[count++] = myObject;
            }
        }
        memory.setHeap(newHeap);
        return count;
    }
}
