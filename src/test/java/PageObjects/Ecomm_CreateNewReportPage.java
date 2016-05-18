package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Stefan on 21.04.2016.
 */
public class Ecomm_CreateNewReportPage extends WBA_BasePage {
    //Locators
    By breadcrumb = By.cssSelector("#content > h2");

    By creationDateFrom = By.cssSelector("#filterBulkOrderCreatedFrom");
    By creationDateTo = By.cssSelector("#filterBulkOrderCreatedTo");
    By selectDateRange = By.cssSelector("#filterBulkOrderDaterange");
    By selectReportCriteria = By.cssSelector("#filterBulkOrderReportcriteria");
    By saveReportButton = By.cssSelector("#FilterMyReportsForm > div.actions > ul > li.btn_actions.save > input[type=\"submit\"]");
    By exportButton = By.cssSelector("#FilterMyReportsForm > div.actions > ul > li.btn_actions.export > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#FilterMyReportsForm > div.actions > ul > li.btn_actions.cancel > a");
    By flashMessage = By.cssSelector("#flashMessage");

    //Custom Fields locators
    By createdDate = By.cssSelector("#fields3");
    By PONumber = By.cssSelector("#fields4");
    By orderStatus = By.cssSelector("#fields5");
    By custNameField = By.cssSelector("#fields38");
    By requester = By.cssSelector("#fields40");
    By style = By.cssSelector("#fields44");
    By buyerSalesOrderNo = By.cssSelector("#fields45");
    By netValue = By.cssSelector("#fields46");
    By orderSource = By.cssSelector("#fields47");
    By custAccess = By.cssSelector("#fields48");

    By article = By.cssSelector("#fields11");
    By brand = By.cssSelector("#fields12");
    By ticket = By.cssSelector("#fields13");
    By length = By.cssSelector("#fields42");
    By finish = By.cssSelector("#fields43");
    By shadeCode = By.cssSelector("#fields14");
    By orderedQty = By.cssSelector("#fields41");
    By adjustedQty = By.cssSelector("#fields15");
    By yourMatNo = By.cssSelector("#fields17");
    By unitOfMeasure = By.cssSelector("#fields18");
    By value = By.cssSelector("#fields19");
    By estimDelivDate = By.cssSelector("#fields20");
    By deliveryDate = By.cssSelector("#fields21");
    By prodStyleNo = By.cssSelector("#fields49");
    By otherInfo = By.cssSelector("#fields50");
    By deliveryStatus = By.cssSelector("#fields37");

    By sapOrderNo = By.cssSelector("#fields23");

    By invoiceNo = By.cssSelector("#fields26");
    By invoiceCrDate = By.cssSelector("#fields27");
    By payDate = By.cssSelector("#fields28");
    By invoiceStatus = By.cssSelector("#fields29");
    By invoiceValue = By.cssSelector("#fields30");
    By currency = By.cssSelector("#fields31");
    By invoicePayDueDate = By.cssSelector("#fields51");

    By deliveryNo = By.cssSelector("#fields33");
    By deliveryLineNo = By.cssSelector("#fields34");
    By deliveryQty = By.cssSelector("#fields36");

    //Save Report
    By reportName = By.cssSelector("#variant");
    By saveBtn = By.cssSelector("#submit1");



    public Ecomm_CreateNewReportPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getBreadcrumb (){return driver.findElement(breadcrumb);}

    public WebElement getSelectDateRange (){return driver.findElement(selectDateRange);}

    public WebElement getSelectReportCriteria(){return driver.findElement(selectReportCriteria);}

    public void setCreationDateFrom (String item) {
        WebElement elem = Wait.visible(driver,creationDateFrom);
        elem.sendKeys(item);
    }

    public void setCreationDateTo (String item) {
        WebElement elem = Wait.visible(driver,creationDateTo);
        elem.sendKeys(item);
    }

    public void selectDateRange (String item) {
        CommonTask.setDropDownField(driver,selectDateRange, item);
    }

    public void selectRepCriteria (String item) {
        CommonTask.setDropDownField(driver, selectReportCriteria, item);
    }

    /**
     * Methods for Set Checkboxes in Custom Fields
     */

