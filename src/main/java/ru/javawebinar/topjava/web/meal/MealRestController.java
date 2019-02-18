package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
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

    @Autowired
    private MealService service;

    List<MealTo> getWithExcessByUserId() {
        return MealsUtil.getWithExcess(getAllByUserId(), SecurityUtil.authUserCaloriesPerDay());
    }

    List<MealTo> getWithExcessByUserIdFilteredByDateTime(User user, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        LocalDateTime startDateTime = LocalDateTime.of(startDate == null ? LocalDate.MIN : startDate, startTime == null ? LocalTime.MIN : startTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate == null ? LocalDate.MAX : endDate, endTime == null ? LocalTime.MAX : endTime);
        return MealsUtil.getFilteredWithExcess(getAllByUserId(), user.getCaloriesPerDay(), startDateTime, endDateTime);
    }

    public Meal create(Meal meal) {
        checkNew(meal);
        if (meal.getUserId().equals(SecurityUtil.authUserId())) {
            return service.create(meal);
        } else {
            throw new NotFoundException("error");
        }
    }

    public void delete(int id) {
        if (service.get(id).getUserId().equals(SecurityUtil.authUserId())) {
            service.delete(id);
        } else {
            throw new NotFoundException("error");
        }
    }

    public Meal get(int id) {
        if (service.get(id).getUserId().equals(SecurityUtil.authUserId())) {
            return service.get(id);
        } else {
            throw new NotFoundException("error");
        }
    }

    public Collection<Meal> getAllByUserId() {
        return service.getAllByUserId();
    }

    public int update(Meal meal, int id) {
        assureIdConsistent(meal, id);
        if (meal.getUserId().equals(SecurityUtil.authUserId())) {
            service.update(meal);
            return SecurityUtil.authUserId();
        } else {
            throw new NotFoundException("error");
        }
    }

    public Collection getAll() {
        return service.getAll();
    }
}