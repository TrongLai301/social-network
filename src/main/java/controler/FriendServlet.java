package controler;

import model.User;
import service.RelationshipDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet (name = "FriendServlet", value = "/friend")
public class FriendServlet extends HttpServlet {
    RelationshipDAO relationshipDAO;

    @Override
    public void init() throws ServletException {
        relationshipDAO = new RelationshipDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionGet = req.getParameter("actionGet");
        if (actionGet == null) {
            actionGet = "";
        }
        try {
            switch (actionGet) {
                case "sendFriendRequest":
                    sendAddFriendRequest(req,resp);
                    break;
                case "acceptRequest" :
                    acceptRequest(req,resp);
                    break;
                case "deleteRelationship" :
                    deleteRelationship(req,resp);
                    break;
                default:
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteRelationship(HttpServletRequest req, HttpServletResponse resp) {
        int firstUserId = (int) req.getSession().getAttribute("idAccount");
        int secondUserId = Integer.parseInt(req.getParameter("id"));

        relationshipDAO.deleteRelationshipOf(firstUserId,secondUserId);
        try {
            req.getRequestDispatcher("/user?actionGet=showUserProfile&id=" + secondUserId).forward(req,resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void acceptRequest(HttpServletRequest req, HttpServletResponse resp) {
        int receiverId = (int) req.getSession().getAttribute("idAccount");
        int senderId = Integer.parseInt(req.getParameter("id"));

        relationshipDAO.addFriend(senderId,receiverId);
        try {
            req.getRequestDispatcher("/user?actionGet=showUserProfile&id=" + senderId).forward(req,resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendAddFriendRequest(HttpServletRequest req, HttpServletResponse resp) {
        int senderId = (int) req.getSession().getAttribute("idAccount");
        int receiverId = Integer.parseInt(req.getParameter("id"));
        sendRequest(senderId,receiverId);
        try {
            req.getRequestDispatcher("/user?actionGet=showUserProfile&id=" + receiverId ).forward(req,resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionPost = req.getParameter("actionPost");
        if (actionPost == null) {
            actionPost = "";
        }
        try {
            switch (actionPost) {
                default:
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendRequest(int senderId, int receiverId){
        relationshipDAO.addRequestAddFriend(senderId,receiverId);
    }

}