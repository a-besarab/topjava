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
        service.create(newMeal, USER_ID_1);
        assertMatch(service.getAll(USER_ID_1), MEAL_2, newMeal, MEAL_1);
    }

    @Test
    public void get() throws Exception {
        Meal newMeal = service.get(MEAL_ID_1, USER_ID_1);
        assertMatch(newMeal, MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void getAlien() throws Exception {
        service.get(MEAL_ID_1, USER_ID_2);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID_1);
        assertMatch(all, MEAL_2, MEAL_1);
    }

    @Test
    public void update() {
        MEAL_1.setDateTime(LocalDateTime.now());
        MEAL_1.setDescription("des");
        MEAL_1.setCalories(4444);
        service.update(MEAL_1, USER_ID_1);
        assertMatch(service.get(MEAL_ID_1, USER_ID_1), MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void updateAlien() {
        service.update(MEAL_1, USER_ID_2);
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        service.delete(MEAL_ID_1, USER_ID_1);
        service.get(MEAL_ID_1, USER_ID_1);
    }

    @Test(expected = NotFoundException.class)
    public void deleteAlien() throws Exception {
        service.delete(MEAL_ID_1, USER_ID_2);
    }

    @Test
    public void getBetweenDate() throws Exception {
        LocalDate from = LocalDate.of(2015, 5, 30);
        LocalDate to = LocalDate.of(2015, 5, 30);
        List<Meal> filteredList = service.getBetweenDates(from, to, USER_ID_1);
        assertMatch(filteredList, MEAL_2, MEAL_1);
    }

    @Test
    public void getBetweenDateTime() throws Exception {
        LocalDateTime from = LocalDateTime.of(2015, 5, 30, 12, 30);
        LocalDateTime to = LocalDateTime.of(2015, 5, 30, 19, 0);
        List<Meal> filteredList = service.getBetweenDateTimes(from, to, USER_ID_1);
        assertMatch(filteredList, MEAL_2);
    }
}