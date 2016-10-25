package forum;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.*;

public class DisplayPanel extends JPanel {
    String title;
    int idealDimensions;
    
    DisplayPanel(){};
    
    DisplayPanel(LinkedObject lo){};
    
    DisplayPanel(int fontsize, String title, int winx, int winy){
        super.setLayout(new FlowLayout());
        int firstSpace = 0;
        int secondSpace = 0;
        int row = 1;
        this.title = title;
        idealDimensions = (int)(.15 * (Math.sqrt(winx * winx + winy*winy)));
        for(int i = 0; i < title.length(); i++){
            if(title.charAt(i) == ' '){
                
                    super.add(new JLabel("the value of I is: "+i +". the ideal dimension is: "
                    + idealDimensions+".\nthe value of row is: "+row));
                if(i<(idealDimensions*row)){
                    firstSpace = i;
                    super.add(new JLabel("firing 'if(i<(idealDimensions*row))'"));
                }else if(i == idealDimensions){
                    //code for ending the row and starting again
                    super.add(new JLabel("firing 'i == dimensions'"));
                }else{
                    super.add(new JLabel("firing 'else statment, secondspace being sassigned to i'"));
                    secondSpace = i;
                }
                super.revalidate();
                super.repaint();
                //now see if the secondspace or firstspace is closer to the idealdimensions
                //((firstSpace +((secondSpace-firstSpace)/2)) <  (idealDimensions*row))
                if(((firstSpace +((secondSpace-firstSpace)/2)) <  (idealDimensions*row))){
                    //code for ending the row and starting again
                    //adjust idealdimensions to reflect the length of the 
                }
                
                
            }//end of "if(title.charAt(i) == ' ')"
        }
    }
    
    
    
    
}
