package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-jdbc.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 11, 0), "Завтрак", 500);
        service.create(newMeal, USER_ID);
        assertMatch(service.getAll(USER_ID), USER_MEAL_4, USER_MEAL_3, USER_MEAL_2, USER_MEAL_1, newMeal);
    }

    @Test
    public void get() throws Exception {
        Meal newMeal = service.get(USER_MEAL_ID_1, USER_ID);
        assertMatch(newMeal, USER_MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void getAlien() throws Exception {
        service.get(USER_MEAL_ID_1, ADMIN_ID);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, USER_MEAL_4, USER_MEAL_3, USER_MEAL_2, USER_MEAL_1);
    }

    @Test
    public void update() {
        Meal newMeal = ADMIN_MEAL_1;
        newMeal.setDateTime(LocalDateTime.now());
        newMeal.setDescription("Админский ланч");
        newMeal.setCalories(4444);
        service.update(newMeal, ADMIN_ID);
        assertMatch(service.get(ADMIN_MEAL_ID_1, ADMIN_ID), newMeal);
    }

    @Test(expected = NotFoundException.class)
    public void updateAlien() {
        service.update(USER_MEAL_1, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        service.delete(USER_MEAL_ID_1, USER_ID);
        service.get(USER_MEAL_ID_1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteAlien() throws Exception {
        service.delete(USER_MEAL_ID_1, ADMIN_ID);
    }

    @Test
    public void getBetweenDate() throws Exception {
        LocalDate from = LocalDate.of(2015, 5, 30);
        LocalDate to = LocalDate.of(2015, 5, 30);
        List<Meal> filteredList = service.getBetweenDates(from, to, USER_ID);
        assertMatch(filteredList, USER_MEAL_3, USER_MEAL_2, USER_MEAL_1);
    }

    @Test
    public void getBetweenDateTime() throws Exception {
        LocalDateTime from = LocalDateTime.of(2015, 5, 30, 12, 30);
        LocalDateTime to = LocalDateTime.of(2015, 5, 30, 19, 0);
        List<Meal> filteredList = service.getBetweenDateTimes(from, to, USER_ID);
        assertMatch(filteredList, USER_MEAL_2);
    }
}