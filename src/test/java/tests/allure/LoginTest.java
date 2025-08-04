
package tests.allure;
import io.qameta.allure.*;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Epic("User Management")
@Feature("Login")
public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:5173/login");
        driver.manage().window().maximize();
    }

    @Test(description = "Verify login functionality with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Successful login")
    @Description("This test enters correct credentials and clicks the login button to verify user can login")
    public void testLogin() {
        WebElement email = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/form/label[1]/input"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/form/label[2]/input"));
        WebElement loginBtn = driver.findElement(By.xpath("//button[text()='LOGIN']"));

        email.sendKeys("rhansima");
        password.sendKeys("1234");
        loginBtn.click();


    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
