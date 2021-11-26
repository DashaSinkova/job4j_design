package ru.job4j.gc;


public class GCUser {
    private static final Runtime RUNTIME = Runtime.getRuntime();

    public static void info() {
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", RUNTIME.freeMemory());
        System.out.printf("Total: %d%n", RUNTIME.totalMemory());
        System.out.printf("Max: %d%n", RUNTIME.maxMemory());
    }

    public static void main(String[] args) {
        info();
        User user1 = new User();
        User user2 = new User(1, "Dasha");
        User user3 = new User(111111, "123456789");
        info();
    }
}
