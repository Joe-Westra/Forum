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
    }
    
    
    
    
    private static void test() {
        String text = "hello";

        List<Long> qlinks = new ArrayList<>();
        List<Long> alinks = new ArrayList<>();
        
        for(int i = 0; i <10;i++){
            qlinks.add((long) (Math.random() * 20));
            alinks.add((long)(Math.random()*20));
        }
        
        int id = 2; 

        
        Question q = new Question(qlinks,alinks,id,text);
        System.out.println(q.toString());
    }
    

}
