package web_request_and_multiple_threads;

import java.util.concurrent.TimeUnit;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Main {

	public static void main(String[] args) {
		String urlString = "https://www.ss.com/lv/transport/cars/audi/a4/sell/";
		test(urlString);
		System.setProperty("webdriver.chrome.driver", "D:\\Eclipse\\selenium_files\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver;
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.navigate().to(urlString);
		driver.manage().window().maximize();
		//driver.navigate().refresh();
		//String tag = driver.findElement(By.id("show_cnt_stat")).getText();
		//System.out.println(tag);
		
		
		WebElement textbox = driver.findElement(By.id("f_o_8_max"));
		Actions builder = new Actions(driver);
		Actions seriesOfActions = builder.moveToElement(textbox).click(textbox).sendKeys(textbox, "8000").sendKeys(Keys.ENTER);
		seriesOfActions.perform();
		
		String newUrl = driver.getCurrentUrl();
		System.out.println(newUrl);
		//driver.quit();
		
		//Document doc = WebReader.getPage(newUrl);
		//WebReader.getTableTrTag(newUrl); //neiet ar jauno url, jo pārlādējot no jauna nesaglabājas filtra uzstādījumi. tā kā Jsoup ar šo nevar labi miksēt
		//WebReader.test(newUrl);

	}
	public static void test(String newUrl) {
		Thread thread = new Thread(){
			public void run() {
		WebReader.getTableTrTag(newUrl);
			}
		};
		thread.start();
	}
	

}
