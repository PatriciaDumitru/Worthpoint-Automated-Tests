package PageObjects;

import AutomationFramework.DataItems;
import static PageObjects.CCE_MainPage.ordersHeader;
import static PageObjects.Ecomm_MainPage.ordersTab;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//This class is a superclass from which other WBA page classes inherit from. 
//It manages elements common to most pages (coats logo, sliding text bar, logout link, footer)

public class WBA_BasePage {
    
    //Declare WebDriver
    protected static WebDriver driver;
    
    //Page element locators
    static By todayLabelLocator = By.cssSelector("#header > div.top > span.left > span.today");
    static By dateLabelLocator = By.cssSelector("#header > div.top > span.left > span.date");
    static By accessTypeLocator = By.id("access_type");
    static By languageMenuLocator = By.id("lang-menu");
    static By welcomeLabelLocator = By.cssSelector("#header > div.top > span.right > span.welcome");
    static By userNameLocator = By.cssSelector("#header > div.top > span.right > span.username > a");
    static By logoutLabelLocator = By.cssSelector("#header > div.top > span.right > span.logout > a");
    static By slidingTextLocator = By.cssSelector("#slides > div > div > span:nth-child(1)");
    static By coatsLogoLocator = By.cssSelector("#header > a > img");
    public static By breadcrumbLocator = By.cssSelector("#content > h2");
    public static By breadcrumbLocator2 = By.cssSelector("#list_page_breadcrumb > h1");
    public static By breadcrumbLocator3 = By.cssSelector("#list_page_breadcrumb > h2");
    static By footerLocator = By.id("footer");
    static By contentFrame = By.id("content");
    public static By noRecords = By.className("norec");
    
    //eComm Navigation bar tabs
    static By ordersTab = By.cssSelector("#topnav > li:nth-child(1)");
        static By manualEntrySubTab = By.cssSelector("#topnav > li:nth-child(1) > div > div > ul > li:nth-child(1)");
        static By uploadOrderSubTab = By.cssSelector("#topnav > li:nth-child(1) > div > div > ul > li:nth-child(2)");
        static By fromExistingSubTab = By.cssSelector("#topnav > li:nth-child(1) > div > div > ul > li:nth-child(3)");
        static By shadeNotAvailSubTab = By.cssSelector("#topnav > li:nth-child(1) > div > div > ul > li:nth-child(4)");
        static By waitingForShadeSubTab = By.cssSelector("#topnav > li:nth-child(1) > div > div > ul > li:nth-child(5)");
        
    static By outstandingOrdersTab = By.cssSelector("#topnav > li:nth-child(2)");
        static By outOrdersSubTab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(1)");
        static By outstOrderDraftSubTab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(2)");
        static By outstUploadDraftSubTab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(3)");
        static By courierTrackingUpdate = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(4)");
        static By pendingApprovalSubTab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(5)");
        static By pendingApprovalSubTabApprover = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(3)");
        static By deniedOrderSubTab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(6)");
        static By uploadDraftErrorSubTab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(7)");
        
    static By sapInterfaceLogTab = By.cssSelector("#topnav > li:nth-child(3)");
    
    static By reportsTab = By.cssSelector("#topnav > li:nth-child(4)");
        static By invoicesSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(1)");
        static By deliveryNotesSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(2)");
        static By summaryOfSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(3)");
        static By outPaySubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(4)");
        static By myReportsSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(5)");
        static By coatsUserRepSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(6)");
        static By termsConditionsSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(7)");
        static By orderAppHistSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(8)");
        
    static By dashboardTab = By.cssSelector("#topnav > li:nth-child(5)");
        static By realUploadFailedSubTab = By.cssSelector("#topnav > li:nth-child(5) > div > div > ul > li:nth-child(1)");
        static By backendIPSubTab = By.cssSelector("#topnav > li:nth-child(5) > div > div > ul > li:nth-child(2)");
        static By backendFailedSubTab = By.cssSelector("#topnav > li:nth-child(5) > div > div > ul > li:nth-child(3)");
        static By ftpFailedSubTab = By.cssSelector("#topnav > li:nth-child(5) > div > div > ul > li:nth-child(4)");
        static By failedConOrdSubTab = By.cssSelector("#topnav > li:nth-child(5) > div > div > ul > li:nth-child(5)");

    //eComm Locators for customer user
    static By pacTab = By.cssSelector("#topnav > li:nth-child(3)");
    static By failedContractOrderSubtab = By.cssSelector("#topnav > li:nth-child(5) > div > div > ul > li:nth-child(5)");
    
    //CCE Navbar locators
    static By ordersHeader = By.cssSelector("#topnav > li:nth-child(2)");
        static By orderSamplesSubtab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(1)");
        static By outstandingDraftSubtab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(2)");
        static By manualEnrichSubtab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(3)");
        static By orderStatusSubtab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(4)");
        static By dnReprintSubtab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(5)");
        static By feedbackSubtab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(6)");
        static By feedbackCompletedSubtab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(7)");
        static By feedbackAwaitingSubtab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(8)");
        
