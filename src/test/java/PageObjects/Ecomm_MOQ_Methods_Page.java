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

public class Ecomm_MOQ_Methods_Page extends WBA_BasePage {

    public Ecomm_MOQ_Methods_Page(WebDriver passedDriver) {
        super(passedDriver);
    }

    //Locators
    //General
    By chooseTheOtherProfile = By.id("access_type"); //CCE or Ecomm depending on which page you are

    //Sales Org Masters Default Page
    By salesOrgIdField = By.id("filterSalesOrgName");
    By filterSalesOrgButton = By.xpath(".//*[@id='FilterIndexForm']/div[3]/ul/li[1]/input");
    By editSalesOrgButton = By.xpath(".//*[@id='content']/div[2]/table/tbody/tr[2]/td[8]/a[1]/span");

    //Sales Org Edit Page
    By salesOrgNameEditPageField = By.id("SalesOrgSalesOrgName");
    By partialStockCheckBox = By.id("SalesOrgPartialStockCheck");
    By forwardDaysCheckBox = By.id("SalesOrgForwardOrderDaysCheck");
    By forwardDaysField = By.id("SalesOrgForwardOrderDays");
    By saveSalesOrgButton = By.id("save");

    //Customer Masters Page
    By customerNameField = By.id("filterCustomerCustomerName");
    By filterCustomerButton = By.xpath(".//*[@id='FilterIndexForm']/div[3]/ul/li[1]/input");
    By editCustomerButton= By.xpath(".//*[@id='content']/div[2]/table/tbody/tr[2]/td[7]/a[1]/span");

    //Customer Edit Page
    By customerNameEditPageField = By.id("CustomerCustomerName");
    By mdqCheckBox = By.id("CustomerCustMdqEnabled");
    By saveCustomerButton = By.xpath(".//*[@id='CustomerEditForm']/div[4]/ul/li[1]/input");


    //Manual Entry Pag
        //Customer detail
         By customerNameMEField = By.id("s2id_customer_id");
         By shipToPartyMEField = By.id("ship_to_party_id");
         By requesterNameMEField = By.id("BulkOrderRequesterId");
         By buyersMEField = By.id("#s2id_BuyerId > a > span.select2-chosen.select_image_add");
         By customerPONrMEField = By.id("BulkOrderPoNumber");
        //Line detail
        //Buttons
        By nextButton = By.id("next");
        By ajustMOQField = By.xpath(".//*[@id='remove_0']/td[7]");

    public WebElement ajustMOQ() {
        return driver.findElement(ajustMOQField);
    }

    public Ecomm_MOQ_Methods_Page goToSalesOrgAndEdit(){
        driver.get(DataItems.mastersSalesOrgURL);
        setSalesOrg(DataItems.salesOrgFilterString);
        filterSalesOrg();
        editSalesOrg();
        waitForSalesOrgEditPageToOpen();
        return this;
    }

    public Ecomm_MOQ_Methods_Page setSalesOrg(String item) {
        CommonTask.setInputField(driver, salesOrgIdField, item);
        return this;
    }

    public Ecomm_MOQ_Methods_Page filterSalesOrg() {
        Actions action = new Actions(driver);
        action.click(driver.findElement(filterSalesOrgButton)).build().perform();
        return this;
    }

    public Ecomm_MOQ_Methods_Page editSalesOrg() {
        Actions action = new Actions(driver);
        action.click(driver.findElement(editSalesOrgButton)).build().perform();
        return this;
    }


    public Ecomm_MOQ_Methods_Page waitForSalesOrgEditPageToOpen() {
        WebElement wait = Wait.clickable(driver,salesOrgNameEditPageField);
        return this;
    }

    public Ecomm_MOQ_Methods_Page goToCustomerAndEdit(){
        driver.get(DataItems.mastersCustomerURL);
        setCustomerName(DataItems.custNameFilterString);
        filterCustomerName();
        editCustomerName();
        waitForCustomerEditPageToOpen();
        return this;
    }

    public Ecomm_MOQ_Methods_Page setCustomerName(String item) {
        CommonTask.setInputField(driver, customerNameField, item);
        return this;
    }

    public Ecomm_MOQ_Methods_Page filterCustomerName() {
        Actions action = new Actions(driver);
        action.click(driver.findElement(filterCustomerButton)).build().perform();
        return this;
    }

    public Ecomm_MOQ_Methods_Page editCustomerName() {
        Actions action = new Actions(driver);
        action.click(driver.findElement(editCustomerButton)).build().perform();
        return this;
    }

