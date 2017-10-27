package cn.jadyn.marksweep;

import cn.jadyn.core.MaxMemoryException;
import cn.jadyn.core.MyObject;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class MSObject extends MyObject {

    private boolean mark;

    public MSObject(Object object, MSPointer[] fields) throws MaxMemoryException {
        super(object,fields);
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    @Override
    public MSPointer[] getFields() {
        return (MSPointer[]) super.getFields();
    }
}
