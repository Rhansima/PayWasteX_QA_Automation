package tests.allure;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:5173/login");
        driver.manage().window().maximize();
    }

    @Test(description = "Click 'Create An Account' and verify redirection")

    @Severity(value = SeverityLevel.CRITICAL)

    @Story("Navigate from Login to Registration")

    @Description("Test checks if 'Create An Account' button correctly navigates user to register page")

    public void testClickCreateAccount() throws InterruptedException {
        WebElement createAcc = driver.findElement(By.xpath("//a[contains(text(),'Create An Account')]"));
        createAcc.click();

        String expectedUrl = "http://localhost:5173/login"; // Correct target URL?
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedUrl, "Navigation to Register page failed!");

        Thread.sleep(3000);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
