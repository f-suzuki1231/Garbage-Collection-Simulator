package cn.jadyn.marksweep;

import cn.jadyn.core.*;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class MSObject extends MyObject {

    private boolean mark;

    public MSObject(Field[] fields, Computer computer) throws MaxMemoryException, NoMemoryException {
        super(fields, computer);
    }


    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }


}
