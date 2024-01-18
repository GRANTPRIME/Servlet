package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.json.JsonObject;
import jakarta.servlet.ServletResponseWrapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {

    private AtomicInteger counter = new AtomicInteger(5);
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=utf-8");
//        request.setCharacterEncoding("UTF-8");
//        PrintWriter pw = response.getWriter();
//
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        double salary = Double.parseDouble(request.getParameter("salary"));
//
//        User user = new User(name, surname, salary);
//        model.add(user, counter.getAndIncrement());
//
//        pw.print("<html>" +
//                "<h3>Пользователь " + name + " " + surname + " с зарплатой= " + salary +  " успешно создан! :)</h3>" +
//                "<a href=\"addUser.html\">Создать нового пользователя</a><br/>" +
//                "<a href=\"index.jsp\">Домой</a>" +
//                "</html>");
//    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder jb = new StringBuilder();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);

        request.setCharacterEncoding("UTF-8");

        String name = jobj.get("name").toString();
        String surname = jobj.get("surname").toString();
        double salary = jobj.get("salary").getAsDouble();


        User user = new User(name, surname, salary);
        model.add(user, counter.getAndIncrement());

        response.setContentType("application/json;charset=utf-8");
        ServletResponseWrapper responce = null;
        PrintWriter pw = responce.getWriter();

        pw.print(gson.toJson(model.getFromList()));

    }


}



