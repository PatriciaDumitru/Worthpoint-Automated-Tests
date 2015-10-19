package PageObjects;

import AutomationFramework.Categories;
import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import org.junit.Assert;
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
    static By breadcrumbLocator = By.cssSelector("#list_page_breadcrumb > h1");
    static By productDetailsTableLocator = By.id("t1");
    static By customerDetailsTableLocator = By.cssSelector("#BulkOrderOrdermanualForm > div.container > div:nth-child(2) > table");
    static By nextButtonLocator = By.id("next");
    static By saveDraftLocator = By.id("drafts");
    static By cancelButtonLocator = By.id("cancel1");
    static By flashMessageLocator = By.id("flashMessage");
    static By titleLocator = By.cssSelector("#BulkOrderOrdermanualForm > div.container > div:nth-child(2) > div.tbl-title > h1");
    
    //Customer detail field locators
    static By customerNameField = By.cssSelector("#s2id_customer_id > a > span.select2-chosen.select_image_add");//Initial customer name field to click
    static By customerNameSearchField = By.cssSelector("#select2-drop > div > input");//Search field which appears after click
    static By customerNameSearchResult = By.cssSelector("#select2-drop > ul > li");//The result which is displayed after typing
    static By shipToPartyField = By.id("ship_to_party_id"); //Initial dropdown menu field
    static By shipToPartyOption = By.cssSelector("#ship_to_party_id > option:nth-child(2)");//First option in drop down menu
    static By requestorField = By.id("BulkOrderRequesterId"); //Initial dropdown menu field
    static By requestorOption = By.cssSelector("##BulkOrderRequesterId > option:nth-child(1)"); //First option in drop down menu
    static By buyersField = By.cssSelector("#s2id_BuyerId > a > span.select2-chosen.select_image_add");//Initial dropdown menu field
    static By buyersSearchField = By.cssSelector("#select2-drop > div > input");//Search field which appears after click
    static By buyersSearchResult = By.cssSelector("#select2-drop > ul > li");//First search result to appear after typing
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
        return driver.findElement(breadcrumbLocator);
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
    
    public Ecomm_ManualEntryPage setCustomerName(String customerName) {
        //Wait for customer name field to be clickable
        WebElement waitToClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(customerNameField));
        //click field and type customer name
        Actions typeCustomerName = new Actions(driver);
        typeCustomerName.click(driver.findElement(customerNameField)).build().perform();
        WebElement waitForSearchField = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(customerNameSearchField));
        driver.findElement(customerNameSearchField).sendKeys(customerName);
        //Wait for search result to load
        Boolean waitForResult = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(customerNameSearchResult, customerName));
        //Press enter
        typeCustomerName.sendKeys(driver.findElement(customerNameSearchField), Keys.ENTER).build().perform();
        //wait for fields to update
        Boolean waitForUpdate = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(buyersField, DataItems.custDetails[3]));
        return this;
    }
    
    public Ecomm_ManualEntryPage setCustomerNameNew(String item) {
        CommonTask.setSearchField(driver, customerNameField, item);
        return this;
    }
    
    public Ecomm_ManualEntryPage setShipToParty(String shipToParty) {
        //Wait for ship to party field to be clickable
        WebElement waitToClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(shipToPartyField));
        //click field and type ship to party name
        Actions typeShipToParty = new Actions(driver);
        typeShipToParty.click(driver.findElement(shipToPartyField)).build().perform();
        WebElement waitForDropDown = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(driver.findElement(shipToPartyOption)));
        typeShipToParty.sendKeys(shipToParty + Keys.ENTER).build().perform();
        return this;
    }
    
    public Ecomm_ManualEntryPage setShipToPartyNew(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, shipToPartyField, item);
        return this;
    }
    
    public Ecomm_ManualEntryPage setRequestor(String requestor) {
        //Wait for requestor field to be clickable
        WebElement waitToClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(requestorField));
        //click field and type requestor name
        Actions typeRequestorName = new Actions(driver);
        typeRequestorName.click(driver.findElement(requestorField)).build().perform();
        //Type requestor name
        typeRequestorName.sendKeys(driver.findElement(requestorField),requestor).build().perform();
        //Wait for text to appear in field before pressing enter
        boolean waitForText = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(requestorField, requestor));
        typeRequestorName.sendKeys(Keys.ENTER);
        return this;
    }
    
    public Ecomm_ManualEntryPage setRequestorNew(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, requestorField, item);
        return this;
    }
    
    public Ecomm_ManualEntryPage setBuyers(String buyer) {
        //Wait for buyers field to be clickable
        WebElement waitToClick = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(buyersField));
        //Click field and wait for search to appear
        Actions typeBuyer = new Actions(driver);
        typeBuyer.click(driver.findElement(buyersField)).build().perform();
        //Type buyer 
        typeBuyer.sendKeys(driver.findElement(buyersSearchField),buyer).build().perform();
        //Wait for text to appear in field before pressing enter
        WebElement waitForText = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(buyersSearchResult));
        typeBuyer.sendKeys(driver.findElement(buyersSearchField),Keys.ENTER).build().perform();
        return this;
    }
    
    public Ecomm_ManualEntryPage setBuyersNew(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, buyersField, item);
        return this;
    }

    public Ecomm_ManualEntryPage setPoNumber(String poNumber) {
        //Wait for field to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(poNumberField));
        //Click field and enter PO number
        Actions typePoNumber = new Actions(driver);
        typePoNumber.click(driver.findElement(poNumberField)).build().perform();
        

        try {
            //Access file to read
            FileReader fr = new FileReader("C:\\Screenshots\\ID.txt");
            BufferedReader br = new BufferedReader(fr);
            
            //Get current ID
            String idString = br.readLine();
            int id = Integer.valueOf(idString);
            //Increment ID to be written back to file
            id++;
            
            br.close();
            fr.close();
            
            //Access file to write
            FileWriter fw = new FileWriter("C:\\Screenshots\\ID.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            
            //Write incremented id to file
            bw.write(String.valueOf(id));
            //Append the ID and type the PO number
            String PONumber = poNumber+idString;
            typePoNumber.sendKeys(driver.findElement(poNumberField),PONumber).build().perform();
            DataItems.lastUsedPO = PONumber;
            
            bw.close();
            fw.close();
            
        } catch (IOException e) {
            System.out.println("IO exception handling the ID file");
        }

        return this;
    }
    
    public Ecomm_ManualEntryPage setPoNumberNew(String item) throws InterruptedException {
        
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
        //new action to click and send text to field:
        Actions clickAndType = new Actions(driver);
        clickAndType.click(driver.findElement(ymnLocator)).build().perform();
        clickAndType.sendKeys(yourMaterialNumber).build().perform();
        //Click away from field (in the table header) to prompt input and wait for table to update
        By headerLocator = By.cssSelector("#t1 > thead > tr > th:nth-child(3)");
        clickAndType.click(driver.findElement(headerLocator)).build().perform();
        //Wait for finish detail to be added to ensure table is updated
        By finishLocator = By.id("Finish"+lineNumber);
        Boolean waitForUpdate = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(finishLocator, DataItems.expFinish));
        return this;
    }
    
    public Ecomm_ManualEntryPage setArticle(String article,int lineNumber) {
        WebElement articleField = driver.findElement(By.cssSelector("#s2id_BulkOrderLine"+lineNumber+"ArticleId > a"));
        
        //Wait for field to be accessible
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(articleField));
        //Click field and type article
        Actions clickAndType = new Actions(driver);
        clickAndType.click(articleField).build().perform();
        
        By articleSearchLocator = By.cssSelector("#select2-drop > div > input");
        WebElement waitForSearch = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(articleSearchLocator));
        clickAndType.sendKeys(driver.findElement(articleSearchLocator),article).build().perform();
        
        By articleResultLocator = By.cssSelector("#select2-drop > ul > li.select2-results-dept-0.select2-result.select2-result-selectable.select2-highlighted");
        //Wait for result to appear
        WebElement waitForResult = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(articleResultLocator));
        //Press enter
        clickAndType.sendKeys(driver.findElement(articleSearchLocator),Keys.ENTER).build().perform();
        
        return this;
    }
    
    public Ecomm_ManualEntryPage setBrand(String brand, int lineNumber) {
        //Produce locator for field
        By brandLocator = By.id("Brand"+lineNumber);
        
        //Wait for field to be available
        WebElement waitForField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(brandLocator));
        
        //New action to click and send text to field
        Actions clickAndType = new Actions(driver);
        clickAndType.click(driver.findElement(brandLocator)).build().perform();
        clickAndType.sendKeys(driver.findElement(brandLocator),brand+Keys.ENTER).build().perform();
        
        //Wait for field to update
        boolean waitForUpdate = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(brandLocator, brand));
        
        return this;
    }
    
    public Ecomm_ManualEntryPage setTicket(String ticket,int lineNumber) {
        //Produce locator for field
        By ticketLocator = By.id("Ticket"+lineNumber);
        
        //Wait for field to be available
        WebElement waitForField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(ticketLocator));
        
        //New action to click and send text to field
        Actions clickAndType = new Actions(driver);
        clickAndType.click(driver.findElement(ticketLocator)).build().perform();
        clickAndType.sendKeys(driver.findElement(ticketLocator),ticket+Keys.ENTER).build().perform();
        
        //Wait for field to update
        boolean waitForUpdate = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(ticketLocator, ticket));
        
        return this;
    }
    
    public Ecomm_ManualEntryPage setLength(String length, int lineNumber) {
        //Produce locator for field
        By lengthLocator = By.id("Length"+lineNumber);
        
        //Wait for field to be available
        WebElement waitForField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(lengthLocator));
        
        //New action to click and send text to field
        Actions clickAndType = new Actions(driver);
        clickAndType.click(driver.findElement(lengthLocator)).build().perform();
        clickAndType.sendKeys(driver.findElement(lengthLocator),length+Keys.ENTER).build().perform();
        
        //Wait for field to update
        boolean waitForUpdate = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(lengthLocator, length));
        
        return this;
    }
    
    public Ecomm_ManualEntryPage setFinish(String finish, int lineNumber) {
        //Produce locator for field
        By finishLocator = By.id("Finish"+lineNumber);
        
        //Wait for field to be available
        WebElement waitForField = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(finishLocator));
        
        //New action to click and send text to field
        Actions clickAndType = new Actions(driver);
        clickAndType.click(driver.findElement(finishLocator)).build().perform();
        clickAndType.sendKeys(driver.findElement(finishLocator),finish+Keys.ENTER).build().perform();
        
        //Wait for field to update
        boolean waitForUpdate = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(finishLocator, finish));
        
        return this;
    }
    
    public Ecomm_ManualEntryPage setShadeCode(String shadeCode,int lineNumber) {
        //produce locator for field. Line numbers start from 0
        By shadeCodeLocator = By.id("s2id_BulkOrderLine"+lineNumber+"ShadeId");
        //produce locator for search field
        By shadeCodeSearchLocator = By.cssSelector("#select2-drop > div > input");
        //locator for first search result
        By shadeCodeResult = By.cssSelector("#select2-drop > ul > li");
        //new action to click and send text to field
        Actions clickAndType = new Actions(driver);
        clickAndType.click(driver.findElement(shadeCodeLocator)).build().perform();
        //wait for search field to appear
        WebElement waitForSearchField = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(shadeCodeSearchLocator));
        driver.findElement(shadeCodeSearchLocator).sendKeys(shadeCode);
        //Wait for search result to load and press enter
        boolean waitForResult = new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(shadeCodeResult,shadeCode));
        clickAndType.sendKeys(Keys.ENTER).build().perform();
        return this;
    }
    
    public Ecomm_ManualEntryPage setQty(int quantity, int lineNumber) {
        //produce locator for field. Line numbers start from 0
        By qtyLocator = By.id("quantity"+lineNumber);
        //new action to click and send text to field:
        Actions clickAndType = new Actions(driver);
        clickAndType.click(driver.findElement(qtyLocator)).build().perform();
        clickAndType.sendKeys(String.valueOf(quantity)).build().perform();
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
        
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(dateFieldLocator));
        Actions action = new Actions(driver);
        action.sendKeys(driver.findElement(dateFieldLocator),date);
        
        return this;
    }
    
    //set order details using Your Material Number and shade code from master data
    public Ecomm_ManualEntryPage setOrderDetailsYMN(String[] details,int lineNumber) {
        //Wait for field to be available
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(By.id("materialno_"+lineNumber)));
        setYourMaterialNumber(details[0],lineNumber);
        setQty(Integer.valueOf(details[1]),lineNumber);
        setDate(lineNumber);
        
        return this;
    }
    
    //Set order details using Your Material Number and separate shade code (not in master data)
    public Ecomm_ManualEntryPage setOrderDetailsYMNShadeCode(String[] details, int lineNumber) {
        //Wait for field to be available
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(By.id("materialno_"+lineNumber)));
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
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(nextButtonLocator));
        //Click next
        Actions clickNext = new Actions(driver);
        clickNext.click(driver.findElement(nextButtonLocator)).build().perform();
        //Submit the alert
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        //Sometimes additional alerts appear. Catch these, output their text, and accept by default
        boolean alertPresence;
        try {
            Alert secondAlert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
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
    
    public void pressNextExpectingFailure() {
        //Wait for button to be clickable
        WebElement waitForClickable = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(nextButtonLocator));
        //Click next
        Actions clickNext = new Actions(driver);
        clickNext.click(driver.findElement(nextButtonLocator)).build().perform();
        //Submit the alert
        Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        this.waitForLoad();

    }
    
    public WebElement waitForError() {
        return new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(flashMessageLocator));
    }
    
    public boolean waitForTitle() {
        boolean waitForText = new WebDriverWait(driver,5).until(ExpectedConditions.textToBePresentInElementLocated(titleLocator,"Manual Entry"));
        return waitForText;
    }
    
    public String getCustomerName() {
        return driver.findElement(customerNameField).getText();
    }
    
    public String getShipToName() {
        Boolean waitForSelection = new WebDriverWait(driver,5).until(CommonTask.selectionToBePresent(shipToPartyField));
        
        Select select = new Select(driver.findElement(shipToPartyField));
        return select.getFirstSelectedOption().getText();
    }
    
    public String getRequestorName() {
        Boolean waitForSelection = new WebDriverWait(driver,5).until(CommonTask.selectionToBePresent(requestorField));
        
        Select select = new Select(driver.findElement(requestorField));
        return select.getFirstSelectedOption().getText();
    }
    
    public String getBuyer() {
        WebElement waitForClickable = new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(buyersField));
        return driver.findElement(buyersField).getText();
    }
    
    public String getCustPONo() {
        return driver.findElement(poNumberField).getAttribute("value");
    }
    
    public String getYourMatNum(int row) {
        By ymnFieldLocator = By.id("materialno_"+row);
        return driver.findElement(ymnFieldLocator).getAttribute("value");
    }
}