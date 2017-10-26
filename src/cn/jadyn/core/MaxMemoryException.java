package cn.jadyn.core;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class MaxMemoryException extends Exception {

    private static final String msg = "Memory overflow!";

    public MaxMemoryException() {
        super(msg);
    }
}
