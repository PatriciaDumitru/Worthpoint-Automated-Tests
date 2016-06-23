
package PageObjects;

import AutomationFramework.CommonTask;
import AutomationFramework.DataItems;
import AutomationFramework.Wait;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Mst_SubAccountPage extends WBA_BasePage {
    
    //Locators
    By customerNameField = By.id("filterCustomerCustomerName");
    By subAccountNameField = By.id("filterPayerName");
    By salesOrgField = By.id("s2id_filterSalesOrgId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newSubAcctButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");
    By recordCountLabel = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
    
    public Mst_SubAccountPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(breadcrumbLocator));
        return element;
    }
    
    public Mst_SubAccountPage setCustomerName(String item) {
        CommonTask.setInputField(driver,customerNameField,item);
        return new Mst_SubAccountPage(driver);
    }
    
    public Mst_SubAccountPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver,salesOrgField,item);
        return new Mst_SubAccountPage(driver);
    }
    
    public Mst_SubAccountPage setSubAccountName(String item) {
        
        CommonTask.setInputField(driver,subAccountNameField,item);
        return new Mst_SubAccountPage(driver);
    }
    
    public String getSalesOrg() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        return element.getText();
    }
    
    public Mst_SubAccountPage pressSearch() {
        WebElement button = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        button.click();
        return new Mst_SubAccountPage(driver);
    }
    
    public Mst_SubAccountPage pressReset() {
        WebElement button = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        button.click();
        return new Mst_SubAccountPage(driver);
    }
    
    public Mst_SubAccountPage pressImport() {
        WebElement button = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        button.click();
        return new Mst_SubAccountPage(driver);
    }
    
    public CCE_ExportDownloadPage pressExport() {
        WebElement button = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        
        Actions action = new Actions(driver);
        action.moveToElement(button).build().perform();
        
        By xlsx = By.cssSelector("#export-menu > ul > li:nth-child(4) > a");
        WebElement xlsxBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(xlsx));
        
        action.click(xlsxBtn).build().perform();
        
        return new CCE_ExportDownloadPage(driver);
    }
    
    public Mst_AddSubAccountPage pressNewSubAccount() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newSubAcctButton));
        element.click();
        
        return new Mst_AddSubAccountPage(driver);
    }
    
    public Mst_EditSubAccountPage pressEdit(int row) {
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditSubAccountPage(driver);
    }
    
    public Mst_SubAccountPage pressDelete(int row,String name) {
        
        //Check name is as expected before deletion
        By nameLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td:nth-child(5)");
        WebElement nameElement = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(nameLocator));
        
        AssertJUnit.assertTrue("Sub Account Page: Intended item for deletion does not match given Account Name. Deletion did not occur",nameElement.getText().equals(name));
        
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_SubAccountPage(driver);
    }
    
    public int getRow(String name) {
        
        if (!checkForRecords()) {
            return -1;
        }
        
        //Click cell near record count field to provide focus
        By cellLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(1)");
        WebElement cell = Wait.visible(driver,cellLocator);
        cell.click();
        
        int records = getRecordCount(recordCountLabel);
        int tableCount = (records > 10) ? 10 : records;
        
        for (int i = 2; i < (tableCount+2); i++) {
            By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(5)");
            WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (element.getText().equals(name)) {
                return i;
            }
        }
        return -1;
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(customerNameField));
        WebElement subAcct = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(subAccountNameField));
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement subAcctBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newSubAcctButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Sub Account Page: Customer Name Field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Sub Account Page: Sub-account Name Field not displayed",subAcct.isDisplayed());
        AssertJUnit.assertTrue("Sub Account Page: Sales Organisation Field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Sub Account Page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Sub Account Page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Sub Account Page: Import button not displayed",importBtn.isDisplayed());
        AssertJUnit.assertTrue("Sub Account Page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Sub Account Page: Sub Account button not displayed",subAcctBtn.isDisplayed());
        
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(subAccountNameField));
    }

    public void deleteSubAccount(){
        int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();
        System.out.println(nrOfEntry - 1 +" SubAccounts matching the test criteria found ");

        for(int i = nrOfEntry;i > 1; i--)
        {
            pressDelete(2, "AutoTest SubAccount");
            setSalesOrg("ID51");
            setSubAccountName("AutoTest SubAccount");
            pressSearch();
            waitForElement();
        }
        System.out.println("SubAccount cleared");
    }

    public static boolean checkRecordDetails(WebDriver driver,String... detail){

        //Getting the number of table columns
        int nrOfCol = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[2]/td")).size();
        System.out.println(nrOfCol+" found");

        //Variable used to return the result of the method
        boolean check = false;

        //The i starts from the column you want to check
        //Extract from the nrOfCol the number of the final columns you don't need to check (usually Status and Action columns)
        //The columns you don't check must be in a successive order
        for(int i=2; i <= nrOfCol - 1;i++){

            //fieldLocator is the location of each table column
            By fieldLocator = By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[2]/td["+i+"]");

            //Getting the text from columns
            String text = driver.findElement(fieldLocator).getText().trim();
            System.out.println(text);
            System.out.println(detail[i-2]);

            //Checking if the column matches the detail data, detail starts from detail[0]
            if(text.equals( detail[i-2] )){
                System.out.println("Column number "+ i +" checked");
                check = true;
            } else {
                System.out.println("Column number "+ i +" does not match the data");
                check = false;
                break;
            }
        }

        return check;
    }
}
