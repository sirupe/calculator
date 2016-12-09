package operation;
 
import calculator.Calc;
 
public class Numbers extends OperatorAbstract {
    @Override
    public void Calc(String num1, Calc swingTest) {
        String resultNum = swingTest.getResultNum();
        swingTest.setResultNum(resultNum.equals("0") ? num1 : resultNum + num1);
        swingTest.getResultNumLabel().setText(swingTest.getResultNum());
    }
}