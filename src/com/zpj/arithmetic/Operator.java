package com.zpj.arithmetic;

/**
 * 操作符接口
 * @author Z-P-J
 */
public interface Operator extends Operable {

    int getPriority();

    boolean equal(Operator operator);

}
