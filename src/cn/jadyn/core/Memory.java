package cn.jadyn.core;

import java.util.List;
import java.util.Stack;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class Memory {

    private int size = 128;

    private List<Pointer> staticArea;

    private MyObject[] heap;

    private int heapPointer;

    private GarbageCollector garbageCollector;

    private Strategy gcStrategy;

    public Memory(Strategy strategy) {
        this.staticArea = new Stack<>();
        this.heap = new MyObject[size];
        this.gcStrategy = strategy;
        this.garbageCollector = new GarbageCollector();
    }

    public void addPointer(Pointer p) {
        staticArea.add(p);
    }

    public void removePointer(Pointer p) {
        staticArea.remove(p);
    }

    public int allocate(MyObject obj) throws MaxMemoryException {
        // heap is full, gc occur
        if (heapPointer == size)
            heapPointer = garbageCollector.gc(this, gcStrategy);
        // heap is still full, throw exception
        if (heapPointer == size)
            throw new MaxMemoryException();
        heap[heapPointer] = obj;
        return heapPointer++;
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
