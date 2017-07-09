package p1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HTMLParser {
	
	
	public static String getHtml(String sourceUrl) throws Exception
	{
		URL url = new URL(sourceUrl);
		BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));
		String inputLine;
		StringBuffer stringBuffer = new StringBuffer();
		while((inputLine = read.readLine())!=null)
		{
			stringBuffer.append(inputLine);
		}
		return stringBuffer.toString();
	}
	
	public static String htmlToJson(String source) throws Exception
	{   	
		if(source==null || source.isEmpty()) return "";
		
        Document doc = Jsoup.parse(source);
		JSONArray jsonArr = new JSONArray();
        for (Element table : doc.select("table")) {
            for (Element row : table.select("tr")) {
                JSONObject jsonObj = new JSONObject();
                Elements tds = row.select("td");
                String tech = "";
                String reason = "";
                String lifeCycle = "";
                if(tds.size()==1) tech = tds.get(0).text();
                else if(tds.size()==2)
                {
	                 tech = tds.get(0).text();
	                 reason = tds.get(1).text();
                }
                else if(tds.size()==3)
                {
                	 tech = tds.get(0).text();
	                 reason = tds.get(1).text();
	                 lifeCycle = tds.get(2).text();
                }
                jsonObj.put("Tech", tech);
                jsonObj.put("Reason", reason);
                jsonObj.put("LifeCycle", lifeCycle);
                jsonArr.put(jsonObj);
             }
        }
        return jsonArr.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String sourceUrl = "https://github.com/egis/handbook/blob/master/Tech-Stack.md";
		try 
		{
			String html = getHtml(sourceUrl);
			String json = htmlToJson(html);
			System.out.println(json);
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
