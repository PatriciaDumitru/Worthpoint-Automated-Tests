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



        //Manual Upload Page



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
        driver.get(DataItems.mastersCusotmersURL);
        setCustomerName(DataItems.custNameFilterString);
        filterCustomerName();
        editCustomerName();
        waitForCustomerEditPageToOpen();
        return this;
    }

    public Ecomm_MOQ_Methods_Page setCustomerName(String item) {
        CommonTask.setSearchField(driver, salesOrgIdField, item);
        return this;
    }

    public Ecomm_MOQ_Methods_Page filterCustomerName() {
        Actions action = new Actions(driver);
        action.click(driver.findElement(filterCustomerButton)).build().perform();
        return this;
    }

    public Ecomm_MOQ_Methods_Page editCustomerName() {
        Actions action = new Actions(driver);
        action.click(driver.findElement(editSalesOrgButton)).build().perform();
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
        CommonTask.uncheckBox(driver, partialStockCheckBox);

        return this;
    }

    public Ecomm_MOQ_Methods_Page enableMOQCheckBox(){
        CommonTask.setCheckBox(driver, mdqCheckBox);
        return this;
    }

    public Ecomm_MOQ_Methods_Page disableMOQCheckBox(){
        CommonTask.setCheckBox(driver, mdqCheckBox);
        return this;
    }

    public Ecomm_MOQ_Methods_Page enableFordwardOrderDayAndSetForwardDate(){
        enableFordwardOrderDay();
        setForwardDate(DataItems.forwardDays);
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
        CommonTask.uncheckBox(driver, forwardDaysCheckBox);
        return this;
    }



    public Ecomm_MOQ_Methods_Page saveSalesOrg() {
        Actions action = new Actions(driver);
        action.click(driver.findElement(saveSalesOrgButton)).build().perform();
        return this;
    }


}
