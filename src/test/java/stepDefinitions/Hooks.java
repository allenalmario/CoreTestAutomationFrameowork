package stepDefinitions;

import base.BasePage;
import com.microsoft.playwright.Page;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.PlaywrightDriver;

public class Hooks {

    Page page;

    @Before
    public void setUp() {
        PlaywrightDriver.setupDriver();

        BasePage basePage = new BasePage();
    }

    @After
    public void tearDown() {
        PlaywrightDriver.closeBrowser();
        PlaywrightDriver.quitPlaywright();
    }
}
