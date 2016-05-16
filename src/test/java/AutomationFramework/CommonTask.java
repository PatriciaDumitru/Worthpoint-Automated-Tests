
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
import org.w3c.dom.Element;


public class CommonTask {
    
    private static By contentLocator = By.id("content");
    private static By frameLocator = By.id("TB_iframeContent");
    private static By showingLocator = By.xpath("//*[@id='recs-pp']/preceding-sibling::span");
    private static By perPageMenu = By.cssSelector("#recs-pp > a");
    private static By option10 = By.cssSelector("#recs-pp > ul > li:nth-child(1) > a");
    private static By option25 = By.cssSelector("#recs-pp > ul > li:nth-child(2) > a");
    private static By option50 = By.cssSelector("#recs-pp > ul > li:nth-child(3) > a");
    private static By prevButton = By.cssSelector("#content > div.flexi-grid > dl > dd > span.prev");
    private static By nextButton = By.cssSelector("#content > div.flexi-grid > dl > dd > span.next");
    private static By lastButton = By.cssSelector("#content > div.flexi-grid > dl > dd > span.next + span");
    private static By adminHeader = By.cssSelector("#topnav > li:nth-child(8)");
    private static By mastersSubtab = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3)");

    //Reports locators
    private static By lastButtonReports = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > dl > dd > span:nth-child(12)");
    private static By prevButtonReports = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > dl > dd > span.prev");
    private static By nextButtonReports = By.cssSelector("#content > div.tbl-toggle.cc_grid_outer > div > div.scrollTableContainer > div > dl > dd > span.next");


    public static void setSearchField(WebDriver driver,By fieldLocator, String item) {
        //Set the value of search field (located by fieldLocator) to "item"
        
        //Locator for input field used to enter search term
        By searchLocator = By.cssSelector("#select2-drop > div > input");
        
        //Locator for result label which appears from search
        By resultLocator = By.cssSelector("#select2-drop > ul > li");
        
        //Wait for field to be available
        WebElement waitForClickable = Wait.clickable(driver, fieldLocator);
        
        //Click field, revealing search box
        Actions action = new Actions(driver);
        action.click(driver.findElement(fieldLocator)).build().perform();
        
        //Wait for search box to be available
        WebElement waitForSearch = Wait.presence(driver,searchLocator);
        
        //Enter search term
        driver.findElement(searchLocator).sendKeys(item);
        
        //Wait for search result appear
        Boolean waitForResult = Wait.textPresent(driver,resultLocator, item);
        
        //Press enter to select top result
        action.sendKeys(driver.findElement(searchLocator), Keys.ENTER).build().perform();
        
        //Wait for field to update
        boolean waitForUpdate = Wait.textPresent(driver,fieldLocator, item); 
    }

    public static void setSearchFieldAndCheckElementIsNotPresent(WebDriver driver,By fieldLocator, String item, String errorMSG) {
        //Set the value of search field (located by fieldLocator) to "item"

        //Locator for input field used to enter search term
        By searchLocator = By.cssSelector("#select2-drop > div > input");

        //Locator for result label which appears from search
        By resultLocator = By.cssSelector("#select2-drop > ul > li");

        //Wait for field to be available
        WebElement waitForClickable = Wait.clickable(driver, fieldLocator);

        //Click field, revealing search box
        Actions action = new Actions(driver);
        action.click(driver.findElement(fieldLocator)).build().perform();

        //Wait for search box to be available
        WebElement waitForSearch = Wait.presence(driver,searchLocator);

        //Enter search term
        driver.findElement(searchLocator).sendKeys(item);

        //Wait for search result appear
        Boolean waitForResult = Wait.textPresent(driver,resultLocator, errorMSG);


    }
    
    public static void resetSearchField(WebDriver driver, String id) {
        //Reset the value of search field (located by id). Achieves this by clicking small "x" within field
        
        //Locator for "x" button
        By resetButton = By.cssSelector("#"+id+" > a > abbr");
        
        //Wait for button to be available
        WebElement wait = Wait.clickable(driver,resetButton);
        
        //Click button, clearing field
        driver.findElement(resetButton).click();
        
        //Wait for field to be empty
        boolean waitForUpdate = Wait.textPresent(driver,By.id(id), ""); 
    }
    
    public static void setDropDownField(WebDriver driver,By fieldLocator,String item)  {
        //Set value of drop down field (referenced by fieldLocator) to "item"
        
        //Wait for field to be available
        WebElement wait = Wait.clickable(driver,fieldLocator);
        
        //"Select" object provides useful methods for drop-down fields
        Select select = new Select(driver.findElement(fieldLocator));
        driver.findElement(fieldLocator).click();
        
        //Wait for option to be visible
        Boolean waitForOption = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.optionPresent(item,select));
        
        //Select the option
        select.selectByVisibleText(item);
        
        //Wait for selection to be item
        boolean waitForSelection = Wait.selectionToBe(driver,fieldLocator,item);
    }

    public static boolean setDropDownFieldAndCheckThatElementIsNotPresent(WebDriver driver,By fieldLocator,String item)  {
        //Set value of drop down field (referenced by fieldLocator) to "item"

        //Wait for field to be available
        WebElement wait = Wait.clickable(driver,fieldLocator);

        //"Select" object provides useful methods for drop-down fields
        Select select = new Select(driver.findElement(fieldLocator));
        driver.findElement(fieldLocator).click();

        //Wait for option to be visible
        Boolean waitForOption = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.not(CommonTask.optionPresent(item,select)));

        return waitForOption;
    }
    
    public static void setDropDownFieldAlt(WebDriver driver, By fieldLocator, String item) {
        //Alternative method for setting values in drop-down fields
        
        //Wait for field to be available
        WebElement field = Wait.clickable(driver,fieldLocator);

        //Click field to reveal search field
        field.click();
        
        //Search field locator
        By searchFieldLocator = By.xpath("//input[contains(@id,'s2id_autogen')]");
        
        //Wait for search field to be available
        WebElement searchField = Wait.clickable(driver,searchFieldLocator);
        
        //Enter seach term and press enter to select
        searchField.sendKeys(item+Keys.ENTER);

        //Locator for "choice" item
        By choiceLocator = By.className("select2-search-choice");
        
        //Wait for choice item to be visible
        WebElement choice = Wait.visible(driver,choiceLocator);
    }
    
    public static void clearDropDownField(WebDriver driver, By fieldLocator) {
        //Clear selection from drop down field
        
        //Wait for field to be available
        WebElement wait = Wait.clickable(driver,fieldLocator);
        
        //Select object used to interact with field
        Select select = new Select(driver.findElement(fieldLocator));
        
        //Click field to reveal options
        driver.findElement(fieldLocator).click();
        
        //Select option 0, also displayed as "Select"
        select.selectByIndex(0);
                
    }
    
    public static void setChoiceField(WebDriver driver, By fieldLocator, String item) {
        //Enter "item" into choice field
        
        //Locator for result label
        By resultLocator = By.cssSelector("#select2-drop > ul > li.select2-results-dept-0.select2-result.select2-result-selectable.select2-highlighted");
        
        //Wait for element to be available
        WebElement waitForClickable = Wait.clickable(driver,fieldLocator);
        
        //Click element to focus search field
        driver.findElement(fieldLocator).click();
        
        //Enter item
        driver.findElement(fieldLocator).sendKeys(item);
        
        //Wait for result element, referenced above
        boolean waitForResult = Wait.textPresent(driver,resultLocator,item);
        
        //Press enter to select
        driver.findElement(fieldLocator).sendKeys(Keys.ENTER);
        
    }
    
    public static void setChoiceFieldAlt(WebDriver driver,By fieldLocator,String item){
        //An alternative method to enter "item" into a choice field
        
        //Locator for the result element
        By resultLocator = By.cssSelector("#select2-drop > ul > li");
        
        //Wait for field to be available
        WebElement waitForClickable = Wait.clickable(driver,fieldLocator);
        
        //Click field to focus search box
        driver.findElement(fieldLocator).click();
        
        //Enter "item"
        driver.findElement(fieldLocator).sendKeys(item);
        
        //Wait for result label to appear
        boolean waitForResult = Wait.textPresent(driver,resultLocator,item);
        
        //Press enter to select
        driver.findElement(fieldLocator).sendKeys(Keys.ENTER);
    }
    
    public static void setTextField(WebDriver driver,By fieldLocator, String item) {
    	//Enter "item" into a text field
        
        //Wait for the element to be available
        WebElement element = Wait.clickable(driver,fieldLocator);
    	
        //Click the field to focus the cursor here
        element.click();
        
        //Enter "item"
    	element.sendKeys(item);
        
        //Wait for the value of "item" to be found in the field
    	boolean waitForText = Wait.textPresent(driver,fieldLocator, item);
    }
    
    public static void setInputField(WebDriver driver, By fieldLocator, String item) {
        //Enter "item" into a text field. This is an alternative method to setTextField, as the input field requires a different wait condition
        
        //Wait for the field to be available
        WebElement element = Wait.clickable(driver,fieldLocator);
        
        //Click the field
    	element.click();
        
        //Enter "item" into the field
    	element.sendKeys(item);
        
        //Wait for "item" to be found in the field. This waiting condition uses getAttribute("value") because it is an input field
    	//boolean waitForText = Wait.textPresent(driver,fieldLocator,item);
    }
    
    public static void setInputFieldAlt(WebDriver driver, String id, String item) {
        //Enter "item" into an input field. This is an alternative method to the ones above
        
        //Wait for the field to be available
        WebElement waitForField = Wait.clickable(driver,By.id(id));
        
        //Use Javascript to set the element's text
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('"+id+"').setAttribute('attr', '"+item+"')");
        
        //Wait for the text to be present in the field
        boolean waitForText = Wait.textPresentInput(driver,By.id(id),item);
    }
    
    public static void setCheckBox(WebDriver driver, By fieldLocator) {
        //Check a checkbox, ensuring its value is "checked"
        
        //Only perform the condition if it is not already selected to avoid a timeout exception
        if (!driver.findElement(fieldLocator).isSelected()) {
            //Wait for element to be available
            WebElement element = Wait.clickable(driver,fieldLocator);
            
            //Check box
            element.click();
            
            //Wait for box to be checked
            Boolean waitForChecked = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.boxIsChecked(driver.findElement(fieldLocator)));
        }
        
    }

    public static void unSetCheckBox(WebDriver driver, By fieldLocator) {
        //Check a checkbox, ensuring its value is "unchecked"

        //Only perform the condition if it is not already selected to avoid a timeout exception
        if (driver.findElement(fieldLocator).isSelected()) {
            //Wait for element to be available
            WebElement element = Wait.clickable(driver,fieldLocator);

            //Check box
            element.click();

            //Wait for box to be unchecked
            //Boolean waitForChecked = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.boxIsNotChecked(driver.findElement(fieldLocator)));
        }

    }
    
    public static void uncheckBox(WebDriver driver, By fieldLocator) {
        //Uncheck a box, setting its value fo "unchecked"
        
        //Wait for element to be available
        WebElement element = Wait.clickable(driver,fieldLocator);
        
        //Click box to uncheck it
        element.click();
        
        //Wait for box to be unchecked
        Boolean waitForChecked = new WebDriverWait(driver,DataItems.shortWait).until(CommonTask.boxIsUnchecked(element));
    }
    
    public static void setCheckBoxNoWait(WebDriver driver, By fieldLocator) {
        //Set a checkbox to "checked", but without waiting for the box to be checked
        
        //Wait for element to be available
        WebElement waitForClickable = Wait.clickable(driver,fieldLocator);
        
        //Check box
        driver.findElement(fieldLocator).click();
    }
    
    public static void clickInputCheckBox(WebDriver driver, By fieldLocator) {
        //Click an "input" checkbox, turning its value from checked to unchecked or visa-versa. These checkboxes are different to the ones handled above
        
        //Wait for element to be available
        WebElement btn = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(fieldLocator));
        
        //Click element
        btn.click();
    }
    
    public static void setDateField(WebDriver driver, By fieldLocator) {
        //Set the value of a date field. The algorithm will selet a date 3 days in advance, 
        //unless the date is beyond the 25th, when the 3rd of next month will be selected
        
        //Wait for element to be available
        WebElement element = Wait.clickable(driver,fieldLocator);
        
        //Click element to reveal date picker
        element.click();
        
        //Get the current day of the month
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        
        //New action for clicking
        Actions clickDatePicked = new Actions(driver);

        if (dayOfMonth < 25) {
            //Calculate day of month three days in future
            String date = String.valueOf((int)dayOfMonth+3);

            //Find label with corect date and click it
            By dateLocator = By.xpath("//a[contains(text(),'"+date+"')]");
            clickDatePicked.click(driver.findElement(dateLocator)).build().perform();
        } else {
            //Find label for next month and click
            By nextMonthLocator = By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span");
            clickDatePicked.click(driver.findElement(nextMonthLocator)).build().perform();
            
            //Find label for 3rd of the month and click
            By thirdOfMonthLocator = By.xpath("//a[contains(text(),\"3\")]");
            clickDatePicked.click(driver.findElement(thirdOfMonthLocator)).click().perform();
        }

        //Select Now Time
        By nowButton = By.xpath(".//*[@id='ui-datepicker-div']/div[3]/button[1]");
        clickDatePicked.click(driver.findElement(nowButton)).click().perform();

        new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOf(driver.findElement(nowButton)));

        //Select Now Time
        By doneButton = By.xpath(".//*[@id='ui-datepicker-div']/div[3]/button[2]");
        clickDatePicked.click(driver.findElement(doneButton)).click().perform();


