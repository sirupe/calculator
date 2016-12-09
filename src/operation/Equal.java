package operation;
 
import calculator.Calc;
 
public class Equal extends OperatorAbstract {
    @Override
    public void Calc(Calc swingTest) {
        if(!swingTest.getPrevCal().equals("")) {
            swingTest.getOperator().get(swingTest.getPrevCal()).Calc(swingTest);
            swingTest.getResultNumLabel().setText(swingTest.getResultNum());
            swingTest.setProcessNum(swingTest.getResultNum() + " ");
            //            this.processNum += this.resultNum + " " + act + " ";
            swingTest.getProcessNumLabel().setText(swingTest.getProcessNum());
            swingTest.numsInit();
        }
    }
}
