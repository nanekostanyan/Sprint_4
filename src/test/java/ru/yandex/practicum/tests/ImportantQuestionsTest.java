package ru.yandex.practicum.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.MainPage;

@RunWith(Parameterized.class)
public class ImportantQuestionsTest {
    private final By accordion;
    private final By accordionAnswer;
    private final String expectedText;

    public ImportantQuestionsTest(By accordion, By accordionAnswer, String expectedText){
        this.accordion = accordion;
        this.accordionAnswer = accordionAnswer;
        this.expectedText = expectedText;
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Parameterized.Parameters
    public static Object[][] getAccordionParams(){
        return new Object[][] {
                {MainPage.createAccordionLocator(0), MainPage.createAccordionAnswer(0), "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {MainPage.createAccordionLocator(1), MainPage.createAccordionAnswer(1), "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {MainPage.createAccordionLocator(2), MainPage.createAccordionAnswer(2), "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {MainPage.createAccordionLocator(3), MainPage.createAccordionAnswer(3), "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {MainPage.createAccordionLocator(4), MainPage.createAccordionAnswer(4), "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {MainPage.createAccordionLocator(5), MainPage.createAccordionAnswer(5), "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {MainPage.createAccordionLocator(6), MainPage.createAccordionAnswer(6), "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {MainPage.createAccordionLocator(7), MainPage.createAccordionAnswer(7), "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void testNonExistingOrderNotFound() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.scrollToAccordion();
        mainPage.clickOnAccordion(this.accordion);
        mainPage.checkAccordionText(this.accordionAnswer, this.expectedText);
    }
}

