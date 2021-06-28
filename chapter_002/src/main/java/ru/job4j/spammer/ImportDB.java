package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Connection cn;
    private Properties properties;
    private String file;

    public ImportDB(Properties properties, String file) throws Exception {
        this.file = file;
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("jdbc.driver"));
        cn = DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password"));
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(file))) {
            rd.lines().forEach(el -> {
                String[] str = el.split(";");
                users.add(new User(str[0], str[1]));
            });
        }
        return users;
    }

    public void save(List<User> users) throws SQLException {
            try (Statement st = cn.createStatement()) {
                st.execute("create table if not exists users(" +
                        "id serial primary key," +
                        "name text," +
                        "email text);");
            }
            for (User user : users) {
                try (PreparedStatement ps = cn.prepareStatement("insert into users(name, email) values (?, ?)")) {
                    ps.setString(1, user.getName());
                    ps.setString(2, user.getEmail());
                    ps.execute();
                }
            }
        }
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (FileInputStream rd = new FileInputStream("C:\\projects\\job4j_design\\chapter_002\\src\\main\\resources\\app.properties")) {
            properties.load(rd);
        }
        ImportDB db = new ImportDB(properties, "C:\\projects\\job4j_design\\chapter_002\\src\\main\\resources\\dump.txt");
        db.save(db.load());
    }
}
