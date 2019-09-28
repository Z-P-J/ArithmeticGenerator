package com.zpj.arithmetic.impl;

import com.zpj.arithmetic.base.Operator;

/**
 * 算术运算符
 * @author Z-P-J
 */
public class ArithmeticOperator extends Operator {

    public static final ArithmeticOperator ADDITION = new ArithmeticOperator(" + ", 1);
    public static final ArithmeticOperator SUBTRACTION = new ArithmeticOperator(" - ", 1);
    public static final ArithmeticOperator MULTIPLICATION = new ArithmeticOperator(" × ", 2);
    public static final ArithmeticOperator DIVISION = new ArithmeticOperator(" ÷ ", 2);
    public static final ArithmeticOperator MOD = new ArithmeticOperator(" mod ", 2);
//    public static final ArithmeticOperator ADDITION = new ArithmeticOperator("+", 1);

    private String operator;
    private int priority;

    private ArithmeticOperator(String operator, int priority) {
        this.operator = operator;
        this.priority = priority;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equal(Operator operator) {
        return operator !=null && this.getValue().equals(operator.getValue());
    }

    @Override
    public String toString() {
        return getValue();
    }

    /**
     * 根据操作符计算两个操作数
     * @param a1 操作数1
     * @param a2 操作数2
     * @return 计算结果
     */
    public IntegerOperand calculate(IntegerOperand a1, IntegerOperand a2) {
        int result = 0;
        if (this.equal(ADDITION)) {
            result = a1.getNum() + a2.getNum();
        } else if (this.equal(SUBTRACTION)) {
            result = a1.getNum() - a2.getNum();
        } else if (this.equal(MULTIPLICATION)) {
            result = a1.getNum() * a2.getNum();
        } else if (this.equal(DIVISION)) {
            result = a1.getNum() / a2.getNum();
        } else if (this.equal(MOD)) {
            result = a1.getNum() % a2.getNum();
        }
        return new IntegerOperand(result);
    }

    @Override
    public String getValue() {
        return operator;
    }
}
