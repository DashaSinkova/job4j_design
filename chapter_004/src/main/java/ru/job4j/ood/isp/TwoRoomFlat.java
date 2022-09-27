package ru.job4j.ood.isp;

public class TwoRoomFlat implements Flat {
    @Override
    public String getBathroom() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getToilet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSquare() {
        return 45;
    }

    @Override
    public String getToiletWithBathroom() {
        return "ToiletWithBathroom";
    }
}
