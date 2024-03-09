package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {


    private String generateDate(int addDays, String pattern){
        return LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    void shouldCardDelivery(){
        open("http://localhost:9999");
        $("[data-test-id='city']input").setValue("Казань");
        String planningDate = generateDate(4,"dd.mm.yyyy");
        $("[data-test-id='date']input").sendKeys(Keys.chord(Keys.SHIFT, Keys.PAGE_UP), Keys.BACK_SPACE);
        $("[data-test-id='date']input").setValue(planningDate);
        $("[data-test-id='name']input").setValue("Казаков-Сергеевич Андрей");
        $("[data-test-id='phone']input").setValue("+79200000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));



    }
}
