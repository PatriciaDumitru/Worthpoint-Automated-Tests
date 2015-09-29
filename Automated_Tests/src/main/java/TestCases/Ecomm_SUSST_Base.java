
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.ContinuePage;
import PageObjects.EcommPage;
import PageObjects.LoginPage;
import PageObjects.ManualEntryPage;
import PageObjects.WbaSelectionPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ecomm_SUSST_Base {
    
    WebDriver driver;
    
    public Ecomm_SUSST_Base(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public EcommPage SUSST_SetUp(String testTitle,String scenarioID) {
        System.out.println("TEST: "+testTitle);
        System.out.println("Scenario ID: "+scenarioID);
    
        //navigate to QA site
        driver.get(TestSuite.targetURL);
    
        //maximise browser window
        driver.manage().window().maximize();
        
        System.out.println("Logging in...");
    
        //new LoginPage
        LoginPage liPage = new LoginPage(driver);

        //log in
        ContinuePage cont = liPage.loginAs(TestSuite.validCoatsUsername,TestSuite.validCoatsPassword);

        System.out.println("Logged in. Continuing to selection page...");

        //press continue, select eComm
        WbaSelectionPage selectPage = cont.pressContinue();

        System.out.println("Selection page loaded. eComm selected...");

        EcommPage eCommPage = selectPage.pressEcomm();

        System.out.println("eComm page loaded.");
        
        return eCommPage;
    }
    
}
