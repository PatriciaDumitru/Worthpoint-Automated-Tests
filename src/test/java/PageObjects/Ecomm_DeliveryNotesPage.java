
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.Wait;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Ecomm_DeliveryNotesPage extends WBA_BasePage {
    
    //Locators
    By custNameField = By.xpath("//*[@id=\"s2id_filterSapCcdeliverynoteCustomerId\"]/a");//By.id("s2id_filterSapccDeliverynoteCustomerId");
    By delDateFromField =By.xpath("//*[@id=\"filterSapCcdeliverynoteDeliveryDateFrom\"]");// By.id("filterSapccDeliverynoteDeliveryDateFrom");
    By delDateToField = By.xpath("//*[@id=\"filterSapCcdeliverynoteDeliveryDateTo\"]");//By.id("filterSapccDeliverynoteDeliveryDateTo");
    By matNumField = By.xpath("//*[@id=\"filterSapCcdeliverynoteMaterial\"]"); //By.id("filterSapccDeliverynoteMaterial");
    By brandField = By.id("filterSapccDeliverynoteBrandId");
    By lengthField = By.id("filterSapccDeliverynoteLengthId");
    By requesterField = By.xpath("//*[@id=\"filterSapCcdeliverynoteRequesterId\"]");//By.id("filterRequesterId");
    By delNotesField = By.xpath("//*[@id=\"filterSapCcdeliverynoteDeliveryNo\"]");//By.id("filterSapccDeliverynoteDeliveryNo");
    By yourMatNumField = By.xpath("//*[@id=\"filterSapCcdeliverynoteCustomerMaterialNo\"]");//By.id("filterSapccDeliverynoteCustomerMaterialNo");
    By ticketField = By.id("filterSapccDeliverynoteTicketId");
    By custPOField = By.xpath("//*[@id=\"filterSapCcdeliverynotePoNumber\"]");//By.id("filterSapccDeliverynotePoNumber");
    By searchButton = By.xpath("//*[@id=\"FilterDeliverynoteForm\"]/div[2]/table/tbody/tr[8]/td/div/input");//By.cssSelector("#FilterDeliverynoteForm > div.grid_12 > table > tbody > tr:nth-child(8) > td > div > input");
    By clearButton = By.xpath("//*[@id=\"FilterDeliverynoteForm\"]/div[2]/table/tbody/tr[8]/td/a");//By.cssSelector("#FilterDeliverynoteForm > div.grid_12 > table > tbody > tr:nth-child(8) > td > a");
    By printButton = By.xpath("//*[@id=\"content\"]/div[2]/div/div[2]/table/tbody/tr[3]/td[1]/a/img");//By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(3) > td:nth-child(1) > a");
    By viewButton = By.xpath("//*[@id=\"content\"]/div[2]/div/div[2]/table/tbody/tr[3]/td[2]/a/span");//By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(3) > td:nth-child(2) > a");
    By exportButton = By.xpath("//*[@id=\"content\"]/div[2]/div/div[2]/div/a");//By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > a");

    By tableHeaders = By.xpath("//*[@id=\"content\"]/div[2]/div/div[2]/table/tbody/tr[1]/th");//*[@id="content"]/div[2]/div/div[2]/table/tbody/tr[1]/th[1]
    public By noRecords = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > div");
    public String requesterPart1 = "#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(";
    public String requesterPart2 = ") > td:nth-child(5)";
    
    public Ecomm_DeliveryNotesPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(custNameField);
    }
    
    public WebElement getDelDateFromField() {
        return driver.findElement(delDateFromField);
    }
    
    public WebElement getDelDateToField() {
        return driver.findElement(delDateToField);
    }
    
    public WebElement getMatNumField() {
        return driver.findElement(matNumField);
    }
    
    public WebElement getBrandField() {
        return driver.findElement(brandField);
    }
    
    public WebElement getLengthField() {
        return driver.findElement(lengthField);
    }
    
    public WebElement getRequesterField() {
        return driver.findElement(requesterField);
    }
    
    public WebElement getDelNotesField() {
        return driver.findElement(delNotesField);
    }
    
    public WebElement getYourMatNumField() {
        return driver.findElement(yourMatNumField);
    }
    
    public WebElement getTicketField() {
        return driver.findElement(ticketField);
    }
    
    public WebElement getCustPOField() {
        return driver.findElement(custPOField);
    }
    
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
    
    public WebElement getClearButton() {
        return driver.findElement(clearButton);
    }
    
    public WebElement getViewButton() {
        return driver.findElement(viewButton);
    }
    
    public WebElement getPrintButton() {
        return driver.findElement(printButton);
    }
    
    public WebElement getExportButton() {
        return driver.findElement(exportButton);
    }
    
    public Ecomm_DeliveryNotesPage setCustName(String item) {
        CommonTask.setSearchField(driver, custNameField, item);
        return this;
    }
    
    public Ecomm_DeliveryNotesPage setDelNoteNo(String item) {
        CommonTask.setTextField(driver, delNotesField, item);
        return this;
    }
    
    public Ecomm_DeliveryNotesPage setRequester(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, requesterField, item);
        return this;
    }
    
    public Ecomm_DeliveryNotesPage setCustPO(String item) throws InterruptedException {
        CommonTask.setInputField(driver, custPOField, item);
        return this;
    }

    public String getClearButtonLabel() {
        WebElement button = Wait.clickable(driver, clearButton);
        String label = driver.findElement(clearButton).getText();
        return label;
    }

    public StringBuilder getTableHeader() {
        WebElement button = Wait.clickable(driver,searchButton);
        int countColumn = driver.findElements(tableHeaders).size();
        System.out.println("No of columns in header:"+countColumn);
        StringBuilder header = new StringBuilder();
        for (int i=0;i<countColumn;i++){
            By tableHeader = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(1) > th:nth-child("+(i+1)+")");
            header = header.append(driver.findElement(tableHeader).getText());
            header = header.append("|");
        }
        return header;
    }
    
    public Ecomm_DeliveryNotesPage pressSearch() {
        WebElement search = Wait.clickable(driver,searchButton);
        search.click();
        return this;
    }
    
    public Ecomm_DeliveryNotesPage pressReset() {
        WebElement reset = Wait.clickable(driver, clearButton);
        reset.click();
        return this;
    }
    
    public Ecomm_OrderViewPage pressView() {
        WebElement view = Wait.clickable(driver,viewButton);
        view.click();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_OrderViewPage pressPrint() {
        WebElement print = Wait.clickable(driver,printButton);
        print.click();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement export = Wait.clickable(driver,exportButton);
        export.click();
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public void checkFields_SUMST() {
        //Wait for all items to be clickable
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement delFrom = Wait.clickable(driver,delDateFromField);
        WebElement delTo = Wait.clickable(driver,delDateToField);
        WebElement matNum = Wait.clickable(driver,matNumField);
        //WebElement brand = Wait.clickable(driver,brandField);
        //WebElement length = Wait.clickable(driver,lengthField);
        WebElement requester = Wait.clickable(driver,requesterField);
        WebElement delNotes = Wait.clickable(driver,delNotesField);
        WebElement yourMatNum = Wait.clickable(driver,yourMatNumField);
        //WebElement ticket = Wait.clickable(driver,ticketField);
        WebElement custPO = Wait.clickable(driver,custPOField);
        WebElement search = Wait.clickable(driver,searchButton);
        WebElement reset = Wait.clickable(driver, clearButton);
        WebElement view = Wait.clickable(driver,viewButton);
        WebElement print = Wait.clickable(driver,printButton);
        WebElement export = Wait.clickable(driver,exportButton);
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Delivery Notes Page: Customer Name Field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Delivery Date From Field not displayed correctly",delFrom.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Delivery Date To Field not displayed correctly",delTo.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Material Number Field not displayed correctly",matNum.isDisplayed());
        //AssertJUnit.assertTrue("Delivery Notes Page: Brand Field not displayed correctly",brand.isDisplayed());
        //AssertJUnit.assertTrue("Delivery Notes Page: Length Field not displayed correctly",length.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Requester Field not displayed correctly",requester.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Delivery Notes Field not displayed correctly",delNotes.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Your Material Number Field not displayed correctly",yourMatNum.isDisplayed());
        //AssertJUnit.assertTrue("Delivery Notes Page: Ticket Field not displayed correctly",ticket.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Customer PO Field not displayed correctly",custPO.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Search Button not displayed correctly",search.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Reset Button not displayed correctly",reset.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: View Button not displayed correctly",view.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Print Button not displayed correctly",print.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Export Button not displayed correctly",export.isDisplayed());
    
    }
    
    public void checkFields_SUSST() {
        //Wait for all items to be clickable
        WebElement delFrom = Wait.clickable(driver,delDateFromField);
        WebElement delTo = Wait.clickable(driver,delDateToField);
        WebElement matNum = Wait.clickable(driver,matNumField);
        //WebElement brand = Wait.clickable(driver,brandField);
        //WebElement length = Wait.clickable(driver,lengthField);
        WebElement delNotes = Wait.clickable(driver,delNotesField);
        WebElement yourMat = Wait.clickable(driver,yourMatNumField);
        //WebElement ticket = Wait.clickable(driver,ticketField);
        WebElement custPO = Wait.clickable(driver,custPOField);
        WebElement search = Wait.clickable(driver,searchButton);
        WebElement reset = Wait.clickable(driver, clearButton);
        WebElement view = Wait.clickable(driver,viewButton);
        WebElement print = Wait.clickable(driver,printButton);
        WebElement export = Wait.clickable(driver,exportButton);
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Delivery Notes Page: Delivery Date From Field not displayed correctly",delFrom.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Delivery Date To Field not displayed correctly",delTo.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Material Number Field not displayed correctly",matNum.isDisplayed());
        //AssertJUnit.assertTrue("Delivery Notes Page: Brand Field not displayed correctly",brand.isDisplayed());
        //AssertJUnit.assertTrue("Delivery Notes Page: Length Field not displayed correctly",length.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Delivery Notes Field not displayed correctly",delNotes.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Your Material Number Field not displayed correctly",yourMat.isDisplayed());
        //AssertJUnit.assertTrue("Delivery Notes Page: Ticket Field not displayed correctly",ticket.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Customer PO Field not displayed correctly",custPO.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Search Button not displayed correctly",search.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Reset Button not displayed correctly",reset.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: View Button not displayed correctly",view.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Print Button not displayed correctly",print.isDisplayed());
        AssertJUnit.assertTrue("Delivery Notes Page: Export Button not displayed correctly",export.isDisplayed());
    }
}
