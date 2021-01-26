package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailLoginPage 
{
private RemoteWebDriver driver;
private WebDriverWait wait;

@FindBy(how=How.XPATH,using="//input[@type='email']")
private WebElement userid;

@FindBy(how=How.XPATH,using="//span[text()='Next']/parent::*")
private WebElement next;

@FindBy(how=How.XPATH,using="//input[@type='password']")
private WebElement password;

@FindBy(how=How.XPATH,using="//div[text()='Compose']")
private WebElement compose;

public GmailLoginPage(RemoteWebDriver driver,WebDriverWait wait)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
	this.wait=wait;
}

public void enteruserid(String id)
{
	wait.until(ExpectedConditions.visibilityOf(userid));
	userid.sendKeys(id);
	wait.until(ExpectedConditions.visibilityOf(next));
	next.click();
}

public void password(String pwd)
{
	wait.until(ExpectedConditions.visibilityOf(password));
	password.sendKeys(pwd);
	next.click();
	
}
public boolean isComposeDisplayed()
{
try
{
	wait.until(ExpectedConditions.visibilityOf(compose));
	return(true);
}
catch(Exception ex)
{
	return(false);
}

}

}
