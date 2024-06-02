package shopping.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import shopping.testcases.BaseClass;

public class AppTestListener extends BaseClass implements ITestListener{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext context) {
		String reportName = "Test-Report-" + getDateTimeString() + ".html";
		htmlReporter = new ExtentHtmlReporter("./extent-reports/" + reportName);
		htmlReporter.loadXMLConfig("./extent-config.xml");
		
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", System.getProperty("user.name"));
		
		htmlReporter.config().setDocumentTitle("SauceDemo Application Test Report");
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		//System.out.println(context.getName() + " started");
	  }
	public void onFinish(ITestContext context) {
		extent.flush();
		System.out.println(context.getName() + " finished");
	  }
	public void onTestStart(ITestResult result) {
	    System.out.println(result.getName() + " test started");
	  }

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName(),ExtentColor.GREEN));
		CaptureScreenshot(result.getName() + "_success");
		System.out.println(result.getName() + " test is successful");
	  }

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(),ExtentColor.RED));
		CaptureScreenshot(result.getName() + "_failure");
		System.out.println(result.getName() + " test is failed");
	  }
	  
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName(),ExtentColor.AMBER));
		System.out.println(result.getName() + " test is skipped");
	  }

}
