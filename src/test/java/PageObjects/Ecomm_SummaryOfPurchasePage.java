
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.Wait;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Ecomm_SummaryOfPurchasePage extends WBA_BasePage {
    
    //Locators
    By custNameField = By.xpath("//*[@id=\"s2id_filterSapCcpurchaseCustomerId\"]");//By.id("s2id_filterSapccPurchaseCustomerId");
    By custPOField = By.xpath("//*[@id=\"filterSapCcpurchasePoNumber\"]");//By.id("filterSapccPurchasePoNumber");
    By matNumField = By.id("filterSapccPurchaseMaterial");
    By brandField = By.id("filterSapccPurchaseBrandId");
    By lengthField = By.id("filterSapccPurchaseLengthId");
    By salesOrgName = By.xpath("//*[@id=\"s2id_filterSapCcpurchaseSalesOrgId\"]/ul");
    By customerCode = By.xpath("//*[@id=\"filterSapCcpurchaseCustomerCode\"]");
    By ecommOrderNo = By.xpath("//*[@id=\"filterSapCcpurchaseOrderNumber\"]");
    By payerName = By.xpath("//*[@id=\"filterSapCcpurchasePayerName\"]");
    By busPrincName = By.xpath("//*[@id=\"filterSapCcpurchaseBusinessPrincipleName\"]");
    By styleNo = By.xpath("//*[@id=\"filterSapCcpurchaseFreetext\"]");
    By purchaseDateFromField = By.xpath("//*[@id=\"filterSapCcpurchasePoDateFrom\"]");//By.id("filterSapccPurchasePoDateFrom");
    By purchaseDateToField = By.xpath("//*[@id=\"filterSapCcpurchasePoDateTo\"]");//By.id("filterSapccPurchasePoDateTo");
    By yourMatNumField = By.id("filterSapccPurchaseCustomerMaterialNo");
    By ticketField = By.id("filterSapccPurchaseTicketId");
    By requesterField = By.id("filterRequesterId");
    By searchButton = By.xpath("//*[@id=\"FilterPurchaseForm\"]/div[2]/table/tbody/tr[6]/td/div/input");//By.cssSelector("#FilterPurchaseForm > div.grid_12 > table > tbody > tr:nth-child(6) > td > div > input");
    By clearButton = By.xpath("//*[@id=\"FilterPurchaseForm\"]/div[2]/table/tbody/tr[6]/td/a");//By.cssSelector("#FilterPurchaseForm > div.grid_12 > table > tbody > tr:nth-child(6) > td > a");
    By viewButton = By.xpath("//*[@id=\"content\"]/div[2]/div/div[2]/table/tbody/tr[3]/td[1]/a/span");//By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(3) > td:nth-child(1) > a");
    By exportButton = By.xpath("//*[@id=\"content\"]/div[2]/div/div[2]/div/a");//By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > a");

    By tableHeaders = By.xpath("//*[@id=\"content\"]/div[2]/div/div[2]/table/tbody/tr[1]/th");
    public String brandPart1 = "#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(";
    public String brandPart2 = ") > td:nth-child(5)";
    public String custPoTable1 = "#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(";
    public String custPoTable2 = "3) > td:nth-child(2)";
    public By noRecords = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > div");
    
    public Ecomm_SummaryOfPurchasePage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public Ecomm_SummaryOfPurchasePage setCustName(String item) {
        CommonTask.setSearchField(driver,custNameField,item);
        return this;
    }
    
    public Ecomm_SummaryOfPurchasePage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,brandField,item);
        return this;
    }
    
    public Ecomm_SummaryOfPurchasePage setRequester(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,requesterField,item);
        return this;
    }

    public Ecomm_SummaryOfPurchasePage setSalesOrgName(String item) throws InterruptedException {
        /**
         * Creator: Stefan
         * Date: 15.06.2016
         */
        Wait.clickable(driver,salesOrgName);
        CommonTask.setDropDownField(driver,salesOrgName,item);
        return this;
    }

    public Ecomm_SummaryOfPurchasePage setEcommOrderNo(String item) throws InterruptedException {
        /**
         * Creator: Stefan
         * Date: 15.06.2016
         */
        Wait.clickable(driver,ecommOrderNo);
        CommonTask.setDropDownField(driver,ecommOrderNo,item);
        return this;
    }

    public Ecomm_SummaryOfPurchasePage setCustCode(String item) throws InterruptedException {
        /**
         * Creator: Stefan
         * Date: 15.06.2016
         */
        Wait.clickable(driver,customerCode);
        CommonTask.setInputField(driver,customerCode,item);
        return this;
    }

    public Ecomm_SummaryOfPurchasePage setPayerName(String item) throws InterruptedException {
        /**
         * Creator: Stefan
         * Date: 15.06.2016
         */
        Wait.clickable(driver,payerName);
        CommonTask.setInputField(driver,payerName,item);
        return this;
    }

    public String getClearButtonLabel() {
        WebElement button = Wait.clickable(driver, clearButton);
        String label = button.getText();
        return label;
    }

    public StringBuilder getTableHeader() {
        WebElement button = Wait.clickable(driver,searchButton);
        int columnCount = driver.findElements(tableHeaders).size();
        StringBuilder header = new StringBuilder();
        for (int i=0;i<columnCount;i++){
            By tableHeader = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(1) > th:nth-child("+(i+1)+")");
            //#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > table > tbody > tr:nth-child(1) > th:nth-child(1)
            header = header.append(driver.findElement(tableHeader).getText());
            header = header.append("|");
        }
        return header;
    }
    
    public Ecomm_SummaryOfPurchasePage pressSearch() {
        WebElement search = Wait.clickable(driver,searchButton);
        search.click();
        return this;
    }
    
    public Ecomm_SummaryOfPurchasePage pressClearButton() {
        WebElement reset = Wait.clickable(driver, clearButton);
        reset.click();
        return this;
    }
    
    public Ecomm_OrderViewPage pressView() {
        WebElement view = Wait.clickable(driver,viewButton);
        view.click();
        return new Ecomm_OrderViewPage(driver);
    }
    
    public Ecomm_ExportDownloadPage pressExport() {
        WebElement export = Wait.clickable(driver,exportButton);
        export.click();
        return new Ecomm_ExportDownloadPage(driver);
    }
    
    public void checkFields_SUMST() {
        //Wait for all elements to be clickable
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement custPO = Wait.clickable(driver,custPOField);
        //WebElement brand = Wait.clickable(driver,brandField);
        //WebElement length = Wait.clickable(driver,lengthField);
        //WebElement matNum = Wait.clickable(driver,matNumField);
        WebElement purFrom = Wait.clickable(driver,purchaseDateFromField);
        WebElement purTo = Wait.clickable(driver,purchaseDateToField);
        WebElement salesOrg = Wait.clickable(driver,salesOrgName);
        //WebElement requester = Wait.clickable(driver,requesterField);
        //WebElement yourMat = Wait.clickable(driver,yourMatNumField);
        //WebElement ticket = Wait.clickable(driver,ticketField);
        WebElement search = Wait.clickable(driver,searchButton);
        WebElement reset = Wait.clickable(driver, clearButton);
        WebElement view = Wait.clickable(driver,viewButton);
        WebElement export = Wait.clickable(driver,exportButton);
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Summary of Purchase Page: Customer Name field not displayed correctly",custName.isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Customer PO field not displayed correctly",custPO.isDisplayed());
        //AssertJUnit.assertTrue("Summary of Purchase Page: Brand field not displayed correctly",brand.isDisplayed());
        //AssertJUnit.assertTrue("Summary of Purchase Page: Length field not displayed correctly",length.isDisplayed());
        //AssertJUnit.assertTrue("Summary of Purchase Page: Material Number field not displayed correctly",matNum.isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Purchase Date From field not displayed correctly",purFrom.isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Purchase Date To field not displayed correctly",purTo.isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Purchase Date To field not displayed correctly",salesOrg.isDisplayed());
        //AssertJUnit.assertTrue("Summary of Purchase Page: Requester field not displayed correctly",requester.isDisplayed());
        //AssertJUnit.assertTrue("Summary of Purchase Page: Your Material Number field not displayed correctly",yourMat.isDisplayed());
        //AssertJUnit.assertTrue("Summary of Purchase Page: Ticket field not displayed correctly",ticket.isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Search button not displayed correctly",search.isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Reset button not displayed correctly",reset.isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: View button not displayed correctly",view.isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Export button not displayed correctly",export.isDisplayed());
    }
    
    public void checkFields_SUSST() {
        //Wait for all elements to be clickable
        WebElement custPO = Wait.clickable(driver,custPOField);
        //WebElement brand = Wait.clickable(driver,brandField);
        //WebElement length = Wait.clickable(driver,lengthField);
        //WebElement matNum = Wait.clickable(driver,matNumField);
        WebElement purFrom = Wait.clickable(driver,purchaseDateFromField);
        WebElement purTo = Wait.clickable(driver,purchaseDateToField);
        //WebElement yourMat = Wait.clickable(driver,yourMatNumField);
        //WebElement ticket = Wait.clickable(driver,ticketField);
        WebElement search = Wait.clickable(driver,searchButton);
        WebElement reset = Wait.clickable(driver, clearButton);
        WebElement view = Wait.clickable(driver,viewButton);
        WebElement export = Wait.clickable(driver,exportButton);
    
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Summary of Purchase Page: Customer PO field not displayed correctly",custPO.isDisplayed());
        //AssertJUnit.assertTrue("Summary of Purchase Page: Brand field not displayed correctly",brand.isDisplayed());
        //AssertJUnit.assertTrue("Summary of Purchase Page: Length field not displayed correctly",length.isDisplayed());
        //AssertJUnit.assertTrue("Summary of Purchase Page: Material Number field not displayed correctly",matNum.isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Purchase Date From field not displayed correctly",purFrom.isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Purchase Date To field not displayed correctly",purTo.isDisplayed());
        //AssertJUnit.assertTrue("Summary of Purchase Page: Your Material Number field not displayed correctly",yourMat.isDisplayed());
        //AssertJUnit.assertTrue("Summary of Purchase Page: Ticket field not displayed correctly",ticket.isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Search button not displayed correctly",search.isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Reset button not displayed correctly",reset.isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: View button not displayed correctly",view.isDisplayed());
        AssertJUnit.assertTrue("Summary of Purchase Page: Export button not displayed correctly",export.isDisplayed());
    }
    
}
