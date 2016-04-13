package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import PageObjects.CCE_MainPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.Ecomm_OrderConfirmationPage;
import PageObjects.Ecomm_OrderViewPage;
import PageObjects.Ecomm_OutstandingOrdersPage;
import com.coats.selenium.DriverFactory;
import static com.coats.selenium.DriverFactory.getDriver;
import PageObjects.Ecomm_MOQ_Methods_Page;
import com.coats.selenium.tests.Cce_Base;
import com.google.common.base.Verify;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.*;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


public class Ecomm_MOQ_Test extends DriverFactory{

    //MOQ1 Template
    public void MOQ1(Integer quantity, String expectedMOQ, String article, String shade) throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("Profile 1" ,"ECOMM_MOQ_01");

        Ecomm_MOQ_Methods_Page moqMeth = new Ecomm_MOQ_Methods_Page(driver);

        moqMeth.setProfile01();

        moqMeth.goEccomManualEntry();

        moqMeth.setCustomerDetails();

        Ecomm_ManualEntryPage mePage = new Ecomm_ManualEntryPage(driver);

        //Setting Line Details
        System.out.println("Manual Entry : Entering line details...");

        System.out.println("Manual Entry : Setting article...");

        mePage.setArticle(article,0);

        System.out.println("Manual Entry : Setting shade...");

        mePage.setShadeCode(shade, 0);

        System.out.println("Manual Entry : Setting Qty ...");

        mePage.setQty(quantity, 0);

        System.out.println("Manual Entry : Setting date...");

        mePage.setDateToday(0);

        System.out.println("Manual Entry : Press next and accept alerts");

        moqMeth.pressNext2();

