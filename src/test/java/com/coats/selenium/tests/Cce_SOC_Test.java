
package com.coats.selenium.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import PageObjects.CCE_AddOrderPage;
import PageObjects.CCE_EnrichOrderPage;
import PageObjects.CCE_HubSosPage;
import PageObjects.CCE_LRMLogPage;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_ManualEnrichPage;
import PageObjects.CCE_NewBuyerPage;
import PageObjects.CCE_OrderSamplesPage;
import PageObjects.CCE_OrderViewPage;
import PageObjects.CCE_OrderStatusPage;
import PageObjects.CCE_SAPLogPage;
import PageObjects.Mst_SalesOrgMaterialsPage;
import com.coats.selenium.DriverFactory;
import com.google.common.base.Verify;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Cce_SOC_Test extends DriverFactory {
    
    @Test // Order Samples Page :: Page and filter checks, cancel function 
    (groups = {"QuickTest","CCE","CCE_Orders"})
    public void SOC1() throws InterruptedException, IOException, Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SAMPLE ORDER SOC1: User can select all fields", "G_CCE_SOC_2");
        
        System.out.println("Navigating to Order Samples...");
        
        CCE_OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Order Samples\\1Order Samples Page.png"));
        
        System.out.println("Order samples loaded. Checking Customer Name, Customer Code, and Requestor fields...");
        
        //Check prompt fields
        orderSamples.checkFields();
        
        System.out.println("Fields checked. Entering customer details and submitting...");
        
        //Enter customer details, submit prompt and check order detail fields
        orderSamples.setCustName(DataItems.custDetails[0]);
        orderSamples.setRequestor(DataItems.custDetails[2]);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Order Samples\\2Customer details entered.png"));
        
        CCE_AddOrderPage addOrder = orderSamples.pressSubmit();
        addOrder.waitForLoad();
        
        System.out.println("Details submitted. Checking Order Details fields...");
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Order Samples\\3Prompt submitted.png"));
        
        //Check all fields are clickable
        addOrder.checkFields();
        
        System.out.println("Fields checked. Testing cancel button...");
        
        CCE_OrderSamplesPage prompt = addOrder.pressCancel();
        
        prompt.waitForLoad();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Order Samples\\4Order cancelled.png"));
        
        System.out.println("Order cancelled.");
        
    }
    
    @Test //Order Samples Page :: Single line order
    (groups = {"CCE","CCE_Orders"})
    public void SOC2() throws InterruptedException, IOException, Exception {
        
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SAMPLE ORDER SOC2: Single line, within threshold", "G_CCE_SOC_1");
        
        System.out.println("Navigating to Order Samples...");
        
        CCE_OrderSamplesPage orderSamples = ccePage.pressOrderSamples();       
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(DataItems.custDetails[0]);
        orderSamples.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        CCE_AddOrderPage addOrder = orderSamples.pressSubmit();
        
        System.out.println("Prompt submitted. Adding order details...");
        
        //Ship to, business principal, article, shade code, mum type, request, purpose, quantity, line numer
        addOrder.inputDetails(DataItems.custDetails[1],DataItems.custDetails[3],DataItems.expArticle,
                DataItems.expShadeCode,DataItems.copMUM,DataItems.sewing,DataItems.salesSamp,1);
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Order Samples\\5Order details added.png"));
        
        System.out.println("Order details added. Submitting order...");
        
        CCE_OrderStatusPage outstOrders = addOrder.pressSubmit();
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Order Samples\\6Order submitted.png"));
        
        System.out.println("Order submitted. Verifying order details...");
        
        //Line numbers start from 2
        CCE_OrderViewPage viewPage = outstOrders.pressView(2);
        viewPage.switchTo();
        viewPage.waitForContent();
        
        //Take a screenshot
        File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile6,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Order Samples\\7View Order.png"));
        
        Verify.verify(viewPage.getCustName().equals(DataItems.custDetails[0]),"Sample Order View: Customer name incorrect in view");
        Verify.verify(viewPage.getRequestorName().equals(DataItems.custDetails[2]),"Sample Order View: Requestor name incorrect in view");
        Verify.verify(viewPage.getShadeCode(2).equals(DataItems.expShadeCode),"Sample Order View: Shade code incorrect in view");        
        
        System.out.println("Details verified. Exiting view page and ending test.");
        
        System.out.println("Order No.: "+viewPage.getOrderNumber());
        
        viewPage.closeView();    
        
    } 
    
    @Test //Order Samples Page :: Multi-line order
    (groups = {"CCE","CCE_Orders"})
    public void SOC3() throws InterruptedException, IOException, Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SAMPLE ORDER SOC3: Multiple line, within threshold", "G_CCE_SOC_1 (multiple)");
        
        System.out.println("Navigating to Order Samples...");
        
        CCE_OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(DataItems.custDetails[0]);
        orderSamples.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        CCE_AddOrderPage addOrder = orderSamples.pressSubmit();
        
        System.out.println("Prompt submitted. Adding order details...");
        
        //Ship to, business principal, article, shade code, mum type, request, purpose, quantity, line numer
        addOrder.inputDetails(DataItems.custDetails[1],DataItems.custDetails[3],DataItems.expArticle,
                DataItems.expShadeCode,DataItems.copMUM,DataItems.sewing,DataItems.salesSamp,1);
        
        System.out.println("Line 1 details added. Creating new line and adding details...");
              
        int lineNumber = 1;
        
        addOrder.pressNewLine(lineNumber);
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Order Samples\\8Line added.png"));
        
        //Ship to, business principal, article, shade code, mum type, request, purpose, quantity, line numer
        addOrder.inputAdditionalLines(DataItems.article3,
                DataItems.shadeCode3,DataItems.copMUM,DataItems.sewing,DataItems.salesSamp,1,1);
        
        System.out.println("Details added. Submitting order...");
        
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Order Samples\\9Line filled.png"));
        
        CCE_OrderStatusPage outstOrders = addOrder.pressSubmit();
        
        System.out.println("Order submitted. Verifying order details...");
        
        //Line numbers start from 2
        CCE_OrderViewPage viewPage = outstOrders.pressView(2);
        viewPage.switchTo();
        viewPage.waitForContent();
        
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Order Samples\\10View multiple lines.png"));
        
        //Ensure iframe is selected
        //viewPage.switchTo();
        
        Verify.verify(viewPage.getCustName().equals(DataItems.custDetails[0]),"Sample Order View: Customer name incorrect in view");
        Verify.verify(viewPage.getRequestorName().equals(DataItems.custDetails[2]),"Sample Order View: Requestor name incorrect in view");
        Verify.verify(viewPage.getShadeCode(2).equals(DataItems.expShadeCode),"Sample Order View: Shade code incorrect in view");        
        
        System.out.println("Order details verified. Exiting view and ending test.");
        
        System.out.println("Order No.: "+viewPage.getOrderNumber());
        
        viewPage.closeView();
        
    }

    @Test //Order Samples Page :: Single line order, quantity above threshold
    (groups = {"CCE","CCE_Orders","QuickTest"})
    public void SOC4() throws InterruptedException, IOException, Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SAMPLE ORDER SOC4: Single line, above threshold", "G_CCE_SOC_6");
        
        System.out.println("Navigating to Order Samples...");
        
        CCE_OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(DataItems.custDetails[0]);
        orderSamples.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        CCE_AddOrderPage addOrder = orderSamples.pressSubmit();
        
        System.out.println("Prompt submitted. Adding order details...");
        
        //Ship to, business principal, article, shade code, mum type, request, purpose, quantity, line numer
        addOrder.inputDetails(DataItems.custDetails[1],DataItems.custDetails[3],DataItems.expArticle,
                DataItems.expShadeCode,DataItems.coneMUM,DataItems.sewing,DataItems.salesSamp,15);
        
        System.out.println("Details added. Submitting order, expecting rejection...");
        
        addOrder.pressSubmitExceeded();
        
        //Wait for message to appear
        WebElement waitForMessage = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(CCE_AddOrderPage.flashMessageLocator));
        
        String message = driver.findElement(CCE_AddOrderPage.flashMessageLocator).getText();
        
        if (message.contains("could not be saved")) {
            System.out.println("Order submitted. Message received: " + driver.findElement(CCE_AddOrderPage.flashMessageLocator).getText());
            System.out.println("Rejected as expected.");
        } else {
            System.out.println("***Unexpected message received upon submission.***");
        }
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Order Samples\\11Rejected Order.png"));
        
    }
    
    @Test //Order Samples Page :: Multi-line copied data
    (groups = {"CCE","CCE_Orders","QuickTest"})
    public void SOC5() throws InterruptedException, IOException, Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SAMPLE ORDER SOC5: Multiple lines, copied data", "G_CCE_SOC_8");
        
        System.out.println("Navigating to Order Samples...");
        
        CCE_OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(DataItems.custDetails[0]);
        orderSamples.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        CCE_AddOrderPage addOrder = orderSamples.pressSubmit();
        addOrder.waitForElement();
        
        System.out.println("Prompt submitted. Adding line 0 order details...");
        
        //Ship to, business principal, article, shade code, mum type, request, purpose, quantity, line numer
        addOrder.inputDetails(DataItems.custDetails[1],DataItems.custDetails[3],DataItems.expArticle,
                DataItems.expShadeCode,DataItems.coneMUM,DataItems.sewing,DataItems.salesSamp,1);
        
         //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Order Samples\\12Line1 details added.png"));
        
        System.out.println("Line 0 order details added. Adding new line...");
        
        //New line (will be line 1), copy data
        addOrder.pressNewLine(1);
        
        System.out.println("New line added. Copying data...");
        
        addOrder.pressCopy(1);
        addOrder.waitForCopy();
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Order Samples\\13Line1 details copied.png"));
        
        System.out.println("Data copied. Adding remaining line 1 order details...");
        
        //Enter brand, ticket
        addOrder.setBrand(DataItems.brand,1);
        addOrder.setTicket(DataItems.ticket,1);
        addOrder.setShadeCode(DataItems.shadeCode,1);
        addOrder.setQuantity(1, 1);
        
        System.out.println("Order details added. Submitting...");
        
        CCE_OrderStatusPage outstOrder = addOrder.pressSubmit();
        outstOrder.waitForElement();
        
        System.out.println("Details submitted. Verifying details...");
        
        //View first order in table...Line numbers start from 2
        CCE_OrderViewPage viewPage = outstOrder.pressView(2);
        viewPage.switchTo();
        viewPage.waitForContent();
        
        viewPage.verifyCopied();
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Order Samples\\14View multiline copied.png"));
        
        System.out.println("Details verified.");
        
        System.out.println("Order No.: "+viewPage.getOrderNumber());

    }
    
    @Test //Order Samples Page :: Pend order 
    (groups = {"CCE","CCE_Orders","QuickTest"})
    public void SOC6() throws InterruptedException, IOException, Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SAMPLE ORDER SOC6: Save as draft", "G_CCE_SOC_12");
        
        System.out.println("Navigating to Order Samples...");
        
        CCE_OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        orderSamples.waitForElement();
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(DataItems.custDetails[0]);
        orderSamples.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        CCE_AddOrderPage addOrder = orderSamples.pressSubmit();
        
        System.out.println("Prompt submitted. Adding order details...");
        
        //Ship to, business principal, article, shade code, mum type, request, purpose, quantity, line numer
        addOrder.inputDetails(DataItems.custDetails[1],DataItems.custDetails[3],DataItems.expArticle,
                DataItems.expShadeCode,DataItems.coneMUM,DataItems.sewing,DataItems.salesSamp,2);
        
        System.out.println("Order details added. Pressing pend order...");
        
        CCE_OrderStatusPage orderStatus = addOrder.pressPendOrder();
        orderStatus.waitForMessage();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(DataItems.screenshotsFilepath+"\\CCE\\Orders\\Order Samples\\15Pend order pressed.png"));
        
        System.out.println("Order pended. Getting order stage...");
        
        CCE_OrderViewPage viewPage = orderStatus.pressView(2);
        viewPage.switchTo();
        viewPage.waitForContent();
        String orderNo = viewPage.getOrderNumber();
        
        orderStatus = viewPage.closeView();
        
        System.out.println("Order Status: "+ orderStatus.getOrderStage(orderNo));
        
        System.out.println("Order No.: "+orderNo);

    } 
    
    @Test //Order Samples Page :: SUMST :: Sales Org material auto-hides MUM type
    (groups ={"CCE","CCE_Orders"})
    public void SOC7() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SAMPLE ORDER SOC7: Sales Org Material Master hides MUM Type", "G_CCE_SOC_3");
        
        System.out.println("Navigaing to Sales Org Materials Master...");
        
        Mst_SalesOrgMaterialsPage somPage = ccePage.selectSalesOrgMaterials();
        somPage.waitForElement();
        
        System.out.println("Page reached. Setting article and sales org...");
        
        somPage.setArticle(DataItems.conOrdArticle);
        somPage.setSalesOrg(DataItems.sampSalesOrg);
        
        System.out.println("Article set. Listing records...");
        
        somPage.pressSearch();
        somPage.waitForElement();
        
        System.out.println("Records listed. Getting hidden MUM Types...");
        
        String type = somPage.getHiddenTypes();
        
        System.out.println("Type-to-hide received for Article: " + DataItems.conOrdArticle + " with Sales Org.: " + DataItems.sampSalesOrg + " is "  + type);
        
        System.out.println("Navigating to Order Samples...");
        
        CCE_OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(DataItems.custDetails[0]);
        orderSamples.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        CCE_AddOrderPage addOrder = orderSamples.pressSubmit();
        addOrder.waitForElement();
        
        System.out.println("Prompt submitted. Setting ship to party...");
        
        addOrder.setShipToParty(DataItems.custDetails[1]);
        
        System.out.println("Ship-to set. Setting article...");
        
        addOrder.setArticle(DataItems.conOrdArticle, 0);
        boolean waitForTicketUpdate = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElement(addOrder.getTicketField(), DataItems.conOrdTicket));
        
        System.out.println("Article set. Setting shade code to allow time for page update...");
        
        addOrder.setShadeCode(DataItems.shadeCode, 0);
        
        System.out.println("Shade code set. Checking MUM is hidden...");
        
        AssertJUnit.assertTrue("Order Samples (Add order) Page: MUM Type listed in Sales Org Materials not hidden when article entered",addOrder.checkHidden(type));
        
        System.out.println("MUM Type correctly hidden, consistent with master data.");
    }
    
    @Test //Order Samples Page :: SUMST :: Direct enrich feature available and Warehouse option working
    (groups = {"CCE","CCE_Orders"})
    public void SOC8() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SAMPLE ORDER SOC8: Direct Enrich: Available and working. Warehouse SOS test", "G_CCE_SOC_12");
        
        System.out.println("Navigating to Order Samples...");
        
        CCE_OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(DataItems.custDetails[0]);
        orderSamples.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        CCE_AddOrderPage addOrder = orderSamples.pressSubmit();
        addOrder.waitForElement();
        
        System.out.println("Promt submitted. Entering ship-to: ");
        
        addOrder.setShipToParty(DataItems.custDetails[1]);
        
        System.out.println("Ship To entered. Adding order details...");
        
        addOrder.setArticle(DataItems.article, 0);
        addOrder.setShadeCode(DataItems.shadeCode, 0);
        addOrder.setMUMType("Cop", 0);
        addOrder.setRequestType(DataItems.colourMatch, 0);
        addOrder.setPurposeType(DataItems.bulkPurpose, 0);
        addOrder.setCustomerRef(0);
        addOrder.setQuantity(1, 0);
        
        System.out.println("Details set. Asserting Direct Enrich feature available...");
        
        AssertJUnit.assertTrue("Add Order (Order Samples) Page: Direct Enrich feature not displayed",
                addOrder.getDirEnYesButton().isDisplayed() && addOrder.getDirEnNoButton().isDisplayed());
        
        System.out.println("Direct Enrich feature available. Select 'Yes'...");
        
        addOrder.getDirEnYesButton().click();
        
        System.out.println("Enrich selected. Checking fields appear...");
        
        addOrder.checkEnrichFields();
        
        System.out.println("Fields appear. Setting SOS to Warehouse...");
        
        addOrder.getEnrichWHSButton().click();
        
        System.out.println("SOS set. Submitting order...");
        
        CCE_OrderStatusPage statusPage = addOrder.pressSubmit();
        statusPage.waitForElement();
        
        System.out.println("Order submitted. Finding order and retrieving order number...");
        
        String orderNo = statusPage.getOrderNo(DataItems.lastUsedPO);
        
        AssertJUnit.assertFalse("Order Status Page: Order with reference:"+DataItems.lastUsedPO+" not found in table",orderNo==null);       
        
        AssertJUnit.assertTrue("Order Status Page: Order stage not as expected. Order No.: " + orderNo, statusPage.getOrderStage(orderNo).equals("Send to INBOX"));
        
        System.out.println("Order No.: " + orderNo + ". Searching for order in SAP Log...");
        
        CCE_SAPLogPage sapPage = statusPage.pressSAPLog();
        sapPage.waitForElement();
        
        System.out.println("SAP Log reached. Searching for order...");
        
        AssertJUnit.assertFalse("SAP Log Page: Order with order no.: " + orderNo + " could not be found",sapPage.findOrder(orderNo)==null);
        
        System.out.println("Order found.");
        System.out.println("Order No.: " + orderNo);
        System.out.println("Customer Reference: " + DataItems.lastUsedPO);
        System.out.println("Order Status: Send to INBOX");
        System.out.println("SAP Status: " + sapPage.findOrder(orderNo));
    }
    
    @Test //Order Samples Page :: SUMST :: Direct enrich feature, Hub/Lab options working
    (groups = {"CCE","CCE_Orders"})
    public void SOC9() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SAMPLE ORDER SOC9: Direct Enrich feature: Hub/Lab options test", "G_CCE_SOC_12");
        
        System.out.println("Navigating to Order Samples...");
        
        CCE_OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(DataItems.custDetails[0]);
        orderSamples.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        CCE_AddOrderPage addOrder = orderSamples.pressSubmit();
        addOrder.waitForElement();
        
        System.out.println("Promt submitted. Entering ship-to: ");
        
        addOrder.setShipToParty(DataItems.custDetails[1]);
        
        System.out.println("Ship To entered. Adding order details...");
        
        addOrder.setArticle(DataItems.article, 0);
        addOrder.setShadeCode(DataItems.shadeCode, 0);
        addOrder.setMUMType("Cop", 0);
        addOrder.setRequestType(DataItems.colourMatch, 0);
        addOrder.setPurposeType(DataItems.bulkPurpose, 0);
        addOrder.setCustomerRef(0);
        addOrder.setQuantity(1, 0);
        addOrder.getDirEnYesButton().click();
        
        System.out.println("Details set. Setting SOS to Lab...");
        
        addOrder.getEnrichLabButton().click();
        
        System.out.println("SOS set. Submitting order...");
        
        CCE_OrderStatusPage statusPage = addOrder.pressSubmit();
        statusPage.waitForElement();
        
        System.out.println("Order submitted. Finding order and retrieving order number...");
        
        String orderNo = statusPage.getOrderNo(DataItems.lastUsedPO);
        
        AssertJUnit.assertFalse("Order Status Page: Order with reference:"+DataItems.lastUsedPO+" not found in table",orderNo==null); 
        
        AssertJUnit.assertTrue("Order Status Page: Order Stage not as expected. Order No.: " + orderNo, statusPage.getOrderStage(orderNo).equals("Lab SOS"));
        
        System.out.println("Order No.: " + orderNo + ". Searching for order in LRM Log...");
        
        CCE_LRMLogPage lrmPage = statusPage.pressLRMLog();
        lrmPage.waitForElement();
        
        System.out.println("LRM Log reached. Searching for order...");
        
        AssertJUnit.assertFalse("LRM Log Page: Order with order no.: " + orderNo + " could not be found",lrmPage.findOrder(orderNo)==null);
        
        System.out.println("Order found.");
        System.out.println("Order No.: " + orderNo);
        System.out.println("Customer Reference: " + DataItems.lastUsedPO);
        System.out.println("Order Stage: Lab SOS");
        System.out.println("Error Message: " + lrmPage.findOrder(orderNo));
        
        System.out.println("Testing Hub SOS...");
        
        CCE_OrderSamplesPage samplesPage = lrmPage.pressOrderSamples();
        samplesPage.waitForElement();
        
        System.out.println("Order prompt reached. Entering customer details...");
        
        samplesPage.setCustName(DataItems.custDetails[0]);
        samplesPage.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Details entered. Submitting...");
        
        CCE_AddOrderPage orderPage = samplesPage.pressSubmit();
        orderPage.waitForElement();
        
        System.out.println("Add Order Page reached. Entering ship-to...");
        
        orderPage.setShipToParty(DataItems.custDetails[1]);
        
        System.out.println("Ship-to set. Entering order line details...");
        
        addOrder.setArticle(DataItems.article, 0);
        addOrder.setShadeCode(DataItems.shadeCode, 0);
        addOrder.setMUMType("Cop", 0);
        addOrder.setRequestType(DataItems.colourMatch, 0);
        addOrder.setPurposeType(DataItems.bulkPurpose, 0);
        addOrder.setCustomerRef(0);
        addOrder.setQuantity(1, 0);
        addOrder.getDirEnYesButton().click();
        
        System.out.println("Details set. Setting SOS to Hub...");
        
        addOrder.getEnrichHubButton().click();
        
        System.out.println("SOS Set. Submitting order...");
        
        CCE_OrderStatusPage statusPage2 = addOrder.pressSubmit();
        statusPage2.waitForElement();
        
        System.out.println("Order submitted.");
        
        String orderNo2 = statusPage.getOrderNo(DataItems.lastUsedPO);
        
        AssertJUnit.assertFalse("Order Status Page: Order with reference:"+DataItems.lastUsedPO+" not found in table",orderNo2==null); 
        
        AssertJUnit.assertTrue("Order Status Page: Order Stage not as expected. Order No.: " + orderNo2, statusPage.getOrderStage(orderNo2).equals("Hub SOS"));
        
        System.out.println("Order No.: " + orderNo2 + ". Searching for order in Hub SOS...");
        
        CCE_HubSosPage hubPage = statusPage2.pressHubSos();
        hubPage.waitForElement();
        
        System.out.println("Hub SOS reached. Searching for order...");
        
        AssertJUnit.assertFalse("Hub SOS Page: Order with order no.: " + orderNo2 + " could not be found",hubPage.findOrder(orderNo2)==null);
        
        System.out.println("Order found.");
        System.out.println("Order No.: " + orderNo2);
        System.out.println("Customer Reference: " + DataItems.lastUsedPO);
        System.out.println("Order Stage: "+hubPage.findOrder(orderNo2));
        
    }
    
    @Test //Order Samples Page :: SUMST :: New Buyer function
    (groups = {"CCE"})
    public void SOC10() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SAMPLE ORDER SOC10: New Buyer function", "OP_NBB_01");
        
        System.out.println("Navigating to Order Samples...");
        
        CCE_OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(DataItems.custDetails[0]);
        orderSamples.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        CCE_AddOrderPage addOrder = orderSamples.pressSubmit();
        addOrder.waitForElement();
        
        System.out.println("Promt submitted. Pressing new buyer...");
        
        CCE_NewBuyerPage nbPage = addOrder.pressNewBuyer();
        nbPage.waitForElement();
        
        System.out.println("New Buyer page reached. Checking fields...");
        
        nbPage.checkFields();
        
        AssertJUnit.assertTrue("New Buyer Page: Title not as expected",nbPage.getTitleField().getText().equals("NEW BUYER"));
        
        System.out.println("Fields checked. Entering details...");
        
        String buyerName = CommonTask.generatePO("buyer");
        
        System.out.println("Name requested: " + buyerName);
        
        nbPage.setCustomerName(DataItems.custDetails[0]);
        nbPage.setBuyerName(buyerName);
        nbPage.setBuyerDesc("Automated Test - no need to act");
        
        System.out.println("Details set. Submitting...");
        
        CCE_AddOrderPage addPage = nbPage.pressSubmit();
        addPage.waitForElement();
        
        System.out.println("Add Order page reached.");
    }
    
    @Test //Order Samples Page :: SUMST :: Above Qty for LAB but within threshold Qty
    (groups = {"CCE","CCE_Orders"})
    public void SOC11() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();
        
        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("SAMPLE ORDER SOC11: Single line, above LAB qty but within threshold", "G_CCE_SOC_5");
        
        System.out.println("Navigating to Order Samples...");
        
        CCE_OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(DataItems.custDetails[0]);
        orderSamples.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        CCE_AddOrderPage addOrder = orderSamples.pressSubmit();
        
        System.out.println("Prompt submitted. Adding order details...");
        
        //Adding details
        addOrder.setShipToParty(DataItems.custDetails[1]);
        addOrder.setBrand(DataItems.thresholdBrand, 0);
        addOrder.setShadeCode(DataItems.shadeCode, 0);
        addOrder.setMUMType(DataItems.thresholdMUMType, 0);
        addOrder.setTicket(DataItems.thresholdTicket, 0);
        addOrder.setCustomerRef(0);
        addOrder.setPurposeType(DataItems.bulkPurpose, 0);
        addOrder.setRequestType(DataItems.sewing, 0);
        addOrder.setQuantity(DataItems.thresholdQty, 0);
        
        try {
            Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
            AssertJUnit.assertTrue("Order Samples Page: Free quantity exceeded. Unexpected alert appeared: " + alert.getText(),alert.getText().contains("exceed the limit for free sample"));
            alert.accept();
        } catch (TimeoutException t) {
            System.out.println("No alert appeared");
        }
        
        System.out.println("Details added. Submitting order...");
        
        CCE_OrderStatusPage statusPage = addOrder.pressSubmit();
        statusPage.waitForElement();
        
        System.out.println("Order submitted. Retrieving Order No...");
        
        String orderNo = statusPage.getOrderNo(DataItems.lastUsedPO);
        
        System.out.println("Order No.: " + orderNo);
        
        System.out.println("Checking order appears in status page with correct SOS...");
        
        AssertJUnit.assertFalse("Order Status Page: Order (Order No.:" + orderNo+"), not found",statusPage.getCurrentSOS(orderNo).equals("not found"));
        
        String currentSOS = statusPage.getCurrentSOS(orderNo);
        
        AssertJUnit.assertTrue("Order Status Page: Current SOS not as expected. Warehouse expected", currentSOS.equals("Warehouse"));
        
        System.out.println("Current SOS as expected.");
        System.out.println("Order No.: " + orderNo);
        System.out.println("Customer PO: " + DataItems.lastUsedPO);
        System.out.println("Current SOS: " + currentSOS);
    }
    
}
