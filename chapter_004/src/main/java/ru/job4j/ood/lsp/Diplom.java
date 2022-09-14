package ru.job4j.ood.lsp;

public class Diplom {
    private int page;

    public Diplom(int page) {
        check(page);
        this.page = page;
    }

    public void check(int page) {
        if (page < 50) {
            throw new IllegalArgumentException("Need more pages!");
        }
    }
}
