package TestCases.Generated;

import AutomationFramework.CommonTask;
import AutomationFramework.TestSuite;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_MappingAlert;
import PageObjects.Ecomm_UploadOrderPage;
import java.io.File;
import java.io.IOException;
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

public class Ecomm_CO_UORT {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public By contentLocator = By.id("BulkOrderLineViewUplodErrorListForm");
  public By cancelButton = By.id("cancel1");
  public By confirmButton = By.id("submit1");
  
  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    baseUrl = "https://qawcs.coatscolourexpress.com/";
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test //Upload Orders Page :: Realtime contract order upload, expecting "No matching reference" error
  public void CORT1() throws Exception {
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Upload Order Contract Order #1", "CO_UO_1", TestSuite.validCustUsername, TestSuite.validCustPassword);
    
      System.out.println("Navigating to Upload Order...");
    
    Ecomm_UploadOrderPage uoPage = eComm.clickUploadOrder();
    uoPage.waitForLoad();
    
      System.out.println("Upload Order reached. Setting filepath and upload method...");
    
    driver.findElement(By.id("filename")).clear();
    driver.findElement(By.id("filename")).sendKeys(TestSuite.co_uploadOrderFilepath);
    
    driver.findElement(By.id("bulkuploadprocess1")).click();
    
      System.out.println("Method set. Submitting...");
    
    //Take a screenshot
    File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\Contract Order\\1Filepath entered.png"));
    
    driver.findElement(By.cssSelector("input.btn-submit-upload")).click();
    
      System.out.println("Submitted. Select 'no' in alert...");
    
    Ecomm_MappingAlert mapAlert = new Ecomm_MappingAlert(driver);
    mapAlert.pressNo();
    
    System.out.println("Mapping page reached. Setting mapping...");
    
    new Select(driver.findElement(By.id("BulkOrderLineCustomerMaterialNo"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderLineArticleId"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderBuyerId"))).selectByVisibleText("Requestor Name");
    new Select(driver.findElement(By.id("BulkOrderPoNumber"))).selectByVisibleText("Customer PO Number");
    new Select(driver.findElement(By.id("BulkOrderLineRequiredDate"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderLineFinishId"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderWarehouseInstruction"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderStyle"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderBuyerSalesOrderno"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderLineProdStyleNo"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderLineOtherinfo"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderLineContract"))).selectByVisibleText("Contract PO No");
    new Select(driver.findElement(By.id("BulkOrderLineCustomerPrice"))).selectByVisibleText("N/A");
    
    System.out.println("Mapping set. Confirming...");
    
    //Take a screenshot
    File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\Contract Order\\2Mapping page.png"));
    
    driver.findElement(By.id("trigger")).click();
    
    try {
        Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
        assertTrue(alert.getText().matches("^Do you want to Upload the file[\\s\\S]$"));
        alert.accept();
    } catch(Exception e) {
        System.out.println("No alert after mapping confirmation");
    }
    
    try {
        Alert alert3 = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
        alert3.accept();
    } catch (Exception e) {
        System.out.println("No additional alerts.");
    }

    CommonTask.waitForPageLoad(driver);
    
    //Take a screenshot
    File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\Contract Order\\3Error expected - Confirmation Page.png"));
    
    Actions scroller = new Actions(driver);
    scroller.moveToElement(driver.findElement(cancelButton)).build().perform();
          
    WebElement waitForButton = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(cancelButton));
    
    //Take a screenshot
    File scrFile8 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile8,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\Contract Order\\4Error expected - Confirmation Page scrolled.png"));
    
      System.out.println("Viewing error...");
    
    driver.findElement(By.linkText("Line with Error")).click();
    CommonTask.waitForOverlay(driver,contentLocator);
    
    System.out.println("View displayed. Closing view...");
    
    //Take a screenshot
    File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\Contract Order\\5Error View.png"));
    
    Actions action = new Actions(driver);
    action.sendKeys(Keys.ESCAPE).build().perform();
    
    System.out.println("View closed.");
    
    try {
        Alert alert2 = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
        assertTrue(alert2.getText().matches("^Do you want to cancel the current operation[\\s\\S]$"));
        alert2.accept();
    } catch (Exception e) {
        System.out.println("No alert upon closing view.");
    }
      
        driver.findElement(cancelButton).click();
        CommonTask.waitForPageLoad(driver);
        System.out.println("Order cancelled, as Contract Order Call-off is disabled");
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\Contract Order\\6Order cancelled.png"));

  }

  @Test //Upload Orders Page :: Realtime contract order upload, expecting validation success
  public void CORT2() throws IOException {
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Upload Order Contract Order #2", "CO_ME_2", TestSuite.validCustUsername, TestSuite.validCustPassword);
    
    Ecomm_MainPage ecomm = new Ecomm_MainPage(driver);
    ecomm.clickUploadOrder();
    
    driver.findElement(By.id("filename")).clear();
    driver.findElement(By.id("filename")).sendKeys(TestSuite.co_uploadOrderFilepath2);
    
    driver.findElement(By.cssSelector("input.btn-submit-upload")).click();
    
    Ecomm_MappingAlert mapAlert = new Ecomm_MappingAlert(driver);
    mapAlert.pressNo();
    
    new Select(driver.findElement(By.id("BulkOrderLineCustomerMaterialNo"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderLineArticleId"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderBuyerId"))).selectByVisibleText("Requestor Name");
    new Select(driver.findElement(By.id("BulkOrderPoNumber"))).selectByVisibleText("Customer PO Number");
    new Select(driver.findElement(By.id("BulkOrderLineRequiredDate"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderLineFinishId"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderWarehouseInstruction"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderStyle"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderBuyerSalesOrderno"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderLineProdStyleNo"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderLineOtherinfo"))).selectByVisibleText("N/A");
    new Select(driver.findElement(By.id("BulkOrderLineContract"))).selectByVisibleText("Contract PO No");
    new Select(driver.findElement(By.id("BulkOrderLineCustomerPrice"))).selectByVisibleText("N/A");
    
    driver.findElement(By.id("trigger")).click();
    
    Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
    assertTrue(alert.getText().matches("^Do you want to Upload the file[\\s\\S]$"));
    alert.accept();
    
    try {
        Alert alert3 = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
        alert3.accept();
    } catch (Exception e) {
        System.out.println("Alert not found");;
    }
    
    
    CommonTask.setSearchField(driver, By.id("s2id_BuyerId_0"), TestSuite.conOrdDetails[3]);
    
    //Take a screenshot
    File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\Contract Order\\7Validation success - Confirmation page.png"));
   
    Actions scroller = new Actions(driver);
    scroller.moveToElement(driver.findElement(cancelButton)).build().perform();

    WebElement waitForPresence = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(cancelButton));
    
    //Take a screenshot
    File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile6,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\Contract Order\\8Validation success - Confirmation page scrolled.png"));
    
    if (TestSuite.contractOrderCallOff) {
        driver.findElement(confirmButton).click();
        CommonTask.waitForPageLoad(driver);
        System.out.println("Order confirmed.");
        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\Contract Order\\9Order confirmed.png"));
    } else {
        driver.findElement(cancelButton).click();
        CommonTask.waitForPageLoad(driver);
        System.out.println("Order cancelled, as Conract Call-off is disabled");
        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(TestSuite.screenshotsFilepath+"\\EComm\\Orders\\Upload Order\\Contract Order\\9Order cancelled.png"));
    }
    
    
  }
  
  @After
  public void tearDown() throws Exception {
    driver.close(); 
      driver.quit();
      System.out.println("----------------------------------------------------");
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

