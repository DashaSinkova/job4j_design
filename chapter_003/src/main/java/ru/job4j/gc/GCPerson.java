package ru.job4j.gc;

/*

* 1. Изучить класс Runtime, методы.
* 2. Что за цифры выдает программа, почему память увеличивается относительно начального размера
* 3. после добавления объекта выводим инфу о памяти и о занимаемом месте
* 4. Прочитать про занимаемое место в памяти объектом
* 5. carrotsearch
* */
public class GCPerson {
    private static final Runtime RUNTIME = Runtime.getRuntime();
    private static long memBeforeAdd = RUNTIME.freeMemory();

    public static void info() {
        System.out.println("Память до добавления объекта" + memBeforeAdd);
        System.out.println("===Объект добавлен===");
        long memAfterAdd = RUNTIME.freeMemory();
        System.out.println("Свободная память: " + memAfterAdd);
        System.out.println("Объект занимает: " + Math.abs(memBeforeAdd - memAfterAdd) + System.lineSeparator());
        memBeforeAdd = memAfterAdd;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new User(i, "name");
            info();
            System.gc();
        }
    }
}
