package tests.allure;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Epic("User Management")
@Feature("Customer Registration")
public class CustomerRegisterTest {

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

    @Test(description = "Complete customer registration with form fill")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Customer submits registration form")
    @Description("Clicks on 'Create an Account' ➜ 'Customer' ➜ fills form ➜ clicks Register")
    public void testCustomerRegistrationFormFill() {
        // Step 1: Navigate to Register Page
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/form/div[2]/span/a"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[4]/div/div/button[1]"))).click();

        // Step 2: Fill form fields
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='fullName']"))).sendKeys("Test User");
        driver.findElement(By.xpath("//*[@name='fullName']")).sendKeys("Hansima");
        driver.findElement(By.xpath("//*[@name='nicOrRegNo']")).sendKeys("2000897689076");
        driver.findElement(By.xpath("//*[@name='email']")).sendKeys("tharushi@gmail.com");
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("1234");
        driver.findElement(By.xpath("//*[@name='confirmPassword']")).sendKeys("1234");

        driver.findElement(By.xpath("//*[@name='businessName']")).sendKeys("hansi");
        driver.findElement(By.xpath("//*[@name='businessType']")).sendKeys("hotel");
        driver.findElement(By.xpath("//*[@name='address']")).sendKeys("akuressa");
        driver.findElement(By.xpath("//*[@name='city']")).sendKeys("matara");
        driver.findElement(By.xpath("//*[@name='mobile']")).sendKeys("0777654767");


        // Step 4: Click Register
        WebElement registerBtn = driver.findElement(By.xpath("//button[text()='Register']"));
        registerBtn.click();

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
