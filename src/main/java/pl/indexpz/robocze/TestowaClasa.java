package pl.indexpz.robocze;

import pl.indexpz.utils.User;
import pl.indexpz.utils.UserDao;

public class TestowaClasa {
    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        User adam = new User("Adam", "123","adam@wp.pl" );
        userDao.create(adam);

        userDao.showTableOfUsers();

    }
}
