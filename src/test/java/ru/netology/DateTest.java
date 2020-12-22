package ru.netology;

import com.codeborne.selenide.Condition;
import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DateTest {

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSubmitRequest() {
        $("[data-test-id=city] input").setValue("Са");
        $(byText("Санкт-Петербург")).click();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now().plusDays(3);
        String date = localDate.format(dateTimeFormatter);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79210000000");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 15000).shouldHave(exactText("Успешно! Встреча успешно забронирована на " + date));
    }
}
