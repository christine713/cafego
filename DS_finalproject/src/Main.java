
import java.util.HashMap;
import java.util.Scanner;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		//把hw3改成輸入keyword 連接google 算出count
		System.out.println("Please type: 捷運站名");
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()){
		    String station = sc.next();
			try {
				HashMap<String,String> query=new GoogleQuery(station + "咖啡廳").query();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		    WordCounter counter = new WordCounter(query);
		    System.out.println(counter.countKeyword(keyword));
		}
		



}
}