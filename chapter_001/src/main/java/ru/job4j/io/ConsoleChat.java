/*
Курс Петра/ Джуниор/  Блок 2.2. Ввод-вывод/ 1. Ввод-вывод/ 6. Кодировка. [#297691]
'Консольный чат'
 */
package ru.job4j.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
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
            //ищем ответ из другого файла, прибавляем к строке
                String botAnswer = searchAnswer();
                line = "Пользователь: " + line + System.lineSeparator() + "Бот: " + botAnswer;
                System.out.println("Бот: " + botAnswer);

            } else {
                line = "Пользователь: " + line;
            }
            //записываем строку в итоговый файл
            writeInFile(line);
            System.out.print("Пользователь: ");
            line = scanner.nextLine();
        }
        System.out.println("Бот: Разговор окончен.");
        writeInFile("Пользователь: " + line + System.lineSeparator() + "Бот: Разговор окончен.");
    }
    private void writeInFile(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(line + System.lineSeparator());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String searchAnswer() {
        String res = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            Object[] array = reader.lines().toArray();
            double i = array.length * Math.random();
            int index = (int) i;
            res = array[index].toString();
        } catch (Exception e) {
            System.out.println("Файл пуст");
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./path.txt", "./botanswers.txt");
        cc.run();
    }
}
