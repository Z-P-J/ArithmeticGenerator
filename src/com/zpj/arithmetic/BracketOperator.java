package com.zpj.arithmetic;

/**
 * 括号操作符类
 * @author Z-P-J
 */

public enum  BracketOperator implements Operator {
    /**
     *
     */
    LEFT_BRACKET("("),
    RIGHT_BRACKET(")");

    private String operator;

    BracketOperator(String operator) {
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
