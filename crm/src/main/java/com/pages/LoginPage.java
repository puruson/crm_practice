package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.Base;

public class LoginPage extends Base{
	@FindBy(name="username")
	public WebElement username;
	
	@FindBy(name="password")
	public WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	public WebElement loginBtn;
	
	public LoginPage(){
		PageFactory.initElements(driver,this);
	}
	
	public String validateLoginPageTitleTest(){
		return driver.getTitle();
	}
	
	public void login(String un, String pwd) throws Exception
	{
		username.sendKeys(un);
		password.sendKeys(pwd);
		Thread.sleep(1000);
		loginBtn.click();
	}

}
