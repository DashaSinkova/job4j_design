package ru.job4j.ood.lsp;

public class MasterDiplom extends Diplom {
    private int page;

    public MasterDiplom(int page) {
        super(page);
        check(page);
        this.page = page;
    }

    @Override
    public void check(int page) {
        if (page < 70) {
            throw new IllegalArgumentException("Need more pages!");
        }
    }

    public static void main(String[] args) {
        Diplom diplom = new MasterDiplom(60);
    }
}
