package com.zpj.arithmetic;

import com.zpj.arithmetic.base.Operand;
import com.zpj.arithmetic.base.Operator;
import com.zpj.arithmetic.impl.ArithmeticOperator;
import com.zpj.arithmetic.impl.IntegerOperand;

/**
 * @author Z-P-J
 */
public class BinaryArithmeticNode {

    private ArithmeticOperator operator = null;
    private IntegerOperand operand = null;

    private BinaryArithmeticNode parentNode = null;
    private BinaryArithmeticNode leftNode = null;
    private BinaryArithmeticNode rightNode = null;

    public BinaryArithmeticNode() {
    }

    public BinaryArithmeticNode(IntegerOperand operand, ArithmeticOperator operator) {
        this.operand = operand;
        this.operator = operator;
    }

    public void setOperator(ArithmeticOperator operator) {
        this.operator = operator;
    }

    public void setOperand(IntegerOperand operand) {
        this.operand = operand;
    }

    public void setParentNode(BinaryArithmeticNode parentNode) {
        this.parentNode = parentNode;
    }

    public void setLeftNode(BinaryArithmeticNode leftNode) {
        leftNode.setParentNode(this);
        this.leftNode = leftNode;
    }

    public void setRightNode(BinaryArithmeticNode rightNode) {
        rightNode.setParentNode(this);
        this.rightNode = rightNode;
    }

    public ArithmeticOperator getOperator() {
        return operator;
    }

    public IntegerOperand getOperand() {
        return operand;
    }

    public BinaryArithmeticNode getParentNode() {
        return parentNode;
    }

    public BinaryArithmeticNode getLeftNode() {
        return leftNode;
    }

    public BinaryArithmeticNode getRightNode() {
        return rightNode;
    }
}
