package com.zpj.arithmetic;

/**
 * 操作数
 * @author Z-P-J
 */
public class ArithmeticOperand implements Operable {

    private int operand;

    public ArithmeticOperand(int operand) {
        this.operand = operand;
    }

    public int getNum() {
        return operand;
    }

    @Override
    public String getValue() {
        if (operand >= 0) {
            return toString();
        } else {
            return "(" + operand + ")";
        }
    }

    @Override
    public String toString() {
        return String.valueOf(operand);
    }
}
