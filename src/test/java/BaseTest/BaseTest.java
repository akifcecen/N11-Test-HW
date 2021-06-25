package BaseTest;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driver;
    private static Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    DesiredCapabilities capabilities = new DesiredCapabilities();

    @BeforeScenario
    public void setUP() throws Exception {

        String baseUrl  ="https://www.n11.com/";

        ChromeOptions options = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-fullscreen");
        System.setProperty("webdriver.chrome.driver","WebDriver/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);


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
