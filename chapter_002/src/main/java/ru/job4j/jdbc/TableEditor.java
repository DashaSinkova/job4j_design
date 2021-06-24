package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public boolean createTable(String tableName) throws Exception {
        // создает пустую таблицу без столбцов с указанным именем;
        String sql = String.format("create table if not exists %s();", tableName);
        return executeRequest(sql);
    }

    private boolean executeRequest(String sql) {
        boolean res = false;
       try (Statement statement = connection.createStatement()) {
           statement.execute(sql);
           res = true;
       } catch (Exception e) {
           System.out.println("Запрос не выполнен");
       }
        return res;
    }

    public boolean dropTable(String tableName) throws Exception {
        //- dropTable() – удаляет таблицу по указанному имени;
        String sql = String.format("drop table if exists %s;", tableName);
        return executeRequest(sql);
    }

    public boolean addColumn(String tableName, String columnName, String type) throws Exception {
        //добавляет столбец в таблицу;
        String sql = String.format("alter table %s add column %s %s;", tableName, columnName, type);
       return executeRequest(sql);
    }

    public boolean dropColumn(String tableName, String columnName) throws Exception {
        //удаляет столбец из таблицы;
        String sql = String.format("alter table %s drop column %s;", tableName, columnName);
        return executeRequest(sql);
    }

    public boolean renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        //переименовывает столбец.
        String sql = String.format("alter table %s rename column %s to %s;", tableName, columnName, newColumnName);
       return executeRequest(sql);
    }

    public String getScheme(String tableName) throws SQLException {
//        StringBuilder scheme = new StringBuilder();
//        DatabaseMetaData metaData = connection.getMetaData();
//        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
//            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
//            while (columns.next()) {
//                scheme.append(String.format("%-15s %-15s%n",
//                        columns.getString("COLUMN_NAME"),
//                        columns.getString("TYPE_NAME")));
//            }
//        }
//        System.out.println(scheme);
//        return scheme.toString();
        StringBuilder scheme = new StringBuilder();
        scheme.append(String.format("%-15s %-15s%n", "column", "type"));
        ResultSet rs = connection.createStatement().executeQuery("select * from "+tableName);
        ResultSetMetaData rsMetaData = rs.getMetaData();
        for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
            scheme.append(String.format("%-15s %-15s%n", rsMetaData.getColumnName(i), rsMetaData.getColumnTypeName(i)));
        }
        System.out.println(scheme);
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("C:\\projects\\job4j_design\\chapter_001\\app.properties"));
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable("Seasons");
        tableEditor.getScheme("Seasons");
        tableEditor.addColumn("Seasons", "name", "varchar(255)");
        tableEditor.getScheme("Seasons");
        tableEditor.addColumn("Seasons", "temperature", "decimal");
        tableEditor.getScheme("Seasons");
        tableEditor.renameColumn("Seasons", "name", "title");
        tableEditor.getScheme("Seasons");
        tableEditor.dropColumn("Seasons", "temperature");
        tableEditor.getScheme("Seasons");
        tableEditor.dropTable("Seasons");
        tableEditor.close();
    }
}