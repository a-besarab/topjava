package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {

    Object get(String id);

    Object update(Meal meal, String id);

    void delete(String id);

    List<Object> getAll();
}
