package controler;

import com.google.gson.Gson;
import model.Admin;
import model.User;
import org.json.simple.JSONObject;
import service.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Class admin servlet dùng để hiển thị và sử lý logic liên quan đến trang admin ví dụ như block or view

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {

    private String admin = "admin";
    private String login = "user";

    private String userPerWeekAdmin = "userPerWeekAdmin";
    private String userPerMonthAdmin = "userPerMonthAdmin";
    private String userPerYearAdmin = "userPerYearAdmin";

    private String userPerWeekLogin = "userPerWeekLogin";
    private String userPerMonthLogin = "userPerMonthLogin";
    private String userPerYearLogin = "userPerYearLogin";
    private UserDAOImpl userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionGet = req.getParameter("action");
        if (actionGet == null) {
            actionGet = "";
        }
        try {
            switch (actionGet) {
                case "block":
                    blockUserById(req, resp);
                    break;
                // Đoạn code dưới là để chuyển hươớng tới trang home
                case "Week":
                    chartWeekUser(req, resp);
                    break;
                case "Month":
                    chartMonthUser(req, resp);
                    break;
                case "Year":
                    chartYearUser(req, resp);
                    break;
                default:
                    directToHome(req, resp);
                    break;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    private void chartWeekUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Admin> adminWeek = userDAO.userAccessAdmin(userPerWeekAdmin);
        List<Integer> userAccessPerWeek = userPerWeekAccess(adminWeek);
        dataSynthesisPerWeek(userAccessPerWeek, resp, req, admin);

        List<User> userWeek = userDAO.userAccessLogin(userPerWeekLogin);
        List<Integer> userLoginPerWeek = userPerWeekLogin(userWeek);
        dataSynthesisPerWeek(userLoginPerWeek, resp, req, login);
        req.getRequestDispatcher("/admin/chartWeek.jsp").forward(req, resp);
    }

    private void chartMonthUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Admin> adminMonth = userDAO.userAccessAdmin(userPerMonthAdmin);
        List<Integer> userAccessPerMonth = userPerMonthAccess(adminMonth);
        dataSynthesisPerMonth(userAccessPerMonth, req, resp, admin);

        List<User> loginMonth = userDAO.userAccessLogin(userPerMonthLogin);
        List<Integer> userLoginPerMonth = userPerMonthLogin(loginMonth);
        dataSynthesisPerMonth(userLoginPerMonth, req, resp, login);
        req.getRequestDispatcher("/admin/chartMonth.jsp").forward(req, resp);
    }

    private void chartYearUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Admin> adminYear = userDAO.userAccessAdmin(userPerYearAdmin);
        List<Integer> userAccessPerYear = userPerYearAccess(adminYear);
        dataSynthesisPerYear(userAccessPerYear, req, resp, admin);

        List<User> loginMonth = userDAO.userAccessLogin(userPerMonthLogin);
        List<Integer> userLoginPerYear = userPerYearLogin(loginMonth);
        dataSynthesisPerYear(userLoginPerYear, req, resp, login);
        req.getRequestDispatcher("/admin/chartYear.jsp").forward(req, resp);
    }


    public void blockUserById(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.getUserById(id);
        if (user.getPermission().equals("admin")) {
            request.setAttribute("message", "khong the xoa doi tuong admin");
            List<User> defaultListUser = userDAO.getAllUser();
            request.setAttribute("defaultListUser", defaultListUser);
            request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
        } else {
            if (user.getStatus() == null) {
                userDAO.addBlockUser(id);
                request.setAttribute("messageBlock", "thanh cong");
                List<User> defaultListUser = userDAO.getAllUser();
                request.setAttribute("defaultListUser", defaultListUser);
                request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
            } else {
                if (user.getStatus().equals("working")) {
                    userDAO.addBlockUser(id);
                    List<User> defaultListUser = userDAO.getAllUser();
                    request.setAttribute("messageBlock", "thanh cong");
                    request.setAttribute("defaultListUser", defaultListUser);
                    request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
                } else {
                    userDAO.removeBlockUser(id);
                    List<User> defaultListUser = userDAO.getAllUser();
                    request.setAttribute("messageRemove", "thanh cong");
                    request.setAttribute("defaultListUser", defaultListUser);
                    request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
                }
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionPost = req.getParameter("actionPost");
        if (actionPost == null) {
            actionPost = "";
        }
        switch (actionPost) {
            default:
        }
    }

    public void directToHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> defaultListUser = userDAO.getAllUser();
        req.setAttribute("defaultListUser", defaultListUser);
        req.getRequestDispatcher("/admin/home.jsp").forward(req, resp);
    }

    //    Week
    public void dataSynthesisPerWeek(List<Integer> week, HttpServletResponse response, HttpServletRequest request, String select) throws ServletException, IOException {
        List<Integer> listWeek = new ArrayList<>();
        int totalMonday = 0;
        int totalTuesday = 0;
        int totalWednesday = 0;
        int totalThursday = 0;
        int totalFriday = 0;
        int totalSaturday = 0;
        int totalSunday = 0;
        for (int weekS : week
        ) {
            switch (weekS) {
                case 2:
                    totalMonday++;
                    break;
                case 3:
                    totalTuesday++;
                    break;
                case 4:
                    totalWednesday++;
                    break;
                case 5:
                    totalThursday++;
                    break;
                case 6:
                    totalFriday++;
                    break;
                case 7:
                    totalSaturday++;
                    break;
                case 8:
                    totalSunday++;
                    break;
            }
        }
        listWeek.add(totalMonday);
        listWeek.add(totalTuesday);
        listWeek.add(totalWednesday);
        listWeek.add(totalThursday);
        listWeek.add(totalFriday);
        listWeek.add(totalSaturday);
        listWeek.add(totalSunday);
        if (select.equals("admin")) {
            request.setAttribute("listWeekAccess", listWeek);
        } else if (select.equals("user")) {
            request.setAttribute("listWeekLogin", listWeek);
        }
    }

    public List<Integer> userPerWeekAccess(List<Admin> admins) {
        SimpleDateFormat weekCheck = new SimpleDateFormat("EEE");
        List<Integer> numWeek = new ArrayList<>();
        for (Admin a : admins) {
            int weekNumber = changeWeekToNumberNow(weekCheck, a.getDate());
            numWeek.add(weekNumber);
        }
        return numWeek;
    }

    public List<Integer> userPerWeekLogin(List<User> users) {
        SimpleDateFormat weekCheck = new SimpleDateFormat("EEE");
        List<Integer> numWeek = new ArrayList<>();
        for (User a : users) {
            int weekNumber = changeWeekToNumberNow(weekCheck, a.getDateCreate());
            numWeek.add(weekNumber);
        }
        return numWeek;
    }

    public int changeWeekToNumberNow(SimpleDateFormat weekTest, Date date) {
        String weekK = weekTest.format(date);
        int numberChage = 0;
        switch (weekK) {
            case "Th 2":
                numberChage = 2;
                break;
            case "Th 3":
                numberChage = 3;
                break;
            case "Th 4":
                numberChage = 4;
                break;
            case "Th 5":
                numberChage = 5;
                break;
            case "Th 6":
                numberChage = 6;
                break;
            case "Th 7":
                numberChage = 7;
                break;
            case "CN":
                numberChage = 8;
                break;
        }
        return numberChage;
    }

    // Week
    // Month
    public List<Integer> userPerMonthAccess(List<Admin> admins) {
        SimpleDateFormat monthCheck = new SimpleDateFormat("dd");
        List<Integer> numWeek = new ArrayList<>();
        for (Admin a : admins) {
            int month = Integer.parseInt(monthCheck.format(a.getDate()));
            numWeek.add(month);
        }
        return numWeek;
    }

    public List<Integer> userPerMonthLogin(List<User> users) {
        SimpleDateFormat monthCheck = new SimpleDateFormat("dd");
        List<Integer> numWeek = new ArrayList<>();
        for (User a : users) {
            int month = Integer.parseInt(monthCheck.format(a.getDateCreate()));
            numWeek.add(month);
        }
        return numWeek;
    }

    public void dataSynthesisPerMonth(List<Integer> listMonth, HttpServletRequest request, HttpServletResponse response, String select) {
        List<Integer> listMontUser = new ArrayList<>();
        int day1 = 0;
        int day2 = 0;
        int day3 = 0;
        int day4 = 0;
        int day5 = 0;
        int day6 = 0;
        int day7 = 0;
        int day8 = 0;
        int day9 = 0;
        int day10 = 0;
        int day11 = 0;
        int day12 = 0;
        int day13 = 0;
        int day14 = 0;
        int day15 = 0;
        int day16 = 0;
        int day17 = 0;
        int day18 = 0;
        int day19 = 0;
        int day20 = 0;
        int day21 = 0;
        int day22 = 0;
        int day23 = 0;
        int day24 = 0;
        int day25 = 0;
        int day26 = 0;
        int day27 = 0;
        int day28 = 0;
        int day29 = 0;
        int day30 = 0;
        int day31 = 0;
        for (int month : listMonth
        ) {
            switch (month) {
                case 1:
                    day1++;
                    break;
                case 2:
                    day2++;
                    break;
                case 3:
                    day3++;
                    break;
                case 4:
                    day4++;
                    break;
                case 5:
                    day5++;
                    break;
                case 6:
                    day6++;
                    break;
                case 7:
                    day7++;
                    break;
                case 8:
                    day8++;
                    break;
                case 9:
                    day9++;
                    break;
                case 10:
                    day10++;
                    break;
                case 11:
                    day11++;
                    break;
                case 12:
                    day12++;
                    break;
                case 13:
                    day13++;
                    break;
                case 14:
                    day14++;
                    break;
                case 15:
                    day15++;
                    break;
                case 16:
                    day16++;
                    break;
                case 17:
                    day17++;
                    break;
                case 18:
                    day18++;
                    break;
                case 19:
                    day19++;
                    break;
                case 20:
                    day20++;
                    break;
                case 21:
                    day21++;
                    break;
                case 22:
                    day22++;
                    break;
                case 23:
                    day23++;
                    break;
                case 24:
                    day24++;
                    break;
                case 25:
                    day25++;
                    break;
                case 26:
                    day26++;
                    break;
                case 27:
                    day27++;
                    break;
                case 28:
                    day28++;
                    break;
                case 29:
                    day29++;
                    break;
                case 30:
                    day30++;
                    break;
                case 31:
                    day31++;
                    break;
                default:
                    break;
            }
        }
        listMontUser.add(day1);
        listMontUser.add(day2);
        listMontUser.add(day3);
        listMontUser.add(day4);
        listMontUser.add(day5);
        listMontUser.add(day6);
        listMontUser.add(day7);
        listMontUser.add(day8);
        listMontUser.add(day9);
        listMontUser.add(day10);
        listMontUser.add(day11);
        listMontUser.add(day12);
        listMontUser.add(day13);
        listMontUser.add(day14);
        listMontUser.add(day15);
        listMontUser.add(day16);
        listMontUser.add(day17);
        listMontUser.add(day18);
        listMontUser.add(day19);
        listMontUser.add(day20);
        listMontUser.add(day21);
        listMontUser.add(day22);
        listMontUser.add(day23);
        listMontUser.add(day24);
        listMontUser.add(day25);
        listMontUser.add(day26);
        listMontUser.add(day27);
        listMontUser.add(day28);
        listMontUser.add(day29);
        listMontUser.add(day30);
        listMontUser.add(day31);
        if (select.equals("admin")) {
            request.setAttribute("listMonthAccess", listMontUser);
        } else if (select.equals("user")) {
            request.setAttribute("listMonthLogin", listMontUser);
        }
    }

    //Month
//Year
    public List<Integer> userPerYearAccess(List<Admin> admins) {
        SimpleDateFormat yearCheck = new SimpleDateFormat("MM");
        List<Integer> numYear = new ArrayList<>();
        for (Admin a : admins) {
            int year = Integer.parseInt(yearCheck.format(a.getDate()));
            numYear.add(year);
            System.out.println(year);
        }
        return numYear;
    }

    public List<Integer> userPerYearLogin(List<User> users) {
        SimpleDateFormat monthCheck = new SimpleDateFormat("MM");
        List<Integer> numYear = new ArrayList<>();
        for (User a : users) {
            int year = Integer.parseInt(monthCheck.format(a.getDateCreate()));
            numYear.add(year);
//            System.out.println(year);
        }
        return numYear;
    }

    public void dataSynthesisPerYear(List<Integer> listYear, HttpServletRequest request, HttpServletResponse response, String select) {
        List<Integer> listYearUser = new ArrayList<>();
        int month1 = 0;
        int month2 = 0;
        int month3 = 0;
        int month4 = 0;
        int month5 = 0;
        int month6 = 0;
        int month7 = 0;
        int month8 = 0;
        int month9 = 0;
        int month10 = 0;
        int month11 = 0;
        int month12 = 0;
        for (int year : listYear
        ) {
            switch (year) {
                case 1:
                    month1++;
                    break;
                case 2:
                    month2++;
                    break;
                case 3:
                    month3++;
                    break;
                case 4:
                    month4++;
                    break;
                case 5:
                    month5++;
                    break;
                case 6:
                    month6++;
                    break;
                case 7:
                    month7++;
                    break;
                case 8:
                    month8++;
                    break;
                case 9:
                    month9++;
                    break;
                case 10:
                    month10++;
                    break;
                case 11:
                    month11++;
                    break;
                case 12:
                    month12++;
                    break;
                default:
                    break;
            }
        }
        listYearUser.add(month1);
        listYearUser.add(month2);
        listYearUser.add(month3);
        listYearUser.add(month4);
        listYearUser.add(month5);
        listYearUser.add(month6);
        listYearUser.add(month7);
        listYearUser.add(month8);
        listYearUser.add(month9);
        listYearUser.add(month10);
        listYearUser.add(month11);
        listYearUser.add(month12);
        if (select.equals("admin")) {
            request.setAttribute("listYearAccess", listYearUser);
        } else if (select.equals("user")) {
            request.setAttribute("listYearLogin", listYearUser);
        }
    }
//Year
}




