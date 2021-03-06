
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_AddCustMaterialPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("CustomerMaterialSalesOrgId");
    By custNameField = By.id("s2id_CustomerMaterialCustomerId");
    By custMaterialNoField = By.id("CustomerMaterialCustomerMaterialNo");
    By coatsArticleField = By.id("s2id_CustomerMaterialArticleId");
    By coatsBrandField = By.id("CustomerMaterialBrandId");
    By coatsTicketField = By.id("CustomerMaterialTicketId");
    By coatsLengthField = By.id("CustomerMaterialLengthId");
    By coatsFinishField = By.id("CustomerMaterialFinishId");
    By coatsShadeField = By.id("s2id_CustomerMaterialShadeId");
    By saveButton = By.cssSelector("#CustomerMaterialAddForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CustomerMaterialAddForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_AddCustMaterialPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return Wait.visible(driver,DataItems.breadcrumbLocator);
    }
    
    public Mst_AddCustMaterialPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,salesOrgField,item);
        return new Mst_AddCustMaterialPage(driver);
    }
    
    public Mst_AddCustMaterialPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver,custNameField,item);
        return new Mst_AddCustMaterialPage(driver);
    }
    
    public Mst_AddCustMaterialPage setCustomerMaterialNo(String item) throws InterruptedException {
        CommonTask.setInputField(driver,custMaterialNoField,item);
        return new Mst_AddCustMaterialPage(driver);
    }
    
    public Mst_AddCustMaterialPage setArticle(String item) throws InterruptedException {
        CommonTask.setSearchField(driver,coatsArticleField,item);
        return new Mst_AddCustMaterialPage(driver);
    }
    
    public Mst_AddCustMaterialPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,coatsBrandField,item);
        return new Mst_AddCustMaterialPage(driver);
    }
    
    public Mst_AddCustMaterialPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,coatsTicketField,item);
        return new Mst_AddCustMaterialPage(driver);
    }
    
    public Mst_AddCustMaterialPage setLength(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,coatsLengthField,item);
        return new Mst_AddCustMaterialPage(driver);
    }
    
    public Mst_AddCustMaterialPage setFinish(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,coatsFinishField,item);
        return new Mst_AddCustMaterialPage(driver);
    }
    
    public Mst_AddCustMaterialPage setShadeCode(String item) throws InterruptedException {
        CommonTask.setSearchField(driver,coatsShadeField,item);
        return new Mst_AddCustMaterialPage(driver);
    }
    
    public Mst_CustMaterialsPage pressSave() {
        WebElement element = Wait.clickable(driver,saveButton);
        element.click();
        
        return new Mst_CustMaterialsPage(driver);
    }
    
    public Mst_CustMaterialsPage pressCancel() {
        WebElement element = Wait.clickable(driver,cancelButton);
        element.click();
        
        return new Mst_CustMaterialsPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = Wait.clickable(driver,salesOrgField);
        WebElement custName = Wait.clickable(driver,custNameField);
        WebElement custMatNo = Wait.clickable(driver,custMaterialNoField);
        WebElement article = Wait.clickable(driver,coatsArticleField);
        WebElement brand = Wait.clickable(driver,coatsBrandField);
        WebElement ticket = Wait.clickable(driver,coatsTicketField);
        WebElement length = Wait.clickable(driver,coatsLengthField);
        WebElement finish = Wait.clickable(driver,coatsFinishField);
        WebElement shadeCode = Wait.clickable(driver,coatsShadeField);
        WebElement save = Wait.clickable(driver,saveButton);
        WebElement cancel = Wait.clickable(driver,cancelButton);
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Add Customer Material Page: Sales Organisation Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Material Page: Customer Name Field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Material Page: Customer Material No. Field not displayed",custMatNo.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Material Page: Article Field not displayed",article.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Material Page: Brand Field not displayed",brand.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Material Page: Ticket Field not displayed",ticket.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Material Page: Length Field not displayed",length.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Material Page: Finish Field not displayed",finish.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Material Page: Shade Code Field not displayed",shadeCode.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Material Page: Save button not displayed",save.isDisplayed());
        AssertJUnit.assertTrue("Add Customer Material Page: Cancel button not displayed",cancel.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement shadeCode = Wait.clickable(driver,coatsShadeField);
    }
    
}