    public Ecomm_MOQ_Methods_Page waitForCustomerEditPageToOpen() {
        WebElement wait = Wait.clickable(driver,customerNameEditPageField);
        return this;
    }

    public Ecomm_MOQ_Methods_Page enablePartialStockCheckBox(){
        CommonTask.setCheckBox(driver, partialStockCheckBox);
        return this;
    }

    public Ecomm_MOQ_Methods_Page disablePartialStockCheckBox(){
        CommonTask.unSetCheckBox(driver, partialStockCheckBox);

        return this;
    }

    public Ecomm_MOQ_Methods_Page enableMOQCheckBox(){
        CommonTask.setCheckBox(driver, mdqCheckBox);
        return this;
    }

    public Ecomm_MOQ_Methods_Page disableMOQCheckBox(){
        CommonTask.unSetCheckBox(driver, mdqCheckBox);
        return this;
    }

    public Ecomm_MOQ_Methods_Page enableFordwardOrderDayAndSetForwardDate10(){
        enableFordwardOrderDay();
        setForwardDate(DataItems.forwardDays10);
        return this;
    }

    public Ecomm_MOQ_Methods_Page enableFordwardOrderDayAndSetForwardDate01(){
        enableFordwardOrderDay();
        setForwardDate(DataItems.forwardDays01);
        return this;
    }
    public Ecomm_MOQ_Methods_Page enableFordwardOrderDay(){
        CommonTask.setCheckBox(driver, forwardDaysCheckBox);
        return this;
    }

    public Ecomm_MOQ_Methods_Page setForwardDate(String item){
        driver.findElement(forwardDaysField).clear();
        CommonTask.setInputField(driver, forwardDaysField, item);
        return this;
    }

    public Ecomm_MOQ_Methods_Page disableFordwardOrderDay(){
        CommonTask.unSetCheckBox(driver, forwardDaysCheckBox);
        return this;
    }



    public Ecomm_MOQ_Methods_Page saveSalesOrg() {
        Actions action = new Actions(driver);
        action.click(driver.findElement(saveSalesOrgButton)).build().perform();
        return this;
    }

    public Ecomm_MOQ_Methods_Page saveCustomer() {
        Actions action = new Actions(driver);
        action.click(driver.findElement(saveCustomerButton)).build().perform();
        return this;
    }


    //MOQ Disabled, PartialStock Enabled, Forward Order Days: enabled , Forward Order days: 10
    public Ecomm_MOQ_Methods_Page setProfile01(){

        System.out.println("Sales organization: Going to sales organization edit page...");

        goToSalesOrgAndEdit();

        System.out.println("Sales organization: Enabling Partial...");

        enablePartialStockCheckBox();

        System.out.println("Sales organization: Forwading Day and setting it to 10...");

        enableFordwardOrderDayAndSetForwardDate10();

        System.out.println("Sales organization: Saving changes...");

        saveSalesOrg();

        System.out.println("Customer: Going to customer edit page...");

        goToCustomerAndEdit();

        System.out.println("Customer: Disabling MOQ Checkbox...");

        disableMOQCheckBox();

        System.out.println("Customer: Saving customers changes...");

        saveCustomer();
        return this;
    }

    //MOQ Enabled, PartialStock Enabled, Forward Order Days: enabled , Forward Order days: 10
    public Ecomm_MOQ_Methods_Page setProfile02(){

        System.out.println("Sales organization: Going to sales organization edit page...");

        goToSalesOrgAndEdit();

        System.out.println("Sales organization: Enabling Partial...");

        enablePartialStockCheckBox();

        System.out.println("Sales organization: Forwading Day and setting it to 10...");

        enableFordwardOrderDayAndSetForwardDate10();

        System.out.println("Sales organization: Saving changes...");

        saveSalesOrg();

        System.out.println("Customer: Going to customer edit page...");

        goToCustomerAndEdit();

        System.out.println("Customer: Enabling MOQ Checkbox...");

        enableMOQCheckBox();

        System.out.println("Customer: Saving customers changes...");

        saveCustomer();
        return this;
    }

    //MOQ Enabled, PartialStock Disabled, Forward Order Days: enabled , Forward Order days: 10
    public Ecomm_MOQ_Methods_Page setProfile03(){

        System.out.println("Sales organization: Going to sales organization edit page...");

        goToSalesOrgAndEdit();

        System.out.println("Sales organization: Disable Partial Stock...");

        disablePartialStockCheckBox();

        System.out.println("Sales organization: Forwarding Day and setting it to 10...");

        enableFordwardOrderDayAndSetForwardDate10();

        System.out.println("Sales organization: Saving changes...");

        saveSalesOrg();

        System.out.println("Customer: Going to customer edit page...");

        goToCustomerAndEdit();

        System.out.println("Customer: Enabling MOQ Checkbox...");

        enableMOQCheckBox();

        System.out.println("Customer: Saving customers changes...");

        saveCustomer();
        return this;
    }

