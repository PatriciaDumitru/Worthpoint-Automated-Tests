
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


public class Mst_CustBusinessPrincipalPage extends WBA_BasePage {
    
    //Locators
    By salesOrgField = By.id("s2id_filterSalesOrgId");
    By custNameField = By.id("s2id_filterCustomerId");
    By searchButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(1) > input[type=\"submit\"]");
    By resetButton = By.cssSelector("#FilterIndexForm > div.actions > ul > li:nth-child(2) > a");
    By importButton = By.cssSelector("#content > div.actions > ul > li:nth-child(1) > a");
    By exportButton = By.cssSelector("#export-menu > a");
    By newCustBPButton = By.cssSelector("#content > div.actions > ul > li:nth-child(3) > a");

    By coatsPrincipalNameField = By.id("s2id_filterBusinessPrincipalId");

    public Mst_CustBusinessPrincipalPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getBreadcrumb() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(DataItems.breadcrumbLocator));
        return element;
    }
    
    public Mst_CustBusinessPrincipalPage setSalesOrg(String item) {
        CommonTask.setSearchField(driver,salesOrgField,item);
        return new Mst_CustBusinessPrincipalPage(driver);
    }
    
    public Mst_CustBusinessPrincipalPage setCustomerName(String item) {
        CommonTask.setSearchField(driver,custNameField,item);
        return new Mst_CustBusinessPrincipalPage(driver);
    }
    
    public Mst_CustBusinessPrincipalPage pressSearch() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        element.click();
        return new Mst_CustBusinessPrincipalPage(driver);
    }
    
    public Mst_CustBusinessPrincipalPage pressReset() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        element.click();
        return new Mst_CustBusinessPrincipalPage(driver);
    }
    
    public Mst_AddCustBusPrincPage pressNew() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCustBPButton));
        element.click();
        
        return new Mst_AddCustBusPrincPage(driver);
    }
    
    public int getRow(String name) {
        By headerLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(4) > a");
        WebElement header = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
        
        By recordsField = By.cssSelector("#content > div.flexi-grid > dl > dt > span.left");
        int count = this.getRecordCount(recordsField);

        for (int i = 2; i < (count+2); i++) {
            By cellLocator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+i+") > td:nth-child(4)");
            WebElement cell = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(cellLocator));
            System.out.println(cell.getText().trim());
            if (cell.getText().trim().equals(name)) {
                System.out.println("equality found");
                return i;
            }
        }
        return -1;        
    }

    public int getNrOfEntry() {
        By brandHeader = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child(1) > th:nth-child(4) > a");
        WebElement header = new WebDriverWait(driver, DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(brandHeader));

        AssertJUnit.assertTrue("Customer Brands Page: Customer Brand column has moved, update locators", header.getText().equals("Customer Business Principal"));

        int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();

        if (nrOfEntry > 1){
            return 1;
        }
        return -1;
    }
    
    public Mst_EditCustBusPrincPage pressEdit(int row) {  
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(1) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        return new Mst_EditCustBusPrincPage(driver);
    }
    
    public Mst_CustBusinessPrincipalPage pressDelete(int row) {  
        By locator = By.cssSelector("#content > div.flexi-grid > table > tbody > tr:nth-child("+row+") > td.actions > a:nth-child(3) > span");
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        return new Mst_CustBusinessPrincipalPage(driver);
    }
    
    public CCE_ExportDownloadPage pressExport() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        
        By xlsx = By.linkText("XLSX");
        WebElement xlsxBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(xlsx));
        xlsxBtn.click();
        
        return new CCE_ExportDownloadPage(driver);
    }
    
    public Mst_ImportPage pressImport() {
        WebElement element = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        element.click();
        return new Mst_ImportPage(driver);
    }
    
    public void checkFields() {
        //Wait for all elements to be clickable
        WebElement salesOrg = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(salesOrgField));
        WebElement custName = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(custNameField));
        WebElement search = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(searchButton));
        WebElement reset = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(resetButton));
        WebElement importBtn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(importButton));
        WebElement export = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(exportButton));
        WebElement newCustBP = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCustBPButton));
        
        //Assert all elements are displayed
        AssertJUnit.assertTrue("Customer Business Principal page: Sales Organisation field not displayed",salesOrg.isDisplayed());
        AssertJUnit.assertTrue("Customer Business Principal page: Customer Name field not displayed",custName.isDisplayed());
        AssertJUnit.assertTrue("Customer Business Principal page: Search button not displayed",search.isDisplayed());
        AssertJUnit.assertTrue("Customer Business Principal page: Reset button not displayed",reset.isDisplayed());
        AssertJUnit.assertTrue("Customer Business Principal page: Import button not displayed",importBtn.isDisplayed());
        AssertJUnit.assertTrue("Customer Business Principal page: Export button not displayed",export.isDisplayed());
        AssertJUnit.assertTrue("Customer Business Principal page: New Customer Business Principal button not displayed",newCustBP.isDisplayed());
    }
    
    public void waitForElement() {
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(newCustBPButton));
    }

    public Mst_AddCustBusPrincPage setCoatsBusPrinc(String item){
        CommonTask.setSearchField(driver, coatsPrincipalNameField, item);
        return new Mst_AddCustBusPrincPage(driver);
    }

    public void deleteCustBusPrincipal(){
        int nrOfEntry = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr")).size();
        System.out.println(nrOfEntry - 1 +" Customer business principal matching the test criteria found ");

        for(int i = nrOfEntry;i > 1; i--)
        {
            pressDelete(2);
            setSalesOrg("ID51");
            setCustomerName(DataItems.custDetails[0]);
            setCoatsBusPrinc(DataItems.custDetails[3]);
            pressSearch();
            waitForElement();
        }
        System.out.println("Customer business principal cleared");
    }

    public boolean checkRecordDetails(String... detail){

        //Getting the number of table columns
        int nrOfCol = driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[2]/td")).size();
        System.out.println(nrOfCol+" found");

        boolean check = false;

        for(int i=2; i < nrOfCol - 1;i++){
            By fieldLocator = By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[2]/td["+i+"]");

            //Getting the text from columns
            String text = driver.findElement(fieldLocator).getText().trim();
            System.out.println(text);
            System.out.println(detail[i-2]);

            //Checking if the columns matches the data
            if(text.equals( detail[i-2] )){
                System.out.println("Column number "+(i-1)+" checked");
                check = true;
            } else {
                System.out.println("Column number "+(i-1)+" does not match the data");
                check = false;
                break;
            }
        }

        return check;
    }

}
