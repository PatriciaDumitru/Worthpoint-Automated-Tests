
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.AddOrderPage;
import PageObjects.CcePage;
import PageObjects.OrderSamplesPage;
import PageObjects.OrderViewPage_CCE;
import PageObjects.OrderStatusPage_CCE;
import com.google.common.base.Verify;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Cce_SOC {
    
    @Test
    public void fieldChecks() throws InterruptedException, IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("SAMPLE ORDER SOC1: User can select all fields", "G_CCE_SOC_2");
        
        System.out.println("Navigating to Order Samples...");
        
        OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotFolder+"\\Order Samples\\1Page.png"));
        
        System.out.println("Order samples loaded. Checking Customer Name, Customer Code, and Requestor fields...");
        
        //Check prompt fields
        orderSamples.checkFields();
        
        System.out.println("Fields checked. Entering customer details and submitting...");
        
        //Enter customer details, submit prompt and check order detail fields
        orderSamples.setCustName(TestSuite.custDetails[0]);
        orderSamples.setRequestor(TestSuite.custDetails[2]);
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotFolder+"\\Order Samples\\2Customer Entered.png"));
        
        AddOrderPage addOrder = orderSamples.pressSubmit();
        
        System.out.println("Details submitted. Checking Order Details fields...");
        
        //Take a screenshot
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotFolder+"\\Order Samples\\3Prompt submitted.png"));
        
        //Check all fields are clickable
        addOrder.checkFields();
        
        System.out.println("Fields checked. Testing cancel button...");
        
        OrderSamplesPage prompt = addOrder.pressCancel();
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotFolder+"\\Order Samples\\4Order cancelled.png"));
        
        System.out.println("Order cancelled.");
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
        
    } //Field checks and cancel
    
    @Test
    public void orderSamples1() throws InterruptedException, IOException {
        
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("SAMPLE ORDER SOC1: Single line, within threshold", "G_CCE_SOC_1");
        
        System.out.println("Navigating to Order Samples...");
        
        OrderSamplesPage orderSamples = ccePage.pressOrderSamples();       
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(TestSuite.custDetails[0]);
        orderSamples.setRequestor(TestSuite.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        AddOrderPage addOrder = orderSamples.pressSubmit();
        
        System.out.println("Prompt submitted. Adding order details...");
        
        //Ship to, business principal, article, shade code, mum type, request, purpose, quantity, line numer
        addOrder.inputDetails(TestSuite.custDetails[1],TestSuite.custDetails[3],TestSuite.expArticle,
                TestSuite.expShadeCode,TestSuite.copMUM,TestSuite.sewing,TestSuite.salesSamp,1);
        
        //Take a screenshot
        File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotFolder+"\\Order Samples\\5Order details added.png"));
        
        System.out.println("Order details added. Submitting order...");
        
        OrderStatusPage_CCE outstOrders = addOrder.pressSubmit();
        
        //Take a screenshot
        File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotFolder+"\\Order Samples\\6Order submitted.png"));
        
        System.out.println("Order submitted. Verifying order details...");
        
        //Line numbers start from 2
        OrderViewPage_CCE viewPage = outstOrders.pressView(2);
        
        //Take a screenshot
        File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile6,new File(TestSuite.screenshotFolder+"\\Order Samples\\7View Order.png"));
        
        //Switch to iframe
        viewPage.switchTo();
        
        Verify.verify(viewPage.getCustName().equals(TestSuite.custDetails[0]),"Sample Order View: Customer name incorrect in view");
        Verify.verify(viewPage.getRequestorName().equals(TestSuite.custDetails[2]),"Sample Order View: Requestor name incorrect in view");
        Verify.verify(viewPage.getShadeCode(2).equals(TestSuite.expShadeCode),"Sample Order View: Shade code incorrect in view");        
        
        System.out.println("Details verified. Exiting view page and ending test.");
        
        System.out.println("Order No.: "+viewPage.getOrderNumber());
        
        viewPage.closeView();    
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
        
    } //Single line order
    
    @Test
    public void orderSamples2() throws InterruptedException, IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("SAMPLE ORDER SOC2: Multiple line, within threshold", "G_CCE_SOC_1 (multiple)");
        
        System.out.println("Navigating to Order Samples...");
        
        OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(TestSuite.custDetails[0]);
        orderSamples.setRequestor(TestSuite.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        AddOrderPage addOrder = orderSamples.pressSubmit();
        
        System.out.println("Prompt submitted. Adding order details...");
        
        //Ship to, business principal, article, shade code, mum type, request, purpose, quantity, line numer
        addOrder.inputDetails(TestSuite.custDetails[1],TestSuite.custDetails[3],TestSuite.expArticle,
                TestSuite.expShadeCode,TestSuite.copMUM,TestSuite.sewing,TestSuite.salesSamp,1);
        
        System.out.println("Line 1 details added. Creating new line and adding details...");
              
        int lineNumber = 1;
        
        addOrder.pressNewLine(lineNumber);
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotFolder+"\\Order Samples\\8Line added.png"));
        
        //Ship to, business principal, article, shade code, mum type, request, purpose, quantity, line numer
        addOrder.inputAdditionalLines(TestSuite.article3,
                TestSuite.shadeCode3,TestSuite.copMUM,TestSuite.sewing,TestSuite.salesSamp,1,1);
        
        System.out.println("Details added. Submitting order...");
        
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotFolder+"\\Order Samples\\9Line filled.png"));
        
        OrderStatusPage_CCE outstOrders = addOrder.pressSubmit();
        
        System.out.println("Order submitted. Verifying order details...");
        
        //Line numbers start from 2
        OrderViewPage_CCE viewPage = outstOrders.pressView(2);
        
        File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotFolder+"\\Order Samples\\10View multiple lines.png"));
        
        //Ensure iframe is selected
        viewPage.switchTo();
        
        Verify.verify(viewPage.getCustName().equals(TestSuite.custDetails[0]),"Sample Order View: Customer name incorrect in view");
        Verify.verify(viewPage.getRequestorName().equals(TestSuite.custDetails[2]),"Sample Order View: Requestor name incorrect in view");
        Verify.verify(viewPage.getShadeCode(2).equals(TestSuite.expShadeCode),"Sample Order View: Shade code incorrect in view");        
        
        System.out.println("Order details verified. Exiting view and ending test.");
        
        System.out.println("Order No.: "+viewPage.getOrderNumber());
        
        outstOrders = viewPage.closeView();
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
        
    } //Multi-line order
    
    @Test
    public void orderSamples3() throws InterruptedException, IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("SAMPLE ORDER SOC3: Single line, above threshold", "G_CCE_SOC_6");
        
        System.out.println("Navigating to Order Samples...");
        
        OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(TestSuite.custDetails[0]);
        orderSamples.setRequestor(TestSuite.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        AddOrderPage addOrder = orderSamples.pressSubmit();
        
        System.out.println("Prompt submitted. Adding order details...");
        
        //Ship to, business principal, article, shade code, mum type, request, purpose, quantity, line numer
        addOrder.inputDetails(TestSuite.custDetails[1],TestSuite.custDetails[3],TestSuite.expArticle,
                TestSuite.expShadeCode,TestSuite.coneMUM,TestSuite.sewing,TestSuite.salesSamp,15);
        
        System.out.println("Details added. Submitting order, expecting rejection...");
        
        addOrder.pressSubmitExceeded();
        
        //Wait for message to appear
        WebElement waitForMessage = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(AddOrderPage.flashMessageLocator));
        
        String message = driver.findElement(AddOrderPage.flashMessageLocator).getText();
        
        if (message.contains("could not be saved")) {
            System.out.println("Order submitted. Message received: " + driver.findElement(AddOrderPage.flashMessageLocator).getText());
            System.out.println("Rejected as expected.");
        } else {
            System.out.println("***Unexpected message received upon submission.***");
        }
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotFolder+"\\Order Samples\\7Line added.png"));
        
        System.out.println("----------------------------------------------------");
        
        driver.quit();
        
    } //Qty above threshold
    
    @Test
    public void orderSamples4() throws InterruptedException, IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("SAMPLE ORDER SOC4: Multiple lines, copied data", "G_CCE_SOC_8");
        
        System.out.println("Navigating to Order Samples...");
        
        OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(TestSuite.custDetails[0]);
        orderSamples.setRequestor(TestSuite.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        AddOrderPage addOrder = orderSamples.pressSubmit();
        
        System.out.println("Prompt submitted. Adding line 0 order details...");
        
        //Ship to, business principal, article, shade code, mum type, request, purpose, quantity, line numer
        addOrder.inputDetails(TestSuite.custDetails[1],TestSuite.custDetails[3],TestSuite.expArticle,
                TestSuite.expShadeCode,TestSuite.coneMUM,TestSuite.sewing,TestSuite.salesSamp,2);
        
        System.out.println("Line 0 order details added. Adding new line...");
        
        //New line (will be line 1), copy data
        addOrder.pressNewLine(1);
        
        System.out.println("New line added. Copying data...");
        
        addOrder.pressCopy(1);
        
        System.out.println("Data copied. Adding remaining line 1 order details...");
        
        //Enter brand, ticket
        addOrder.setBrand(TestSuite.brand,1);
        addOrder.setTicket(TestSuite.ticket,1);
        addOrder.setShadeCode(TestSuite.shadeCode,1);
        
        System.out.println("Order details added. Submitting...");
        
        OrderStatusPage_CCE outstOrder = addOrder.pressSubmit();
        
        System.out.println("Details submitted. Verifying details...");
        
        //View first order in table...Line numbers start from 2
        OrderViewPage_CCE viewPage = outstOrder.pressView(2);
        
        viewPage.verifyCopied();
        
        System.out.println("Details verified.");
        
        System.out.println("Order No.: "+viewPage.getOrderNumber());
        
        System.out.println("----------------------------------------------------");
    
        driver.quit();
    } //Multi-line copied data

    @Test
    public void orderSamples5() throws InterruptedException, IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CcePage ccePage = base.SUSST_SetUp("SAMPLE ORDER SOC5: Save as draft", "G_CCE_SOC_12");
        
        System.out.println("Navigating to Order Samples...");
        
        OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(TestSuite.custDetails[0]);
        orderSamples.setRequestor(TestSuite.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        AddOrderPage addOrder = orderSamples.pressSubmit();
        
        System.out.println("Prompt submitted. Adding order details...");
        
        //Ship to, business principal, article, shade code, mum type, request, purpose, quantity, line numer
        addOrder.inputDetails(TestSuite.custDetails[1],TestSuite.custDetails[3],TestSuite.expArticle,
                TestSuite.expShadeCode,TestSuite.coneMUM,TestSuite.sewing,TestSuite.salesSamp,2);
        
        System.out.println("Order details added. Pressing pend order...");
        
        OrderStatusPage_CCE orderStatus = addOrder.pressPendOrder();
        
        String orderNo = orderStatus.pressView(2).getOrderNumber();
        
        if (orderStatus.checkStage(orderNo).equals("Order Drafted")) {
            System.out.println("Order pended, saved as a draft.");
        } else {
            System.out.println("***Order Draft: Unexpected order stage***");
        }

        System.out.println("----------------------------------------------------");
        
        driver.quit();
    } //Save as draft
}
