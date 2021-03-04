package pl.indexpz.users;

import pl.indexpz.utils.User;
import pl.indexpz.utils.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/userAdd")
public class UserAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/user/add.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User addUser = new User(name, password, email);
        UserDao userDao = new UserDao();
        userDao.create(addUser);

        getServletContext().getRequestDispatcher("/user/list.jsp").forward(request,response);

    }
}
