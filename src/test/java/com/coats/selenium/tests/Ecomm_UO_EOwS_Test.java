package com.coats.selenium.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.FileFactory;
import PageObjects.*;
import com.coats.selenium.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/**
 * Created by Stefan on 23.03.2016.
 *
 *
 * Prerequisites: Ensure Cce_EnableOrdersWithoutShade_Test -> EOwS_GC_010 is run before this test.
 * We need to enable the flag as a prerequisite.
 */
public class Ecomm_UO_EOwS_Test extends DriverFactory {
    @Test //Enable Order without Shade :: Blank shade orders are separately ordered - Waiting for shade.
            (groups = {"eComm"})
    public void EOwS_GC_030() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();

        //new base test to set up
        Ecomm_Base uoRTTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uoRTTest1.setUp("UPLOAD ORDER Exceptions UORTex1: Invalid material number in spreadsheet", "GE_O_OC_URT_Ex1");

        System.out.println("Navigating to Upload Order...");

        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();

        System.out.println("Upload Order page loaded. Setting filepath...");

        //Create a file with 4 rows : One row with valid shade, one row with invalid shade and 2 rows without shade
        uploadPage.setFilePath(FileFactory.createFile("SUSST",4,"EOwS","",true));

        System.out.println("File path set. Uploading...");

        //Select existing mapping
        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();

        System.out.println("Mapping page reached. Confirming...");

        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();

        //Closing all alerts
        closeAlert(driver);

        closeAlert(driver);

        //Verifying that Order Confirmation Page is displayed
        orderConf.waitForElement();

        //Checking the Flash Message from Order Confirmation page
        System.out.println(orderConf.getFlashMessage());
        AssertJUnit.assertTrue(orderConf.getFlashMessage().contains("No. of rows Failed :0"));
        AssertJUnit.assertTrue(orderConf.getFlashMessage().contains("No. of rows Processed :4"));
        AssertJUnit.assertTrue(orderConf.getFlashMessage().contains("Your orders are split due to some of the order lines don’t have the shade code in the upload file or not defined in our WBA system. Please refer to orders/Waiting for shade or shade not available submenu for more detail."));

        //Get PO number
        String currentPO = orderConf.getCustUploadPOField().getAttribute("value");
        System.out.println("PO number in Order Confirmation page:"+currentPO);
        //Press submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();

        System.out.println("Order submitted.Navigating to Outstanding Upload Order...");

        //Filtering on current PO
        outOrdersPage.setCustomerPO(currentPO);

        //Verifying the No Of Order Lines compared to the Order Status
        outOrdersPage.pressSearch();

        StringBuilder currentOrderNumber = new StringBuilder();
        for (int i=0; i<3;i++){
            String orderNo = outOrdersPage.getOrderNumberSUMST(i);
            String orderStatus = outOrdersPage.getOrderStatus(i);

            System.out.println("Order number on row"+i+":"+orderNo);
            System.out.println("Order status on row"+i+":"+orderStatus);

            if (orderStatus.contains("Waiting For Shade Code")){
                System.out.println("Verifying No. of Order Lines when Status is "+orderStatus+"...");
                AssertJUnit.assertEquals("Incorrect No. of Order Lines!","2",outOrdersPage.getNoOfOrderLines(i));
                currentOrderNumber.append(orderNo);
                System.out.println("Verified!");
            }
            else {
                System.out.println("Verifying No. of Order Lines when Status is "+orderStatus+"...");
                AssertJUnit.assertEquals("Incorrect No. of Order Lines!","1",outOrdersPage.getNoOfOrderLines(i));
                System.out.println("Verified!");
            }
        }

        System.out.println(currentOrderNumber);

        //Navigating to Waiting for Shade Page
        Ecomm_WaitingForShadePage waitForShadePage = eCommPage.clickWaitingForShade();

        waitForShadePage.waitForElement();

        //Searching for Current PO number
        waitForShadePage.setCustPO(currentPO);

        waitForShadePage.pressSearch();

