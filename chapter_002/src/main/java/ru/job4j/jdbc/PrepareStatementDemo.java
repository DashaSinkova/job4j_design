package ru.job4j.jdbc;

import org.postgresql.Driver;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PrepareStatementDemo {
    private Connection connection;
    public PrepareStatementDemo() throws Exception {
        initConnection();
    }
    public void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        connection = DriverManager.getConnection(url, login, password);
    }

    public City insert(City city) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement("insert into cities(name, population) values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                while (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }
    public boolean update(City city) {
        boolean res = false;
        try (PreparedStatement statement = connection.prepareStatement("update cities set name = ?, population = ? where id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            res = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public boolean delete(int id) {
        boolean res = false;
        try (PreparedStatement statement = connection.prepareStatement("delete from cities where id = ?")) {
            statement.setInt(1, id);
            res = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void main(String[] args) throws Exception {
        PrepareStatementDemo preparedStatement = new PrepareStatementDemo();
        Properties properties = new Properties();
        properties.load(new FileInputStream("C:\\projects\\job4j_design\\chapter_001\\app.properties"));
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable("cities");
        tableEditor.addColumn("cities", "id", "serial primary key");
        tableEditor.addColumn("cities", "name", "varchar(255)");
        tableEditor.addColumn("cities", "population", "int");
        System.out.println("Схема таблицы: ");
        tableEditor.getScheme("cities");
        System.out.println("Вставка: ");
        preparedStatement.insert(new City(0, "Санкт-Петербург", 1000000));
          preparedStatement.insert(new City(0, "Москва", 100000000));
          System.out.println(preparedStatement.findAll().toString());
        System.out.println("Обновление: ");
          preparedStatement.update(new City(1, "СПБ", 10));
        System.out.println(preparedStatement.findAll().toString());
        System.out.println("Удаление: ");
        preparedStatement.delete(2);
        System.out.println(preparedStatement.findAll().toString());
    }
}
