
package AutomationFramework;

import org.openqa.selenium.By;



public class DataItems {
    
    //Target URL
    public static String targetURL = "http://qawcs.coatscolourexpress.com/";

    //Filepaths
    public static String chromeDriverFilepath = "C:\\Selenium\\chromedriver V2_19\\chromedriver.exe"; //DEPRECATED: Chrome driver exe filepath
    public static String screenshotsFilepath = "C:\\Selenium\\Screenshots"; //Filepath to which all screenshots (taken on instruction from within program, NOT by surefire) are saved
    public static String xmlFilepath = "C:\\Selenium\\createTest.xml"; //Used in CustomFactory feature, not used
    public static String idFilepath = "C:\\Selenium\\ID.txt"; //Filepath for ID file which is used in generating unique identifiers (PO's, filepaths)
    public static String lastUsedFilepath = ""; //Holds last used filepath when generating files using FileFactory
    
    //Login Credentials
    public static String validCoatsUsername = "joe.sykes@coats.com";
    public static String validCoatsPassword = "password";           //GLOBAL ADMIN, LIFE EASY CUSTOMER, SUMST, REQUESTER
    public static String expectedCoatsUserName = "Joe Sykes";
    
    public static String approverLEUsername = "approver@lifeeasy.com";
    public static String approverLEPassword = "password";           //CUSTOMER, LIFE EASY CUSTOMER, SUSST, APPROVER
    
    public static String validCustUsername = "joecontract@coats.com";
    public static String validCustPassword = "password";            //CUSTOMER, STAR GARMENTS LTD, SUSST, N/A
    public static String expectedCustUserName = "joe sykes";
    
    public static String validTestUsername = "testarun1@coats.com";
    public static String validTestPassword = "password";            //Misc test account with CREDIT BLOCK
    public static String expectedTestUserName = "TEsty arun";
    
    public static String susstUsername = "lifeeasy@customer.com";
    public static String susstPassword = "password";                //CUSTOMER, LIFE EASY CUSTOMER,SUSST
    public static String expectedSUSSTUserName = "Life Easy";
    
    public static String requesterUsername = "autolifeeasy@coats.com";
    public static String requesterPassword = "password";            //CUSTOMER, LIFE EASY CUSTOMER, SUSST, REQUESTER
    public static String expectedAutoUserName = "automated requester";
    public static String requesterShipTo = "Life Easy Customer";    //Ship To Party used alongside this account
    
    public static String invalidUsername = "jfdkjal@jfdalka.com";
    public static String invalidPassword = "invalidpassword";       //INVALID CREDENTIALS
    
    public static String approverUsername = "mail.kamleshpatidar@gmail.com";
    public static String approverPassword = "password";             //CUSTOMER, ANGLER TEST INDONESIA, SUSST, APPROVER
    
    public static String anglerRequesterUsername = "abc@abc.com";
    public static String anglerRequesterPassword = "password";      //CUSTOMER, ANGLER TEST INDONESIA, SUSST, REQUESTER
    
    public static String autoUsername = "automatedtest@coats.com";
    public static String autoPassword = "password";                 //CUSTOMER, ANGLER TEST INDONESIA, SUSST, REQUESTER
    public static String expectedAutoUserName2 = "Automated Test";
    public static String testUserType = "Test1"; //Used in testing All User Types page, when adding a user type
    public static String autoUserCountry = "Indonesia"; //For user above, user country/sales org/hub to be used in Coats Users Page test
    public static String autoUserSalesOrg = "ID51"; 
    public static String autoUserHub = "IDH001";
    
    //Login Page expected title (appears in browser tab)
    public static String loginPageTitle = "Coats"; //Title which appears in browser tab when login page is reached
    
    //CCE Expected titles (breadcrumb text) - empty literals where page has no breadcrumb text        
    public static String ccePageTitle = "Coats Colour Express"; //Expected CCE Main Page title
    public static String[] cceExpectedTitles = {"FCE | Task List", "FCE | Task List - Completed", 
        "FCE | Request", "Orders | Prompt", "Orders | Outstanding Draft", "Orders | Enrich Order", 
        "Orders | Order Status", "Orders | Delivery Notes", "Orders | Feedback", "Orders | Feedback Completed", 
        "Orders | Feedback Awaiting", "Orders | Hub SOS", "Orders | Received Hub", "Orders | Inbox", 
        "Orders | Inbox - SAP", "Orders | Confirm Production", "Refill Cabinet", "Reports | FCE Task Status", 
        "Reports | Order Cycle Time", "Reports | Total Orders", "User Types", "Coats Users", "", "Lrm Log", 
        "SAP Log", ""}; //Expected Page titles for every CCE page, held in the 'breadcrumb' element (top left of page, under logo).
    //For pages without breadcrumb text, use a blank literal so the program does not wait for a title
    
    public static String[][] cceFilterPages = {{"Task List","Task - Completed List"},
        {"Order Draft","Manual Enrich","Order Status","DN Reprint","Feedback Completed","Feedback Awaiting"},
        {"Hub SOS","Received Hub"},{"Inbox","Inbox SAP"},{"Confirm Production"},{},{},{"ALL User Types","Coats Users","LRM Log","SAP Log"},{}};
    //A list of all pages (by their navigation bar link text) which have a filter, used in checking pagination (NOT USED)
    
