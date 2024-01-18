package com.example.calculator;

import jakarta.servlet.annotation.WebServlet;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet

public class CalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jsonString = request.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);

        JSONObject jsonObject = new JSONObject(jsonString);

        int a = jsonObject.getInt("a");
        int b = jsonObject.getInt("b");
        String math = jsonObject.getString("math");

        int result = 0;
        switch (math) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b != 0) {
                    result = a / b;
                } else {
                    response.setStatus(400);
                    return;
                }
                break;
            default:
                response.setStatus(400);
                return;
        }

        JSONObject resultJson = new JSONObject();
        resultJson.put("result", result);

        response.setContentType("application/json;charset=utf-8");

        response.getWriter().write(resultJson.toString());
    }
}