
package AutomationFramework;

import AutomationFramework.Categories.CCE;
import AutomationFramework.Categories.Custom;
import AutomationFramework.Categories.Solo;
import AutomationFramework.Categories.Unstable;
import AutomationFramework.Categories.eComm;
import AutomationFramework.Categories.eComm_Orders;
import AutomationFramework.Categories.eComm_Orders_ContractOrder;
import AutomationFramework.Categories.eComm_Orders_UploadOrder;
import AutomationFramework.Categories.eComm_Orders_UploadOrder_Validation;
import PageObjects.Ecomm_MainPage;
import TestCases.Cce_ConfirmProductiontest;
import TestCases.Cce_DNReprinttest;
import TestCases.Cce_FCETaskStatustest;
import TestCases.Cce_Feedbacktest;
import TestCases.Cce_Hubtest;
import TestCases.Cce_Inboxtest;
import TestCases.Cce_LRMLogtest;
import TestCases.Cce_MainPagetest;
import TestCases.Cce_OrderCycleTimetest;
import TestCases.Cce_OrderStatustest;
import TestCases.Cce_RefillCabinettest;
import TestCases.Cce_SAPLogtest;
import TestCases.Cce_SOCtest;
import TestCases.Cce_TotalOrders;
import TestCases.Ecomm_Dashboard;
import TestCases.Ecomm_OOD;
import TestCases.Ecomm_ProductAvailabilityCheck;
import TestCases.Ecomm_Reports;
import TestCases.Ecomm_SAPInterfaceLog;
import TestCases.Ecomm_SUSST_FEBO;
import TestCases.Ecomm_SUSST_ME;
import TestCases.Ecomm_SUSST_UORT;
import TestCases.Ecomm_Shade;
import TestCases.Generated.Ecomm_CO_ME;
import TestCases.Generated.Ecomm_CO_UORT;
import TestCases.WBA_Custom;
import TestCases.WBA_ForgotPassword;
import TestCases.WBA_Login;
import java.net.MalformedURLException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.By;

@RunWith(Categories.class)
@IncludeCategory(Solo.class)
@Suite.SuiteClasses({
    
    //A LIST OF ALL TEST CLASSES IN THE PROJECT. COMMMENT-OUT TO DISABLE. 
    //USE @IGNORE ANNOTATION TO DISBALE INDIVIDUAL TESTS WITHIN EACH CLASS
    
    WBA_Login.class,
    WBA_ForgotPassword.class,
    WBA_Custom.class,
    Ecomm_MainPage.class,
    Cce_MainPagetest.class,
    Cce_SOCtest.class,
    Cce_OrderStatustest.class,
    Cce_Hubtest.class,
    Cce_Inboxtest.class,
    Cce_ConfirmProductiontest.class,
    Cce_RefillCabinettest.class,    
    Cce_DNReprinttest.class,
    Cce_Feedbacktest.class,
    Cce_FCETaskStatustest.class,
    Cce_OrderCycleTimetest.class,
    Cce_TotalOrders.class,
    Cce_LRMLogtest.class,
    Cce_SAPLogtest.class,
    Ecomm_SUSST_ME.class,
    Ecomm_SUSST_UORT.class,
    Ecomm_SUSST_FEBO.class,
    Ecomm_Shade.class,
    Ecomm_OOD.class,
    Ecomm_SAPInterfaceLog.class,
    Ecomm_Reports.class,
    Ecomm_Dashboard.class, 
    Ecomm_CO_ME.class,
    Ecomm_CO_UORT.class,
    Ecomm_ProductAvailabilityCheck.class
    
})

public class TestSuite {
    
    @BeforeClass //Run before every Test Case class
    public static void setUp() throws MalformedURLException {
        //Declare location of driver
        System.setProperty("webdriver.chrome.driver", DataItems.chromeDriverFilepath);
    }
    
    @After //Run after every individual test (every method in each Test Case class)
    public static void printBreak() {
        //print a break to separate tests
        System.out.println("-----------------------------------------------------------------");
    }
    
}

