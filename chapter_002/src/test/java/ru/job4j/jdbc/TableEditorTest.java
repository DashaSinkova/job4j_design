package ru.job4j.jdbc;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class TableEditorTest {

    public TableEditor getTableEditor() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("C:\\projects\\job4j_design\\chapter_001\\app.properties"));
        return new TableEditor(properties);
    }

    @Test
    public void whenCreateTable() throws Exception {
        StringBuilder res = new StringBuilder();
        res.append(String.format("%-15s %-15s%n", "column", "type"));
        getTableEditor().createTable("Planets");
        Assert.assertThat(res.toString(), is(getTableEditor().getScheme("Planets")));
    }

    @Test
    public void whenDropTable() throws Exception {
        Assert.assertThat(getTableEditor().dropTable("Planets"), is(true));
    }

    @Test
    public void whenAddColumn() throws Exception {
        Assert.assertThat(getTableEditor().addColumn("Planets", "размер", "float"), is(true));
    }

    @Test
    public void whenRenameColumn() throws Exception {
        Assert.assertThat(getTableEditor().renameColumn("Planets", "размер", "size"), is(true));
    }

    @Test
    public void whenDropColumn() throws Exception {
        Assert.assertThat(getTableEditor().dropColumn("Planets", "size"), is(true));
    }
}
