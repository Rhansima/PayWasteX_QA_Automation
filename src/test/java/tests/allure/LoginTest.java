package tests.allure;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Epic("User Management")
@Feature("Login")
public class LoginTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://localhost:5173/login");
    }

    @Test(description = "Verify login functionality with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Successful login")
    @Description("This test enters correct credentials and clicks the login button to verify user can login")
    public void testLogin() {
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/form/label[1]/input")));
        WebElement password = driver.findElement(
                By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/form/label[2]/input"));
        WebElement loginBtn = driver.findElement(By.xpath("//button[text()='LOGIN']"));

        email.sendKeys("rhansima");
        password.sendKeys("1234");

        // Optional: wait for overlays to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.fixed.inset-0.z-50")));

        loginBtn.click();

        // Wait for dashboard or successful indicator
        wait.until(ExpectedConditions.urlContains("/dashboard")); // or change this based on your app

        // Assert success by checking URL or user element
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/dashboard"), "Login failed or unexpected redirection.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
