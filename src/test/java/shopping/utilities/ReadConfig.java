package shopping.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	public ReadConfig()
	{
		File src = new File("./configuration/config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error is " + e.getMessage());
		}
	}
	
	public String getURL()
	{
		return pro.getProperty("url");
	}
	public String getUsername()
	{
		return pro.getProperty("uname");
	}
	public String getPassword()
	{
		return pro.getProperty("pwd");
	}
	public String getBrowser()
	{
		return pro.getProperty("browser");
	}

}
