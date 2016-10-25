package forum;

import java.util.ArrayList;
import java.util.List;


public class Forum {

    
    public static void main(String[] args) {
        /*
        create a qlink, alink arraylist an id number and text
        create a question object with such attributes
        and print out a confirmation of each.
        */
        
        test();
        GUI main = new GUI();
        main.ShowGUI();
    }
    
    
    
    
    private static void test() {
    	HamletQuote ham = new HamletQuote();
    	Question q = new Question(null,null,ham.getQuoteFromHamlet());  
/*
        List<LinkedObject> qlinks = new ArrayList<LinkedObject>();
        List<LinkedObject> alinks = new ArrayList<LinkedObject>();
        
        for(int i = 0; i <10;i++){
        	qlinks.add(new Question(null,null,(long)(Math.random() * Long.MAX_VALUE),ham.getQuoteFromHamlet()));
        	alinks.add(new Question(null,null,(long)(Math.random() * Long.MAX_VALUE),ham.getQuoteFromHamlet()));
        }
        
        
        Question q = new Question(qlinks,alinks,ham.getQuoteFromHamlet());
        System.out.println(q.toString());
        */
    	 
    	
    }
    

}
