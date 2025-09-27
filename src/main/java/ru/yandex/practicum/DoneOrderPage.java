package ru.yandex.practicum;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

public class DoneOrderPage {
    private final WebDriver driver;

    private By doneOrderPage = By.xpath(".//div[starts-with(@class,'Order_ModalHeader') and text()='Заказ оформлен']");

    public DoneOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitToLoad() {
        Assert.assertTrue("Страница с подтверждением об успешном заказе отсутствует",
                new WebDriverWait(driver, EnvConfig.WEB_DRIVER_WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(doneOrderPage)).isDisplayed());
    }
}
