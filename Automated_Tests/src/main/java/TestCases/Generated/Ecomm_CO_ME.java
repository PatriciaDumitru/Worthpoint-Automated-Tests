package TestCases.Generated;

import AutomationFramework.Categories;
import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import java.io.File;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@Category(Categories.eComm_Orders_ContractOrder.class)
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
  private By submitButton = By.id("submit1");
  private By cancelButton = By.id("cancel1");
  private By flashMessage = By.id("flashMessage");
  
  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    baseUrl = "https://qawcs.coatscolourexpress.com/";
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Category({Categories.eComm_Orders_ContractOrder_ManualEntry.class,Categories.QuickSuite.class})
  @Test //Manual Entry Page :: Contract order expecting "No matching contract reference" error
  public void COME1() throws Exception {
    
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order COME1", "CO_ME_1", DataItems.validCustUsername, DataItems.validCustPassword);
    
    Ecomm_ManualEntryPage mePage = eComm.clickManualEntry();
    CommonTask.waitForPageLoad(driver);
    
      System.out.println("Manual Entry Page reached. Entering customer details...");
    
    //Take a screenshot
    File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\1Manual Entry Page.png"));
    
    CommonTask.setSearchField(driver, buyersField, DataItems.custDetails[3]);
    
    driver.findElement(poField).clear();
    String po = CommonTask.generatePO("contract");
    driver.findElement(poField).sendKeys(po);
    
      System.out.println("Customer details entered. Entering line details...");
    
    System.out.println("Customer PO No.: "+po);
    
    CommonTask.waitForPageLoad(driver);
    
    //Take a screenshot
    File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\2Cust Details entered.png"));
    
    CommonTask.setSearchField(driver, articleField, DataItems.conOrdArticle);
    CommonTask.setSearchField(driver,shadeCodeField,DataItems.conOrdShadeCode);
    driver.findElement(quantityField).clear();
    driver.findElement(quantityField).sendKeys(String.valueOf(DataItems.conOrdQty));
    CommonTask.setDateField(driver, dateField);
    driver.findElement(contractPOField).clear();
    driver.findElement(contractPOField).sendKeys("random");
    driver.findElement(lineRefField).clear();
    driver.findElement(lineRefField).sendKeys(DataItems.conOrdLineRef);
    
      System.out.println("Line details entered. Pressing next...");
    
    //Take a screenshot
    File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\3Material details entered.png"));
    
    Ecomm_OrderConfirmationPage orderConf = mePage.pressNext();
    orderConf.waitForLoad();
    
      System.out.println("Order confirmation page reached. Viewing error...");
    
    //Take a screenshot
    File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\4Error expected - Confirmation page.png"));
    
    Actions scroller = new Actions(driver);
    scroller.moveToElement(driver.findElement(cancelButton)).build().perform();
    
    //Take a screenshot
    File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile7,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\5Error expected - Confirmation page scrolled.png"));
    
    driver.findElement(By.linkText("Line with Error")).click();
    Ecomm_OrderViewPage viewPage = new Ecomm_OrderViewPage(driver);
    viewPage.waitForContent();
    
      System.out.println("Error view displayed. Closing view...");
    
    //Take a screenshot
    File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\6Error line view.png"));
    
    viewPage.closeView();
    viewPage.waitForInvisibility();
    
    try {
        Alert alert2 = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
        assertTrue(alert2.getText().matches("^Do you want to cancel the current operation[\\s\\S]$"));
        alert2.accept();
    } catch (Exception e) {
        System.out.println("No alert upon view close");
    }
    
    if (DataItems.contractOrderCallOff) {
        driver.findElement(submitButton).click();
        CommonTask.waitForPageLoad(driver);
    } else {
        driver.findElement(cancelButton).click();
        CommonTask.waitForPageLoad(driver);
        
    }
  }

  @Category({Categories.eComm_Orders_ContractOrder_ManualEntry.class,Categories.QuickSuite.class})
  @Test //Manual Entry Page :: Contract Order expecting validation success
  public void COME2() throws Exception {
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order COME2", "CO_ME_2", DataItems.validCustUsername, DataItems.validCustPassword);
    
    Ecomm_ManualEntryPage mePage = eComm.clickManualEntry();
    mePage.waitForLoad();

    mePage.setBuyers(DataItems.custDetails[3]);
    
    driver.findElement(poField).clear();
    String po = CommonTask.generatePO("contract");
    driver.findElement(poField).sendKeys(po);
    System.out.println("PO used: "+po);
    
    CommonTask.waitForPageLoad(driver);
    
    CommonTask.setSearchField(driver, articleField, DataItems.conOrdArticle);
    CommonTask.setSearchField(driver,shadeCodeField,DataItems.conOrdShadeCode);
    driver.findElement(quantityField).clear();
    driver.findElement(quantityField).sendKeys(String.valueOf(DataItems.conOrdQty));
    CommonTask.setDateField(driver, dateField);
    driver.findElement(contractPOField).clear();
    driver.findElement(contractPOField).sendKeys(DataItems.conOrdPO);
    driver.findElement(lineRefField).clear();
    driver.findElement(lineRefField).sendKeys(DataItems.conOrdLineRef);
    
    Ecomm_OrderConfirmationPage orderConf = mePage.pressNext();
    
    try {
        Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
        assertTrue(alert.getText().matches("^Do you want to SUBMIT the order[\\s\\S]$"));
        alert.accept();
    } catch (Exception e) {
        System.out.println("No alert before confirmation");
    }
    
    orderConf.waitForLoad();
    CommonTask.waitForPageLoad(driver);
    
    //Take a screenshot
    File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\7Validadtion success - Confirmation page.png"));
    
    Actions scroller = new Actions(driver);
    scroller.moveToElement(driver.findElement(cancelButton)).build().perform();
    
    //Take a screenshot
    File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\8Validadtion success - Confirmation page scrolled.png"));
    
    if (DataItems.contractOrderCallOff) {
        driver.findElement(submitButton).click();
        CommonTask.waitForPageLoad(driver);
    } else {
        driver.findElement(cancelButton).click();
        CommonTask.waitForPageLoad(driver);
    }
    
  }
  
  @Category(Categories.eComm_Orders_ContractOrder_ManualEntry.class)
  @Test //Manual Entry Page :: Contract Order using Quantity/LineRef/ContractPO expecting validation success
  public void COME3() throws Exception {
    
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order COME3", "CO_ME_3", DataItems.validCustUsername, DataItems.validCustPassword);
    Ecomm_ManualEntryPage mePage = eComm.clickManualEntry();
    
      System.out.println("Manual Entry Page reached. Entering customer details...");
    
    driver.findElement(By.id("BulkOrderPoNumber")).clear();
    String po = CommonTask.generatePO("contract");
    driver.findElement(poField).sendKeys(po);
    System.out.println("PO used: "+po);
    
      System.out.println("Customer details entered. Entering line details...");
    
    mePage.setBuyers("*OTHERS*");
    driver.findElement(By.id("quantity0")).clear();
    driver.findElement(By.id("quantity0")).sendKeys("1");
    mePage.setDate(0);
    driver.findElement(By.id("txtContract0")).clear();
    driver.findElement(By.id("txtContract0")).sendKeys("40000992");
    driver.findElement(By.id("txtContractLine0")).clear();
    driver.findElement(By.id("txtContractLine0")).sendKeys("10");
    
      System.out.println("Line details entered. Pressing next...");
    
    //Take a screenshot
    File scrFile9 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile9,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\9Quantity PO Line Ref entered.png"));
    
    Ecomm_OrderConfirmationPage orderConf = mePage.pressNext();
    orderConf.waitForLoad();
    
      System.out.println("Order confirmation page reached.");
    
    //Take a screenshot
    File scrFile10 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile10,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\10Order Confirmation page.png"));
    
    Actions action = new Actions(driver);
    action.moveToElement(orderConf.getCancelButton()).build().perform();
    
    if (DataItems.contractOrderCallOff) {
        System.out.println("Submitting order...");
        driver.findElement(submitButton).click();
        CommonTask.waitForPageLoad(driver);
        //Take a screenshot
        File scrFile11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile11,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\11Order confirmed.png"));
        System.out.println("Order submitted.");
    } else {
        System.out.println("Cancelling order...");
        driver.findElement(cancelButton).click();
        CommonTask.waitForPageLoad(driver);
        //Take a screenshot
        File scrFile11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile11,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\11Order cancelled.png"));
        System.out.println("Order cancelled.");
    }
  }
  
  @Category(Categories.eComm_Orders_ContractOrder_ManualEntry.class)
  @Test //Manual Entry Page :: Conract Order using all but quantity expecting validation success
  public void COME4() throws Exception {
    
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order COME4", "CO_ME_4", DataItems.validCustUsername, DataItems.validCustPassword);
    Ecomm_ManualEntryPage mePage = eComm.clickManualEntry();
    
      System.out.println("Manual Entry Page reached. Entering Customer details...");
    
    driver.findElement(By.id("BulkOrderPoNumber")).clear();
    String po = CommonTask.generatePO("contract");
    driver.findElement(poField).sendKeys(po);
    System.out.println("PO used: "+po);
    mePage.setBuyers(DataItems.custDetails[3]);
    
      System.out.println("Customer details entered. Entering line details...");
    
    mePage.setDate(0);
    driver.findElement(By.id("txtContract0")).clear();
    driver.findElement(By.id("txtContract0")).sendKeys("40000992");
    driver.findElement(By.id("txtContractLine0")).clear();
    driver.findElement(By.id("txtContractLine0")).sendKeys("10");
    
      System.out.println("Line details entered. Pressing next...");
    
    //Take a screenshot
    File scrFile9 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile9,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\12All but quantity entered.png"));
    
    Ecomm_OrderConfirmationPage orderConf = mePage.pressNext();
    orderConf.waitForLoad();
    
      System.out.println("Order confirmation page reached. ");
    
    Actions scroll = new Actions(driver);
    scroll.moveToElement(driver.findElement(cancelButton)).build().perform();

    if (DataItems.contractOrderCallOff) {
        System.out.println("Submitting order...");
        Ecomm_OutstandingOrdersPage outPage = orderConf.pressSubmit();
        outPage.waitForLoad();
        CommonTask.waitForPageLoad(driver);
        //Take a screenshot
        File scrFile10 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile10,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\13Order confirmation.png"));       
        System.out.println("Order submitted.");
    } else {
        System.out.println("Cancelling order...");
        driver.findElement(cancelButton).click();
        CommonTask.waitForPageLoad(driver);
        //Take a screenshot
        File scrFile11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile11,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\13Order cancelled.png"));
        System.out.println("Order cancelled.");
    }
  }
  
  @Category(Categories.eComm_Orders_ContractOrder_ManualEntry.class)
  @Test //Manual Entry Page :: Contract Order using all but Contract PO expecting error
  public void COME5() throws Exception {
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order COME5", "CO_ME_5", DataItems.validCustUsername, DataItems.validCustPassword);
    Ecomm_ManualEntryPage mePage = eComm.clickManualEntry();
    mePage.waitForLoad();
    
      System.out.println("Manual Entry page reached. Entering customer details...");
    
    driver.findElement(By.id("BulkOrderPoNumber")).clear();
    String po = CommonTask.generatePO("contract");
    driver.findElement(poField).sendKeys(po);
    System.out.println("PO used: "+po);
    mePage.setBuyers(DataItems.custDetails[3]);
    
    System.out.println("Customer details entered. Entering line details...");
    
    driver.findElement(By.id("quantity0")).clear();
    driver.findElement(By.id("quantity0")).sendKeys("1");
    mePage.setDate(0);
    driver.findElement(By.id("txtContractLine0")).clear();
    driver.findElement(By.id("txtContractLine0")).sendKeys("10");
    
      System.out.println("Line details entered. Pressing next...");
    
    //Take a screenshot
    File scrFile11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile11,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\14All but contractPO entered.png"));

    driver.findElement(By.id("next")).click();
    
      System.out.println("Next pressed, expecting error...");
    
    try {
        Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
        alert.accept();
    } catch (Exception e) {
        System.out.println("No alert before confirmation");
    }  
    
    try {
        Alert alert2 = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
        alert2.accept();
    } catch (Exception e) {
        System.out.println("Additional alert not displayed");
    }
    Ecomm_ManualEntryPage mePage2 = new Ecomm_ManualEntryPage(driver);

    boolean waitForError = new WebDriverWait(driver,5).until(ExpectedConditions.textToBePresentInElementLocated(flashMessage,"could not"));
    
      System.out.println("Error received. ");
    
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(contractPOField)).build().perform();
    
    //Take a screenshot
    File scrFile16 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile16,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\16ContractPO field yellow.png"));
  }
  
  @Category(Categories.eComm_Orders_ContractOrder_ManualEntry.class)
  @Test //Manual Entry Page :: Contract Order using only Contract PO expecting error
  public void COME6() throws Exception {
    
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order #6", "CO_ME_6", DataItems.validCustUsername, DataItems.validCustPassword);
    Ecomm_ManualEntryPage mePage = eComm.clickManualEntry();
    
      System.out.println("Manual Entry Page reached. Entering customer details...");
    
    driver.findElement(By.id("BulkOrderPoNumber")).clear();
    String po = CommonTask.generatePO("contract");
    driver.findElement(poField).sendKeys(po);
    System.out.println("PO used: "+po);
    
      System.out.println("Customer details entered. Entering line details...");
    
    mePage.setBuyers(DataItems.custDetails[3]);
    mePage.setDate(0);
    driver.findElement(By.id("txtContract0")).clear();
    driver.findElement(By.id("txtContract0")).sendKeys("40000992");
    
      System.out.println("Line details entered.");
    
    //Take a screenshot
    File scrFile11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile11,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\17Only contract PO.png"));
    
    Ecomm_OrderConfirmationPage orderConf = mePage.pressNext();

    try {
        Alert alert2 = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
        alert2.accept();
    } catch (Exception e) {
        System.out.println(e.toString());
    }

    CommonTask.waitForPageLoad(driver);
    
    //Take a screenshot
    File scrFile12 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile12,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\18Order confirmation.png"));
    driver.findElement(By.linkText("Line with Error")).click();
    Ecomm_OrderViewPage viewPage = new Ecomm_OrderViewPage(driver);
    viewPage.waitForContent();
    
    //Take a screenshot
    File scrFile13 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile13,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\19Error view.png"));
    
    viewPage.closeView();
    viewPage.waitForInvisibility();
    driver.switchTo().defaultContent();
    
    
  }
  
  @Category(Categories.eComm_Orders_ContractOrder_ManualEntry.class)
  @Test //Manual Entry Page :: Contract Order using only quantity expecting error
  public void COME7() throws Exception {
    
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order #7", "CO_ME_7", DataItems.validCustUsername, DataItems.validCustPassword);
    Ecomm_ManualEntryPage mePage = eComm.clickManualEntry();
    
    driver.findElement(By.id("BulkOrderPoNumber")).clear();
    String po = CommonTask.generatePO("contract");
    driver.findElement(poField).sendKeys(po);
    System.out.println("PO used: "+po);
    mePage.setBuyers(DataItems.custDetails[3]);
    driver.findElement(By.id("quantity0")).clear();
    driver.findElement(By.id("quantity0")).sendKeys("1");
    mePage.setDate(0);
    
    //Take a screenshot
    File scrFile13 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile13,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\20Quantity only entered.png"));
    
    mePage.pressNext();
    Ecomm_ManualEntryPage mePage2 = new Ecomm_ManualEntryPage(driver);

    try {
        Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
        alert.accept();
    } catch (Exception e) {
        System.out.println("No additional alerts displayed. ");
    }
    
    mePage2.waitForLoad();
    boolean waitForError = new WebDriverWait(driver,5).until(ExpectedConditions.textToBePresentInElementLocated(flashMessage, "could not"));
    System.out.println("Error received: "+driver.findElement(flashMessage).getText());
    
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(contractPOField)).build().perform();
    WebElement waitForVisible = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(contractPOField));
    
    //Take a screenshot
    File scrFile14 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile14,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\21Error received.png"));
    
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

