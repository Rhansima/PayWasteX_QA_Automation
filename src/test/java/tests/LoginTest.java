package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:5173/login");
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() {
        WebElement email = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/form/label[1]/input")); // or use ID if available
        WebElement password = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/form/label[2]/input"));
        WebElement loginBtn = driver.findElement(By.xpath("//button[text()='LOGIN']"));

        email.sendKeys("rhansima");
        password.sendKeys("1234");
        loginBtn.click();

        // Add validation after login (e.g., check redirected page or element)
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
