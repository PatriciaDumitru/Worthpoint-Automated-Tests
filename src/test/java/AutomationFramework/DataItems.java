
package AutomationFramework;

import org.openqa.selenium.By;



public class DataItems {
    
    //Target URL
    public static String targetURL = "http://qawcs.coatscolourexpress.com/";

    //Filepaths
    public static String chromeDriverFilepath = "C:\\Selenium\\chromedriver V2_19\\chromedriver.exe";
    public static String screenshotsFilepath = "C:\\Selenium\\Screenshots";
    public static String xmlFilepath = "C:\\Selenium\\createTest.xml";
    public static String idFilepath = "C:\\Selenium\\ID.txt";
    public static String co_uploadOrderFilepath = "C:\\Selenium\\CO_Upload_Order_Test_75.xlsx";
    public static String co_uploadOrderFilepath2 = "C:\\Selenium\\CO_Upload_Order_Test_76.xlsx";
    public static String uploadOrderFilepath = "C:\\Selenium\\Upload_order_test_73.xlsx";
    public static String uploadOrderFilepath2 = "C:\\Selenium\\Upload_order_test_73C.xlsx";
    public static String uploadDraftFilepath = "C:\\Selenium\\Upload_Draft_test_35.xlsx";
    public static String uploadDraftFilepath2 = "C:\\Selenium\\Upload_Draft_test_36.xlsx";
    public static String uploadDraftFilepath3 = "C:\\Selenium\\Upload_Draft_test_37.xlsx";
    public static String uploadYMNExceptionFilepath = "C:\\Selenium\\Upload_Exception_YMN_6.xlsx";
    public static String uploadBrandExceptionFilepath = "C:\\Selenium\\Upload_Exception_Brnd_5.xlsx";
    public static String uploadArticleExceptionFilepath = "C:\\Selenium\\Upload_Exception_Artc_3.xlsx";
    public static String uploadTicketExceptionFilepath = "C:\\Selenium\\Upload_Exception_Tkt_2.xlsx";
    public static String uploadLengthExceptionFilepath = "C:\\Selenium\\Upload_Exception_Lgth_2.xlsx";
    public static String uploadFinishExceptionFilepath = "C:\\Selenium\\Upload_Exception_Fnsh_2.xlsx";
    public static String uploadShadeExceptionFilepath = "C:\\Selenium\\Upload_Exception_Shde_2.xlsx";
    public static String uploadShipToExceptionFilepath = "C:\\Selenium\\Upload_Exception_ShipTo_10.xlsx";
    public static String uploadSubAcctFilepath = "C:\\Selenium\\Upload_order_SubAcct_8.xlsx";
    
    //Login details
    public static String validCoatsUsername = "joe.sykes@coats.com";
    public static String validCoatsPassword = "password";
    public static String expectedCoatsUserName = "Joe Sykes";
    
    public static String validCustUsername = "joecontract@coats.com";
    public static String validCustPassword = "password";
    public static String expectedCustUserName = "joe sykes";
    
    public static String invalidUsername = "jfdkjal@jfdalka.com";
    public static String invalidPassword = "invalidpassword";
    
    public static String approverUsername = "mail.kamleshpatidar@gmail.com";
    public static String approverPassword = "password";
    
    //Login Page expected title (appears in browser tab)
    public static String loginPageTitle = "Coats";
    
    //CCE Expected titles (breadcrumb text) - empty literals where page has no breadcrumb text        
    public static String ccePageTitle = "Coats Colour Express";
    public static String[] cceExpectedTitles = {"FCE | Task List", "FCE | Task List - Completed", 
        "FCE | Request", "Orders | Prompt", "Orders | Outstanding Draft", "Orders | Enrich Order", 
        "Orders | Order Status", "Orders | Delivery Notes", "Orders | Feedback", "Orders | Feedback Completed", 
        "Orders | Feedback Awaiting", "Orders | Hub SOS", "Orders | Received Hub", "Orders | Inbox", 
        "Orders | Inbox - SAP", "Orders | Confirm Production", "Refill Cabinet", "Reports | FCE Task Status", 
        "Reports | Order Cycle Time", "Reports | Total Orders", "User Types", "Coats Users", "", "Lrm Log", 
        "SAP Log", ""};
    
