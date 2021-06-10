/*
саджест названий компаний по подстроке — из списка всех доступных названий вывести определенное
 число компаний, которые начинаются с введенной строчки. Предполагается,
 что класс будет вызываться при заполнении формы на сайте или мобильном приложении с высоким RPS (requests per second).
* */package test.yandex;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SuggestService {
    private List<String> companyNames;
    public SuggestService(List<String> companyNames) {
        this.companyNames = companyNames;
    }
    public List<String> suggest(String input, Integer numberOfSuggest) {
        return companyNames.stream().filter(name -> name.startsWith(input)).limit(numberOfSuggest).collect(Collectors.toList());
    }
}
