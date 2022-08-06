package ru.job4j.template;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
@Disabled
public class GeneratorTest {
    @Test
    public void whenGenerateCorrectly() {
        Generator generator = new SimpleGenerator();
        Map<String, String> words = new HashMap<>();
        words.put("name", "Petr Arsentev");
        words.put("subject ", "you");
        assertThat(generator.produce("I am a ${name}, Who are ${subject}?", words)).isEqualTo("I am a Petr Arsentev, Who are you?");
    }

    @Test
    public void whenExceptionWithNoAllKeysInMap() {
        Generator generator = new SimpleGenerator();
        Map<String, String> words = new HashMap<>();
        words.put("subject ", "you");
        assertThrows(IllegalArgumentException.class, () -> generator.produce("I am a ${name}, Who are ${subject}?", words));
    }

    @Test
    public void whenExceptionWithALotKeysInMap() {
        Generator generator = new SimpleGenerator();
        Map<String, String> words = new HashMap<>();
        words.put("subject ", "you");
        words.put("name", "Petr Arsentev");
        words.put("name1", "Petr Arsentev");
        assertThrows(IllegalArgumentException.class, () -> generator.produce("I am a ${name}, Who are ${subject}?", words));
    }

}
