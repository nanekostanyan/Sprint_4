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

    private final By accordion = By.cssSelector(".accordion");
    private final By orderTopButton = By.cssSelector(".Button_Button__ra12g");
    private final By orderBottomButton = By.cssSelector(".Home_FinishButton__1_cWm>button");
    private final String accordionElementString = "#accordion__heading-%d";
    private final String accordionAnswerString = "#accordion__panel-%d p";

    private By createAccordionLocator(int number) {
        return By.cssSelector(String.format(accordionElementString, number));
    }

    private By createAccordionAnswer(int number) {
        return By.cssSelector(String.format(accordionAnswerString, number));
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

    public void clickOnAccordion(int accordingIndex) {
        By locator = createAccordionLocator(accordingIndex);
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void checkAccordionText(int accordingIndex, String expected) {
        By locator = createAccordionAnswer(accordingIndex);
        WebElement element = new WebDriverWait(driver, EnvConfig.WEB_DRIVER_WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        String actualText = driver.findElement(locator).getText();
        Assert.assertEquals("Текст не соответствует ожидаемому", expected, actualText);
    }

    public void clickOrderButton(int orderButton) {
        By locator = null;
        switch (orderButton) {
            case EnvConfig.TOP_BUTTON:
                locator = orderTopButton;
                break;
            case EnvConfig.BOTTOM_BUTTON:
                locator = orderBottomButton;
                break;
            default:
                Assert.fail(String.format("Нет такой кнопки заказа: %d", orderButton));
        }

        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        element.click();
    }
}
