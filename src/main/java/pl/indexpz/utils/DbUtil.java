package pl.indexpz.utils;

import java.sql.*;

public class DbUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "coderslab";
    private static final String DB_PARAMS = "?characterEncoding=utf8";
    //    private static final String DB_PARAMS = "?characterEncoding=utf8&useSSL=false[&nazwa=wartość]*";


    public static Connection connect() throws SQLException {
        return connect(null);
    }

    public static Connection connect(String dbName) throws SQLException {
        String sql = DB_URL + (dbName != null ? "/" + dbName : "") + DB_PARAMS;
        Connection connection = DriverManager.getConnection(sql, DB_USER, DB_PASSWORD);
        return connection;
    }


    public static void insert(Connection conn, String query, String... params) {
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void printData(Connection conn, String query, String... columnNames) throws SQLException {

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                for (String param : columnNames) {
                    System.out.println(resultSet.getString(param));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static final String DELETE_QUERY = "DELETE FROM tableName where id = ?";

    public static void remove(Connection conn, String tableName, int id) {
        try (PreparedStatement statement = conn.prepareStatement(DELETE_QUERY.replace("tableName", tableName));) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
