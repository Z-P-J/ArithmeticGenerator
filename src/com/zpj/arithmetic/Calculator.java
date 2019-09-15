package com.zpj.arithmetic;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 计算器类，使用算术表达式求值算法进行计算
 * @author Z-P-J
 */
public class Calculator {

    private final LinkedList<Operable> operableList = new LinkedList<>();
    private final LinkedList<Operable> postfixList = new LinkedList<>();
    private final Stack<Operator> operatorStack = new Stack<>();
    private final Stack<ArithmeticOperand> calculateStack = new Stack<>();
    private String arithmetic = "";
    private int result;
    private boolean enableExactDivision;
    private boolean calculated = false;
    private OnCalculateListener listener;

    public void addOperable(Operable operable) {
        operableList.add(operable);
        arithmetic = arithmetic + operable.getValue();
    }

    public LinkedList<Operable> getOperableList() {
        return operableList;
    }

    public String getArithmetic() {
        return arithmetic + "=";
    }

    public String getArithmeticAndResult() {
        return arithmetic + "=" + calculate();
    }

    public void resetArithmetic() {
        StringBuilder builder = new StringBuilder();
        for (Operable operable : operableList) {
            builder.append(operable.getValue());
        }
        arithmetic = builder.toString();
//        System.out.println("arithmetic=" + arithmetic);
    }

    public void setListener(OnCalculateListener listener) {
        this.listener = listener;
    }

    public void setEnableExactDivision(boolean enableExactDivision) {
        this.enableExactDivision = enableExactDivision;
    }

    public boolean isCalculated() {
        return calculated;
    }

    private void preparePostfixList() {
        while (!operableList.isEmpty()) {
            Operable operable = operableList.pop();
            if (operable instanceof ArithmeticOperand) {
                postfixList.add(operable);
            } else if (operable instanceof ArithmeticOperator) {
                ArithmeticOperator operator = (ArithmeticOperator) operable;
                if (operatorStack.contains(BracketOperator.LEFT_BRACKET)) {
                    operatorStack.push(operator);
                } else {
                    pushToStack(operator);
                }
//                System.out.println("pushToStack1111=" + operatorStack);
//                pushToStack(operator);
//                System.out.println("pushToStack222=" + operatorStack);
            } else if (operable instanceof BracketOperator) {
                BracketOperator operator = (BracketOperator) operable;
//                System.out.println("com.zpj.arithmetic.BracketOperator=" + operator);
                if (operator.equal(BracketOperator.LEFT_BRACKET)) {
//                    System.out.println("com.zpj.arithmetic.BracketOperator.LEFT_BRACKET");
                    operatorStack.push(operator);
//                    System.out.println("111operatorStack=" + operatorStack);
                } else {
                    while (true) {
//                        System.out.println("operatorStack=" + operatorStack);
                        Operator popOperator = operatorStack.pop();
                        if (popOperator instanceof ArithmeticOperator) {
                            postfixList.add(popOperator);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        while (!operatorStack.isEmpty()) {
            Operator topOperator = operatorStack.pop();
            postfixList.add(topOperator);
        }
    }

    private void pushToStack(ArithmeticOperator operator) {
        if (operatorStack.isEmpty()) {
            operatorStack.push(operator);
            return;
        }
        if (operator.getPriority() > operatorStack.peek().getPriority()) {
            operatorStack.push(operator);
        } else {
            postfixList.add(operatorStack.pop());
            pushToStack(operator);
        }
    }

    public int calculate() {
        if (calculated) {
            return result;
        }
        preparePostfixList();
        while (!postfixList.isEmpty()) {
            Operable operable = postfixList.pop();
            if (operable instanceof ArithmeticOperand) {
                calculateStack.push((ArithmeticOperand) operable);
            } else if (operable instanceof ArithmeticOperator){
                ArithmeticOperand operableSecond = calculateStack.pop();
                ArithmeticOperand operableFirst = calculateStack.pop();
                ArithmeticOperator operator = (ArithmeticOperator) operable;
                if (operator.equal(ArithmeticOperator.DIVISION) || operator.equal(ArithmeticOperator.MOD)) {
                    if (operableSecond.getNum() == 0) {
                        return 0;
                    }
                }
                if (enableExactDivision && operator.equal(ArithmeticOperator.DIVISION)) {
                    if (operableFirst.getNum() % operableSecond.getNum() != 0) {
                        return 0;
                    }
                }
                ArithmeticOperand result = operator.calculate(operableFirst, operableSecond);
                calculateStack.push(result);
                if (!calculateStack.isEmpty() && listener != null) {
                    if (listener.onIntermediateCal(result.getNum())) {
                        return 0;
                    }
                }
            }
        }
        result = calculateStack.pop().getNum();
        calculated = true;
        return result;
    }

    public interface OnCalculateListener{
        /**
         *
         * @param intermediateValue
         * @return
         */
        boolean onIntermediateCal(int intermediateValue);
    }
}
