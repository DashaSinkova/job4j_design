package ru.job4j.cache;

/**
 * Класс для работы с пользователем.
 * Предоставить пользователю возможности:
 *
 * - указать кэшируемую директорию
 *
 * - загрузить содержимое файла в кэш
 *
 * - получить содержимое файла из кэша
 */
public class Emulator {
    public static void main(String[] args) {
        DirFileCache cache = new DirFileCache("./test");
        System.out.println(cache.get("test.txt"));
        System.out.println(cache.get("test2.txt"));
    }
}
