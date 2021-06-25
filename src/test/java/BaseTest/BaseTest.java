package BaseTest;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private static Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    public static String browserName="chrome";
    DesiredCapabilities capabilities = new DesiredCapabilities();

    @BeforeScenario
    public void setUP() throws Exception {

        String baseUrl  ="https://www.n11.com/";
        String platform ="windows";
        String browser  ="chrome";

        if (StringUtils.isEmpty(System.getenv("key"))){
            if ("windows".equalsIgnoreCase(platform)){
                if ("chrome".equalsIgnoreCase(browser)){
                    ChromeOptions options = new ChromeOptions();
                    capabilities = DesiredCapabilities.chrome();
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    options.setExperimentalOption("prefs", prefs);
                    options.addArguments("--kiosk");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--start-fullscreen");
                    System.setProperty("webdriver.chrome.driver","WebDriver/chromedriver.exe");
                    driver = new ChromeDriver(options);
                    driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

                }else if ("firefox".equalsIgnoreCase(browser)){
                    FirefoxOptions options = new FirefoxOptions();
                    capabilities = DesiredCapabilities.firefox();
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    options.addArguments("--kiosk");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--start-fullscreen");
                    FirefoxProfile profile = new FirefoxProfile();
                    capabilities.setCapability(FirefoxDriver.PROFILE,profile);
                    capabilities.setCapability("marionette",true);
                    System.setProperty("webdriver.gecko.driver","WebDriver/geckodriver.exe");
                    driver = new FirefoxDriver(options);
                    driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
                }


            }else if("mac".equalsIgnoreCase(platform)){
                if ("chrome".equalsIgnoreCase(browser)){
                    ChromeOptions options = new ChromeOptions();
                    capabilities = DesiredCapabilities.chrome();
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    options.setExperimentalOption("prefs", prefs);
                    options.addArguments("--kiosk");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--start-fullscreen");
                    System.setProperty("webdriver.chrome.driver","WebDriver/chromedriver");
                    driver = new ChromeDriver(options);
                    driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
                }else if ("firefox".equalsIgnoreCase(browser)){
                    FirefoxOptions options = new FirefoxOptions();
                    capabilities = DesiredCapabilities.firefox();
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("profile.default_content_setting_values.notifications", 2);

                    options.addArguments("--kiosk");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--start-fullscreen");
                    FirefoxProfile profile = new FirefoxProfile();
                    capabilities.setCapability(FirefoxDriver.PROFILE,profile);
                    capabilities.setCapability("marionette",true);
                    System.setProperty("webdriver.gecko.driver","WebDriver/geckodriver");
                    driver = new FirefoxDriver(options);
                    driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
                }

            }

        }

        driver.manage().window().fullscreen();
        driver.get(baseUrl);
        LOGGER.info(baseUrl + " BROWSERDA ACILDI.");
    }

    @AfterScenario
    public void tearDown() throws Exception  {

        LOGGER.info("TEST TAMAMLANDI DRIVER KAPATILIYOR..");
        driver.quit();

    }

}
