package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ecomm_ManualEntryPage extends WBA_BasePage {
    
    //general page element locators
    static By navBarLocator = By.id("navigation");
    static By productDetailsTableLocator = By.id("t1");
    static By customerDetailsTableLocator = By.cssSelector("#BulkOrderOrdermanualForm > div.container > div:nth-child(2) > table");
    static By nextButtonLocator = By.id("next");
    static By saveDraftLocator = By.id("drafts");
    static By cancelButtonLocator = By.id("cancel1");
    static By flashMessageLocator = By.id("flashMessage");
    static By titleLocator = By.cssSelector("#BulkOrderOrdermanualForm > div.container > div:nth-child(2) > div.tbl-title.minus.plus > h1");
    static By articleHeadCell = By.cssSelector("#t1 > thead > tr > th:nth-child(4)");
    static By formLocator = By.id("BulkOrderOrdermanualForm");
    
    //Customer detail field locators
    static By customerNameField = By.cssSelector("#s2id_customer_id > a");//Initial customer name field to click
    static By shipToPartyField = By.id("ship_to_party_id"); //Initial dropdown menu field
    static By requestorField = By.id("BulkOrderRequesterId"); //Initial dropdown menu field
    static By buyersField = By.cssSelector("#s2id_BuyerId > a > span.select2-chosen.select_image_add");//Initial dropdown menu field
    static By poNumberField = By.id("BulkOrderPoNumber");//Text field to enter PO Number
    static By shipToAddressOutput = By.id("ship_to_party_address");//Text output area to display address
    
    public Ecomm_ManualEntryPage(WebDriver passedDriver) {
        super(passedDriver);
    }
    
    public WebElement getNavBar() {
        //find and return element
        return driver.findElement(navBarLocator);
    }
    
    public WebElement getBreadcrumb() {
        //find and return element
        return driver.findElement(breadcrumbLocator2);
    }
    
    public WebElement getCustNameField() {
        return driver.findElement(customerNameField);
    }
    
    public WebElement getShipToPartyField() {
        return driver.findElement(shipToPartyField);
    }
    
    public WebElement getRequestorField() {
        return driver.findElement(requestorField);
    }
     
    public WebElement getBuyersField() {
        return driver.findElement(buyersField);
    }
     
    public WebElement getPONumberField() {
        return driver.findElement(poNumberField);
    }
    
    public WebElement getCustomerTable() {
        //find and return element
        return driver.findElement(customerDetailsTableLocator);
    }
    
    public WebElement getProductTable() {
        //find and return element
        return driver.findElement(productDetailsTableLocator);
    }
    
    public WebElement getNextButton() {
        //find and return element
        return driver.findElement(nextButtonLocator);
    }
    
    public WebElement getSaveDraftButton() {
        //find and return element
        return driver.findElement(saveDraftLocator);
    }
    
    public WebElement getCancelButton() {
        //find and return element
        return driver.findElement(cancelButtonLocator);
    }
    
    public Ecomm_ManualEntryPage deleteRow(int lineNumber) {
        //find and return element. Line numbers start from 0.
        By deleteLineLocator = By.xpath("//*[@id=\"delete_" + lineNumber + "\"]");
        Actions deleteLine = new Actions(driver);
        deleteLine.click(driver.findElement(deleteLineLocator));
        return this;
    }  
    
    public Ecomm_ManualEntryPage setCustomerName(String item) {
        CommonTask.setSearchField(driver, customerNameField, item);
        return this;
    }
    
    public Ecomm_ManualEntryPage setShipToParty(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, shipToPartyField, item);
        return this;
    }
    
    public Ecomm_ManualEntryPage setRequestor(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, requestorField, item);
        return this;
    }
    
    public Ecomm_ManualEntryPage setBuyers(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, buyersField, item);
        return this;
    }
    
    public Ecomm_ManualEntryPage setPONumber(String item) throws InterruptedException {
        
        try {
            //Access file to read
            FileReader fr = new FileReader(DataItems.idFilepath);
            BufferedReader br = new BufferedReader(fr);
            //Get current ID
            String idString = br.readLine();
            int id = Integer.valueOf(idString);
            //Increment ID to be written back to file
            id++;
            br.close();
            fr.close();
            
            //Access file to write
            FileWriter fw = new FileWriter(DataItems.idFilepath);
            BufferedWriter bw = new BufferedWriter(fw);
            
            //Write incremented id to file
            bw.write(String.valueOf(id));
            bw.close();
            fw.close();
            //Append the ID and type the PO number
            String PONumber = item+idString;
            CommonTask.setInputField(driver, poNumberField, PONumber);
            DataItems.lastUsedPO = PONumber;
            
        } catch (IOException e) {
            System.out.println("Customer PO Number method: IO exception handling the ID file");
        }
        
        return this;
    }
    
    public Ecomm_ManualEntryPage setYourMaterialNumber(String yourMaterialNumber,int lineNumber) {
        //produce locator for field. Line numbers start from 0
        By ymnLocator = By.id("materialno_"+lineNumber);
        CommonTask.setInputField(driver, ymnLocator, yourMaterialNumber);
        
        //Click away from field (in the table header) to prompt input and wait for table to update
        By headerLocator = By.cssSelector("#t1 > thead > tr > th:nth-child(3)");
        driver.findElement(headerLocator).click();
        
        //Wait for finish detail to be added to ensure table is updated
        By finishLocator = By.id("Finish"+lineNumber);
        Boolean waitForUpdate = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(finishLocator, DataItems.expFinish));
        
        return this;
    }
    
    public Ecomm_ManualEntryPage setArticle(String article,int lineNumber) {
        By articleField = By.id("s2id_BulkOrderLine"+lineNumber+"ArticleId");
        CommonTask.setSearchField(driver, articleField, article);
        driver.findElement(articleHeadCell).click();
        
        return this;
    }
    
    public Ecomm_ManualEntryPage setBrand(String brand, int lineNumber) {
        //Produce locator for field
        By brandLocator = By.id("Brand"+lineNumber);
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(brandLocator));
        
        Select select = new Select(field);
        field.click();
        select.selectByVisibleText(brand);
        
        //Wait for field to update
        boolean waitForUpdate = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.selectionToBe(brandLocator, brand));
        
        return this;
    }
    
    public Ecomm_ManualEntryPage setTicket(String ticket,int lineNumber) {
        //Produce locator for field
        By ticketLocator = By.id("Ticket"+lineNumber);
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(ticketLocator));
        
        Select select = new Select(field);
        field.click();
        select.selectByVisibleText(ticket);
        
        //Wait for field to update
        boolean waitForUpdate = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.selectionToBe(ticketLocator, ticket));
        
        return this;
    }
    
    public Ecomm_ManualEntryPage setLength(String length, int lineNumber) {
        //Produce locator for field
        By lengthLocator = By.id("Length"+lineNumber);
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(lengthLocator));
        
        Select select = new Select(field);
        field.click();
        select.selectByVisibleText(length);
        
        //Wait for field to update
        boolean wait = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.selectionToBe(lengthLocator, length));
        
        return this;
    }
    
    public Ecomm_ManualEntryPage setFinish(String finish, int lineNumber) {
        //Produce locator for field
        By finishLocator = By.id("Finish"+lineNumber);
        WebElement field = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(finishLocator));
        
        Select select = new Select(field);
        field.click();
        select.selectByVisibleText(finish);
        
        //Wait for field to update
        boolean wait = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.selectionToBe(finishLocator, finish));
        
        return this;
    }
    
    public Ecomm_ManualEntryPage setShadeCode(String shadeCode,int lineNumber) {
        //produce locator for field. Line numbers start from 0
        By shadeCodeLocator = By.cssSelector("#s2id_BulkOrderLine"+lineNumber+"ShadeId > a");
        CommonTask.setSearchField(driver, shadeCodeLocator, shadeCode);
        return this;
    }
    
    public Ecomm_ManualEntryPage setQty(int quantity, int lineNumber) {
        //produce locator for field. Line numbers start from 0
        By qtyLocator = By.id("quantity"+lineNumber);
        CommonTask.setInputField(driver,qtyLocator,String.valueOf(quantity));
        return this;
    }
    
    public Ecomm_ManualEntryPage setStyleNo(String item, int lineNumber) {
        By locator = By.id("txtStyleno"+lineNumber);
        CommonTask.setTextField(driver, locator, item);
        return this;
    }
    
    public Ecomm_ManualEntryPage setOtherInfo(String item, int lineNumber) {
        By locator = By.id("txtOtherInfo"+lineNumber);
        CommonTask.setTextField(driver, locator, item);
        return this;
    }
    
    public Ecomm_ManualEntryPage setContractPO(String item, int lineNumber) {
        By locator = By.id("txtContract"+lineNumber);
        CommonTask.setInputField(driver, locator, item);
        return this;
    }
    
    public Ecomm_ManualEntryPage setLineRef(String item, int lineNumber) {
        By locator = By.id("txtContractLine"+lineNumber);
        CommonTask.setInputField(driver, locator, item);
        return this;
    }
    
    public WebElement getQtyField() {
        By qtyLocator = By.id("quantity0");
        return driver.findElement(qtyLocator);
    }
    
    public String getQuantity(int row) {
        By qtyLocator = By.id("quantity"+row);
        return driver.findElement(qtyLocator).getAttribute("value");
    }
    
    public Ecomm_ManualEntryPage setDate(int lineNumber) {
        //produce locator for field. Line numbers start from 0
        By dateFieldLocator = By.id("required_date_"+lineNumber);
        
        //Click required field to open picker
        Actions openPicker = new Actions(driver);
        openPicker.click(driver.findElement(dateFieldLocator)).build().perform();
        
        //Get the current day of the month
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        
        //New action for clicking
        Actions clickDatePicked = new Actions(driver);
        
        //if current Day of month is less than 28th, choose date three days advance. Else, click next month and choose 3rd
        if (dayOfMonth < 28) {
            //calculate date three days in future
            String date = String.valueOf((int)dayOfMonth+3);
            By dateLocator = By.xpath("//a[contains(text(),'"+date+"')]");
            clickDatePicked.click(driver.findElement(dateLocator)).build().perform();
        } else {
            //locate next month button and click
            By nextMonthLocator = By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span");
            clickDatePicked.click(driver.findElement(nextMonthLocator)).build().perform();
            
            //locate 3rd of month button and click
            By thirdOfMonthLocator = By.xpath("//a[contains(text(),\"3\")]");
            clickDatePicked.click(driver.findElement(thirdOfMonthLocator)).click().perform();
        }       
        return this;
    }
    
    public String getDate(int lineNumber) {
        By dateFieldLocator = By.id("required_date_" + lineNumber);
        
        return driver.findElement(dateFieldLocator).getAttribute("value");
    }
    
    public Ecomm_ManualEntryPage setSpecificDate(String date, int lineNumber) {
        //produce locator for field. Line numbers start from 0
        By dateFieldLocator = By.id("required_date_"+lineNumber);
        
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(dateFieldLocator));
        Actions action = new Actions(driver);
        action.sendKeys(driver.findElement(dateFieldLocator),date).build().perform();
        
        return this;
    }
    
    //set order details using Your Material Number and shade code from master data
    public Ecomm_ManualEntryPage setOrderDetailsYMN(String[] details,int lineNumber) {
        //Wait for field to be available
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(By.id("materialno_"+lineNumber)));
        setYourMaterialNumber(details[0],lineNumber);
        setQty(Integer.valueOf(details[1]),lineNumber);
        setDate(lineNumber);
        
        return this;
    }
    
    //Set order details using Your Material Number and separate shade code (not in master data)
    public Ecomm_ManualEntryPage setOrderDetailsYMNShadeCode(String[] details, int lineNumber) {
        //Wait for field to be available
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(By.id("materialno_"+lineNumber)));
        setYourMaterialNumber(details[0],lineNumber);
        setShadeCode(details[1],lineNumber);
        setQty(Integer.valueOf(details[2]),lineNumber);
        setDate(lineNumber);
        
        return this;     
    }
    
    //Set order details using Article and shade code
    public Ecomm_ManualEntryPage setOrderDetailsArticle(String[] details,int lineNumber) {
        //Enter article, shade code and quantity
        setArticle(details[0],lineNumber);
        setShadeCode(details[1],lineNumber);
        setQty(Integer.valueOf(details[2]),lineNumber);
        setDate(lineNumber);
        
        return this;
    }
    
    //Set order detais using a combination of brand/ticket/length/finish/shadecode
    public Ecomm_ManualEntryPage setOrderDetailsCombination(String[] details, int lineNumber) {
        //Enter brand, ticket, length, finish, and shadecode
        setBrand(details[0],lineNumber);
        setTicket(details[1],lineNumber);
        setLength(details[2],lineNumber);
        setFinish(details[3],lineNumber);
        setShadeCode(details[4],lineNumber);
        
        //Set quantity and date
        setQty(Integer.valueOf(details[5]),lineNumber);
        setDate(lineNumber);
        
        return this;
    }
    
    public Ecomm_OrderConfirmationPage pressNext() {
        //Wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(nextButtonLocator));
        //Click next
        Actions clickNext = new Actions(driver);
        clickNext.click(driver.findElement(nextButtonLocator)).build().perform();
        //Submit the alert
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        //Sometimes additional alerts appear. Catch these, output their text, and accept by default
        boolean alertPresence;
        try {
            Alert secondAlert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert();
            String alertText = secondAlert.getText();
            alertPresence = true;
            System.out.println("Additional alert appeared while confirming: " + alertText);
        } catch (Exception e) {
            alertPresence = false;
        }

        if (alertPresence) {
            driver.switchTo().alert().accept();
        }
        
        return new Ecomm_OrderConfirmationPage(driver);
    }
    
    public Ecomm_OutstandingOrderDraftPage pressSaveAsDraft() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveDraftLocator));
        driver.findElement(saveDraftLocator).click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Ecomm_OutstandingOrderDraftPage(driver);
    }
    
    public Ecomm_ManualEntryPage pressNextExpectingFailure() {
        //Wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(nextButtonLocator));
        //Click next
        Actions clickNext = new Actions(driver);
        clickNext.click(driver.findElement(nextButtonLocator)).build().perform();
        //Submit the alert
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Ecomm_ManualEntryPage(driver);
        
    }
    
    public WebElement waitForError() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(flashMessageLocator));
    }
    
    public boolean waitForTitle() {
        boolean waitForText = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(titleLocator,"Manual Entry"));
        return waitForText;
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.longWait).until(ExpectedConditions.presenceOfElementLocated(formLocator));
    }
    
    public String getCustomerName() {
        return driver.findElement(customerNameField).getText();
    }
    
    public String getShipToName() {
        Boolean waitForSelection = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.selectionToBePresent(shipToPartyField));
        
        Select select = new Select(driver.findElement(shipToPartyField));
        return select.getFirstSelectedOption().getText();
    }
    
    public String getRequestorName() {
        Boolean waitForSelection = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.selectionToBePresent(requestorField));
        
        Select select = new Select(driver.findElement(requestorField));
        return select.getFirstSelectedOption().getText();
    }
    
    public String getBuyer() {
        WebElement waitForClickable = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(buyersField));
        return driver.findElement(buyersField).getText();
    }
    
    public String getCustPONo() {
        return driver.findElement(poNumberField).getAttribute("value");
    }
    
    public String getYourMatNum(int row) {
        By ymnFieldLocator = By.id("materialno_"+row);
        return driver.findElement(ymnFieldLocator).getAttribute("value");
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement waitForCustName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
        WebElement waitForShipTo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(shipToPartyField));
        WebElement waitForRequestor = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(requestorField));
        WebElement waitForBuyers = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(buyersField));
        WebElement waitForPOField = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(poNumberField));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Manual Entry Page: Customer Name field not displayed correctly", getCustNameField().isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Ship To Party field not displayed correctly", getShipToPartyField().isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Requestor field not displayed correctly", getRequestorField().isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Buyers field not displayed correctly", getBuyersField().isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Customer PO No. field not displayed correctly", getPONumberField().isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Product Details table not displayed correctly", getProductTable().isDisplayed());
    }
}