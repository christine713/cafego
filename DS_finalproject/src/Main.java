
import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.*;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;

public class Main {

	public static void main(String[] args) throws IOException ,NullPointerException{
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please type the number of keywords:");
		int numOfKeywords = sc.nextInt();
		System.out.println("Please type the keyword and its weight:");
		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		for(int i =0;i<numOfKeywords;i++)
		{
			String keywordName = sc.next();
			double keywordWeight = sc.nextDouble();
			Keyword k = new Keyword(keywordName, keywordWeight);
			keywords.add(k);
		}
		System.out.println("Please type the MRT name:");
		String mrtName = sc.next();
		String search = mrtName + "咖啡廳";
		HashMap<String, String> reV = new GoogleQuery(search).query();
		//try {
		//	System.out.println(new GoogleQuery(search).query());
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		List<Map.Entry<String, String>> listData =
	            new ArrayList<Map.Entry<String, String>>(reV.entrySet());
		
		//System.out.println(reV);
		//System.out.println(listData.get(3).toString().split("=")[2]);
		//System.out.println(reV.get(1).split("=")[2]);
		//System.out.println(reV.get(2).split("=")[2]);
				
//		try {
		for(int j=0;j<listData.size();j++) {
		System.out.println(listData.get(j).toString().split("=")[2]);
		WebPage rootPage = new WebPage(listData.get(j).toString().split("=")[2],Integer.toString(j));		
		WebTree tree = new WebTree(rootPage);
		WebChild children = new WebChild(reV.get(j).split("=")[2]);

		HashMap<String, String> re = children.query();
		for (int k=0;k<re.size();k++) {
			tree.root.addChild(new WebNode(new WebPage(re.get(k).split("=")[2],Integer.toString(k))));
			tree.setPostOrderScore(keywords);
			tree.eularPrintTree();
			}
		}
	}
}

//import java.util.HashMap;
//import java.util.Scanner;
//import java.io.IOException;
//
//public class Main {
//
//	public static void main(String[] args) throws IOException {
//		//把hw3改成輸入keyword 連接google 算出count
//		System.out.println("Please type: 捷運站名");
//		Scanner sc = new Scanner(System.in);
//		while(sc.hasNextLine()){
//		    String station = sc.next();
//			try {
//				HashMap<String,String> query=new GoogleQuery(station + "咖啡廳").query();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//		    WordCounter counter = new WordCounter(query);
//		    System.out.println(counter.countKeyword(keyword));
//		}
		



//}
//}