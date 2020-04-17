package Seleniumhandson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Day2Nykaa {

	public static void main(String[] args) throws InterruptedException {
		
		// Set the chromedriver.exe file to JAVA class
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		// Launch ChromeBrowser and disable notificationss
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);

		
		// Open URL
		driver.get("https://www.nykaa.com/");
		
		// Maximize Browser
		driver.manage().window().maximize();
		
		//Mouseover on Brands and Mouseover on Popular
		WebElement brands = driver.findElementByXPath("//a[text()='brands']");
		Actions brand1=new Actions(driver);
		brand1.moveToElement(brands).perform();
		Thread.sleep(2000);
		
		WebElement popular = driver.findElementByXPath("//a[text()='Popular']");
		Actions popular1=new Actions(driver);
		popular1.moveToElement(popular).click().perform();
		
		// Click L'Oreal Paris
		driver.findElementByXPath("(//li[@class='brand-logo menu-links'])[5]").click();
		
		// Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> window= driver.getWindowHandles();
		List<String> windowhandle= new ArrayList<String>(window);
		driver.switchTo().window( windowhandle.get(2));
		System.out.println("window title is :"+driver.getTitle());
		
		// Click sort By and select customer top rated
		driver.findElementByXPath("//span[text()='Sort By : ']").click();
		driver.findElementByXPath("(//span[text()='customer top rated'])[1]").click();
		Thread.sleep(2000);
		
		// Click Category and click Shampoo
		
		Thread.sleep(2000);
		driver.findElementByXPath("//div[text()='Category']").click();
		driver.findElementByXPath("(//span[contains(text(),'Shampoo')])[1]").click();
		Thread.sleep(2000);
		
		// check whether the Filter is applied with Shampoo

		String shampoofilter = driver.findElementByXPath("//li[text()='Shampoo']").getText();
		if(shampoofilter.contains("Shampoo")) {
			System.out.println("filter applied");
		}
		else
		{
			System.out.println("filter is not applied");
		}
		
		
		// Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElementByXPath("//span[contains(text(),'Paris Colour Protect Shampoo')]").click();
		Thread.sleep(2000);
		
		// GO to the new window and select size as 175ml
		Set<String> windowHandle = driver.getWindowHandles();
		List<String> windowHandle2=new ArrayList<String>(windowHandle);
		Thread.sleep(3000);
		driver.switchTo().window(windowHandle2.get(3));
		driver.findElementByXPath("//span[text()='175ml']").click();
		
		//Print the MRP of the product
		 String cost = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText();
		 System.out.println(cost);
		 
		//Click on ADD to BAG
		 driver.findElementByXPath("(//button[@class='combo-add-to-btn prdt-des-btn js--toggle-sbag nk-btn nk-btn-rnd atc-simple m-content__product-list__cart-btn  '])[1]").click();
		
		 // Go to Shopping Bag 
		 driver.findElementByXPath("//div[@class='AddBagIcon']").click();
		  
		//Print the Grand Total amount
		 String total = driver.findElementByXPath("//div[@class='value medium-strong']").getText();
		 System.out.println("Total Amount is :" + total);
		 
		//Click Proceed
        driver.findElementByXPath("//button[@class='btn full fill no-radius proceed ']").click();
		//Click on Continue as Guest
        driver.findElementByXPath("//button[@class='btn full big']").click();
		// Print the warning message (delay in shipment)
        String text = driver.findElementByXPath("//div[@class='message']").getText();
        System.out.println("Warning message captured is:"+text);
		  
		// Close all windows
        driver.quit();


	}

}
