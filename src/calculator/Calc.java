package calculator;
 
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
 
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
import operation.*;
 
public class Calc extends JFrame implements ActionListener, KeyListener {
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton[][] buttons;
    private GridLayout gridLayout;
    private JLabel processNumLabel;
    private JLabel resultNumLabel;
    private String resultNum;
    private String processNum;
    private String prevNum;
    private String prevCal;
    private String prevInput;
    
    private boolean isShift = false;
    
    private Map<String, Operator> operator;
    
    public Calc() {
        this.operator = new HashMap<String, Operator>();
 
        this.numsInit();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(
                SizesEnum.FRAME_POSITION_X.getSize(),
                SizesEnum.FRAME_POSITION_Y.getSize(),
                SizesEnum.FRAME_WIDTH.getSize(),
                SizesEnum.FRAME_HEIGHT.getSize()
        );
        this.setLayout(null);
        
        this.setTopPanel();
        this.setBottomPanel();
        
        this.setVisible(true);
    }//생성자
 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String act = e.getActionCommand();
        
        this.inputEvent(act);
        
    }//actionPerformed
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        String act = null;
        
        // [- : 45] [+ : shift 61] [back : 8] [esc : 27] [% : shift 53] [* : shift 56] [/ : 47] [c: 67]
        if(this.isShift) {
            switch(key) {
            case 61 :
                act = "+";
                break;
            case 56 :
                act = "*";
                break;
            case 53 : 
                act = "%";
                break;
            
            }
            this.isShift = false;
            
        } else {
            switch(key) {
            case 45 :
                act = "-";
                break;
            case 10 :
                act = "=";
                break;
            case 8 :
                act = "←";
                break;
            case 27 :
                act = "CE";
                break;
            case 47 :
                act = "/";
                break;
            case 67 :
                act = "C";
                break;
            default :
                key -= '0';
                act = key >= 0 && key <= 9 ? key + "" : "";
                
            }
        }
        
        // 키값이 없는 키의 입력이 들어오면 NullPointerException 발생하므로 try-catch
        try {
            if(!act.isEmpty()) {
                this.inputEvent(act);
            }
        } catch(NullPointerException ne) {
            
        }
        
        // 키보드에서 shift 와 함꼐 써야 하는 %, *, + 기호를 제대로 입력받지 못하므로 쓰레드 처리
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                this.isShift = false;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }).start();
        
        // isShift 를 플래그로써 shift 키의 입력이 들어왔는지 식별 용으로 사용
        this.isShift = e.getKeyCode() == KeyEvent.VK_SHIFT;
    }
    
    public void inputEvent(String act) {
        System.out.println(this.resultNum.length() > 11);
        this.resultNumLabel.setFont(
                this.resultNum.length() > 11 ? 
                SizesEnum.RESULT_NUM_FONT_OVERFLOW.getFont() : 
                SizesEnum.RESULT_NUM_FONT_DEFAULT.getFont()
        );
        
        // 21억 이상의 수는 연산할 수 없어 에러가 발생하므로 try-catch
        try {
            // ★숫자 입력시
            if(act.charAt(0) >= '0' && act.charAt(0) <= '9') {
                // ☆ 현재 화면에 0밖에 없고 입력되는 값도 0이라면 0을 화면에 추가시키지 않기 위한 if문
                if(!(act.equals("0") && this.resultNum.equals(act))) {
                    // * 현재 화면의 입력숫자가 20글자 미만이어야지만 숫자입력이 가능하다.
                    if(this.resultNum.length() < 20) {
                        this.operator.get(act).Calc(act, this);
                    }
                }
            // ★연산기호 입력시
            } else if(act.equals("*") || act.equals("/") || act.equals("-") || act.equals("+")) {
                // ☆ 바로 이전 입력이 기호인데 또 기호를 입력하였다면 입력한 기호로 연산기호를 바꿔주기 위한 if문
                if(this.prevInput.equals("*") || this.prevInput.equals("/") || 
                   this.prevInput.equals("-") || this.prevInput.equals("+")) {
                    this.processNum = this.processNum.substring(0, this.processNum.length() - 2) + act + " ";
                    this.prevCal = act;
                // ☆ 이전 입력이 기호가 아니라면 정상적으로 연산을 실행한다.
                } else {
                    this.processNum += this.resultNum + " " + act + " ";
                    // * 연산기호 입력시 이전 숫자가 존재한다면 이전에 입력한 연산자로 계산하고
                    if(this.prevNum.length() != 0) {
                        this.operator.get(this.prevCal).Calc(this);
                    // * 이전 숫자가 없다면 현재 입력한 연산자로 계산한다.
                    } else {
                        this.operator.get(act).Calc(this.resultNum, this);
                    } // if-else 3
                    
                    // 입력된 연산자는 이전 연산자에 저장, 입력된 숫자 이전 숫자에 저장, 현재 입력값은 비운다.
                    this.prevCal = act;
                    this.prevNum = this.resultNum;
                    this.resultNum = "";
                } // if-else 2
            // ★이외 기호 입력시
            } else {
                this.operator.get(act).Calc(this);
            } // if-else if-else 1
        // 연산불가한 정보기 들어온다면 catch 에서 에러메세지 출력.
        } catch(NumberFormatException ne) {
            this.numsInit();
            this.resultNumLabel.setFont(SizesEnum.ERROR_FONT.getFont());
            this.resultNumLabel.setText("Can not be calculated");
            this.processNumLabel.setText(this.processNum);
        }//try-catch
        
        // 화면 출력범위를 초과하면 텍스트 짤라주기
        if(this.processNum.length() > 17) {
            this.processNum = "..." + this.processNum.substring(this.processNum.length() - 16);
        }
        
        this.processNumLabel.setText(this.processNum);
    
        // 0으로 나누었을 때의 에러메세지 출력.
        if(this.resultNum.equals("Infinity")) {
            this.numsInit();
            this.resultNumLabel.setFont(SizesEnum.ERROR_FONT.getFont());
            this.resultNumLabel.setText("Can not divide by 0");
            this.processNumLabel.setText(this.processNum);
        }
        
        // 이전 입력값은 무조건 지금 들어온 입력값으로 대체한다.
        this.prevInput = act;    
    }
    
    // 초기화메소드
    public void numsInit() {
        this.resultNum     = "0";
        this.processNum = "";
        this.prevNum     = "";
        this.prevCal     = "";
        this.prevInput  = "";
    }
 
    public void setTopPanel() {
        this.topPanel        = new JPanel();
        this.processNumLabel = new JLabel();        
        this.processNumLabel.setFont(SizesEnum.PROCESS_NUM_FONT.getFont());
        this.processNumLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.processNumLabel.setBounds(
                SizesEnum.PROCESSNUM_LABEL_POSITION_X.getSize(), 
                SizesEnum.PROCESSNUM_LABEL_POSITION_Y.getSize(),
                SizesEnum.LABEL_WIDTH.getSize(),
                SizesEnum.LABEL_HEIGHT.getSize()
        );
        
        this.resultNumLabel  = new JLabel("0");
        this.resultNumLabel.setFont(SizesEnum.RESULT_NUM_FONT_DEFAULT.getFont());
        this.resultNumLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.resultNumLabel.setBounds(
                SizesEnum.RESULTNUM_LABEL_POSITION_X.getSize(),
                SizesEnum.RESULTNUM_LABEL_POSITION_Y.getSize(),
                SizesEnum.LABEL_WIDTH.getSize(),
                SizesEnum.LABEL_HEIGHT.getSize()
        );
        
        this.topPanel.add(this.processNumLabel);
        this.topPanel.add(this.resultNumLabel);
        
        this.topPanel.setLayout(null);
        this.topPanel.setBackground(new Color(236, 243, 253));
        this.topPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        
        this.topPanel.setBounds(
                SizesEnum.TOP_PANEL_POSITION_X.getSize(), 
                SizesEnum.TOP_PANEL_POSITION_Y.getSize(), 
                SizesEnum.TOP_PANEL_WIDTH.getSize(), 
                SizesEnum.TOP_PANEL_HEIGHT.getSize()
        );
        
        this.topPanel.setVisible(true);
        this.add(this.topPanel);
    }//setTopPanel()
    
    public void setBottomPanel() {
        this.gridLayout = new GridLayout(5, 4);
        this.gridLayout.setHgap(7);
        this.gridLayout.setVgap(7);
        String[][] buttonsInfo = 
            {
                {"←", "CE", "C", "%"},
                {"7", "8", "9", "/"},
                {"4", "5", "6", "*"},
                {"1", "2", "3", "-"},
                {".", "0", "=", "+"}
            };
        
        Operator[][] buttonsFunc = 
            {
                {new BackSpace(), new ClearError(), new Clear(),   new Percent()},
                {new Numbers(),   new Numbers(),    new Numbers(), new Div()},
                {new Numbers(),   new Numbers(),    new Numbers(), new Mul()},
                {new Numbers(),   new Numbers(),    new Numbers(), new Sub()},
                {new Dot(),       new Numbers(),     new Equal(),   new Sum()}
            };
        
        this.buttons = new JButton[5][4];        
        this.bottomPanel = new JPanel();
        this.bottomPanel.setLayout(this.gridLayout);
        
        for(int i = 0, leng = buttonsInfo.length; i < leng; i++) {
            for(int j = 0, jleng = buttonsInfo[0].length; j < jleng; j++) {
                this.buttons[i][j] = new JButton(buttonsInfo[i][j]);
                
                if(buttonsInfo[i][j].charAt(0) >= '0' && buttonsInfo[i][j].charAt(0) <= '9') {
                    this.buttons[i][j].setForeground(new Color(204, 051, 051));
                    this.buttons[i][j].setBackground(Color.white);
                } else {
                    this.buttons[i][j].setForeground(new Color(255, 102, 102));
                    this.buttons[i][j].setBackground(new Color(255, 204, 204));
                }
                
                this.buttons[i][j].setFont(SizesEnum.BUTTON_FONT.getFont());
                this.buttons[i][j].addActionListener(this);
                this.buttons[i][j].addKeyListener(this);
                this.bottomPanel.add(this.buttons[i][j]);
                this.operator.put(buttonsInfo[i][j], buttonsFunc[i][j]);
            }
        }
        
        this.bottomPanel.setBounds(
                SizesEnum.BOTTOM_PANEL_POSITION_X.getSize(), 
                SizesEnum.BOTTOM_PANEL_POSITION_Y.getSize(), 
                SizesEnum.BOTTOM_PANEL_WIDTH.getSize(), 
                SizesEnum.BOTTOM_PANEL_WIDTH.getSize()
        );    
        
        this.bottomPanel.setVisible(true);
        this.add(this.bottomPanel);
 
    }//setBottomPanel()
    
    public JLabel getProcessNumLabel() {
        return processNumLabel;
    }
 
    public JLabel getResultNumLabel() {
        return resultNumLabel;
    }
 
    public String getResultNum() {
        return resultNum;
    }
 
    public String getProcessNum() {
        return processNum;
    }
    
    public String getDoNum() {
        return prevNum;
    }
    
    public String getPrevCal() {
        return prevCal;
    }
    
    public Map<String, Operator> getOperator() {
        return operator;
    }
    
    public void setResultNum(String resultNum) {
        this.resultNum = resultNum;
    }
 
    public void setProcessNum(String processNum) {
        this.processNum = processNum;
    }
 
    @Override
    public void keyTyped(KeyEvent e) {
    }
 
    @Override
    public void keyReleased(KeyEvent e) {        
    }
}