package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID_1 = START_SEQ - 1;
    public static final int MEAL_ID_2 = START_SEQ - 2;
    public static final int MEAL_ID_3 = START_SEQ - 3;
    public static final int USER_ID_1 = START_SEQ;
    public static final int USER_ID_2 = START_SEQ + 1;

    public static Meal MEAL_1 = new Meal(MEAL_ID_1,
            LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static Meal MEAL_2 = new Meal(MEAL_ID_2,
            LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static Meal MEAL_3 = new Meal(MEAL_ID_3,
            LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}