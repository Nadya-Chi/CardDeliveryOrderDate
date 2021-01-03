package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.generateByDate;
import static ru.netology.DataGenerator.generateByPhone;

public class DateTest {

    OrderDeliveryCardInfo user = DataGenerator.generateByUsers("ru");

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSubmitRequest() {
        $("[data-test-id=city] input").setValue(user.getCity());

        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(generateByDate(5));

        $("[data-test-id=name] input").setValue(user.getName());
        $("[data-test-id=phone] input").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=success-notification]").waitUntil(Condition.visible, 15000)
                .shouldHave(exactText("Успешно! Встреча успешно запланирована на " + generateByDate(5)));

        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(generateByDate(4));
        $("[class='button__text']").click();
        $("[data-test-id=replan-notification]").waitUntil(Condition.visible, 15000)
                .shouldHave(exactText("Необходимо подтверждение У вас уже запланирована встреча на другую дату." +
                        " Перепланировать? Перепланировать"));
        $("[data-test-id=replan-notification] [role=button]").click();
        $("[data-test-id=success-notification]").waitUntil(Condition.visible, 15000)
                .shouldHave(exactText("Успешно! Встреча успешно запланирована на " + generateByDate(4)));
    }

    @Test
    public void shouldNotSubmitRequest() {
        $("[data-test-id=city] input").setValue(user.getCity());

        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(generateByDate(5));

        $("[data-test-id=name] input").setValue(user.getName());
        $("[data-test-id=phone] input").setValue(generateByPhone());
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=phone] [class='input__sub']")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
