package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage implements Storage {
    Map<String, Meal> allMeal = new HashMap<>();

    @Override
    public void create(Meal meal) {
        allMeal.put(MealsUtil.getId(), meal);
    }

    @Override
    public void read(String id) {
        allMeal.get(id);
    }

    @Override
    public void update(Meal meal, String id) {
        delete(id);
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


