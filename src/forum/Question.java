package forum;

import java.util.List;

public class Question extends LinkedObject{

    Question(List<Long> qlinks, List<Long> alinks, int id, String text) {
        super(qlinks,alinks,id,text);
    }
    
    public String toString(){
        return(qlinks.toString()+ alinks.toString()+id+text);
    }

    
    
    
}
