package ru.job4j.io;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import org.junit.Test;

public class ArgsNameTest {
    @Test
    public void whenGetFirstName() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx"), is("512"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {});
        jvm.get("Xmx");
    }
}
