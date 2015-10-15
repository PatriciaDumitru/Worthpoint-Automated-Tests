
package AutomationFramework;

import org.openqa.selenium.By;



public class DataItems {
    //eComm Upload order - requester value to be used
    public static String UORTrequestor = "approver 1 test";
    //Other items for test
    public static String article = "8754180";
    //Third set (CCE Order Samples line 2)
    public static String article3 = "8754090";
    //PRE-REQUISITE VARIABLES TO BE CONFIGURED
    //Target URL
    public static String targetURL = "http://qawcs.coatscolourexpress.com/";
    //Accept Code value to be used when accepting "Awaiting feedback"
    public static String acceptCode = "AutoTestAccept";
    public static String brand3 = "astra";
    //Expected breadcrumbs for CCE pages in order from left to right. Empty literals used for pages without a breadcrumb
    public static String[] cceExpectedTitles = {"FCE | Task List", "FCE | Task List - Completed", "FCE | Request", "Orders | Prompt", "Orders | Outstanding Draft", "Orders | Enrich Order", "Orders | Order Status", "Orders | Delivery Notes", "Orders | Feedback", "Orders | Feedback Completed", "Orders | Feedback Awaiting", "Orders | Hub SOS", "Orders | Received Hub", "Orders | Inbox", "Orders | Inbox - SAP", "Orders | Confirm Production", "Refill Cabinet", "Reports | FCE Task Status", "Reports | Order Cycle Time", "Reports | Total Orders", "User Types", "Coats Users", "", "Lrm Log", "SAP Log", ""};
    public static By breadcrumbLocator2 = By.cssSelector("#list_page_breadcrumb > h1");
    //Purpose types for CCE
    public static String bulkPurpose = "Bulk (Samples for Bulk Fabric)";
    public static String chromeDriverFilepath = "C:\\Selenium\\chromedriver V2_19\\chromedriver.exe";
    public static String brand = "epic";
    //Generic breadcrumb locator
    public static By breadcrumbLocator = By.cssSelector("#content > h2");
    //Order numbers. sampRequester is the requester for sampOrderNo, sampCustName is the customer name for sampOrderNo
    public static String bulkOrderNo = "5020757";
    //Second set of items for test
    public static String brand2 = "gramax";
    public static By breadcrumbLocator3 = By.cssSelector("#list_page_breadcrumb > h2");
    //To prevent contract orders being called off, set boolean to false
    public static boolean contractOrderCallOff = false;
    public static String conOrdPO = "40000992";
    public static String conOrdLineRef = "10";
    public static String createTestFilepath = "C:\\Selenium\\CreateTest.xlsx";
    public static String eCommPageTitle = "Coats eComm";
    //MUM types for CCE
    public static String copMUM = "Cop";
    //Request types for CCE
    public static String colourMatch = "colMat";
    public static String co_uploadOrderFilepath2 = "C:\\Selenium\\CO_Upload_Order_Test_52.xlsx";
    public static String conOrdShadeCode = "WHITE";
    public static String coneMUM = "Cone";
    public static String[][] eCommExpectedTitles = {{"Orders | Manual Entry", "", "Orders | From Existing Bulk Order", "Orders | Shade Not Available", "Orders | Waiting For Shade Code", "Orders | Outstanding", "Orders | Drafts", "Orders | Upload Drafts", "Orders | Pending Approval List", "Orders | Denied Order List", "SAP Interface Log", "Reports | Invoices", "Reports | Delivery Notes", "Reports | Summary of Purchase", "Reports | Outstanding Payments", "Reports | Customer Care", "Terms and Conditions Report", "Real Upload Failed Files", "Backend In Process Files", "Backend Failed Files", "FTP Failed Files", ""}, {"2", "", "2", "2", "2", "2", "2", "2", "2", "2", "2", "3", "3", "3", "3", "1", "2", "2", "2", "2", "2", ""}};
    public static String ccePageTitle = "Coats Colour Express";
    public static String conOrdLength = "5000";
    //Contract order test items
    public static String[] conOrdDetails = {"Star Garments Ltd.", "Star Garments", "joe sykes", "*OTHERS*", "CustomerPO_ "};
    public static String custCode = "106499";
    public static String conOrdTicket = "120";
    public static String co_uploadOrderFilepath = "C:\\Selenium\\CO_Upload_Order_Test_51.xlsx";
    public static int conOrdQty = 1;
    public static String conOrdArticle = "8754120";
    public static String screenshotsFilepath = "C:\\Selenium\\Screenshots";
    public static String conOrdFinish = "STANDARD";
    //Individual expected values for Your Material Number above
    public static String expArticle = "8754180";
    //Customer details to be used in manual entry tests
    public static String[] custDetails = {"Life Easy Customer", "CCE HUB OFFICES", "approver 1 test", "*OTHERS*", "AutoTestPO_"};
    public static String conOrdBrand = "astra";
    public static String sampCustName = "Life Easy Customer";
    public static String ticket = "025";
    public static String shadeCode2 = "C9455";
    //Your Material Number for test
    public static String yourMatNum = "andy test 11";
    public static String salesSamp = "Salesman Sample";
    public static String expFinish2 = "STANDARD";
    public static String finish = "STANDARD";
    public static String invalidPassword = "invalidpassword";
    public static String lastUsedPO = "";
    public static String shadeCode3 = "C9455";
    public static String sewing = "sewing";
    public static String shadeDev = "Shade Development";
    //Individual expected values for Your Material Number above
    public static String expArticle2 = "8754180";
    public static String sampOrderNo = "1129108";
    //SAP Interface log filter
    public static String sapMessage = "Success";
    public static String ticket2 = "080";
    public static String idFilepath = "C:\\Selenium\\ID.txt";
    public static String length2 = "2500";
    //A second Your Material Number for multi line tests
    public static String yourMatNum2 = "eComm Astra 180";
    public static String expBrand = "astra";
    //Expected page titles (the text in the browser tabs)
    public static String loginPageTitle = "Coats";
    public static String finish2 = "STANDARD";
    public static String hub = "IDH006";
    //Some tests require files to be sent to the printer. Disabling will simply skip  the "click print" call during test
    public static boolean printingEnabled = false;
    public static String preprodPurpose = "Pre-production";
    public static int quantity2 = 6;
    public static String xmlFilepath = "C:\\Selenium\\createTest.xml";
    public static String ticket3 = "090";
    public static String expShadeCode2 = "C9700";
    public static String expFinish = "STANDARD";
    public static String expTicket2 = "180";
    public static String sampRequester = "approver 1 test";
    public static String protoPurpose = "Prototype";
    public static String shadeCode = "H0975";
    public static String validCustUsername = "joecontract@coats.com";
    public static String validCustPassword = "password";
    public static String expTicket = "180";
    public static int quantity3 = 1;
    //Login details used in Login test case
    public static String validCoatsUsername = "joe.sykes@coats.com";
    public static String validCoatsPassword = "password";
    public static String expectedUserName = "Joe Sykes";
    public static String expLength = "5000";
    public static String invalidUsername = "jfdkjal@jfdalka.com";
    public static String viconeMUM = "Vicone";
    public static String uploadOrderFilepath = "C:\\Selenium\\Upload_order_test_39.xlsx";
    public static String expBrand2 = "astra";
    public static String salesOrganisation = "ID50";
    public static int quantity = 3;
    public static String expShadeCode = "C1711";
    //Save report title
    public static String reportTitle = "AutoTestReport";
    public static String expLength2 = "5000";
    public static String length = "1000";
    
    
    
}
