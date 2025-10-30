package ru.yandex.practicum.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.MainPage;

@RunWith(Parameterized.class)
public class ImportantQuestionsTest {
    private final String testName;
    private final int accordionIndex;
    private final String expectedText;

    public ImportantQuestionsTest(String testName, int accordionIndex, String expectedText){
        this.testName = testName;
        this.accordionIndex = accordionIndex;
        this.expectedText = expectedText;
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Object[][] getAccordionParams(){
        return new Object[][] {
                {"Тестирование аккордиона - 0 элемент.", 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Тестирование аккордиона - 1 элемент.", 1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Тестирование аккордиона - 2 элемент.", 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Тестирование аккордиона - 3 элемент.", 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Тестирование аккордиона - 4 элемент.", 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Тестирование аккордиона - 5 элемент.", 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Тестирование аккордиона - 6 элемент.", 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Тестирование аккордиона - 7 элемент.", 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void testNonExistingOrderNotFound() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.scrollToAccordion();
        mainPage.clickOnAccordion(this.accordionIndex);
        mainPage.checkAccordionText(this.accordionIndex, this.expectedText);
    }
}

