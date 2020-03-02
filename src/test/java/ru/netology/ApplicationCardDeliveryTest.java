package ru.netology;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.CardPage;

public class ApplicationCardDeliveryTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    void shouldSubmitRequestAndChangeBookedDate() {
        CardPage cardApplication = new CardPage();
        cardApplication.openPage();
        cardApplication.sendRequestWithAllFieldsFilledIn();
        cardApplication.findSuccessElement();
        cardApplication.replanWithNewDate();
    }

    @Test
    void shouldNotSubmitRequest(){
        CardPage cardApplication = new CardPage();
        cardApplication.openPage();
        cardApplication.sendRequestWithEmptyFields();
        cardApplication.findError();
    }

    @Test
    void shouldNotSubmitRequestWithWrongData(){
        CardPage cardApplication = new CardPage();
        cardApplication.openPage();
        cardApplication.sendRequestWithWrongData();
        cardApplication.findErrorElement();
    }

    @Test
    void shouldSubmitRequest() {
        CardPage cardApplication = new CardPage();
        cardApplication.openPage();
        cardApplication.sendRequestWithSpecificName();
        cardApplication.findSuccessElement();
    }

}
