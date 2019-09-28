package com.zpj.arithmetic.impl;

import com.zpj.arithmetic.base.Operand;

/**
 * 浮点操作数
 * @author Z-P-J
 */
public class DoubleOperand extends Operand {

    private double operand;

    public DoubleOperand(double operand) {
        this.operand = operand;
    }

    public double getNum() {
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
