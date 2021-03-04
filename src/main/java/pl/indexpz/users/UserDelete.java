package pl.indexpz.users;

import pl.indexpz.utils.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/userDelete")
public class UserDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        getServletContext().getRequestDispatcher("/user/delete.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] parameterArray = request.getParameterValues("id");

        int id = Integer.parseInt(parameterArray[0]);

        UserDao userDao = new UserDao();
        request.setAttribute("id", userDao.read(id));

        System.out.println(userDao.read(id));

        userDao.delete(id);



    }
}
