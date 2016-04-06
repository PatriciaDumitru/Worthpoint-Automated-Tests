package PageObjects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.AssertJUnit;


public class Ecomm_ReportingPaginationChecks_Page {

    WebDriver driver;


    // ---------------------- Locators

    By pagination = By.xpath(".//*[@id='recs-pp']/a");

    //Invoice
    By orderDateFromField = By.id("filterSapccInvoiceCreatedDateFrom");
    By orderDateToField = By.id("filterSapccInvoiceCreatedDateTo");
    By searchButtonInvoice = By.cssSelector("#FilterInvoiceForm > div.grid_12 > table > tbody > tr:nth-child(7) > td > div > input");
    By resetButtonInvoice = By.cssSelector("#FilterInvoiceForm > div.grid_12 > table > tbody > tr:nth-child(7) > td > a");

    //Delivery Notes
    By delDateFromField = By.id("filterSapccDeliverynoteDeliveryDateFrom");
    By delDateToField = By.id("filterSapccDeliverynoteDeliveryDateTo");
    By searchButtonDN = By.cssSelector("#FilterDeliverynoteForm > div.grid_12 > table > tbody > tr:nth-child(7) > td > div > input");
    By resetButtonDN = By.cssSelector("#FilterDeliverynoteForm > div.grid_12 > table > tbody > tr:nth-child(7) > td > a");


    //Summary of Purchase
    By purchaseDateFromField = By.id("filterSapccPurchasePoDateFrom");
    By purchaseDateToField = By.id("filterSapccPurchasePoDateTo");
    By searchButtonSoP = By.cssSelector("#FilterPurchaseForm > div.grid_12 > table > tbody > tr:nth-child(6) > td > div > input");
    By resetButtonSoP = By.cssSelector("#FilterPurchaseForm > div.grid_12 > table > tbody > tr:nth-child(6) > td > a");

    //Outstanding Payments
    By by30Button = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(4) > label:nth-child(2)");
    By by60Button = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(4) > label:nth-child(5)");
    By by90Button = By.cssSelector("#FilterPaymentForm > div.grid_12 > table > tbody > tr:nth-child(1) > td:nth-child(4) > label:nth-child(8)");
    By searchButtonOP = By.cssSelector("#FilterPurchaseForm > div.grid_12 > table > tbody > tr:nth-child(6) > td > div > input");
    By resetButtonOP = By.cssSelector("#FilterPurchaseForm > div.grid_12 > table > tbody > tr:nth-child(6) > td > a");

    //Order Approval History
    By creationDateFromField = By.id("filterBulkOrderCreatedFrom");
    By creationDateToField = By.id("filterBulkOrderCreatedTo");
    By searchButtonOAH = By.cssSelector("#FilterApprovalhistoryForm > div.grid_12 > table > tbody > tr:nth-child(6) > td > div > input");
    By resetButtonOAH = By.cssSelector("#FilterApprovalhistoryForm > div.grid_12 > table > tbody > tr:nth-child(6) > td > a");
By test = By.xpath("//*[@id=\"s2id_filterSapccInvoiceCustomerId\"]/a/span[1]");

    // ---------------------- Methods

    public boolean paginationIsDisplayed(WebDriver driver){
         return driver.findElement(pagination).isDisplayed();
    }


    //Invoice
    public void setOrderDateFrom(WebDriver driver){
        CommonTask.setSearchField(driver, test, "Life Easy Customer");
        CommonTask.setDateField(driver, orderDateFromField);
    }
    public void setOrderDateTo(WebDriver driver){
        CommonTask.setDateField(driver, orderDateToField);
    }
    public void resetFilterInvocie(WebDriver driver){
        Actions action = new Actions(driver);
        action.click(driver.findElement(resetButtonInvoice)).build().perform();
    }
    public void searchFilterInvoice(WebDriver driver){
        Actions action = new Actions(driver);
        action.click(driver.findElement(searchButtonInvoice)).build().perform();

    }

    //Delivery Notes
    public void setDelDateFromField(WebDriver driver){
        CommonTask.setDateField(driver, delDateFromField);
    }
    public void setDelDateToField(WebDriver driver){
        CommonTask.setDateField(driver, delDateToField);
    }
    public void resetFilterDeliveryNotes(WebDriver driver){
        Actions action = new Actions(driver);
        action.click(driver.findElement(resetButtonDN)).build().perform();
    }
    public void searchFilterDeliveryNotes(WebDriver driver){
        Actions action = new Actions(driver);
        action.click(driver.findElement(searchButtonDN)).build().perform();

    }

    //Summary of Purchase
    public void setPurchaseDateFromField(WebDriver driver){
        CommonTask.setDateField(driver, purchaseDateFromField);
    }
    public void setPurchaseDateToField(WebDriver driver){
        CommonTask.setDateField(driver, purchaseDateToField);
    }
    public void resetFilterSoP(WebDriver driver){
        Actions action = new Actions(driver);
        action.click(driver.findElement(resetButtonSoP)).build().perform();
    }
    public void searchFilterSoP(WebDriver driver){
        Actions action = new Actions(driver);
        action.click(driver.findElement(searchButtonSoP)).build().perform();

    }

    //Outstanding Payments
    public void set30Option(WebDriver driver){
        CommonTask.setCheckBox(driver, by30Button);
    }
    public void set60Option(WebDriver driver){
        CommonTask.setCheckBox(driver, by60Button);
    }
    public void set90Option(WebDriver driver){
        CommonTask.setCheckBox(driver, by90Button);
    }
    public void resetFilterOP(WebDriver driver){
        Actions action = new Actions(driver);
        action.click(driver.findElement(resetButtonOP)).build().perform();
    }
    public void searchFilterOP(WebDriver driver){
        Actions action = new Actions(driver);
        action.click(driver.findElement(searchButtonOP)).build().perform();

    }

    //Order Approval History
    public void setCreationDateFromField(WebDriver driver){
        CommonTask.setDateField(driver, creationDateFromField);
    }
    public void setCreationDateToField(WebDriver driver){
        CommonTask.setDateField(driver, creationDateToField);
    }
    public void resetFilterOAH(WebDriver driver){
        Actions action = new Actions(driver);
        action.click(driver.findElement(resetButtonOAH)).build().perform();
    }
    public void searchFilterOAH(WebDriver driver){
        Actions action = new Actions(driver);
        action.click(driver.findElement(searchButtonOAH)).build().perform();

    }


}