    //Orders
    public Ecomm_CreateNewReportPage setCreatedDate() {
        CommonTask.setCheckBox(driver, createdDate);
        return this;
    }

    public Ecomm_CreateNewReportPage setPONumber() {
        CommonTask.setCheckBoxNoWait(driver, PONumber);
        return this;
    }

    public Ecomm_CreateNewReportPage setOrderStatus() {
        CommonTask.setCheckBoxNoWait(driver, orderStatus);
        return this;
    }

    public Ecomm_CreateNewReportPage setcustName() {
        CommonTask.setCheckBoxNoWait(driver, custNameField);
        return this;
    }

    public Ecomm_CreateNewReportPage setReqester() {
        CommonTask.setCheckBoxNoWait(driver, requester);
        return this;
    }

    public Ecomm_CreateNewReportPage setStyle() {
        CommonTask.setCheckBoxNoWait(driver, style);
        return this;
    }

    public Ecomm_CreateNewReportPage setBuyerSalesOrderNo(){
        CommonTask.setCheckBox(driver, buyerSalesOrderNo);
        return this;
    }

    public Ecomm_CreateNewReportPage setNetValue(){
        CommonTask.setCheckBox(driver, netValue);
        return this;
    }

    public Ecomm_CreateNewReportPage setOrderSource(){
        CommonTask.setCheckBox(driver, orderSource);
        return this;
    }

    public Ecomm_CreateNewReportPage setCustAccess(){
        CommonTask.setCheckBox(driver, custAccess);
        return this;
    }

    //Order Lines
    public Ecomm_CreateNewReportPage setArticle() {
        CommonTask.setCheckBoxNoWait(driver, article);
        return this;
    }

    public Ecomm_CreateNewReportPage setBrand() {
        CommonTask.setCheckBoxNoWait(driver, brand);
        return this;
    }

    public Ecomm_CreateNewReportPage setTicket() {
        CommonTask.setCheckBoxNoWait(driver, ticket);
        return this;
    }

    public Ecomm_CreateNewReportPage setLength() {
        CommonTask.setCheckBoxNoWait(driver, length);
        return this;
    }

    public Ecomm_CreateNewReportPage setFinish() {
        CommonTask.setCheckBoxNoWait(driver, finish);
        return this;
    }

    public Ecomm_CreateNewReportPage setShadeCode() {
        CommonTask.setCheckBoxNoWait(driver, shadeCode);
        return this;
    }

    public Ecomm_CreateNewReportPage setOrderedQty() {
        CommonTask.setCheckBoxNoWait(driver, orderedQty);
        return this;
    }

    public Ecomm_CreateNewReportPage setAdjustedQty() {
        CommonTask.setCheckBoxNoWait(driver, adjustedQty);
        return this;
    }

    public Ecomm_CreateNewReportPage setYourMatNo() {
        CommonTask.setCheckBoxNoWait(driver, yourMatNo);
        return this;
    }

    public Ecomm_CreateNewReportPage setUnitOfMeasure() {
        CommonTask.setCheckBoxNoWait(driver, unitOfMeasure);
        return this;
    }

    public Ecomm_CreateNewReportPage setValue() {
        CommonTask.setCheckBoxNoWait(driver, value);
        return this;
    }

    public Ecomm_CreateNewReportPage setEstimatedDelivDate() {
        CommonTask.setCheckBoxNoWait(driver, estimDelivDate);
        return this;
    }

    public Ecomm_CreateNewReportPage setDeliveryDate() {
        CommonTask.setCheckBoxNoWait(driver, deliveryDate);
        return this;
    }

    public Ecomm_CreateNewReportPage setProdStyleNo() {
        CommonTask.setCheckBoxNoWait(driver, prodStyleNo);
        return this;
    }

    public Ecomm_CreateNewReportPage setOtherInfo() {
        CommonTask.setCheckBoxNoWait(driver, otherInfo);
        return this;
    }

    public Ecomm_CreateNewReportPage setDeliveryStatus() {
        CommonTask.setCheckBoxNoWait(driver, deliveryStatus);
        return this;
    }

    //SAP Order

    public Ecomm_CreateNewReportPage setSAPOrderNo() {
        CommonTask.setCheckBoxNoWait(driver, sapOrderNo);
        return this;
    }

