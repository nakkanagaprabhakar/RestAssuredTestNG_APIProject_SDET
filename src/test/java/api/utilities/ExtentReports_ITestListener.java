package api.utilities;

import org.testng.ITestContext;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReports_ITestListener implements ITestListener {
	
	public ExtentSparkReporter sparkReporter; 
	public ExtentTest test;
	public ExtentReports extent; 
	
	String repName;
	
	public void onStart (ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());   //time stamp 
		repName="Test-Report-"+timeStamp+".html";
		
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"\\reports\\"+repName);    //specify location of the report
		
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");     // Title of report 
		sparkReporter.config().setReportName("UsersAPI");           // name of the report 

		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pest Store Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("User", "Prabhakar");
	
	}
	
	public void onTestSuccess (ITestResult result) 
	{
		test = extent.createTest(result.getName());
		test.assignCategory (result.getMethod().getGroups());
		test.createNode(result.getName()); test.log(Status. PASS, "Test Passed");
		
		System.out.println("Test Passed");
		
		
	}
		
	public void onTestFailure (ITestResult result)
	{
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
		System.out.println("Test Passed");
		
	}
	
	
	public void onTestSkipped (ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory (result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish (ITestContext testContext)
	{
		extent.flush();
		
		
	}
	


}













