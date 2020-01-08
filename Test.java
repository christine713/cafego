import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test () {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("big5");
		request.setCharacterEncoding("big5");
		response.setContentType("text/html");
		if(request.getParameter("keyword")== null) {
			String requestUric = request.getRequestURI();
			request.setAttribute("requestUric", requestUric);
			request.getRequestDispatcher("cafego.jsp").forward(request, response);
			return;
		}
		
		
		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		Keyword a1 = new Keyword("咖啡廳",10 );
		keywords.add(a1);
		Keyword b1 = new Keyword("限時",8);
		keywords.add(b1);
		Keyword c1 = new Keyword("Wifi",8);
		keywords.add(c1);
		Keyword d1 = new Keyword("插座",6);
		keywords.add(d1);
//		Keyword e1 = new Keyword("好吃",10);
//		keywords.add(e1);
		Keyword g1 = new Keyword(request.getParameter("keyword")+"站",10);
		keywords.add(g1);
		
		GoogleQuery google = new GoogleQuery("台北"+request.getParameter("keyword")+"站咖啡");
		HashMap<String, String> query = google.query();
		List<Map.Entry<String, String>> listData =
	            new ArrayList<Map.Entry<String, String>>(query.entrySet());

		for(int j=0;j<listData.size();j++) {
			System.out.println(listData.get(j).toString().split("q=")[1]);
			WebPage rootPage = new WebPage(listData.get(j).toString().split("q=")[1],Integer.toString(j));		
			WebTree tree = new WebTree(rootPage);
			
			try {
				tree.setPostOrderScore(keywords);
				tree.eularPrintTree();
				}catch(Exception e) {
					
				}
				}
		HashMap<String,Double> querys = new HashMap<String,Double>();
		for(int n=0;n<listData.size();n++) {
			WebPage rootPage = new WebPage(listData.get(n).toString().split("q=")[1],Integer.toString(n));		
			WebTree tree = new WebTree(rootPage);
			try {
			tree.setPostOrderScore(keywords);
			querys.put(listData.get(n).toString().split("q=")[0], tree.root.nodeScore);
			}catch (Exception e) {
				// TODO: handle exception
			querys.put(listData.get(n).toString().split("q=")[0], 0.0);
			}
		}
		
		List<Map.Entry<String, Double>> qlist = 
				new ArrayList<Map.Entry<String, Double>>(querys.entrySet()); //轉換為list
		qlist.sort(new Comparator<Map.Entry<String, Double>>() {
	          @Override
	          public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
	              return o2.getValue().compareTo(o1.getValue());
	          }
	      }); 
		
		HashMap<String, String> output = new HashMap<String, String>();
		
		for(int i=2;i<qlist.size();i++)
		{
			try 
			{
				String title = qlist.get(i).toString().split("=")[0];
				String citeUrl = query.get(title);
				//System.out.println(title+" "+citeUrl);
				output.put(title, citeUrl);


			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}


		String[][] s = new String[output.size()][2];
		
		request.setAttribute("output", s);
		
		int num = 0;
		for(Entry<String, String> entry : output.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
//		    if (value.contains("maps")) {
//		    	num++;
//		    }else {
		    	s[num][0] = key;
			    s[num][1] = value;
			    System.out.println(output);
			    num++;
//		    }
		    
		}
		
		request.getRequestDispatcher("GoogleItem.jsp").forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
