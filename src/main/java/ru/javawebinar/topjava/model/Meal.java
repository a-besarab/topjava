package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class Meal {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final String id;

    public static final Meal EMPTY = new Meal(LocalDateTime.MIN, "", 0);

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(dateTime, description, calories, null);
    }

    public Meal(LocalDateTime dateTime, String description, int calories, String id) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.id = id == null ? UUID.randomUUID().toString() : id;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
