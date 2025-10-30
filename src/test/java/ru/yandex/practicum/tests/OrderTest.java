package ru.yandex.practicum.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.*;
import ru.yandex.practicum.pages.util.EnvConfig;

@RunWith(Parameterized.class)
public class OrderTest {
    private final String testName;
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String metroNamePrefix;
    private final int orderButton;
    private final int metroSelectNumber;
    private final int calendarDateRow;
    private final int calendarDateColumn;
    private final int bookingDurationNumber;
    private final String[] colorOfheckboxes;
    private final String courierComment;

    @Rule
    public DriverFactory factory = new DriverFactory();

    public OrderTest(String testName,
                     int orderButton,
                     String name,
                     String surname,
                     String address,
                     String phone,
                     String metroNamePrefix,
                     int metroSelectNumber,
                     int calendarDateRow,
                     int calendarDateColumn,
                     int bookingDurationNumber,
                     String[] colorOfheckboxes,
                     String courierComment) {

        this.testName = testName;

        // У нас две кнопки
        this.orderButton = orderButton;

        // Для окна ForWhomPage
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.metroNamePrefix = metroNamePrefix;
        this.metroSelectNumber = metroSelectNumber;

        // Для окна BookingPage
        this.calendarDateRow = calendarDateRow;
        this.calendarDateColumn = calendarDateColumn;
        this.bookingDurationNumber = bookingDurationNumber;
        this.colorOfheckboxes = colorOfheckboxes;
        this.courierComment = courierComment;
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Object[][] getParams(){
        return new Object[][]{
            {
                "Тестирование флоу заказа через верхнюю кнопку.", // Имя теста
                EnvConfig.TOP_BUTTON,
                // параметры для ForWhomPage
                "Алексей", // name
                "Воробьёв", // surname
                "Санкт-Петербург", // address
                "+7147852963", // phone
                "Крас", // первые буквы метро
                2, // порядковый номер оставшегося метро в селекторе
                // параметры для ForWhomPage
                2,// строка календаря выбора даты доставки
                5, // колонка календаря выбора даты доставки
                3, // номер строки выбора времени аренды
                new String[]{ // colors checkboxes
                        EnvConfig.BLACK_CHECKBOX_NAME,
                        EnvConfig.GREY_CHECKBOX_NAME,
                },
                "Привет. Привези мне самокатную пиццу, пожалуйста." // комментарий для курьера
            },
            {
                "Тестирование флоу заказа через нижнюю кнопку.", // Имя теста
                EnvConfig.BOTTOM_BUTTON,
                // параметры для ForWhomPage
                "Андрей", // name
                "Кикабидзе", // surname
                "Ереван", // address
                "+9874261533", // phone
                "Пио", // первые буквы метро
                1, // порядковый номер оставшегося метро в селекторе
                // параметры для ForWhomPage
                1, // строка календаря выбора даты доставки
                1, // колонка календаря выбора даты доставки
                1, // номер строки выбора времени аренды
                new String[]{ // colors checkboxes
                        EnvConfig.GREY_CHECKBOX_NAME,
                },
                "" // комментарий для курьера
            },
        };
    }

    @Test
    public void testToMakeAnOrder() throws InterruptedException {
        WebDriver driver = factory.getDriver();

        var mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickOrderButton(orderButton);

        ForWhomPage turtle = new ForWhomPage(driver);
        turtle.fillPage(name, surname, address, phone, metroNamePrefix, metroSelectNumber);
        turtle.clickNextButton();

        BookingPage bookingPage = new BookingPage(driver);
        bookingPage.fillPage(calendarDateRow, calendarDateColumn, bookingDurationNumber, colorOfheckboxes, courierComment);
        bookingPage.clickOrderButton();

        ConfirmOrderPage confirmOrderPage = new ConfirmOrderPage(driver);
        confirmOrderPage.waitToLoad();
        confirmOrderPage.clickOrderButton();

        DoneOrderPage doneOrderPage = new DoneOrderPage(driver);
        doneOrderPage.waitToLoad();
    }
}
