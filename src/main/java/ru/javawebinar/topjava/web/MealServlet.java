package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MapMealStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private Storage storage = new MapMealStorage();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        storage.update(new Meal(TimeUtil.getLocalDateTime(req.getParameter("dateTime")),
                req.getParameter("description"), Integer.valueOf(req.getParameter("calories")), id), id);
        resp.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String action = request.getParameter("action");
        String mealId = request.getParameter("mealId");
        if (action == null) {
            request.setAttribute("meal", MealsUtil.getFilteredWithExcess(storage.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
            return;
        }
        switch (action) {
            case "delete":
                storage.delete(mealId);
                response.sendRedirect("meals");
                break;
            case "add":
                request.setAttribute("meal", Meal.EMPTY);
                request.getRequestDispatcher("/edit.jsp").forward(request, response);
                break;
            case "edit":
                request.setAttribute("meal", storage.get(mealId));
                request.getRequestDispatcher("/edit.jsp").forward(request, response);
                break;
        }
    }
}
