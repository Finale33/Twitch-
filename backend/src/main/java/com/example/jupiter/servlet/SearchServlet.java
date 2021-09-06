package com.example.jupiter.servlet;

import com.example.jupiter.external.TwitchClient;
import com.example.jupiter.external.TwitchException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//我们不支持doPost 因为我们无法改动twitch API的东西

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gameId = request.getParameter("game_id");
        if (gameId == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); //其实就是400
            return;
        }

        TwitchClient client = new TwitchClient();
        try {
            ServletUtil.writeItemMap(response,client.searchItems(gameId));
        } catch (TwitchException e) {
            throw new ServletException(e);
        }
    }
}
