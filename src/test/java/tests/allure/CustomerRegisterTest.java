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
        driver.get("http://localhost:5173/login"); // ✅ Start from login page
    }

    @Test(description = "Full customer registration redirection flow")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Navigate and select customer registration")
    @Description("Clicks on 'Create an Account' then 'Customer' and verifies the registration topic")
    public void testCustomerRegistration() {
        // Step 1: Click "Create An Account"
        WebElement createAccLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/form/div[2]/span/a")));
        createAccLink.click();

        // Step 2: Click "Customer" button
        WebElement customerBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/div[4]/div/div/button[1]")));
        customerBtn.click();

        // Step 3: Verify heading is displayed
        WebElement topicElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div[1]/h2")));

        String actualTopic = topicElement.getText();
        System.out.println("Page topic: " + actualTopic);

        // ✅ Corrected expected topic
        String expectedTopic = "Register As A Customer";
        Assert.assertEquals(actualTopic, expectedTopic, "Unexpected topic after selecting customer register");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
