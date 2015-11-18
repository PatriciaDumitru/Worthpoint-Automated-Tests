
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import static PageObjects.WBA_BasePage.driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_EditCustMaterialPage extends WBA_BasePage {
    
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
    By saveButton = By.cssSelector("#CustomerMaterialEditForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By cancelButton = By.cssSelector("#CustomerMaterialEditForm > div.actions > ul > li:nth-child(2) > a");
    
    public Mst_EditCustMaterialPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }
    
    public Mst_EditCustMaterialPage setSalesOrg(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,salesOrgField,item);
        return new Mst_EditCustMaterialPage(driver);
    }
    
    public Mst_EditCustMaterialPage setCustomerName(String item) throws InterruptedException {
        CommonTask.setSearchField(driver,custNameField,item);
        return new Mst_EditCustMaterialPage(driver);
    }
    
    public Mst_EditCustMaterialPage setCustomerMaterialNo(String item) throws InterruptedException {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custMaterialNoField));
        element.clear();
        
        CommonTask.setInputField(driver,custMaterialNoField,item);
        return new Mst_EditCustMaterialPage(driver);
    }
    
    public Mst_EditCustMaterialPage setArticle(String item) throws InterruptedException {
        CommonTask.setSearchField(driver,coatsArticleField,item);
        return new Mst_EditCustMaterialPage(driver);
    }
    
    public Mst_EditCustMaterialPage setBrand(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,coatsBrandField,item);
        return new Mst_EditCustMaterialPage(driver);
    }
    
    public Mst_EditCustMaterialPage setTicket(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,coatsTicketField,item);
        return new Mst_EditCustMaterialPage(driver);
    }
    
    public Mst_EditCustMaterialPage setLength(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,coatsLengthField,item);
        return new Mst_EditCustMaterialPage(driver);
    }
    
    public Mst_EditCustMaterialPage setFinish(String item) throws InterruptedException {
        CommonTask.setDropDownField(driver,coatsFinishField,item);
        return new Mst_EditCustMaterialPage(driver);
    }
    
    public Mst_EditCustMaterialPage setShadeCode(String item) throws InterruptedException {
        CommonTask.setSearchField(driver,coatsShadeField,item);
        return new Mst_EditCustMaterialPage(driver);
    }
    
    public Mst_CustMaterialsPage pressSave() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        element.click();
        
        return new Mst_CustMaterialsPage(driver);
    }
    
    public Mst_CustMaterialsPage pressCancel() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        element.click();
        
        return new Mst_CustMaterialsPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement custMatNo = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custMaterialNoField));
        WebElement article = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsArticleField));
        WebElement brand = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsBrandField));
        WebElement ticket = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsTicketField));
        WebElement length = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsLengthField));
        WebElement finish = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsFinishField));
        WebElement shadeCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsShadeField));
        WebElement save = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(saveButton));
        WebElement cancel = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(cancelButton));
        
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
        WebElement shadeCode = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(coatsShadeField));
    }
    
}