        System.out.println("Manual Entry : Assert MOQ Output");
        AssertJUnit.assertTrue("MOQ is not the right one", moqMeth.ajustMOQ().getText().equals(expectedMOQ));

    }

    @Test //MOQ1
            (groups = {"eComm", "QuickTest"})
    public void MOQ1_1() throws Exception {
        MOQ1(10,"10",DataItems.articleMOQ, DataItems.shadeCode01MOQ);
    }



    //MOQ2 Template
    public void MOQ2A(Integer quantity, String expectedMOQ, String article, String shade) throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("Profile 2" ,"ECOMM_MOQ_02");

        Ecomm_MOQ_Methods_Page moqMeth = new Ecomm_MOQ_Methods_Page(driver);

        moqMeth.setProfile02();

        System.out.println("Going Manual Entry page...");

        moqMeth.goEccomManualEntry();

        moqMeth.setCustomerDetails();

        Ecomm_ManualEntryPage mePage = new Ecomm_ManualEntryPage(driver);

        //Setting Line Details
        System.out.println("Manual Entry : Entering line details...");

        System.out.println("Manual Entry : Setting article...");

        mePage.setArticle(article,0);

        System.out.println("Manual Entry : Setting shade...");

        mePage.setShadeCode(shade, 0);

        System.out.println("Manual Entry : Setting Qty ...");

        mePage.setQty(quantity, 0);

        System.out.println("Manual Entry : Setting date...");

        mePage.setDateToday(0);

        System.out.println("Manual Entry : Press next and accept alerts");

        moqMeth.pressNext2();

        System.out.println("Manual Entry : Assert MOQ Output");
        AssertJUnit.assertTrue("MOQ is not the right one", moqMeth.ajustMOQ().getText().equals(expectedMOQ));

    }

    public void MOQ2B(Integer quantity, String expectedMOQ, String article, String shade) throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("Profile 2" ,"ECOMM_MOQ_02");

        Ecomm_MOQ_Methods_Page moqMeth = new Ecomm_MOQ_Methods_Page(driver);

        moqMeth.setProfile02();

        System.out.println("Going Manual Entry page...");

        moqMeth.goEccomManualEntry();

        moqMeth.setCustomerDetails();

        Ecomm_ManualEntryPage mePage = new Ecomm_ManualEntryPage(driver);

        //Setting Line Details
        System.out.println("Manual Entry : Entering line details...");

        System.out.println("Manual Entry : Setting article...");

        mePage.setArticle(article,0);

        System.out.println("Manual Entry : Setting shade...");

        mePage.setShadeCode(shade, 0);

        System.out.println("Manual Entry : Setting Qty ...");

        mePage.setQty(quantity, 0);

        System.out.println("Manual Entry : Setting date...");

        mePage.setDateToday(0);

        System.out.println("Manual Entry : Press next and accept alerts");

        moqMeth.pressNext1();

        System.out.println("Manual Entry : Assert MOQ Output");
        AssertJUnit.assertTrue("MOQ is not the right one", moqMeth.ajustMOQ().getText().equals(expectedMOQ));

    }


    @Test //MOQ2_1
            (groups = {"eComm", "QuickTest"})
    public void MOQ2_1() throws Exception {
        MOQ2B(10,"11",DataItems.articleMOQ, DataItems.shadeCode01MOQ);
    }

    @Test //MOQ2_2
            (groups = {"eComm"})
    public void MOQ2_2() throws Exception {
        MOQ2A(6,"6",DataItems.articleMOQ, DataItems.shadeCode02MOQ);
    }

    @Test //MOQ2_3
            (groups = {"eComm"})
    public void MOQ2_3() throws Exception {
        MOQ2B(15,"19",DataItems.articleMOQ, DataItems.shadeCode02MOQ);
    }

    @Test //MOQ2_4
            (groups = {"eComm"})
    public void MOQ2_4() throws Exception {
        MOQ2B(45,"55",DataItems.articleMOQ, DataItems.shadeCode02MOQ);
    }


    public void MOQ3A(Integer quantity, String expectedMOQ, String article, String shade) throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("Profile 3" ,"ECOMM_MOQ_03");

        Ecomm_MOQ_Methods_Page moqMeth = new Ecomm_MOQ_Methods_Page(driver);

        moqMeth.setProfile03();

        moqMeth.goEccomManualEntry();

        moqMeth.setCustomerDetails();

        Ecomm_ManualEntryPage mePage = new Ecomm_ManualEntryPage(driver);

        //Setting Line Details
        System.out.println("Manual Entry : Entering line details...");

        System.out.println("Manual Entry : Setting article...");

        mePage.setArticle(article,0);

        System.out.println("Manual Entry : Setting shade...");

        mePage.setShadeCode(shade, 0);

        System.out.println("Manual Entry : Setting Qty ...");

        mePage.setQty(quantity, 0);

        System.out.println("Manual Entry : Setting date...");

        mePage.setDateToday(0);

        System.out.println("Manual Entry : Press next and accept alerts");

        moqMeth.pressNext2();

        System.out.println("Manual Entry : Assert MOQ Output");
        AssertJUnit.assertTrue("MOQ is not the right one", moqMeth.ajustMOQ().getText().equals(expectedMOQ));

    }

    public void MOQ3B(Integer quantity, String expectedMOQ, String article, String shade) throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("Profile 3" ,"ECOMM_MOQ_03");

        Ecomm_MOQ_Methods_Page moqMeth = new Ecomm_MOQ_Methods_Page(driver);

        moqMeth.setProfile03();

        moqMeth.goEccomManualEntry();

        moqMeth.setCustomerDetails();

        Ecomm_ManualEntryPage mePage = new Ecomm_ManualEntryPage(driver);

        //Setting Line Details
        System.out.println("Manual Entry : Entering line details...");

        System.out.println("Manual Entry : Setting article...");

        mePage.setArticle(article,0);

        System.out.println("Manual Entry : Setting shade...");

        mePage.setShadeCode(shade, 0);

        System.out.println("Manual Entry : Setting Qty ...");

        mePage.setQty(quantity, 0);

        System.out.println("Manual Entry : Setting date...");

        mePage.setDateToday(0);

        System.out.println("Manual Entry : Press next and accept alerts");

        moqMeth.pressNext1();

        System.out.println("Manual Entry : Assert MOQ Output");
        AssertJUnit.assertTrue("MOQ is not the right one", moqMeth.ajustMOQ().getText().equals(expectedMOQ));

    }

    @Test //MOQ3_1
            (groups = {"eComm", "QuickTest"})
    public void MOQ3_1() throws Exception {
        MOQ3B(10,"11",DataItems.articleMOQ, DataItems.shadeCode01MOQ);
    }

    @Test //MOQ3_2
            (groups = {"eComm"})
    public void MOQ3_2() throws Exception {
        MOQ3A(6,"6",DataItems.articleMOQ, DataItems.shadeCode02MOQ);
    }

    @Test //MOQ3_3
            (groups = {"eComm"})
    public void MOQ3_3() throws Exception {
        MOQ3B(15,"23",DataItems.articleMOQ, DataItems.shadeCode02MOQ);
    }

    @Test //MOQ3_4
            (groups = {"eComm"})
    public void MOQ3_4() throws Exception {
        MOQ3B(45,"47",DataItems.articleMOQ, DataItems.shadeCode02MOQ);
    }



    public void MOQ4A(Integer quantity, String expectedMOQ, String article, String shade) throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("Profile 4" ,"ECOMM_MOQ_04");

        Ecomm_MOQ_Methods_Page moqMeth = new Ecomm_MOQ_Methods_Page(driver);

        moqMeth.setProfile04();

        moqMeth.goEccomManualEntry();

        moqMeth.setCustomerDetails();

        Ecomm_ManualEntryPage mePage = new Ecomm_ManualEntryPage(driver);

        //Setting Line Details
        System.out.println("Manual Entry : Entering line details...");

        System.out.println("Manual Entry : Setting article...");

        mePage.setArticle(article,0);

        System.out.println("Manual Entry : Setting shade...");

        mePage.setShadeCode(shade, 0);

        System.out.println("Manual Entry : Setting Qty ...");

        mePage.setQty(quantity, 0);

        System.out.println("Manual Entry : Setting date...");

        mePage.setDateTomorrow(0);

        System.out.println("Manual Entry : Press next and accept alerts");

        moqMeth.pressNext2();

        System.out.println("Manual Entry : Assert MOQ Output");
        AssertJUnit.assertTrue("MOQ is not the right one", moqMeth.ajustMOQ().getText().equals(expectedMOQ));

    }

    public void MOQ4B(Integer quantity, String expectedMOQ, String article, String shade) throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("Profile 4" ,"ECOMM_MOQ_04");

        Ecomm_MOQ_Methods_Page moqMeth = new Ecomm_MOQ_Methods_Page(driver);

        moqMeth.setProfile04();

        moqMeth.goEccomManualEntry();

        moqMeth.setCustomerDetails();

        Ecomm_ManualEntryPage mePage = new Ecomm_ManualEntryPage(driver);

        //Setting Line Details
        System.out.println("Manual Entry : Entering line details...");

        System.out.println("Manual Entry : Setting article...");

        mePage.setArticle(article,0);

        System.out.println("Manual Entry : Setting shade...");

        mePage.setShadeCode(shade, 0);

        System.out.println("Manual Entry : Setting Qty ...");

        mePage.setQty(quantity, 0);

        System.out.println("Manual Entry : Setting date...");

        mePage.setDateTomorrow(0);

        System.out.println("Manual Entry : Press next and accept alerts");

        moqMeth.pressNext1();

        System.out.println("Manual Entry : Assert MOQ Output");
        AssertJUnit.assertTrue("MOQ is not the right one", moqMeth.ajustMOQ().getText().equals(expectedMOQ));

    }

    @Test //MOQ4_1
            (groups = {"eComm"})
    public void MOQ4_1() throws Exception {
        MOQ4B(12,"23",DataItems.articleMOQ, DataItems.shadeCode02MOQ);
    }

    @Test //MOQ4_2
            (groups = {"eComm"})
    public void MOQ4_2() throws Exception {
        MOQ4B(7,"11",DataItems.articleMOQ, DataItems.shadeCode02MOQ);
    }


    //MOQ2 Template
    public void MOQ5A(Integer quantity, String expectedMOQ, String article, String shade) throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("Profile 2" ,"ECOMM_MOQ_02");

        Ecomm_MOQ_Methods_Page moqMeth = new Ecomm_MOQ_Methods_Page(driver);

        moqMeth.setProfile05();

        System.out.println("Going Manual Entry page...");

        moqMeth.goEccomManualEntry();

        moqMeth.setCustomerDetails();

        Ecomm_ManualEntryPage mePage = new Ecomm_ManualEntryPage(driver);

        //Setting Line Details
        System.out.println("Manual Entry : Entering line details...");

        System.out.println("Manual Entry : Setting article...");

        mePage.setArticle(article,0);

        System.out.println("Manual Entry : Setting shade...");

        mePage.setShadeCode(shade, 0);

        System.out.println("Manual Entry : Setting Qty ...");

        mePage.setQty(quantity, 0);

        System.out.println("Manual Entry : Setting date...");

        mePage.setDateTomorrow(0);

        System.out.println("Manual Entry : Press next and accept alerts");

        moqMeth.pressNext2();

        System.out.println("Manual Entry : Assert MOQ Output");
        AssertJUnit.assertTrue("MOQ is not the right one", moqMeth.ajustMOQ().getText().equals(expectedMOQ));

    }

    public void MOQ5B(Integer quantity, String expectedMOQ, String article, String shade) throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("Profile 2" ,"ECOMM_MOQ_02");

        Ecomm_MOQ_Methods_Page moqMeth = new Ecomm_MOQ_Methods_Page(driver);

        moqMeth.setProfile05();

        System.out.println("Going Manual Entry page...");

        moqMeth.goEccomManualEntry();

        moqMeth.setCustomerDetails();

        Ecomm_ManualEntryPage mePage = new Ecomm_ManualEntryPage(driver);

        //Setting Line Details
        System.out.println("Manual Entry : Entering line details...");

        System.out.println("Manual Entry : Setting article...");

        mePage.setArticle(article,0);

        System.out.println("Manual Entry : Setting shade...");

        mePage.setShadeCode(shade, 0);

        System.out.println("Manual Entry : Setting Qty ...");

        mePage.setQty(quantity, 0);

        System.out.println("Manual Entry : Setting date...");

        mePage.setDateTomorrow(0);

        System.out.println("Manual Entry : Press next and accept alerts");

        moqMeth.pressNext1();

        System.out.println("Manual Entry : Assert MOQ Output");
        AssertJUnit.assertTrue("MOQ is not the right one", moqMeth.ajustMOQ().getText().equals(expectedMOQ));

    }

    @Test //MOQ5_1
            (groups = {"eComm"})
    public void MOQ5_1() throws Exception {
        MOQ5B(25,"35",DataItems.articleMOQ, DataItems.shadeCode01MOQ);
    }

    @Test //MOQ5_1
            (groups = {"eComm"})
    public void MOQ5_2() throws Exception {
        MOQ5B(13,"19",DataItems.articleMOQ, DataItems.shadeCode02MOQ);
    }

    @Test //MOQ5_1
            (groups = {"eComm"})
    public void MOQ5_3() throws Exception {
        MOQ5A(5,"5",DataItems.articleMOQ, DataItems.shadeCode02MOQ);
    }

    public void MOQ6A(Integer quantity, String expectedMOQ, String article, String shade) throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("Profile 6" ,"ECOMM_MOQ_06");

        Ecomm_MOQ_Methods_Page moqMeth = new Ecomm_MOQ_Methods_Page(driver);

        moqMeth.setProfile05();

        System.out.println("Going Manual Entry page...");

        moqMeth.goEccomManualEntry();

        moqMeth.setCustomerDetails();

        Ecomm_ManualEntryPage mePage = new Ecomm_ManualEntryPage(driver);

        //Setting Line Details
        System.out.println("Manual Entry : Entering line details...");

        System.out.println("Manual Entry : Setting article...");

        mePage.setArticle(article,0);

        System.out.println("Manual Entry : Setting shade...");

        mePage.setShadeCode(shade, 0);

        System.out.println("Manual Entry : Setting Qty ...");

        mePage.setQty(quantity, 0);

        System.out.println("Manual Entry : Setting date...");

        mePage.setDateDayAfterTomorrow(0);

        System.out.println("Manual Entry : Press next and accept alerts");

        moqMeth.pressNext2();

        System.out.println("Manual Entry : Assert MOQ Output");
        AssertJUnit.assertTrue("MOQ is not the right one", moqMeth.ajustMOQ().getText().equals(expectedMOQ));

    }

    public void MOQ6B(Integer quantity, String expectedMOQ, String article, String shade) throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("Profile 6" ,"ECOMM_MOQ_06");

        Ecomm_MOQ_Methods_Page moqMeth = new Ecomm_MOQ_Methods_Page(driver);

        moqMeth.setProfile05();

        System.out.println("Going Manual Entry page...");

        moqMeth.goEccomManualEntry();

        moqMeth.setCustomerDetails();

        Ecomm_ManualEntryPage mePage = new Ecomm_ManualEntryPage(driver);

        //Setting Line Details
        System.out.println("Manual Entry : Entering line details...");

        System.out.println("Manual Entry : Setting article...");

        mePage.setArticle(article,0);

        System.out.println("Manual Entry : Setting shade...");

        mePage.setShadeCode(shade, 0);

        System.out.println("Manual Entry : Setting Qty ...");

        mePage.setQty(quantity, 0);

        System.out.println("Manual Entry : Setting date...");

        mePage.setDateDayAfterTomorrow(0);

        System.out.println("Manual Entry : Press next and accept alerts");

        moqMeth.pressNext1();

        System.out.println("Manual Entry : Assert MOQ Output");
        AssertJUnit.assertTrue("MOQ is not the right one", moqMeth.ajustMOQ().getText().equals(expectedMOQ));

    }

    @Test //MOQ6_1
            (groups = {"eComm"})
    public void MOQ6_1() throws Exception {
        MOQ6B(50,"71",DataItems.articleMOQ, DataItems.shadeCode01MOQ);
    }

    @Test //MOQ6_2
            (groups = {"eComm"})
    public void MOQ6_2() throws Exception {
        MOQ6B(30,"35",DataItems.articleMOQ, DataItems.shadeCode02MOQ);
    }

    @Test //MOQ6_3
            (groups = {"eComm"})
    public void MOQ6_3() throws Exception {
        MOQ6B(6,"11",DataItems.articleMOQ, DataItems.shadeCode02MOQ);
    }

    public void MOQ7(Integer quantity0, Integer quantity1, Integer quantity2, Integer quantity3, Integer quantity4, Integer quantity5,
                     String expectedMOQ, String article, String shade) throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("Profile 7" ,"ECOMM_MOQ_06");

        Ecomm_MOQ_Methods_Page moqMeth = new Ecomm_MOQ_Methods_Page(driver);

        moqMeth.setProfile02();

        System.out.println("Going Manual Entry page...");

        moqMeth.goEccomManualEntry();

        moqMeth.setCustomerDetails();

        Ecomm_ManualEntryPage mePage = new Ecomm_ManualEntryPage(driver);

        //Setting Line Details
        System.out.println("Manual Entry : Entering line details...");

        System.out.println("Manual Entry : Setting article...");

        mePage.setArticle(article,0);
        mePage.setArticle(article,1);
        mePage.setArticle(article,2);
        mePage.setArticle(article,3);
        mePage.setArticle(article,4);
        mePage.setArticle(article,5);

        System.out.println("Manual Entry : Setting shade...");

        mePage.setShadeCode(shade, 0);
        mePage.setShadeCode(shade, 1);
        mePage.setShadeCode(shade, 2);
        mePage.setShadeCode(shade, 3);
        mePage.setShadeCode(shade, 4);
        mePage.setShadeCode(shade, 5);

        System.out.println("Manual Entry : Setting Qty ...");

        mePage.setQty(quantity0, 0);
        mePage.setQty(quantity1, 1);
        mePage.setQty(quantity2, 2);
        mePage.setQty(quantity3, 3);
        mePage.setQty(quantity4, 4);
        mePage.setQty(quantity5, 5);

        System.out.println("Manual Entry : Setting date...");

        mePage.setDateToday(0);
        mePage.setDateToday(1);
        mePage.setDateToday(2);
        mePage.setDateToday(3);
        mePage.setDateToday(4);
        mePage.setDateToday(5);

        System.out.println("Manual Entry : Press next and accept alerts");

        moqMeth.pressNext1();

        System.out.println("Manual Entry : Assert MOQ Output");
        AssertJUnit.assertTrue("MOQ is not the right one", moqMeth.ajustMOQ().getText().equals(expectedMOQ));

    }

    @Test //MOQ7_1
            (groups = {"eComm"})
    public void MOQ7_3() throws Exception {
        MOQ7(6,10,12,14,18,20,"11",DataItems.articleMOQ, DataItems.shadeCode03MOQ);
    }
}
