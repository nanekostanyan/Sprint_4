package ru.yandex.practicum.tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.MainPage;
import ru.yandex.practicum.StatusPage;

public class ScooterTests {

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testNonExistingOrderNotFound() throws InterruptedException {

        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickOnStatusButton();
        mainPage.enterOrderIn("123");
        StatusPage statusPage = mainPage.clickOnGoButton();
        statusPage.checkErrorMessage();

    }
}