    static By hubHeader = By.cssSelector("#topnav > li:nth-child(3)");
        static By hubSosSubtab = By.cssSelector("#topnav > li:nth-child(3) > div > div > ul > li:nth-child(1)");
        static By receivedHubSubtab = By.cssSelector("#topnav > li:nth-child(3) > div > div > ul > li:nth-child(2)");
        
    static By inboxHeader = By.cssSelector("#topnav > li:nth-child(4)");
        static By inboxSubtab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(1)");
        static By inboxSAPSubtab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(2)");
        
    static By conProdHeader = By.cssSelector("#topnav > li:nth-child(5)");
    
    static By refCabHeader = By.cssSelector("#topnav > li:nth-child(6)");
    
    static By reportsHeader = By.cssSelector("#topnav > li:nth-child(7)");
        static By fceTaskStatusSubtab = By.cssSelector("#topnav > li:nth-child(7) > div > div > ul > li:nth-child(1)");
        static By orderCycleTimeSubtab = By.cssSelector("#topnav > li:nth-child(7) > div > div > ul > li:nth-child(2)");
        static By totalOrdersSubtab = By.cssSelector("#topnav > li:nth-child(7) > div > div > ul > li:nth-child(3)");
        
    static By adminHeader = By.cssSelector("#topnav > li:nth-child(8)");
        static By allUserTypesSubtab = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(1)");
        static By coatsUsersSubtab = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(2)");
        static By mastersSubtab = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3)");
            static By salesOrgOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(3)");
            static By shadesOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(2) > ul > li:nth-child(4)");
            static By customerShadesOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(3) > ul > li:nth-child(11)");
            static By countriesOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(2)");
            static By multiSoldToOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(3) > ul > li:nth-child(13)");
            static By subAccountOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(3) > ul > li:nth-child(4)");
            static By salesOrgMatOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(2) > ul > li:nth-child(6)");
            static By customersOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(3) > ul > li:nth-child(2)");
            static By approverListOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(3) > ul > li:nth-child(12)");
            static By custBusPrincOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(3) > ul > li:nth-child(14)");
            static By custFinishesOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(3) > ul > li:nth-child(10)");
            static By custLengthsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(3) > ul > li:nth-child(9)");
            static By custTicketsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(3) > ul > li:nth-child(8)");
            static By custBrandsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(3) > ul > li:nth-child(7)");
            static By custMaterialsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(3) > ul > li:nth-child(6)");
            static By businessPrincipalsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(3) > ul > li:nth-child(5)");
            static By plantsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(4) > a");
            static By hubsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(6)");
            static By plantHolidaysOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(5) > a");
            static By enterpriseStructureOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(7)");
            static By brandsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(8)");
            static By ticketsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(9)");
            static By lengthsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(10)");
            static By finishesOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(11)");
            static By basicMatOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(12)");
            static By matGroupsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(13)");
            static By hierarchyOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(14)");
            static By lightSourcesOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(15)");
            static By purposeTypesOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(16)");
            static By rejectionReasonsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(17)");
            static By quantityFactorsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(2) > ul > li:nth-child(7)");
            static By lengthOffersOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(2) > ul > li:nth-child(8)");
            static By warehouseInstOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li:nth-child(18)");
            static By shadeCardsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(2) > ul > li:nth-child(2)");
            static By shadeCardPlantsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(2) > ul > li:nth-child(3)");
            static By chargedProductsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(2) > ul > li:nth-child(9)");
            static By forcedEnrichmentOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(2) > ul > li:nth-child(10)");
            static By supplyPlantsOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(2) > ul > li:nth-child(11)");
            static By dyeLotMultiplesOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(2) > ul > li:nth-child(12)");
            static By orderTypeOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(2) > ul > li:nth-child(13)");
        static By lrmLogSubtab = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(4)");
        static By sapLogSubtab = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(5)");
        static By archivesSubtab = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(6)");
    
    //Navigation headers specifically for requester/approver/alternative user types
        static By deniedOrderSubtabRequester = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(5)");
        static By deniedOrderSubtabApprover = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(4)");
    
    
    public WBA_BasePage(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public static WebElement getTodayLabel() {
        //find and return element
        return driver.findElement(todayLabelLocator);
    }
    
    public static WebElement getDateLabel() {
        //find and return element
        return driver.findElement(dateLabelLocator);
    }
    
    public static WebElement getAccessTypeSelector() {
        //find and return element
        return driver.findElement(accessTypeLocator);
    }
    
    public static WebElement getLanguageMenu() {
        //find and return element
        return driver.findElement(languageMenuLocator);
    }
    
    public static WebElement getWelcomeLabel() {
        //find and return element
        return driver.findElement(welcomeLabelLocator);
    }
    
    public static WebElement getUserName() {
        //find and return element
        return driver.findElement(userNameLocator);
    }
    
    public static WebElement getLogoutLabel() {
        //find and return element
        return driver.findElement(logoutLabelLocator);
    }
    
    public static WebElement getTextSlider() {
        //find and return element
        return driver.findElement(slidingTextLocator);
    }
    
    public static WebElement getCoatsLogo() {
        //find and return element
        return driver.findElement(coatsLogoLocator);
    }
    
    public static WebElement getFooter() {
        //find and return element
        return driver.findElement(footerLocator);
    }
    
    public static String getBreadcrumbText() {
        //find and return element and text
        return driver.findElement(breadcrumbLocator).getText();
    }
    
    public static String getBreadcrumbText2() {
        return driver.findElement(breadcrumbLocator2).getText();
    }
    
    public static String getBreadcrumbText3() {
        return driver.findElement(breadcrumbLocator3).getText();
    }
    
    public static int getRecordCount(By locator) {
        //Allows the number of records in a filtered table to be returned
        WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
        String text = cell.getText();
        String[] parts = text.split("of ");
        String[] textParts = parts[1].split("\\|");
        String textCount = textParts[0];
        
        return Integer.parseInt(String.valueOf(textCount));
    }
    
    public void assertBaseElements() {
        System.out.println("Asserting base elements...");

        //Wait for elements to be visible
        WebElement waitForElements = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(getFooter()));

        //Assert that all elements belonging to the base page (listed in the locators) are displayed correctly
        AssertJUnit.assertTrue("Base page: 'Today' label not displayed",getTodayLabel().isDisplayed());
        AssertJUnit.assertTrue("Base page: Date label not displayed",getDateLabel().isDisplayed());       
        DateFormat df = new SimpleDateFormat("EEEE, dd MMM yyyy"); //Assert today's date in matching format to label
        String date = df.format(new Date());
        AssertJUnit.assertTrue("Base page: Date in header does not match current date",date.equals(getDateLabel().getText()));
        AssertJUnit.assertTrue("Base page: CCE/eComm access selector not displayed",getAccessTypeSelector().isDisplayed());
        AssertJUnit.assertTrue("Base page: language menu not displayed",getLanguageMenu().isDisplayed());
        AssertJUnit.assertTrue("Base page: 'Welcome' label not displayed",getWelcomeLabel().getText().equals("Welcome"));
        AssertJUnit.assertTrue("Base page: 'Logout' label not displayed",getLogoutLabel().isDisplayed());
        AssertJUnit.assertTrue("Base page: Coats logo not displayed",getCoatsLogo().isDisplayed());
        AssertJUnit.assertTrue("Base page: Footer not displayed",getFooter().isDisplayed());
    
        System.out.println("Assertions successful.");
    }
    
    public void waitForLoad() {
        WebElement waitForContent = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.presenceOfElementLocated(contentFrame));
    }
    
    //Switch site (from CCE to Ecomm and vice versa)
    
    public Ecomm_MainPage clickEcomm() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(accessTypeLocator));
        btn.click();
        
        return new Ecomm_MainPage(driver);
    }
    
    public CCE_MainPage clickCCE() {
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(accessTypeLocator));
        btn.click();
        
        return new CCE_MainPage(driver);
    }
    
    //Ecomm Navigation methods
    
    public Ecomm_ManualEntryPage clickManualEntry() {
        //Wait for visibility of navigation bar
        WebElement waitForVisible = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ordersTab));
        //navigate to the manual entry link
        driver.findElement(ordersTab).click();
        //wait for menu to drop down
        WebElement waitForMenuVis = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(manualEntrySubTab));
        driver.findElement(manualEntrySubTab).click();
        
        //return a manual entry page
        return new Ecomm_ManualEntryPage(driver);
    }
    
    public Ecomm_UploadOrderPage clickUploadOrder() {
        //Wait for orders tab
        WebElement waitForOrders = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ordersTab));
        //Navigate to the subtab link
        driver.findElement(ordersTab).click();
        //Wait for drop down
        WebElement waitForMenu = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(uploadOrderSubTab)));
        driver.findElement(uploadOrderSubTab).click();
        
        //return a manual entry page
        return new Ecomm_UploadOrderPage(driver);
    }
    
    public Ecomm_FromExistingPage clickFromExisting() {
        //Wait for orders tab
        WebElement waitForOrders = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ordersTab));
        driver.findElement(ordersTab).click();
        
        WebElement waitForMenu = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(fromExistingSubTab)));
        driver.findElement(fromExistingSubTab).click();
        
        return new Ecomm_FromExistingPage(driver);
    }
    
    public Ecomm_ShadeNotAvailablePage clickShadeNotAvailable() {
        //Wait for orders tab
        WebElement waitForOrders = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ordersTab));
        driver.findElement(ordersTab).click();
        
        WebElement waitForMenu = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(shadeNotAvailSubTab)));
        driver.findElement(shadeNotAvailSubTab).click();
        
        return new Ecomm_ShadeNotAvailablePage(driver);
    }
    
    public Ecomm_WaitingForShadePage clickWaitingForShade() {
        //Wait for orders tab
        WebElement waitForOrders = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ordersTab));
        driver.findElement(ordersTab).click();
        
        WebElement waitForMenu = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(waitingForShadeSubTab)));
        driver.findElement(waitingForShadeSubTab).click();
        
        return new Ecomm_WaitingForShadePage(driver);
    }
    
    public Ecomm_OutstandingOrdersPage clickOutstandingOrders() {
         //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstandingOrdersTab)));      
        driver.findElement(outstandingOrdersTab).click();
        
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(outOrdersSubTab)));      
        driver.findElement(outOrdersSubTab).click();
        
        return new Ecomm_OutstandingOrdersPage(driver);
    }
    
    public Ecomm_OutstandingOrderDraftPage clickOutstandingDraft() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstandingOrdersTab)));      
        driver.findElement(outstandingOrdersTab).click();
        
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstOrderDraftSubTab)));      
        driver.findElement(outstOrderDraftSubTab).click();
        
        return new Ecomm_OutstandingOrderDraftPage(driver);
    }
    
    public Ecomm_OutstandingUploadDraftPage clickOutstandingUploadDraft() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstandingOrdersTab)));
        
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(outstandingOrdersTab)).click().build().perform();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstUploadDraftSubTab)));
        action.click(driver.findElement(outstUploadDraftSubTab)).build().perform();
        
        return new Ecomm_OutstandingUploadDraftPage(driver);
    }
    
    public Ecomm_PendingApprovalListPage clickPendingApprovalListPage() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstandingOrdersTab)));
        
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(outstandingOrdersTab)).click().build().perform();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(pendingApprovalSubTab)));
        action.click(driver.findElement(pendingApprovalSubTab)).build().perform();
        
        return new Ecomm_PendingApprovalListPage(driver);
    }
    
    public Ecomm_PendingApprovalListPage clickPendingApprovalListPageApprover() {
        //Works specifically for approver account (mail.kamleshpatidar@gmail.com)
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstandingOrdersTab)));
        
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(outstandingOrdersTab)).click().build().perform();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(pendingApprovalSubTabApprover)));
        action.click(driver.findElement(pendingApprovalSubTabApprover)).build().perform();
        
        return new Ecomm_PendingApprovalListPage(driver);
    }
    
    public Ecomm_DeniedOrderPage clickDeniedOrderRequester() {
        //Works specifically for requester account (autolifeeasy@coats.com)
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstandingOrdersTab)));
        
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(outstandingOrdersTab)).click().build().perform();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(deniedOrderSubtabRequester)));
        action.click(driver.findElement(deniedOrderSubtabRequester)).build().perform();
        
        return new Ecomm_DeniedOrderPage(driver);
    }
    
    public Ecomm_DeniedOrderPage clickDeniedOrderApprover() {
        //Works specifically for requester account (autolifeeasy@coats.com)
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstandingOrdersTab)));
        
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(outstandingOrdersTab)).click().build().perform();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(deniedOrderSubtabApprover)));
        action.click(driver.findElement(deniedOrderSubtabApprover)).build().perform();
        
        return new Ecomm_DeniedOrderPage(driver);
    }

    public Ecomm_SAPInterfaceLogPage clickSAPInterfaceLog() {
    	//Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(sapInterfaceLogTab)));
        
        driver.findElement(sapInterfaceLogTab).click();
        
        return new Ecomm_SAPInterfaceLogPage(driver);
    }
    
    public Ecomm_ProductAvailabilityCheckPage clickProductAvailabilityCheck() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(pacTab));
        driver.findElement(pacTab).click();
        return new Ecomm_ProductAvailabilityCheckPage(driver);
    }
    
    public Ecomm_InvoicesPage clickInvoices() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(invoicesSubTab)));
        driver.findElement(invoicesSubTab).click();
		
        return new Ecomm_InvoicesPage(driver);
	}
    
    public Ecomm_DeliveryNotesPage clickDeliveryNotes() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(deliveryNotesSubTab)));
        driver.findElement(deliveryNotesSubTab).click();
		
        return new Ecomm_DeliveryNotesPage(driver);
    }
    
    public Ecomm_SummaryOfPurchasePage clickSummaryOfPurchases() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(summaryOfSubTab)));
        driver.findElement(summaryOfSubTab).click();
		
        return new Ecomm_SummaryOfPurchasePage(driver);
    }
    
    public Ecomm_OutstandingPaymentsPage clickOutstandingPayments() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(outPaySubTab)));
        driver.findElement(outPaySubTab).click();
		
        return new Ecomm_OutstandingPaymentsPage(driver);
    }
    
    public Ecomm_MyReportsPage clickMyReports() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(myReportsSubTab)));
        driver.findElement(myReportsSubTab).click();
		
        return new Ecomm_MyReportsPage(driver);
    }
    
    public Ecomm_OrderApprovalHistoryPage clickOrderApprovalHistory() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(orderAppHistSubTab)));
        driver.findElement(orderAppHistSubTab).click();
		
        return new Ecomm_OrderApprovalHistoryPage(driver);
    }
    
    public Ecomm_BackendInProcessPage clickBackendInProcess() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(dashboardTab)));
        driver.findElement(dashboardTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(backendIPSubTab)));
        driver.findElement(backendIPSubTab).click();
		
        return new Ecomm_BackendInProcessPage(driver);
    }
    
    public Ecomm_BackendFailedFilesPage clickBackendFailedFiles() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(dashboardTab)));
        driver.findElement(dashboardTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(backendFailedSubTab)));
        driver.findElement(backendFailedSubTab).click();
		
        return new Ecomm_BackendFailedFilesPage(driver);
    }
    
    public Ecomm_FTPFailedFilesPage clickFTPFailedFiles() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(dashboardTab)));
        driver.findElement(dashboardTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(ftpFailedSubTab)));
        driver.findElement(ftpFailedSubTab).click();
		
        return new Ecomm_FTPFailedFilesPage(driver);
    }
    
    public Ecomm_FailedContractOrderPage clickFailedContractOrder() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(dashboardTab)));
        driver.findElement(dashboardTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(failedContractOrderSubtab)));
        driver.findElement(failedContractOrderSubtab).click();
		
        return new Ecomm_FailedContractOrderPage(driver);
    }
    
    //CCE Navigation methods
    
    public CCE_OrderSamplesPage pressOrderSamples() {

        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderSamplesSubtab));
        action.click(driver.findElement(orderSamplesSubtab)).build().perform();
        
        return new CCE_OrderSamplesPage(driver);
    }
    
    public CCE_ManualEnrichPage pressManualEnrich() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(manualEnrichSubtab));
        action.click(driver.findElement(manualEnrichSubtab)).build().perform();
        
        return new CCE_ManualEnrichPage(driver);
    }
    
    public CCE_OutstandingDraftPage pressOutstandingDraft() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(outstandingDraftSubtab));
        action.click(driver.findElement(outstandingDraftSubtab)).build().perform();
        
        return new CCE_OutstandingDraftPage(driver);
    }
    
    public CCE_OrderStatusPage pressOrderStatus() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderStatusSubtab));
        action.click(driver.findElement(orderStatusSubtab)).build().perform();
        
        return new CCE_OrderStatusPage(driver);
    }
    
    public CCE_DNReprintPage pressDNReprint() {
        
        WebElement waitForVisibility = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(ordersHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(dnReprintSubtab));
        action.click(driver.findElement(dnReprintSubtab)).build().perform();
        
        return new CCE_DNReprintPage(driver);
        
    }
    
    public CCE_FeedbackPage pressFeedback() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(feedbackSubtab));
        action.click(driver.findElement(feedbackSubtab)).build().perform();
        
        return new CCE_FeedbackPage(driver);
    }
    
    public CCE_FeedbackAwaitingPage pressFeedbackAwaiting() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(feedbackAwaitingSubtab));
        action.click(driver.findElement(feedbackAwaitingSubtab)).build().perform();
        
        return new CCE_FeedbackAwaitingPage(driver);
    }
    
    public CCE_FeedbackCompletedPage pressFeedbackCompleted() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ordersHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(feedbackCompletedSubtab));
        action.click(driver.findElement(feedbackCompletedSubtab)).build().perform();
        
        return new CCE_FeedbackCompletedPage(driver);
    }
    
    public CCE_HubSosPage pressHubSos() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(hubHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubSosSubtab));
        action.click(driver.findElement(hubSosSubtab)).build().perform();
        
        return new CCE_HubSosPage(driver);
    }
    
    public CCE_ReceivedHubPage pressReceivedHub() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(hubHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(receivedHubSubtab));
        action.click(driver.findElement(receivedHubSubtab)).build().perform();
        
        return new CCE_ReceivedHubPage(driver);
    }
    
    public CCE_InboxPage pressInbox() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(inboxHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(inboxHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(inboxSubtab));
        action.click(driver.findElement(inboxSubtab)).build().perform();
        
        return new CCE_InboxPage(driver);
    }
    
    public CCE_InboxSAPPage pressInboxSAP() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(inboxHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(inboxHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(inboxSAPSubtab));
        action.click(driver.findElement(inboxSAPSubtab)).build().perform();
        
        return new CCE_InboxSAPPage(driver);
    }
    
    public CCE_ConfirmProductionPage pressConfirmProduction() {
        //Wait for header
        WebElement waitForHeaderVis = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(conProdHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(conProdHeader));
        //Click header
        driver.findElement(conProdHeader).click();
        return new CCE_ConfirmProductionPage(driver);
    }
    
    public CCE_RefillCabinetPage pressRefillCabinet() {
        //Wait for header
        WebElement waitForHeaderVis = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(refCabHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(refCabHeader));
        //Click header
        driver.findElement(refCabHeader).click();
        return new CCE_RefillCabinetPage(driver);
    }
    
    public CCE_FCETaskStatusPage pressFCETaskStatus() {
        WebElement waitForHeaderVis = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(reportsHeader));       
        //Wait for header
        WebElement waitForHeaderClick = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(reportsHeader));
        //Click header
        driver.findElement(reportsHeader).click();
        //Wait for feedback completed subtab
        WebElement waitForSubtab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(fceTaskStatusSubtab));
        //click subtab
        driver.findElement(fceTaskStatusSubtab).click();
        
        return new CCE_FCETaskStatusPage(driver);
    }
    
    public CCE_OrderCycleTimePage pressOrderCycleTime() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(reportsHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(reportsHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderCycleTimeSubtab));
        action.click(driver.findElement(orderCycleTimeSubtab)).build().perform();
        
        return new CCE_OrderCycleTimePage(driver);
    } 
    
    public CCE_TotalOrdersPage pressTotalOrders() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(reportsHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(reportsHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(totalOrdersSubtab));
        action.click(driver.findElement(totalOrdersSubtab)).build().perform();
        
        return new CCE_TotalOrdersPage(driver);
    }
    
    public Mst_AllUserTypesPage pressAllUserTypes() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(allUserTypesSubtab));
        action.click(driver.findElement(allUserTypesSubtab)).build().perform();
        
        return new Mst_AllUserTypesPage(driver);
    }
    
    public Mst_CoatsUsersPage pressCoatsUsers() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsUsersSubtab));
        action.click(driver.findElement(coatsUsersSubtab)).build().perform();
        
        return new Mst_CoatsUsersPage(driver);
    }
    
    public CCE_LRMLogPage pressLRMLog() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lrmLogSubtab));
        action.click(driver.findElement(lrmLogSubtab)).build().perform();
        
        return new CCE_LRMLogPage(driver);
    }
    
    public CCE_SAPLogPage pressSAPLog() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(sapLogSubtab));
        action.click(driver.findElement(sapLogSubtab)).build().perform();
        
        return new CCE_SAPLogPage(driver);
    }
    
    public CCE_MainPage openMasters() {
        By menuHead = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li.menuHead");
        
        WebElement admin = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(admin).build().perform();
        
        WebElement masters = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(masters).build().perform();
        
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(menuHead));
        
        return new CCE_MainPage(driver);
        
    }
    
    public boolean checkFiltrationAndRecords(String locator1, String locator2, By noRecords, String item, int firstRow) {
        
        System.out.println("Checking for records...");
        
        try {
            WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(noRecords));
            System.out.println("No records found");
            return true;           
        } catch (Exception e) {
            System.out.println("Records found");
        }
        

        for (int i = 0; i < 5; i++) {
            By cellLocator = By.cssSelector(locator1 + "" + (i+firstRow) + "" + locator2);
            String text ="";
            try {
                text = driver.findElement(cellLocator).getText();
            } catch(Exception e) {
                System.out.println("All records searched");
                return true;
            }
            if (!(text.equals(item))) {
                return false;
            }
        }
        return true;
        
    }
    
    public boolean checkFiltration(String locator1, String locator2, String item, int firstRow) {
        
        for (int i = 0; i < 3; i++) {
            By cellLocator = By.cssSelector(locator1 + "" + (i+firstRow) + "" + locator2);
            String text ="";
            try {
                WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(cellLocator));
                text = driver.findElement(cellLocator).getText();
                if (!(text.contains(item))) {
                    return false;
                }
            } catch(Exception e) {
                System.out.println("All records searched");
                return true;
            }
            
        }
        return true;
        
    }
    
    public boolean checkFiltration(String locator1, String locator2, String item, By countField, int firstRow) {
        //Method will check that the filter applied works properly. Locator 1 and 2 make up the locator, and are split to allow the row number to be inseted
        //Item is the expected item to be contained by the cell referenced with locator 1 and 2
        //countField is the element in the bottom left which holds record count information
        //firstRow is the table's starting row value. Usually 2
        
        int count = this.getRecordCount(countField);
        
        int tableCount = (count >= 10) ? 10 : count;
        
        for (int i = firstRow; i < (tableCount + firstRow); i++) {
            By locator = By.cssSelector(locator1 + "" +  i + "" + locator2);
            WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (!element.getText().trim().contains(item)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean checkFiltration(String locator1, String locator2, String item, int firstRow,int multiplier) {
        
        for (int i = 0; i < 3; i++) {
            By cellLocator = By.cssSelector(locator1 + "" + (i*multiplier+firstRow) + "" + locator2);
            String text ="";
            try {
                WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(cellLocator));
                text = driver.findElement(cellLocator).getText();
                if (!(text.contains(item))) {
                    return false;
                }
            } catch(Exception e) {
                System.out.println("All records searched");
                return true;
            }
            
        }
        return true;
        
    }
    
    public boolean checkForRecords() {
        try {
            boolean wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.invisibilityOfElementLocated(noRecords));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    public boolean checkForFatalError() {
        try {
            boolean wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(breadcrumbLocator, "Fatal Error"));
            System.out.println("Fatal Error found");
            return true;
        } catch (Exception e) {
            System.out.println("No error detected.");
            return false;
        }       
    }
    
    //Masters Navigation methods
    
    public Mst_SalesOrgPage selectSalesOrg() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgOption));
        action.click(driver.findElement(salesOrgOption)).build().perform();
        
        return new Mst_SalesOrgPage(driver);
        
    }
    
    public Mst_SalesOrgMaterialsPage selectSalesOrgMaterials() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgMatOption));
        action.click(driver.findElement(salesOrgMatOption)).build().perform();
        
        return new Mst_SalesOrgMaterialsPage(driver);
        
    }
    
    public Mst_CustomersPage selectCustomers() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customersOption));
        action.click(driver.findElement(customersOption)).build().perform();
        
        return new Mst_CustomersPage(driver);
        
    }
    
    public Mst_ShadesPage selectShades() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadesOption));
        action.click(driver.findElement(shadesOption)).build().perform();
        
        return new Mst_ShadesPage(driver);
    }
    
    public Mst_CustomerShadesPage selectCustomerShades() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerShadesOption));
        action.click(driver.findElement(customerShadesOption)).build().perform();
        
        return new Mst_CustomerShadesPage(driver);
    }
    
    public Mst_CountriesPage selectCountries() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(countriesOption));
        action.click(driver.findElement(countriesOption)).build().perform();
        
        return new Mst_CountriesPage(driver);
    }
    
    public Mst_SubAccountPage selectSubAccount() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(subAccountOption));
        action.click(driver.findElement(subAccountOption)).build().perform();
        
        return new Mst_SubAccountPage(driver);
    }
    
    public Mst_MultiSoldToPage selectMultiSoldTo() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(multiSoldToOption));
        action.click(driver.findElement(multiSoldToOption)).build().perform();
        
        return new Mst_MultiSoldToPage(driver);
    }
    
    public Mst_ApproverListPage selectApproverList() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(approverListOption));
        action.click(driver.findElement(approverListOption)).build().perform();
        
        return new Mst_ApproverListPage(driver);
    }
    
    public Mst_CustBusinessPrincipalPage selectCustBusinessPrincipal() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custBusPrincOption));
        action.click(driver.findElement(custBusPrincOption)).build().perform();
        
        return new Mst_CustBusinessPrincipalPage(driver);
    }
    
    public Mst_CustFinishesPage selectCustomerFinishes() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custFinishesOption));
        action.click(driver.findElement(custFinishesOption)).build().perform();
        
        return new Mst_CustFinishesPage(driver);
    }
    
    public Mst_CustLengthsPage selectCustomerLengths() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custLengthsOption));
        action.click(driver.findElement(custLengthsOption)).build().perform();
        
        return new Mst_CustLengthsPage(driver);
    }
    
    public Mst_CustTicketsPage selectCustomerTickets() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custTicketsOption));
        action.click(driver.findElement(custTicketsOption)).build().perform();
        
        return new Mst_CustTicketsPage(driver);
    }
    
    public Mst_CustBrandsPage selectCustomerBrands() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custBrandsOption));
        action.click(driver.findElement(custBrandsOption)).build().perform();
        
        return new Mst_CustBrandsPage(driver);
    }
    
    public Mst_CustMaterialsPage selectCustomerMaterials() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custMaterialsOption));
        action.click(driver.findElement(custMaterialsOption)).build().perform();
        
        return new Mst_CustMaterialsPage(driver);
    }
    
    public Mst_BusinessPrincipalsPage selectBusinessPrincipals() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(businessPrincipalsOption));
        action.click(driver.findElement(businessPrincipalsOption)).build().perform();
        
        return new Mst_BusinessPrincipalsPage(driver);
    }
    
    public Mst_PlantsPage selectPlants() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantsOption));
        action.click(driver.findElement(plantsOption)).build().perform();
        
        return new Mst_PlantsPage(driver);
    }
    
    public Mst_HubsPage selectHubs() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hubsOption));
        action.click(driver.findElement(hubsOption)).build().perform();
        
        return new Mst_HubsPage(driver);
    }
    
    public Mst_PlantHolidaysPage selectPlantHolidays() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(plantHolidaysOption));
        action.click(driver.findElement(plantHolidaysOption)).build().perform();
        
        return new Mst_PlantHolidaysPage(driver);
    }
    
    public Mst_EnterpriseStructurePage selectEnterpriseStructure() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(enterpriseStructureOption));
        action.click(driver.findElement(enterpriseStructureOption)).build().perform();
        
        return new Mst_EnterpriseStructurePage(driver);
    }
    
    public Mst_BrandsPage selectBrands() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandsOption));
        action.click(driver.findElement(brandsOption)).build().perform();
        
        return new Mst_BrandsPage(driver);
    }
    
    public Mst_TicketsPage selectTickets() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketsOption));
        action.click(driver.findElement(ticketsOption)).build().perform();
        
        return new Mst_TicketsPage(driver);
    }
    
    public Mst_LengthsPage selectLengths() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthsOption));
        action.click(driver.findElement(lengthsOption)).build().perform();
        
        return new Mst_LengthsPage(driver);
    }
    
    public Mst_FinishesPage selectFinishes() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(finishesOption));
        action.click(driver.findElement(finishesOption)).build().perform();
        
        return new Mst_FinishesPage(driver);
    }
    
    public Mst_BasicMaterialsPage selectBasicMaterials() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(basicMatOption));
        action.click(driver.findElement(basicMatOption)).build().perform();
        
        return new Mst_BasicMaterialsPage(driver);
    }
    
    public Mst_MaterialGroupsPage selectMaterialGroups() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(matGroupsOption));
        action.click(driver.findElement(matGroupsOption)).build().perform();
        
        return new Mst_MaterialGroupsPage(driver);
    }
    
    public Mst_HierarchyPage selectHierarchy() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(hierarchyOption));
        action.click(driver.findElement(hierarchyOption)).build().perform();
        
        return new Mst_HierarchyPage(driver);
    }
    
    public Mst_LightSourcesPage selectLightSources() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lightSourcesOption));
        action.click(driver.findElement(lightSourcesOption)).build().perform();
        
        return new Mst_LightSourcesPage(driver);
    }
    
    public Mst_PurposeTypesPage selectPurposeTypes() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(purposeTypesOption));
        action.click(driver.findElement(purposeTypesOption)).build().perform();
        
        return new Mst_PurposeTypesPage(driver);
    }
    
    public Mst_RejectionReasonsPage selectRejectionReasons() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(rejectionReasonsOption));
        action.click(driver.findElement(rejectionReasonsOption)).build().perform();
        
        return new Mst_RejectionReasonsPage(driver);
    }
    
    public Mst_QuantityFactorsPage selectQuantityFactors() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(quantityFactorsOption));
        action.click(driver.findElement(quantityFactorsOption)).build().perform();
        
        return new Mst_QuantityFactorsPage(driver);
    }
    
    public Mst_LengthOffersPage selectLengthOffers() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthOffersOption));
        action.click(driver.findElement(lengthOffersOption)).build().perform();
        
        return new Mst_LengthOffersPage(driver);
    }
    
    public Mst_WarehouseInstructionsPage selectWarehouseInstructions() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(warehouseInstOption));
        action.click(driver.findElement(warehouseInstOption)).build().perform();
        
        return new Mst_WarehouseInstructionsPage(driver);
    }
    
    public Mst_ShadeCardsPage selectShadeCards() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCardsOption));
        action.click(driver.findElement(shadeCardsOption)).build().perform();
        
        return new Mst_ShadeCardsPage(driver);
    }
    
    public Mst_ShadeCardPlantsPage selectShadeCardPlants() {
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shadeCardPlantsOption));
        action.click(driver.findElement(shadeCardPlantsOption)).build().perform();
        
        return new Mst_ShadeCardPlantsPage(driver);
    }
    
    public Mst_ChargedProductsPage selectChargedProducts() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(chargedProductsOption));
        action.click(driver.findElement(chargedProductsOption)).build().perform();
        
        return new Mst_ChargedProductsPage(driver);
        
    }
    
    public Mst_ForcedEnrichmentPage selectForcedEnrichment() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(forcedEnrichmentOption));
        action.click(driver.findElement(forcedEnrichmentOption)).build().perform();
        
        return new Mst_ForcedEnrichmentPage(driver);
        
    }
    
    public Mst_SupplyPlantsPage selectSupplyPlants() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(supplyPlantsOption));
        action.click(driver.findElement(supplyPlantsOption)).build().perform();
        
        return new Mst_SupplyPlantsPage(driver);
        
    }
    
    public Mst_DyeLotMultiplesPage selectDyeLotMultiples() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(dyeLotMultiplesOption));
        action.click(driver.findElement(dyeLotMultiplesOption)).build().perform();
        
        return new Mst_DyeLotMultiplesPage(driver);
        
    }
    
    public Mst_OrderTypePage selectOrderType() {
        
        WebElement waitForHeader = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(adminHeader)).build().perform();
        WebElement waitForSubTab = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(driver.findElement(mastersSubtab)).build().perform();
        WebElement waitForSalesOrgMaster = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(orderTypeOption));
        action.click(driver.findElement(orderTypeOption)).build().perform();
        
        return new Mst_OrderTypePage(driver);
    }
    
    public WBA_LoginPage pressLogout() {
        WebElement label = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(logoutLabelLocator));
    
        label.click();
        
        return new WBA_LoginPage(driver);
    }
    
    public WBA_LoginPage pressLogoutAlt() {
        By selector = By.linkText("Logout");
        
        WebElement link = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(selector));
        
        link.click();
        
        return new WBA_LoginPage(driver);
    }
}
