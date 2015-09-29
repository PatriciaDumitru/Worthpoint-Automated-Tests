
package PageObjects;

import AutomationFramework.CommonTask;
import static PageObjects.BasePage.driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FeedbackCompletedPage_CCE extends BasePage {
    
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");
    By salesOrg = By.id("s2id_filterSampleOrderSalesOrgId");
    By hub = By.id("s2id_filterSampleOrderHubId");
    By orderNoField = By.id("s2id_filterSampleOrderLineOrderId");
    By custNameField = By.id("s2id_filterSampleOrderCustomerId");
    By requesterField = By.id("s2id_filterSampleOrderRequesterId");
    By shadeCodeField = By.id("s2id_filterSampleOrderLineShadeId");
    By orderDateFromField = By.id("filterSampleOrderLineUpdatedTo");
    By orderDateToField = By.id("filterSampleOrderCreatedTo");
    By statusField = By.id("s2id_filterSampleOrderLineIsAccepted");
    By rematchField = By.id("s2id_filterSampleOrderLineIsRematchRequired");
    By fbDateFromField = By.id("filterSampleOrderLineUpdatedFrom");
    By fbDateToField = By.id("filterSampleOrderLineUpdatedTo");
    By listOrdersButton = By.cssSelector("#FilterFeedbackCompletedForm > div.actions > ul > li:nth-child(1)");
    By resetButton = By.cssSelector("#FilterFeedbackCompletedForm > div.actions > ul > li:nth-child(2)");
    By exportMenu = By.id("export-menu");
    By csvButton = By.cssSelector("#export-menu > ul > li:nth-child(1)");
    By txtButton = By.cssSelector("#export-menu > ul > li:nth-child(2)");
    By xlsButton = By.cssSelector("#export-menu > ul > li:nth-child(3)");
    By xlsxButton = By.cssSelector("#export-menu > ul > li:nth-child(4)");
    By filterForm = By.id("FilterFeedbackCompletedForm");
    
    
    public FeedbackCompletedPage_CCE(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public String getTitle() {
        WebElement waitForTitle = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(breadcrumb));
        
        return driver.findElement(breadcrumb).getText();
    }
    
    public WebElement getSalesOrgField() {
        return driver.findElement(salesOrg);
    }
    
    public WebElement getHubField() {
        return driver.findElement(hub);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getRequesterField() {
        return driver.findElement(requesterField);
    }
    
    public WebElement getShadeCodeField() {
        return driver.findElement(shadeCodeField);
    }
    
    public WebElement getOrderFromField() {
        return driver.findElement(orderDateFromField);
    }
    
    public WebElement getOrderToField() {
        return driver.findElement(orderDateToField);
    }
    
    public WebElement getStatusField() {
        return driver.findElement(statusField);
    }
    
    public WebElement getRematchField() {
        return driver.findElement(rematchField);
    }
    
    public WebElement getFbFromField() {
        return driver.findElement(fbDateFromField);
    }
    
    public WebElement getFbToField() {
        return driver.findElement(fbDateToField);
    }
    
    public WebElement getListOrdersButton() {
        return driver.findElement(listOrdersButton);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton);
    }
    
    public WebElement getExportMenu() {
        return driver.findElement(exportMenu);
    }
    
    public FeedbackCompletedPage_CCE setSalesOrg(String item) {
        CommonTask.setSearchField(driver, salesOrg, item);
        
        return this;
    }
    
    public FeedbackCompletedPage_CCE setHub(String item) {
        CommonTask.setSearchField(driver, hub, item);
        
        return this;
    }
    
    public FeedbackCompletedPage_CCE setOrderNo(String item) {
        CommonTask.setSearchField(driver, orderNoField, item);
        
        return this;
    }
    
    public FeedbackCompletedPage_CCE setRequester(String item) {
        CommonTask.setSearchField(driver, requesterField, item);
        
        return this;
    }
    
    public FeedbackCompletedPage_CCE setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        
        return this;
    }
    
    public FeedbackCompletedPage_CCE setShadeCode(String item) {
        CommonTask.setSearchField(driver, shadeCodeField, item);
        
        return this;
    }
    
    public FeedbackCompletedPage_CCE setOrderDateFrom(String item) {
        CommonTask.setSearchField(driver, orderDateFromField, item);
        
        return this;
    }
    
    public FeedbackCompletedPage_CCE setOrderDateTo(String item) {
        CommonTask.setSearchField(driver, orderDateToField, item);
        
        return this;
    }
    
    public FeedbackCompletedPage_CCE setStatus(String item) {
        CommonTask.setSearchField(driver, statusField, item);
        
        return this;
    }
    
    public FeedbackCompletedPage_CCE setRematch(String item) {
        CommonTask.setSearchField(driver, rematchField, item);
        
        return this;
    }
    
    public FeedbackCompletedPage_CCE setFbDateFrom(String item) {
        CommonTask.setSearchField(driver, fbDateFromField, item);
        
        return this;
    }
    
    public FeedbackCompletedPage_CCE setFbDateToName(String item) {
        CommonTask.setSearchField(driver, fbDateToField, item);
        
        return this;
    }
    
    public FeedbackCompletedPage_CCE pressListOrders() {
        //Wait for element
        WebElement waitForButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        
        driver.findElement(filterForm).submit();
        
        return this;
    }
    
    public FeedbackCompletedPage_CCE pressReset() {
        //Wait for element
        WebElement waitForButton = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        
        driver.findElement(resetButton).click();
        
        return this;  
    }
    
    public FeedbackCompletedPage_CCE exportAs(String type) {
        switch(type){
            case "csv": pressCsv(); break;
            case "txt": pressTxt(); break;
            case "xlsx": pressXlsx(); break;
            case "xls": pressXls(); break;
        }
        
        return this;
    }
    
    public void pressCsv() {
        //Wait for export menu
        WebElement waitForMenu = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportMenu));
        
        //Click menu and select csv
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(exportMenu)).build().perform();
        
        //Wait for subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(csvButton));
        
        //Click tab
        driver.findElement(csvButton).click();
       
        
    }
    
    public void pressTxt() {
        //Wait for export menu
        WebElement waitForMenu = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportMenu));
        
        //Click menu and select csv
        driver.findElement(exportMenu).click();
        
        //Wait for subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(txtButton));
        
        //Click tab
        driver.findElement(txtButton).click();
       
        
    }
    
    public void pressXls() {
        //Wait for export menu
        WebElement waitForMenu = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportMenu));
        
        //Click menu and select csv
        driver.findElement(exportMenu).click();
        
        //Wait for subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(xlsButton));
        
        //Click tab
        driver.findElement(xlsButton).click();
       
        
    }
    
    public void pressXlsx() {
        //Wait for export menu
        WebElement waitForMenu = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportMenu));
        
        //Click menu and select csv
        driver.findElement(exportMenu).click();
        
        //Wait for subtab
        WebElement waitForSubtab = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(xlsxButton));
        
        //Click tab
        driver.findElement(xlsxButton).click();
       
        
    }
    
    public void checkFields() {
        //Wait for all to be clickable
        WebElement waitForSalesOrg = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(salesOrg));
        WebElement waitForHub = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(hub));
        WebElement waitForOrderNo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderNoField));
        WebElement waitForCustName = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement waitForRequester = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requesterField));
        WebElement waitForShadeCode = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shadeCodeField));
        WebElement waitForOrderDateFrom = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderDateFromField));
        WebElement waitForOrderDateTo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(orderDateToField));
        WebElement waitForStatus = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(statusField));
        WebElement waitForRematch = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(rematchField));
        WebElement waitForFbFrom = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fbDateFromField));
        WebElement waitForFbTo = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(fbDateToField));
        WebElement waitForListOrders = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(listOrdersButton));
        WebElement waitForReset = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement waitForExport = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(exportMenu));
        
        //Assert all elements are displayed
        Assert.assertTrue("Feedback Completed Page: Sales Organisation Field not displayed correctly",getSalesOrgField().isDisplayed());
        Assert.assertTrue("Feedback Completed Page: Hub Field not displayed correctly",getHubField().isDisplayed());
        Assert.assertTrue("Feedback Completed Page: Order No Field not displayed correctly",getOrderNoField().isDisplayed());
        Assert.assertTrue("Feedback Completed Page: Customer Name Field not displayed correctly",getCustNameField().isDisplayed());
        Assert.assertTrue("Feedback Completed Page: Requester Field not displayed correctly",getRequesterField().isDisplayed());
        Assert.assertTrue("Feedback Completed Page: Shade Code Field not displayed correctly",getShadeCodeField().isDisplayed());
        Assert.assertTrue("Feedback Completed Page: Order Date From Field not displayed correctly",getOrderFromField().isDisplayed());
        Assert.assertTrue("Feedback Completed Page: Order Date To Field not displayed correctly",getOrderToField().isDisplayed());
        Assert.assertTrue("Feedback Completed Page: Status Field not displayed correctly",getStatusField().isDisplayed());
        Assert.assertTrue("Feedback Completed Page: Rematch Field not displayed correctly",getRematchField().isDisplayed());
        Assert.assertTrue("Feedback Completed Page: Feedback Date From Field not displayed correctly",getFbFromField().isDisplayed());
        Assert.assertTrue("Feedback Completed Page: Feedback Date To Field not displayed correctly",getFbToField().isDisplayed());
        Assert.assertTrue("Feedback Completed Page: List Orders Button not displayed correctly",getListOrdersButton().isDisplayed());
        Assert.assertTrue("Feedback Completed Page: Reset Button not displayed correctly",getResetButton().isDisplayed());
        Assert.assertTrue("Feedback Completed Page: Export Menu not displayed correctly",getExportMenu().isDisplayed());
               
    }
    
    
    
}
