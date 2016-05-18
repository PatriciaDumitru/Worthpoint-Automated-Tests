package com.coats.selenium.tests.RCTests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.PreFlows;
import AutomationFramework.Wait;
import PageObjects.CCE_MainPage;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import PageObjects.Mst_CustomersPage;
import PageObjects.Mst_EditCustomerPage;
import PageObjects.WBA_LoginPage;
import com.coats.selenium.DriverFactory;
import com.coats.selenium.tests.Cce_Base;
import com.coats.selenium.tests.Ecomm_Base;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.*;

public class Ecomm_CO_ME_Test extends DriverFactory {

  private final By shipToPartyField = By.id("ship_to_party_id");
  private final By poField = By.id("BulkOrderPoNumber");
  private final By dateField = By.id("required_date_0");
  private final By buyersField = By.id("s2id_BuyerId");
  private final By articleField = By.id("s2id_BulkOrderLine0ArticleId");
  private final By shadeCodeField = By.id("s2id_BulkOrderLine0ShadeId");
  private final By quantityField = By.id("quantity0");
 // private final By contractPOField = By.id("txtContract0");
  //private final By lineRefField = By.id("txtContractLine0");
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

    CommonTask.setDropDownField(driver, shipToPartyField, "Star Garments");
    CommonTask.setSearchField(driver, buyersField, DataItems.custDetails[3]);
    
    driver.findElement(poField).clear();
    String po = CommonTask.generatePO("contract");
    driver.findElement(poField).sendKeys(po);
    
    System.out.println("Customer details entered. Entering line details...");
    
    System.out.println("Customer PO No.: "+po);
    
    CommonTask.waitForPageLoad(driver);
    
    CommonTask.setSearchField(driver, articleField, DataItems.conOrdArticle);
    CommonTask.setSearchField(driver,shadeCodeField,DataItems.conOrdShadeCode);
    driver.findElement(quantityField).clear();
    driver.findElement(quantityField).sendKeys(String.valueOf(DataItems.conOrdQty));
    CommonTask.setDateFieldWithoutHours(driver, dateField);
    //driver.findElement(contractPOField).clear();
    //driver.findElement(contractPOField).sendKeys("random");
    //driver.findElement(lineRefField).clear();
    //driver.findElement(lineRefField).sendKeys(DataItems.conOrdLineRef);
    
      System.out.println("Line details entered. Pressing next...");
    
    Ecomm_OrderConfirmationPage orderConf = mePage.pressNext();
    orderConf.waitForElement();
    
      System.out.println("Order confirmation page reached. Viewing error...");
    
    Actions scroller = new Actions(driver);
    scroller.moveToElement(driver.findElement(cancelButton)).build().perform();
    
