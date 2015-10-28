package com.coats.selenium.tests.RCTests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import com.coats.selenium.DriverFactory;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.*;

public class Ecomm_CO_ME_Test extends DriverFactory {

  private String baseUrl;
  private final boolean acceptNextAlert = true;
  private final StringBuffer verificationErrors = new StringBuffer();
  private final By poField = By.id("BulkOrderPoNumber");
  private final By dateField = By.id("required_date_0");
  private final By buyersField = By.id("s2id_BuyerId");
  private final By articleField = By.id("s2id_BulkOrderLine0ArticleId");
  private final By shadeCodeField = By.id("s2id_BulkOrderLine0ShadeId");
  private final By quantityField = By.id("quantity0");
  private final By contractPOField = By.id("txtContract0");
  private final By lineRefField = By.id("txtContractLine0");
  private final By overlayContent = By.cssSelector("#BulkOrderLineViewUplodErrorListForm");
  private final By submitButton = By.id("submit1");
  private final By cancelButton = By.id("cancel1");
  private final By flashMessage = By.id("flashMessage");
  
  @Test //Manual Entry Page :: Contract order expecting "No matching contract reference" error
  (groups = {"eComm","eComm_Orders"})
  public void COME1() throws Exception {
    
    WebDriver driver = getDriver();
      
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
    orderConf.waitForElement();
    
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
        Alert alert2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        AssertJUnit.assertTrue(alert2.getText().matches("^Do you want to cancel the current operation[\\s\\S]$"));
        alert2.accept();
    } catch (Exception e) {
        System.out.println("No alert upon view close");
    }
    
