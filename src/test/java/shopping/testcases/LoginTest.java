package shopping.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import shopping.pages.LoginPage;
import shopping.utilities.ExcelData;

@Listeners(shopping.listeners.AppTestListener.class)
public class LoginTest extends BaseClass{
	
	@Test(dataProvider="LoginData")
	public void test_login(String user, String pass)
	{
		lp.LoginFeatureTest(user, pass);
	}
	
	@DataProvider(name="LoginData")
	public Object[][] getLoginData()
	{
		ExcelData exl = new ExcelData("./TestData/AppTestData.xlsx");
		int rows = exl.getRowCount(0);
		
		Object[][] data = new Object[rows][2];
		
		for(int i=0;i< rows; i++)
		{
			data[i][0] = exl.getData(0, i, 0);
			data[i][1] = exl.getData(0, i, 1);
		}
		return data;
	}
	
}
