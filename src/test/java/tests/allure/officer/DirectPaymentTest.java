package tests.allure.officer;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Epic("Officer Module")
@Feature("Direct Payment")
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
                By.xpath("//*[@id=\"root\"]/div/div/div/div/div[2]/button[3]")));
        directpayBtn.click();

        wait.until(ExpectedConditions.urlContains("/responsibleOfficer/directpayments"));
    }

    @Test(description = "Fill Direct Customer Payment form and Confirm Payment")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Direct Customer Payment")
    @Description("Fills in the payment form with valid data and confirms the payment")
    public void testDirectPaymentForm() throws InterruptedException {

        // Step 2: Fill in the form details

        // Select the Zone
        WebElement zoneSelect = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div[2]/div[2]/div[2]/div[1]/div[1]/select"));
        Select zone = new Select(zoneSelect);
        zone.selectByVisibleText("Zone A1");

        // Enter the Bill ID
        WebElement billId = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div[2]/div[2]/div[2]/div[1]/div[3]/input"));
        billId.sendKeys("BILL123");

        // Enter the Register No and check auto-fill functionality for Company and Customer Name
        WebElement registerNo = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div[2]/div[2]/div[2]/div[1]/div[2]/input"));
        registerNo.sendKeys("REG001");

        // Validate that the Company Name and Customer Name are auto-filled after Register No is entered
        WebElement companyName = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div[2]/div[2]/div[2]/div[1]/div[5]/input"));
        WebElement customerName = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div[2]/div[2]/div[2]/div[1]/div[4]/input"));

        Assert.assertFalse(companyName.getAttribute("value").isEmpty(), "Company Name was not auto-filled.");
        Assert.assertFalse(customerName.getAttribute("value").isEmpty(), "Customer Name was not auto-filled.");

        // Enter Amount Paid and Receipt Number
        WebElement amountPaid = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div[2]/div[2]/div[2]/div[1]/div[6]/input"));
        amountPaid.sendKeys("1000");

        WebElement receiptNumber = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div[2]/div[2]/div[2]/div[1]/div[7]/input"));
        receiptNumber.sendKeys("REC001");

        // Step 3: Click on 'Confirm Payment' button
        WebElement confirmBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div[2]/div[2]/div[2]/div[2]/button"));
        confirmBtn.click();


    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
