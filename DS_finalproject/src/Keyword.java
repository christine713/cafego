



public class Keyword {
	public String name;
    public double count;
    public double weight;
    
    public Keyword(String name,double weight){
		this.name = name;
//		this.count =count;
		this.weight =weight;
    }
    
    @Override
    public String toString(){
    	return "["+name+","+weight+"]";
//    	return "["+name+","+count+","+weight+"]";
    }
}
