package BaseTest;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driver;
    private static Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    DesiredCapabilities capabilities = new DesiredCapabilities();
    String nodeUrl="http://192.168.2.177:29980/wd/hub";

    @BeforeScenario
    public void setUP() throws Exception {

        String baseUrl  ="https://www.n11.com/";
        String browser ="remoteBrowser";

        if(browser.equals("chrome")){
        ChromeOptions options = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        System.setProperty("webdriver.chrome.driver","WebDriver/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.get(baseUrl);
        LOGGER.info(baseUrl + " BROWSERDA ACILDI.");
        }

        else if(browser.equals("remoteBrowser")){

            capabilities = DesiredCapabilities.chrome();
            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.WINDOWS);
            driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.get(baseUrl);
            LOGGER.info(baseUrl + " REMOTE BROWSERDA ACILDI.");
        }
    }

    @AfterScenario
    public void tearDown() throws Exception  {

        LOGGER.info("TEST TAMAMLANDI DRIVER KAPATILIYOR..");
        driver.quit();

    }

}
