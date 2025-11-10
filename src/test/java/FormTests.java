import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class FormTests {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void fillFormAndVerifyPopup() {
        driver.get("https://demoqa.com/automation-practice-form");


        driver.findElement(By.id("firstName")).sendKeys("Luka");
        driver.findElement(By.id("lastName")).sendKeys("Bitsadze");
        driver.findElement(By.id("userEmail")).sendKeys("luka@example.com");

        driver.findElement(By.xpath("//label[text()='Male']")).click();
        driver.findElement(By.id("userNumber")).sendKeys("555123456");


        driver.findElement(By.id("dateOfBirthInput")).click();
        new Select(driver.findElement(By.className("react-datepicker__month-select"))).selectByVisibleText("May");
        new Select(driver.findElement(By.className("react-datepicker__year-select"))).selectByVisibleText("2000");
        driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day--010')]")).click();


        WebElement subject = driver.findElement(By.id("subjectsInput"));
        subject.sendKeys("Maths");
        subject.sendKeys(Keys.ENTER);


        driver.findElement(By.xpath("//label[text()='Sports']")).click();


        driver.findElement(By.id("currentAddress")).sendKeys("Tbilisi, Georgia");


        WebElement state = driver.findElement(By.id("react-select-3-input"));
        state.sendKeys("NCR");
        state.sendKeys(Keys.ENTER);

        WebElement city = driver.findElement(By.id("react-select-4-input"));
        city.sendKeys("Delhi");
        city.sendKeys(Keys.ENTER);


        driver.findElement(By.id("submit")).click();


        WebElement popup = driver.findElement(By.className("modal-content"));
        Assert.assertTrue(popup.isDisplayed(), "Popup should be visible after submit");

        String popupText = popup.getText();
        Assert.assertTrue(popupText.contains("Luka Bitsadze"), "Name should appear in popup");
        Assert.assertTrue(popupText.contains("luka@example.com"), "Email should appear in popup");
        Assert.assertTrue(popupText.contains("555123456"), "Phone should appear in popup");
        Assert.assertTrue(popupText.contains("Male"), "Gender should appear in popup");
        Assert.assertTrue(popupText.contains("Maths"), "Subject should appear in popup");
        Assert.assertTrue(popupText.contains("Sports"), "Hobby should appear in popup");

        driver.findElement(By.id("closeLargeModal")).click();
    }

    @AfterClass
    public void teardown() {
        if (driver != null)
            driver.quit();
    }
}
