package com.coats.selenium.tests.RCTests;

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
import org.openqa.selenium.*;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


public class Ecomm_MOQ_Test extends DriverFactory{

    @Test //Manual Entry Page :: SUSST :: Page checks and single line order using YMN and master shade
            (groups = {"eComm","eComm_Orders"})
    public void MOQ1() throws Exception {
        //New chrome driver
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        base.setUp("MOQ Disabled, PartialStock Enabled, Forward Order days: 10, Stock available: none," +
                " Requester Delivery Date: Today, Credit block: No","ECOMM_MOQ_01");


        Ecomm_MOQ_Methods_Page moqMeth = new Ecomm_MOQ_Methods_Page(driver);

        System.out.println("Sales organization: Going to sales organization edit page...");

        moqMeth.goToSalesOrgAndEdit();

        System.out.println("Sales organization: Enabling Partial...");

        moqMeth.enablePartialStockCheckBox();

        System.out.println("Sales organization: Forwading Day and setting it to 10...");

        moqMeth.enableFordwardOrderDayAndSetForwardDate();

        System.out.println("Sales organization: Saving changes...");

        moqMeth.saveSalesOrg();

        System.out.println("Customer: Going to customer edit page...");

        moqMeth.goToCustomerAndEdit();

        System.out.println("Customer: Disabling MOQ Checkbox...");

        moqMeth.disableMOQCheckBox();

        System.out.println("Customer: Saving customers changes...");

        moqMeth.saveCustomer();





    }

}
