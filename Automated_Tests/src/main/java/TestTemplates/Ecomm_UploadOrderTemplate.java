
package TestTemplates;

import AutomationFramework.CommonTask;
import AutomationFramework.TestSuite;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_MappingAlert;
import PageObjects.Ecomm_MappingPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import PageObjects.Ecomm_UploadOrderPage;
import PageObjects.WBA_ContinuePage;
import PageObjects.WBA_LoginPage;
import PageObjects.WBA_SelectionPage;
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Ecomm_UploadOrderTemplate {
    
    public Ecomm_UploadOrderTemplate(String[] testDetails,String[] custDetails,String[] mapDetails) throws IOException, AWTException, InterruptedException {
        runTest(testDetails,custDetails,mapDetails);
    }
    
    public void runTest(String[] testDetails, String[] custDetails, String[] mapDetails) throws IOException, AWTException, InterruptedException {
        
        //Check user type in test details to find username and password
        String username="";
        String password="";
        switch(testDetails[1]) {
            case "SUSST Coats": username = TestSuite.validCoatsUsername; password = TestSuite.validCoatsPassword; break;
            case "SUSST Customer": username = TestSuite.validCustUsername; password = TestSuite.validCustPassword; break;
        }

        System.out.println("===Starting test: "+testDetails[0]+"===");
        
        //New driver instance
        System.setProperty("webdriver.chrome.driver",TestSuite.chromeDriverFilepath);
        WebDriver driver = new ChromeDriver();
        
        driver.get(TestSuite.targetURL);
        
        //maximise browser window
        driver.manage().window().maximize();
        
        System.out.println("Logging in...");
    
        //new LoginPage
        WBA_LoginPage liPage = new WBA_LoginPage(driver);

        //log in
        WBA_ContinuePage cont = liPage.loginAs(username,password);

        System.out.println("Logged in. Continuing to selection page...");

        //press continue, select eComm
        WBA_SelectionPage selectPage = cont.pressContinue();

        System.out.println("Selection page loaded. eComm selected...");

        Ecomm_MainPage eCommPage = selectPage.pressEcomm();
        eCommPage.waitForLoad();

        System.out.println("eComm page loaded. Navigating to Upload Order...");

        Ecomm_UploadOrderPage uoPage = eCommPage.clickUploadOrder();
        uoPage.waitForLoad();
        
        //Take a screenshot
        File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile1,new File(TestSuite.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\1Upload Order Page.png"));
        
        System.out.println("Upload Order Page reached. Setting filepath...");
        
        uoPage.setFilePath(testDetails[3]);
        
        System.out.println("Filepath set. Setting upload method...");
        
        if (testDetails[4].equals("true")) {
            uoPage.pressRealtime();
            System.out.println("Realtime selected. Uploading...");
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\2Upload in Realtime.png"));
        } else {
            uoPage.pressBackend();
            System.out.println("Backend selected. Uploading...");
            //Take a screenshot
            File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\2Upload in Backend.png"));
        }
        
        Ecomm_MappingAlert mapAlert = uoPage.pressUpload();
        
        if (testDetails[5].equals("true")) {
            Ecomm_MappingPage mapPage = mapAlert.pressYes();
            //Take a screenshot
            File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\3Mapping alert.png"));
            CommonTask.waitForPageLoad(driver);
            System.out.println("Existing map selected. Confirming map...");
            
            if (testDetails[1].equals("SUSST Coats")) {
                mapPage.setSalesOrg(mapDetails[0]);
                mapPage.setCustomerName(mapDetails[1]);
                
            }
            
            Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();
            orderConf.waitForLoad();
            
            if (!custDetails[0].equals("")) {
                orderConf.setRequestor(custDetails[0]);
            }
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\4Mapping confirmed.png"));
            
            Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit();
            outOrders.waitForLoad();
            CommonTask.waitForPageLoad(driver);
            
            //Take a screenshot
            File scrFile5= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\5Order submitted.png"));
            
        } else {
            Ecomm_MappingPage mapPage = mapAlert.pressNo();
            //Take a screenshot
            File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile3,new File(TestSuite.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\3Mapping alert.png"));
            System.out.println("Existing map rejected. Re-mapping...");
            
            if (testDetails[1].equals("SUSST Coats")) {
                mapPage.setSalesOrg(mapDetails[0]);
                mapPage.setCustomerName(mapDetails[1]); 
                mapPage.setSubAcct(mapDetails[11]);
                mapPage.setRequestor(mapDetails[18]);
            } 
            
            mapPage.setArticle(mapDetails[2]);
            mapPage.setTicket(mapDetails[3]);
            mapPage.setFinish(mapDetails[4]);
            mapPage.setShadeCode(mapDetails[5]);
            mapPage.setRequiredDate(mapDetails[6]);
            mapPage.setQty(mapDetails[7]);
            mapPage.setStyle(mapDetails[8]);
            mapPage.setStyleNo(mapDetails[9]);
            mapPage.setContractPO(mapDetails[10]);            
            mapPage.setShipToParty(mapDetails[12]);
            mapPage.setYourMatNum(mapDetails[13]);
            mapPage.setBrand(mapDetails[14]);
            mapPage.setLength(mapDetails[15]);
            mapPage.setBuyers(mapDetails[16]);
            mapPage.setCustPO(mapDetails[17]);
            mapPage.setWarehouseInst(mapDetails[19]);
            mapPage.setBuyerNumber(mapDetails[20]);
            mapPage.setOtherInfo(mapDetails[21]);
            mapPage.setLineRef(mapDetails[22]);
            mapPage.setCustPrice(mapDetails[23]);
            
             //Take a screenshot
            File scrFile5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile5,new File(TestSuite.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\4Mapping set.png"));

            Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();
            orderConf.waitForLoad();
            
            //Take a screenshot
            File scrFile4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile4,new File(TestSuite.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\5Mapping confirmed.png"));
            
            if (!custDetails[0].equals("")) {
                orderConf.setRequestor(custDetails[0]);
            }
            
            Ecomm_OutstandingOrdersPage outOrders = orderConf.pressSubmit();
            outOrders.waitForLoad();
            CommonTask.waitForPageLoad(driver);
            
            //Take a screenshot
            File scrFile6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile6,new File(TestSuite.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\6Order submitted.png"));
            
            
        }
        
        Ecomm_OutstandingOrdersPage outOrders2 = new Ecomm_OutstandingOrdersPage(driver);
        Ecomm_OrderViewPage viewPage = outOrders2.pressView(0);
        viewPage.waitForContent();
        
        //Take a screenshot
        File scrFile7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile7,new File(TestSuite.screenshotsFilepath+"\\Custom Tests\\"+testDetails[0]+"\\6Order view.png"));
        
        viewPage.closeView();
        viewPage.waitForInvisibility();
        
        driver.close();
        driver.quit();
        
        System.out.println("----------------------------------------------------");
        
    }
    
    
}
