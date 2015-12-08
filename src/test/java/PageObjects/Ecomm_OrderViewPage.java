package PageObjects;

import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
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
    static By ftDataTable = By.className("frm");
    static By availableQtyCell = By.cssSelector("#thickbox > tbody > tr:nth-child(2) > td:nth-child(1)"); //Available qty cell on product availability check page
    
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
        WebElement cell = Wait.visible(driver,COError);
        return cell.getText();
    }
    
    public String getAvailableQty() {
        try {
            WebElement qtyCell = Wait.visible(driver,availableQtyCell);
            return qtyCell.getText();
        } catch (Exception e) {
            return "Stock Unavailable";
        }
        
    }
    
    public void switchTo() {
    	//This method is used to set focus to the overlay which appears. Elements on the view overlay cannot be accessed until the focus is switched to the frame
        WebDriver wait = Wait.frame(driver,frameLocator);
    }
    
    public void pressPrint() {
    	switchTo();
    	WebElement print = Wait.clickable(driver,printButton);
    	if (DataItems.printingEnabled) {
        	print.click();
        	System.out.println("Item sent to printer");
    	} else {
    		System.out.println("Printing is disabled, item was not sent to printer");
    		this.closeView();
                this.waitForInvisibility();
    	}

    }
    
    public void waitForContent() {
    	WebElement wait = Wait.visible(driver,frameLocator);
    }
    
    public void waitForFTData() {
        WebElement wait = Wait.visible(driver,ftDataTable);
    }
    
    public void waitForErrorTable() {
        switchTo();
        WebElement wait = Wait.presence(driver,contentLocator);
    }
    
    public void waitForProductInfo() {
        Boolean waitForTitle = Wait.textPresent(driver,productInfoLocator,"Product Information");
    }
    
    public void waitForInvisibility() {
    	Boolean wait = Wait.invisible(driver,frameLocator);
    }
    
    public void closeView() {
    	//Press esc
    	Actions pressEsc = new Actions(driver);
    	pressEsc.sendKeys(Keys.ESCAPE).build().perform();
    	//Accept alert
    	Alert alert = Wait.alert(driver);
    	alert.accept();
    	
    	this.waitForInvisibility();
    	
    }
    
    public Ecomm_OutstandingOrdersPage exitView() {
        //New action to press escape
        Actions pressEsc = new Actions(driver);
        pressEsc.sendKeys(Keys.ESCAPE).build().perform();
        //Accept alert
        Alert alert = Wait.alert(driver);
        alert.accept();
        
        return new Ecomm_OutstandingOrdersPage(driver);
    }

}