    public static String[][] cceFilterPages = {{"Task List","Task - Completed List"},
        {"Order Draft","Manual Enrich","Order Status","DN Reprint","Feedback Completed","Feedback Awaiting"},
        {"Hub SOS","Received Hub"},{"Inbox","Inbox SAP"},{"Confirm Production"},{},{},{"ALL User Types","Coats Users","LRM Log","SAP Log"},{}};
    
    //eComm Expected titles - the second array indicates which breadcrumb locator is used on each page, as the breadcrumb is not uniform across all pages
    public static String eCommPageTitle = "Coats eComm";
    public static String[][] eCommExpectedTitles = {
        {"Orders | Manual Entry", "", "Orders | From Existing Bulk Order", "Orders | Shade Not Available", "Orders | Waiting For Shade Code", 
            "Orders | Outstanding", "Orders | Drafts", "Orders | Upload Drafts", "Orders | Courier Tracking Update","Orders | Pending Approval List", "Orders | Denied Order List", "Missing Method in BulkOrdersController",
        "SAP Interface Log", 
        "Reports | Invoices", "Reports | Delivery Notes", "Reports | Summary of Purchase", "Reports | Outstanding Payments", "Reports | Customer Care", "Reports | Coats User","Terms and Conditions Report", "Reports | Order Approval History",
        "Real Upload Failed Files", "Backend In Process Files", "Backend Failed Files", "FTP Failed Files", "Orders | Failed Contract Orders",""}, 
        {"2", "", "2", "2", "2", 
            "2", "2", "2", "4", "2", "2", "1",
            "2", 
            "3", "3", "3", "3", "1", "1", "2", "2", 
            "2", "2", "2", "2", "2",
            ""}};
    
    //Breadcrumb locators - the breadcrumb changes position/element type throughout the site
    public static By breadcrumbLocator = By.cssSelector("#content > h2");
    public static By breadcrumbLocator2 = By.cssSelector("#list_page_breadcrumb > h1");
    public static By breadcrumbLocator3 = By.cssSelector("#list_page_breadcrumb > h2");
    public static By breadcrumbLocator4 = By.cssSelector("#content > div > h1");
    
    //Masters tabs
    public static String[][] masters = {
        {"BLANK","Countries","Sales Organisations","Plants","Plant Holidays","Hubs","Enterprise Structure","Brands","Tickets","Lengths","Finishes","Basic Materials","Material Groups","Hierarchy","Light Sources","Purpose Types","Rejection Reasons","Warehouse Instructions"},
        
        {"BLANK","Shade Cards","Shade Card - Plants","Shades","BLANK","Sales Org. Materials","Quantity Factors","Length Offers","Charged Products","Forced Enrichment Products","Supply Plants","Dye Lot Multiples","Order Type","Warehouse Stocks","Allowed Quantities","Hub Stocks"},
        
        {"BLANK","Customers","Ship To Parties","Sub Account","Business Principals","Customer Materials","Customer Brands","Customer Tickets","Customer Lengths","Customer Finishes","Customer Shades","Approver List","Multi Sold To Users","Customer Business Principal","Customer Ship To Party","Cabinets","Marketing New Features","Marketing Running Texts"}
    };
    
    //Customer details to be used in manual entry tests
    public static String[] custDetails = {"Life Easy Customer", "CCE HUB OFFICES", "approver 1 test", "*OTHERS*", "AutoTestPO_"};
    public static String custCode = "106499";
    
    //Customer details to be used in subaccount tests
    public static String[] subCustDetails = {"Angler Test Indonesia","test","abc test","*OTHERS*"};
    public static String subAccount = "Andywisak";
    
    //Requester used during Upload Order Tests
    public static String UORTrequestor = "approver 1 test";
    
