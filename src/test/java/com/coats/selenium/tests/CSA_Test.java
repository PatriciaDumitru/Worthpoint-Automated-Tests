package com.coats.selenium.tests;

import AutomationFramework.DataItems;
import PageObjects.CCE_MainPage;
import PageObjects.CCE_DNPrintPage;
import PageObjects.CCE_DNReprintPage;
import PageObjects.CPA_Methods;
import com.coats.selenium.DriverFactory;
import com.google.common.base.Verify;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class CSA_Test extends DriverFactory {

    @Test //DN Reprint Page :: Page checks
            (groups = {"CCE"})
    public void CSA1() throws Exception {
        //New driver object to control browser
        WebDriver driver = getDriver();

        //New base object to handle log-in and set up
        Cce_Base base = new Cce_Base(driver);

        //Set up returns a CCE Page and outputs test details
        CCE_MainPage ccePage = base.setUp("DN Reprint DR1: User can select all fields", "G_CCE_DR_1");

        CPA_Methods cpaMeth = new CPA_Methods(driver);

        cpaMeth.custSetup("Select");
        cpaMeth.csaSetup("ID51", "Life Easy Customer", "astra", "8754180");
        cpaMeth.cceOrderSampleSetup("Life Easy Customer", "Life Easy");

    }

}