package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
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
import org.openqa.selenium.TimeoutException;
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
    static By addMaterial = By.className("msgBox");
    static By addMaterialText = By.className("msgBoxTitle");
    static By flashMsg = By.cssSelector("#BulkOrderOrdermanualForm > div.container > div:nth-child(2) > div.tbl-title > h4");


    //Customer detail field locators
    static By customerNameField = By.id("s2id_customer_id");//Initial customer name field to click
    static By shipToPartyField = By.id("ship_to_party_id"); //Initial dropdown menu field
    static By requestorField = By.id("BulkOrderRequesterId"); //Initial dropdown menu field
    static By buyersField = By.cssSelector("#s2id_BuyerId > a > span.select2-chosen.select_image_add");//Initial dropdown menu field
    static By subAccountField = By.id("payer_id");//Initial dropdown menu field
    static By poNumberField = By.id("BulkOrderPoNumber");//Text field to enter PO Number
    static By shipToAddressOutput = By.id("ship_to_party_address");//Text output area to display address

    //Customer detail labels (used for SUSST)
    static By custNameLabel = By.cssSelector("#BulkOrderOrdermanualForm > div.container > div:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(2) > label");
    static By requestorLabel = By.cssSelector("#BulkOrderOrdermanualForm > div.container > div:nth-child(2) > table > tbody > tr:nth-child(3) > td > label");

    //Line detail fields
    static By contractPOField = By.id("txtContract0");
    static By lineRefField = By.id("txtContractLine0");

    static By contractOrderButton = By.id("contract_check");
    static By findNormalOrdButton = By.id("normal_check");

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

    public WebElement getSubAccountField() {
        WebElement wait = Wait.clickable(driver, subAccountField);
        return driver.findElement(subAccountField);
    }

    public WebElement getProductTable() {
        //find and return element
        return driver.findElement(productDetailsTableLocator);
    }

    public boolean findContractPOField() {

        try {
            WebElement element = Wait.clickable(driver, contractPOField);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean findLineRefField() {
        try {
            WebElement element = Wait.clickable(driver, lineRefField);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean findContractOrdButton() {

        try {
            WebElement element = driver.findElement(contractOrderButton);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean findNormalOrdButton() {

        try {
            WebElement element =  driver.findElement (findNormalOrdButton);
            return true;
        } catch (Exception e) {
            return false;
        }

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

    public Ecomm_ManualEntryPage setShipToPartyWithWait(String item) throws InterruptedException {
        boolean selectionPresent = Wait.selectionPresent(driver, shipToPartyField);
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

    public Ecomm_ManualEntryPage setSubAccount(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, subAccountField, item);
        return new Ecomm_ManualEntryPage(driver);
    }

    public Ecomm_ManualEntryPage setPONumber(String item) throws InterruptedException, IOException {

        String poNumber = CommonTask.generatePO(item);
        CommonTask.setInputField(driver, poNumberField, poNumber);
        DataItems.lastUsedPO = poNumber;

        return this;
    }

    public Ecomm_ManualEntryPage setYourMaterialNumber(String yourMaterialNumber, int lineNumber) {
        //produce locator for field. Line numbers start from 0
        By ymnLocator = By.id("materialno_" + lineNumber);
        CommonTask.setInputField(driver, ymnLocator, yourMaterialNumber);

        //Click away from field (in the table header) to prompt input and wait for table to update
        By headerLocator = By.cssSelector("#t1 > thead > tr > th:nth-child(3)");
        driver.findElement(headerLocator).click();

        //Wait for finish detail to be added to ensure table is updated
        By finishLocator = By.id("Finish" + lineNumber);
        Boolean waitForUpdate = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(finishLocator, DataItems.expFinish));

        return this;
    }

    public Ecomm_ManualEntryPage setArticle(String article, int lineNumber) {
        By articleField = By.id("s2id_BulkOrderLine" + lineNumber + "ArticleId");
        CommonTask.setSearchField(driver, articleField, article);
        driver.findElement(articleHeadCell).click();

        return this;
    }

    public Ecomm_ManualEntryPage setBrand(String brand, int lineNumber) {
        //Produce locator for field
        By brandLocator = By.id("Brand" + lineNumber);
        WebElement field = Wait.clickable(driver, brandLocator);

        Select select = new Select(field);
        field.click();
        select.selectByVisibleText(brand);

        //Wait for field to update
        boolean waitForUpdate = Wait.selectionToBe(driver, brandLocator, brand);

        return this;
    }

    public Ecomm_ManualEntryPage setTicket(String ticket, int lineNumber) {
        //Produce locator for field
        By ticketLocator = By.id("Ticket" + lineNumber);
        WebElement field = Wait.clickable(driver, ticketLocator);

        Select select = new Select(field);
        field.click();
        select.selectByVisibleText(ticket);

        //Wait for field to update
        boolean waitForUpdate = Wait.selectionToBe(driver, ticketLocator, ticket);

        return this;
    }

    public Ecomm_ManualEntryPage setLength(String length, int lineNumber) {
        //Produce locator for field
        By lengthLocator = By.id("Length" + lineNumber);
        WebElement field = Wait.clickable(driver, lengthLocator);

        Select select = new Select(field);
        field.click();
        select.selectByVisibleText(length);

        //Wait for field to update
        boolean wait = Wait.selectionToBe(driver, lengthLocator, length);

        return this;
    }

    public Ecomm_ManualEntryPage setFinish(String finish, int lineNumber) {
        //Produce locator for field
        By finishLocator = By.id("Finish" + lineNumber);
        WebElement field = Wait.clickable(driver, finishLocator);

        Select select = new Select(field);
        field.click();
        select.selectByVisibleText(finish);

        //Wait for field to update
        boolean wait = Wait.selectionToBe(driver, finishLocator, finish);

        return this;
    }

    public Ecomm_ManualEntryPage setShadeCode(String shadeCode, int lineNumber) {
        //produce locator for field. Line numbers start from 0
        By shadeCodeLocator = By.cssSelector("#s2id_BulkOrderLine" + lineNumber + "ShadeId > a");
        CommonTask.setSearchField(driver, shadeCodeLocator, shadeCode);
        return this;
    }

    public Ecomm_ManualEntryPage setQty(int quantity, int lineNumber) {
        //produce locator for field. Line numbers start from 0

        By qtyLocator = By.id("quantity" + lineNumber);
        driver.findElement(qtyLocator).clear();
        CommonTask.setInputField(driver, qtyLocator, String.valueOf(quantity));
        return this;
    }

    public Ecomm_ManualEntryPage setStyleNo(String item, int lineNumber) {
        By locator = By.id("txtStyleno" + lineNumber);
        CommonTask.setTextField(driver, locator, item);
        return this;
    }

    public Ecomm_ManualEntryPage setOtherInfo(String item, int lineNumber) {
        By locator = By.id("txtOtherInfo" + lineNumber);
        CommonTask.setTextField(driver, locator, item);
        return this;
    }

    public Ecomm_ManualEntryPage setContractPO(String item, int lineNumber) {
        By locator = By.id("txtContract" + lineNumber);
        CommonTask.setInputField(driver, locator, item);
        return this;
    }

    public Ecomm_ManualEntryPage setLineRef(String item, int lineNumber) {
        By locator = By.id("txtContractLine" + lineNumber);
        CommonTask.setInputField(driver, locator, item);
        return this;
    }

    public WebElement getQtyField() {
        By qtyLocator = By.id("quantity0");
        return driver.findElement(qtyLocator);
    }

    public String getQuantity(int row) {
        By qtyLocator = By.id("quantity" + row);
        return driver.findElement(qtyLocator).getAttribute("value");
    }

    public Ecomm_ManualEntryPage setDate(int lineNumber) {
        //produce locator for field. Line numbers start from 0
        By dateFieldLocator = By.id("required_date_" + lineNumber);

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
            String date = String.valueOf((int) dayOfMonth + 3);
            By dateLocator = By.xpath("//a[contains(text(),'" + date + "')]");
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

    public Ecomm_ManualEntryPage setDateToday(int lineNumber) {
        //produce locator for field. Line numbers start from 0
        By dateFieldLocator = By.id("required_date_" + lineNumber);

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
            String date = String.valueOf((int) dayOfMonth);
            By dateLocator = By.xpath("//a[contains(text(),'" + date + "')]");
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

    public Ecomm_ManualEntryPage setDateTomorrow(int lineNumber) {
        //produce locator for field. Line numbers start from 0
        By dateFieldLocator = By.id("required_date_" + lineNumber);

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
            String date = String.valueOf((int) dayOfMonth + 1);
            By dateLocator = By.xpath("//a[contains(text(),'" + date + "')]");
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

    public Ecomm_ManualEntryPage setDateDayAfterTomorrow(int lineNumber) {
        //produce locator for field. Line numbers start from 0
        By dateFieldLocator = By.id("required_date_" + lineNumber);

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
            String date = String.valueOf((int) dayOfMonth + 2);
            By dateLocator = By.xpath("//a[contains(text(),'" + date + "')]");
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
        By dateFieldLocator = By.id("required_date_" + lineNumber);

        WebElement dateField = Wait.clickable(driver, dateFieldLocator);
        Actions action = new Actions(driver);
        action.sendKeys(dateField, date).build().perform();

        return this;
    }

    //set order details using Your Material Number and shade code from master data
    public Ecomm_ManualEntryPage setOrderDetailsYMN(String[] details, int lineNumber) {
        //Wait for field to be available
        WebElement waitForClickable = Wait.clickable(driver, By.id("materialno_" + lineNumber));
        setYourMaterialNumber(details[0], lineNumber);
        setQty(Integer.valueOf(details[1]), lineNumber);
        setDate(lineNumber);

        return this;
    }

    //Set order details using Your Material Number and separate shade code (not in master data)
    public Ecomm_ManualEntryPage setOrderDetailsYMNShadeCode(String[] details, int lineNumber) {
        //Wait for field to be available
        WebElement waitForClickable = Wait.clickable(driver, By.id("materialno_" + lineNumber));
        setYourMaterialNumber(details[0], lineNumber);
        setShadeCode(details[1], lineNumber);
        setQty(Integer.valueOf(details[2]), lineNumber);
        setDate(lineNumber);

        return this;
    }

    //Set order details using Article and shade code
    public Ecomm_ManualEntryPage setOrderDetailsArticle(String[] details, int lineNumber) {
        //Enter article, shade code and quantity
        setArticle(details[0], lineNumber);
        setShadeCode(details[1], lineNumber);
        setQty(Integer.valueOf(details[2]), lineNumber);
        setDate(lineNumber);

        return this;
    }

    //Set order detais using a combination of brand/ticket/length/finish/shadecode
    public Ecomm_ManualEntryPage setOrderDetailsCombination(String[] details, int lineNumber) {
        //Enter brand, ticket, length, finish, and shadecode
        setBrand(details[0], lineNumber);
        setTicket(details[1], lineNumber);
        setLength(details[2], lineNumber);
        setFinish(details[3], lineNumber);
        setShadeCode(details[4], lineNumber);

        //Set quantity and date
        setQty(Integer.valueOf(details[5]), lineNumber);
        setDate(lineNumber);

        return this;
    }

    public Ecomm_OrderConfirmationPage pressNext() {
        //Wait for button to be clickable
        WebElement next = Wait.clickable(driver, nextButtonLocator);
        //Click next
        Actions clickNext = new Actions(driver);
        clickNext.click(next).build().perform();
        //Submit the alert
        Alert alert = Wait.alert(driver);
        alert.accept();

        //Sometimes additional alerts appear. Catch these, output their text, and accept by default
        boolean alertPresence;
        try {
            Alert secondAlert = Wait.alert(driver);
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

    public Ecomm_OrderConfirmationPage pressNextMOQ() {
        //Wait for button to be clickable
        WebElement next = Wait.clickable(driver, nextButtonLocator);
        //Click next
        Actions clickNext = new Actions(driver);
        clickNext.click(next).build().perform();
        //Submit the alert
        Alert alert = Wait.alert(driver);
        alert.accept();

        try {
            Alert alert2 = Wait.alert(driver);
            if (!(alert2.getText().contains("order qty has been rounded"))) {
                System.out.println("Additional alert appeared: " + alert2.getText());
                alert2.accept();
            }
        } catch (TimeoutException t) {
            System.out.print(t);
        }

        AssertJUnit.assertTrue("Manual Entry Page: Rounded quantity (MOQ) Alert did not appear", waitForQuantityAlert());

        return new Ecomm_OrderConfirmationPage(driver);
    }

    public Ecomm_OutstandingOrderDraftPage pressSaveAsDraft() {
        WebElement saveDraft = Wait.clickable(driver, saveDraftLocator);
        saveDraft.click();

        Alert alert = Wait.alert(driver);
        alert.accept();

        return new Ecomm_OutstandingOrderDraftPage(driver);
    }

    public Ecomm_ManualEntryPage pressNextExpectingFailure() {
        //Wait for button to be clickable
        WebElement nextBtn = Wait.clickable(driver, nextButtonLocator);
        //Click next
        Actions clickNext = new Actions(driver);
        clickNext.click(nextBtn).build().perform();
        //Submit the alert
        Alert alert = Wait.alert(driver);
        alert.accept();

        return new Ecomm_ManualEntryPage(driver);

    }

    public WebElement waitForError() {
        return Wait.visible(driver, flashMessageLocator);
    }

    public boolean waitForTitle() {
        boolean waitForText = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(titleLocator, "Manual Entry"));
        return waitForText;
    }

    public void waitForElement() {
        WebElement wait = Wait.clickable(driver, saveDraftLocator);
    }

    public String getCustomerName() {
        return driver.findElement(customerNameField).getText();
    }

    public String getShipToName() {
        Boolean waitForSelection = Wait.selectionPresent(driver, shipToPartyField);

        Select select = new Select(driver.findElement(shipToPartyField));
        return select.getFirstSelectedOption().getText();
    }

    public String getRequestorName() {
        Boolean waitForSelection = Wait.selectionPresent(driver, requestorField);

        Select select = new Select(driver.findElement(requestorField));
        return select.getFirstSelectedOption().getText();
    }

    public String getBuyer() {
        WebElement field = Wait.clickable(driver, buyersField);
        return field.getText();
    }

    public String getCustPONo() {
        return driver.findElement(poNumberField).getAttribute("value");
    }

    public String getYourMatNum(int row) {
        By ymnFieldLocator = By.id("materialno_" + row);
        return driver.findElement(ymnFieldLocator).getAttribute("value");
    }

    public String getArticle(int row) {
        By articleField = By.cssSelector("#s2id_BulkOrderLine" + row + "ArticleId > a > span.select2-chosen.select_image_add");
        WebElement article = Wait.clickable(driver, articleField);
        return article.getText();
    }

    public String getBrand(int row) {
        By brandLocator = By.id("Brand" + row);

        Select select = new Select(driver.findElement(brandLocator));

        return select.getFirstSelectedOption().getText();

    }

    public String getTicket(int row) {
        By ticketLocator = By.id("Ticket" + row);

        Select select = new Select(driver.findElement(ticketLocator));

        return select.getFirstSelectedOption().getText();
    }

    public String getLength(int row) {
        By lengthLocator = By.id("Length" + row);

        Select select = new Select(driver.findElement(lengthLocator));

        return select.getFirstSelectedOption().getText();
    }

    public String getFinish(int row) {
        By finishLocator = By.id("Finish" + row);

        Select select = new Select(driver.findElement(finishLocator));

        return select.getFirstSelectedOption().getText();
    }

    public String getSetBrand(int row) {
        //Field changes once article entered. This method is used to get the value from the field after article is input
        By locator = By.id("token_brand_id_" + row);
        WebElement field = Wait.visible(driver, locator);
        return field.getAttribute("value");
    }

    public String getSetTicket(int row) {
        //Field changes once article entered. This method is used to get the value from the field after article is input
        By locator = By.id("token_ticket_id_" + row);
        WebElement field = Wait.visible(driver, locator);
        return field.getAttribute("value");
    }

    public String getSetLength(int row) {
        //Field changes once article entered. This method is used to get the value from the field after article is input
        By locator = By.id("token_length_id_" + row);
        WebElement field = Wait.visible(driver, locator);
        return field.getAttribute("value");
    }

    public String getSetFinish(int row) {
        //Field changes once article entered. This method is used to get the value from the field after article is input
        By locator = By.id("token_finish_id_" + row);
        WebElement field = Wait.visible(driver, locator);
        return field.getAttribute("value");
    }

    public String getShadeCode(int row) {
        By shadeCodeLocator = By.cssSelector("#s2id_BulkOrderLine" + row + "ShadeId > a > span.select2-chosen.select_image_add > span:nth-child(2)");

        String[] parts = driver.findElement(shadeCodeLocator).getText().split(" ");

        return parts[0];
    }

    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement custName = Wait.clickable(driver, customerNameField);
        WebElement shipTo = Wait.clickable(driver, shipToPartyField);
        WebElement requestor = Wait.clickable(driver, requestorField);
        WebElement buyers = Wait.clickable(driver, buyersField);
        WebElement po = Wait.clickable(driver, poNumberField);

        //Assert all elements are displayed
        AssertJUnit.assertTrue("Manual Entry Page: Customer Name field not displayed correctly", custName.isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Ship To Party field not displayed correctly", shipTo.isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Requestor field not displayed correctly", requestor.isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Buyers field not displayed correctly", buyers.isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Customer PO No. field not displayed correctly", po.isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Product Details table not displayed correctly", getProductTable().isDisplayed());
    }

    public void checkSUSSTFields() {
        //Wait for all elements to be clickable
        WebElement shipTo = Wait.clickable(driver, shipToPartyField);
        WebElement buyers = Wait.clickable(driver, buyersField);
        WebElement po = Wait.clickable(driver, poNumberField);

        //Wait for visibility of labels
        WebElement custName = Wait.visible(driver, custNameLabel);
        WebElement requestor = Wait.visible(driver, requestorLabel);

        //Assert all elements are displayed
        AssertJUnit.assertTrue("Manual Entry Page: Ship To Party field not displayed correctly", shipTo.isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Buyers field not displayed correctly", buyers.isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Customer PO No. field not displayed correctly", po.isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Product Details table not displayed correctly", getProductTable().isDisplayed());
    }

    public void checkContractOrderFields() {
        //Wait for all elements to be clickable
        WebElement shipTo = Wait.clickable(driver, shipToPartyField);
        WebElement buyers = Wait.clickable(driver, buyersField);
        WebElement po = Wait.clickable(driver, poNumberField);
        //WebElement contractPO = Wait.clickable(driver,contractPOField);
        // WebElement lineRef = Wait.clickable(driver,lineRefField);

        //Wait for visibility of labels
        WebElement custName = Wait.visible(driver, custNameLabel);
        WebElement requestor = Wait.visible(driver, requestorLabel);

        //Assert all elements are displayed
        AssertJUnit.assertTrue("Manual Entry Page: Ship To Party field not displayed correctly", shipTo.isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Buyers field not displayed correctly", buyers.isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Customer PO No. field not displayed correctly", po.isDisplayed());
        AssertJUnit.assertTrue("Manual Entry Page: Product Details table not displayed correctly", getProductTable().isDisplayed());
        //AssertJUnit.assertTrue("Manual Entry Page: Contract PO Field not displayed",contractPO.isDisplayed());
        //AssertJUnit.assertTrue("Manual Entry Page: Contract PO Field not displayed",lineRef.isDisplayed());
    }

    public boolean waitForAddMaterialMessage() {

        boolean appears;

        try {
            WebElement wait = Wait.visible(driver, addMaterial);
            appears = true;
        } catch (Exception e) {
            appears = false;
        }
        return appears;
    }

    public boolean waitForQuantityAlert() {
        try {
            Alert alert = Wait.alert(driver);
            System.out.println("Alert appeared: " + alert.getText());

            if (alert.getText().contains("order quantity has been rounded")) {
                alert.accept();
                return true;
            } else {
                alert.accept();
                return false;
            }

        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getAddMaterialAlertText() {
        WebElement alert = Wait.visible(driver, addMaterialText);
        return alert.getText();
    }


    public Ecomm_ManualEntryPage susstTwik() {

        setQty(3, 0);
        setQty(3, 1);
        setDate(0);
        setDate(1);
        pressNext();

        return this;
    }

    public WebElement getFlashMessage() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(flashMsg));
    }
}