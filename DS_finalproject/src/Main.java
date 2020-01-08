


import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.*;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;

public class Main {

	public static void main(String[] args) throws IOException ,NullPointerException{
		// TODO Auto-generated method stub
		//WHYYYYYYYY 23456789101234567s890112345
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
		String search = mrtName + "��撱�";
		
//		GoogleQuery ya = new GoogleQuery(search);
//		ya.NextPageUrl();
		
		HashMap<String, String> reV = new GoogleQuery(search).query();
		
		//try {
		//	System.out.println(new GoogleQuery(search).query());
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		List<Map.Entry<String, String>> listData =
	            new ArrayList<Map.Entry<String, String>>(reV.entrySet());
		//System.out.println(listData);
		//System.out.println(listData.get(3).toString().split("=")[2]);
		//System.out.println(reV.get(1).split("=")[2]);
		//System.out.println(reV.get(2).split("=")[2]);
				
//		try {
		for(int j=0;j<listData.size();j++) {
		System.out.println(listData.get(j).toString().split("=")[2]);
		WebPage rootPage = new WebPage(listData.get(j).toString().split("=")[2],Integer.toString(j));		
		WebTree tree = new WebTree(rootPage);
		WebChild children = new WebChild(listData.get(j).toString().split("=")[2]);

//		HashMap<String, String> re = children.query();
//		List<Map.Entry<String, String>> listData2 =
//	            new ArrayList<Map.Entry<String, String>>(re.entrySet());
		
//		for (int k=0;k<listData2.size();k++) {
			try {
//			tree.root.addChild(new WebNode(new WebPage(listData2.get(k).toString().split("=")[2],Integer.toString(k))));
			tree.setPostOrderScore(keywords);
			tree.eularPrintTree();
			}catch(Exception e) {
				
			}
			}
		
		
		HashMap<String,Double> querys = new HashMap<String,Double>();
		for(int n=0;n<listData.size();n++) {
			WebPage rootPage = new WebPage(listData.get(n).toString().split("=")[2],Integer.toString(n));		
			WebTree tree = new WebTree(rootPage);
			try {
			tree.setPostOrderScore(keywords);
//			System.out.println(tree.root.nodeScore);
			querys.put(listData.get(n).toString().split("/")[0], tree.root.nodeScore);
			}catch (Exception e) {
				// TODO: handle exception
			querys.put(listData.get(n).toString().split("/")[0], 0.0);
			}
		}
		
		List<Map.Entry<String, Double>> qlist = new ArrayList<Map.Entry<String, Double>>(querys.entrySet()); //頧�list
		qlist.sort(new Comparator<Map.Entry<String, Double>>() {
	          @Override
	          public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
	              return o2.getValue().compareTo(o1.getValue());
	          }
	      });
		
		System.out.println(qlist);
		}
		
	}
