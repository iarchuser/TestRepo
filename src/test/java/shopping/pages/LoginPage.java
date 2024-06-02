package shopping.pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage {
	WebDriver ldriver;
	public LoginPage(WebDriver rdriver)
	{
		ldriver = rdriver;
	}
	
	By txt_user = By.name("user-name");
	By txt_pass = By.name("password");
	By btn_login = By.id("login-button");
	By lbl_products = By.className("product_label");
	By openMenu =By.xpath("//button[text()='Open Menu']");
	By logOut = By.xpath("//a[text()='Logout']");
	
	public void LoginToApplication(String username, String passwd)
	{	
		ldriver.findElement(txt_user).sendKeys(username);
		ldriver.findElement(txt_pass).sendKeys(passwd);
		ldriver.findElement(btn_login).click();
		Assert.assertTrue(ldriver.findElement(lbl_products).isDisplayed());
	}
	
	public void LoginFeatureTest(String username, String passwd)
	{	
		List<WebElement> products = ldriver.findElements(lbl_products);
	
		if(products.size()>0)
		{
			ldriver.findElement(openMenu).click();
			ldriver.findElement(logOut).click();
		}
		ldriver.findElement(txt_user).clear();
		ldriver.findElement(txt_user).sendKeys(username);
		ldriver.findElement(txt_pass).clear();
		ldriver.findElement(txt_pass).sendKeys(passwd);
		ldriver.findElement(btn_login).click();
		Assert.assertTrue(ldriver.findElement(lbl_products).isDisplayed());
	}
}
