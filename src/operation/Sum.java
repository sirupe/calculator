package operation;
 
import calculator.Calc;
 
public class Sum extends OperatorAbstract {
    @Override
    public void Calc(Calc swingTest) {
        String num1 = swingTest.getDoNum();
        String num2 = swingTest.getResultNum();
        String resultS;
        if(num1.contains(".") || num2.contains(".")) {
            Double resultD = Double.parseDouble(num1) + Double.parseDouble(num2);
            resultS = resultD.toString();
        } else {
            Integer resultI = Integer.parseInt(num1) + Integer.parseInt(num2);
            resultS = resultI.toString();
        }
        
        swingTest.setResultNum(resultS);
    }
}
