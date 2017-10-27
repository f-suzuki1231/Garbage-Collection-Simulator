package cn.jadyn.marksweep;
import cn.jadyn.core.Pointer;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class MSPointer extends Pointer {

    public MSPointer(MSObject object) {
        super(object);
        if (object != null) {
            object.setMark(true);
        }
    }

    public void pointTo(int address) {
        super.pointTo(address);
    }

    @Override
    public MSObject getObject() {
        return (MSObject) super.getObject();
    }


}
