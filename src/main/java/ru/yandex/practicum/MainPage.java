package ru.yandex.practicum;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

public class MainPage {

    private final WebDriver driver;

    private By accordion = By.cssSelector(".accordion");

    public static final By orderTopButton = By.cssSelector(".Button_Button__ra12g");
    public static final By orderBottomButton = By.cssSelector(".Home_FinishButton__1_cWm>button");

    public static By createAccordionLocator(int number) {
        return By.cssSelector("#accordion__heading-" + number);
    }

    public static By createAccordionAnswer(int number) {
        return By.cssSelector("#accordion__panel-" + number + " p");
    }

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get(EnvConfig.BASE_URL);
    }

    public void scrollToAccordion() {
        WebElement element = new WebDriverWait(driver, EnvConfig.WEB_DRIVER_WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(accordion));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickOnAccordion(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void checkAccordionText(By locator, String expected) {
        WebElement element = new WebDriverWait(driver, EnvConfig.WEB_DRIVER_WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        String actualText = driver.findElement(locator).getText();
        Assert.assertEquals("Текст не соответствует ожидаемому", expected, actualText);
    }

    public void clickOrderButton(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        element.click();
    }
}
