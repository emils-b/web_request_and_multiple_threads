package web_request_and_multiple_threads;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebReader {

	static Document getPage(String urlString) {
		Document doc = null;
		pause(350);
		try {
			System.out.println("Calling web request");
			doc = Jsoup.connect(urlString).get();
		} catch (Exception e) {
			System.out.println("ERROR with web request");
			e.printStackTrace();
		}
		return doc;
	}
	
	
	//gribēju, ka pēc id sākuma atlasa "id_" bet neizdomāju kā
	static void getTableTrTag(String urlString) {
		Document doc = getPage(urlString);
		Element allTable = doc.getElementById("filter_frm");
		Elements allTrTags = allTable.getElementsByTag("tr");
		for (Element tr: allTrTags) {
			getTableCarId(tr);
		}
	}
	
	
	static void getTableCarId(Element tr) {
		Elements ids = tr.getElementsByAttribute("id");
		if (ids.size()==4) {
			Element id = ids.first();
			Elements tds = id.getElementsByTag("td");
			Elements aTag = getATag(tds);
			String href = getHref(aTag);
			createCarObjects(tds, href);
		}
	}
	
	static void createCarObjects(Elements tds, String href) {
		Car car = new Car(tds.get(3).text(), tds.get(4).text(), tds.get(5).text(), tds.get(6).text(), href);
		Car.cars.add(car);
	}
	
	static Elements getATag(Elements tds) {
		Element td = tds.get(1);
		Elements aTag = td.getElementsByTag("a");
		return aTag;
	}
	
	static String getHref(Elements aTag) {
		String href = aTag.attr("href");
		return href;
	}
	
	
	static void pause(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			
		}
	}
	
}
