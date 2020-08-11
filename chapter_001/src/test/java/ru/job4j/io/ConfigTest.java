package ru.job4j.io;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import org.junit.Test;

public class ConfigTest {
    @Test
    public void whenGetAppComments() {
        Config config = new Config("app.properties");
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }
    @Test
    public void whenGetAppCommentsNotFound() {
        Config config = new Config("app.properties");
        config.load();
        assertThat(config.value("## PostgreSQL"), is(nullValue()));
    }
}
