package ru.netology.data;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class CardPage {
    private SelenideElement date = $("[data-test-id=date] input");
    private SelenideElement agreement = $("[data-test-id=agreement]");
    private SelenideElement cityName = $("[data-test-id=city] input");
    private SelenideElement personName = $("[data-test-id=name] input");
    private SelenideElement phoneNumber = $("[data-test-id=phone] input");

    public void openPage() {
        open("http://localhost:9999");
    }

    public void sendRequestWithAllFieldsFilledIn() {
        cityName.setValue(DataGenerator.generateNewApplication().getCity());
        personName.setValue(DataGenerator.generateNewApplication().getName());
        phoneNumber.setValue(DataGenerator.generateNewApplication().getPhone());
        agreement.click();
        $$("button").find(exactText("Запланировать")).click();
    }
    public void sendRequestWithWrongData() {
        cityName.setValue(DataGenerator.generateNewErrorApplication().getCity());
        personName.setValue(DataGenerator.generateNewErrorApplication().getName());
        phoneNumber.setValue(DataGenerator.generateNewErrorApplication().getPhone());
        agreement.click();
        $$("button").find(exactText("Запланировать")).click();
    }
    public void findSuccessElement() {
        $(withText("Успешно")).waitUntil(visible, 5000);
    }
    public void findErrorElement() {
        $(withText("Доставка в выбранный город недоступна")).waitUntil(visible, 5000);
    }
    public void findError() {
        $(withText("Поле обязательно для заполнения")).waitUntil(visible, 5000);
    }
    public void replanWithNewDate() {
        date.doubleClick().sendKeys(BACK_SPACE);
        date.setValue(DataGenerator.generateNewApplication().getDate());
        $$("button").find(exactText("Запланировать")).click();
        $$("button").find(exactText("Перепланировать")).click();
        $(withText("Успешно")).waitUntil(visible, 5000);
    }
    public void sendRequestWithEmptyFields() {
        personName.setValue(DataGenerator.generateNewApplication().getName());
        phoneNumber.setValue(DataGenerator.generateNewApplication().getPhone());
        agreement.click();
        $$("button").find(exactText("Запланировать")).click();
    }
    public void sendRequestWithSpecificName() {
        cityName.setValue(DataGenerator.generateNewApplication().getCity());
        personName.setValue("Артём Семёнов");
        phoneNumber.setValue(DataGenerator.generateNewApplication().getPhone());
        agreement.click();
        $$("button").find(exactText("Запланировать")).click();
    }
}
