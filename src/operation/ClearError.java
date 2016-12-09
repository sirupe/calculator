package operation;
 
import calculator.Calc;
 
public class ClearError extends OperatorAbstract {
    @Override
    public void Calc(Calc swingTest) {
        swingTest.setResultNum("0");
        swingTest.getResultNumLabel().setText(swingTest.getResultNum());
    }
}
