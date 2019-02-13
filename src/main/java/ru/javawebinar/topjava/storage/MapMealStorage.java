package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.TestDataMeals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MapMealStorage implements Storage {
    private static Map<String, Meal> allMeal;

    public MapMealStorage() {
        allMeal = new ConcurrentHashMap<>();
        TestDataMeals.getTestList().forEach(m -> update(m, m.getId()));
    }

    @Override
    public Meal get(String id) {
        return allMeal.get(id);
    }

    @Override
    public Meal update(Meal meal, String id) {
        if (isExist(id)) {
            delete(id);
        }
        allMeal.put(id, meal);
        return meal;
    }

    @Override
    public void delete(String id) {
        allMeal.remove(id);
    }

    public List<Object> getAll() {
        return new ArrayList<>(allMeal.values());
    }

    private boolean isExist(String id) {
        return get(id) != null;
    }

    public static String getId() {
        return UUID.randomUUID().toString();
    }
}


