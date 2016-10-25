package forum;

import java.util.List;

public class LOModel {
    String text;
    List<LinkedObject> qlinks;
    List<LinkedObject> alinks;
    Long id;
	public LOModel(List<LinkedObject> qlinks,
			List<LinkedObject> alinks,
			Long id,
			String text){
		this.qlinks = qlinks;
		this.alinks = alinks;
		this.id = id;
		this.text = text;
	}

}
