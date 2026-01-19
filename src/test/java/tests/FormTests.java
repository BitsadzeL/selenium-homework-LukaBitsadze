package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PracticeFormPage;

@Epic("UI Automation")
@Feature("Forms")
public class FormTests extends BaseTest {

    @Test(description = "Fill form and verify popup")
    @Story("Practice Form")
    @Severity(SeverityLevel.NORMAL)
    public void fillFormAndVerifyPopup() {

        PracticeFormPage form = new PracticeFormPage(driver);
        form.open();

        form.fillPersonalInfo(
                "Luka",
                "Bitsadze",
                "luka@example.com",
                "5551234567"
        );

        form.submit();

        Assert.assertTrue(form.isPopupVisible());
        Assert.assertTrue(form.getPopupText().contains("Luka Bitsadze"));
    }
}
