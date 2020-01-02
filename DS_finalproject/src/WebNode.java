
import java.io.IOException;
import java.util.ArrayList;

public class WebNode {
	public WebNode parent;
	public ArrayList<WebNode> children;
	public WebPage webPage;	
	public double nodeScore;
	
	public WebNode(WebPage webPage){
		this.webPage = webPage;
		this.children = new ArrayList<WebNode>();
	}
	
	public void setNodeScore(ArrayList<Keyword> keywords) throws IOException{
		//this method should be called in post-order mode
		
		//1. compute webPage score
		//2. set webPage score to nodeScore
		webPage.setScore(keywords);
		nodeScore+=webPage.score;
		//3. nodeScore += all childrens nodeScore
		for(int i=0;i<children.size();i++) {
			nodeScore+=children.get(i).nodeScore;
		}
		
	}
	
	public void addChild(WebNode child){
		// add the WebNode to its children list
		children.add(child);
	}
	
	public boolean isTheLastChild(){
		if(this.parent == null) return true;
		ArrayList<WebNode> siblings = this.parent.children;
		
		return this.equals(siblings.get(siblings.size() - 1));
	}
	
	public int getDepth(){
		int retVal = 1;
		WebNode currNode = this;
		while(currNode.parent!=null){
			retVal ++;
			currNode = currNode.parent;
		}
		return retVal;
	}
}