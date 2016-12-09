package operation;
 
import calculator.Calc;
 
public class BackSpace extends OperatorAbstract {
    @Override
    public void Calc(Calc swingTest) {
        String resultNum = swingTest.getResultNum();
        if(resultNum.length() > 1) {
            swingTest.setResultNum(resultNum.substring(0, resultNum.length() - 1));
            swingTest.getResultNumLabel().setText(swingTest.getResultNum());
        } else {
            swingTest.setResultNum("0");
            swingTest.getResultNumLabel().setText(swingTest.getResultNum());
        }
    }
}
