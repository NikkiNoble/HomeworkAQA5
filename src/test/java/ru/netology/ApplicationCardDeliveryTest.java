package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class ApplicationCardDeliveryTest {

    private SelenideElement date = $("[data-test-id=date] input");
    private String page = "http://localhost:9999";
    private SelenideElement agreement = $("[data-test-id=agreement]");
    private SelenideElement cityName = $("[data-test-id=city] input");
    private SelenideElement personName = $("[data-test-id=name] input");
    private SelenideElement phoneNumber = $("[data-test-id=phone] input");


    @Test
    public void ShouldSubmitRequestAndChangeBookedDate(){
        open("http://localhost:9999");
        cityName.setValue(DataGenerator.generateNewApplication().getCity());
        personName.setValue(DataGenerator.generateNewApplication().getName());
        phoneNumber.setValue(DataGenerator.generateNewApplication().getPhone());
        agreement.click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Успешно")).waitUntil(visible, 5000);
        date.doubleClick().sendKeys(BACK_SPACE);
        date.setValue(DataGenerator.generateNewApplication().getDate());
        $$("button").find(exactText("Запланировать")).click();
        $$("button").find(exactText("Перепланировать")).click();
        $(withText("Успешно")).waitUntil(visible, 5000);
    }

    @Test
    void ShouldNotSubmitRequest(){
        open("http://localhost:9999");
        personName.setValue(DataGenerator.generateNewApplication().getName());
        phoneNumber.setValue(DataGenerator.generateNewApplication().getPhone());
        agreement.click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Поле обязательно для заполнения")).waitUntil(visible, 5000);
    }

    @Test
    void ShouldNotSubmitRequestWithWrongData(){
        open("http://localhost:9999");
        cityName.setValue(DataGenerator.generateNewErrorApplication().getCity());
        personName.setValue(DataGenerator.generateNewErrorApplication().getName());
        phoneNumber.setValue(DataGenerator.generateNewErrorApplication().getPhone());
        agreement.click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Доставка в выбранный город недоступна")).waitUntil(visible, 5000);
    }

}
