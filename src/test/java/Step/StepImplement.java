package Step;

import BaseTest.BaseTest;
import ElementsInfo.ElementsInfo;
import TestingBase.ElementHelper;
import TestingBase.StoreHelper;
import com.thoughtworks.gauge.Step;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class StepImplement extends BaseTest {

    private static Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory
            .getLogger(StepImplement.class);
    private Actions actions =new Actions(driver);
    public static int DEFAULT_MAX_ITERATION_COUNT = 150;
    public static int DEFAULT_MILLISECOND_WAIT_AMOUNT = 100;
    private File fileoutput=new File("N11_All_Stores.csv");

    public StepImplement() {
        PropertyConfigurator
                .configure(StepImplement.class.getClassLoader().getResource("log4j.properties"));
    }

    private WebElement findElement(String key) {
        ElementsInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(infoParam));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;
    }

    private List<WebElement> findElements(String key){
        ElementsInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam= ElementHelper.getElementInfoToBy(elementInfo);
        return driver.findElements(infoParam);
    }

    private void clickElement(WebElement element){element.click();}

    private void clickElementBy(String key){findElement(key).click();}

    private void hoverElement(WebElement element){actions.moveToElement(element).build().perform();}

    private void hoverElementBy(String key){
     WebElement element =findElement(key);
     actions.moveToElement(element).build().perform();
     }
    public WebElement getElementWithKeyIfExists(String key) {
        WebElement webElement;
        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            try {
                webElement = findElementWithKey(key);
                logger.info(key + " elementi bulundu.");
                return webElement;
            } catch (WebDriverException e) {
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail("Element: '" + key + "' doesn't exist.");
        return null;
    }

    private boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public WebElement findElementWithKey(String key) {
        return findElement(key);
    }

    private boolean isDisplayedBy(By by) {
        return driver.findElement(by).isDisplayed();
    }

    public String getElementText(String key) {
        return findElement(key).getText();
    }
    public void javaScriptClicker(WebDriver driver, WebElement element) {

        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("var evt = document.createEvent('MouseEvents');"
                + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
                + "arguments[0].dispatchEvent(evt);", element);
    }

    public String getElementAttributeValue(String key, String attribute) {
        return findElement(key).getAttribute(attribute);
    }
    public void javaScriptClicker(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }



    @Step({"<int> saniye bekle"})
    public void waitBySeconds(int seconds) {
        try {
            logger.info(seconds + " saniye bekleniyor.");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step({"<long> milisaniye bekle"})
    public void waitByMilliSeconds(long milliseconds) {
        try {
            logger.info(milliseconds + " milisaniye bekleniyor.");
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step({"Elementi bekle ve sonra tıkla <key>"})
    public void waitForElementThenClick(String key){
        getElementWithKeyIfExists(key);
        clickElement(key);
    }

    @Step({"Elementine tıkla <key>"})
    public void clickElement(String key) {
        if (!key.equals("")) {
            WebElement element = findElement(key);
            hoverElement(element);
            clickElement(element);
            logger.info(key + " elementine tiklandi.");
        }
    }
    @Step({"<text> textini <key> elemente yaz"})
    public void sendKeys(String text, String key) {
        if (!key.equals("")) {
            findElement(key).sendKeys(text);
            findElement(key).sendKeys(Keys.ENTER);
            logger.info(key + " elementine " + text + " texti yazildi ve enter tuşuna basildi.");
        }
    }
    @Step({"Elemente BACKSPACE keyi yolla <key>"})
    public void sendKeyToElementBACKSPACE(String key) {
        findElement(key).sendKeys(Keys.BACK_SPACE);
        logger.info(key + " elementine BACKSPACE keyi yollandı.");
    }

    @Step({"<key> elementinin <attribute> niteliği <value> değerine sahip mi"})
    public void checkElementAttributeEquals(String key, String attribute, String expectedValue) {
        WebElement element = findElement(key);

        String actualValue;
        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            actualValue = element.getAttribute(attribute).trim();
            if (actualValue.equals(expectedValue)) {
                logger.info(
                        key + " elementinin " + attribute + " niteliği " + expectedValue + " değerine sahip.");
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail("Elementin Attribute değeri beklenen değerle aynı değil");
    }

    @Step({"<key> elementinin text degerini getir"})
    public void checkAttribute(String key){
            WebElement element = findElement(key);

            logger.info(key + " elementinine : " + element.getText());

    }
    @Step({"<key> listesinden random bir element seç"})
    public void randomPick(String key) {
        List<WebElement> elements = findElements(key);
        Random random = new Random();
        int index = random.nextInt(elements.size());
        elements.get(index).click();
    }

    @Step({"<url> adresine git"})
    public void goToUrl(String url) {
        driver.get(url);
        logger.info(url + " adresine gidiliyor.");
    }


    @Step({"<key> harf bilgili <key> magaza bilgilerini csv dosyasına yaz"})
    public void writeListItemsToCsv(String letters,String key) throws IOException {
        logger.info("Magaza bilgileri CSV dosyasina kaydediliyor.");

        List<WebElement> storeElementList = findElements(key);

         char c;
         for(c = 'A'; c <= 'Z';){
             logger.info(c+ " Harfli magazalar yaziliyor");
             saveElementAttributeCsv(storeElementList,c);
             driver.findElement(By.xpath("//div[@class=\"letters\"]/span[@data-has-seller='"+ ++c +"']")).click();
             waitBySeconds(2);
             storeElementList=findElements(key);
         }
        logger.info("Tum magazalar CSV dosyasina kaydedildi");
    }

public void saveElementAttributeCsv(List<WebElement> list,char letter ) throws IOException {
    FileWriter fileWriter = new FileWriter(fileoutput, false);
    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
    StringBuilder stringBuilder = new StringBuilder();
    char chr =letter;
    String store ="";
    Iterator<WebElement> it = list.iterator();
    stringBuilder.append(chr+ " HARFLI MAGAZALAR-------------------------------------------------------------------------\n");
    while (it.hasNext()){
        store=it.next().getText();
        stringBuilder.append(store).append(",\t");
    }
    bufferedWriter.write(stringBuilder.toString());
    bufferedWriter.flush();
    bufferedWriter.close();
}


    @Step({"<key> Elementine focus ol"})
    public void elementFocus(String key) {
        actions.moveToElement(findElement(key));
        actions.build().perform();
        logger.info(key + " elementine focus olundu.");
    }

    @Step({"<key> elementine focus ile tıkla"})
    public void clickElementWithFocus(String key) {
        actions.moveToElement(findElement(key));
        actions.click();
        actions.build().perform();
        logger.info(key + " elementine focus ile tiklandi.");
    }

    @Step({"Javascript ile xpath tıkla <xpath>"})
    public void javascriptClickerWithXpath(String xpath) {
        assertTrue("Element bulunamadi", isDisplayedBy(By.xpath(xpath)));
        javaScriptClicker(driver, driver.findElement(By.xpath(xpath)));
        logger.info("Javascript ile " + xpath + " tiklandi.");
    }
    @Step({"Chrome uyari popup'ini kabul et"})
    public void acceptChromeAlertPopup() {
        driver.switchTo().alert().accept();
    }

}