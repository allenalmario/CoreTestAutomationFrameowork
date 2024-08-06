package utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class PlaywrightDriver {

    public static PlaywrightDriver playwrightDriver;
    private static Playwright playwright;
    public static Browser browser;
    public static Page page;
    public static Properties objectRepository = new Properties();
    public static Properties config = new Properties();
    private static FileInputStream inputStream;

    /**
     * PlaywrightDriver
     * @param n/a
     * @return n/a
     * When method is called, it loads the property files and creates the Playwright Page object
     */
    private PlaywrightDriver() {

        try {
            inputStream = new FileInputStream("./src/main/resources/properties/objectRepository.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            objectRepository.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            inputStream = new FileInputStream("./src/main/resources/properties/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            config.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        playwright = Playwright.create();

        Boolean headlessValue = Boolean.parseBoolean(config.getProperty("headless"));

        switch (config.getProperty("browser").toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headlessValue));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headlessValue));
                break;
            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headlessValue));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headlessValue));
                break;
            case "edge":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headlessValue));
                break;
        }
        page = browser.newPage();
    }

    /**
     * getPage
     * @param n/a
     * @return page object
     */
    public static Page getPage() {
        return page;
    }

    /**
     * setupDriver
     * @param n/a
     * @return n/a
     * Calls on the PlaywrightDriver method which loads the properties and creates the Playwright page object
     */
    public static void setupDriver() {
        playwrightDriver = new PlaywrightDriver();
    }

    /**
     * openPage
     * @param url - url of page to navigate to
     * @return n/a
     */
    public static void openPage(String url) {
        page.navigate(url);
    }

    /**
     * closeBrowser
     * @param n/a
     * @return n/a
     * closes the browser and page objects
     */
    public static void closeBrowser() {
        browser.close();
        page.close();
    }

    /**
     * quitPlaywright
     * @param n/a
     * @return n/a
     * if page object is not null, closes playwright object
     */
    public static void quitPlaywright() {
        if (page!=null) {
            playwright.close();
        }
    }
}
