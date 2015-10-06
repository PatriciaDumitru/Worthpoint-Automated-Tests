
package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_MainPage extends WBA_BasePage {
    
    //Page element locators
    static By contentImageLocator = By.cssSelector("#header > div:nth-child(6) > img");
    static By navBarLocator = By.id("topnav");
    static By mainImageLocator = By.cssSelector("#welcomefrm1 > center > img");
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
    static By invoicesSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(1)");
    static By deliveryNotesSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(2)");
    static By summaryOfSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(3)");
    static By outPaySubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(4)");
    static By myReportsSubTab = By.cssSelector("#topnav > li:nth-child(4) > div > div > ul > li:nth-child(5)");
    static By backendIPSubTab = By.cssSelector("#topnav > li:nth-child(5) > div > div > ul > li:nth-child(2)");
    static By backendFailedSubTab = By.cssSelector("#topnav > li:nth-child(5) > div > div > ul > li:nth-child(3)");
    static By ftpFailedSubTab = By.cssSelector("#topnav > li:nth-child(5) > div > div > ul > li:nth-child(4) > a"); 
    
    public Ecomm_MainPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public static WebElement getContentImage() {
        //find and return element
        return driver.findElement(contentImageLocator);
    }
    
    public static WebElement getNavBar() {
        //find and return element
        return driver.findElement(navBarLocator);
    }
    
    public static WebElement getMainImage() {
        //find and return element
        return driver.findElement(mainImageLocator);
    }
    
    public static void hoverNavBar(String tabTitle) {
        By tabLocator = By.xpath("//a[contains(text(),'" + tabTitle+ "')]");
        //new action to hover over nav bar item
        Actions hoverItem = new Actions(driver);
        hoverItem.moveToElement(driver.findElement(tabLocator)).build().perform();     
    }
    
    public Ecomm_ManualEntryPage clickManualEntry() {
        //Wait for visibility of navigation bar
        WebElement waitForNavBar = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ordersTab));
        //navigate to the manual entry link
        driver.findElement(ordersTab).click();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(manualEntrySubTab)));
        driver.findElement(manualEntrySubTab).click();
        
        //return a manual entry page
        return new Ecomm_ManualEntryPage(driver);
    }
    
    public Ecomm_UploadOrderPage clickUploadOrder() {
        //Wait for orders tab
        WebElement waitForOrders = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(ordersTab));
        
        //navigate to the Upload Order link
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersTab)).click().build().perform();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(uploadOrderSubTab)));
        action.click(driver.findElement(uploadOrderSubTab)).build().perform();
        
        //return a manual entry page
        return new Ecomm_UploadOrderPage(driver);
    }
    
    public Ecomm_FromExistingPage clickFromExisting() {
        //Wait for orders tab
        WebElement waitForOrders = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(ordersTab));
        
        //navigate to the Upload Order link
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(ordersTab)).click().build().perform();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(fromExistingSubTab)));
        action.click(driver.findElement(fromExistingSubTab)).build().perform();
        
        return new Ecomm_FromExistingPage(driver);
    }
    
    public Ecomm_OutstandingOrderDraftPage clickOutstandingDraft() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstandingOrdersTab)));
        
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(outstandingOrdersTab)).click().build().perform();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstOrderDraftSubTab)));
        action.click(driver.findElement(outstOrderDraftSubTab)).build().perform();
        
        return new Ecomm_OutstandingOrderDraftPage(driver);
    }
    
    public Ecomm_OutstandingUploadDraftPage clickOutstandingUploadDraft() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstandingOrdersTab)));
        
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(outstandingOrdersTab)).click().build().perform();
        //wait for menu to drop down
        WebElement waitForMenu = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(outstUploadDraftSubTab)));
        action.click(driver.findElement(outstUploadDraftSubTab)).build().perform();
        
        return new Ecomm_OutstandingUploadDraftPage(driver);
    }

    public Ecomm_SAPInterfaceLogPage clickSAPInterfaceLog() {
    	//Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(sapInterfaceLogTab)));
        
        driver.findElement(sapInterfaceLogTab).click();
        
        return new Ecomm_SAPInterfaceLogPage(driver);
    }
    
    public Ecomm_InvoicesPage clickInvoices() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(invoicesSubTab)));
        driver.findElement(invoicesSubTab).click();
		
        return new Ecomm_InvoicesPage(driver);
	}
    
    public Ecomm_DeliveryNotesPage clickDeliveryNotes() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(deliveryNotesSubTab)));
        driver.findElement(deliveryNotesSubTab).click();
		
        return new Ecomm_DeliveryNotesPage(driver);
    }
    
    public Ecomm_SummaryOfPurchasePage clickSummaryOfPurchases() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(summaryOfSubTab)));
        driver.findElement(summaryOfSubTab).click();
		
        return new Ecomm_SummaryOfPurchasePage(driver);
    }
    
    public Ecomm_OutstandingPaymentsPage clickOutstandingPayments() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(outPaySubTab)));
        driver.findElement(outPaySubTab).click();
		
        return new Ecomm_OutstandingPaymentsPage(driver);
    }
    
    public Ecomm_MyReportsPage clickMyReports() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(reportsTab)));
        driver.findElement(reportsTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(myReportsSubTab)));
        driver.findElement(myReportsSubTab).click();
		
        return new Ecomm_MyReportsPage(driver);
    }
    
    public Ecomm_BackendInProcessPage clickBackendInProcess() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(dashboardTab)));
        driver.findElement(dashboardTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(backendIPSubTab)));
        driver.findElement(backendIPSubTab).click();
		
        return new Ecomm_BackendInProcessPage(driver);
    }
    
    public Ecomm_BackendFailedFilesPage clickBackendFailedFiles() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(dashboardTab)));
        driver.findElement(dashboardTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(backendFailedSubTab)));
        driver.findElement(backendFailedSubTab).click();
		
        return new Ecomm_BackendFailedFilesPage(driver);
    }
    
    public Ecomm_FTPFailedFilesPage clickFTPFailedFiles() {
        //Wait for tab
        WebElement waitForTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(dashboardTab)));
        driver.findElement(dashboardTab).click();
		
        //Wait for subtab
        WebElement waitForSubTab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(ftpFailedSubTab)));
        driver.findElement(ftpFailedSubTab).click();
		
        return new Ecomm_FTPFailedFilesPage(driver);
    }
    
}
