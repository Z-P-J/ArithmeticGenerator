import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Z-P-J
 */
public class ArithmeticGenerator {

    private static final String EQUAL = "=";
    private static final String LEFT_BRACKET = "(";
    private static final String RIGHT_BRACKET = ")";

    private final List<ArithmeticOperator> operatorList = new ArrayList<>();
    private int operandCount;
    private int generateResultCount = 10;
    private boolean limitFinalResult = false;
    private boolean limitIntermediateResult = false;
    private boolean enableBracket = false;
    private boolean enableExactDivision = false;
    private int minNum = Integer.MIN_VALUE;
    private int maxNum = Integer.MAX_VALUE;
    private int minIntermediateResult = Integer.MIN_VALUE;
    private int maxIntermediateResult = Integer.MAX_VALUE;
    private int minFinalResult = Integer.MIN_VALUE;
    private int maxFinalResult = Integer.MAX_VALUE;
    private boolean showFinalResult = false;
    private OnGenerateArithmeticListener listener;

    private final Stack<Integer> operandStack = new Stack<>();
    private final Stack<ArithmeticOperator> operatorStack = new Stack<>();


    public interface OnGenerateArithmeticListener{
        /**
         * 生成一条算式时回调
         * @param arithmetic the arithmetic formula
         */
        void onGenerate(String arithmetic);
    }

    private ArithmeticGenerator() {

    }

    public static ArithmeticGenerator with() {
        return new ArithmeticGenerator();
    }

    public ArithmeticGenerator setOperandCount(int operandCount) {
        if (operandCount < 2) {
            throw new RuntimeException("运算数个数不能低于2个");
        }
        this.operandCount = operandCount;
        return this;
    }

    public ArithmeticGenerator setGenerateResultCount(int generateResultCount) {
        this.generateResultCount = generateResultCount;
        return this;
    }

    /**
     * 算式中是否可以包含括号
     * @param enableBracket
     * @return this
     */
    public ArithmeticGenerator setEnableBracket(boolean enableBracket) {
        this.enableBracket = enableBracket;
        return this;
    }

    public ArithmeticGenerator setEnableExactDivision(boolean enableExactDivision) {
        this.enableExactDivision = enableExactDivision;
        return this;
    }

    /**
     *
     * @param minNum
     * @param maxNum
     * @return this
     */
    public ArithmeticGenerator setNumRange(int minNum, int maxNum) {
        this.minNum = minNum;
        this.maxNum = maxNum;
        return this;
    }

    /**
     *
     * @param minIntermediateResult
     * @param maxIntermediateResult
     * @return this
     */
    public ArithmeticGenerator setIntermediateResultRange(int minIntermediateResult, int maxIntermediateResult) {
        limitIntermediateResult = true;
        this.minIntermediateResult = minIntermediateResult;
        this.maxIntermediateResult = maxIntermediateResult;
        return this;
    }

    /**
     *
     * @param minFinalResult
     * @param maxFinalResult
     * @return this
     */
    public ArithmeticGenerator setFinalResultRange(int minFinalResult, int maxFinalResult) {
        limitFinalResult = true;
        this.minFinalResult = minFinalResult;
        this.maxFinalResult = maxFinalResult;
        return this;
    }

    /**
     *
     * @param showFinalResult
     * @return this
     */
    public ArithmeticGenerator setShowFinalResult(boolean showFinalResult) {
        this.showFinalResult = showFinalResult;
        return this;
    }

    /**
     * 添加运算符
     * @param operator
     * @return this
     */
    public ArithmeticGenerator addOperator(ArithmeticOperator operator) {
        if (!this.operatorList.contains(operator)) {
            this.operatorList.add(operator);
        }
        return this;
    }

    /**
     *
     * @param operators
     * @return this
     */
    public ArithmeticGenerator addOperators(List<ArithmeticOperator> operators) {
        for (ArithmeticOperator operator : operators) {
            addOperator(operator);
        }
        return this;
    }

    public ArithmeticGenerator setListener(OnGenerateArithmeticListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * 启动算式生成器
     */
    public void start() {
        System.out.println("算式生成器启动");
        if (operatorList.isEmpty()) {
            throw new RuntimeException("请添加算术运算符");
        }
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < generateResultCount; i++) {
            if (listener != null) {
                listener.onGenerate(generateArithmetic());
            }
        }

        long finishTime = System.currentTimeMillis();
        System.out.println("算式生成完毕，共花费：" + (finishTime - startTime) + "ms");
    }

    /**
     *
     * @return a random number
     */
    private int getNum() {
        return (int) (Math.random() * (maxNum));
    }

    /**
     *
     * @return a random arithmetic operator
     */
    private ArithmeticOperator getOperator() {
        return operatorList.get((int) (Math.random() * operatorList.size()));
    }

    private String generateArithmetic() {
        String arithmetic = "";
        if (limitFinalResult && limitIntermediateResult) {
            //todo
        } else if (limitIntermediateResult) {
            //todo
        } else if (limitFinalResult) {
            //todo
        } else {
            arithmetic = getNum() + getOperator().toString() + getNum() + getOperator().toString() + getNum() + EQUAL;
        }
        if (showFinalResult) {
            //todo
        }
        return arithmetic;
    }

}
