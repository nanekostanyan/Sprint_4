package ru.yandex.practicum;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;

public class StatusPage {
    private final WebDriver driver;
    private By errorImage = By.cssSelector("img[alt='Not found']");

    public StatusPage(WebDriver driver) {

        this.driver = driver;
    }

    public void checkErrorMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(errorImage));
        Assert.assertTrue(driver.findElement(errorImage).isDisplayed());
    }

}
