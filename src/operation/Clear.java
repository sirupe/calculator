package operation;
 
import calculator.Calc;
 
public class Clear extends OperatorAbstract {
    @Override
    public void Calc(Calc swingTest) {
        swingTest.numsInit();
        swingTest.getResultNumLabel().setText(swingTest.getResultNum());
        swingTest.getProcessNumLabel().setText(swingTest.getProcessNum());
    }
}
