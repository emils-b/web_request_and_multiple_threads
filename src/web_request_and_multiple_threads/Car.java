package web_request_and_multiple_threads;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Car {
	int year;
	String usedFuelType;
	double engineCap;
	int mileage;
	int price;
	String hrefToCarPage;
	static ArrayList<Car> cars = new ArrayList<Car>();
	int nrOfVisits;
	String transmition;

	public Car(String year, String engineType, String mileage, String price, String href) {
		this.year = Integer.parseInt(year);
		this.usedFuelType = getUsedFuelType(engineType);
		this.engineCap = getEngineCap(engineType);
		this.mileage = getMileage(mileage);
		this.price = getPrice(price);
		this.hrefToCarPage = "https://www.ss.com"+href; //jāuzliek ss.com sākums citur kā konstante
		//this.nrOfVisits = getVisitCount(this.hrefToCarPage);
		getVisitCount(this.hrefToCarPage);
		this.transmition = getTransmitionTypeFromUrl(this.hrefToCarPage);
		printCar();
		
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
	static void getVisitCount(String urlString) {
		//Document doc = WebReader.getPage(urlString);
		//Element nrOfVisit = doc.getElementById("show_cnt_stat");
		//int nr = Integer.parseInt(nrOfVisit.text());
		WebDriver driver;
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		long start = System.currentTimeMillis();
		driver.navigate().to(urlString);
		//Actions builder = new Actions(driver);
		//Actions stopLoading;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println("before first stoper___________________________");
		long end = System.currentTimeMillis();
		//if (end-start > 2000) {
			System.out.println("first stopper Test>>>>>>>>>>>>>>>>>.");
			js.executeScript("return window.stop");//stopLoading = builder.sendKeys(Keys.ESCAPE);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		System.out.println("before refreshed_____________________________");
		//driver.navigate().refresh();
		System.out.println("after refreshed_____________________________");
		long end2 = System.currentTimeMillis();
		//if (end2-start > 2000) {
			System.out.println("second stoper Test<<<<<<<<<<<<<<<<<<<,");
			//js.executeScript("return window.stop");//stopLoading = builder.sendKeys(Keys.ESCAPE);
		//}
		String visitCount = driver.findElement(By.id("show_cnt_stat")).getText();
		System.out.println(visitCount);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
		//System.out.println(nr);
	}
	
	/*JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("return window.stop");*/

}
