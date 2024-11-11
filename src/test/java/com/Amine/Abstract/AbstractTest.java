package com.Amine.Abstract;


import io.github.bonigarcia.wdm.WebDriverManager;
import listener.TestListener;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import util.Config;
import util.Constants;

import java.net.MalformedURLException;

import java.net.URI;


@Listeners({TestListener.class})
public abstract class AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
    protected WebDriver driver;

    //we call this before any test suite is executed
    @BeforeSuite
    public void setupConfig(){
        Config.initialize();

    }

    @BeforeTest
    public void setUp(ITestContext ctx) throws MalformedURLException {

        this.driver= Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver():getLocalDriver();
        this.driver.manage().window().maximize();
        ctx.setAttribute(Constants.DRIVER, this.driver);
    }
/*
    protected ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-autofill-keyboard-accessory-view");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("disable-features=AutofillAddressEnabled");
        options.setExperimentalOption("prefs", Map.of(
                "profile.autofill_profile_enabled", false,
                "profile.password_manager_enabled", false,
                "credentials_enable_service", false
        ));
        return options;
    }

 */

    private WebDriver getRemoteDriver() throws MalformedURLException {

        Capabilities capabilities;
        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
            capabilities = new FirefoxOptions();
        } else {
            capabilities = new ChromeOptions();
        }

        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);

        log.info("grid url: {}",url);

        URI uri = URI.create(url);
        return new RemoteWebDriver(uri.toURL(), capabilities);
    }

    private WebDriver getLocalDriver() {
        String browser = Config.get("browser");
        // or System.getProperty("browser") if you're using it directly from the pom.xml

        if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    }


    @AfterTest
    public void tearDown(){

        if (driver != null)
            driver.quit();
    }

    /*
    @AfterMethod
    public void sleep(){

        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
    }*/
}
