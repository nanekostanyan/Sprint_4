package ru.yandex.practicum.pages.util;

import java.time.Duration;

public class EnvConfig {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    public static final int IMPLICITY_TIMEOUT = 2;
    public static final int EXPLICITY_TIMEOUT = 2;
    public static final Duration WEB_DRIVER_WAIT_TIMEOUT = Duration.ofSeconds(2);
    public static final int TOP_BUTTON = 0;
    public static final int BOTTOM_BUTTON = 1;
    public static final String BLACK_CHECKBOX_NAME = "black";
    public static final String GREY_CHECKBOX_NAME = "grey";
}
