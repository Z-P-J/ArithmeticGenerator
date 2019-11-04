package com.zpj.arithmetic.base;

/**
 * 操作符抽象类
 * @author Z-P-J
 */
public abstract class Operator implements Operable {

    /**
     * 获取优先级
     * @return 优先级大小
     */
    public abstract int getPriority();

    /**
     * 判断两个操作符是否相等
     * @param operator 要比较的操作符
     * @return 是否相等
     */
    public abstract boolean equal(Operator operator);

}
