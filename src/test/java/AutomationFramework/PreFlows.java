package AutomationFramework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class PreFlows {

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
    By approvelCheckBoxSalesOrg = By.id("SalesOrgSalesApprovalWorkflow");
    By getCallOffOrderCheckBoxSalesOrg = By.id("SalesOrgOffOrder");

    //Customer Masters Page
    By customerNameField = By.id("filterCustomerCustomerName");
    By filterCustomerButton = By.xpath(".//*[@id='FilterIndexForm']/div[3]/ul/li[1]/input");
    By editCustomerButton= By.xpath(".//*[@id='content']/div[2]/table/tbody/tr[2]/td[7]/a[1]/span");

    //Customer Edit Page
    By customerNameEditPageField = By.id("CustomerCustomerName");
    By mdqCheckBox = By.id("CustomerCustMdqEnabled");
    By approvelCheckBoxCust = By.id("CustomerApprovalWorkflow");
    By saveCustomerButton = By.xpath(".//*[@id='CustomerEditForm']/div[4]/ul/li[1]/input");
    By deliveryPlant = By.id("CustomerDeliveryPlantId");
    By callOffOrderCheckBoxCust = By.id("CustomerOffOrder");
    By autoEnrichNoneOption = By.id("CustomerAutoEnrichOptions0");

    //Manual Entry Page
    By buyersField = By.xpath(".//*[@id='s2id_BuyerId']/a/abbr");
    By normalOrderRadioButton = By.id("normal_check");


    //Locator
    By logoutButton = By.xpath(".//*[@id='header']/div/span[2]/span[5]/a");

    public void goToSalesOrgAndEdit(WebDriver driver, String salesOrg){
        driver.get(DataItems.mastersSalesOrgURL);
        setSalesOrg(driver,salesOrg);
        filterSalesOrg(driver);
        editSalesOrg(driver);
        waitForSalesOrgEditPageToOpen(driver);
    }

    public void setDeliveryPlantAndEnableApprovelCheckboxForSalesOrgAndCust(WebDriver driver, String salesOrg, String customer, String plant){
        goToSalesOrgAndEdit(driver,salesOrg);
        enableApprovalCheckBoxForSalesOrg(driver);
        saveSalesOrg(driver);
        goToCustomerAndEdit(driver, customer);
        setDeliveryPlant(driver, plant);
        enableApprovalCheckBoxForCust(driver);
        saveCustomer(driver);
    }

    public void disableApprovelCheckBoxForSalesOrgAndCust(WebDriver driver, String salesOrg, String customer){
        goToSalesOrgAndEdit(driver,salesOrg);
        disableApprovalCheckBoxForSalesOrg(driver);
        saveSalesOrg(driver);
        goToCustomerAndEdit(driver, customer);
        disableApprovalCheckBoxForCust(driver);
        saveCustomer(driver);
    }

    public void setDeliveryPlant(WebDriver driver, String plant) {
        CommonTask.setDropDownField(driver, deliveryPlant, plant);
    }

    public void setSalesOrg(WebDriver driver, String item) {
        CommonTask.setInputField(driver, salesOrgIdField, item);
    }

    public void filterSalesOrg(WebDriver driver) {
        Actions action = new Actions(driver);
        action.click(driver.findElement(filterSalesOrgButton)).build().perform();
    }

    public void  editSalesOrg(WebDriver driver) {
        Actions action = new Actions(driver);
        action.click(driver.findElement(editSalesOrgButton)).build().perform();
    }

    public void  waitForSalesOrgEditPageToOpen(WebDriver driver) {
        WebElement wait = Wait.clickable(driver, salesOrgNameEditPageField);
    }

    public void goToCustomerAndEdit(WebDriver driver, String customer){
        driver.get(DataItems.mastersCustomerURL);
        setCustomerName(driver, customer);
        filterCustomerName(driver);
        editCustomerName(driver);
        waitForCustomerEditPageToOpen(driver);
    }

    public void  setCustomerName(WebDriver driver, String item) {
        CommonTask.setInputField(driver, customerNameField, item);
    }

    public void filterCustomerName(WebDriver driver) {
        Actions action = new Actions(driver);
        action.click(driver.findElement(filterCustomerButton)).build().perform();
    }

    public void editCustomerName(WebDriver driver) {
        Actions action = new Actions(driver);
        action.click(driver.findElement(editCustomerButton)).build().perform();
    }

    public void waitForCustomerEditPageToOpen(WebDriver driver) {
        WebElement wait = Wait.clickable(driver,customerNameEditPageField);
    }

    public void enableMOQForCustomer(WebDriver driver, String customer){
        goToCustomerAndEdit(driver, customer);
        enableMOQCheckBoxAndSaveCustomer(driver);
    }

    public void enableMOQCheckBoxAndSaveCustomer(WebDriver driver){
        enableMOQCheckBox(driver);
        saveCustomer(driver);
    }
    public void enableMOQCheckBox(WebDriver driver){
        CommonTask.setCheckBox(driver, mdqCheckBox);
    }

    public void disableMOQCheckBox(WebDriver driver){
        CommonTask.unSetCheckBox(driver, mdqCheckBox);
    }

    public void enableApprovalCheckBoxForCust(WebDriver driver){
        CommonTask.setCheckBox(driver, approvelCheckBoxCust);
    }

    public void disableApprovalCheckBoxForCust(WebDriver driver){
        CommonTask.unSetCheckBox(driver, approvelCheckBoxCust);
    }


    public void enableApprovalCheckBoxForSalesOrg(WebDriver driver){
        CommonTask.setCheckBox(driver, approvelCheckBoxSalesOrg);
    }
    public void disableApprovalCheckBoxForSalesOrg(WebDriver driver){
        CommonTask.unSetCheckBox(driver, approvelCheckBoxSalesOrg);
    }


    public void disableApprovalCheckBox(WebDriver driver){
        CommonTask.unSetCheckBox(driver, approvelCheckBoxCust);
    }

    public void saveSalesOrg(WebDriver driver) {
        Actions action = new Actions(driver);
        action.click(driver.findElement(saveSalesOrgButton)).build().perform();
    }

    public void saveCustomer(WebDriver driver) {
        Actions action = new Actions(driver);
        action.click(driver.findElement(saveCustomerButton)).build().perform();
    }

    public void chooseTheOtherProfile(WebDriver driver){
        Actions action = new Actions(driver);
        action.click(driver.findElement(chooseTheOtherProfile)).build().perform();
    }

    public void logoutAction(WebDriver driver){
        Actions action = new Actions(driver);
        action.click(driver.findElement(logoutButton)).build().perform();
    }

    public void removeBuyers(WebDriver driver){
        Actions action = new Actions(driver);
        action.click(driver.findElement(buyersField)).build().perform();
    }

    public void selectNormalOrderRadioButton(WebDriver driver){
        CommonTask.setCheckBox(driver, normalOrderRadioButton);
    }

    public void activateCallOffOrderForCustomer(WebDriver driver, String customer){
        goToCustomerAndEdit(driver, customer);
        activateCallOffOrderCheckboxCust(driver);
        saveCustomer(driver);
    }

    public void activateCallOffOrderForSalesOrg(WebDriver driver, String salesOrg){
        goToSalesOrgAndEdit(driver, salesOrg);
        activateCallOffOrderCheckboxSalesOrg(driver);
        saveSalesOrg(driver);
    }

    public void deActivateCallOffOrderForCustomer(WebDriver driver, String customer){
        goToCustomerAndEdit(driver, customer);
        deActivateCallOffOrderCheckbox(driver);
        saveCustomer(driver);
    }
    public void activateCallOffOrderAndDeactivateApprovalForCustomer(WebDriver driver, String customer) {
        goToCustomerAndEdit(driver, customer);
        activateCallOffOrderCheckboxCust(driver);
        disableApprovalCheckBox(driver);
        saveCustomer(driver);
    }

    public void deActivateCallOffOrderAndApprovalForCustomer(WebDriver driver, String customer){
        goToCustomerAndEdit(driver, customer);
        deActivateCallOffOrderCheckbox(driver);
        disableApprovalCheckBox(driver);
        saveCustomer(driver);

    }

    public void activateCallOffOrderCheckboxCust(WebDriver driver){
        CommonTask.setCheckBox(driver,callOffOrderCheckBoxCust);
    }

    public void activateCallOffOrderCheckboxSalesOrg(WebDriver driver){
        CommonTask.setCheckBox(driver,getCallOffOrderCheckBoxSalesOrg);
    }

    public void deActivateCallOffOrderCheckbox(WebDriver driver){
        CommonTask.unSetCheckBox(driver,callOffOrderCheckBoxCust);
    }

    public void deActivateAutoEnrichForCustomer(WebDriver driver, String customer){
        goToCustomerAndEdit(driver, customer);
        deActivateAutoEnrich(driver);
        saveCustomer(driver);
    }

    public void deActivateAutoEnrich(WebDriver driver){
        CommonTask.setCheckBox(driver, autoEnrichNoneOption);
    }





}
