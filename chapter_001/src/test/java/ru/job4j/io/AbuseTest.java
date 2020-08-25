package ru.job4j.io;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;

public class AbuseTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void drop() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter in = new PrintWriter(source)) {
            in.write("hello foolish dude ");
        }
        Abuse.drop(source.getAbsolutePath(), target.getAbsolutePath(), List.of("foolish"));
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader out = new BufferedReader(new FileReader(target))) {
            out.lines().forEach(rsl :: append);
        }
        assertThat(rsl.toString(), is("hello dude "));
    }
}
