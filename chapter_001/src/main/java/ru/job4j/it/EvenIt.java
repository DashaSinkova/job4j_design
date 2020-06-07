package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private  int[] numbers;
    private int position = 0;
    public EvenIt(int[] numbers) {
        this.numbers = numbers;
    }
    public boolean hasNext() {
        boolean res = false;
        for (int i = position; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                position = i;
                res = true;
                break;
            }
        }
        return res;
    }
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[position++];
    }

}
