package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.ComposePage;
import pages.GmailLoginPage;
import utilities.ExcelUtility;
import utilities.TestUtility;

public class LoginTest {

	public static void main(String[] args) throws Exception 
	{
		
	TestUtility tu=new TestUtility();
	String fpath=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData.xlsx";
	ExcelUtility eu=new ExcelUtility(fpath);
	try
	{
	eu.openSheet("Sheet1");
int rc=	eu.getRowCount();
int cc=eu.getColCount();
eu.createResultColumn(0, cc);
for(int i=1;i<rc;i++)
{
String bn=	eu.getCellValue(i, 0);
String uid=eu.getCellValue(i, 1);
String pwd=eu.getCellValue(i, 2);
RemoteWebDriver driver=tu.openbrowser(bn);
WebDriverWait wait=tu.defineWait(driver);
GmailLoginPage gp=new GmailLoginPage(driver,wait);
ComposePage cp=new ComposePage(driver,wait);

tu.launchSite();
gp.enteruserid(uid);
gp.password(pwd);

try
{
if(gp.isComposeDisplayed())
{
	eu.setCellValue(i, cc, "Login done successfully");
}
else
{
	eu.setCellValue(i, cc, "Login failed");
}


eu.saveAndCloseExcel();

tu.closeSite();
}
catch(Exception ex)
{
	System.out.println("exc : "+ex.getMessage());
	eu.saveAndCloseExcel();
}
eu.openSheet("Sheet2");
int rc1=eu.getRowCount();
int cc1=eu.getColCount();
for(int j=1;j<rc1;j++)
{
String toaddr=	eu.getCellValue(j, 0);
String sub=eu.getCellValue(j, 1);
String msgbody=eu.getCellValue(j, 2);
String filepath=eu.getCellValue(j, 3);
cp.composemail(toaddr, sub, msgbody, filepath);
}
if(cp.isMessageSentVisible())
{
	eu.setCellValue(1, cc1, "Mail sent");
}
else
{
	eu.setCellValue(1, cc1, "Mail not sent");
}
}
}
catch(Exception ex)
		{
			System.out.println("exception is "+ex.getMessage());
			eu.saveAndCloseExcel();
			
		}
	
	

	}

}
