package tests;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.opencsv.CSVWriter;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.HomePage;

public class Search_iphoneTest {
	
	WebDriver driver;
	
	@BeforeTest
	public void openFlipkart() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions co=new ChromeOptions();
		co.addArguments("--disable-notifications");
	    driver=new ChromeDriver(co);
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void search_iphone() throws IOException
	{
		HomePage hp=new HomePage(driver);
		try {
		hp.close_LoginpopUp().click();
		hp.get_SearchBox().click();
		hp.get_SearchBox().sendKeys("iphone");
		hp.click_OnSearch().click();
		Select s=new Select(hp.setPrice());
		s.selectByValue("30000");
		List<WebElement> allMobileOptions = hp.getAllMobileOptions();
		System.out.println(allMobileOptions.size());
					
		String csvOutputFile = "writetest.csv";
		 //check if file exist
	     boolean isFileExist = new File(csvOutputFile).exists();
	     
	     //create a FileWriter constructor to open a file in appending mode
	     CSVWriter writer = new CSVWriter(new FileWriter(csvOutputFile, true), ',');
	     List<String[]> data = new ArrayList<String[]>();	    
	  
	    // write header column if the file did not already exist
	                if(!isFileExist)
	                {
	                	data.add(new String[] {"Device Details, Price, Ratings"});
	                }
	                
	                Iterator<WebElement> it=allMobileOptions.iterator();
	                while(it.hasNext())
	                {
	                	WebElement option = it.next();
	                	String deviceDetails = option.findElement(By.className("_3wU53n")).getText();
	                	String price = option.findElement(By.xpath("//div[contains(@class,'_1vC4OE')]")).getText();
	                	data.add(new String[] {deviceDetails,price});
	                }
	               
	                writer.writeAll(data);    
	                writer.close();
		
		}
		catch(StaleElementReferenceException e)
		{
			hp.close_LoginpopUp().click();
		}
		
	}

}