    //MOQ Enabled, PartialStock Disabled, Forward Order Days: disasbled , Forward Order days: none
    public Ecomm_MOQ_Methods_Page setProfile04(){

        System.out.println("Sales organization: Going to sales organization edit page...");

        goToSalesOrgAndEdit();

        System.out.println("Sales organization: Enabling Partial...");

        disablePartialStockCheckBox();

        System.out.println("Sales organization: Disabling fordard days checkbox...");

        disableFordwardOrderDay();

        System.out.println("Sales organization: Saving changes...");

        saveSalesOrg();

        System.out.println("Customer: Going to customer edit page...");

        goToCustomerAndEdit();

        System.out.println("Customer: Enabling MOQ Checkbox...");

        enableMOQCheckBox();

        System.out.println("Customer: Saving customers changes...");

        saveCustomer();
        return this;
    }

    //MOQ Enabled, PartialStock Enabled, Forward Order Days: enabled , Forward Order days: 1
    public Ecomm_MOQ_Methods_Page setProfile05(){

        System.out.println("Sales organization: Going to sales organization edit page...");

        goToSalesOrgAndEdit();

        System.out.println("Sales organization: Enabling Partial...");

        enablePartialStockCheckBox();

        System.out.println("Sales organization: Forwading Day and setting it to 10...");

        enableFordwardOrderDayAndSetForwardDate01();

        System.out.println("Sales organization: Saving changes...");

        saveSalesOrg();

        System.out.println("Customer: Going to customer edit page...");

        goToCustomerAndEdit();

        System.out.println("Customer: Enabling MOQ Checkbox...");

        enableMOQCheckBox();

        System.out.println("Customer: Saving customers changes...");

        saveCustomer();
        return this;
    }

    public Ecomm_MOQ_Methods_Page goEccomManualEntry() {
        Actions action = new Actions(driver);
        action.click(driver.findElement(chooseTheOtherProfile)).build().perform();

        System.out.println("Navigating to Manual Order Entry Page...");

        driver.get(DataItems.manualEntryEcommURL);

        System.out.println("Checking Manual Order Entry Page is reached...");

        waitForManualEntryPageToOpen();

        return this;
    }

    public Ecomm_MOQ_Methods_Page waitForManualEntryPageToOpen() {
        WebElement wait = Wait.clickable(driver,customerNameMEField);
        return this;
    }

    public Ecomm_MOQ_Methods_Page setCustomerDetails() {

        System.out.println("Manual Entry Page: Setting Customer Details");

        setCustomerMEName(DataItems.customerName);

        System.out.println("Manual Entry Page: Setting Customer ame");

        setShipToPartyME(DataItems.shipToParty);

        System.out.println("Manual Entry Page: Setting Ship To Party");

        setRequesterNameME(DataItems.requesterName);

        System.out.println("Manual Entry Page: Setting Requester Name");

        setCustomerPONrME(DataItems.nullPO);

        System.out.println("Manual Entry Page: Setting Null PO");

        return this;
    }

    public Ecomm_MOQ_Methods_Page setCustomerMEName(String item){
        CommonTask.setSearchField(driver, customerNameMEField, item);
        return this;
    }

    public Ecomm_MOQ_Methods_Page setShipToPartyME(String item) {
        CommonTask.setDropDownField(driver, shipToPartyMEField, item);
        return this;
    }

    public Ecomm_MOQ_Methods_Page setRequesterNameME(String item){
        CommonTask.setDropDownField(driver, requesterNameMEField, item);
        return this;
    }

    public Ecomm_MOQ_Methods_Page setBuyersME(String item){
        CommonTask.setSearchField(driver, buyersMEField, item);
        return this;
    }

    public Ecomm_MOQ_Methods_Page setCustomerPONrME(String item){
        CommonTask.setInputField(driver, customerPONrMEField, item);
        return this;
    }

    public Ecomm_MOQ_Methods_Page pressNext1() {

        //Wait for button to be clickable
        WebElement next = Wait.clickable(driver,nextButton);
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
        if (alertPresence) {
            driver.switchTo().alert().accept();
        }

        return this;
    }

    public Ecomm_MOQ_Methods_Page pressNext2() {

        //Wait for button to be clickable
        WebElement next = Wait.clickable(driver,nextButton);
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

        return this;
    }




}
