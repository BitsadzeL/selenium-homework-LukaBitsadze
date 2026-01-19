package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertsPage;

@Epic("UI Automation")
@Feature("Alerts")
public class AlertTests extends BaseTest {

    @Test(description = "Handle alert with textbox")
    @Story("Prompt Alert")
    @Severity(SeverityLevel.CRITICAL)
    public void handleAlertWithTextbox() {
        AlertsPage alertsPage = new AlertsPage(driver);

        alertsPage.open();
        alertsPage.openTextboxAlert();

        String name = "Luka Bitsadze";
        alertsPage.handlePrompt(name);

        Assert.assertTrue(alertsPage.getResult().contains(name));
    }
}
