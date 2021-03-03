package pl.indexpz.utils;


import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UserDao extends User {
    /**
     * CRUD
     */
    private static final String CREATE_USER_QUERY = "INSERT  INTO  users (name, password, email) VALUES (?,?,?);";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id =  ? ;";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET name = ?, password = ?, email= ? WHERE id = ?;";
    private static final String REMOVE_USER_QUERY = "DELETE FROM users WHERE id = ?;";

    private static final String All_QUERY = "SELECT * FROM users;";

    public UserDao() {
    }

    public UserDao(String name, String password, String email) {
        super(name, password, email);
    }


    public User create(User user) {
        int tempId = 0;
        if (!isUserExist(user)) {
            try (Connection db_manager_conn = DbUtil.getConnection()) {
                String name = user.getName();
                String password = hashPassword(user.getPassword());
                String email = user.getEmail();
                PreparedStatement preparedStatement = db_manager_conn.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    tempId = id;
                }


            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Nie udało połączyć się z bazą danych. " + ex);
            }
            System.out.println("Dodano użytkownika " + user.getName() + " do bazy danych o Id: " + tempId);
        } else {
            System.out.println("Użytkownik znajduje się już w bazie danych.");
        }

        return user;
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User read(int userId) {
        try (Connection db_manager_conn = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = db_manager_conn.prepareStatement(READ_USER_QUERY);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User tempUser = new User();
                tempUser.setId(resultSet.getInt("id"));
                tempUser.setName(resultSet.getString("name"));
                tempUser.setPassword(resultSet.getString("password"));
                tempUser.setEmail(resultSet.getString("email"));
                return tempUser;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Nie udało połączyć się z bazą danych. " + ex);
        }
        return null;
    }

    public void update(User user) {
        int tempId = 0;
        if (!isIdExist(user.getId()) || !isUserExist(user)) {
            try (Connection db_manager_conn = DbUtil.getConnection()) {
                PreparedStatement preparedStatement = db_manager_conn.prepareStatement(UPDATE_USER_QUERY);
                int id = user.getId();
                String name = user.getName();
                String password = this.hashPassword(user.getPassword());
                String email = user.getEmail();
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);
                preparedStatement.setInt(4, id);
                preparedStatement.executeUpdate();
                tempId = id;
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Nie udało połączyć się z bazą danych. " + ex);
            }
        } else {
            System.out.println("Nie udało się nadpisać danych, brak użytkownika w bazie danych o id: " + tempId);
        }
    }

    public void delete(int userId) {
        if (!isIdExist(userId)) {
            try (Connection db_manager_conn = DbUtil.getConnection()) {
                PreparedStatement preparedStatement = db_manager_conn.prepareStatement(REMOVE_USER_QUERY);
                preparedStatement.setInt(1, userId);
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Nie udało połączyć się z bazą danych. " + ex);
            }
        } else {
            System.out.println("Nie udało się usunąć, brak użytkownika w bazie danych o id: " + userId);
        }
    }


    public User[] findAll() {
        User[] tempUsers = new User[0];

        try (Connection db_manager_conn = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = db_manager_conn.prepareStatement(All_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User tempUser = new User();
                tempUser.setId(resultSet.getInt("id"));
                tempUser.setName(resultSet.getString("name"));
                tempUser.setPassword(resultSet.getString("password"));
                tempUser.setEmail(resultSet.getString("email"));
                tempUsers = addToArray(tempUser, tempUsers);
            }
            return tempUsers;

        } catch (SQLException ex) {
            ex.printStackTrace();
//            throw new RuntimeException("Nie udało połączyć się z bazą danych. " + ex);
            return null;
        }

    }


    private User[] addToArray(User user, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = user;
        return tmpUsers;
    }


    public int getUserIdFromDB(User user) {
        int id = 0;
        try (Connection db_manager_conn = DbUtil.getConnection()) {
//            String sql = "SELECT id FROM users where name LIKE  + '"+ user.getEmail() + "';";
            String sql = "SELECT id FROM users where name LIKE  + '" + user.getEmail() + "';";
            PreparedStatement preparedStatement = db_manager_conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
//            PreparedStatement preparedStatement = db_manager_conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
//                id = resultSet.getLong(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Nie udało połączyć się z bazą danych. " + ex);
        }
        return id;
    }

    public static void showTableOfUsers() {
        try (Connection db_manager_conn = DbUtil.getConnection()) {
            DbUtil.printData(db_manager_conn, All_QUERY, "id", "name", "password", "email");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Nie udało połączyć się z bazą danych. " + ex);
        }
    }

    public boolean isIdExist(int id) {
        boolean result = false;
        try (Connection db_manager_conn = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = db_manager_conn.prepareStatement(All_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idToCheck = resultSet.getInt("id");

                if (idToCheck == id) {
                    result = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Nie udało połączyć się z bazą danych. " + ex);
        }
        return result;
    }

    public boolean isUserExist(User user) {
        boolean result = false;
        try (Connection db_manager_conn = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = db_manager_conn.prepareStatement(All_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String emailToCheck = resultSet.getString("email");

                if (emailToCheck.equalsIgnoreCase(user.getEmail())) {
                    result = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Nie udało połączyć się z bazą danych. " + ex);
        }
        return result;
    }


}
