package p1;

import static org.junit.Assert.*;

import org.junit.Test;

public class HTMLParserTest {

	@Test
	public void test1() throws Exception {
		String html = "";
		String json = HTMLParser.htmlToJson(html);
		assertEquals("", json);
	}
	
	@Test
	public void test2() throws Exception {
		String html = null;
		String json = HTMLParser.htmlToJson(html);
		assertEquals("", json);
	}

	@Test
	public void test3() throws Exception {
		String html = 
				"<table>"
				+ "<tbody>"
				+ "<tr>"
				+ "<td>Java</td>"
				+ "<td>Object oriented</td>"
				+ "<td></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>Hibenate</td>"
				+ "<td>Website monitoring</td"
				+ "<td></td>"
				+ "</tr>"
				+ "</tbody>"
				+ "</table>";
		
		String actualJSON = HTMLParser.htmlToJson(html);
		String expectedJSON = "[{\"LifeCycle\":\"\",\"Tech\":\"Java\",\"Reason\":\"Object oriented\"},{\"LifeCycle\":\"\",\"Tech\":\"Hibenate\",\"Reason\":\"Website monitoring\"}]";
		assertEquals(expectedJSON, actualJSON);
	}
}

        
    
    
        
        
        
    

