package web_request_and_multiple_threads;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

	public static void main(String[] args) {
		String urlString = "https://www.ss.com/lv/transport/cars/audi/a4/sell/";
		//Document doc = WebReader.getPage(urlString);
		//WebReader.getTableTrTag(urlString);
		WebReader.test(urlString);
		/*
		 * Jāuztaisa, ka katru objektu raksta excel un tad papildina ar to kas nav un tad  var attiecīgi no tā ielasīt
		 * vai arī jānolasa no visām lapām (tālākajiem tabiem) uzreiz objekti
		 * 
		 * jāpapēta kā var dabūt arī ar javascript ielasītos datus aktualizēt
		 * 
		 * ievade sērča logā
		 */
		
		//Response price = Jsoup.connect(urlString).data();

	}
	

}