    if (DataItems.contractOrderCallOff) {
        driver.findElement(submitButton).click();
        CommonTask.waitForPageLoad(driver);
        System.out.println("Order submitted");
    } else {
        driver.findElement(cancelButton).click();
        CommonTask.waitForPageLoad(driver);
        System.out.println("Order cancelled as call-off is disabled");   
    }
  }

  @Test //Manual Entry Page :: Contract Order expecting validation success
  (groups = {"eComm","eComm_Orders"})
  public void COME2() throws Exception {
    
    WebDriver driver = getDriver();
      
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order COME2", "CO_ME_2", DataItems.validCustUsername, DataItems.validCustPassword);
    
    Ecomm_ManualEntryPage mePage = eComm.clickManualEntry();
    mePage.waitForElement();

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
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        AssertJUnit.assertTrue(alert.getText().matches("^Do you want to SUBMIT the order[\\s\\S]$"));
        alert.accept();
    } catch (Exception e) {
        System.out.println("No alert before confirmation");
    }
    
    orderConf.waitForElement();
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
        System.out.println("Order submitted");
    } else {
        driver.findElement(cancelButton).click();
        CommonTask.waitForPageLoad(driver);
        System.out.println("Order cancelled as call-off is disabled");
    }
    
  }
  
  @Test //Manual Entry Page :: Contract Order using Quantity/LineRef/ContractPO expecting validation success
  (groups = {"eComm","eComm_Orders"})
  public void COME3() throws Exception {
    
    WebDriver driver = getDriver();
      
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order COME3", "CO_ME_3", DataItems.validCustUsername, DataItems.validCustPassword);
    Ecomm_ManualEntryPage mePage = eComm.clickManualEntry();
    
    System.out.println("Manual Entry Page reached. Entering customer details...");
    
    WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(By.id("BulkOrderPoNumber")));
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
    orderConf.waitForElement();
    
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
        System.out.println("Order cancelled as call-off is disabled");
    }
  }
  
  @Test //Manual Entry Page :: Conract Order using all but quantity expecting validation success
  (groups = {"eComm","eComm_Orders"})
  public void COME4() throws Exception {
    
    WebDriver driver = getDriver();  
    
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order COME4", "CO_ME_4", DataItems.validCustUsername, DataItems.validCustPassword);
    Ecomm_ManualEntryPage mePage = eComm.clickManualEntry();
    
      System.out.println("Manual Entry Page reached. Entering Customer details...");
    
    WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(By.id("BulkOrderPoNumber")));  
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
    orderConf.waitForElement();
    
      System.out.println("Order confirmation page reached. ");
    
    Actions scroll = new Actions(driver);
    scroll.moveToElement(driver.findElement(cancelButton)).build().perform();

    if (DataItems.contractOrderCallOff) {
        System.out.println("Submitting order...");
        Ecomm_OutstandingOrdersPage outPage = orderConf.pressSubmit();
        outPage.waitForElement();
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
        System.out.println("Order cancelled as call-off is disabled");
    }
  }
  
  @Test //Manual Entry Page :: Contract Order using all but Contract PO expecting error
  (groups = {"eComm","eComm_Orders"})
  public void COME5() throws Exception {
    
    WebDriver driver = getDriver();  
      
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order COME5", "CO_ME_5", DataItems.validCustUsername, DataItems.validCustPassword);
    Ecomm_ManualEntryPage mePage = eComm.clickManualEntry();
    mePage.waitForElement();
    
    System.out.println("Manual Entry page reached. Entering customer details...");
    
    WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(By.id("BulkOrderPoNumber")));
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
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
    } catch (Exception e) {
        System.out.println("No alert before confirmation");
    }  
    
    try {
        Alert alert2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert2.accept();
    } catch (Exception e) {
        System.out.println("Additional alert not displayed");
    }
    Ecomm_ManualEntryPage mePage2 = new Ecomm_ManualEntryPage(driver);
    boolean waitForError = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(flashMessage,"could not"));
    
    System.out.println("Error received. ");
    
    WebElement wait2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(contractPOField));  
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(contractPOField)).build().perform();
    
    //Take a screenshot
    File scrFile16 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile16,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\16ContractPO field yellow.png"));
  } 

  @Test //Manual Entry Page :: Contract Order using only Contract PO expecting error
  (groups = {"eComm","eComm_Orders"})
  public void COME6() throws Exception {
    
    WebDriver driver = getDriver();
      
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order #6", "CO_ME_6", DataItems.validCustUsername, DataItems.validCustPassword);
    Ecomm_ManualEntryPage mePage = eComm.clickManualEntry();
    
    System.out.println("Manual Entry Page reached. Entering customer details...");
    
    WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(By.id("BulkOrderPoNumber")));
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
        Alert alert2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert2.accept();
    } catch (Exception e) {
        System.out.println("No additional alert");
    }

    CommonTask.waitForPageLoad(driver);
    
    //Take a screenshot
    File scrFile12 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile12,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\18Order confirmation.png"));
    
      System.out.println("Viewing error...");
    
    driver.findElement(By.linkText("Line with Error")).click();
    Ecomm_OrderViewPage viewPage = new Ecomm_OrderViewPage(driver);
    viewPage.waitForContent();
    
    //Take a screenshot
    File scrFile13 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile13,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\19Error view.png"));
    
      System.out.println("View displayed. Closing view...");
    
    viewPage.closeView();
    viewPage.waitForInvisibility();
    driver.switchTo().defaultContent();
    
      System.out.println("View closed.");
    
    
  }
  
  @Test //Manual Entry Page :: Contract Order using only quantity expecting error
  (groups = {"eComm","eComm_Orders"})
  public void COME7() throws Exception {
    
    WebDriver driver = getDriver();
      
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order #7", "CO_ME_7", DataItems.validCustUsername, DataItems.validCustPassword);
    Ecomm_ManualEntryPage mePage = eComm.clickManualEntry();
    
    WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(By.id("BulkOrderPoNumber")));
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
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
    } catch (Exception e) {
        System.out.println("No additional alerts displayed. ");
    }
    
    mePage2.waitForElement();
    boolean waitForError = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(flashMessage, "could not"));
    System.out.println("Error received: "+driver.findElement(flashMessage).getText());
    
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(contractPOField)).build().perform();
    WebElement waitForVisible = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(contractPOField));
    
    //Take a screenshot
    File scrFile14 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile14,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\21Error received.png"));
    
  } 
  
}

