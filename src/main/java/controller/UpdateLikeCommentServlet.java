package controller;

import org.json.simple.JSONObject;
import service.UserDAOImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet(name = "UpdateLikeCommentServlet", value = "/updateLikeCommentServlet")
public class UpdateLikeCommentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAOImpl userDAO = null;
        int commentLikesCount = 0;
        int idComment =Integer.parseInt(request.getParameter("idComment"));
        boolean checkLikedComment = Boolean.parseBoolean(request.getParameter("checkLikedComment"));
        if(checkLikedComment){
            userDAO.decreaseCommentLikesInDatabase(idComment);
            commentLikesCount = userDAO.getCommentLikeCount(idComment);
        }else{

        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("likeCmtCount", commentLikesCount);
        checkLikedComment = !checkLikedComment;
        jsonResponse.put("checkLikedComment", checkLikedComment);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
