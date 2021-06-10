package test.yandex;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class SuggestServiceTest {
    @Test
    public void whenInsertThreeLettersCompanies() {
        SuggestService service = new SuggestService(Arrays.asList("Яндекс", "ЯндексТакси",
                "ЯндексЛавка","ЯндексЕда", "Jetbrains",
                "MailRu", "T-Systems", "EPAM"));
        List<String> result = Arrays.asList("Яндекс", "ЯндексТакси",
                "ЯндексЛавка");
        assertThat(service.suggest("Янд", 3), is(result));
    }
    @Test
    public void whenInsertOneLettersCompanies() {
        SuggestService service = new SuggestService(Arrays.asList("Яндекс", "ЯндексТакси",
                "ЯндексЛавка","ЯндексЕда", "Jetbrains",
                "MailRu", "T-Systems", "EPAM"));
        List<String> result = Arrays.asList("Jetbrains");
        assertThat(service.suggest("J", 3), is(result));
    }
}
