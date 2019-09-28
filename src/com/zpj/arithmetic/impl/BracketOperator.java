package com.zpj.arithmetic.impl;

import com.zpj.arithmetic.base.Operator;

/**
 * 括号操作符类
 * @author Z-P-J
 */
public class  BracketOperator extends Operator {

    public static final BracketOperator LEFT_BRACKET = new BracketOperator("(");
    public static final BracketOperator RIGHT_BRACKET = new BracketOperator(")");

    private String operator;

    private BracketOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public boolean equal(Operator operator) {
        return operator !=null && this.getValue().equals(operator.getValue());
    }

    @Override
    public String getValue() {
        return operator;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
