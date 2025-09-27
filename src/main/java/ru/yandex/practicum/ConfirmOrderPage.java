package ru.yandex.practicum;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

public class ConfirmOrderPage {
    private final WebDriver driver;

    private By confirmOrderPage = By.cssSelector(".Order_Modal__YZ-d3");
    private By confirmOrderButton = By.xpath(".//div[starts-with(@class,'Order_Buttons')]/button[text()='Да']");

    public ConfirmOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitToLoad() {
        new WebDriverWait(driver, EnvConfig.WEB_DRIVER_WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(confirmOrderPage));
    }

    public void clickOrderButton() {
        driver.findElement(confirmOrderButton).click();
    }
}
