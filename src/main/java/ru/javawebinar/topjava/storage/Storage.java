package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

public interface Storage {
    void create(Meal meal);

    void read(String id);

    void update(Meal meal, String id);

    void delete(String id);
}
