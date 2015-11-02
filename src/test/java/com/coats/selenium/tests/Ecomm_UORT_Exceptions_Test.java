
package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.Ecomm_MainPage;
import PageObjects.Ecomm_MappingAlert;
import PageObjects.Ecomm_MappingPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_UploadOrderPage;
import com.coats.selenium.DriverFactory;
import static com.coats.selenium.DriverFactory.getDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class Ecomm_UORT_Exceptions_Test extends DriverFactory {
    
    @Test //Upload Order Realtime :: SUMST :: Incomplete material in spreadsheet exception
    (groups = {"eComm","eComm_Orders","Solo"})
    public void UORTex1() throws Exception{
        //new chrome driver
        WebDriver driver = getDriver();
        
        //new base test to set up
        Ecomm_Base uortTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uortTest1.SUSST_SetUp("UPLOAD ORDER Exceptions UORTex1: Invalid material number in spreadsheet", "GE_O_OC_URT_Ex1");
        
        System.out.println("Navigating to Upload Order...");
        
        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();
        
        System.out.println("Upload Order page loaded. Setting filepath...");
        
        uploadPage.setFilePath(DataItems.uploadExceptionFilepath);
        
        System.out.println("File path set. Uploading...");
        
        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();
        
        System.out.println("Mapping page reached. Setting sales org and customer name...");
        
        mapPage.setSalesOrg("ID52");
        mapPage.setCustomerName(DataItems.custDetails[0]);
        
        System.out.println("Details set. Setting mapping...");
        
        String[][] mapping = {  {"Customer Name","Customer Name"},
                                {"Article","N/A"},
                                {"Ticket","Ticket"},
                                {"Finish","Finish"},
                                {"Shade Code","Shade Code"},
                                {"Required Date","Required Date"},
                                {"Qty","Qty"},
                                {"Style","N/A"},
                                {"Style No./Production No.","N/A"},
                                {"Sub Account","N/A"},
                                {"Ship to Party Name","Ship to Party Name"},
                                {"Your Material No.","Your Material Number"},
                                {"Brand","Brand"},
                                {"Length","Length"},
                                {"Buyers","N/A"},
                                {"Customer PO No","Customer PO No"},
                                {"Requestor Name","Requestor Name"},
                                {"Warehouse Instruction","N/A"},
                                {"Buyer Sales Order Number","N/A"},
                                {"Other Information","N/A"},
                                {"Customer Price","N/A"}
                                };
        
        mapPage.setMapping(mapping);
        
        System.out.println("Mapping set. Confirming...");
        
        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();
        orderConf.waitForElement();
        
        System.out.println("Order confirmation reached. Asserting errors appear...");
        
        AssertJUnit.assertTrue("Order Confirmation Page: No errors found despite invalid material in spreadsheet",orderConf.viewErrors());
        
        System.out.println("Errors found.");
    }
    
}
