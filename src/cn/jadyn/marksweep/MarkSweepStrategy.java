package cn.jadyn.marksweep;

import cn.jadyn.core.Memory;
import cn.jadyn.core.MyObject;
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
            MSPointer p = (MSPointer) iterator.next();
            mark(p);
        }
        return sweep(memory);
    }

    private void mark(MSPointer pointer) {
        if (!pointer.getObject().isMark()) {
            pointer.getObject().setMark(true);
            MSPointer[] fields = pointer.getObject().getFields();
            if (fields != null){
                for (MSPointer field : fields){
                    mark(field);
                }
            }
        }
    }

    private int sweep(Memory memory) {
        MSObject[] heap = (MSObject[]) memory.getHeap();
        for (int i = 0; i < heap.length; i++) {
            if (heap[i].isMark())
                heap[i].setMark(false);
            else
                heap[i] = null;
        }
        // compact
        MyObject[] newHeap = new MyObject[memory.getSize()];
        int count = 0;
        for (MSObject msObject : heap) {
            if (msObject != null) {
                newHeap[count++] = msObject;
                msObject.setAddress(count);
            }

        }
        memory.setHeap(newHeap);
        return count;
    }
}
