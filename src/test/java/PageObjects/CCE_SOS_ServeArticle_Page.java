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

public class CCE_SOS_ServeArticle_Page extends WBA_BasePage {

    public CCE_SOS_ServeArticle_Page(WebDriver passedDriver) {
            super(passedDriver);
        }

    // ------------------------------------- Locators

    // Warehouse/Hub Locators
    By salesOrgField = By.id("OurStockSalesOrgId");
    By plantField = By.id("OurStockPlantId");
    By articleField = By.id("s2id_OurStockArticleId");
    By brandField = By.id("OurStockBrandId");
    By ticketField = By.id("OurStockTicketId");
    By mumTypeField = By.id("OurStockMumTypeId");
    By shadeField = By.id("s2id_OurStockShadeId");
    By quantityField = By.id("OurStockQuantity");
    By saveButton = By.cssSelector("#OurStockAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#OurStockAddForm > div.actions > ul > li:nth-child(2) > a");



    public void  setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, salesOrgField, item);
    }

    public void  etPlant(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, plantField, item);
        
    }

    public void setArticle(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, articleField, item);
        
    }

    public void setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, brandField, item);
        
    }

    public void setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, ticketField, item);
        
    }

    public void setMUMType(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver, mumTypeField, item);
        
    }

    public void setShade(String item) throws InterruptedException {
        CommonTask.setSearchField(driver, shadeField, item);
        
    }

    public void setQuantity(String item) throws InterruptedException {
        WebElement element = Wait.clickable(driver,quantityField);
        element.clear();

        CommonTask.setInputField(driver, quantityField, item);
        
    }


}
