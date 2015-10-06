
package TestCases;

import AutomationFramework.TestSuite;
import PageObjects.WBA_BasePage;
import PageObjects.CCE_MainPage;
import PageObjects.WBA_ContinuePage;
import PageObjects.Ecomm_MainPage;
import PageObjects.WBA_LoginPage;
import PageObjects.Ecomm_ManualEntryPage;
import PageObjects.WBA_SelectionPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Cce_SOC_Base {
    
    WebDriver driver;
    
    public Cce_SOC_Base(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public CCE_MainPage SUSST_SetUp(String testTitle,String scenarioID) {
        System.out.println("TEST: "+testTitle);
        System.out.println("Scenario ID: "+scenarioID);
    
        //navigate to QA site
        driver.get(TestSuite.targetURL);
    
        //maximise browser window
        driver.manage().window().maximize();
        
        System.out.println("Logging in...");
    
        //new LoginPage
        WBA_LoginPage liPage = new WBA_LoginPage(driver);

        //log in
        WBA_ContinuePage cont = liPage.loginAs(TestSuite.validCoatsUsername,TestSuite.validCoatsPassword);

        System.out.println("Logged in. Continuing to selection page...");

        //press continue, select CCE
        WBA_SelectionPage selectPage = cont.pressContinue();

        System.out.println("Selection page loaded. CCE selected...");

        CCE_MainPage ccePage = selectPage.pressCce();

        System.out.println("CCE page loaded.");
        
        return ccePage;
    }
    
}
