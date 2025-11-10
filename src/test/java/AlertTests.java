import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class AlertTests {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void handleAlertWithTextbox() {
        driver.get("https://demo.automationtesting.in/Alerts.html");


        driver.findElement(By.xpath("//a[contains(text(),'Alert with Textbox')]")).click();


        driver.findElement(By.xpath("//button[contains(text(),'click the button to demonstrate the prompt box')]")).click();


        Alert alert = driver.switchTo().alert();
        String name = "Luka Bitsadze";
        alert.sendKeys(name);
        alert.accept();


        WebElement result = driver.findElement(By.id("demo1"));
        String resultText = result.getText();

        Assert.assertTrue(resultText.contains(name), "Alert result should contain entered name");
    }

    @AfterClass
    public void teardown() {
        if (driver != null)
            driver.quit();
    }
}
