
package TestCases.Generated;

import AutomationFramework.TestSuiteOLD;
import PageObjects.Ecomm_MainPage;
import PageObjects.WBA_ContinuePage;
import PageObjects.WBA_LoginPage;
import PageObjects.WBA_SelectionPage;
import org.openqa.selenium.WebDriver;

public class Ecomm_GeneratedBase {
    
    WebDriver driver;
    
    public Ecomm_GeneratedBase(WebDriver passedDriver) {
        driver = passedDriver;
    }
    
    public Ecomm_MainPage setUp(String testTitle, String scenarioID, String username, String password) {
        System.out.println("TEST: "+testTitle);
        System.out.println("Scenario ID: "+scenarioID);
        
        driver.get(TestSuiteOLD.targetURL);
        
        driver.manage().window().maximize();
        
        System.out.println("Navigating to eComm Page...");
        
        WBA_LoginPage liPage = new WBA_LoginPage(driver);
        
        WBA_ContinuePage contPage = liPage.loginAs(username,password);
        
        WBA_SelectionPage selectPage = contPage.pressContinue();
        
        Ecomm_MainPage eCommPage = selectPage.pressEcomm();
        
        eCommPage.waitForLoad();
        
        System.out.println("eComm Page reached.");
        
        return eCommPage;
        
    }
    
}