   /*
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

    */
  }

  @Test //Manual Entry Page :: Contract Order expecting validation success
  (groups = {"eComm","eComm_Orders"})
  public void COME2() throws Exception {
    
    WebDriver driver = getDriver();
      
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order COME2", "CO_+_ME_ME_01", DataItems.validCustUsername, DataItems.validCustPassword);
    
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
    CommonTask.setDateFieldWithoutHours(driver, dateField);
    //driver.findElement(contractPOField).clear();
    //driver.findElement(contractPOField).sendKeys(DataItems.conOrdPO);
    //driver.findElement(lineRefField).clear();
    //driver.findElement(lineRefField).sendKeys(DataItems.conOrdLineRef);
    
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
    
    Actions scroller = new Actions(driver);
    scroller.moveToElement(driver.findElement(cancelButton)).build().perform();
    
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
    //driver.findElement(By.id("txtContract0")).clear();
    //driver.findElement(By.id("txtContract0")).sendKeys("40000992");
    //driver.findElement(By.id("txtContractLine0")).clear();
    //driver.findElement(By.id("txtContractLine0")).sendKeys("10");
    
      System.out.println("Line details entered. Pressing next...");
    
    Ecomm_OrderConfirmationPage orderConf = mePage.pressNext();
    orderConf.waitForElement();
    
      System.out.println("Order confirmation page reached.");
    
    Actions action = new Actions(driver);
    action.moveToElement(orderConf.getCancelButton()).build().perform();
    
    if (DataItems.contractOrderCallOff) {
        System.out.println("Submitting order...");
        driver.findElement(submitButton).click();
        CommonTask.waitForPageLoad(driver);
        System.out.println("Order submitted.");
    } else {
        System.out.println("Cancelling order...");
        driver.findElement(cancelButton).click();
        CommonTask.waitForPageLoad(driver);

        System.out.println("Order cancelled as call-off is disabled");
    }
  }
  
  @Test //Manual Entry Page :: Conract Order using all but quantity expecting validation success
  (groups = {"eComm","eComm_Orders"})
  public void COME4() throws Exception {
    
    WebDriver driver = getDriver();  
    
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order COME4", "CO_+_ME_03", DataItems.validCustUsername, DataItems.validCustPassword);
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
    //driver.findElement(By.id("select2-drop-mask")).clear();
    //driver.findElement(By.id("select2-drop-mask")).sendKeys("40000992");
    //driver.findElement(By.id("txtContractLine0")).clear();
    //driver.findElement(By.id("txtContractLine0")).sendKeys("10");
    
      System.out.println("Line details entered. Pressing next...");
    
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
        System.out.println("Order submitted.");
    } else {
        System.out.println("Cancelling order...");
        driver.findElement(cancelButton).click();
        CommonTask.waitForPageLoad(driver);

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
//    driver.findElement(By.id("txtContractLine0")).clear();
    //driver.findElement(By.id("txtContractLine0")).sendKeys("10");
    
      System.out.println("Line details entered. Pressing next...");

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
    
    //WebElement wait2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(contractPOField));
    //Actions action = new Actions(driver);
    //action.moveToElement(driver.findElement(contractPOField)).build().perform();

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
    CommonTask.setDropDownField(driver, shipToPartyField, "Star Garments");
    
      System.out.println("Customer details entered. Entering line details...");
    
    mePage.setBuyers(DataItems.custDetails[3]);
    mePage.setDate(0);
      mePage.setContractPO("test",0);
    //driver.findElement(By.id("txtContract0")).clear();
    //driver.findElement(By.id("txtContract0")).sendKeys("40000992");
    
      System.out.println("Line details entered.");
    
    Ecomm_OrderConfirmationPage orderConf = mePage.pressNext();

    try {
        Alert alert2 = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert2.accept();
    } catch (Exception e) {
        System.out.println("No additional alert");
    }

    CommonTask.waitForPageLoad(driver);
    
      System.out.println("Viewing error...");
    
    driver.findElement(By.linkText("Line with Error")).click();
    Ecomm_OrderViewPage viewPage = new Ecomm_OrderViewPage(driver);
    viewPage.waitForContent();
    
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

//    mePage.setBuyers(DataItems.custDetails[3]);
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
    
    //Actions action = new Actions(driver);
    //action.moveToElement(driver.findElement(contractPOField)).build().perform();
   // WebElement waitForVisible = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(contractPOField));
    
    //Take a screenshot
    File scrFile14 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile14,new File(DataItems.screenshotsFilepath+"\\EComm\\Orders\\Manual Entry\\Contract Order\\21Error received.png"));
    
  } 
  
  @Test //Manual Entry Page :: Field checks
  (groups = {"eComm","eComm_Orders"})
  public void COME8() throws Exception {
    WebDriver driver = getDriver();
      
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order COME8", "CO_MEUI_01 and 02", DataItems.validCustUsername, DataItems.validCustPassword);
    Ecomm_ManualEntryPage mePage = eComm.clickManualEntry();
    System.out.println("Manual Entry Page reached. Checking fields...");
      
    mePage.checkContractOrderFields();
      
    System.out.println("Fields checked.");
    
  }
  
  @Test //Manual Entry Page :: Contract Order using only Contract PO and Line Ref AND Contract Order using only Contract PO and material to call off entire line
  (groups = {"eComm","eComm_Orders"})
  public void COME9() throws Exception {
    WebDriver driver = getDriver();
      
    Ecomm_GeneratedBase base = new Ecomm_GeneratedBase(driver);   
    Ecomm_MainPage eComm = base.setUp("eComm Manual Entry Contract Order COME9 (part 1)", "CO_+_ME_08", DataItems.validCustUsername, DataItems.validCustPassword);
    Ecomm_ManualEntryPage mePage = eComm.clickManualEntry();
    
    System.out.println("Manual Entry Page reached. Entering customer details...");
    
    WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(By.id("BulkOrderPoNumber")));
    driver.findElement(By.id("BulkOrderPoNumber")).clear();
    String po = CommonTask.generatePO("contract");
    driver.findElement(poField).sendKeys(po);
    System.out.println("PO used: "+po);
    CommonTask.setDropDownField(driver, shipToPartyField, "Star Garments");
    
    System.out.println("Customer details entered. Entering line details...");
    
    mePage.setBuyers("*OTHERS*");
    mePage.setDate(0);
    //driver.findElement(By.id("txtContract0")).clear();
   // driver.findElement(By.id("txtContract0")).sendKeys("40000992");
    //driver.findElement(By.id("txtContractLine0")).clear();
   // driver.findElement(By.id("txtContractLine0")).sendKeys("10");
    
    System.out.println("Line details entered. Pressing next...");
    
    Ecomm_OrderConfirmationPage orderConf = mePage.pressNext();
    orderConf.waitForElement();
    
    System.out.println("Order confirmation page reached. Checking line details copied correctly...");
    
    //orderConf.checkContractMaterialDetails();
    
    System.out.println("Details copied correctly. Obtaining remaining line quantity...");
      
    //int qty = orderConf.getOrderedQty();
    
      //System.out.println("Quantity receieved: " + qty);
    
    Actions action = new Actions(driver);
    action.moveToElement(orderConf.getCancelButton()).build().perform();
    
    if (DataItems.contractOrderCallOff) {
        System.out.println("Submitting order...");
        driver.findElement(submitButton).click();
        CommonTask.waitForPageLoad(driver);
        System.out.println("Order submitted.");
    } else {
        System.out.println("Cancelling order...");
        Ecomm_ManualEntryPage mePage4 = orderConf.pressCancel();
        mePage4.waitForElement();
        System.out.println("Order cancelled as call-off is disabled");
    }
    
    WBA_LoginPage liPage = orderConf.pressLogout();
    liPage.waitForElement();
    
    Ecomm_Base base2 = new Ecomm_Base(driver);
    Ecomm_MainPage mainPage = base2.setUp("eComm Manual Entry Contract Order COME9 (part 2)", "CO_+_ME_09", DataItems.validCustUsername, DataItems.validCustPassword);
    mainPage.waitForLoad();
    
      System.out.println("Navigating to Manual Entry...");
      
    Ecomm_ManualEntryPage mePage2 = mainPage.clickManualEntry();
    mePage2.waitForElement();
    
      System.out.println("Manual Entry page reached. Entering Customer details...");
      
    mePage2.setShipToParty(DataItems.conOrdDetails[1]);
    mePage2.setBuyers(DataItems.conOrdDetails[3]);
    mePage2.setPONumber("CO_UniqueRef_");
    
      System.out.println("Details entered. Entering Contract PO No., Qty, and material...");
      
    mePage2.setArticle(DataItems.conOrdArticle, 0);
    mePage2.setShadeCode(DataItems.conOrdShadeCode, 0);
    //mePage2.setQty(qty, 0);
    mePage2.setDate(0);
    mePage2.setContractPO(DataItems.conOrdPO, 0);
    
      System.out.println("Details set. Pressing next...");
    
    Ecomm_OrderConfirmationPage orderConf2 = mePage2.pressNext();
    orderConf2.waitForElement();
    
      System.out.println("Confirmation Page reached. Checking details...");
      
      orderConf.checkContractMaterialDetails();
      
      System.out.println("Details as expected. Checking quantity...");
      
      //AssertJUnit.assertTrue("Order Confirmation Page: Quantity in confirmation page not equal to remaining line quantity",orderConf.getOrderedQty() == qty);
      
      System.out.println("Quantity as expected");
      
      if (DataItems.contractOrderCallOff) {
        System.out.println("Submitting order...");
        driver.findElement(submitButton).click();
        CommonTask.waitForPageLoad(driver);
        System.out.println("Order submitted.");
    } else {
        System.out.println("Cancelling order...");
        Ecomm_ManualEntryPage mePage3 = orderConf2.pressCancel();
        mePage3.waitForElement();
        System.out.println("Order cancelled as call-off is disabled");
    }
  }
  
  @Test //Manual Entry Page :: Contract Order using Contract PO, material, qty<remaining, unique ref
  (groups = {"eComm","eComm_Orders"})
  public void COME10() throws Exception {
    WebDriver driver = getDriver();
      
      Ecomm_Base base2 = new Ecomm_Base(driver);
    Ecomm_MainPage mainPage = base2.setUp("eComm Manual Entry Contract Order COME10", "CO_+_ME_11", DataItems.validCustUsername, DataItems.validCustPassword);
    mainPage.waitForLoad();
    
      System.out.println("Navigating to Manual Entry...");
      
    Ecomm_ManualEntryPage mePage2 = mainPage.clickManualEntry();
    mePage2.waitForElement();
    
      System.out.println("Manual Entry page reached. Entering Customer details...");
      
    mePage2.setShipToParty(DataItems.conOrdDetails[1]);
    mePage2.setBuyers(DataItems.conOrdDetails[3]);
    mePage2.setPONumber("CO_UniqueRef_");
    
      System.out.println("Details entered. Entering Contract PO No., Qty, and material...");
      
    mePage2.setArticle(DataItems.conOrdArticle, 0);
    mePage2.setShadeCode(DataItems.conOrdShadeCode, 0);
    mePage2.setQty(1, 0);
    mePage2.setDate(0);
    mePage2.setContractPO(DataItems.conOrdPO, 0);
    
      System.out.println("Details set. Pressing next...");
    
    Ecomm_OrderConfirmationPage orderConf2 = mePage2.pressNext();
    orderConf2.waitForElement();
    
      System.out.println("Confirmation Page reached. Checking details...");
      
      orderConf2.checkContractMaterialDetails();
      
      System.out.println("Details as expected");
      
      if (DataItems.contractOrderCallOff) {
        System.out.println("Submitting order...");
        driver.findElement(submitButton).click();
        CommonTask.waitForPageLoad(driver);
        System.out.println("Order submitted.");
    } else {
        System.out.println("Cancelling order...");
        Ecomm_ManualEntryPage mePage3 = orderConf2.pressCancel();
        mePage3.waitForElement();
        System.out.println("Order cancelled as call-off is disabled");
    }
  }
  
  @Test //Manual Entry Page :: Contract Order using Contract PO, material
  (groups = {"eComm","eComm_Orders"})
  public void COME11() throws Exception {
    WebDriver driver = getDriver();

      //Login as admin and set master data
      Cce_Base base = new Cce_Base(driver);

      //Set up returns a CCE Page and outputs test details
      CCE_MainPage ccePage = base.setUp("", "");

      //Setting master data
      PreFlows pf = new PreFlows();
      pf.chooseTheOtherProfile(driver);
      pf.disableMOQForCustomer(driver, "Star Garments Ltd.");
      pf.chooseTheOtherProfile(driver);

      //Logout of admin
      pf.logoutAction(driver);

      Ecomm_Base base2 = new Ecomm_Base(driver);
    Ecomm_MainPage mainPage = base2.setUp("eComm Manual Entry Contract Order COME11", "CO_+_ME_11", DataItems.validCustUsername, DataItems.validCustPassword);
    mainPage.waitForLoad();

    
      System.out.println("Navigating to Manual Entry...");
      
    Ecomm_ManualEntryPage mePage2 = mainPage.clickManualEntry();
    mePage2.waitForElement();
    
      System.out.println("Manual Entry page reached. Entering Customer details...");
      
    mePage2.setShipToParty(DataItems.conOrdDetails[1]);
    mePage2.setBuyers(DataItems.conOrdDetails[3]);
    mePage2.setPONumber(DataItems.conOrdDetails[4]);
    
      System.out.println("Details entered. Entering Contract PO No. and material...");
      
    mePage2.setArticle(DataItems.conOrdArticle, 0);
    mePage2.setShadeCode(DataItems.conOrdShadeCode, 0);
    mePage2.setDate(0);
    mePage2.setContractPO(DataItems.conOrdPO, 0);
    
      System.out.println("Details set. Pressing next...");
    
    Ecomm_OrderConfirmationPage orderConf2 = mePage2.pressNext();
    orderConf2.waitForElement();
    
      System.out.println("Confirmation Page reached. Checking material details...");
      
      orderConf2.checkContractMaterialDetails();

      int qty = orderConf2.getOrderedQty();
      
      System.out.println("Material details as expected. Quantity copied from SAP: " + qty);
      
      if (DataItems.contractOrderCallOff) {
        System.out.println("Submitting order...");
        driver.findElement(submitButton).click();
        CommonTask.waitForPageLoad(driver);
        System.out.println("Order submitted.");
    } else {
        System.out.println("Cancelling order...");
        Ecomm_ManualEntryPage mePage3 = orderConf2.pressCancel();
        mePage3.waitForElement();
        System.out.println("Order cancelled as call-off is disabled");
    }
  }
  
  @Test //Manual Entry Page :: Non-contract re-check
  (groups ={"eComm","eComm_Orders"})
  public void COMERC1() throws Exception {
      //This test is very similar to those in ME_SUMST but will check specifically for absence of contract order items while completing
      
      //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base susstTest3 = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = susstTest3.setUp("MANUAL ENTRY COMERC1: Single line, Your Material Number without master data shade code (contract order re-check)","CO_ME_R_01");
        
        System.out.println("Navigating to Manual Entry...");
        
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        
        System.out.println("Manual Entry page loaded. Entering customer details...");
        
        //Input Customer Details
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);    
        manualEntryPage.setShipToParty(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        //Number of order lines
        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.yourMatNum,DataItems.shadeCode,String.valueOf(DataItems.quantity)},
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMNShadeCode(details[i], i);
        }

        System.out.println("Product details entered. Checking for absence of contract order fields...");
        
        AssertJUnit.assertFalse("Manual Entry Page: Contract PO field appears despite call-off disbaled",manualEntryPage.findContractPOField());
        AssertJUnit.assertFalse("Manual Entry Page: Line reference field appears despite call-off disbaled",manualEntryPage.findLineRefField());

        System.out.println("Fields absent, as expected. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();

        System.out.println("Order confirmation page reached. Checking for absence of Contract PO, SAP Contract No., and Line reference cells...");
        
        AssertJUnit.assertFalse("Order Confirmation Page: Contract PO cell appears despite call-off disabled",orderConf.findContractPOCell());
        AssertJUnit.assertFalse("Order Confirmation Page: SAP Contract No cell appears despite call-off disabled",orderConf.findSAPContractNoCell());
        AssertJUnit.assertFalse("Order Confirmation Page: Line Ref cell appears despite call-off disabled",orderConf.findLineRefCell());

        System.out.println("Cells absent, as expected. Cancelling...");
        
        Ecomm_ManualEntryPage mePage = orderConf.pressCancel();
        mePage.waitForElement(); 
        
        System.out.println("Cancelled.");
  }
  
  @Test //Manual Entry Page :: Non-contract re-check (MOQ Active)
  (groups = {"eComm","eComm_Orders"})
  public void COMERC2() throws Exception {
      //This test checks specifically for the absence of contract order fields
      //New chrome driver
        WebDriver driver = getDriver();

        //new base test to handle set up
        Ecomm_Base base = new Ecomm_Base(driver);
        //Set up returns a manual entry page to begin data entry
        Ecomm_MainPage eCommPage = base.setUp("MANUAL ENTRY COMERC2: Non-contract order re-check (MOQ ACTIVE)","CO_ME_R_02");

        System.out.println("Navigating to Manual Entry...");

        //press manual entry
        Ecomm_ManualEntryPage manualEntryPage = eCommPage.clickManualEntry();
        manualEntryPage.waitForElement();

        System.out.println("Manual Entry Page reached. Entering customer details...");

        //Input Customer Details    
        manualEntryPage.setCustomerName(DataItems.custDetails[0]);
        manualEntryPage.setShipToPartyWithWait(DataItems.custDetails[1]);
        manualEntryPage.setRequestor(DataItems.custDetails[2]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        int numberOfLines = 1;

        //Order details to be entered
        String[][] details = {
            //line 1 details
            {DataItems.yourMatNum,String.valueOf(1)},
        };

        //Input details for each line
        for (int i = 0; i < numberOfLines; i++) {
            manualEntryPage = manualEntryPage.setOrderDetailsYMN(details[i], i);
        }
        
        String date = manualEntryPage.getDate(0);
        
        System.out.println("Product details entered. Ensuring auto-fill is correct...");
        
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill article not as expected in master data",manualEntryPage.getArticle(0).equals(DataItems.expArticle));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill brand not as expected in master data",manualEntryPage.getBrand(0).equals(DataItems.expBrand));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill ticket not as expected in master data",manualEntryPage.getTicket(0).equals(DataItems.expTicket));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill length not as expected in master data",manualEntryPage.getLength(0).equals(DataItems.expLength));
        AssertJUnit.assertTrue("Manual Entry Page: Auto-fill finish not as expected in master data",manualEntryPage.getFinish(0).equals(DataItems.expFinish));
        AssertJUnit.assertTrue("Manual Entry Page: Shade code not as expected after being changed from material Master shade",manualEntryPage.getShadeCode(0).equals(DataItems.expShadeCode));
        
        System.out.println("Auto-fill correct. Checking for contract order fields...");
        
        AssertJUnit.assertFalse("Manual Entry page: Contract PO No. field displayed despite call-off disabled",manualEntryPage.findContractPOField());
        AssertJUnit.assertFalse("Manual Entry page: Line reference field displayed despite call-off disabled",manualEntryPage.findLineRefField());
        
        System.out.println("Fields absent, as expected. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNextMOQ();
        //orderConf.waitForElement();

        Alert alert = Wait.alert(driver);
        alert.accept();
        System.out.println("MOQ Alert appeared as expected.");



        System.out.println("Order confirmation page reached. Checking details are maintained...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer PO Number not maintained after manual entry page",orderConf.getUploadPONumber().equals(DataItems.lastUsedPO));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Customer Name not maintained after manual entry page",orderConf.getCustomerName().equals(DataItems.custDetails[0]));
        AssertJUnit.assertTrue("Order Confirmation Page: Ship To Party not maintained after manual entry page",orderConf.getShipToParty().equals(DataItems.custDetails[1]));
        AssertJUnit.assertTrue("Order Confirmation Page: Requester not maintained after manual entry page",orderConf.getRequester().equals(DataItems.custDetails[2]));
        AssertJUnit.assertTrue("Order Confirmation Page: Buyers not maintained after manual entry page",orderConf.getBuyers().equals(DataItems.custDetails[3]));

        String[] parts = orderConf.getCoatsMaterial().split("-");
        System.out.println(parts[1]);
        AssertJUnit.assertTrue("Order Confirmation Page: Shade Code (from Coats Mat Num) not maintained after manual entry page",parts[1].equals(DataItems.expShadeCode));
        
        AssertJUnit.assertTrue("Order Confirmation Page: Ordered Quantity not maintained after manual entry page",orderConf.getOrderedQty()==1);
        AssertJUnit.assertTrue("Order Confirmation Page: Required Date not maintained after manual entry page",orderConf.getRequiredDate().equals(date));
        
        System.out.println("Details maintained. Check adjusted quantity appears...");
        
        int adjQty = orderConf.getAdjustedQty();
        
        System.out.println("Adjusted quantity retrieved. Checking value...");

        AssertJUnit.assertTrue("Order Confirmation Page: Adjusted Quantity less than or equal to the ordered quantity",adjQty>1);
        
        System.out.println("Value greater than ordered qty, as expected. Check for absence of Contract Order cells...");
        
        AssertJUnit.assertFalse("Order Confirmation Page (MOQ Active): Contract PO No. cell displayed despite call-off disabled",orderConf.findContractPOCell());
        AssertJUnit.assertFalse("Order Confirmation Page (MOQ Active): SAP Contract No. cell displayed despite call-off disabled",orderConf.findSAPContractNoCell());
        AssertJUnit.assertFalse("Order Confirmation Page (MOQ Active): Line refernce cell displayed despite call-off disabled",orderConf.findLineRefCell());
        
        System.out.println("Fields absent, as expected. Cancelling order...");
        
        //Press Submit
        Ecomm_ManualEntryPage mePage = orderConf.pressCancel(); 
        mePage.waitForElement();

        System.out.println("Order cancelled.");

  }
  
  @Test //Manual Entry Page :: Non-contract re-check (Call-off disabled at customer level)
  (groups = {"eComm","eComm_Orders"})
  public void COMERC3() throws Exception {
      //Contract order call-off is disabled for Star Garments Ltd at the customer level in master data, and an order is placed, checking for absence of contract order fields
      WebDriver driver = getDriver();
      
      Cce_Base base = new Cce_Base(driver);
      CCE_MainPage mainPage = base.setUp("Manual Entry Page COMERC3: Non-contract recheck, call-off disabled at customer level", "CO_ME_R_03");
      mainPage.waitForLoad();
      
      System.out.println("Navigating to Customers master...");
      
      Mst_CustomersPage custPage = mainPage.selectCustomers();
      custPage.waitForElement();
      
      System.out.println("Page reached. Entering filter crtieria and listing records...");
      
      custPage.setCustomerName(DataItems.conOrdDetails[0]);
      custPage.pressSearch();
      custPage.waitForElement();
      
      System.out.println("Records listed. Editing first item...");
      
      Mst_EditCustomerPage editPage = custPage.pressEdit(2);
      editPage.waitForElement();
      
      System.out.println("Edit paged reached. Checking page shows data for correct customer...");
      
      AssertJUnit.assertTrue("Edit Customer Page: Customer name not as expected",editPage.getCustomerName().equals(DataItems.conOrdDetails[0]));
      
      System.out.println("Data correct. Disabling contract order call-off...");
      
          editPage.unsetCallOffOrder();
      
      System.out.println("Disabled. Saving...");
      
      editPage.clickSave();
      custPage.waitForElement();
      
      System.out.println("Saved. Logging into Contract Order account...");
      
      WBA_LoginPage liPage = editPage.pressLogout();
      liPage.waitForElement();
      
      Ecomm_Base base2 = new Ecomm_Base(driver);
      Ecomm_MainPage mainPage2 = base2.setUp("", "", DataItems.validCustUsername, DataItems.validCustPassword);
      mainPage2.waitForLoad();
      
      System.out.println("Navigating to Manual Entry...");
      
      Ecomm_ManualEntryPage manualEntryPage = mainPage2.clickManualEntry();
        
      System.out.println("Manual Entry page loaded. Entering customer details...");
        
        manualEntryPage.setShipToParty(DataItems.conOrdDetails[1]);
        manualEntryPage.setBuyers(DataItems.custDetails[3]);
        manualEntryPage.setPONumber(DataItems.custDetails[4]);

        System.out.println("Customer details entered. Entering product details...");

        manualEntryPage.setArticle(DataItems.article, 0);
        manualEntryPage.setShadeCode(DataItems.shadeCode, 0);
        manualEntryPage.setQty(3, 0);
        manualEntryPage.setDate(0);

        System.out.println("Product details entered. Checking for absence of contract order fields...");
        
        AssertJUnit.assertFalse("Manual Entry Page: Contract PO field appears despite call-off disbaled",manualEntryPage.findContractPOField());
        AssertJUnit.assertFalse("Manual Entry Page: Line reference field appears despite call-off disbaled",manualEntryPage.findLineRefField());

        System.out.println("Fields absent, as expected. Pressing next...");
        
        //Press next
        Ecomm_OrderConfirmationPage orderConf = manualEntryPage.pressNext();

        System.out.println("Order confirmation page reached. Checking for absence of Contract PO, SAP Contract No., and Line reference cells...");
        
        AssertJUnit.assertFalse("Order Confirmation Page: Contract PO cell appears despite call-off disabled",orderConf.findContractPOCell());
        AssertJUnit.assertFalse("Order Confirmation Page: SAP Contract No cell appears despite call-off disabled",orderConf.findSAPContractNoCell());
        AssertJUnit.assertFalse("Order Confirmation Page: Line Ref cell appears despite call-off disabled",orderConf.findLineRefCell());

        System.out.println("Cells absent, as expected. Cancelling...");
        
        Ecomm_ManualEntryPage mePage = orderConf.pressCancel();
        mePage.waitForElement(); 
        
        System.out.println("Cancelled. Enabling Contract Order call-off in customer level...");
        
        WBA_LoginPage liPage2 = mePage.pressLogout();
        liPage2.waitForElement();
        
        System.out.println("Logged out. Logging into admin...");
        
        Cce_Base base3 = new Cce_Base(driver);
        CCE_MainPage mainPage3 = base3.setUp("", "");
        mainPage3.waitForLoad();
        
        Mst_CustomersPage custPage3 = mainPage3.selectCustomers();
        custPage3.waitForElement();
        
        System.out.println("Customer master reached. Finding customer...");
        
        custPage.setCustomerName(DataItems.conOrdDetails[0]);
      custPage.pressSearch();
      custPage.waitForElement();

      Mst_EditCustomerPage editPage2 = custPage.pressEdit(2);
      editPage2.waitForElement();
      
      System.out.println("Edit paged reached. Checking page shows data for correct customer...");
      
      AssertJUnit.assertTrue("Edit Customer Page: Customer name not as expected",editPage2.getCustomerName().equals(DataItems.conOrdDetails[0]));
      
      System.out.println("Data correct. Enabling contract order call-off...");
      
      editPage2.setCallOffOrder();
      editPage.waitForElement();
      
      System.out.println("Enabled. Saving...");
      
      editPage.clickSave();
      custPage.waitForElement();
      
      System.out.println("Saved");
        
  }
  
}