    //Invoices

    public Ecomm_CreateNewReportPage setInvoiceNo() {
        CommonTask.setCheckBoxNoWait(driver, invoiceNo);
        return this;
    }

    public Ecomm_CreateNewReportPage setInvoiceCreatedDate() {
        CommonTask.setCheckBoxNoWait(driver, invoiceCrDate);
        return this;
    }

    public Ecomm_CreateNewReportPage setPaymentDate() {
        CommonTask.setCheckBoxNoWait(driver, payDate);
        return this;
    }

    public Ecomm_CreateNewReportPage setInvoiceStatus() {
        CommonTask.setCheckBoxNoWait(driver, invoiceStatus);
        return this;
    }

    public Ecomm_CreateNewReportPage setInvoiceValue() {
        CommonTask.setCheckBoxNoWait(driver, invoiceValue);
        return this;
    }

    public Ecomm_CreateNewReportPage setCurrency() {
        CommonTask.setCheckBoxNoWait(driver, currency);
        return this;
    }

    public Ecomm_CreateNewReportPage setInvoicePayDueDate() {
        CommonTask.setCheckBoxNoWait(driver, invoicePayDueDate);
        return this;
    }

    //Delivery Notes

    public Ecomm_CreateNewReportPage setDeliveryNo() {
        CommonTask.setCheckBoxNoWait(driver, deliveryNo);
        return this;
    }

    public Ecomm_CreateNewReportPage setDeliveryLineNo() {
        CommonTask.setCheckBoxNoWait(driver, deliveryLineNo);
        return this;
    }

    public Ecomm_CreateNewReportPage setDelivereyQty() {
        CommonTask.setCheckBoxNoWait(driver, deliveryQty);
        return this;
    }

    //Bottom buttons

    public Ecomm_SaveReportPage pressSaveReport() {
        WebElement save = Wait.clickable(driver,saveReportButton);
        save.click();
        return new Ecomm_SaveReportPage(driver);
    }

    public Ecomm_ExportDownloadPage pressExport() {
        WebElement export = Wait.clickable(driver,exportButton);
        export.click();
        return new Ecomm_ExportDownloadPage(driver);
    }

    public Ecomm_AdvancedReportsPage pressCancel() {
        WebElement cancel = Wait.clickable(driver,cancelButton);
        cancel.click();
        CommonTask.waitForPageLoad(driver);
        return new Ecomm_AdvancedReportsPage(driver);
    }

    //Frequently used checkboxes

    public void selectAllFromOrders (){
        setCreatedDate();
        setPONumber();
        setOrderStatus();
        setcustName();
        setReqester();
        setStyle();
        setBuyerSalesOrderNo();
        setNetValue();
        setOrderSource();
        setCustAccess();
    }

    public void selectAllFromOrderLines(){
        setArticle();
        setBrand();
        setTicket();
        setLength();
        setFinish();
        setShadeCode();
        setOrderedQty();
        setAdjustedQty();
        setYourMatNo();
        setUnitOfMeasure();
        setValue();
        setEstimatedDelivDate();
        setDeliveryDate();
        setProdStyleNo();
        setOtherInfo();
        setDeliveryStatus();
    }

    public void selectAllFromInvoices(){
        setInvoiceNo();
        setInvoiceCreatedDate();
        setPaymentDate();
        setInvoiceStatus();
        setInvoiceValue();
        setCurrency();
        setInvoicePayDueDate();
    }

    public void selectAllDeliveryNotes(){
        setDeliveryNo();
        setDeliveryLineNo();
        setDelivereyQty();
    }

    public void selectSAPOrder(){
        WebElement elem = Wait.clickable(driver,sapOrderNo);
        setSAPOrderNo();
    }

    //Save Report pop-up
    public void inputReportName(String text){
        WebElement element = Wait.clickable(driver,reportName);
        element.sendKeys(text);
    }

    public void saveRep(){
        WebElement element = Wait.clickable(driver,saveBtn);
        element.click();
    }

    public String getFlashMessageText(){
        WebElement element = Wait.visible(driver,flashMessage);
        return element.getText();
    }

}
