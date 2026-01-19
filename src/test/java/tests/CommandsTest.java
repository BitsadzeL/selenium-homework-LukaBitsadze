package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DynamicControlsPage;

@Epic("UI Automation")
@Feature("Dynamic Controls")
public class CommandsTest extends BaseTest {

    @Test(description = "Enable input field and verify behavior")
    @Story("Enable / Disable Input")
    @Severity(SeverityLevel.NORMAL)
    public void enableInputFieldTest() {

        DynamicControlsPage page = new DynamicControlsPage(driver);

        page.open();
        page.clickEnable();
        page.waitForInputToBeEnabled();

        Assert.assertTrue(page.isInputEnabled(),
                "Input field should be enabled");

        Assert.assertTrue(page.getMessageText().contains("enabled"),
                "Message should confirm enabling");

        page.typeText("Bootcamp");
    }
}
