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

public class CPA_Methods extends WBA_BasePage {

    public CPA_Methods(WebDriver passedDriver) {
        super(passedDriver);
    }


    //Locators

    //Customer Masters Page
    By customerNameField = By.id("filterCustomerCustomerName");
    By filterCustomerButton = By.xpath(".//*[@id='FilterIndexForm']/div[3]/ul/li[1]/input");
    By editCustomerButton = By.cssSelector("span.btn-edit");

    //Customer Edit Page
    By customerNameEditPageField = By.id("CustomerCustomerName");
    By customerDeliveryPlantIdField = By.id("CustomerDeliveryPlantId");
    By saveButtonCustomerPage = By.cssSelector("input[type=\"submit\"]");

    //CPA Page

    By addCSA = By.linkText("Assign Specific Article");

    //CPA Edit Page
    By csaSalesOrgField = By.id("CustomerPrivateArticleSalesOrgId");
    By enableCSARBtutton = By.cssSelector("option[value=\"11\"]");
    By customerNameFieldCSA = By.id("s2id_CustomerPrivateArticleCustomerId");
    By csaBrand = By.id("s2id_autogen3");
    By csaArticle = By.id("s2id_autogen2");
    By csaSaveButton = By.cssSelector("input[type=\"submit\"]");

    //CCE Order Sampel Prompt
    By cceOScustName = By.id("s2id_customer_id");
    By cceOSreqName = By.id("SampleOrderRequesterId");
    By saveCCEOSPrompt = By.xpath(".//*[@id='SampleOrderPromptForm']/div[3]/ul/li[1]/input");


    public CPA_Methods custSetup(String plant){
        driver.get(DataItems.mastersSalesOrgURL);
        driver.get(DataItems.mastersCustomerURL);
        driver.findElement(customerNameField).clear();
        driver.findElement(customerNameField).sendKeys("life easy");
        driver.findElement(filterCustomerButton).click();
        driver.findElement(editCustomerButton).click();
        new Select(driver.findElement(customerDeliveryPlantIdField)).selectByVisibleText(plant);
        driver.findElement(saveButtonCustomerPage).click();


        return this;
    }

    public void csaSetup(String salesOrg, String custName, String brand, String article) throws Exception {

        driver.get(DataItems.mastersCSAUrl);

        driver.findElement(addCSA).click();
        new Select(driver.findElement(csaSalesOrgField)).selectByVisibleText(salesOrg);

        CommonTask.setSearchField(driver, customerNameFieldCSA, custName);
        CommonTask.setChoiceField(driver, csaBrand, brand);
        CommonTask.setChoiceField(driver, csaArticle, article);

        driver.findElement(csaSaveButton).click();
    }

    public void cceOrderSampleSetup(String customer, String requester) throws Exception {

        driver.get(DataItems.cceOrderSamplePrompt);

        CommonTask.setSearchField(driver, cceOScustName, customer);
        CommonTask.setDropDownField(driver, cceOSreqName, requester);

        driver.findElement(saveCCEOSPrompt).click();

    }
}

