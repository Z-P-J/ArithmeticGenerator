package com.zpj.arithmetic;

/**
 * 算式运算符
 * @author Z-P-J
 */
public enum ArithmeticOperator implements Operator {

    /**
     *
     */
    ADDITION("+", 1),
    SUBTRACTION("-", 1),
    MULTIPLICATION("*", 2),
    DIVISION("÷", 2),
    MOD("%", 2);

    private String operator;
    private int priority;

    ArithmeticOperator(String operator, int priority) {
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

    public ArithmeticOperand calculate(ArithmeticOperand a1, ArithmeticOperand a2) {
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
        return new ArithmeticOperand(result);
    }

    @Override
    public String getValue() {
        return operator;
    }
}
