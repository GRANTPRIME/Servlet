package ru.appline;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServletDelete extends HttpServlet {
    private Map<Integer, User> users = new HashMap<>();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDeleteRequest deleteRequest = UserUtils.parseJson(req);

        int userId = deleteRequest.getId();

        if (users.containsKey(userId)) {
            users.remove(userId);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(UserUtils.toJson(new UserDeleteResponse("Запись о пользователе успешно удалена.")));
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println(UserUtils.toJson(new UserDeleteResponse("Пользователь не найден.")));
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

    }

    private static class UserUtils {
        public static UserDeleteRequest parseJson(HttpServletRequest request) throws IOException {
            return null;
        }

        public static String toJson(UserDeleteResponse response) {
            return null;
        }
    }

    private static class UserDeleteRequest {
        private int id;

        public int getId() {
            return id;
        }

    }

    private static class UserDeleteResponse {
        private String message;

        public UserDeleteResponse(String message) {
            this.message = message;
        }

    }
}
