package cn.jadyn.copying;

import cn.jadyn.core.Field;
import cn.jadyn.core.Memory;
import cn.jadyn.core.Pointer;
import cn.jadyn.core.Strategy;

/**
 * Created by liangjiateng on 2017/10/31.
 */
public class CopyingStrategy implements Strategy {
    @Override
    public int execute(Memory memory) {
        for (Pointer pointer : memory.getStaticArea()) {
            forward(pointer, memory);
        }
        memory.setSwapped(!memory.isSwapped());
        return 1;
    }

    public void forward(Pointer p, Memory memory) {
        CPObject object = (CPObject) p.getObject();
        if (object == null)
            return;
        if (object.hasForwardAddress())
            p.pointTo(object.getForwardAddress());
        else {
            int newAddress;
            if (memory.isSwapped())
                newAddress = copy(p, 0, 64, memory);
            else
                newAddress = copy(p, 64, 128, memory);
            ((CPObject) p.getObject()).setForwardAddress(newAddress);
            for (Field field : p.getObject().getFields()) {
                forward(field, memory);
            }
            p.pointTo(newAddress);
        }
    }

    public int copy(Pointer p, int start, int end, Memory memory) {
        int heapPoint = start;
        return heapPoint;
    }

    public void sweep() {

    }
}
