package com.example.jupiter.servlet;

import com.example.jupiter.db.MySQLConnection;
import com.example.jupiter.db.MySQLException;
import com.example.jupiter.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(request.getReader(), User.class); //这个getReader()从body里读参数,是stream
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        boolean isUserAdded = false;
        MySQLConnection connection = null;
        try {
            connection = new MySQLConnection();
            user.setPassword(ServletUtil.encryptPassword(user.getUserId(), user.getPassword())); //加密
            isUserAdded = connection.addUser(user);
        } catch (MySQLException e) {
            throw new ServletException(e);
        } finally {
            connection.close();
        }

        if (!isUserAdded) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }


//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
}
