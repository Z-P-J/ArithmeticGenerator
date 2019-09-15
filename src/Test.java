public class Test {

    public static void main(String[] args) {
        ArithmeticGenerator.with()
                .setOperandCount(3)
                .setGenerateResultCount(300)
                .setNumRange(0, 100)
//                .setFinalResultRange(0, 100)
//                .setIntermediateResultRange(0, 100)
                .setShowFinalResult(true)
                .setEnableBracket(true)
                .addOperator(ArithmeticOperator.ADDITION)
                .addOperator(ArithmeticOperator.SUBTRACTION)
                .addOperator(ArithmeticOperator.MULTIPLICATION)
                .addOperator(ArithmeticOperator.DIVISION)
                .setListener(new ArithmeticGenerator.OnGenerateArithmeticListener() {
                    @Override
                    public void onGenerate(String arithmetic) {
                        System.out.println(arithmetic);
                    }
                })
                .start();
    }

}