/*
        //Move Minutes Bar
        WebElement From = driver.findElement(By.cssSelector("html body div#ui-datepicker-div.ui-datepicker.ui-widget.ui-widget-content.ui-helper-clearfix.ui-corner-all div#ui-timepicker-div-MarketingRunningtextAvailableFrom.ui-timepicker-div dl dd.ui_tpicker_hour div#ui_tpicker_hour_MarketingRunningtextAvailableFrom.ui-slider.ui-slider-horizontal.ui-widget.ui-widget-content.ui-corner-all a.ui-slider-handle.ui-state-default.ui-corner-all"));

        WebElement To = driver.findElement(By.cssSelector("html body div#ui-datepicker-div.ui-datepicker.ui-widget.ui-widget-content.ui-helper-clearfix.ui-corner-all div#ui-timepicker-div-MarketingRunningtextAvailableFrom.ui-timepicker-div dl dd.ui_tpicker_hour div#ui_tpicker_hour_MarketingRunningtextAvailableFrom.ui-slider.ui-slider-horizontal.ui-widget.ui-widget-content.ui-corner-all a.ui-slider-handle.ui-state-default.ui-corner-all"));

        Actions dragAndDrop = new Actions(driver);

        dragAndDrop.clickAndHold(From)

                .moveToElement(To)

                .release(To)

                .build();

        dragAndDrop.perform();

        //Move Hour Bar
        WebElement From2 = driver.findElement(By.cssSelector("html body div#ui-datepicker-div.ui-datepicker.ui-widget.ui-widget-content.ui-helper-clearfix.ui-corner-all div#ui-timepicker-div-MarketingRunningtextAvailableFrom.ui-timepicker-div dl dd.ui_tpicker_minute div#ui_tpicker_minute_MarketingRunningtextAvailableFrom.ui-slider.ui-slider-horizontal.ui-widget.ui-widget-content.ui-corner-all a.ui-slider-handle.ui-state-default.ui-corner-all"));

        WebElement To2 = driver.findElement(By.cssSelector("html body div#ui-datepicker-div.ui-datepicker.ui-widget.ui-widget-content.ui-helper-clearfix.ui-corner-all div#ui-timepicker-div-MarketingRunningtextAvailableFrom.ui-timepicker-div dl dd.ui_tpicker_minute div#ui_tpicker_minute_MarketingRunningtextAvailableFrom.ui-slider.ui-slider-horizontal.ui-widget.ui-widget-content.ui-corner-all a.ui-slider-handle.ui-state-default.ui-corner-all"));

        Actions dragAndDrop2 = new Actions(driver);

        dragAndDrop.clickAndHold(From2)

                .moveToElement(To2)

                .release(To)

                .build();

        dragAndDrop.perform();
*/
    }


    public static void setDateFieldWithoutHours(WebDriver driver, By fieldLocator) {
        //Set the value of a date field. The algorithm will selet a date 3 days in advance,
        //unless the date is beyond the 25th, when the 3rd of next month will be selected

        //Wait for element to be available
        WebElement element = Wait.clickable(driver,fieldLocator);

        //Click element to reveal date picker
        element.click();

        //Get the current day of the month
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        //New action for clicking
        Actions clickDatePicked = new Actions(driver);

        if (dayOfMonth < 25) {
            //Calculate day of month three days in future
            String date = String.valueOf((int)dayOfMonth+3);

            //Find label with corect date and click it
            By dateLocator = By.xpath("//a[contains(text(),'"+date+"')]");
            clickDatePicked.click(driver.findElement(dateLocator)).build().perform();
        } else {
            //Find label for next month and click
            By nextMonthLocator = By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span");
            clickDatePicked.click(driver.findElement(nextMonthLocator)).build().perform();

            //Find label for 3rd of the month and click
            By thirdOfMonthLocator = By.xpath("//a[contains(text(),\"3\")]");
            clickDatePicked.click(driver.findElement(thirdOfMonthLocator)).click().perform();
        }


    }
    
    public static ExpectedCondition<Boolean> textContains(final String text, final String elementText) {
        //A wait condition which will ensure the elementText variable contains the text variable before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                if (elementText.contains(text)) {
                    return true;
                } else {
                    return false;
                }
            }
        
        };
    }
    
    public static ExpectedCondition<Boolean> boxIsChecked(final WebElement element) {
        //A wait condition which will ensure a check box is checked before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                if (element.getAttribute("checked").equals("true")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }
    
    public static ExpectedCondition<Boolean> boxIsNotChecked(final WebElement element) {
        //A wait condition which will ensure a checkbox is unchecked before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                if (element.getAttribute("checked").equals("false")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }
    
    public static ExpectedCondition<Boolean> boxIsUnchecked(final WebElement element) {
        //A wait condition which will ensure a checkbox is not checked before continuing (alternative method)
        return new ExpectedCondition<Boolean>() {
            public Boolean apply (WebDriver f) {
                if (element.isSelected()) {
                    return false;
                } else {
                    return true;
                }
            }
        };
    }
    
    public static ExpectedCondition<Boolean> textToBePresentInput(final By locator, final String text) {
        //A wait condition which will ensure text is present in the field (for input fields) before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                WebElement inputField = f.findElement(locator);
                if (inputField.getAttribute("value").equals(text)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> textToBePresentInput2(final String element, final String text) {
        //A wait condition which will ensure text is present in the field (for input fields) before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {

                WebElement inputField = f.findElement(By.id("BrandName"));
                if (inputField.getAttribute("value").equals(text)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> textToBePresentInput3(final String element, final String text) {
        //A wait condition which will ensure text is present in the field (for input fields) before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {

                WebElement inputField = f.findElement(By.id("CountryCountryCode"));
                if (inputField.getAttribute("value").equals(text)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }
    
    public static ExpectedCondition<Boolean> selectionToBePresent(final By locator) {
        //A wait condition which will ensure a drop-down menu is not blank before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                Select select = new Select(f.findElement(locator));
                if (select.getFirstSelectedOption().equals(null)) {
                    return false;
                } else {
                    return true;
                }
            }
        };
    }
    
    public static ExpectedCondition<Boolean> selectionToBe(final By locator, final String item) {
        //A wait condition which will ensure the "item" is selected before contiuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                Select select = new Select(f.findElement(locator));
                if (select.getFirstSelectedOption().getText().equals(item)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }
    
    public static ExpectedCondition<Boolean> optionPresent(final String item, final Select select) {
        //A wait condition which will ensure an option is present in the drop-down menu before continuing
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver f) {
                List<WebElement> options = select.getOptions();
                boolean returnMe = false;
                for (WebElement element : options) {
                    if (element.getText().equals(item)) {
                        returnMe = true;
                        break;
                    }
                }
                return returnMe;
            }
        };
    }
    
    public static void waitForPageLoad(WebDriver driver) {
        //Wait for a page to load. This is not particularly reliable for ensuring a page has loaded. Use a last resort. Designed for RCTests
        
        //Wait for the "content" element to be visible
        WebElement wait = Wait.visible(driver,contentLocator,8);
    }
    
    public static void waitForOverlay(WebDriver driver) {
        //Wait for an overlay frame to be available and switch control to it. Generally reliable. Designed for RCTests
        
        //Wait for frame
        WebDriver wait = new WebDriverWait(driver,8).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }
    
    public static void waitForOverlay(WebDriver driver, By overylayContent) {
        //Wait for an overlay frame to be available and switch control to it. Specific content is also specified to make more reliable wait condition. Designed for RCTests
        
        //Wait for frame
        WebDriver wait = new WebDriverWait(driver,8).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        
        //Wait for content
        WebElement waitAgain = new WebDriverWait(driver,8).until(ExpectedConditions.visibilityOfElementLocated(overylayContent));
    }
    
    public static void waitForViewClose(WebDriver driver) {
        //Wait for an overlay to be invisible. Designed for RCTests
        
        //Wait for frame to close
        boolean waitForClose = new WebDriverWait(driver,8).until(ExpectedConditions.invisibilityOfElementLocated(frameLocator));
    }
    
    public static void waitForFieldUpdate(WebDriver driver, By fieldLocator, String item) {
        //Wait for a field to update with a new input by checking its text. Designed for RCTests
        
        //Wait for text to be in field
        boolean wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.textToBePresentInElementLocated(fieldLocator, item));
    }
    
    public static String getErrorDescription(WebDriver driver) {
        //Clicking "Lines with Error" in the eComm Order Confirmation Page will show an overlay. This method returns the first line error description from the overlay
       
        //Locator for top line error cell
        By cellLocator = By.cssSelector("#BulkOrderLineViewUplodErrorListForm > div.grid_12 > div.grid_12 > div.tbl-toggle > div.scrollTableContainer.scroll-pane > table > tbody > tr > td:nth-child(9)");
        
        //Wait for cell to be available
        WebElement cell = new WebDriverWait(driver,DataItems.longWait).until(ExpectedConditions.visibilityOfElementLocated(cellLocator));

        //Return the error
        return cell.getText();
    }
    
    public static void closeView(WebDriver driver) {
        //Close an overlay. Designed for RCTests
        
        //Press ESCAPE to prompt alert
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();
        
        //Wait for and accept alert
        Alert alert = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        
        //Wait for overlay to disappear
        waitForViewClose(driver);
    }
    
    public static String generatePO(String type) throws FileNotFoundException, IOException {
        //Generate a PO number to be used in order creation. A PO number is a value set by the customer, used to identify a specific order
        
        //Initialise a PONumber with value null to begin
        String PONumber = "null";
        try {

            //Access file to read
            FileReader fr = new FileReader(DataItems.idFilepath);
            BufferedReader br = new BufferedReader(fr);
            
            //Get current ID
            String idString = br.readLine();
            int id = Integer.valueOf(idString);
            
            //Increment ID to be written back to file
            id++;
            
            //Safely close file
            br.close();
            fr.close();
            
            //Access file to write
            FileWriter fw = new FileWriter(DataItems.idFilepath);
            BufferedWriter bw = new BufferedWriter(fw);
            
            //Write incremented id to file
            bw.write(String.valueOf(id));
            
            //Safely close file
            bw.close();
            fw.close();
            
            //A prefix value will be added to the number to give more meaning
            String prefix;
            
            //Determine prefix based on type parameter
            switch (type) {
                case "contract": prefix = DataItems.conOrdDetails[4]; break;
                case "buyer": prefix = "BuyerRequest_"; break;
                case "": prefix = DataItems.custDetails[4]; break;
                case "file": prefix = ""; break;
                default: prefix = type;
            }
            
            //Produce PO Number
            PONumber = prefix+idString;       

        } catch (IOException e) {
            System.out.println("IO exception handling the ID file");
        }
 
        //Return generated PO
        return PONumber;
    }
    
    public static boolean checkPagination(WebDriver driver) {
        //Check the pagination (page numbering) on each web page containing a filter. This does not work for all pages and so must be modified for the exceptions
        
        //Locator for field which appears when no records appear
        By noRecords = By.xpath("//*[contains(text(), 'No records found.')]");
        
        boolean records;
        
        try {
            //Wait for the "no records found" field to be displayed. If it is NOT displayed, continue. Else - timeout exception.
            //These waits are a source of slowness in the tests. The full 10 seconds must be waited to ensure the field is not displayed. Better method?
            Boolean wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.invisibilityOfElementLocated(noRecords));
            
            System.out.println("Records found");
            
            //Set records to true to signal records were found
            records = true;
        } catch (TimeoutException t) {
            //Timeout Exception only reached if noRecords field displayed
            System.out.println("No records found.");
            
            //Set records to false to show not records were found
            records = false;
        }
        
        //Only complete if records were found
        if (records) {
            
            //"Showing" refers to field in bottom left of filter which says "Showing 1-x of y"
            boolean showing;
            int recordCount = -1;
            try {
                //Wait for Showing field to be available
                WebElement waitForShowing = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.visibilityOfElementLocated(showingLocator));
                
                //If showing field is displayed, set showing to true
                showing = true;
            } catch (TimeoutException t) {
                //TimeoutException reached if showing field is not dislayed
                showing = false;
            }
            
            //Get number of records displayed
            if (showing) {
                String[] showingText = driver.findElement(showingLocator).getText().split("of ");
                String temp = showingText[1];
                String[] tempArray = temp.split("\\|");
                String numberOfRecords = tempArray[0];
                recordCount = Integer.valueOf(numberOfRecords);
            }

            //PerPage refers to the menu which allows the number of items per page to be selected
            boolean perPage;
            try {
                //Wait for menu to be available
                WebElement waitForMenu = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.visibilityOfElementLocated(perPageMenu));
                
                //If menu is available, set value to true to signal menu is shown
                perPage = true;
            } catch (TimeoutException t) {
                //TimeoutException only reached if perPage is not available. Set to false to show menu is not shown
                perPage = false;
            }
            
            //Options refers to the options located in the per page menu (e.g. 10/25/50)
            boolean options;
            
            //Move mouse over element to display menu
            Actions action = new Actions(driver);
            action.moveToElement(driver.findElement(perPageMenu)).build().perform();
            
            try {
                //Wait for each option to be available
                WebElement waitFor10 = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.presenceOfElementLocated(option10));
                WebElement waitFor25 = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.presenceOfElementLocated(option25));
                WebElement waitFor50 = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.presenceOfElementLocated(option50));
                
                //If all options are available, set options to true to signal all options are shown
                options = true;
            } catch (TimeoutException t) {
                //TimeoutException only reached if one of the options wasnt available
                options = false;
            }
            
            //Assert that all initial parts of the pagination are found as expected by combining boolean values
            AssertJUnit.assertTrue("Pagination Check: One or more elements of 'Showing x-y of z', 'Records Per Page', '10/25/50 options' were not visible",
                    showing&&perPage&&options);

            //Jumps refers to the labels which allow jumps in the page number to be made (e.g. prev, next)
            boolean jumps;
            
            try {
                //Wait for both jump options to appear
                WebElement waitForPrev = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.visibilityOfElementLocated(prevButton));
                WebElement waitForNext = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.visibilityOfElementLocated(nextButton));
                //Set jumps to true to signal both jump options appear
                jumps = true;
            } catch (TimeoutException t) {
                //TimeoutException only reached if jumps are not available
                jumps = false;
            }
            
            //Check if there should be multiple pages by dividing by 10 (multiple of items on page)
            if ((recordCount/10) > 1) {
                
                //Last refers to the label which jumps to the last page
                boolean last;
                
                try {
                    //Find element 
                    WebElement element = driver.findElement(lastButton);
                    WebElement waitForLast = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.visibilityOf(element));
                    //Set last to true to show element was found
                    last = true;
                } catch (TimeoutException t) {
                    //Timeout Exception only reached if last button was not shown
                    last = false;
                }
                
                //Assert both the jumps and last labels appear as expected
                AssertJUnit.assertTrue("Pagination check: Prev/Next/Last button not displayed",jumps&&last);
            }
            
            AssertJUnit.assertTrue("Pagination check: Prev/Next button not displayed",jumps);
            
        }
        
        System.out.println("Pagination as expected");
        
        //Return true to signal pagination was as expected
        return true;
        
    }

    public static boolean checkReportsPagination(WebDriver driver) {
        /**
         * Created by: Stefan
         * Description: This is used to verify the pagination in eComm > Reports (it's similar to checkPagination above, only changed a few locators)
         */
        //Check the pagination (page numbering) on each web page containing a filter. This does not work for all pages and so must be modified for the exceptions

        //Locator for field which appears when no records appear
        By noRecords = By.xpath("//*[contains(text(), 'No records found.')]");

        boolean records;

        try {
            //Wait for the "no records found" field to be displayed. If it is NOT displayed, continue. Else - timeout exception.
            //These waits are a source of slowness in the tests. The full 10 seconds must be waited to ensure the field is not displayed. Better method?
            Boolean wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.invisibilityOfElementLocated(noRecords));

            System.out.println("Records found");

            //Set records to true to signal records were found
            records = true;
        } catch (TimeoutException t) {
            //Timeout Exception only reached if noRecords field displayed
            System.out.println("No records found.");

            //Set records to false to show not records were found
            records = false;
        }

        //Only complete if records were found
        if (records) {

            //"Showing" refers to field in bottom left of filter which says "Showing 1-x of y"
            boolean showing;
            int recordCount = -1;
            try {
                //Wait for Showing field to be available
                WebElement waitForShowing = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.visibilityOfElementLocated(showingLocator));

                //If showing field is displayed, set showing to true
                showing = true;
            } catch (TimeoutException t) {
                //TimeoutException reached if showing field is not dislayed
                showing = false;
            }

            //Get number of records displayed
            if (showing) {
                String[] showingText = driver.findElement(showingLocator).getText().split("of ");
                String temp = showingText[1];
                String[] tempArray = temp.split("\\|");
                String numberOfRecords = tempArray[0];
                recordCount = Integer.valueOf(numberOfRecords);
            }

            //PerPage refers to the menu which allows the number of items per page to be selected
            boolean perPage;
            try {
                //Wait for menu to be available
                WebElement waitForMenu = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.visibilityOfElementLocated(perPageMenu));

                //If menu is available, set value to true to signal menu is shown
                perPage = true;
            } catch (TimeoutException t) {
                //TimeoutException only reached if perPage is not available. Set to false to show menu is not shown
                perPage = false;
            }

            //Options refers to the options located in the per page menu (e.g. 10/25/50)
            boolean options;

            //Move mouse over element to display menu
            Actions action = new Actions(driver);
            action.moveToElement(driver.findElement(perPageMenu)).build().perform();

            try {
                //Wait for each option to be available
                WebElement waitFor10 = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.presenceOfElementLocated(option10));
                WebElement waitFor25 = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.presenceOfElementLocated(option25));
                WebElement waitFor50 = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.presenceOfElementLocated(option50));

                //If all options are available, set options to true to signal all options are shown
                options = true;
            } catch (TimeoutException t) {
                //TimeoutException only reached if one of the options wasnt available
                options = false;
            }

            //Assert that all initial parts of the pagination are found as expected by combining boolean values
            AssertJUnit.assertTrue("Pagination Check: One or more elements of 'Showing x-y of z', 'Records Per Page', '10/25/50 options' were not visible",
                    showing&&perPage&&options);

            //Jumps refers to the labels which allow jumps in the page number to be made (e.g. prev, next)
            boolean jumps;

            try {
                //Wait for both jump options to appear
                WebElement waitForPrev = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.visibilityOfElementLocated(prevButtonReports));
                WebElement waitForNext = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.visibilityOfElementLocated(nextButtonReports));
                //Set jumps to true to signal both jump options appear
                jumps = true;
            } catch (TimeoutException t) {
                //TimeoutException only reached if jumps are not available
                jumps = false;
            }

            //Check if there should be multiple pages by dividing by 10 (multiple of items on page)
            if ((recordCount/10) > 1) {

                //Last refers to the label which jumps to the last page
                boolean last;

                try {
                    //Find element
                    WebElement element = driver.findElement(lastButtonReports);
                    WebElement waitForLast = new WebDriverWait(driver,DataItems.shorterWait).until(ExpectedConditions.visibilityOf(element));
                    //Set last to true to show element was found
                    last = true;
                } catch (TimeoutException t) {
                    //Timeout Exception only reached if last button was not shown
                    last = false;
                }

                //Assert both the jumps and last labels appear as expected
                AssertJUnit.assertTrue("Pagination check: Prev/Next/Last button not displayed",jumps&&last);
            }

            AssertJUnit.assertTrue("Pagination check: Prev/Next button not displayed",jumps);

        }

        System.out.println("Pagination as expected");

        //Return true to signal pagination was as expected
        return true;

    }

    public static void openMasters(WebDriver driver) {
        //Open the masters menu from CCE > Admin > Masters to allow specific master options to be chosen
        
        //Locator for menu
        By menuHead = By.cssSelector("#topnav > li:nth-child(8) > div > div > ul > li:nth-child(3) > div > div:nth-child(1) > ul > li.menuHead");
        
        //Hover over admin header
        WebElement admin = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(adminHeader));
        Actions action = new Actions(driver);
        action.moveToElement(admin).build().perform();
        
        //Hover over masters header
        WebElement masters = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.elementToBeClickable(mastersSubtab));
        action.moveToElement(masters).build().perform();
        
        //Wait for menu to appear
        WebElement wait = new WebDriverWait(driver,DataItems.shortWait).until(ExpectedConditions.visibilityOfElementLocated(menuHead));
    }
    
}
