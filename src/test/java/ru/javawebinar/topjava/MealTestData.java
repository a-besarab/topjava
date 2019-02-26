package ru.javawebinar.topjava;

import org.junit.Assert;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_MEAL_ID_1 = START_SEQ + 2;
    public static final int USER_MEAL_ID_2 = START_SEQ + 3;
    public static final int USER_MEAL_ID_3 = START_SEQ + 4;
    public static final int USER_MEAL_ID_4 = START_SEQ + 5;
    public static final int ADMIN_MEAL_ID_1 = START_SEQ + 6;
    public static final int ADMIN_MEAL_ID_2 = START_SEQ + 7;

    public static Meal USER_MEAL_1 = new Meal(USER_MEAL_ID_1,
            LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак1", 500);
    public static Meal USER_MEAL_2 = new Meal(USER_MEAL_ID_2,
            LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед1", 1000);
    public static Meal USER_MEAL_3 = new Meal(USER_MEAL_ID_3,
            LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин1", 500);
    public static Meal USER_MEAL_4 = new Meal(USER_MEAL_ID_4,
            LocalDateTime.of(2015, Month.MAY, 29, 10, 0), "Завтрак2", 500);
    public static Meal ADMIN_MEAL_1 = new Meal(ADMIN_MEAL_ID_1,
            LocalDateTime.of(2015, Month.MAY, 29, 13, 0), "Обед2", 1000);
    public static Meal ADMIN_MEAL_2 = new Meal(ADMIN_MEAL_ID_2,
            LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин2", 500);


    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}