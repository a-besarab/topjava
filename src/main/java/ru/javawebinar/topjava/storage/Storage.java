package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {

    Meal get(String id);

    void update(Meal meal, String id);

    void delete(String id);

    List<Meal> getAll();
}
