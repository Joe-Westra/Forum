
package forum;

import java.awt.Color;
import javax.swing.JTextArea;
/*
This class is just a simple wrapped textarea modded to look like a label


*/

public class WrappedText extends JTextArea{
    
    
WrappedText(){
    
    super.setWrapStyleWord(false);
    super.setLineWrap(true);
    super.setOpaque(true);
    super.setEditable(false);
    super.setFocusable(false);
    super.setBackground(Color.BLUE);
    super.setBorder(null);
}
    
    
}
