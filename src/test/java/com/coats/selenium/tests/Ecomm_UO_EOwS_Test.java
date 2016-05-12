package com.coats.selenium.tests;

import AutomationFramework.CommonTask;
import AutomationFramework.PreFlows;
import AutomationFramework.DataItems;
import AutomationFramework.FileFactory;
import AutomationFramework.Wait;
import PageObjects.*;
import com.coats.selenium.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import test.asserttests.AssertTest;

/**
 * Created by Stefan on 23.03.2016.
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
        Ecomm_MainPage eCommPage = uoRTTest1.setUp("Blank shade orders are separately ordered - Waiting for shade.", "EOwS_GC_030");


        //Enabling the flags for Sales Org and Customer
        PreFlows pf = new PreFlows();
        pf.enableEnableOrdersWithoutShadeForSalesOrgandCust(driver, DataItems.autoUserSalesOrg, DataItems.customerName);

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

        System.out.println("Mapping page loaded. Setting mapping...");

        //Mapping details
        //Element 0 of each array holds the field name. Element 1 of each array holds the corresponding header used in the file.
        //If there is no corresponding header in the file, use "N/A"
        String[][] mapping = {  {"Customer Name","Customer Name"},
                                {"Article","Article"},
                                {"Ticket","Ticket"},
                                {"Finish","Finish"},
                                {"Shade Code","Shade Code"},
                                {"Required Date","Required Date"},
                                {"Qty","Qty"},
                                {"Style","N/A"},
                                {"Style No./Production No.","N/A"},
                                {"Sub Account","N/A"},
                                {"Ship to Party Name","Ship to Party Name"},
                                {"Your Material No.","N/A"},
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

        Ecomm_MappingPage mappedPage = mapPage.setMappingWithoutLineRef(mapping);

        System.out.println("Mapping page reached. Confirming...");

        Ecomm_OrderConfirmationPage orderConf = mappedPage.pressConfirm();

        //Closing all alerts
        closeAlert(driver);

        closeAlert(driver);


        //Verifying that Order Confirmation Page is displayed
        orderConf.waitForPageElem();

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

    @Test //Enable Order without Shade :: Invalid shade orders are separately ordered - Shade Not Available.
            (groups = {"eComm"})
    public void EOwS_GC_040() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();

        //new base test to set up
        Ecomm_Base uoRTTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uoRTTest1.setUp("Incorrect shade orders are separately ordered - Shade not available", "EOwS_GC_040");

        //Enabling the flags for Sales Org and Customer
        PreFlows pf = new PreFlows();
        pf.enableEnableOrdersWithoutShadeForSalesOrgandCust(driver, DataItems.autoUserSalesOrg, DataItems.customerName);

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
        orderConf.waitForElement2();

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


        //Navigating to Shade Not Available
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

    @Test //Enable Order without Shade :: Edit Waiting for Shade and submit changes
            (groups = {"eComm"})
    public void EOwS_GC_050() throws Exception{
        //new chrome driver
        WebDriver driver = getDriver();

        //new base test to set up
        Ecomm_Base uoRTTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uoRTTest1.setUp("Edit Waiting for Shade and reorder", "EOwS_GC_050");

        //Enabling the flags for Sales Org and Customer
        PreFlows pf = new PreFlows();
        pf.enableEnableOrdersWithoutShadeForSalesOrgandCust(driver, DataItems.autoUserSalesOrg, DataItems.customerName);

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
        orderConf.waitForElement2();

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

        StringBuilder noShadeOrderNumber = new StringBuilder();
        StringBuilder correctShadeOrderNumber = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            String orderNo = outOrdersPage.getOrderNumberSUMST(i);
            String orderStatus = outOrdersPage.getOrderStatus(i);

            System.out.println("Order number on row" + (i+1) + ":" + orderNo);
            System.out.println("Order status on row" + (i+1) + ":" + orderStatus);


            if (orderStatus.contains("Waiting For Shade Code")) {
                System.out.println("Verifying No. of Order Lines when Status is " + orderStatus + "...");

                //Verify that Waiting For Shade Code status is set to 2 lines
                AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "2", outOrdersPage.getNoOfOrderLines(i));

                //
                noShadeOrderNumber.append(orderNo);
                System.out.println("Verified!");
            } else {
//                if (orderStatus.contains("Open")){
//                    System.out.println("Verifying No. of Order Lines when Status is " + orderStatus + "...");
//                    AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "1", outOrdersPage.getNoOfOrderLines(i));
//                    correctShadeOrderNumber.append(orderNo);
//                    System.out.println("Verified!");
//                } else {
                    System.out.println("Verifying No. of Order Lines when Status is " + orderStatus + "...");
                    AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "1", outOrdersPage.getNoOfOrderLines(i));
                    correctShadeOrderNumber.append(orderNo);
                    System.out.println("Verified!");
//                }
            }
        }

        System.out.println("Order No for Waiting for Shade Code order status:"+noShadeOrderNumber);
        //System.out.println("Order No for Open order status:"+correctShadeOrderNumber);


        //Navigating to Waiting for Shade Page
        Ecomm_WaitingForShadePage waitForShadePage = eCommPage.clickWaitingForShade();

        waitForShadePage.waitForElement();

        //Searching for Current PO number
        waitForShadePage.setCustPO(currentPO);

        waitForShadePage.pressSearch();

        //Editing the order without shade
        Ecomm_WaitingForShadeCodeOrderConfirmationPage waitForShadeCodeConf = waitForShadePage.pressEdit2();

        waitForShadeCodeConf.waitForElement();

        //Press Edit button of first table row
        Ecomm_OrderInformationPage orderInfoPage = waitForShadeCodeConf.pressEdit(0);

        CommonTask.waitForOverlay(driver);

        orderInfoPage.setShadeCode("C1103");
        Ecomm_WaitingForShadeCodeOrderConfirmationPage waitForShadeCodeConf2 = orderInfoPage.pressSubmit2();
        AssertJUnit.assertTrue("Flash message is not correct or update was not performed!",waitForShadeCodeConf2.getFlashMessage().contains("The Order Line 10 has been updated"));

        Ecomm_OutstandingOrdersPage outstandingOrdersPage2 = waitForShadeCodeConf2.pressConfirm();

        //Verify Flash Message
        AssertJUnit.assertTrue("Flash message is not correct!",outstandingOrdersPage2.getFlashMessage().contains(noShadeOrderNumber+" has been updated"));
        System.out.println("Test PASSED!");

    }

    @Test //Enable Order without Shade :: Edit Shade Not Available orders and then submit.
            (groups = {"eComm"})
    public void EOwS_GC_060() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();

        //new base test to set up
        Ecomm_Base uoRTTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uoRTTest1.setUp("Edit Shade Not Available and reorder", "EOwS_GC_060");

        //Enabling the flags for Sales Org and Customer
        PreFlows pf = new PreFlows();
        pf.enableEnableOrdersWithoutShadeForSalesOrgandCust(driver, DataItems.autoUserSalesOrg, DataItems.customerName);

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
        orderConf.waitForElement2();

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
            String shadeNotAvailable = outOrdersPage.getShadeNotAvailable(i);

            System.out.println("Order number on row" + i + ":" + orderNo);
            System.out.println("Order status on row" + i + ":" + orderStatus);
            System.out.println("Shade Not Available on row" + i + ":" + shadeNotAvailable);

            if (shadeNotAvailable.contains("Yes")) {
                System.out.println("Verifying No. of Order Lines when Shade Not Available is " + shadeNotAvailable + "...");
                AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "1", outOrdersPage.getNoOfOrderLines(i));
                currentOrderNumber.append(orderNo);
                System.out.println("Verified!");
            } else {
                if (orderStatus.contains("Waiting For Shade Code")) {
                    System.out.println("Verifying No. of Order Lines when Status is " + orderStatus + "...");
                    AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "2", outOrdersPage.getNoOfOrderLines(i));
                    System.out.println("Verified!");
                } else {
                    System.out.println("Verifying No. of Order Lines when Status is " + orderStatus + "...");
                    AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "1", outOrdersPage.getNoOfOrderLines(i));
                    System.out.println("Verified!");
                }
            }
        }

        System.out.println(currentOrderNumber);


        //Navigating to Shade Not Available
        Ecomm_ShadeNotAvailablePage shadeNotAvailablePage = eCommPage.clickShadeNotAvailable();

        shadeNotAvailablePage.waitForLoad();

        //Searching for our Customer Po
        shadeNotAvailablePage.setCustPO(currentPO);
        shadeNotAvailablePage.pressSearch();

        shadeNotAvailablePage.waitForLoad();

        //Edit the Order -> going to Shade Not Available Order Confirmation page
        Ecomm_ShadeOrderConfirmationPage waitShadeCodeOrderConf = shadeNotAvailablePage.pressEdit2();

        //Edit the order
        Ecomm_OrderInformationPage orderInfoPage = waitShadeCodeOrderConf.pressEdit();

        CommonTask.waitForOverlay(driver);

        orderInfoPage.setShadeCode("C1103");
        Ecomm_ShadeOrderConfirmationPage waitShadeCodeOrderConf2 = orderInfoPage.pressSubmit();
        AssertJUnit.assertTrue("Flash message is not correct or update was not performed!",waitShadeCodeOrderConf2.getFlashMessage().contains("The Order Line 10 has been updated"));

        Ecomm_OutstandingOrdersPage outstandingOrdersPage2 = waitShadeCodeOrderConf2.pressSubmit();

        //Verify Flash Message -Checking to see if the new shade has changed
        AssertJUnit.assertTrue("Flash message is not correct!",outstandingOrdersPage2.getFlashMessage().contains(currentOrderNumber+" has been updated"));
        System.out.println("Test PASSED!");
    }

    @Test //Enable Order without Shade :: Edit Waiting for Shade but don't check to confirm
            (groups = {"eComm"})
    public void EOwS_GC_070() throws Exception{
        //new chrome driver
        WebDriver driver = getDriver();

        //new base test to set up
        Ecomm_Base uoRTTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uoRTTest1.setUp("Edit Waiting for shade but don't check to confirm", "EOwS_GC_070");

        //Enabling the flags for Sales Org and Customer
        PreFlows pf = new PreFlows();
        pf.enableEnableOrdersWithoutShadeForSalesOrgandCust(driver, DataItems.autoUserSalesOrg, DataItems.customerName);

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
        orderConf.waitForElement2();

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

        StringBuilder noShadeOrderNumber = new StringBuilder();
        StringBuilder correctShadeOrderNumber = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            String orderNo = outOrdersPage.getOrderNumberSUMST(i);
            String orderStatus = outOrdersPage.getOrderStatus(i);

            System.out.println("Order number on row" + (i+1) + ":" + orderNo);
            System.out.println("Order status on row" + (i+1) + ":" + orderStatus);


            if (orderStatus.contains("Waiting For Shade Code")) {
                System.out.println("Verifying No. of Order Lines when Status is " + orderStatus + "...");

                //Verify that Waiting For Shade Code status is set to 2 lines
                AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "2", outOrdersPage.getNoOfOrderLines(i));

                //
                noShadeOrderNumber.append(orderNo);
                System.out.println("Verified!");
            } else {
                System.out.println("Verifying No. of Order Lines when Status is " + orderStatus + "...");
                AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "1", outOrdersPage.getNoOfOrderLines(i));
                correctShadeOrderNumber.append(orderNo);
                System.out.println("Verified!");
            }
        }

        System.out.println("Order No for Waiting for Shade Code order status:"+noShadeOrderNumber);
        //System.out.println("Order No for Open order status:"+correctShadeOrderNumber);


        //Navigating to Waiting for Shade Page
        Ecomm_WaitingForShadePage waitForShadePage = eCommPage.clickWaitingForShade();

        waitForShadePage.waitForElement();

        //Searching for Current PO number
        waitForShadePage.setCustPO(currentPO);

        waitForShadePage.pressSearch();

        //Editing the order without shade
        Ecomm_WaitingForShadeCodeOrderConfirmationPage waitForShadeCodeConf = waitForShadePage.pressEdit2();

        waitForShadeCodeConf.waitForElement();

        //Press Edit button of first table row
        Ecomm_OrderInformationPage orderInfoPage = waitForShadeCodeConf.pressEdit(0);

        CommonTask.waitForOverlay(driver);

        orderInfoPage.setShadeCode("C1103");
        Ecomm_WaitingForShadeCodeOrderConfirmationPage waitForShadeCodeConf2 = orderInfoPage.pressSubmit2();
        AssertJUnit.assertTrue("Flash message is not correct or update was not performed!",waitForShadeCodeConf2.getFlashMessage().contains("The Order Line 10 has been updated"));

        //Press Back button
        Ecomm_WaitingForShadePage waitForShadePage2 = waitForShadeCodeConf2.pressBackBtn();

        //Verify that no or order line is still 2 in Waiting For Shade Code
        AssertJUnit.assertTrue("Incorrect number of order lines!",waitForShadePage2.getTableNoOfOrderLines().equals("2"));

        System.out.println("Test PASSED!");

    }

    @Test //Enable Order without Shade :: Edit Shade not available but don't confirm
            (groups = {"eComm"})
    public void EOwS_GC_080() throws Exception {
        //new chrome driver
        WebDriver driver = getDriver();

        //new base test to set up
        Ecomm_Base uoRTTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uoRTTest1.setUp("Edit Shade Not Available but don't confirm", "EOwS_GC_080");

        //Enabling the flags for Sales Org and Customer
        PreFlows pf = new PreFlows();
        pf.enableEnableOrdersWithoutShadeForSalesOrgandCust(driver, DataItems.autoUserSalesOrg, DataItems.customerName);

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
        orderConf.waitForElement2();

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
            String shadeNotAvailable = outOrdersPage.getShadeNotAvailable(i);

            System.out.println("Order number on row" + i + ":" + orderNo);
            System.out.println("Order status on row" + i + ":" + orderStatus);
            System.out.println("Shade Not Available on row" + i + ":" + shadeNotAvailable);

            if (shadeNotAvailable.contains("Yes")) {
                System.out.println("Verifying No. of Order Lines when Shade Not Available is " + shadeNotAvailable + "...");
                AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "1", outOrdersPage.getNoOfOrderLines(i));
                currentOrderNumber.append(orderNo);
                System.out.println("Verified!");
            } else {
                if (orderStatus.contains("Waiting For Shade Code")) {
                    System.out.println("Verifying No. of Order Lines when Status is " + orderStatus + "...");
                    AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "2", outOrdersPage.getNoOfOrderLines(i));
                    System.out.println("Verified!");
                } else {
                    System.out.println("Verifying No. of Order Lines when Status is " + orderStatus + "...");
                    AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "1", outOrdersPage.getNoOfOrderLines(i));
                    System.out.println("Verified!");
                }
            }
        }

        System.out.println(currentOrderNumber);


        //Navigating to Shade Not Available
        Ecomm_ShadeNotAvailablePage shadeNotAvailablePage = eCommPage.clickShadeNotAvailable();

        shadeNotAvailablePage.waitForLoad();

        //Searching for our Customer Po
        shadeNotAvailablePage.setCustPO(currentPO);
        shadeNotAvailablePage.pressSearch();

        shadeNotAvailablePage.waitForLoad();

        //Edit the Order -> going to Shade Not Available Order Confirmation page
        Ecomm_ShadeOrderConfirmationPage waitShadeCodeOrderConf = shadeNotAvailablePage.pressEdit2();

        //Edit the order
        Ecomm_OrderInformationPage orderInfoPage = waitShadeCodeOrderConf.pressEdit();

        CommonTask.waitForOverlay(driver);

        orderInfoPage.setShadeCode("C1103");
        Ecomm_ShadeOrderConfirmationPage waitShadeCodeOrderConf2 = orderInfoPage.pressSubmit();
        AssertJUnit.assertTrue("Flash message is not correct or update was not performed!",waitShadeCodeOrderConf2.getFlashMessage().contains("The Order Line 10 has been updated"));

        Ecomm_ShadeNotAvailablePage shadeNotAvailablePage2 = waitShadeCodeOrderConf2.pressBack();

        waitShadeCodeOrderConf2.pressBack(); // this will be removed when the extra alert is gone
        shadeNotAvailablePage2.pressSearch();

        //Verify that nothing really modified and the order number is still under Shade Not Available
        AssertJUnit.assertTrue("The Customer PO No cannot be found!",shadeNotAvailablePage2.getTableCustPONo().equals(currentPO));

        System.out.println("Test PASSED!");
    }

    @Test //Enable Order without Shade :: WBA-684
            (groups = {"eComm", "QuickTest"})
    public void EOwS_GC_090() throws Exception{
        //new chrome driver
        WebDriver driver = getDriver();

        //new base test to set up
        Ecomm_Base uoRTTest1 = new Ecomm_Base(driver);
        //Set up returns an eComm page
        Ecomm_MainPage eCommPage = uoRTTest1.setUp("WBA-684", "EOwS_GC_090");

        //Enabling the flags for Sales Org and Customer
        PreFlows pf = new PreFlows();
        pf.enableEnableOrdersWithoutShadeForSalesOrgandCust(driver, DataItems.autoUserSalesOrg, DataItems.customerName);

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
        orderConf.waitForElement2();

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

        StringBuilder noShadeOrderNumber = new StringBuilder();
        StringBuilder correctShadeOrderNumber = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            String orderNo = outOrdersPage.getOrderNumberSUMST(i);
            String orderStatus = outOrdersPage.getOrderStatus(i);

            System.out.println("Order number on row" + (i+1) + ":" + orderNo);
            System.out.println("Order status on row" + (i+1) + ":" + orderStatus);


            if (orderStatus.contains("Waiting For Shade Code")) {
                System.out.println("Verifying No. of Order Lines when Status is " + orderStatus + "...");

                //Verify that Waiting For Shade Code status is set to 2 lines
                AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "2", outOrdersPage.getNoOfOrderLines(i));

                //
                noShadeOrderNumber.append(orderNo);
                System.out.println("Verified!");
            } else {
                System.out.println("Verifying No. of Order Lines when Status is " + orderStatus + "...");
                AssertJUnit.assertEquals("Incorrect No. of Order Lines!", "1", outOrdersPage.getNoOfOrderLines(i));
                correctShadeOrderNumber.append(orderNo);
                System.out.println("Verified!");
            }
        }

        System.out.println("Order No for Waiting for Shade Code order status:"+noShadeOrderNumber);
        //System.out.println("Order No for Open order status:"+correctShadeOrderNumber);


        //Navigating to Waiting for Shade Page
        Ecomm_WaitingForShadePage waitForShadePage = eCommPage.clickWaitingForShade();

        waitForShadePage.waitForElement();

        //Searching for Current PO number
        waitForShadePage.setCustPO(currentPO);

        waitForShadePage.pressSearch();

        //Editing the order without shade
        Ecomm_WaitingForShadeCodeOrderConfirmationPage waitForShadeCodeConf = waitForShadePage.pressEdit2();

        waitForShadeCodeConf.waitForElement();

        //Press Edit button of first table row
        Ecomm_OrderInformationPage orderInfoPage = waitForShadeCodeConf.pressEdit(0);

        CommonTask.waitForOverlay(driver);

        orderInfoPage.setShadeCode("C1103");
        Ecomm_WaitingForShadeCodeOrderConfirmationPage waitForShadeCodeConf2 = orderInfoPage.pressSubmit2();
        waitForShadeCodeConf2.waitForElement();
        //AssertJUnit.assertTrue("Flash message is not correct or update was not performed!",waitForShadeCodeConf2.getFlashMessageText().contains("The Order Line 10 has been updated"));

        //Press Edit button on second row
        Ecomm_OrderInformationPage orderInfoPage2 = waitForShadeCodeConf2.pressEdit(0);
        CommonTask.waitForOverlay(driver);
        orderInfoPage2.setShadeCode("BLACKD");
        Ecomm_WaitingForShadeCodeOrderConfirmationPage waitForShadeCodeConf3 = orderInfoPage2.pressSubmit2();
        waitForShadeCodeConf3.waitForElement();
        //AssertJUnit.assertTrue("Flash message is not correct or update was not performed!",waitForShadeCodeConf3.getFlashMessageText().contains("The Order Line 20 has been updated"));

        //Un-check the first line checkbox
        CommonTask.unSetCheckBox(driver,waitForShadeCodeConf3.getCheckboxLocator(0));

        //Confirm order with only one row checked
        Ecomm_OutstandingOrdersPage outstandingOrdersPage2 = waitForShadeCodeConf3.pressConfirm();

        //Navigating to Waiting for Shade Page
        Ecomm_WaitingForShadePage waitForShadePage2 = eCommPage.clickWaitingForShade();

        waitForShadePage2.waitForElement();

        //Searching for Current PO number
        waitForShadePage2.setCustPO(currentPO);

        waitForShadePage2.pressSearch();

        //Editing the order without shade
        Ecomm_WaitingForShadeCodeOrderConfirmationPage waitForShadeCodeConf4 = waitForShadePage2.pressEdit2();

        //Confirming the order
        Ecomm_OutstandingOrdersPage outstandingOrdersPage3 = waitForShadeCodeConf4.pressConfirm();

        //Navigating to Waiting for Shade Page
        Ecomm_WaitingForShadePage waitForShadePage3 = eCommPage.clickWaitingForShade();

        waitForShadePage3.waitForElement();

        //Searching for Current PO number
        waitForShadePage3.setCustPO(currentPO);

        waitForShadePage3.pressSearch();

        //Verify the table is empty
        AssertJUnit.assertTrue("Table has content!",waitForShadePage3.getNoResultsText().equals("No records found."));


        //Verify that no or order line is still 2 in Waiting For Shade Code
        // AssertJUnit.assertTrue("Incorrect number of order lines!",waitForShadePage2.getTableNoOfOrderLines().equals("2"));

        //System.out.println("Test PASSED!");

    }

    //Methods used for this Class
    public void closeAlert(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert message:"+alert.getText());
            alert.accept();
            System.out.println("Alert closed!");
        } catch (Exception e) {
            System.out.println("No error(s) displayed");
        }
    }


    //@Test //enable this only when you need to create a file to use in manual tests
    public void testCreateFile123() throws Exception {
        FileFactory.createFile("SUSST",4,"EOwS","",true);
    }
}
