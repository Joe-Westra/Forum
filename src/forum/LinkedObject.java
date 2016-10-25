package forum;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;

/**
 *
 * @author joe
 */
public class LinkedObject extends AbstractButton{
	
	LOModel model;
	LOView view;
    
    LinkedObject(List<LinkedObject> qlinks,
    				List<LinkedObject> alinks,
    				Long id,
    				String text){
    	
    	model = new LOModel(qlinks,alinks,id,text);
    	view = new LOView(text);
        
    }
    
    public String toString(){
    	return(model.text);
    }
    
    protected void paintComponent(Graphics g){
    	 view.paint(g);
    	 System.out.println("here");
     }
}
