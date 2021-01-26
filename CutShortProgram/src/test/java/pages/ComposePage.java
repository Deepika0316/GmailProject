package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComposePage 
{
 private RemoteWebDriver driver;
 private WebDriverWait wait;
 
 @FindBy(how=How.XPATH,using="//div[text()='Compose']")
 private WebElement compose;
 
 @FindBy(how=How.XPATH,using="//div[text()='New Message']")
 private WebElement newheader;
 
 @FindBy(how=How.XPATH,using="//textarea[@aria-label='To']")
 private WebElement to;
 
 @FindBy(how=How.NAME,using="subjectbox")
 private WebElement subject;
 
 @FindBy(how=How.XPATH,using="//div[@aria-label='Message Body']")
 private WebElement emailbody;
 
 @FindBy(how=How.XPATH,using="//div[@command='Files']")
 private WebElement file;
 
 @FindBy(how=How.XPATH,using="//div[text()='Send']")
 private WebElement send;
 
 @FindBy(how=How.XPATH,using="//span[text()='Message sent.']")
 private WebElement sentmsg;
 
 public ComposePage(RemoteWebDriver driver,WebDriverWait wait)
 {
	 this.driver=driver;
	 PageFactory.initElements(driver, this);
	 this.wait=wait;
 }
 
 public void composemail(String toaddr,String sub,String msgbody,String filepath) throws Exception
 {
	 wait.until(ExpectedConditions.visibilityOf(compose)).click();
	 	 wait.until(ExpectedConditions.visibilityOf(newheader));
	 	 
		 wait.until(ExpectedConditions.visibilityOf(to)).sendKeys(toaddr);
		 wait.until(ExpectedConditions.visibilityOf(subject)).sendKeys(sub);
		 wait.until(ExpectedConditions.visibilityOf(emailbody)).sendKeys(msgbody);
		 wait.until(ExpectedConditions.elementToBeClickable(file)).click();
		 StringSelection ss=new StringSelection(filepath);
		 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		 Robot r=new Robot();
		 r.keyPress(KeyEvent.VK_CONTROL);
		 r.keyPress(KeyEvent.VK_V);
		 r.keyRelease(KeyEvent.VK_CONTROL);
		 r.keyRelease(KeyEvent.VK_V);
		 r.keyPress(KeyEvent.VK_ENTER);
		 r.keyRelease(KeyEvent.VK_ENTER);
		 
		 wait.until(ExpectedConditions.visibilityOf(send)).click();
	 
}
 
 public boolean isMessageSentVisible()
 {
	 try
	 {
		 wait.until(ExpectedConditions.visibilityOf(sentmsg));
		 return(true);
	 }
	 catch(Exception ex)
	 {
		 return(false);
	 }
 }
	
	
}
