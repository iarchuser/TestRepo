package shopping.testcases;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import shopping.pages.LoginPage;
import shopping.utilities.ReadConfig;

public class BaseClass {
	LoginPage lp;
	ReadConfig conf = new ReadConfig();
	public String url = conf.getURL();
	public String uname = conf.getUsername();
	public String pwd = conf.getPassword();
	public static WebDriver driver;
	public String browser = conf.getBrowser();
	@BeforeClass
	public void ApplicationLaunch()
	{
		if(browser.equals("ff"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		if(browser.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		lp = new LoginPage(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		lp.LoginToApplication(uname, pwd);
	}
	
	@AfterClass
	public void closeApplication()
	{
		driver.quit();
	}
	
	public void CaptureScreenshot(String TcaseName)
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File("./screenshots/" + TcaseName + "_" + getDateTimeString() + ".png");
		try {
			FileUtils.copyFile(source, target);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error - " + e.getMessage());
		}
	}
	
	public String getDateTimeString()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat("ddMMyyyy_HHmmss");
		return sf.format(cal.getTime());
	}
}
