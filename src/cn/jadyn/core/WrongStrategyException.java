package cn.jadyn.core;

/**
 * Created by liangjiateng on 2017/10/26.
 */
public class WrongStrategyException extends Exception {
    public WrongStrategyException() {
        super("Wrong garbage collection strategy!");
    }
}
