package calculator;
 
import java.awt.Font;
import java.awt.Toolkit;
 
public enum SizesEnum {
    SCREEN_WIDTH((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
    SCREEN_HEIGHT((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()),
 
    FRAME_WIDTH(350),
    FRAME_HEIGHT(520),
    FRAME_POSITION_X((SCREEN_WIDTH.getSize() / 2) - (FRAME_WIDTH.getSize() / 2)),
    FRAME_POSITION_Y((SCREEN_HEIGHT.getSize() / 2) - (FRAME_HEIGHT.getSize() / 2)),
    
    TOP_PANEL_WIDTH((FRAME_WIDTH.getSize() / 100) * 98),
    TOP_PANEL_HEIGHT((FRAME_HEIGHT.getSize() / 100) * 25),
    TOP_PANEL_POSITION_X((FRAME_WIDTH.getSize() - TOP_PANEL_WIDTH.getSize()) / 3),
    TOP_PANEL_POSITION_Y(FRAME_HEIGHT.getSize() / 40),
    
    BOTTOM_PANEL_WIDTH(TOP_PANEL_WIDTH.getSize()),
    BOTTOM_PANEL_HEIGHT((FRAME_HEIGHT.getSize() / 100) * 60),
    BOTTOM_PANEL_POSITION_X(TOP_PANEL_POSITION_X.getSize()),
    BOTTOM_PANEL_POSITION_Y((TOP_PANEL_POSITION_Y.getSize() * 2) + TOP_PANEL_HEIGHT.getSize()),
            
    LABEL_WIDTH((TOP_PANEL_WIDTH.getSize() / 10) * 9),
    LABEL_HEIGHT(TOP_PANEL_HEIGHT.getSize() / 2),
    PROCESSNUM_LABEL_POSITION_X((TOP_PANEL_WIDTH.getSize() - LABEL_WIDTH.getSize()) / 2),
    PROCESSNUM_LABEL_POSITION_Y(TOP_PANEL_POSITION_Y.getSize() - 10),
    RESULTNUM_LABEL_POSITION_X(PROCESSNUM_LABEL_POSITION_X.getSize()),
    RESULTNUM_LABEL_POSITION_Y(TOP_PANEL_POSITION_Y.getSize() + LABEL_HEIGHT.getSize() - 20),
    
    BUTTON_FONT(new Font("consolas", Font.PLAIN, 23)),
    PROCESS_NUM_FONT(new Font("consolas", Font.PLAIN, 23)),
    RESULT_NUM_FONT_DEFAULT(new Font("consolas", Font.PLAIN, 38)),
    RESULT_NUM_FONT_OVERFLOW(new Font("consolas", Font.PLAIN, 20)),
    ERROR_FONT(new Font("consolas", Font.PLAIN, 20));
    
    private int size;
    private Font font;
    
    private SizesEnum() {}
    
    private SizesEnum(int num) {
        this.size = num;
    }
    
    private SizesEnum(Font font) {
        this.font = font;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public Font getFont() {
        return this.font;
    }
}
