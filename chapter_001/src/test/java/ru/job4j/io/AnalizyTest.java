package ru.job4j.io;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenGetDateServerUnavailable() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("res.log");
        try (PrintWriter in = new PrintWriter(source)) {
            in.println("200 10:56:01");
            in.println("200 10:57:01");
            in.println("400 10:58:01");
            in.println("200 10:59:01");
            in.println("500 11:01:02");
            in.println("200 11:02:02");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(line -> rsl.append(line + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(rsl.toString(), is("10:58:01;10:59:01;" + System.lineSeparator() + "11:01:02;11:02:02;" + System.lineSeparator()));
    }
    @Test
    public void whenUseTemporaryFolder() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter in = new PrintWriter(source)) {
            in.println("200 10:56:01");
            in.println("200 10:57:01");
            in.println("400 10:58:01");
            in.println("200 10:59:01");
            in.println("500 11:01:02");
            in.println("200 11:02:02");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader out = new BufferedReader(new FileReader(target))) {
            out.lines().forEach(line -> rsl.append(line + System.lineSeparator()));
        }
        assertThat(rsl.toString(), is("10:58:01;10:59:01;" + System.lineSeparator() + "11:01:02;11:02:02;" + System.lineSeparator()));
    }

}
