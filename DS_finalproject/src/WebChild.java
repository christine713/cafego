import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebChild {
	
	public URL u;
	public String url;
	public String content;
	
	public WebChild(String url) throws MalformedURLException
	{
		this.url=url;
		this.u=new URL(url);
	}
	
	private String fetchContent() throws IOException
	{
		String retVal = "";
		URL u = new URL(url);
		URLConnection conn = u.openConnection();
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
		//lis = lis.select(".ZINbbc");
		//System.out.println(lis.size());
		//for(Element li : lis)
		for(int i=1;i<lis.size();i++)
		{
			try 
			{
				//String title = lis.get(i).select(".BNeawe").get(0).text();
				
				String title = Integer.toString(i);
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