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

    @Test //MOQ1
            (groups = {"eComm"})
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

        moqMeth.pressNext();

        System.out.println("Manual Entry : Assert MOQ Output");
        AssertJUnit.assertTrue("MOQ is not the right one", moqMeth.ajustMOQ().getText().equals(expectedMOQ));

    }

    @Test //MOQ1
            (groups = {"eComm"})
    public void MOQ1_1() throws Exception {
        MOQ1(15,"15",DataItems.articleMOQ, DataItems.shadeCode01MOQ);
    }




    @Test //Manual Entry Page :: SUSST :: Page checks and single line order using YMN and master shade
            (groups = {"eComm"})
    public void MOQ2() throws Exception {
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
    }

    @Test //Manual Entry Page :: SUSST :: Page checks and single line order using YMN and master shade
            (groups = {"eComm"})
    public void MOQ3() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("Profile 3" ,"ECOMM_MOQ_03");

        Ecomm_MOQ_Methods_Page moqMeth = new Ecomm_MOQ_Methods_Page(driver);

        moqMeth.setProfile03();

        moqMeth.goEccomManualEntry();
    }

    @Test //Manual Entry Page :: SUSST :: Page checks and single line order using YMN and master shade
            (groups = {"eComm"})
    public void MOQ4() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("Profile 4" ,"ECOMM_MOQ_04");

        Ecomm_MOQ_Methods_Page moqMeth = new Ecomm_MOQ_Methods_Page(driver);

        moqMeth.setProfile04();

        moqMeth.goEccomManualEntry();
    }

}
