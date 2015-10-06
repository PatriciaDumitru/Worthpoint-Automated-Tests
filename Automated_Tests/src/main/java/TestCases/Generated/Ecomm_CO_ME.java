package TestCases.Generated;

import AutomationFramework.CommonTask;
import AutomationFramework.TestSuite;
import PageObjects.EcommPage;
import java.io.File;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_CO_ME {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private By poField = By.id("BulkOrderPoNumber");
  private By dateField = By.id("required_date_0");
  private By buyersField = By.id("s2id_BuyerId");
  private By articleField = By.id("s2id_BulkOrderLine0ArticleId");
  private By shadeCodeField = By.id("s2id_BulkOrderLine0ShadeId");
  private By quantityField = By.id("quantity0");
  private By contractPOField = By.id("txtContract0");
  private By lineRefField = By.id("txtContractLine0");
  private By overlayContent = By.cssSelector("#BulkOrderLineViewUplodErrorListForm");
  private By cancelButton = By.id("cancel1");
  
  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    baseUrl = "https://qawcs.coatscolourexpress.com/";
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test //Manual Entry Page :: Contract order expecting "No matching contract reference" error
  public void COME1() throws Exception {
    driver.get(baseUrl + "/");
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.id("UserUsername"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.id("UserUsername")).clear();
    driver.findElement(By.id("UserUsername")).sendKeys("joecontract@coats.com");
    
    driver.findElement(By.id("UserPassword")).clear();
    driver.findElement(By.id("UserPassword")).sendKeys("password");
    
    driver.findElement(By.cssSelector("input.loginbutton")).click();
    
    driver.findElement(By.xpath("//div[@id='wrapper']/div[4]/a[2]/img")).click();
    
    driver.findElement(By.xpath("//area[4]")).click();
    
    EcommPage eComm = new EcommPage(driver);
    eComm.clickManualEntry();
    CommonTask.waitForPageLoad(driver);
    
    //Take a screenshot
    File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\1Manual Entry Page.png"));
    
    CommonTask.setSearchField(driver, buyersField, TestSuite.custDetails[3]);
    
    driver.findElement(poField).clear();
    String po = CommonTask.generatePO("contract");
    driver.findElement(poField).sendKeys(po);
    
    CommonTask.waitForPageLoad(driver);
    
    //Take a screenshot
    File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\2Cust Details entered.png"));
    
    CommonTask.setSearchField(driver, articleField, TestSuite.conOrdArticle);
    CommonTask.setSearchField(driver,shadeCodeField,TestSuite.conOrdShadeCode);
    driver.findElement(quantityField).clear();
    driver.findElement(quantityField).sendKeys(String.valueOf(TestSuite.conOrdQty));
    CommonTask.setDateField(driver, dateField);
    driver.findElement(contractPOField).clear();
    driver.findElement(contractPOField).sendKeys(TestSuite.conOrdPO);
    driver.findElement(lineRefField).clear();
    driver.findElement(lineRefField).sendKeys(TestSuite.conOrdLineRef);
    
    //Take a screenshot
    File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\3Material details entered.png"));
    
    driver.findElement(By.id("next")).click();
    
    Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
    assertTrue(alert.getText().matches("^Do you want to SUBMIT the order[\\s\\S]$"));
    alert.accept();
    
    CommonTask.waitForPageLoad(driver);
    
    //Take a screenshot
    File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\4Error expected - Confirmation page.png"));
    
    Actions scroller = new Actions(driver);
    scroller.moveToElement(driver.findElement(cancelButton)).build().perform();
    
    //Take a screenshot
    File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile7,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\5Error expected - Confirmation page scrolled.png"));
    
    driver.findElement(By.linkText("Line with Error")).click();
    CommonTask.waitForOverlay(driver,overlayContent);
    
    //Take a screenshot
    File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\6Error line view.png"));
    
    CommonTask.closeView(driver);
    
    Alert alert2 = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
    assertTrue(alert2.getText().matches("^Do you want to cancel the current operation[\\s\\S]$"));
    alert2.accept();
    
    driver.findElement(cancelButton).click();
    CommonTask.waitForPageLoad(driver);
    
  }

  @Test //Manual Entry Page :: Contract Order expecting validation success
  public void COME2() throws Exception {
      driver.get(baseUrl + "/");
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.id("UserUsername"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.id("UserUsername")).clear();
    driver.findElement(By.id("UserUsername")).sendKeys("joecontract@coats.com");
    
    driver.findElement(By.id("UserPassword")).clear();
    driver.findElement(By.id("UserPassword")).sendKeys("password");
    
    driver.findElement(By.cssSelector("input.loginbutton")).click();
    
    driver.findElement(By.xpath("//div[@id='wrapper']/div[4]/a[2]/img")).click();
    
    driver.findElement(By.xpath("//area[4]")).click();
    
    EcommPage eComm = new EcommPage(driver);
    eComm.clickManualEntry();
    CommonTask.waitForPageLoad(driver);  

    CommonTask.setSearchField(driver, buyersField, TestSuite.custDetails[3]);
    
    driver.findElement(poField).clear();
    String po = CommonTask.generatePO("contract");
    driver.findElement(poField).sendKeys(po);
    
    CommonTask.waitForPageLoad(driver);
    
    CommonTask.setSearchField(driver, articleField, TestSuite.conOrdArticle);
    CommonTask.setSearchField(driver,shadeCodeField,TestSuite.conOrdShadeCode);
    driver.findElement(quantityField).clear();
    driver.findElement(quantityField).sendKeys(String.valueOf(TestSuite.conOrdQty));
    CommonTask.setDateField(driver, dateField);
    driver.findElement(contractPOField).clear();
    driver.findElement(contractPOField).sendKeys(TestSuite.conOrdPO);
    driver.findElement(lineRefField).clear();
    driver.findElement(lineRefField).sendKeys(TestSuite.conOrdLineRef);
    
    driver.findElement(By.id("next")).click();
    
    Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
    assertTrue(alert.getText().matches("^Do you want to SUBMIT the order[\\s\\S]$"));
    alert.accept();
    
    CommonTask.waitForPageLoad(driver);
    
    //Take a screenshot
    File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\7Validadtion success - Confirmation page.png"));
    
    Actions scroller = new Actions(driver);
    scroller.moveToElement(driver.findElement(cancelButton)).build().perform();
    
    //Take a screenshot
    File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\8Validadtion success - Confirmation page scrolled.png"));
    
    driver.findElement(cancelButton).click();
    CommonTask.waitForPageLoad(driver);
    
  }
  
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

