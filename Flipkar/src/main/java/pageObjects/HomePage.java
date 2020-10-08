package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public static WebDriver driver;
	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[contains(@class,'_2AkmmA')]") private WebElement login_popUp;
	@FindBy(className="LM6RPg") private WebElement searchBox;
	@FindBy(className="vh79eN") private WebElement search;
	@FindBy(xpath="(//select[@class='fPjUPw'])[2]") private WebElement price;
	@FindBy(xpath="//div[contains(@data-tkid,'SEARCH')]") private List<WebElement> moblieOptions;
	
	public WebElement close_LoginpopUp()
	{
		return login_popUp;
	}
	public WebElement get_SearchBox()
	{
		return searchBox;
	}
	public WebElement click_OnSearch()
	{
		return search;
	}
    public WebElement setPrice()
    {
    	return price;
    }
    public List<WebElement> getAllMobileOptions()
    {
		return moblieOptions;
    	
    }
}