    //Line details for manual entry tests
    public static String article = "8754180";
    public static String brand = "epic";
    public static String ticket = "025";
    public static String length = "2000";
    public static String finish = "STANDARD";
    public static String shadeCode = "C1202";
    public static int quantity = 3;
    
    public static String yourMatNum = "andy test 11";
    public static String expArticle = "8754180";
    public static String expBrand = "astra";
    public static String expTicket = "180";
    public static String expLength = "5000";
    public static String expFinish = "STANDARD";
    public static String expShadeCode = "C1711";
    
    public static String yourMatNum2 = "eComm Astra 180";
    public static String expArticle2 = "8754180";  
    public static String expBrand2 = "astra";
    public static String expTicket2 = "180";
    public static String expLength2 = "5000";
    public static String expFinish2 = "STANDARD";
    public static String expShadeCode2 = "C9700";
    
    public static String brand2 = "gramax";
    public static String ticket2 = "080";   
    public static String length2 = "5000";
    public static String finish2 = "STANDARD";
    public static String shadeCode2 = "C9455";
    public static int quantity2 = 6;
    
    public static String article3 = "8754090";
    public static String brand3 = "astra";
    public static String ticket3 = "090";
    public static String shadeCode3 = "C9455";
    public static int quantity3 = 1;
    
    //Details used in CCE Threshold qty tests (id=SOC_5)
    public static String thresholdBrand = "astra";
    public static String thresholdTicket="120";
    public static String thresholdMUMType="Cone";
    public static int thresholdQty=6;
    
    //Customer details to be used in Contract Order tests
    public static String[] conOrdDetails = {"Star Garments Ltd.", "Star Garments", "joe sykes", "*OTHERS*", "CustomerPO_ "};
    
    //Line details for Contract Order tests    
    public static String conOrdArticle = "8754120";
    public static String conOrdBrand = "astra";
    public static String conOrdTicket = "120";
    public static String conOrdLength = "5000";
    public static String conOrdFinish = "STANDARD";
    public static String conOrdShadeCode = "WHITE";
    public static int conOrdQty = 1;
    public static String conOrdPO = "40000992";
    public static String conOrdLineRef = "10";
    
    //Switch on/off Contract Order Call-off
    public static boolean contractOrderCallOff = false;
    
    //Refill cabinet code
    public static String cabinetName = "AutoTestCabinet";
    
    //MUM Types
    public static String coneMUM = "Cone";
    public static String viconeMUM = "Vicone";
    public static String copMUM = "Cop";
    
    //Request types
    public static String sewing = "sewing";
    public static String shadeDev = "Shade Development";
    public static String colourMatch = "colMat";
    public static String salesSamp = "Salesman Sample";
    
    //Purpose types
    public static String bulkPurpose = "Bulk (Samples for Bulk Fabric)";
    public static String preprodPurpose = "Pre-production";
    public static String protoPurpose = "Prototype";
    
    //Cancellation reasons
    public static String wrongEntryCust = "Wrong Entry by Customer";
    
    //Sample order details
    public static String sampOrderNo = "1129113";
    public static String sampCustName = "Life Easy Customer";
    public static String sampRequester = "approver 1 test";
    public static String sampSalesOrg = "ID52";
    
    //Sample bulk order number
    public static String bulkOrderNo = "5020757";
    
    //eComm My Reports report title
    public static String reportTitle = "AutoTestReport";
    
    //Accept Code value to be used when accepting "Awaiting feedback"
    public static String acceptCode = "AutoTestAccept";
    
    public static String salesOrganisation = "ID51";
    public static String hub = "IDH006";
    
    //Switch on/off printing documents
    public static boolean printingEnabled = false;
    
    //SAP Interface log filter
    public static String sapMessage = "Success";
    
    public static String lastUsedPO = "";
    
    //Data used in masters
    public static String autoUserType = "AutoTestUser";
    
    //Wait times in seconds
    public static int shorterWait = 8;
    public static int shortWait = 10;
    public static int longWait = 20;
    public static int downloadWait = 60;
    
}
