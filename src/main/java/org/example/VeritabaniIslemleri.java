package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeritabaniIslemleri {

    final String JDBC_CONNECTION_STR = "jdbc:mysql://localhost:3306/rehber";
    final String USERNAME = "root";
    final String PASSWORD = "1234";

    public void addPerson(Kisiler kisiler) {

        String sql = "insert into kisiler (name, surname, city, phone) " +
                "values (?, ?, ?, ?) ";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, kisiler.getName());
            preparedStatement.setString(2, kisiler.getSurname());
            preparedStatement.setString(3, kisiler.getCity());
            preparedStatement.setString(4, kisiler.getPhone());

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " satır eklendi.");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deletePerson(Kisiler kisiler) {
        String query = "delete from kisiler where id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try
        {
            Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, kisiler.getId());

            int affectedRows = preparedStmt.executeUpdate();
            System.out.println(affectedRows + " kişi silindi.");

            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Bir sorun var!");
            System.err.println(e.getMessage());
        }
    }
    public void updatePerson(Kisiler kisiler){
        String sql = "update kisiler set name = ?, surname = ?, city = ?, phone = ? where id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, kisiler.getName());
            preparedStatement.setString(2, kisiler.getSurname());
            preparedStatement.setString(3, kisiler.getCity());
            preparedStatement.setInt(4, Integer.parseInt(kisiler.getPhone()));
            preparedStatement.setString(5, String.valueOf(kisiler.getId()));

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " kişi güncellendi.");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Kisiler> findAllPerson() {

        List<Kisiler> kisilerList = new ArrayList<>();
        String sql = "select * from kisiler";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String city = resultSet.getString("city");
                String phone = resultSet.getString("phone");

                Kisiler kisiler = new Kisiler();
                kisiler.setId(id);
                kisiler.setName(name);
                kisiler.setSurname(surname);
                kisiler.setCity(city);
                kisiler.setPhone(phone);
                kisilerList.add(kisiler);
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kisilerList;
    }

}
