package ru.appline;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {
    private Model model = Model.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        Map<String, Object> responseBody = new HashMap<>();

        int id = Integer.parseInt(request.getParameter("id"));

        if (id == 0) {
            Map<Integer, User> userList = model.getFromList();
            responseBody.put("message", "Доступные пользователи:");
            responseBody.put("users", userList);
        } else if (id > 0) {
            User user = model.getFromList().get(id);
            if (user != null) {
                responseBody.put("user", user);
            } else {
                responseBody.put("message", "Такого пользователя нет :(");
            }
        }

        response.getWriter().write(responseBody.toString());
    }
}
