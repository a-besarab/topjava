package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {

    private final MealService service;
    private final Integer userId;
    private final int caloriesPerDay;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
        this.userId = SecurityUtil.authUserId();
        this.caloriesPerDay = SecurityUtil.authUserCaloriesPerDay();
    }

    List<MealTo> getWithExcessByUserId() {
        return MealsUtil.getWithExcess(getAll(userId), caloriesPerDay);
    }

    List<MealTo> getWithExcessByUserIdFilteredByDateTime(User user, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        LocalDateTime startDateTime = LocalDateTime.of(startDate == null ? LocalDate.MIN : startDate, startTime == null ? LocalTime.MIN : startTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate == null ? LocalDate.MAX : endDate, endTime == null ? LocalTime.MAX : endTime);
        return MealsUtil.getFilteredWithExcess(getAll(userId), user.getCaloriesPerDay(), startDateTime, endDateTime);
    }

    public Meal create(Meal meal) {
        checkNew(meal);
        meal.setUserId(userId);
        return service.create(meal);
    }

    public void delete(int id) {
        service.delete(id, userId);
    }

    public Meal get(int id) {
        return service.get(id, userId);
    }

    public void update(Meal meal, int id) {
        assureIdConsistent(meal, id);
        service.update(meal);
    }

    public Collection<Meal> getAll(int userId) {
        return service.getAll(userId);
    }
}