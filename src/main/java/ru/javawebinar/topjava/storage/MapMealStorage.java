package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.TestDataMeals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapMealStorage implements Storage {
    private static Map<String, Meal> allMeal = new ConcurrentHashMap<>();

    static {
        for (Meal meal : TestDataMeals.getTestList()) {
            allMeal.put(meal.getId(), meal);
        }
    }

    @Override
    public Meal get(String id) {
        return allMeal.get(id);
    }

    @Override
    public void update(Meal meal, String id) {
        allMeal.put(id, meal);
    }

    @Override
    public void delete(String id) {
        allMeal.remove(id);
    }

    public List<Meal> getAll() {
        return new ArrayList<>(allMeal.values());
    }
}


