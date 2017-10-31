package cn.jadyn.core;

import cn.jadyn.marksweep.MarkSweepStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class Memory {

    private int size = 128;

    private List<Pointer> staticArea;

    private List<Field> fieldsArea;

    private MyObject[] heap;

    private int heapPointer;

    private GarbageCollector garbageCollector;

    private Strategy gcStrategy;

    private boolean swapped;

    private MyObject[] from;
    private MyObject[] to;

    public boolean isSwapped() {
        return swapped;
    }

    public void setSwapped(boolean swapped) {
        this.swapped = swapped;
    }

    public Memory(Strategy strategy) {
        this.staticArea = new ArrayList<>();
        this.heap = new MyObject[size];
        this.gcStrategy = strategy;
        this.garbageCollector = new GarbageCollector();
        this.fieldsArea = new ArrayList<>();

        this.from = new MyObject[size];
        this.to = new MyObject[size];
    }

    public void addPointer(Pointer p) {
        staticArea.add(p);
    }

    public void removePointer(Pointer p) {
        staticArea.remove(p);
    }

    public void addField(Field p) {
        fieldsArea.add(p);
    }

    public void removeField(Field p) {
        fieldsArea.remove(p);
    }

    public int allocate(MyObject obj) throws MaxMemoryException {
        switch (gcStrategy.getClass().getName()) {
            case "cn.jadyn.marksweep.MarkSweepStrategy":
                // heap is full, gc occur
                if (heapPointer == size) {
                    System.out.println("Garbage collection start");
                    heapPointer = garbageCollector.gc(this, gcStrategy);
                }
                // heap is still full, throw exception
                if (heapPointer == size)
                    throw new MaxMemoryException();
                heap[heapPointer] = obj;
                break;
            case "cn.jadyn.copying.CopyingStrategy":
                // heap is full, gc occur
                if (heapPointer == size) {
                    System.out.println("Garbage collection start");
                    heapPointer = garbageCollector.gc(this, gcStrategy);
                }
                // heap is still full, throw exception
                if (heapPointer == size)
                    throw new MaxMemoryException();
                if (!isSwapped())
                    from[heapPointer] = obj;
                else
                    to[heapPointer] = obj;
                break;
        }
        return heapPointer++;
    }

    public List<Field> getFieldsArea() {
        return fieldsArea;
    }

    public void gc() {
        System.out.println("Garbage collection start");
        heapPointer = garbageCollector.gc(this, gcStrategy);
        heapPointer++;
    }

    public MyObject[] getHeap() {
        return heap;
    }

    public List<Pointer> getStaticArea() {
        return staticArea;
    }

    public int getHeapPointer() {
        return heapPointer;
    }

    public int getSize() {
        return size;
    }


    public void setHeap(MyObject[] heap) {
        this.heap = heap;
    }

    public MyObject[] getFrom() {
        return from;
    }

    public void setFrom(MyObject[] from) {
        this.from = from;
    }

    public MyObject[] getTo() {
        return to;
    }

    public void setTo(MyObject[] to) {
        this.to = to;
    }

}
