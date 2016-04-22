
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;

import org.openqa.selenium.Alert;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

    public class Ecomm_MyReportsPage extends WBA_BasePage {
    
    //Locators
    By selectAllButton = By.id("Filter#");
    By createdDateButton = By.id("fields3");
    By poNumberButton = By.id("fields4");
    By articleButton = By.id("fields11");
    By brandButton = By.id("fields12");
    By ticketButton = By.id("fields13");
    By shadeCodeButton = By.id("fields14");
    By invoiceNoButton = By.id("fields26");
    By deliveryNoButton = By.id("fields33");
    By orderNoField = By.id("s2id_filterBulkOrderId");
    By custNameField = By.id("s2id_filterBulkOrderCustomerId");
    By createDateFromField = By.id("filterBulkOrderCreatedFrom");
    By createDateToField = By.id("filterBulkOrderCreatedTo");
    By custCodeField = By.id("filterCustomerCustomerCode");
    By saveMyReportButton = By.cssSelector("#FilterMyReportsForm > div.actions > ul > li:nth-child(1) > input");
    By printButton_SUSST = By.cssSelector("#FilterMyReportsForm > div.actions > ul > li:nth-child(2) > input[type=\"submit\"]");
    By exportButton_SUSST = By.cssSelector("#FilterMyReportsForm > div.actions > ul > li:nth-child(3)");
    By resetButton_SUSST = By.cssSelector("#FilterMyReportsForm > div.actions > ul > li:nth-child(4) > a");

    By printButton_SUMST = By.cssSelector("#FilterMyReportsForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By exportButton_SUMST = By.cssSelector("#FilterMyReportsForm > div.actions > ul > li:nth-child(2)");
    By resetButton_SUMST = By.cssSelector("#FilterMyReportsForm > div.actions > ul > li:nth-child(3) ");
    By flashMessage = By.id("flashMessage");

    By savedReports = By.cssSelector("#\\31");
    By savedRepName = By.cssSelector("#content > div:nth-child(4) > table > tbody > tr:nth-child(1) > td:nth-child(1)"); //first row
    By createNewRep = By.cssSelector("#content > div:nth-child(5) > a");
    By deleteSavedRep = By.cssSelector("#content > div:nth-child(4) > table > tbody > tr:nth-child(1) > td:nth-child(4) > a > span"); //first row

    public Ecomm_MyReportsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public WebElement getSelectAllButton() {
        return driver.findElement(selectAllButton);
    }
    
    public WebElement getCreatedDateButton() {
        return driver.findElement(createdDateButton);
    }
    
    public WebElement getPONumberButton() {
        return driver.findElement(poNumberButton);
    }
    
    public WebElement getArticleButton() {
        return driver.findElement(articleButton);
    }
    
    public WebElement getBrandButton() {
        return driver.findElement(brandButton);
    }
    
    public WebElement getTicketButton() {
        return driver.findElement(ticketButton);
    }
    
    public WebElement getShadeCodeButton() {
        return driver.findElement(shadeCodeButton);
    }
    
    public WebElement getInvoiceNoButton() {
        return driver.findElement(invoiceNoButton);
    }
    
    public WebElement getDeliveryNoButton() {
        return driver.findElement(deliveryNoButton);
    }
    
    public WebElement getOrderNoField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(orderNoField);
    }
    
    public WebElement getCreateFromField() {
        return driver.findElement(createDateFromField);
    }
    
    public WebElement getCreateToField() {
        return driver.findElement(createDateToField);
    }
    
    public WebElement getCustCodeField() {
        return driver.findElement(custCodeField);
    }
    
    public WebElement getSaveMyReportButton() {
        return driver.findElement(saveMyReportButton);
    }
    
    public WebElement getPrintButton() {
        return driver.findElement(printButton_SUMST);
    }
    
    public WebElement getPrintButton_SUSST() {
        return driver.findElement(printButton_SUSST);
    }
    
    public WebElement getExportButton() {
        return driver.findElement(exportButton_SUMST);
    }
    
    public WebElement getExportButton_SUSST() {
        return driver.findElement(exportButton_SUSST);
    }
    
    public WebElement getResetButton() {
        return driver.findElement(resetButton_SUMST);
    }
    
    public WebElement getResetButton_SUSST() {
        return driver.findElement(resetButton_SUSST);
    }
    
    public WebElement getFlashMessage() {
        return driver.findElement(flashMessage);
    }

    public boolean checkSavedReports() {
        try {
            WebElement savedRep = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(savedReports)));
            System.out.println("There are Saved Reports!");
            return true;
        } catch (Exception e) {
            System.out.println("No Saved Reports!");
            return false;
        }
    }

    public void deleteSavedReport(){
        WebElement del = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(savedReports)));
        del.click();
        driver.findElement(deleteSavedRep).click();
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert message:"+alert.getText());
            alert.accept();
            System.out.println("Alert closed!");
        } catch (Exception e) {
            System.out.println("No error(s) displayed");
        }
    }

    public Ecomm_CreateNewReportPage createNewReport(){
        WebElement elem = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(driver.findElement(createNewRep)));
        elem.click();
        return new Ecomm_CreateNewReportPage(driver);
    }

    
    public Ecomm_MyReportsPage setSelectAll() {
        CommonTask.setCheckBox(driver, selectAllButton);
        return this;
    }
    
    public Ecomm_MyReportsPage uncheckSelectAll() {
        CommonTask.uncheckBox(driver, selectAllButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setCreatedDate() {
        CommonTask.setCheckBox(driver, createdDateButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setPONumber() {
        CommonTask.setCheckBoxNoWait(driver, poNumberButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setArticle() {
        CommonTask.setCheckBoxNoWait(driver, articleButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setBrand() {
        CommonTask.setCheckBoxNoWait(driver, brandButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setTicket() {
        CommonTask.setCheckBoxNoWait(driver, ticketButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setShadeCode() {
        CommonTask.setCheckBoxNoWait(driver, shadeCodeButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setInvoiceNo() {
        CommonTask.setCheckBoxNoWait(driver, invoiceNoButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setDeliveryNo() {
        CommonTask.setCheckBoxNoWait(driver, deliveryNoButton);
        return this;
    }
    
    public Ecomm_MyReportsPage setOrderNo(String item) {
        CommonTask.setSearchField(driver,orderNoField,item);
        return this;
    }
    
    public Ecomm_MyReportsPage setCustName(String item) {
        CommonTask.setSearchField(driver,custNameField,item);
        return this;
    }
    
    public Ecomm_MyReportsPage setCustCode(String item) {
        CommonTask.setTextField(driver,custCodeField,item);
        return this;
    }
    
    public Ecomm_OrderViewPage pressPrint() {
        WebElement print = Wait.clickable(driver,printButton_SUMST);
        print.click();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_OrderViewPage pressPrint_SUSST() {
        WebElement print = Wait.clickable(driver,printButton_SUSST);
        print.click();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_SaveReportPage pressSaveReport() {
        WebElement save = Wait.clickable(driver,saveMyReportButton);
        save.click();
        return new Ecomm_SaveReportPage(driver);
    }
 
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement export = Wait.clickable(driver,exportButton_SUMST);
        export.click();
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public Ecomm_MyReportsPage pressReset() {
        WebElement reset = Wait.clickable(driver,resetButton_SUMST);
        reset.click();
        CommonTask.waitForPageLoad(driver);
        Boolean wait2 = Wait.unchecked(driver,selectAllButton);
        return this;
    }
    
    public void waitForElement() {
        WebElement wait = Wait.clickable(driver,selectAllButton);
    }
    
    public void checkFields_SUMST() {
        //Wait for all elements to be clickable 
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement custCode = Wait.clickable(driver,custCodeField);
        WebElement dateFrom = Wait.clickable(driver,createDateFromField);
        WebElement dateTo = Wait.clickable(driver,createDateToField);
        WebElement selectAll = Wait.clickable(driver,selectAllButton);
        WebElement createdDate = Wait.clickable(driver,createdDateButton);
        WebElement PONumber = Wait.clickable(driver,poNumberButton);
        WebElement article = Wait.clickable(driver,articleButton);
        WebElement brand = Wait.clickable(driver,brandButton);
        WebElement ticket = Wait.clickable(driver,ticketButton);
        WebElement shadecode = Wait.clickable(driver,shadeCodeButton);
        WebElement invoiceNo = Wait.clickable(driver,invoiceNoButton);
        WebElement delNo = Wait.clickable(driver,deliveryNoButton);
        WebElement print = Wait.clickable(driver,printButton_SUMST);
        WebElement reset = Wait.clickable(driver,resetButton_SUMST);
        WebElement export = Wait.clickable(driver,exportButton_SUMST);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("My Reports Page: Order No Field not displayed",orderNo.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Customer Name Field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Customer Code Field not displayed",custCode.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Created Date From Field not displayed",dateFrom.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Created Date To Field not displayed",dateTo.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Select All Field not displayed",selectAll.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Created Date Field not displayed",createdDate.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: PO Number Field not displayed",PONumber.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Article Field not displayed",article.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Brand Field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Ticket Field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Shade Code Field not displayed",shadecode.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Invoice No Field not displayed",invoiceNo.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Delivery No Field not displayed",delNo.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Print button not displayed",print.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Reset button not displayed",reset.isDisplayed());
    }
    
    public void checkFields_SUSST() {
        //Wait for all elements to be clickable 
        WebElement orderNo = Wait.clickable(driver,orderNoField);
        WebElement dateFrom = Wait.clickable(driver,createDateFromField);
        WebElement dateTo = Wait.clickable(driver,createDateToField);
        WebElement selectAll = Wait.clickable(driver,selectAllButton);
        WebElement createdDate = Wait.clickable(driver,createdDateButton);
        WebElement PONumber = Wait.clickable(driver,poNumberButton);
        WebElement article = Wait.clickable(driver,articleButton);
        WebElement brand = Wait.clickable(driver,brandButton);
        WebElement ticket = Wait.clickable(driver,ticketButton);
        WebElement shadecode = Wait.clickable(driver,shadeCodeButton);
        WebElement invoiceNo = Wait.clickable(driver,invoiceNoButton);
        WebElement delNo = Wait.clickable(driver,deliveryNoButton);
        WebElement print = Wait.clickable(driver,printButton_SUSST);
        WebElement reset = Wait.clickable(driver,resetButton_SUSST);
        WebElement export = Wait.clickable(driver,exportButton_SUSST);
        WebElement save = Wait.clickable(driver,saveMyReportButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("My Reports Page: Order No Field not displayed",orderNo.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Created Date From Field not displayed",dateFrom.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Created Date To Field not displayed",dateTo.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Select All Field not displayed",selectAll.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Created Date Field not displayed",createdDate.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: PO Number Field not displayed",PONumber.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Article Field not displayed",article.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Brand Field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Ticket Field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Shade Code Field not displayed",shadecode.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Invoice No Field not displayed",invoiceNo.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Delivery No Field not displayed",delNo.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Print button not displayed",print.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Save My Report button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("My Reports Page: Reset button not displayed",reset.isDisplayed());
    }
    
}
