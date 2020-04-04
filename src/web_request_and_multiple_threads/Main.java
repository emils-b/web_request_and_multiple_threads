package web_request_and_multiple_threads;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

	public static void main(String[] args) {
		String urlString = "https://www.ss.com/lv/transport/cars/audi/a4/sell/";
		//Document doc = WebReader.getPage(urlString);
		WebReader.getTableTrTag(urlString);
		
		getVisitCount("https://www.ss.com/msg/lv/transport/cars/audi/a4/fhggn.html");

	}
	
	static void getVisitCount(String urlString) {
		Document doc = WebReader.getPage(urlString);
		Element nrOfVisit = doc.getElementById("show_cnt_stat");
		int nr = Integer.parseInt(nrOfVisit.text());
		System.out.println(nr);
	}

}
