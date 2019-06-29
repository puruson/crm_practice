package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Base
{
    public static WebDriver driver;
    public static Properties prop;
    public static ExtentReports er;
	public static ExtentTest et;
	public static WebDriverWait wt;
	public static Actions act;
	public static Date dt;
	
   public Base()
   {
	   try 
	   {
		   prop = new Properties();
		   FileInputStream fis=new FileInputStream("E:\\batch239\\crm\\src\\main\\java\\com\\config\\config.properties");
		   prop.load(fis);
	   }
	   catch(Exception ex) 
	   { 
		   System.out.println(ex.getMessage()); 
	   } 
	  
   }
   
   @SuppressWarnings("deprecation")
   public static void initialization()
   {	   
	   String browserName = prop.getProperty("browser");
	   
	   if(browserName.equalsIgnoreCase("chrome"))
	   {
		   DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		   capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, false);
		   System.setProperty("webdriver.chrome.driver",prop.getProperty("chromedriver"));
		   driver=new ChromeDriver(capabilities);  
	   }
	   else if(browserName.equalsIgnoreCase("firefox"))
	   {
		   System.setProperty("webdriver.gecko.driver",prop.getProperty("firefoxdriver"));
		   driver=new FirefoxDriver();
	   }
	   else 
	   {
		   et.log(LogStatus.ERROR, "Unknown Browser");
	   }
	   	   
	   //driver.manage().deleteAllCookies();
	   driver.get(prop.getProperty("url"));
	   driver.manage().window().maximize();
	   driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("Page_TimeOut")), TimeUnit.SECONDS);
	   driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("Implicit_wait")), TimeUnit.SECONDS);        
   }
   
   public static void wait(WebElement element)
   {
	    WebDriverWait wt=new WebDriverWait(driver,15);
		wt.until(ExpectedConditions.visibilityOf(element));
   }
   
   public static void create_ext_Report(String reportName)
   {
	   er=new ExtentReports("ExtentReports\\"+reportName+".html",false);
   }
   
   public static void create_ext_Test(String testName)
   {
	   et=er.startTest(testName);
   }

   public static void close_ext_Report()
   {
   	er.endTest(et);
   	er.flush();
   }
   
   public void failed(String testMethodName)
   {
	   try {
		     File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
   	         File dest = new File("Screenshots\\"+testMethodName+"_"+".png");
   	         FileUtils.copyFile(scr, dest);
    	     et.log(LogStatus.FAIL, testMethodName+"() method is Failed"+
   	                    et.addScreenCapture("Screenshots\\"+testMethodName+"_"+".png"));
	       }
	   catch(Exception ex)
	       {
		       System.out.println(ex.getMessage());
	       } 	   
    }
   
   public void pass(String testMethodName)
   {
	   try {
		     File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
   	         File dest = new File("Screenshots\\"+testMethodName+"_"+".png");
   	         FileUtils.copyFile(scr, dest);
    	     et.log(LogStatus.PASS, testMethodName+"() method is Pass"+
   	                    et.addScreenCapture("Screenshots\\"+testMethodName+"_"+".png"));
	       }
	   catch(Exception ex)
	       {
		       System.out.println(ex.getMessage());
	       } 	   
    }
 
}
