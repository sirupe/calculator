package operation;
 
import calculator.Calc;
 
public class Percent extends OperatorAbstract {
    @Override
    public void Calc(Calc swingTest) {
        System.out.println(swingTest.getPrevCal());
    
        Double inputDouble = Double.parseDouble(swingTest.getResultNum()) / 100;
        swingTest.setResultNum(inputDouble.toString());
        new Equal().Calc(swingTest);        
    }    
}
