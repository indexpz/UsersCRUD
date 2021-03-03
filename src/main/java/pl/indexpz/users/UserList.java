package pl.indexpz.users;

import pl.indexpz.utils.DbUtil;
import pl.indexpz.utils.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/user/list")
public class UserList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDao userDao = new UserDao();
        request.setAttribute("users", userDao.findAll());



        try (Connection conn = DbUtil.getConnection()){

            System.out.println("Połączono z bazą danych...");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Nie udało się połączyć z bazą danych");
        }


    }
}
