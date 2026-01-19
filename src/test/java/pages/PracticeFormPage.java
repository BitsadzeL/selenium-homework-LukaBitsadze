package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PracticeFormPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /* =======================
       Page Actions
       ======================= */

    @Step("Open Practice Form page")
    public void open() {
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @Step("Fill personal information")
    public void fillPersonalInfo(String firstName,
                                 String lastName,
                                 String email,
                                 String phone) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")))
                .sendKeys(firstName);

        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(email);

        driver.findElement(By.xpath("//label[text()='Male']")).click();
        driver.findElement(By.id("userNumber")).sendKeys(phone);
    }

    @Step("Select date of birth")
    public void selectDateOfBirth(String month, String year, String day) {

        driver.findElement(By.id("dateOfBirthInput")).click();

        new Select(wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("react-datepicker__month-select")
                )
        )).selectByVisibleText(month);

        new Select(driver.findElement(
                By.className("react-datepicker__year-select")
        )).selectByVisibleText(year);

        driver.findElement(
                By.xpath("//div[contains(@class,'react-datepicker__day--0" + day + "')]")
        ).click();
    }

    @Step("Select subject: {subject}")
    public void selectSubject(String subject) {
        WebElement subjectInput =
                wait.until(ExpectedConditions.elementToBeClickable(By.id("subjectsInput")));
        subjectInput.sendKeys(subject);
        subjectInput.sendKeys(Keys.ENTER);
    }

    @Step("Select hobby: {hobby}")
    public void selectHobby(String hobby) {
        driver.findElement(By.xpath("//label[text()='" + hobby + "']")).click();
    }

    @Step("Enter address")
    public void enterAddress(String address) {
        driver.findElement(By.id("currentAddress")).sendKeys(address);
    }

    @Step("Select state and city")
    public void selectStateAndCity(String state, String city) {

        WebElement stateInput =
                wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-3-input")));
        stateInput.sendKeys(state);
        stateInput.sendKeys(Keys.ENTER);

        WebElement cityInput =
                wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-4-input")));
        cityInput.sendKeys(city);
        cityInput.sendKeys(Keys.ENTER);
    }

    @Step("Submit the form")
    public void submit() {

        WebElement submitButton = driver.findElement(By.id("submit"));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", submitButton
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", submitButton
        );
    }

    @Step("Verify confirmation popup is visible")
    public boolean isPopupVisible() {
        WebElement popup = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("modal-content")
                )
        );
        return popup.isDisplayed();
    }

    @Step("Get confirmation popup text")
    public String getPopupText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("modal-content")
                )
        ).getText();
    }

    @Step("Close confirmation popup")
    public void closePopup() {
        WebElement closeButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("closeLargeModal"))
        );
        closeButton.click();
    }
}
