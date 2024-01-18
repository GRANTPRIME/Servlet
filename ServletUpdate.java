package ru.appline;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServletUpdate extends HttpServlet {

    private Map<Integer, User> users = new HashMap<>();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserUpdateRequest updateRequest = UserUtils.parseJson(req);

        int userId = updateRequest.getId();

        if (users.containsKey(userId)) {
            User user = users.get(userId);

            user.setName(updateRequest.getName());
            user.setSurname(updateRequest.getSurname());
            user.setSalary(updateRequest.getSalary());

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(UserUtils.toJson(new UserUpdateResponse("Запись о пользователе успешно обновлена.")));
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println(UserUtils.toJson(new UserUpdateResponse("Пользователь не найден.")));
        }
    }

    private static class User {
        private String name;
        private String surname;
        private double salary;

        public User(String name, String surname, double salary) {
            this.name = name;
            this.surname = surname;
            this.salary = salary;
        }

        public void setName(Object name) {
        }

        public void setSurname(Object surname) {
        }

        public void setSalary(Object salary) {
        }
    }

    private static class UserUtils {
        public static UserUpdateRequest parseJson(HttpServletRequest req) throws IOException {
            return null;
        }

        public static String toJson(UserUpdateResponse response) {
            // Реализация метода преобразования объекта в JSON
            return null;
        }
    }

    private static class UserUpdateRequest {
        private int id;
        private String name;
        private String surname;
        private double salary;

        public int getId() {
            return 0;
        }

        public Object getName() {
            return null;
        }

        public Object getSalary() {
            return null;
        }

        public Object getSurname() {
            return null;
        }
    }

    private static class UserUpdateResponse {
        private String message;

        public UserUpdateResponse(String message) {
            this.message = message;
        }

    }
}
