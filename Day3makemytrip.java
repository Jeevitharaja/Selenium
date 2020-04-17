package Seleniumhandson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class Day3makemytrip {

	public static void main(String[] args) throws InterruptedException {
		// Set the chromedriver.exe file to JAVA class
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				
		// Launch ChromeBrowser and disable notificationss
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				ChromeDriver driver = new ChromeDriver(options);

				
		// Open URL
				driver.get("https://www.makemytrip.com");
				
		// Maximize Browser
				driver.manage().window().maximize();
		
		    
		// Click Hotels
			driver.findElementByXPath("(//span[@class=\"chNavText darkGreyText\"])[2]").click();
			 Thread.sleep(2000);
			 
			//Enter city as Goa, and choose Goa, India
			 driver.findElementById("city").click();
			 Thread.sleep(2000);
			 WebElement city = driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']");
			 city.sendKeys("Goa");
			 Thread.sleep(2000);
			 city.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
			 
			 
			// Enter Check in date as Next month 15th (May 15) and Check out as start date+5
			 driver.findElementById("checkin").click();
			 WebElement month = driver.findElementByXPath("(//div[@class='DayPicker-Month'])[2]");
			 month.findElement(By.xpath("(//div[text()='15'])[2]")).click();
				String text = month.findElement(By.xpath("(//div[text()='15'])[2]")).getText();
				int currdate = Integer.parseInt(text);
				
				driver.findElementById("checkout").click();
				WebElement currmonth = driver.findElementByXPath("(//div[@class='DayPicker-Month'])[2]");
				currmonth.findElement(By.xpath("(//div[text()='"+(currdate+5)+"'])[2]")).click();
				
			//Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
				driver.findElementById("guest").click();
				driver.findElementByXPath("(//li[text()=1])[2]").click();
				Select age = new Select(driver.findElementByXPath("//select[@data-cy='childAge-0']"));
				age.selectByVisibleText("12");
				driver.findElementByXPath("//button[@class=\"primaryBtn btnApply\"]").click();
				
			// Click Search button
				driver.findElementByXPath("//button[@id=\"hsw_search_button\"]").click();
				Thread.sleep(5000);
				
			//Handling the blackscreen
				//if(driver.findElementByXPath("//body[@class='bodyFixed overlayWholeBlack']").isDisplayed()) {
					//driver.findElementByXPath("//body[@class='bodyFixed overlayWholeBlack']").click();
				//}
				
				//driver.switchTo().frame(driver.findElementById("webklipper-publisher-widget-container-notification-frame"));
			
				//driver.switchTo().defaultContent();
				driver.findElementByXPath("//div[contains(@class, 'mmBackdrop wholeBlack')]").click();
			//Select locality as Baga
				WebElement Locality = driver.findElementByXPath("//input[@placeholder=\"Enter location or property name\"]");
				Locality.sendKeys("Baga");
				Thread.sleep(2000);
				Locality.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
				
			//Page scroll
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,1000)");
				
			 //Select 5 start in Star Category under Select Filters
			
				driver.findElementByXPath("//label[text()='5 Star']").click();
				Thread.sleep(5000);
			//Click on the first resulting hotel and go to the new window
				driver.findElementByXPath("//span[@id=\"htl_id_seo_201801171522004728\"]").click();
				Set<String> windowHandle = driver.getWindowHandles();
				List<String> list = new ArrayList<String>(windowHandle);
				driver.switchTo().window(list.get(2));
				
			// Print the Hotel Name
				System.out.println("Hotel Name: "+driver.findElementByXPath("//h1[@class='txtHeading font22 latoBlack blackText']").getText());	
			//Click MORE OPTIONS link and Select 3Months plan and close
				driver.findElementByXPath("//span[@class='latoBold font10 blueText pointer']").click();
				Thread.sleep(200045);
				driver.findElementByXPath("(//span[@class='latoBold font12 pointer makeFlex hrtlCenter right blueText'])[1]").click();
				driver.findElementByClassName("close").click();

			//Click on BOOK THIS NOW
				driver.findElementById("detpg_headerright_book_now").click();
				Thread.sleep(3000);
				WebElement modal = driver.findElementByXPath("//div[@class='_Modal modalCont']");
				if(modal.isDisplayed()) {
					driver.findElementByClassName("close").click();
				}
			//Print the Total Payable amount
				System.out.println("Total Payable Amount: "+driver.findElementById("revpg_total_payable_amt").getText().replaceAll("[a-zA-Z]", ""));

			//Close the browser
				driver.close();
				driver.switchTo().window(list.get(1)).close();


	}

}
