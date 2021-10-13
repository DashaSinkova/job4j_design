/*
Курс Петра/ Джуниор/  Блок 2.2. Ввод-вывод/ 1. Ввод-вывод/ 6. Кодировка. [#297691]
'Консольный чат'
*/
package ru.job4j.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private List<String> answers;
    private List<String> dialog = new ArrayList<>();
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(botAnswers))) {
            answers = bufferedReader.lines().collect(Collectors.toList());
            if (answers.size() == 0) {
                throw new Exception("Файл пуст");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Пользователь: ");
        String line = scanner.nextLine();
        boolean isBotAnswer = true;
        while (!line.equals(OUT)) {
            if (line.equals(STOP)) {
                isBotAnswer = false;
            } else if (line.equals(CONTINUE)) {
                isBotAnswer = true;
            }
            if (isBotAnswer) {
                String botAnswer = answers.get((int) (answers.size() * Math.random()));
                line = "Пользователь: " + line + System.lineSeparator() + "Бот: " + botAnswer;
                System.out.println("Бот: " + botAnswer);

            } else {
                line = "Пользователь: " + line;
            }
            dialog.add(line);
            System.out.print("Пользователь: ");
            line = scanner.nextLine();
        }
        System.out.println("Бот: Разговор окончен.");
        dialog.add("Пользователь: " + line + System.lineSeparator() + "Бот: Разговор окончен.");
        writeInFile(dialog);
    }

    private void writeInFile(List<String> dialog) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            for (String line : dialog) {
                writer.write(line + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./path.txt", "./botanswers.txt");
        cc.run();
    }
}
