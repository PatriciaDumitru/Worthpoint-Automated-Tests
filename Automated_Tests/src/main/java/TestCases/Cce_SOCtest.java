
package TestCases;

import AutomationFramework.Categories;
import AutomationFramework.DataItems;
import PageObjects.CCE_AddOrderPage;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_OrderSamplesPage;
import PageObjects.CCE_OrderViewPage;
import PageObjects.CCE_OrderStatusPage;
import com.google.common.base.Verify;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Category(Categories.CCE_Orders.class)
public class Cce_SOCtest {
    

    @Category(Categories.CCE_Orders.class)
    @Test // Order Samples Page :: Page and filter checks, cancel function 
    public void SOC1() throws InterruptedException, IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("SAMPLE ORDER SOC1: User can select all fields", "G_CCE_SOC_2");
        
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
        
        System.out.println("----------------------------------------------------");
        
        driver.close();
        
    }
    
    @Category({Categories.CCE_Orders.class,Categories.QuickSuite.class})
    @Test //Order Samples Page :: Single line order
    public void SOC2() throws InterruptedException, IOException {
        
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("SAMPLE ORDER SOC2: Single line, within threshold", "G_CCE_SOC_1");
        
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
        
        System.out.println("----------------------------------------------------");
        
        driver.close();
        
    } 
    
    @Category(Categories.CCE_Orders.class)
    @Test //Order Samples Page :: Multi-line order
    public void SOC3() throws InterruptedException, IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("SAMPLE ORDER SOC3: Multiple line, within threshold", "G_CCE_SOC_1 (multiple)");
        
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
        
        System.out.println("----------------------------------------------------");
        
        driver.close();
        
    }
    
    @Category(Categories.CCE_Orders.class)
    @Test //Order Samples Page :: Single line order, quantity above threshold
    public void SOC4() throws InterruptedException, IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("SAMPLE ORDER SOC4: Single line, above threshold", "G_CCE_SOC_6");
        
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
        WebElement waitForMessage = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(CCE_AddOrderPage.flashMessageLocator));
        
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
        
        System.out.println("----------------------------------------------------");
        
        driver.close();
        
    }
    
    @Category({Categories.CCE_Orders.class,Categories.QuickSuite.class})
    @Test //Order Samples Page :: Multi-line copied data
    public void SOC5() throws InterruptedException, IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("SAMPLE ORDER SOC5: Multiple lines, copied data", "G_CCE_SOC_8");
        
        System.out.println("Navigating to Order Samples...");
        
        CCE_OrderSamplesPage orderSamples = ccePage.pressOrderSamples();
        
        System.out.println("Order samples loaded. Entering customer details...");
        
        orderSamples.setCustName(DataItems.custDetails[0]);
        orderSamples.setRequestor(DataItems.custDetails[2]);
        
        System.out.println("Customer details entered. Submitting...");
        
        CCE_AddOrderPage addOrder = orderSamples.pressSubmit();
        
        System.out.println("Prompt submitted. Adding line 0 order details...");
        
        //Ship to, business principal, article, shade code, mum type, request, purpose, quantity, line numer
        addOrder.inputDetails(DataItems.custDetails[1],DataItems.custDetails[3],DataItems.expArticle,
                DataItems.expShadeCode,DataItems.coneMUM,DataItems.sewing,DataItems.salesSamp,2);
        
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
        
        System.out.println("Order details added. Submitting...");
        
        CCE_OrderStatusPage outstOrder = addOrder.pressSubmit();
        
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
        
        System.out.println("----------------------------------------------------");
    
        driver.close();
        
    }

    @Category(Categories.CCE_Orders.class)
    @Test //Order Samples Page :: Pend order 
    public void SOC6() throws InterruptedException, IOException {
        //New driver object to control browser
        WebDriver driver = new ChromeDriver();
        
        //New base object to handle log-in and set up
        Cce_SOC_Base base = new Cce_SOC_Base(driver);
        
        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.SUSST_SetUp("SAMPLE ORDER SOC6: Save as draft", "G_CCE_SOC_12");
        
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

        System.out.println("----------------------------------------------------");
        
        driver.close();
    } 
}
