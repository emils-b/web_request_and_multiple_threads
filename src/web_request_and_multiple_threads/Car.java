package web_request_and_multiple_threads;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Car {
	int year;
	String usedFuelType;
	double engineCap;
	int mileage;
	int price;
	String hrefToCarPage;
	static ArrayList<Car> cars = new ArrayList<Car>();
	int nrOfVisits;
	String transmition; //Manual/automat ID tdo_35

	public Car(String year, String engineType, String mileage, String price, String href) {
		this.year = Integer.parseInt(year);
		this.usedFuelType = getUsedFuelType(engineType);
		this.engineCap = getEngineCap(engineType);
		this.mileage = getMileage(mileage);
		this.price = getPrice(price);
		this.hrefToCarPage = "https://www.ss.com"+href; //jāuzliek ss.com sākums citur kā konstante
		//this.nrOfVisits = getVisitCount(this.hrefToCarPage);
		//getVisitCount(this.hrefToCarPage);
		this.transmition = getTransmitionTypeFromUrl(this.hrefToCarPage);
		//printCar();
		
	}
	
	String getUsedFuelType(String engineType) {
		String fuelType = "Petrol";
		if(engineType.length()>3) {
			fuelType = "Diesel";
		}
		return fuelType;
	}
	
	double getEngineCap(String engineType) {
		double engineCap = Double.parseDouble(engineType);
		if(engineType.length()>3) {
			engineCap = Double.parseDouble(engineType.substring(0,engineType.length()-1));
		}
		return engineCap;
	}
	
	int getMileage(String mileage) {
		int mil = 0;
		if(mileage.length()>1) {
			mil = Integer.parseInt(mileage.split(" ")[0]+"000");
		}
		return mil;
	}
	
	int getPrice(String price) {
		int pr = 0;
		if(price.length()>=7) {
			String[] splitPrice = price.split(" ")[0].split(",");
			pr = Integer.parseInt(splitPrice[0] + splitPrice[1]);
		}
		else {
			pr = Integer.parseInt(price.split(" ")[0]);
		}
		return pr;
	}
	
	void printCar() {
		System.out.println(this.year+" "+this.usedFuelType+" "+this.engineCap+" "+this.mileage+" "+this.price);
	}
	
	static String getTransmitionTypeFromUrl(String urlString) {
	Document doc = WebReader.getPage(urlString);
	Element transmitionTypeFromPage = doc.getElementById("tdo_35");
	String transmition = transmitionTypeFromPage.text();
	String preciseTransmitionType = getPreciseTransmitionType(transmition);
	return preciseTransmitionType;
	}
	
	static String getPreciseTransmitionType(String transmition) {
		String preciseTransmitionType = "";
		String[] webTrSplit = transmition.split(" ");
		if (webTrSplit[0].equals("Manuāla")) {
			preciseTransmitionType = "Manual";
		}
		else if (webTrSplit[0].equals("Automāts")) {
			preciseTransmitionType = "Automatic";
		}
		else {
			preciseTransmitionType = "No information or non standart type.";
		}
		return preciseTransmitionType;
	}
	
	//šis nestrādā, iegūst 1 visiem
	/*static void getVisitCount(String urlString) {
		Document doc = WebReader.getPage(urlString);
		Element nrOfVisit = doc.getElementById("show_cnt_stat");
		int nr = Integer.parseInt(nrOfVisit.text());
		//System.out.println(nr);
	}*/

}
