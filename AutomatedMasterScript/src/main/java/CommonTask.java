
import java.io.File;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CommonTask {
    
    public static void checkImageLoaded(String pageName, String xpath, WebDriver driver) {
        //find element by xpath given and check if loaded
        WebElement image = driver.findElement(By.xpath(xpath));
        Boolean isLoaded = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", image);
        //output loaded status 
        System.out.println("Image on " + pageName + " loaded: " + isLoaded);
        
    }
    
    public static void checkNavbarLoaded(WebDriver driver,String navbarXpath,String[] dropdownXpath,String[][] xpctdSubSections) {
        List<WebElement> navbarLinks = driver.findElements(By.xpath(navbarXpath));
        
        int i = 0;
        
        for (WebElement we : navbarLinks) {
            
                Actions action = new Actions(driver);
                int arrayLength = xpctdSubSections[i].length;
                String[] expectedHeadings = new String[arrayLength];
                for (int j = 0; j < arrayLength;j++) {
                    expectedHeadings[j] = xpctdSubSections[i][j];
                }
                
                
                //if the xpath has been given, check for dropdown display
                if (!dropdownXpath[i].isEmpty()) {
                    //click and hold over navbar section
                    action.clickAndHold(we).perform();
                    //check if dropdown present
                    if (driver.findElement(By.xpath(dropdownXpath[i])).isDisplayed()) {
                        System.out.println("Navbar header working, drop-down displayed");
                    } else {
                        System.out.println("Navbar header not working, drop-down not displayed");
                    }
                    //check that all sub sections are found
                    for(String text : expectedHeadings) {
                        try {
                        WebElement heading = driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
                        System.out.println("Found: "+heading.toString());
                        } catch (Exception e) {
                            System.out.println("Error finding headings. Ensure the expected headers in the arrays are correct. Read console for more information.");
                            driver.quit();
                        }
                    }
                    //release click
                    action.release().perform();
                }
                
                i++;          
        }
    }
    
    public static void takeScreenshot(String folderImageName, String imageDescription, WebDriver driver) {
        //take screenshot and place in folderImageName. This location must be of format "subfolder\\imagename"
        try {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile,new File(TestSuite.screenshotFolder + folderImageName +".png"));
        } catch (Exception e) {
            System.out.println("Failure taking screenshot: " + imageDescription);
        }     
    }
    
    public static void login(String username, String password, WebDriver driver, boolean pressContinue) {
        //login
        WebElement usernameField = driver.findElement(By.xpath("//*[@id=\"UserUsername\"]"));
        usernameField.click();
        usernameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"UserPassword\"]"));
        passwordField.click();
        passwordField.sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"login-bottom\"]/div[3]/input")).click();
        
        //check if pressContinue should be done
        if (pressContinue) {
            //click continue
            driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[4]/a[2]/img")).click();
        } 
    }
    
}
