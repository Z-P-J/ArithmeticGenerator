/**
 * 算式运算符
 * @author Z-P-J
 */
public enum ArithmeticOperator {

    /**
     *
     */
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("÷");

    private String operator;

    ArithmeticOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator;
    }

    public boolean isAdd() {
        return this.equal(ADDITION);
    }

    public boolean isSub() {
        return this.equal(SUBTRACTION);
    }

    public boolean isMulti() {
        return this.equal(MULTIPLICATION);
    }

    public boolean isDiv() {
        return this.equal(DIVISION);
    }

    public boolean equal(ArithmeticOperator operator){
        return operator !=null && this.toString().equals(operator.toString());
    }

}
