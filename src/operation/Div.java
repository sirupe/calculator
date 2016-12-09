package operation;
 
import java.awt.Font;
 
import calculator.Calc;
 
public class Div extends OperatorAbstract {
 
    @Override
    public void Calc(Calc swingTest) {
        System.out.println(swingTest.getDoNum());
        System.out.println(swingTest.getResultNum());
        Double resultD = Double.parseDouble(swingTest.getDoNum()) / Double.parseDouble(swingTest.getResultNum());
        String resultS = resultD.toString();
        if(resultS.equals("Infinity")) {
            resultS = "Can't divide by 0";
        }
        System.out.println(resultS);
        swingTest.getResultNumLabel().setFont(new Font("consolas", Font.PLAIN, 25));
        swingTest.setResultNum(resultS);
    }
}