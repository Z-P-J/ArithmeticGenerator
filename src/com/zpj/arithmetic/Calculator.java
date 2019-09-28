package com.zpj.arithmetic;

import com.zpj.arithmetic.base.Operable;
import com.zpj.arithmetic.base.Operator;
import com.zpj.arithmetic.impl.ArithmeticOperator;
import com.zpj.arithmetic.impl.BracketOperator;
import com.zpj.arithmetic.impl.IntegerOperand;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 计算器类，使用算术表达式求值算法进行计算
 * @author Z-P-J
 */
public class Calculator {

    /**
     * Operable队列
     */
    private final LinkedList<Operable> operableList = new LinkedList<>();
    /**
     * 后缀表达式队列
     */
    private final LinkedList<Operable> postfixList = new LinkedList<>();
    /**
     * 操作符栈
     */
    private final Stack<Operator> operatorStack = new Stack<>();
    /**
     * 操作数栈
     */
    private final Stack<IntegerOperand> calculateStack = new Stack<>();
    private String arithmetic = "";
    private int result;
    private boolean enableExactDivision;
    private boolean calculated = false;
    private OnCalculateListener listener;

    /**
     * 添加实现了Operable接口的可操作的对象（操作符和操作数）到队列
     * @param operable 实现了Operable接口的可操作的对象
     */
    public void addOperable(Operable operable) {
        operableList.add(operable);
        arithmetic = arithmetic + operable.getValue();
    }

    /**
     * 获取Operable队列
     * @return operableList
     */
    public LinkedList<Operable> getOperableList() {
        return operableList;
    }

    /**
     * 获取不带结果的算式字符串
     * @return 结果
     */
    public String getArithmetic() {
        return arithmetic + " = ";
    }

    /**
     * 获取带结果的算式字符串
     * @return 结果
     */
    public String getArithmeticAndResult() {
        return arithmetic + " = " + calculate();
    }

    /**
     * 根据Operable队列重新生成算式字符串
     */
    public void resetArithmetic() {
        StringBuilder builder = new StringBuilder();
        for (Operable operable : operableList) {
            builder.append(operable.getValue());
        }
        arithmetic = builder.toString();
    }

    /**
     * 设置监听器
     * @param listener 监听器
     */
    public void setListener(OnCalculateListener listener) {
        this.listener = listener;
    }

    /**
     * 设置是否整除
     * @param enableExactDivision 是否整除
     */
    public void setEnableExactDivision(boolean enableExactDivision) {
        this.enableExactDivision = enableExactDivision;
    }

    /**
     * 判断是否已计算出结果
     * @return 是否已计算出结果
     */
    public boolean isCalculated() {
        return calculated;
    }

    /**
     * 生成后缀队列
     */
    private void preparePostfixList() {
        while (!operableList.isEmpty()) {
            Operable operable = operableList.pop();
            if (operable instanceof IntegerOperand) {
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
//                System.out.println("com.zpj.arithmetic.impl.BracketOperator=" + operator);
                if (operator.equal(BracketOperator.LEFT_BRACKET)) {
//                    System.out.println("com.zpj.arithmetic.impl.BracketOperator.LEFT_BRACKET");
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

    /**
     * 将算术运算符入栈
     * @param operator 算术运算符
     */
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

    /**
     * 计算结果
     * @return 结果
     */
    public int calculate() {
        if (calculated) {
            return result;
        }
        preparePostfixList();
        while (!postfixList.isEmpty()) {
            Operable operable = postfixList.pop();
            if (operable instanceof IntegerOperand) {
                calculateStack.push((IntegerOperand) operable);
            } else if (operable instanceof ArithmeticOperator){
                IntegerOperand operableSecond = calculateStack.pop();
                IntegerOperand operableFirst = calculateStack.pop();
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
                IntegerOperand result = operator.calculate(operableFirst, operableSecond);
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
         * 中间结算时回调
         * @param intermediateValue 计算结果
         * @return 计算结果是否有效
         */
        boolean onIntermediateCal(int intermediateValue);
    }
}
