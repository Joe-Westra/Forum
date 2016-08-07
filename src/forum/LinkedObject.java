package forum;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joe
 */
public class LinkedObject {
    String text;
    List<Long> qlinks;
    List<Long> alinks;
    int id;
    
    LinkedObject(){}
    LinkedObject(List<Long> qlinks, List<Long> alinks, int id, String text){
        this.qlinks = qlinks;
        this.alinks = alinks;
        this.id = id;
        this.text = text;
        
    }
}
