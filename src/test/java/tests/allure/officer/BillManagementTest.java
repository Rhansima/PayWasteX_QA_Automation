package tests.allure.officer;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Epic("Officer Module")
@Feature("Bill Management Navigation")
public class BillManagementTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Step 1: Login before reaching the required page
        driver.get("http://localhost:5173/login");

        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/form/label[1]/input")));
        WebElement password = driver.findElement(
                By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/form/label[2]/input"));
        WebElement loginBtn = driver.findElement(By.xpath("//button[text()='LOGIN']"));

        email.sendKeys("rhansima");
        password.sendKeys("1234");
        loginBtn.click();

        wait.until(ExpectedConditions.urlContains("/responsibleOfficer/dashboard"));
    }

    @Test(description = "Click the Bill Management button and navigate to Bill Management page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Bill Management Button click and navigation verification")
    @Description("Click the Bill Management button to navigate to Bill Management page and verify the page")
    public void testBillManagementNavigation() {
        // Step 1: Wait for the Bill Management button to be clickable and click it
        WebElement billManagementButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='root']/div/div/div/div/div[2]/button[2]")));
        billManagementButton.click();

        // Step 2: Verify that the page navigates to the Bill Management page
        wait.until(ExpectedConditions.urlContains("/responsibleOfficer/billmanagement"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/responsibleOfficer/billmanagement"),
                "Failed to navigate to Bill Management page");

        // Optional Step: Verify that the expected section heading on Bill Management page is visible
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='root']/div/div/main/div/div[1]/div/div/div/div[2]/h1")));
        Assert.assertTrue(heading.isDisplayed(), "Heading not visible on Bill Management page.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
