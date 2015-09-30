
package AutomationFramework;

import TestCases.Cce_ConfirmProduction;
import TestCases.Cce_DNReprint;
import TestCases.Cce_FCETaskStatus;
import TestCases.Cce_Feedback;
import TestCases.Cce_Hub;
import TestCases.Cce_Inbox;
import TestCases.Cce_LRMLog;
import TestCases.Cce_MainPage;
import TestCases.Cce_OrderCycleTime;
import TestCases.Cce_OrderStatus;
import TestCases.Cce_RefillCabinet;
import TestCases.Cce_SAPLog;
import TestCases.Cce_SOC;
import TestCases.Cce_TotalOrders;
import TestCases.Ecomm_MainPage;
import TestCases.ForgotPassword;
import TestCases.Login;
import TestCases.Ecomm_OOD;
import TestCases.Ecomm_SUSST_FEBO;
import TestCases.Ecomm_SUSST_ME;
import TestCases.Ecomm_SUSST_UORT;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.*;
import org.junit.runners.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@RunWith(Suite.class)
@Suite.SuiteClasses({

    //Login.class,
    //ForgotPassword.class,
    //Ecomm_MainPage.class,
    Cce_MainPage.class,
    //Cce_SOC.class,
    //Cce_OrderStatus.class,
    //Cce_Hub.class,
    //Cce_Inbox.class,
    //Cce_ConfirmProduction.class,
    //Cce_RefillCabinet.class,    
    //Cce_DNReprint.class,
    //Cce_Feedback.class,
    //Cce_FCETaskStatus.class,
    //Cce_OrderCycleTime.class,
    //Cce_TotalOrders.class,
    //Cce_LRMLog.class,
    //Cce_SAPLog.class,
    //Ecomm_SUSST_ME.class,
    //Ecomm_SUSST_UORT.class,
    //Ecomm_SUSST_FEBO.class,
    //Ecomm_OOD.class
    
})

public class TestSuite {
    
    //Target URL
    public static String targetURL = "http://qawcs.coatscolourexpress.com/";
    
    //Login details used in Login test case
    public static String validCoatsUsername = "joe.sykes@coats.com";
    public static String validCoatsPassword = "password";
    public static String expectedUserName = "Joe Sykes";
    public static String invalidUsername = "jfdkjal@jfdalka.com";
    public static String invalidPassword = "invalidpassword";
    
    //Expected page title of Login page
    public static String loginPageTitle = "Coats";
    //Expected page title of CCE page
    public static String ccePageTitle = "Coats Colour Express";
    //Expected page title of eComm page
    public static String eCommPageTitle = "Coats eComm";
    
    //CCE expected titles
    public static String[] cceExpectedTitles = {"FCE | Task List","FCE | Task List - Completed","FCE | Request",
            "Orders | Prompt","Orders | Outstanding Draft","Orders | Enrich Order","Orders | Order Status",
            "Orders | Delivery Notes","Orders | Feedback","Orders | Feedback Completed","Orders | Feedback Awaiting",
            "Orders | Hub SOS","Orders | Received Hub","Orders | Inbox","Orders | Inbox - SAP","Orders | Confirm Production",
            "Refill Cabinet","Reports | FCE Task Status","Reports | Order Cycle Time","Reports | Total Orders",
            "User Types","Coats Users","","Lrm Log","SAP Log",""
    };
    
    public static By breadcrumbLocator = By.cssSelector("#content > h2");
    
    //Customer details to be used in manual entry tests
    public static String[] custDetails = {"Life Easy Customer","CCE HUB OFFICES","approver 1 test","*OTHERS*","AutoTestPO_"};
    public static String custCode = "106499";
    public static String lastUsedPO = "";
    //Your Material Number for test
    public static String yourMatNum = "andy test 11";
    //Individual expected values for Your Material Number above
    public static String expArticle = "8754180";
    public static String expBrand = "astra";
    public static String expTicket = "180";
    public static String expShadeCode = "C1711";
    public static String expLength = "5000"; 
    public static String expFinish = "STANDARD";
    
    //A second Your Material Number for multi line tests
    public static String yourMatNum2 = "eComm Astra 180";   
    //Individual expected values for Your Material Number above
    public static String expArticle2 = "8754180";
    public static String expBrand2 = "astra";
    public static String expTicket2 = "180";
    public static String expShadeCode2 = "C9700";
    public static String expLength2 = "5000"; 
    public static String expFinish2 = "STANDARD";
    
    //Other items for test
    public static String article = "8754180";
    public static String brand = "epic";
    public static String ticket = "025";
    public static String length = "1000";
    public static String finish = "STANDARD";
    public static String shadeCode = "H0975";
    public static int quantity = 3;
    public static String salesOrganisation = "ID50";
    public static String hub = "IDH006";
    
    //Second set of items for test
    public static String brand2 = "gramax";
    public static String ticket2 = "080";
    public static String length2 = "2500";
    public static String finish2 = "STANDARD";
    public static String shadeCode2 = "C9455";
    public static int quantity2 = 6;
    
    //Third set (CCE Order Samples line 2) 
    public static String article3 = "8754090";
    public static String brand3 = "astra";
    public static String ticket3 = "090";
    public static String shadeCode3 = "C9455";
    public static int quantity3 = 1;
    
    //Awaiting feedback accept code
    public static String acceptCode = "AutoTestAccept";
    
    //Upload order items
    public static String uploadOrderPath = "C:\\Screenshots\\Upload_order_test_15.xlsx";
    public static String UORTrequestor = "approver 1 test";
    
    //Order numbers. sampRequester is the requester for sampOrderNo, sampCustName is the customer name for sampOrderNo
    public static String bulkOrderNo = "5020757";
    public static String sampOrderNo = "1129107";
    public static String sampCustName = "Life Easy Customer";
    public static String sampRequester = "Test two";
    
    //MUM types for CCE
    public static String copMUM = "Cop";
    public static String coneMUM = "Cone";
    public static String viconeMUM = "Vicone";
    
    //Request types for CCE 
    public static String colourMatch = "colMat";
    public static String sewing = "sewing";
    
    //Purpose types for CCE
    public static String bulkPurpose = "Bulk (Samples for Bulk Fabric)";
    public static String preprodPurpose = "Pre-production";
    public static String protoPurpose = "Prototype";
    public static String salesSamp = "Salesman Sample";
    public static String shadeDev = "Shade Development";
    
    //Destination folder for screenshots
    public static String screenshotFolder = "C:\\Screenshots";
    //File holding ID counter to produce PO numbers
    public static String idFilePath = "C:\\Screenshots\\ID.txt";

    //Details the browser to be used for testing
    public static String browserToUse = "chrome";
    //URL of grid node to use
    public static String nodeURL = "http://localhost:5555/wd/hub";
    public static DesiredCapabilities capability;
    
    public static boolean printingEnabled = false;
    
    @BeforeClass
    public static void setUp() throws MalformedURLException {
        //Declare location of driver
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
    }
    
    @After
    public static void printBreak() {
        //print a break to separate tests
        System.out.println("-----------------------------------------------------------------");
    }
    
}
