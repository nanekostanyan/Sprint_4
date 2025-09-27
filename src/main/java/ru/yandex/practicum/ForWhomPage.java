package ru.yandex.practicum;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

public class ForWhomPage {
    private final WebDriver driver;

    private By forWhomPage = By.cssSelector(".Order_Content__bmtHS");
    private By nameField = By.cssSelector("input[placeholder=\"* Имя\"]");
    private By surnameField = By.cssSelector("input[placeholder=\"* Фамилия\"]");
    private By addressField = By.cssSelector("input[placeholder=\"* Адрес: куда привезти заказ\"]");
    private By metroSelect = By.cssSelector(".select-search__input");
    private static final String metroSelectorString = ".//ul[@class='select-search__options']/li[%d]";
    private By phoneNumber = By.cssSelector("input[placeholder=\"* Телефон: на него позвонит курьер\"]");
    private By nextButton = By.cssSelector(".Order_NextButton__1_rCA button");

    public ForWhomPage(WebDriver driver) {
        this.driver = driver;
    }

    // Возвращает локатор оставшейся [index] станции метро в селекторе
    public static By getMetroLocatorByIndex(int index) {
        return By.xpath(String.format(metroSelectorString, index));
    }

    public void waitToLoad() {
        new WebDriverWait(driver, EnvConfig.WEB_DRIVER_WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(forWhomPage));
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void inputName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void inputSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void inputAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void inputPhone(String phone) {
        driver.findElement(phoneNumber).sendKeys(phone);
    }

    public void metroSelectClick(String name) {
        driver.findElement(metroSelect).sendKeys(name);
    }

    public void selectMetro(By locator) {
        WebElement metro = driver.findElement(locator);
        Assert.assertTrue(metro.isEnabled());
        metro.click();
    }

    public void fillPage(String name,
                         String surname,
                         String address,
                         String phone,
                         String metroNamePrefix,
                         By metroSelectLocator) {

        waitToLoad();
        inputName(name);
        inputSurname(surname);
        inputAddress(address);
        inputPhone(phone);
        metroSelectClick(metroNamePrefix);
        selectMetro(metroSelectLocator);
    }
}
