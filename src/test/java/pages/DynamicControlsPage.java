package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class DynamicControlsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public DynamicControlsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By enableButton = By.xpath("//form[@id='input-example']/button");
    private By inputField = By.xpath("//form[@id='input-example']/input");
    private By message = By.id("message");

    @Step("Open Dynamic Controls page")
    public void open() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
    }

    @Step("Click Enable button")
    public void clickEnable() {
        driver.findElement(enableButton).click();
    }

    @Step("Wait until input field is enabled")
    public void waitForInputToBeEnabled() {
        wait.until(ExpectedConditions.elementToBeClickable(inputField));
    }

    @Step("Get status message")
    public String getMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(message))
                .getText();
    }

    @Step("Check if input field is enabled")
    public boolean isInputEnabled() {
        return driver.findElement(inputField).isEnabled();
    }

    @Step("Type text: {text}")
    public void typeText(String text) {
        WebElement input = driver.findElement(inputField);
        input.sendKeys(text);
        input.clear();
    }
}
