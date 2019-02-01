package ru.javawebinar.topjava.util;

import static ru.javawebinar.topjava.util.TimeUtil.*;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;


public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        getFilteredWithExceededViaStream(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> list = new ArrayList<>();
        Map<LocalDate, Integer> map = new HashMap<>();
        for (UserMeal userMeal : mealList) {
            // map.put(toLocalDate(userMeal), map.getOrDefault(toLocalDate(userMeal), 0) + userMeal.getCalories());
            map.merge(toLocalDate(userMeal), userMeal.getCalories(), (a, b) -> a + b);
        }
        for (UserMeal userMeal : mealList) {
            if (TimeUtil.isBetween(toLocalTime(userMeal), startTime, endTime)) {
                list.add(new UserMealWithExceed(toLocalDateTime(userMeal),
                        userMeal.getDescription(), userMeal.getCalories(),
                        map.get(toLocalDate(userMeal)) > caloriesPerDay));
            }
        }
        return list;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededViaStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> map = new HashMap<>();
        mealList.forEach(p -> map.merge(toLocalDate(p), p.getCalories(), (a, b) -> a + b));
        return mealList.stream()
                .filter(p -> TimeUtil.isBetween(toLocalTime(p), startTime, endTime))
                .map(p -> new UserMealWithExceed(toLocalDateTime(p), p.getDescription(), p.getCalories(),
                        map.get(toLocalDate(p)) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
