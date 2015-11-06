
package PageObjects;

import AutomationFramework.DataItems;
import static PageObjects.CCE_MainPage.ordersHeader;
import static PageObjects.Ecomm_MainPage.ordersTab;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
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
    private Object ExepectedConditions;
    static By contentFrame = By.id("content");
    
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
        static By onlineAppHistSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(8)");
        
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
            static By salesOrgMatOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(2) > ul > li:nth-child(6)");
            static By customersOption = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(3) > ul > li:nth-child(2)");
        static By lrmLogSubtab = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(4)");
        static By sapLogSubtab = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(5)");
        static By archivesSubtab = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(6)");
    
    
    
    
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
    
    public WBA_LoginPage pressLogout() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(logoutLabelLocator));
    
        driver.findElement(logoutLabelLocator).click();
        
        return new WBA_LoginPage(driver);
    }
}
