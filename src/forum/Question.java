package forum;

import java.awt.Graphics;
import java.util.List;

public class Question extends LinkedObject{
	private static Long index = Long.MIN_VALUE;

    Question(List<LinkedObject> qlinks, List<LinkedObject> alinks, Long id, String text) {
        super(qlinks,alinks,id,text);

     }
    Question(List<LinkedObject> qlinks, List<LinkedObject> alinks, String text) {
         super(qlinks,alinks,index,text);
         index++;
      }
  /*
    protected void paintComponent(Graphics g){
   	// paintComponent(g);
    	view.paint(g);
   	 System.out.println("here");
    }
    */
}
