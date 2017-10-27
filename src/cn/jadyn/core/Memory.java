package cn.jadyn.core;

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

    public Memory(Strategy strategy) {
        this.staticArea = new ArrayList<>();
        this.heap = new MyObject[size];
        this.gcStrategy = strategy;
        this.garbageCollector = new GarbageCollector();
        this.fieldsArea = new ArrayList<>();
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
        // heap is full, gc occur
        if (heapPointer == size) {
            System.out.println("Garbage collection start");
            heapPointer = garbageCollector.gc(this, gcStrategy);
        }
        // heap is still full, throw exception
        if (heapPointer == size)
            throw new MaxMemoryException();
        heap[heapPointer] = obj;
        return heapPointer++;
    }

    public List<Field> getFieldsArea() {
        return fieldsArea;
    }

    public void gc() {
        System.out.println("Garbage collection start");
        heapPointer = garbageCollector.gc(this, gcStrategy);
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
}
