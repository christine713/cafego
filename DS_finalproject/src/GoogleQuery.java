import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleQuery 
{
	public String searchKeyword;
	public String url;
	public String url2;
	public String url3;
	public String content;
	public GoogleQuery(String searchKeyword)
	{
		this.searchKeyword = searchKeyword;
		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=10";
		
	}
	
	private String fetchContent() throws IOException
	{
		String retVal = "";
		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
		InputStream in = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(in,"utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;
		while((line=bufReader.readLine())!=null)
		{
			retVal += line;
		}
		return retVal;
	}
	
	 public void NextPageUrl() throws IOException {
		  if(content==null)
		  {
		   content= fetchContent();
		  }
		    Document doc = Jsoup.parse(content);

		    //Elements lis = doc.select("div");
		    Elements liss = doc.select("div#navnct");
//		    liss = lis.select("#navnct");  
		    liss = liss.select("td");
		    
		    for(Element li : liss){
		    	String citeUrl = li.select("a").get(0).attr("href");  
		   //String title = li.select("a").get(0).attr("aria-label");     
		    	System.out.println("citeUrl: " + citeUrl);
		      //System.out.println("title: " + title);

		     }
		 }
	
	
	
	public HashMap<String, String> query() throws IOException
	{
		if(content==null)
		{
			content= fetchContent();
		}
		
		HashMap<String, String> retVal = new HashMap<String, String>();
		Document doc = Jsoup.parse(content);
		//System.out.println(doc.text());
		Elements lis = doc.select("div");
		lis = lis.select(".ZINbbc");
		//System.out.println(lis.size());
		//for(Element li : lis)
		for(int i=2;i<lis.size()-2;i++)
		{
			try 
			{
				String title = lis.get(i).select(".BNeawe").get(0).text();
				String citeUrl = lis.get(i).select("a").get(0).attr("href");
				//System.out.println(title+" "+citeUrl);
				retVal.put(title, citeUrl);


			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}

}