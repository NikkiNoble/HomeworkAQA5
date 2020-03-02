package ru.netology.data;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
public class DataGenerator {
    private DataGenerator() {}

    public static ApplicationRegistration generateNewApplication() {
            Faker faker = new Faker(new Locale("ru"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
            LocalDate dt = LocalDate.now().plus(Period.ofDays(7));
            return new ApplicationRegistration(faker.address().city(),
                    formatter.format(dt),
                    faker.name().fullName(),
                    faker.phoneNumber().cellPhone()
            );
    }
    public static ApplicationRegistration generateNewErrorApplication() {
        Faker faker = new Faker(new Locale("en"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        LocalDate dt = LocalDate.now().plus(Period.ofDays(7));
        return new ApplicationRegistration(faker.address().city(),
                formatter.format(dt),
                faker.name().fullName(),
                faker.phoneNumber().cellPhone()
        );
    }
}
