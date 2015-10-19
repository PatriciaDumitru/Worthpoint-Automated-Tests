
package PageObjects;

import static PageObjects.WBA_BasePage.driver;
import static PageObjects.Ecomm_ManualEntryPage.customerNameField;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author ukspjsykes
 */
public class Ecomm_OutstandingOrderDraftPage extends WBA_BasePage {
    
    //Locators
    static By salesOrgLabelLocator = By.cssSelector("#FilterDraftsForm > div.grid_12 > table > tbody > tr:nth-child(1) > th:nth-child(1) > label");
    static By salesOrgFieldLocator = By.cssSelector("#s2id_filterBulkOrderSalesOrgId > a");
    static By custNameLabelLocator = By.cssSelector("#FilterDraftsForm > div.grid_12 > table > tbody > tr:nth-child(2) > th:nth-child(1) > label");
    static By custNameFieldLocator = By.cssSelector("#s2id_filterBulkOrderCustomerId > a");
    static By custNameSearchLocator = By.cssSelector("#select2-drop > div");
    static By custNameResultLocator = By.cssSelector("#select2-drop > ul > li");
    static By orderNoLabelLocator = By.cssSelector("#FilterDraftsForm > div.grid_12 > table > tbody > tr:nth-child(3) > th:nth-child(1) > label");
    static By orderNoFieldLocator = By.cssSelector("#s2id_filterBulkOrderId > a > span.select2-chosen");
    static By orderNoSearchLocator = By.cssSelector("#select2-drop > div > input");
    static By orderNoResultLocator = By.cssSelector("#select2-drop > ul > li.select2-results-dept-0.select2-result.select2-result-selectable.select2-highlighted");
    static By priceAvailLabelLocator = By.cssSelector("#FilterDraftsForm > div.grid_12 > table > tbody > tr:nth-child(4) > th:nth-child(1) > label");
    static By priceAvailFieldLocator = By.cssSelector("#s2id_filterBulkOrderCustomerPriceAvailabilty > a > span.select2-chosen");
    static By custPOLabelLocator = By.cssSelector("#FilterDraftsForm > div.grid_12 > table > tbody > tr:nth-child(1) > th:nth-child(3) > label");
    static By custPOFieldLocator = By.id("filterBulkOrderPoNumber");
    static By brandLabelLocator = By.cssSelector("#FilterDraftsForm > div.grid_12 > table > tbody > tr:nth-child(2) > th:nth-child(3) > label");
    static By brandFieldLocator = By.cssSelector("#s2id_filterBulkOrderLineBrandId > a > span.select2-chosen");
    static By brandSearchLocator = By.cssSelector("#select2-drop > div > input");
    static By brandResultLocator = By.cssSelector("#select2-drop > ul > li.select2-results-dept-0.select2-result.select2-result-selectable.select2-highlighted");
    static By searchButtonLocator = By.cssSelector("#FilterDraftsForm > div.grid_12 > table > tbody > tr:nth-child(5) > td > div > input");
    static By resultTableLocator = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table");
    static By viewButtonLocator = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(3) > a");
    static By editButtonLocator = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(1) > a > span");
    static By cancelButtonLocator = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2) > a");
    static By cancelMessageLocator = By.id("flashMessage");
    static By overlayLocator = By.id("TB_iframeContent");
    static By poNumberCell = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(6)");
    
    public Ecomm_OutstandingOrderDraftPage(WebDriver passedDriver) {
        super(passedDriver);
    }
   
    public String getPONumber() {
        WebElement waitForVis = new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(poNumberCell));
        return driver.findElement(poNumberCell).getText();
    }
    
    public Ecomm_OutstandingOrderDraftPage setCustName(String custName) {       
        
        //Wait for customer name field to be clickable
        WebElement waitToClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameFieldLocator));
        //click field and type customer name
        Actions typeCustomerName = new Actions(driver);
        typeCustomerName.click(driver.findElement(custNameFieldLocator)).build().perform();
        WebElement waitForSearchField = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(custNameSearchLocator));
        typeCustomerName.sendKeys(custName).build().perform();
        //Wait for search result to load
        Boolean waitForResult = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(custNameResultLocator, custName));
        //Press enter
        typeCustomerName.sendKeys(driver.findElement(custNameSearchLocator), Keys.ENTER).build().perform();
        
        return this;
    
    }
    
    public Ecomm_OutstandingOrderDraftPage setOrderNo(String orderNo) {
        
        //Wait for customer name field to be clickable
        WebElement waitToClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderNoFieldLocator));
        //click field and type customer name
        Actions typeCustomerName = new Actions(driver);
        typeCustomerName.click(driver.findElement(orderNoFieldLocator)).build().perform();
        WebElement waitForSearchField = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(orderNoSearchLocator));
        typeCustomerName.sendKeys(orderNo).build().perform();
        //Wait for search result to load
        Boolean waitForResult = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(orderNoResultLocator, orderNo));
        //Press enter
        typeCustomerName.sendKeys(driver.findElement(orderNoSearchLocator), Keys.ENTER).build().perform();
        
        return this;
        
    }
    
    public Ecomm_OutstandingOrderDraftPage setPONumber(String poNumber) {
        //Wait for field
        WebElement waitForField = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(custPOFieldLocator));
        
        Actions clickAndType = new Actions(driver);
        clickAndType.click(driver.findElement(custPOFieldLocator)).build().perform();
        clickAndType.sendKeys(poNumber).build().perform();
        
        return this;
        
    }
    
    public Ecomm_OutstandingOrderDraftPage setBrand(String brand) {
        //Wait for field to load
        WebElement waitForField = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(brandFieldLocator));
        
        Actions clickAndType = new Actions(driver);
        clickAndType.click(driver.findElement(brandFieldLocator)).build().perform();
        WebElement waitForSearch = new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(brandSearchLocator));
        clickAndType.sendKeys(brand).build().perform();
        
        WebElement waitForResult = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(brandResultLocator));
        
        clickAndType.sendKeys(Keys.ENTER).build().perform();
        
        return this;
    }
    
    public Ecomm_OutstandingOrderDraftPage pressSearch() {
        
        //Wait for element to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
        Actions clickSearch = new Actions(driver);
        clickSearch.click(driver.findElement(searchButtonLocator)).build().perform();
        //Wait for update
        WebElement waitForUpdate = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(resultTableLocator));
        
        return this;
    }
    
    public String getOrderNo(int lineNumber) {
        By locator = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+lineNumber+") > td:nth-child(7)");
        return driver.findElement(locator).getText();
    }
    
    public String findDraft(String poNumber) {
        
        String orderNo = "";
        
        for (int i = 1; i <= 10; i++) {
            By poLocator = By.cssSelector("#content > div.tbl-toggle > div > div.scrollTableContainer.scroll-pane > table > tbody:nth-child(2) > tr:nth-child("+i+") > td:nth-child(6)");
            String cellPO = driver.findElement(poLocator).getText();
            if (cellPO.equals(poNumber)) {
                System.out.println("Draft found in outstanding drafts:");
                orderNo = getOrderNo(i);
                System.out.println("Table row: " + i);
                System.out.println("Order No.: " + orderNo);
            }
        }
        
        return orderNo;
        
    }
    
    public Ecomm_OrderViewPage pressView() {
        
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(viewButtonLocator));
        Actions clickView = new Actions(driver);
        clickView.click(driver.findElement(viewButtonLocator)).build().perform();
        
        WebDriver waitForOverlay = new WebDriverWait(driver,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(overlayLocator));
        
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_ManualEntryPage pressEdit() {
        
        //Wait for button and press
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(editButtonLocator));
        Actions clickView = new Actions(driver);
        clickView.click(driver.findElement(editButtonLocator)).build().perform();
        
        return new Ecomm_ManualEntryPage(driver);
        
    }
    
    public Ecomm_OutstandingOrderDraftPage closeView() {

        //Press esc and confirm alert
        Actions pressEsc = new Actions(driver);
        pressEsc.sendKeys(Keys.ESCAPE).build().perform();
        
        //Handle alert
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        //Wait for overlay to disappear
        //boolean waitForPage = new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOfElementLocated(overlayLocator));
        
        return new Ecomm_OutstandingOrderDraftPage(driver);
        
    }
    
    public Ecomm_OutstandingOrderDraftPage pressCancel() {
        
        //Wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(cancelButtonLocator));
        Actions clickView = new Actions(driver);
        clickView.click(driver.findElement(cancelButtonLocator)).build().perform();
        
        //Confirm alert
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        //Wait for message to appear
        WebElement waitForMessage = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(cancelMessageLocator));
        
        return this;
    }
    
}
