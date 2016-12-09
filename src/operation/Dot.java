package operation;
 
import calculator.Calc;
 
public class Dot extends OperatorAbstract {
    @Override
    public void Calc(Calc swingTest) {
        swingTest.setResultNum(swingTest.getResultNum() + ".");
    }
}
