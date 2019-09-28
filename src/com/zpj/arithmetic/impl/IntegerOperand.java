package com.zpj.arithmetic.impl;

import com.zpj.arithmetic.base.Operand;

/**
 * 整型操作数
 * @author Z-P-J
 */
public class IntegerOperand extends Operand {

    private int operand;

    public IntegerOperand(int operand) {
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
