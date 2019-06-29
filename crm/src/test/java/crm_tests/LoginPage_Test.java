package crm_tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Base;
import com.pages.Homepage;
import com.pages.LoginPage;
import com.util.Utility;

public class LoginPage_Test extends Base
{
	LoginPage lp;
	Homepage hp;
	
	LoginPage_Test()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		initialization();
		lp = new LoginPage();
		hp = new Homepage();
	}
	
	@Test
	public void loginTest() throws Exception
	{
			create_ext_Report("LoginPage");
			create_ext_Test("Login functionality checking");
			Utility.readExcel_File("E:\\batch239\\crm\\src\\main\\java\\com\\ExcelData\\Login_crm.xlsx");
			lp.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void close()
	{
		driver.quit();
		close_ext_Report();
	}

}