    //eComm Expected titles - the second array indicates which breadcrumb locator is used on each page, as the breadcrumb is not uniform across all pages
    public static String eCommPageTitle = "Coats eComm";
    public static String[][] eCommExpectedTitles = {
        {"Orders | Manual Entry", "", "Orders | From Existing Bulk Order", "Orders | Shade Not Available", "Orders | Waiting For Shade Code", 
            "Orders | Outstanding", "Orders | Drafts", "Orders | Upload Drafts", "Orders | Courier Tracking Update","Orders | Pending Approval List", "Orders | Denied Order List", "Orders | Drafts",
        "SAP Interface Log", 
        "Reports | Invoices", "Reports | Delivery Notes", "Reports | Summary of Purchase", "Reports | Outstanding Payments", "Reports | Customer Care", "Reports | Coats User","Terms and Conditions Report", "Reports | Order Approval History",
        "Real Upload Failed Files", "Backend In Process Files", "Backend Failed Files", "FTP Failed Files", "Orders | Failed Contract Orders",""}, 
        {"2", "", "2", "2", "2", 
            "2", "2", "2", "4", "2", "2", "2",
            "2", 
            "3", "3", "3", "3", "1", "1", "2", "2", 
            "2", "2", "2", "2", "2",
            ""}};
    //Expected page titles for eComm pages, held in the breadcrumb element (top left of page, under logo). Some pages use different breadcrumb locators
    //The breadcrumb locator to be used is held in the second array dimension
    
    //Breadcrumb locators - the breadcrumb changes position/element type throughout the site. These correspond to the numbers in eCommExpectedTitles[x][2]
    public static By breadcrumbLocator = By.cssSelector("#content > h2");
    public static By breadcrumbLocator2 = By.cssSelector("#list_page_breadcrumb > h1");
    public static By breadcrumbLocator3 = By.cssSelector("#list_page_breadcrumb > h2");
    public static By breadcrumbLocator4 = By.cssSelector("#content > div > h1");
    
    //This flashMessage element often holds a message/error from WBA when a page loads
    public static By flashMessage = By.id("flashMessage");
    
    //When a filter has no records to display, this element is visible. Use to check if records are displayed
    public static By noRecords = By.className("norec");
    
    //A list of all Masters by their link text
    public static String[][] masters = {
        {"BLANK","Countries","Sales Organisations","Plants","Plant Holidays","Hubs","Enterprise Structure","Brands","Tickets","Lengths","Finishes","Basic Materials","Material Groups","Hierarchy","Light Sources","Purpose Types","Rejection Reasons","Warehouse Instructions"},
        
        {"BLANK","Shade Cards","Shade Card - Plants","Shades","BLANK","Sales Org. Materials","Quantity Factors","Length Offers","Charged Products","Forced Enrichment Products","Supply Plants","Dye Lot Multiples","Order Type","Warehouse Stocks","Allowed Quantities","Hub Stocks"},
        
        {"BLANK","Customers","Ship To Parties","Sub Account","Business Principals","Customer Materials","Customer Brands","Customer Tickets","Customer Lengths","Customer Finishes","Customer Shades","Approver List","Multi Sold To Users","Customer Business Principal","Customer Ship To Party","Cabinets","Marketing New Features","Marketing Running Texts"}
    };
    
    //Customer details to be used in manual entry tests
    public static String[] custDetails = {"Life Easy Customer", "CCE HUB OFFICES", "approver 1 test", "*OTHERS*", "AutoTestPO_"};
    public static String custCode = "106499"; //Customer code for Life Easy Customer
    
    //Customer details to be used in subaccount tests
    public static String[] subCustDetails = {"Angler Test Indonesia","test","abc test","*OTHERS*"};
    public static String subAccount = "Andywisak"; //Sub Account name to be used with Angler Test Indonesia customer
    
    //Alternative customer details used with requesterUsername account
    public static String[] testCustDetails = {"Life Easy Customer","Life Easy Customer","Life Easy","*OTHERS*","AutoTestPO_"};
    
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
    public static String length3 = "5000";
    public static String finish3 = "STANDARD";
    public static String shadeCode3 = "C9455";
    public static int quantity3 = 1;
    
    //Details used in CCE Threshold qty tests (id=SOC_5)
    public static String thresholdBrand = "astra";
    public static String thresholdTicket="120";
    public static String thresholdMUMType="Cone";
    public static int thresholdQty=6;
    
    //Customer details to be used in Contract Order tests
    public static String[] conOrdDetails = {"Star Garments Ltd.", "Star Garments", "joe sykes", "*OTHERS*", "CustomerPO_"};
    
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
    
    //Line details for use with SUSST Account (joecontract@coats.com, Star Garments Ltd.)
    public static String yourMatNumSUSST = "AutoTestMaterial";
    public static String articleSUSST = "1505025";
    public static String brandSUSST = "dual duty";
    public static String ticketSUSST = "025";
    public static String lengthSUSST = "5000";
    public static String finishSUSST = "STANDARD";
    public static String shadeSUSST = "C1711";

    public static String yourMatNum2SUSST = "AutoTestMaterial2";
    public static String article2SUSST = "1505025";
    public static String brand2SUSST = "dual duty";
    public static String ticket2SUSST = "025";
    public static String length2SUSST = "5000";
    public static String finish2SUSST = "STANDARD";
    public static String shade2SUSST = "01212";
    
    //Line details for MOQ tests
    public static String testYourMatNum = "MOQTest";
    public static String MOQArticle = "8754180";
    public static String MOQBrand = "astra";
    public static String MOQTicket = "180";
    public static String MOQLength = "5000";
    public static String MOQFinish = "STANDARD";

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
    public static String countryName = "AutoTest Land";
    
    //Wait times in seconds
    public static int shorterWait = 8;
    public static int shortWait = 10;
    public static int longWait = 20;
    public static int downloadWait = 60;
    
}
