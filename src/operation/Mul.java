package operation;
 
import calculator.Calc;
 
public class Mul extends OperatorAbstract {
    @Override
    public void Calc(Calc swingTest) {
        String resultS;
        String doNum = swingTest.getDoNum();
        String resultNum = swingTest.getResultNum();
        if(doNum.contains(".") || resultNum.contains(".")) {
            Double resultD = Double.parseDouble(doNum) * Double.parseDouble(resultNum);
            resultS = resultD.toString();
        } else {
            Integer resultI = Integer.parseInt(doNum) * Integer.parseInt(resultNum);
            resultS = resultI.toString();
        }
        swingTest.setResultNum(resultS);
        
    }    
}
