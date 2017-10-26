package cn.jadyn.core;
import java.util.Stack;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class Memory {

    private int size = 128;

    private Stack<Pointer> staticArea;

    private MyObject[] heap;

    private int heapPointer;

    private GarbageCollector garbageCollector;

    public Memory() {
        this.staticArea = new Stack<>();
        this.heap = new MyObject[size];
    }

    public void addPointer(Pointer p) {
        staticArea.push(p);
    }

    public int allocate(MyObject obj) throws MaxMemoryException {
        // heap is full, gc occur
        if (heapPointer == size)
            heapPointer = garbageCollector.gc(heap, heapPointer);
        // heap is still full, enlarge capacity
        if (heapPointer == size)
            throw new MaxMemoryException();
        heap[heapPointer] = obj;
        return heapPointer++;
    }
}
