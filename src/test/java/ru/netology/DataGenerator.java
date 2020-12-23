package ru.netology;

import com.github.javafaker.Faker;
import lombok.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Data
public class DataGenerator {
    private DataGenerator() {}

         public static OrderDeliveryCardInfo generateByUsers(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new OrderDeliveryCardInfo(generateByCity(),
                    faker.name().lastName() + " " + faker.name().firstName(),
                    faker.phoneNumber().phoneNumber()
            );
    }

        public static String generateByCity() {
            List<String> cities = Arrays.asList("Астрахань","Кострома","Краснодар","Москва","Петропавловск-Камчатский","Ростов-на-Дону","Салехард","Самара","Санкт-Петербург","Саранск","Саратов","Севастополь","Ставрополь","Сыктывкар","Чебоксары","Элиста","Южно-Сахалинск","Ярославль");
            Random random = new Random();
            String city = cities.get(random.nextInt(cities.size()));
            return city;
        }

        public static String generateByDate(int days) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.now().plusDays(days);
            return localDate.format(dateTimeFormatter);
        }

}


