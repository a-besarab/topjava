package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {

    private final MealService service;
    private final int caloriesPerDay;
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public MealRestController(MealService service) {

        this.service = service;
        this.caloriesPerDay = SecurityUtil.authUserCaloriesPerDay();
    }

    List<MealTo> getWithExcess() {
        return MealsUtil.getWithExcess(getAll(SecurityUtil.authUserId()), caloriesPerDay);
    }

    List<MealTo> getWithExcessFilteredByDateTime(int userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        List<Meal> betweenDays = service.getBetweenDays(startDate == null ? LocalDate.MIN : startDate, endDate == null ? LocalDate.MAX : endDate, userId);
        return MealsUtil.getFilteredWithExcess(betweenDays, SecurityUtil.authUserCaloriesPerDay(),
                startTime == null ? LocalTime.MIN : startTime, endTime == null ? LocalTime.MAX : endTime);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, SecurityUtil.authUserId());
    }

    public void delete(int id) {
        service.delete(id, SecurityUtil.authUserId());
    }

    public Meal get(int id) {
        return service.get(id, SecurityUtil.authUserId());
    }

    public void update(Meal meal, int id) {
        meal.setUserId(SecurityUtil.authUserId());
        assureIdConsistent(meal, id);
        service.update(meal, SecurityUtil.authUserId());
    }

    public List<Meal> getAll(int userId) {
        return service.getAll(userId);
    }
}