package PageObjects;

import AutomationFramework.DataItems;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_OrderViewPage {
    WebDriver driver;
    
    //Table cell locators
    static By yourMatNumCellLocator = By.cssSelector("#BulkOrderLineViewOutstandingOrderListForm > div.grid_12 > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody > tr > td:nth-child(3)");
    static By articleCellLocator = By.cssSelector("#BulkOrderLineViewOutstandingOrderListForm > div.grid_12 > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody > tr > td:nth-child(4)");
    static By brandCellLocator = By.cssSelector("#BulkOrderLineViewOutstandingOrderListForm > div.grid_12 > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody > tr > td:nth-child(5)");
    static By ticketCellLocator = By.cssSelector("#BulkOrderLineViewOutstandingOrderListForm > div.grid_12 > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody > tr > td:nth-child(6)");
    static By shadeCodeCellLocator = By.cssSelector("#BulkOrderLineViewOutstandingOrderListForm > div.grid_12 > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody > tr > td:nth-child(7)");
    static By COError = By.cssSelector("#BulkOrderLineViewContractLinesForm > div.grid_12 > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody > tr > td:nth-child(17) > span");
    static By errorHeader = By.cssSelector("#BulkOrderLineViewContractLinesForm > div.grid_12 > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody > tr > td:nth-child(17)");
    static By printButton = By.cssSelector("#tbl > tbody > tr:nth-child(8) > td:nth-child(2) > a");
    static By frameLocator = By.id("TB_iframeContent");
    static By productInfoLocator = By.cssSelector("#content > div > div.tbl-title.upload");
    static By productInfoText = By.cssSelector("#content > div > table > tbody > tr > td > h1");
    static By contentLocator = By.cssSelector("body > div.grid_12");
    static By ftDataTable = By.cssSelector("body > div > table:nth-child(5) > tbody > tr > td > pre");
    
    public Ecomm_OrderViewPage(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public WebElement getYourMatNumCell() {
        //find and return element
        return driver.findElement(yourMatNumCellLocator);
    }
    
    public WebElement getArticleCell() {
        //find and return element
        return driver.findElement(articleCellLocator);
    }
    
    public WebElement getBrandCell() {
        //find and return element
        return driver.findElement(brandCellLocator);
    }
    
    public WebElement getTicketCell() {
        //find and return element
        return driver.findElement(ticketCellLocator);
    }
    
    public WebElement getShadeCodeCell() {
        //find and return element
        return driver.findElement(shadeCodeCellLocator);
    }
    
    public String getInformation() {
        return driver.findElement(productInfoText).getText();
    }
    
    public String getCOError() {
        switchTo();
        WebElement waitForCell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(COError));
        return driver.findElement(COError).getText();
    }
    
    public void switchTo() {
    	WebDriver waitForFrame = new WebDriverWait(driver,DataItems.longWait).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }
    
    public void pressPrint() {
    	switchTo();
    	WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(printButton));
    	if (DataItems.printingEnabled) {
        	driver.findElement(printButton).click();
        	System.out.println("Item sent to printer");
    	} else {
    		System.out.println("Printing is disabled, item was not sent to printer");
    		this.closeView();
                this.waitForInvisibility();
    	}

    }
    
    public void waitForContent() {
    	WebElement wait = new WebDriverWait(driver,DataItems.longWait).until(ExpectedConditions.visibilityOfElementLocated(frameLocator));
    }
    
    public void waitForFTData() {
        WebElement waitForVisible = new WebDriverWait(driver,DataItems.longWait).until(ExpectedConditions.visibilityOfElementLocated(ftDataTable));
    }
    
    public void waitForErrorTable() {
        switchTo();
        WebElement waitForTable = new WebDriverWait(driver,DataItems.longWait).until(ExpectedConditions.presenceOfElementLocated(contentLocator));
    }
    
    public void waitForProductInfo() {
        Boolean waitForTitle = new WebDriverWait(driver,DataItems.longWait).until(ExpectedConditions.textToBePresentInElementLocated(productInfoLocator,"Product Information"));
    }
    
    public void waitForInvisibility() {
    	Boolean wait = new WebDriverWait(driver,DataItems.longWait).until(ExpectedConditions.invisibilityOfElementLocated(frameLocator));
    }
    
    public void closeView() {
    	//Press esc
    	Actions pressEsc = new Actions(driver);
    	pressEsc.sendKeys(Keys.ESCAPE).build().perform();
    	//Accept alert
    	Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
    	alert.accept();
    	
    	this.waitForInvisibility();
    	
    }
    
    public Ecomm_OutstandingOrdersPage exitView() {
        //New action to press escape
        Actions pressEsc = new Actions(driver);
        pressEsc.sendKeys(Keys.ESCAPE).build().perform();
        //Accept alert
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Ecomm_OutstandingOrdersPage(driver);
    }
    
    
}
