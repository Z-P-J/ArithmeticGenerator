package com.zpj.arithmetic;

import com.zpj.arithmetic.base.Operand;
import com.zpj.arithmetic.base.Operator;
import com.zpj.arithmetic.impl.ArithmeticOperator;
import com.zpj.arithmetic.impl.BracketOperator;
import com.zpj.arithmetic.impl.IntegerOperand;

import java.util.LinkedList;

/**
 * @author Z-P-J
 */
public class BinaryArithmeticTree {

    private BinaryArithmeticNode root = null;

    public BinaryArithmeticNode getRoot() {
        return root;
    }

    public void setRoot(BinaryArithmeticNode root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public String getArithmetic() {
        return inOrder(root);
    }

    private String inOrder(BinaryArithmeticNode currentNode) {
        if (currentNode != null) {
            if (currentNode.getLeftNode() == null && currentNode.getRightNode() == null) {
                return currentNode.getOperand().toString();
            } else {
                String left = inOrder(currentNode.getLeftNode());
                String middle = currentNode.getOperator().toString();
                String right = inOrder(currentNode.getRightNode());
//                if (currentNode.getOperator() == ArithmeticOperator.ADDITION
//                        || currentNode.getOperator() == ArithmeticOperator.SUBTRACTION) {
//
//                } else {
//                    return left + middle + right;
//                }
                return BracketOperator.LEFT_BRACKET.toString() + left + middle + right + BracketOperator.RIGHT_BRACKET.toString();
            }
        }
        return "";
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        BinaryArithmeticTree tree = new BinaryArithmeticTree();
        BinaryArithmeticNode root = new BinaryArithmeticNode(new IntegerOperand(10), ArithmeticOperator.ADDITION);
        tree.setRoot(root);

        int count = 1;
        LinkedList<BinaryArithmeticNode> queue = new LinkedList<>();
        queue.push(root);
        while (count != 10) {
            BinaryArithmeticNode currentNode = queue.pop();
            BinaryArithmeticNode left = new BinaryArithmeticNode(new IntegerOperand(10), ArithmeticOperator.ADDITION);
            BinaryArithmeticNode right = new BinaryArithmeticNode(new IntegerOperand(10), ArithmeticOperator.ADDITION);
            currentNode.setLeftNode(left);
            currentNode.setRightNode(right);
            queue.push(left);
            queue.push(right);
            count++;
        }

        System.out.println(tree.getArithmetic());
    }
}
