package tests.allure.officer;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Epic("Officer Module")
@Feature("Direct payment Scroll")
public class DirectPaymentTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Step 1: Login before reaching dashboard
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

        WebElement directpayBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/div/div/div[2]/button[3]")
        ));
        directpayBtn.click();

        wait.until(ExpectedConditions.urlContains("/responsibleOfficer/directpayments"));
    }

    @Test(description = "Scroll through the officer dashboard and verify element")
    @Severity(SeverityLevel.NORMAL)
    @Story("Dashboard content visibility on scroll")
    @Description("Scrolls down the dashboard and checks that bottom content is visible")
    public void testDirectPaymentScroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll down by 1000 pixels
        js.executeScript("window.scrollBy(0, 1000)");

        // Optional: Wait for content to appear after scroll
        WebElement targetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Direct Customer Payment')]")));

        Assert.assertTrue(targetElement.isDisplayed(), "Expected dashboard element not visible after scroll.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}