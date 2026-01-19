package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertsPage {

    private WebDriver driver;

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By alertWithTextboxTab =
            By.xpath("//a[text()='Alert with Textbox ']");

    private By alertButton =
            By.cssSelector("button[onclick='promptbox()']");

    @Step("Open Alerts page")
    public void open() {
        driver.get("https://demo.automationtesting.in/Alerts.html");
    }

    @Step("Open Alert with Textbox")
    public void openTextboxAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Open tab
        WebElement tab = wait.until(
                ExpectedConditions.elementToBeClickable(alertWithTextboxTab)
        );
        tab.click();

        // Wait until JS finishes rendering button
        WebElement button = wait.until(
                ExpectedConditions.presenceOfElementLocated(alertButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", button);
    }



    @Step("Enter name into alert: {name}")
    public void handlePrompt(String name) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(name);
        alert.accept();
    }

    @Step("Get alert result text")
    public String getResult() {
        return driver.findElement(By.id("demo1")).getText();
    }
}
