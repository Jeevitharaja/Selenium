package Seleniumhandson;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;



public class Day1Myntra {
	


	public static void main(String[] args) throws InterruptedException {
		        // Set the chromedriver.exe file to JAVA class
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				
				// Launch ChromeBrowser and disable notificationss
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				ChromeDriver driver = new ChromeDriver(options);
		
				
				// Open URL
				driver.get("https://www.myntra.com");
				
				// Maximize Browser
				driver.manage().window().maximize();
				
				// Mouse Over on Women Option
				Actions a = new Actions(driver);
				WebElement tar1 = driver.findElementByXPath("//a[text()='Women']");
				a.moveToElement(tar1).perform();
				
				// Sleep for 3000 ms
				Thread.sleep(3000);
				
				// Click On Jackets & Coats 
				driver.findElementByXPath("//a[text()='Jackets & Coats']").click();
				
				// Find Total count of items (Jacket & Coat)
				String items = driver.findElementByXPath("//span[@class='title-count']").getText();
				int total = Integer.parseInt(items.replaceAll("\\D", ""));
				
				
				// Calculate count of Category Jackets 
				String jacketcount = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
				int jackets = Integer.parseInt(jacketcount.replaceAll("\\D", ""));
				
				// Calculate count of Category Coats
				String coatcount = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
				int coats = Integer.parseInt(coatcount.replaceAll("\\D", ""));
				
				// Compare Total count with Categories count
				if (total == (jackets + coats)) 
				{
					        System.out.println("Total count matches");
							}
				else {
								System.out.println("Total count not matches");
							}
				
				// Check Coats Category
				driver.findElementByXPath("(//label[@class='common-customCheckbox vertical-filters-label'])[2]").click();
				
				// Click + More option under BRAND
				driver.findElementByXPath("//div[@class='brand-more']").click();
				
				// Select MANGO Brand
				driver.findElementByXPath("//input[@placeholder='Search brand']").sendKeys("MANGO");
				driver.findElementByXPath("//label[@class=' common-customCheckbox']").click();
				
				// Close pop-up Box
				driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();

				// Sleep for 6000 ms
				Thread.sleep(6000);
				
				// Confirm all the Coats are of brand MANGO
				List<WebElement> mangobrand = driver.findElements(By.xpath("//h3[@class='product-brand']"));
				
				for (WebElement wb : mangobrand) {
					String b = wb.getText();
					if (!b.contentEquals("MANGO")) {
						System.out.println("Brand other that Mango exists");
					}
				}
				
				// Sort by Better Discount
				WebElement tar2 = driver.findElementByXPath("//div[@class='sort-sortBy']");
				a.moveToElement(tar2).perform();
				
				// Sleep for 3000 ms
				Thread.sleep(3000);
				
				// Print the Price of Better Discount Item
				driver.findElementByXPath("//label[text()='Better Discount']").click();
				List<WebElement> list = driver.findElements(By.xpath("//span[@class='product-discountedPrice']"));
				WebElement price = list.get(0);
				String least = price.getText();
				
				
				// Select the item having Better Discount
				price.click();
				int n = Integer.parseInt(least.replaceAll("\\D", ""));
				System.out.println("Least Price of an item = Rs." + n);
				 
				// Mouse over on size of the first item
				Set<String> win = driver.getWindowHandles();
				ArrayList<String> winlis = new ArrayList<String>(win);
				driver.switchTo().window(winlis.get(2));
				driver.findElementByXPath("//p[text()='M']").click();
				
				// Click on WishList Now
				driver.findElementByXPath("//span[text()='WISHLIST NOW']").click();
				
				// Close Browser
				driver.quit();
					
			}
		}
