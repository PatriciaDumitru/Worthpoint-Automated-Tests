package TestCases;

import AutomationFramework.CommonTask;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContractOrderRCTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  static By requesterField = By.cssSelector("#s2id_BuyerId > a");
  static By finishField = By.id("token_finish_id_0");
  static By articleField = By.id("s2id_BulkOrderLine0ArticleId");
  static By shadeCodeField = By.id("s2id_BulkOrderLine0ShadeId");
  static By dateField = By.id("required_date_0");
  
  
  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    baseUrl = "https://qawcs.coatscolourexpress.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testContractOrder1() throws Exception {
    driver.get(baseUrl + "/");
    driver.manage().window().maximize();
    driver.findElement(By.id("UserUsername")).clear();
    driver.findElement(By.id("UserUsername")).sendKeys("joecontract@coats.com");
    driver.findElement(By.id("UserPassword")).clear();
    driver.findElement(By.id("UserPassword")).sendKeys("password");
    driver.findElement(By.cssSelector("input.loginbutton")).click();
    driver.findElement(By.xpath("//div[@id='wrapper']/div[4]/a[2]/img")).click();
    driver.findElement(By.xpath("//area[4]")).click();
    driver.findElement(By.linkText("Orders")).click();
    driver.findElement(By.linkText("Manual Entry")).click();
    CommonTask.setSearchField(driver, requesterField, "*OTHERS*");
    driver.findElement(By.id("BulkOrderPoNumber")).clear();
    driver.findElement(By.id("BulkOrderPoNumber")).sendKeys("TEST ZCQ ARUN 01"); 
    CommonTask.setSearchField(driver,articleField,"8754120");
    CommonTask.setSearchField(driver, shadeCodeField, "WHITE");
    driver.findElement(By.id("quantity0")).clear();
    driver.findElement(By.id("quantity0")).sendKeys("1000");
    driver.findElement(By.id("txtContract0")).clear();
    driver.findElement(By.id("txtContract0")).sendKeys("40000991");
    CommonTask.setDateField(driver, dateField);
    driver.findElement(By.id("txtContractLine0")).clear();
    driver.findElement(By.id("txtContractLine0")).sendKeys("10");
    acceptNextAlert = false;
    driver.findElement(By.id("next")).click();
    Alert alert = new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent());
    assertTrue(closeAlertAndGetItsText().matches("^Do you want to SUBMIT the order[\\s\\S]$"));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

