package ru.yandex.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

public class BookingPage {
    private final WebDriver driver;

    private By bookingPage = By.cssSelector(".Order_Content__bmtHS");
    private By datePickField = By.cssSelector(".react-datepicker__input-container>input");
    private By bookingDurationField = By.cssSelector(".Dropdown-placeholder");
    private By commentForCourier = By.cssSelector(".Input_InputContainer__3NykH>input[class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    private By orderButton = By.xpath(".//div[starts-with(@class,'Order_Buttons')]/button[text()='Заказать']");
    private static final String calendarDateString = ".react-datepicker__month .react-datepicker__week:nth-child(%d) .react-datepicker__day:nth-child(%d)";
    private static final String bookingDurationString = ".Dropdown-menu .Dropdown-option:nth-child(%d)";
    private static final String colorCheckboxString = "input#%s[type='checkbox']";

    public BookingPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final By getCalendarDateLocator(int row, int column) {
        String string = String.format(calendarDateString, row, column);
        return By.cssSelector(string);
    }

    public static final By getBookingDurationLocator(int number) {
        String string = String.format(bookingDurationString, number);
        return By.cssSelector(string);
    }

    public static final By getColorCheckboxLocator(String color) {
        String string = String.format(colorCheckboxString, color);
        return By.cssSelector(string);
    }

    public void waitToLoad() {
        new WebDriverWait(driver, EnvConfig.WEB_DRIVER_WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(bookingPage));
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void selectDate(By locator) {
        driver.findElement(datePickField).click();

        WebElement element = new WebDriverWait(driver, EnvConfig.WEB_DRIVER_WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));;

        element.click();
    }

    public void selectBookingDuration(By locator) {
        driver.findElement(bookingDurationField).click();

        WebElement element = new WebDriverWait(driver, EnvConfig.WEB_DRIVER_WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));;

        element.click();
    }

    public void selectColorCheckboxes(By[] locators) {
        for (int i = 0; i < locators.length; i ++) {
            driver.findElement(locators[i]).click();
        }
    }

    public void inputCommentForCourier(String comment) {
        driver.findElement(commentForCourier).sendKeys(comment);
    }

    public void fillPage(By dateLocator, By bookingDurationLocator, By[] colorLocators, String comment) {
        waitToLoad();
        selectDate(dateLocator);
        selectBookingDuration(bookingDurationLocator);
        selectColorCheckboxes(colorLocators);
        inputCommentForCourier(comment);
    }
}
