package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeUtil {
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    static LocalDate toLocalDate(UserMeal userMeal) {
        return LocalDate.of(userMeal.getDateTime().getYear(), userMeal.getDateTime().getMonth(), userMeal.getDateTime().getDayOfMonth());
    }

    static LocalTime toLocalTime(UserMeal userMeal) {
        return LocalTime.of(userMeal.getDateTime().getHour(), userMeal.getDateTime().getMinute());
    }

    static LocalDateTime toLocalDateTime(UserMeal userMeal) {
        return LocalDateTime.of(toLocalDate(userMeal), toLocalTime(userMeal));
    }
}
