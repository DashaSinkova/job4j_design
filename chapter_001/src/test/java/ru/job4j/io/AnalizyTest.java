package ru.job4j.io;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnalizyTest {
    @Test
    public void whenGetDateServerUnavailable() {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "res.log");
        try (BufferedReader reader = new BufferedReader(new FileReader("res.log"))) {
            List<String> res = new ArrayList<>();
            reader.lines().forEach(res :: add);
            assertThat(res.toString(), is("[10:58:01;10:59:01;, 11:01:02;11:02:02;]"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
