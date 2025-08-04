package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

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

    @Test
    public void testClickCreateAccount() throws InterruptedException {
        WebElement createAcc = driver.findElement(By.xpath("//a[contains(text(),'Create An Account')]"));
        createAcc.click();


        String expectedUrl = "http://localhost:5173/login";
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedUrl, "Navigation to Register page failed!");

        Thread.sleep (3000);
    }



    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
