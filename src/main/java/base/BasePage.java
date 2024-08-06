package base;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import org.testng.Assert;
import utils.PlaywrightDriver;

public class BasePage {

    public static Page page;

    public BasePage() {
        page = PlaywrightDriver.getPage();
    }

    /**
     * click
     * @param locatorKey - the locator name of the element from objectRepository.properties to be clicked
     * @return n/a
     */
    public void click (String locatorKey) {
        try {
            page.locator(PlaywrightDriver.objectRepository.getProperty(locatorKey)).click();
        } catch (Throwable t) {
            Assert.fail(t.getMessage());
        }
    }

    /**
     * mouseOver
     * @param locatorKey - the locator name of the element from objectRepository.properties to be hovered over
     * @return n/a
     */
    public void mouseOver(String locatorKey) {
        try {
            page.hover(PlaywrightDriver.objectRepository.getProperty(locatorKey));
        } catch (Throwable t) {
            Assert.fail(t.getMessage());
        }
    }

    /**
     * isElementPresent
     * @param locatorKey - the locator name of the element from objectRepository.properties to be checked if present on screen
     * @return boolean value of whether element is present or not
     */
    public boolean isElementPresent(String locatorKey) {
        try {
            page.waitForSelector(PlaywrightDriver.objectRepository.getProperty(locatorKey), new Page.WaitForSelectorOptions().setTimeout(2000));
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    /**
     * type
     * @param locatorKey - the locator name of the element from objectRepository.properties to be typed in
     * @param value - content to be typed
     * @return n/a
     */
    public void type(String locatorKey, String value) {
        try {
            page.locator(PlaywrightDriver.objectRepository.getProperty(locatorKey)).fill(value);
        } catch (Throwable t) {
            Assert.fail(t.getMessage());
        }
    }

    /**
     * select
     * @param locatorKey - the locator name of the element from objectRepository.properties to be selected from
     * @param value - value to be selected from selector element
     * @return n/a
     */
    public void select(String locatorKey, String value) {
        try {
            page.selectOption(PlaywrightDriver.objectRepository.getProperty(locatorKey), new SelectOption().setLabel(value));
        } catch (Throwable t) {
            Assert.fail(t.getMessage());
        }
    }

    /**
     * returnPageUrl
     * @param n/a
     * @return url of current page in String format
     */
    public String returnPageUrl() {
        return page.url();
    }

    /**
     * getAttributeValue
     * @param locatorKey - the locator name of the element from objectRepository.properties
     * @param attributeName - element's attribute name to be extracted from
     * @return value of attribute in element in string format
     */
    public String getAttributeValue(String locatorKey, String attributeName) {
        return page.locator(PlaywrightDriver.objectRepository.getProperty(locatorKey)).getAttribute(attributeName);
    }
}