        //Verifying the table content
        AssertJUnit.assertEquals("Incorrect Sales Organization!","ID51",waitForShadePage.getTableSalesOrgText());
        AssertJUnit.assertEquals("Incorrect Customer Name!","Life Easy Customer",waitForShadePage.getTableCustNameText());
        AssertJUnit.assertEquals("Incorrect Customer PO No.!",currentPO,waitForShadePage.getTableCustPONo());
        AssertJUnit.assertEquals("Incorrect eComm Order No.!",currentOrderNumber.toString(),waitForShadePage.getTableOrderNo());
        AssertJUnit.assertEquals("Incorrect No. of Order Lines!","2",waitForShadePage.getTableNoOfOrderLines());
        System.out.println("Test PASSED!");
    }

    @Test //Enable Order without Shade :: Invalid shade orders are separately ordered - Shade Nt Available.
            (groups = {"eComm"})
    public void EOwS_GC_40() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();

        //new base test to set up
        Ecomm_Base uoRTTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uoRTTest1.setUp("UPLOAD ORDER Exceptions UORTex1: Invalid material number in spreadsheet", "GE_O_OC_URT_Ex1");

        System.out.println("Navigating to Upload Order...");

        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();

        System.out.println("Upload Order page loaded. Setting filepath...");

        //Create a file with 4 rows : One row with valid shade, one row with invalid shade and 2 rows without shade
        uploadPage.setFilePath(FileFactory.createFile("SUSST", 4, "EOwS", "", true));

        System.out.println("File path set. Uploading...");

        //Select existing mapping
        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();

        System.out.println("Mapping page reached. Confirming...");

        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();

        //Closing all alerts
        closeAlert(driver);

        closeAlert(driver);

        //Verifying that Order Confirmation Page is displayed
        orderConf.waitForElement();

        //Checking the Flash Message from Order Confirmation page
        System.out.println(orderConf.getFlashMessage());
        AssertJUnit.assertTrue(orderConf.getFlashMessage().contains("No. of rows Failed :0"));
        AssertJUnit.assertTrue(orderConf.getFlashMessage().contains("No. of rows Processed :4"));
        AssertJUnit.assertTrue(orderConf.getFlashMessage().contains("Your orders are split due to some of the order lines don’t have the shade code in the upload file or not defined in our WBA system. Please refer to orders/Waiting for shade or shade not available submenu for more detail."));

        //Get PO number
        String currentPO = orderConf.getCustUploadPOField().getAttribute("value");
        System.out.println("PO number in Order Confirmation page:" + currentPO);
        //Press submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();

        System.out.println("Order submitted.Navigating to Outstanding Upload Order...");

        //Filtering on current PO
        outOrdersPage.setCustomerPO(currentPO);

        //Verifying the No Of Order Lines compared to the Order Status
        outOrdersPage.pressSearch();

        StringBuilder currentOrderNumber = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            String orderNo = outOrdersPage.getOrderNumberSUMST(i);
            String orderStatus = outOrdersPage.getOrderStatus(i);

            System.out.println("Order number on row" + i + ":" + orderNo);
            System.out.println("Order status on row" + i + ":" + orderStatus);

            if (orderStatus.contains("Waiting For Shade Code")) {
                System.out.println("Verifying No. of Order Lines when Status is " + orderStatus + "...");
                AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "2", outOrdersPage.getNoOfOrderLines(i));
                currentOrderNumber.append(orderNo);
                System.out.println("Verified!");
            } else {
                System.out.println("Verifying No. of Order Lines when Status is " + orderStatus + "...");
                AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "1", outOrdersPage.getNoOfOrderLines(i));
                System.out.println("Verified!");
            }
        }

        System.out.println(currentOrderNumber);


        //Navigating to Waiting for Shade Page
        Ecomm_ShadeNotAvailablePage shadeNotAvailablePage = eCommPage.clickShadeNotAvailable ();

        shadeNotAvailablePage.waitForLoad();

        //Searching for Current PO number
        shadeNotAvailablePage.setCustPO(currentPO);

        shadeNotAvailablePage.pressSearch();

        //Verifying the table content
        AssertJUnit.assertEquals("Incorrect Sales Organization!","ID51",shadeNotAvailablePage.getTableSalesOrgText());
        AssertJUnit.assertEquals("Incorrect Customer Name!","Life Easy Customer",shadeNotAvailablePage.getTableCustNameText());
        AssertJUnit.assertEquals("Incorrect Customer PO No.!",currentPO,shadeNotAvailablePage.getTableCustPONo());
        //AssertJUnit.assertEquals("Incorrect eComm Order No.!",currentOrderNumber.toString(),shadeNotAvailablePage.getTableOrderNo());
        AssertJUnit.assertEquals("Incorrect No. of Order Lines!","1",shadeNotAvailablePage.getTableNoOfOrderLines());
        System.out.println("Test PASSED!");

    }

    @Test //Enable Order without Shade :: Edit Waiting for shade and reorder
            (groups = {"eComm"})
    public void EOwS_GC_050() throws Exception{
        //new chrome driver
        WebDriver driver = getDriver();

        //new base test to set up
        Ecomm_Base uoRTTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uoRTTest1.setUp("UPLOAD ORDER Exceptions UORTex1: Invalid material number in spreadsheet", "GE_O_OC_URT_Ex1");

        System.out.println("Navigating to Upload Order...");

        //new upload order page
        Ecomm_UploadOrderPage uploadPage = eCommPage.clickUploadOrder();
        uploadPage.waitForElement();

        System.out.println("Upload Order page loaded. Setting filepath...");

        //Create a file with 4 rows : One row with valid shade, one row with invalid shade and 2 rows without shade
        uploadPage.setFilePath(FileFactory.createFile("SUSST", 4, "EOwS", "", true));

        System.out.println("File path set. Uploading...");

        //Select existing mapping
        Ecomm_MappingAlert mapAlert = uploadPage.pressUpload();
        Ecomm_MappingPage mapPage = mapAlert.pressYes();
        mapPage.waitForElement();

        System.out.println("Mapping page reached. Confirming...");

        Ecomm_OrderConfirmationPage orderConf = mapPage.pressConfirm();

        //Closing all alerts
        closeAlert(driver);

        closeAlert(driver);

        //Verifying that Order Confirmation Page is displayed
        orderConf.waitForElement();

        //Checking the Flash Message from Order Confirmation page
        System.out.println(orderConf.getFlashMessage());
        AssertJUnit.assertTrue(orderConf.getFlashMessage().contains("No. of rows Failed :0"));
        AssertJUnit.assertTrue(orderConf.getFlashMessage().contains("No. of rows Processed :4"));
        AssertJUnit.assertTrue(orderConf.getFlashMessage().contains("Your orders are split due to some of the order lines don’t have the shade code in the upload file or not defined in our WBA system. Please refer to orders/Waiting for shade or shade not available submenu for more detail."));

        //Get PO number
        String currentPO = orderConf.getCustUploadPOField().getAttribute("value");
        System.out.println("PO number in Order Confirmation page:" + currentPO);
        //Press submit
        Ecomm_OutstandingOrdersPage outOrdersPage = orderConf.pressSubmit();
        outOrdersPage.waitForElement();

        System.out.println("Order submitted.Navigating to Outstanding Upload Order...");

        //Filtering on current PO
        outOrdersPage.setCustomerPO(currentPO);

        //Verifying the No Of Order Lines compared to the Order Status
        outOrdersPage.pressSearch();

        StringBuilder currentOrderNumber = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            String orderNo = outOrdersPage.getOrderNumberSUMST(i);
            String orderStatus = outOrdersPage.getOrderStatus(i);

            System.out.println("Order number on row" + i + ":" + orderNo);
            System.out.println("Order status on row" + i + ":" + orderStatus);

            if (orderStatus.contains("Waiting For Shade Code")) {
                System.out.println("Verifying No. of Order Lines when Status is " + orderStatus + "...");
                AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "2", outOrdersPage.getNoOfOrderLines(i));
                currentOrderNumber.append(orderNo);
                System.out.println("Verified!");
            } else {
                System.out.println("Verifying No. of Order Lines when Status is " + orderStatus + "...");
                AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "1", outOrdersPage.getNoOfOrderLines(i));
                System.out.println("Verified!");
            }
        }

        System.out.println(currentOrderNumber);

        //Navigating to Waiting for Shade Page
        Ecomm_WaitingForShadePage waitForShadePage = eCommPage.clickWaitingForShade();

        waitForShadePage.waitForElement();

        //Searching for Current PO number
        waitForShadePage.setCustPO(currentPO);

        waitForShadePage.pressSearch();

        //Editing the order without shade
        waitForShadePage.pressEdit();
        driver.findElement(By.cssSelector("#remove_0 > td:nth-child(1) > a > span")).click();
        //driver.findElement(By.cssSelector("#s2id_shade_id")).click();
        CommonTask.setSearchField(driver,By.id("#s2id_shade_id"),"C1103");

    }

    //Methods used for this Class
    public void closeAlert(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            alert.accept();
            System.out.println("Alert closed!");
        } catch (Exception e) {
            System.out.println("No error(s) displayed");
        }
    }


    @Test
    public void testCreateFile123() throws Exception {
        FileFactory.createFile("SUSST",4,"EOwS","",true);
    }
}
