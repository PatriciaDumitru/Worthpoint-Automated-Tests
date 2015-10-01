
package AutomationFramework;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.By;

import TestCases.Ecomm_SAPInterfaceLog;

@RunWith(Suite.class)
@Suite.SuiteClasses({

    //A LIST OF ALL TEST CLASSES IN THE PROJECT. COMMMENT-OUT TO DISABLE. 
    //USE @IGNORE ANNOTATION TO DISBALE INDIVIDUAL TESTS WITHIN EACH CLASS
    
    //Login.class,
    //ForgotPassword.class,
    //Ecomm_MainPage.class,
    //Cce_MainPage.class,
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
    Ecomm_SAPInterfaceLog.class,
	
})

public class TestSuite {
    
    //PRE-REQUISITE VARIABLES TO BE CONFIGURED
    
    //Target URL
    public static String targetURL = "http://qawcs.coatscolourexpress.com/";
    
    public static String chromeDriverFilepath = "C:\\Selenium\\chromedriver V2_18\\chromedriver.exe";
    public static String screenshotsFilepath = "C:\\Selenium\\Screenshots";
    public static String uploadOrderFilepath = "C:\\Selenium\\Upload_order_test_15.xlsx";
    public static String idFilepath = "C:\\Selenium\\ID.txt";
    
    //Some tests require files to be sent to the printer. Disabling will simply skip  the "click print" call during test
    public static boolean printingEnabled = false;
    
    //Login details used in Login test case
    public static String validCoatsUsername = "joe.sykes@coats.com";
    public static String validCoatsPassword = "password";
    public static String expectedUserName = "Joe Sykes";
    public static String invalidUsername = "jfdkjal@jfdalka.com";
    public static String invalidPassword = "invalidpassword";
    
    //Expected page titles (the text in the browser tabs)
    public static String loginPageTitle = "Coats";
    public static String ccePageTitle = "Coats Colour Express";
    public static String eCommPageTitle = "Coats eComm";
    
    //Expected breadcrumbs for CCE pages in order from left to right. Empty literals used for pages without a breadcrumb
    public static String[] cceExpectedTitles = {"FCE | Task List","FCE | Task List - Completed","FCE | Request",
            "Orders | Prompt","Orders | Outstanding Draft","Orders | Enrich Order","Orders | Order Status",
            "Orders | Delivery Notes","Orders | Feedback","Orders | Feedback Completed","Orders | Feedback Awaiting",
            "Orders | Hub SOS","Orders | Received Hub","Orders | Inbox","Orders | Inbox - SAP","Orders | Confirm Production",
            "Refill Cabinet","Reports | FCE Task Status","Reports | Order Cycle Time","Reports | Total Orders",
            "User Types","Coats Users","","Lrm Log","SAP Log",""
    };
    
    //Generic breadcrumb locator
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
    
    //Accept Code value to be used when accepting "Awaiting feedback"
    public static String acceptCode = "AutoTestAccept";
    
    //eComm Upload order - requester value to be used
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
    
    //SAP Interface log filter
    public static String sapMessage = "Success";

    @BeforeClass //Run before every Test Case class
    public static void setUp() throws MalformedURLException {
        //Declare location of driver
        System.setProperty("webdriver.chrome.driver", chromeDriverFilepath);
    }
    
    @After //Run after every individual test (every method in each Test Case class)
    public static void printBreak() {
        //print a break to separate tests
        System.out.println("-----------------------------------------------------------------");
    }
    
}
