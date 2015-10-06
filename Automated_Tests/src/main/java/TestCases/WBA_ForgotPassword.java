
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.WBA_ForgotPasswordPage;
import PageObjects.WBA_LoginPage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class WBA_ForgotPassword {
    
    @Test //Forgot Password Page :: Reset password
    public void FP1() throws IOException {
        System.out.println("TEST: FORGOT PASSWORD: Coats user forgot password process");
        System.out.println("Scenario ID: G_FP_1");
        
        //New webdriver
        WebDriver driver = new ChromeDriver();
        
        //navigate to QA page
        driver.get(TestSuite.targetURL);
        
        //new login page
        WBA_LoginPage liPage = new WBA_LoginPage(driver);
        
        System.out.println("Click forgot password...");
        
        //Click forgot password
        WBA_ForgotPasswordPage forgotPage = liPage.pressForgotPassword();
        
        //Take a screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,new File(TestSuite.screenshotsFilepath+"\\Login and Forgot Password\\4Forgot Password page.png"));
        
        System.out.println("Reset page reached. Entering e-mail...");
        
        //Provide email and click recover
        forgotPage.setEmail(TestSuite.validCoatsUsername);
        
        System.out.println("E-mail entered. Submitting page...");
        
        WBA_ForgotPasswordPage submittedForgotPage = forgotPage.pressRecover();
        
        //Take a screenshot
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2,new File(TestSuite.screenshotsFilepath+"\\Login and Forgot Password\\5Password reset requested.png"));
        
        System.out.println("Reset requested.");
        
        driver.close();
        
        System.out.println("----------------------------------------------------");
    }
}
