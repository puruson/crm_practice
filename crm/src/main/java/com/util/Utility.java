package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.Base;
import com.relevantcodes.extentreports.LogStatus;

public class Utility extends Base
{
	public static String ssname_Fail;
	public static String ssname_Pass;

	public static String date_Time(){
    	dt = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");
    	String time = sdf.format(dt);
    	return time;
    }
	
    public static void screenshot_Fail(String failureMsg) throws Throwable{
    	String ssname_Fail = date_Time().toString();
    	File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	File dest = new File("\\Screenshots\\"+ssname_Fail+".png");
    	FileUtils.copyFile(scr, dest);
    	et.log(LogStatus.FAIL, failureMsg+et.addScreenCapture("\\Screenshots\\"+ssname_Fail+".png"));
               	// addScreenCapture("Image path")
    }
    
    public static void screenshot_Pass(String passMsg) throws Throwable{
    	String ssname_Pass = date_Time().toString();
    	File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	File dest = new File("\\Screenshots\\"+ssname_Pass+".png");
    	FileUtils.copyFile(scr, dest);
    	et.log(LogStatus.PASS, passMsg+et.addScreenCapture("\\Screenshots\\"+ssname_Pass+".png"));
    }
    
    public static void click(WebElement element){
      element.click();
    }
    
    public static void fill(WebElement element, String text){
    	element.sendKeys(text);
    }
    
    public static void wait_N_click(WebElement element){
    	    WebDriverWait wt=new WebDriverWait(driver,10);
 		    wt.until(ExpectedConditions.visibilityOf(element));
    	    element.click();
    }
    
    public static void scrollToElement(WebElement element){
    	JavascriptExecutor js=(JavascriptExecutor)driver;
    	js.executeScript("arguments[0].scrollIntoView();", element);
    }
    
    public static void scrollToElement_N_click(WebElement element){
    	JavascriptExecutor js=(JavascriptExecutor)driver;
    	js.executeScript("arguments[0].scrollIntoView();", element);
    	element.click();
    }
    
    public static void mouseHover(WebElement ele){
    	Actions act = new Actions(driver);
 	    act.moveToElement(ele).build().perform();
    }
    
    public static void selectByText(WebElement ele, String text){
    	Select dpdwn = new Select(ele);
    	dpdwn.selectByVisibleText(text);
    }
    
    public static void selectByValue(WebElement ele, String value){
    	Select dpdwn = new Select(ele);
    	dpdwn.selectByValue(value);
    }
    
    public static void selectByIndex(WebElement ele, String index){
    	Select dpdwn = new Select(ele);
    	dpdwn.selectByValue(index);
    }
     
    public static void div_Dpdwn(WebElement menu) throws Exception{ 
    	Actions act=new Actions(driver);
    	act.click(menu).build().perform();
    }
           
    public static void readExcel_File(String fileName) throws Exception{
    	File f=new File(fileName);
    	FileInputStream fis=new FileInputStream(f);
    	XSSFWorkbook wb=new XSSFWorkbook(fis);
    	XSSFSheet sh=wb.getSheetAt(0);
    	int RowNum=sh.getLastRowNum();
    }
    
    
} 