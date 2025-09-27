package ru.yandex.practicum.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.*;
import ru.yandex.practicum.pages.util.EnvConfig;

@RunWith(Parameterized.class)
public class OrderTest {
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String metroNamePrefix;
    private final By orderButton;
    private final By metroSelectLocator;
    private final By calendarDateLocator;
    private final By bookingDurationLocator;
    private final By[] colorCheckboxLocators;
    private final String courierComment;

        @Rule
    public DriverFactory factory = new DriverFactory();

    public OrderTest(By orderButton,
                     String name,
                     String surname,
                     String address,
                     String phone,
                     String metroNamePrefix,
                     By metroSelectLocator,
                     By calendarDateLocator,
                     By bookingDurationLocator,
                     By[] colorCheckboxLocators,
                     String courierComment) {

        // У нас две кнопки
        this.orderButton = orderButton;

        // Для окна ForWhomPage
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.metroNamePrefix = metroNamePrefix;
        this.metroSelectLocator = metroSelectLocator;

        // Для окна BookingPage
        this.calendarDateLocator = calendarDateLocator;
        this.bookingDurationLocator = bookingDurationLocator;
        this.colorCheckboxLocators = colorCheckboxLocators;
        this.courierComment = courierComment;
    }

    @Parameterized.Parameters
    public static Object[][] getParams(){
        return new Object[][]{
                {
                    MainPage.orderTopButton,
                    // параметры для ForWhomPage
                    "Алексей", // name
                    "Воробьёв", // surname
                    "Санкт-Петербург", // address
                    "+7147852963", // phone
                    "Крас", // первые буквы метро
                    ForWhomPage.getMetroLocatorByIndex(2), // локатор по порядковому номеру оставшегося метро в селекторе
                    // параметры для ForWhomPage
                    BookingPage.getCalendarDateLocator(2,5), // локатор выбора дня в календаре
                    BookingPage.getBookingDurationLocator(3), // локатор выбора времени аренды
                    new By[]{ // локаторы для colors checkboxes
                            BookingPage.getColorCheckboxLocator(EnvConfig.BLACK_CHECKBOX_NAME),
                            BookingPage.getColorCheckboxLocator(EnvConfig.GREY_CHECKBOX_NAME),
                    },
                    "Привет. Привези мне самокатную пиццу, пожалуйста." // комментарий для курьера
                },
                {
                        MainPage.orderBottomButton,
                        // параметры для ForWhomPage
                        "Андрей", // name
                        "Кикабидзе", // surname
                        "Ереван", // address
                        "+9874261533", // phone
                        "Пио", // первые буквы метро
                        ForWhomPage.getMetroLocatorByIndex(1), // локатор по порядковому номеру оставшегося метро в селекторе
                        // параметры для ForWhomPage
                        BookingPage.getCalendarDateLocator(1,1), // локатор выбора дня в календаре
                        BookingPage.getBookingDurationLocator(1), // локатор выбора времени аренды
                        new By[]{ // локаторы для colors checkboxes
                                BookingPage.getColorCheckboxLocator(EnvConfig.GREY_CHECKBOX_NAME),
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
        turtle.fillPage(name, surname, address, phone, metroNamePrefix, metroSelectLocator);
        turtle.clickNextButton();

        BookingPage bookingPage = new BookingPage(driver);
        bookingPage.fillPage(calendarDateLocator, bookingDurationLocator, colorCheckboxLocators, courierComment);
        bookingPage.clickOrderButton();

        ConfirmOrderPage confirmOrderPage = new ConfirmOrderPage(driver);
        confirmOrderPage.waitToLoad();
        confirmOrderPage.clickOrderButton();

        DoneOrderPage doneOrderPage = new DoneOrderPage(driver);
        doneOrderPage.waitToLoad();
    }
}
