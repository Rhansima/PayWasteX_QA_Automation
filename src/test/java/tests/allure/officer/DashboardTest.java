package tests.allure.officer;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Epic("Officer Module")
@Feature("Dashboard Scroll")
public class DashboardTest {

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
    }

    @Test(description = "Scroll through the officer dashboard and verify element")
    @Severity(SeverityLevel.NORMAL)
    @Story("Dashboard content visibility on scroll")
    @Description("Scrolls down the dashboard and checks that bottom content is visible")

    public void testDashboardScroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll down by 1000 pixels
        js.executeScript("window.scrollBy(0, 1000)");

        // Optional: Wait for content to appear after scroll
        WebElement targetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Quick Actions')]")));

        Assert.assertTrue(targetElement.isDisplayed(), "Expected dashboard element not visible after scroll.");
    }

    @Test(description = "Quick actions button1 navigates and scrolls")
    @Severity(SeverityLevel.NORMAL)
    @Story("Quick actions button click and page scroll verification")
    @Description("Click Quick Action button, navigate to direct payments, and scroll to section heading")
    public void testQuickActionButton1() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Click the Quick Action button
        WebElement quickActionBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div[2]/div[2]/div[2]/div[2]/div[2]/button[1]")));
        quickActionBtn.click();

        // Step 2: Wait for navigation to Direct Payments page
        wait.until(ExpectedConditions.urlContains("/responsibleOfficer/directpayments"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/responsibleOfficer/directpayments"),
                "Failed to navigate to Direct Payments page");

        // Step 3: Scroll to the heading element
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div[2]/div[2]/div[1]/h2")));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", heading);

        // Step 4: Navigate back to the dashboard
        driver.navigate().back();  // This returns you back to the previous page, which should be the dashboard

        // Optional: Verify that you're on the dashboard page
        wait.until(ExpectedConditions.urlContains("/responsibleOfficer/dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/responsibleOfficer/dashboard"),
                "Failed to navigate back to Dashboard page");


    }

    @Test(description = "Quick actions button2 navigates and scrolls")
    @Severity(SeverityLevel.NORMAL)
    @Story("Quick actions button click and page scroll verification")
    @Description("Click Quick Action button, navigate to Bill management, and scroll to section heading")
    public void testQuickActionButton2() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // Increased wait time

        // Step 1: Click the Quick Action button
        WebElement quickActionBtn2 = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div[2]/div[2]/div[2]/div[2]/div[2]/button[2]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", quickActionBtn2);  // Scroll into view
        quickActionBtn2.click();

        // Step 2: Wait for the page to load and verify the URL
        wait.until(ExpectedConditions.urlContains("/responsibleOfficer/billmanagement"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/responsibleOfficer/billmanagement"),
                "Failed to navigate to Bill Management page");

        // Step 3: Wait for the heading to appear
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div[2]/div[2]/div[1]/h2")));

        // Step 4: Scroll to the heading element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", heading);

        // Step 5: Navigate back to the dashboard
        driver.navigate().back();  // This returns you back to the previous page, which should be the dashboard

        // Optional: Verify that you're on the dashboard page
        wait.until(ExpectedConditions.urlContains("/responsibleOfficer/dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/responsibleOfficer/dashboard"),
                "Failed to navigate back to Dashboard page");

    }
    @Test(description = "Quick actions button2 navigates and scrolls")
    @Severity(SeverityLevel.NORMAL)
    @Story("Quick actions button click and page scroll verification")
    @Description("Click Quick Action button, navigate to Bill management, and scroll to section heading")
    public void testQuickActionButton3() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // Increased wait time

        // Step 1: Click the Quick Action button
        WebElement quickActionBtn3 = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/main/div/div[2]/div[2]/div[2]/div[2]/div[2]/button[3]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", quickActionBtn3);  // Scroll into view
        quickActionBtn3.click();

        // Step 2: Wait for the page to load and verify the URL
        wait.until(ExpectedConditions.urlContains("/responsibleOfficer/reports"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/responsibleOfficer/reports"),
                "Failed to navigate to Reports page");

        // Step 3: Wait for the heading to appear
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Available Reports')]")));

        // Step 4: Scroll to the heading element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", heading);


    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
