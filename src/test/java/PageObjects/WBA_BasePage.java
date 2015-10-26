
package PageObjects;

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
    
    //Navigation bar tabs
    static By ordersTab = By.cssSelector("#topnav > li:nth-child(1)");
    static By outstandingOrdersTab = By.cssSelector("#topnav > li:nth-child(2)");
    static By sapInterfaceLogTab = By.cssSelector("#topnav > li:nth-child(3)");
    static By reportsTab = By.cssSelector("#topnav > li:nth-child(4)");
    static By dashboardTab = By.cssSelector("#topnav > li:nth-child(5)");
    static By outstOrderDraftSubTab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(2)");
    static By outstUploadDraftSubTab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(3)");
    static By manualEntrySubTab = By.cssSelector("#topnav > li:nth-child(1) > div > div > ul > li:nth-child(1)");
    static By uploadOrderSubTab = By.cssSelector("#topnav > li:nth-child(1) > div > div > ul > li:nth-child(2)");
    static By fromExistingSubTab = By.cssSelector("#topnav > li:nth-child(1) > div > div > ul > li:nth-child(3) > a");
    static By shadeNotAvailSubTab = By.cssSelector("#topnav > li:nth-child(1) > div > div > ul > li:nth-child(4)");
    static By waitingForShadeSubTab = By.cssSelector("#topnav > li:nth-child(1) > div > div > ul > li:nth-child(5)");
    static By invoicesSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(1)");
    static By deliveryNotesSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(2)");
    static By summaryOfSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(3)");
    static By outPaySubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(4)");
    static By myReportsSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(5)");
    static By backendIPSubTab = By.cssSelector("#topnav > li:nth-child(5) > div > div > ul > li:nth-child(2)");
    static By backendFailedSubTab = By.cssSelector("#topnav > li:nth-child(5) > div > div > ul > li:nth-child(3)");
    static By ftpFailedSubTab = By.cssSelector("#topnav > li:nth-child(5) > div > div > ul > li:nth-child(4) > a"); 
    static By pacTab = By.cssSelector("#topnav > li:nth-child(3)");
    static By outOrdersSubTab = By.cssSelector("#topnav > li:nth-child(2) > div > div > ul > li:nth-child(1)");
    static By failedContractOrderSubtab = By.cssSelector("#topnav > li:nth-child(5) > div > div > ul > li:nth-child(5)");
    
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
        WebElement waitForElements = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(getFooter()));

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
        WebElement waitForContent = new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(contentFrame));
    }
    
    public Ecomm_ManualEntryPage clickManualEntry() {
        //Wait for visibility of navigation bar
        WebElement waitForVisible = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(ordersTab));
        //navigate to the manual entry link
        driver.findElement(ordersTab).click();
        //wait for menu to drop down
        WebElement waitForMenuVis = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(manualEntrySubTab));
        driver.findElement(manualEntrySubTab).click();
        
        //return a manual entry page
        return new Ecomm_ManualEntryPage(driver);
    }
    
    public Ecomm_UploadOrderPage clickUploadOrder() {
        //Wait for orders tab
        WebElement waitForOrders = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(ordersTab));
        //Navigate to the subtab link
        driver.findElement(ordersTab).click();
        //Wait for drop down
        WebElement waitForMenu = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(uploadOrderSubTab)));
        driver.findElement(uploadOrderSubTab).click();
        
        //return a manual entry page
        return new Ecomm_UploadOrderPage(driver);
    }
    
    public Ecomm_FromExistingPage clickFromExisting() {
        //Wait for orders tab
        WebElement waitForOrders = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(ordersTab));
        driver.findElement(ordersTab).click();
        
        WebElement waitForMenu = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(fromExistingSubTab)));
        driver.findElement(fromExistingSubTab).click();
        
        return new Ecomm_FromExistingPage(driver);
    }
    
    public Ecomm_ShadeNotAvailablePage clickShadeNotAvailable() {
        //Wait for orders tab
        WebElement waitForOrders = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(ordersTab));
        driver.findElement(ordersTab).click();
        
        WebElement waitForMenu = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(shadeNotAvailSubTab)));
        driver.findElement(shadeNotAvailSubTab).click();
        
        return new Ecomm_ShadeNotAvailablePage(driver);
    }
    
    public Ecomm_WaitingForShadePage clickWaitingForShade() {
        //Wait for orders tab
        WebElement waitForOrders = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(ordersTab));
        driver.findElement(ordersTab).click();
        
        WebElement waitForMenu = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(waitingForShadeSubTab)));
        driver.findElement(waitingForShadeSubTab).click();
        
        return new Ecomm_WaitingForShadePage(driver);
    }
    
    public Ecomm_OutstandingOrdersPage clickOutstandingOrders() {
         //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstandingOrdersTab)));      
        driver.findElement(outstandingOrdersTab).click();
        
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(outOrdersSubTab)));      
        driver.findElement(outOrdersSubTab).click();
        
        return new Ecomm_OutstandingOrdersPage(driver);
    }
    
    public Ecomm_OutstandingOrderDraftPage clickOutstandingDraft() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstandingOrdersTab)));      
        driver.findElement(outstandingOrdersTab).click();
        
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstOrderDraftSubTab)));      
        driver.findElement(outstOrderDraftSubTab).click();
        
        return new Ecomm_OutstandingOrderDraftPage(driver);
    }
    
    public Ecomm_OutstandingUploadDraftPage clickOutstandingUploadDraft() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstandingOrdersTab)));
        
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(outstandingOrdersTab)).click().build().perform();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstUploadDraftSubTab)));
        action.click(driver.findElement(outstUploadDraftSubTab)).build().perform();
        
        return new Ecomm_OutstandingUploadDraftPage(driver);
    }

    public Ecomm_SAPInterfaceLogPage clickSAPInterfaceLog() {
    	//Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(sapInterfaceLogTab)));
        
        driver.findElement(sapInterfaceLogTab).click();
        
        return new Ecomm_SAPInterfaceLogPage(driver);
    }
    
    public Ecomm_ProductAvailabilityCheckPage clickProductAvailabilityCheck() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(pacTab));
        driver.findElement(pacTab).click();
        return new Ecomm_ProductAvailabilityCheckPage(driver);
    }
    
    public Ecomm_InvoicesPage clickInvoices() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(invoicesSubTab)));
        driver.findElement(invoicesSubTab).click();
		
        return new Ecomm_InvoicesPage(driver);
	}
    
    public Ecomm_DeliveryNotesPage clickDeliveryNotes() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(deliveryNotesSubTab)));
        driver.findElement(deliveryNotesSubTab).click();
		
        return new Ecomm_DeliveryNotesPage(driver);
    }
    
    public Ecomm_SummaryOfPurchasePage clickSummaryOfPurchases() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(summaryOfSubTab)));
        driver.findElement(summaryOfSubTab).click();
		
        return new Ecomm_SummaryOfPurchasePage(driver);
    }
    
    public Ecomm_OutstandingPaymentsPage clickOutstandingPayments() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(outPaySubTab)));
        driver.findElement(outPaySubTab).click();
		
        return new Ecomm_OutstandingPaymentsPage(driver);
    }
    
    public Ecomm_MyReportsPage clickMyReports() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(myReportsSubTab)));
        driver.findElement(myReportsSubTab).click();
		
        return new Ecomm_MyReportsPage(driver);
    }
    
    public Ecomm_BackendInProcessPage clickBackendInProcess() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(dashboardTab)));
        driver.findElement(dashboardTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(backendIPSubTab)));
        driver.findElement(backendIPSubTab).click();
		
        return new Ecomm_BackendInProcessPage(driver);
    }
    
    public Ecomm_BackendFailedFilesPage clickBackendFailedFiles() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(dashboardTab)));
        driver.findElement(dashboardTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(backendFailedSubTab)));
        driver.findElement(backendFailedSubTab).click();
		
        return new Ecomm_BackendFailedFilesPage(driver);
    }
    
    public Ecomm_FTPFailedFilesPage clickFTPFailedFiles() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(dashboardTab)));
        driver.findElement(dashboardTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(ftpFailedSubTab)));
        driver.findElement(ftpFailedSubTab).click();
		
        return new Ecomm_FTPFailedFilesPage(driver);
    }
    
    public Ecomm_FailedContractOrderPage clickFailedContractOrder() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(dashboardTab)));
        driver.findElement(dashboardTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(driver.findElement(failedContractOrderSubtab)));
        driver.findElement(failedContractOrderSubtab).click();
		
        return new Ecomm_FailedContractOrderPage(driver);
    }
    
}
