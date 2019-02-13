package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealStorage {

    Meal get(String id);

    Meal update(Meal meal, String id);

    void delete(String id);

    List<Meal> getAll();
}
