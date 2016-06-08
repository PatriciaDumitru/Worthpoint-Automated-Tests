package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_CustomerPrivateArticlesPage extends WBA_BasePage {

    //Locators
    By customerNameField = By.id("filterCustomerCustomerName");
    By salesOraganizationField = By.id("s2id_filterCustomerPrivateArticleSalesOrgId");
    By articleField = By.id("s2id_autogen2");
    By customerCodeField = By.id("filterCustomerCustomerCode");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By newPrivateArticleField = By.xpath("/html/body/div[1]/div[3]/div[3]/ul/li[2]/a");

    By salesOrgField = By.xpath("//*[@id=\"s2id_filterCustomerPrivateArticleSalesOrgId\"]");

    public Mst_CustomerPrivateArticlesPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getBreadcrumb() {
        return new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
    }

    public Mst_CustomerPrivateArticlesPage setCustomerName(String item) {
        CommonTask.setTextField(driver, customerNameField, item);
        return new Mst_CustomerPrivateArticlesPage(driver);
    }

    public Mst_CustomerPrivateArticlesPage setCustomerCode(String item) {
        CommonTask.setTextField(driver, customerCodeField, item);
        return new Mst_CustomerPrivateArticlesPage(driver);
    }

    public Mst_CustomerPrivateArticlesPage setSalesOrganization(String item) {
        CommonTask.setSearchField(driver, salesOraganizationField, item);
        return new Mst_CustomerPrivateArticlesPage (driver);
    }

    public Mst_CustomerPrivateArticlesPage setArticle(String item) {
        CommonTask.setSearchField(driver, articleField, item);
        return new Mst_CustomerPrivateArticlesPage (driver);
    }

    public Mst_CustomerPrivateArticlesPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        return new Mst_CustomerPrivateArticlesPage(driver);
    }

    public Mst_EditCustomerPrivateArticlesPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        return new Mst_EditCustomerPrivateArticlesPage(driver);
    }


    public Mst_CustomerPrivateArticlesPage pressDelete(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();

        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();

        return new Mst_CustomerPrivateArticlesPage(driver);
    }

    public Mst_AddCustomerPrivateArticlesPage pressNewPrivateArticle() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newPrivateArticleField));
        element.click();

        return new Mst_AddCustomerPrivateArticlesPage(driver);
    }

    public int getRow(String title) {

        if (!checkForRecords()) {
            return -1;
        }

        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(2) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        AssertJUnit.assertTrue("Customer Private Articles Page: Customer name column has moved, update locators",header.getText().equals("Customer Name"));

        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = getRecordCount(recordsField);
        int tableCount = (count > 10) ? 10 : count;

        for (int i = 2; i < (tableCount+2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(2)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (cell.getText().equals(title)) {
                return i;
            }
        }
        return -1;
    }

    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement customerName = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
        WebElement customerCode = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerCodeField));
        WebElement salesOrg = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOraganizationField));
        WebElement search = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement newPrivateArticle = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newPrivateArticleField));


        //Assert all elements are clickable
        AssertJUnit.assertTrue("Customer Private Article Page: Customer Field not displayed", customerName.isDisplayed());
        AssertJUnit.assertTrue("Customer Private Article Page: CustomerCode Field not displayed", customerCode.isDisplayed());
        AssertJUnit.assertTrue("Customer Private Article Page: CustomerCode Field not displayed", salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Customer Private Article Page: Search Button not displayed", search.isDisplayed());
        AssertJUnit.assertTrue("Customer Private Article Page: Reset button not displayed", reset.isDisplayed());
        AssertJUnit.assertTrue("Customer Private Article Page: New Private Article button not displayed", newPrivateArticle.isDisplayed());

    }

    public void waitForElement() {
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
    }


    //Function to delete previously entered data

    public Mst_AddCustomerPrivateArticlesPage setSalesOrg(String item)  {
        CommonTask.setSearchField(driver, salesOrgField, item);
        return new Mst_AddCustomerPrivateArticlesPage(driver);
    }

    public void deleteCustPrivateArticle() {
        int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();
        System.out.println(nrOfEntry - 1 +" Private Test Articles found ");

        for(int i = nrOfEntry;i > 1; i--)
        {
            pressDelete(2);
            setSalesOrg("ID51");
            pressSearch();
            waitForElement();
        }
        System.out.println("Private Test Articles cleared");
    }

}
