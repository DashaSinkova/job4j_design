package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIt implements Iterator<Integer> {
   private int[] arr;
   private int point = 0;
   public ArrayIt(int[] arr) {
       this.arr = arr;
   }
    public boolean hasNext() {
        return point < arr.length;
    }

    public Integer next() {
       if (!hasNext()) {
           throw new NoSuchElementException();
       }
        return arr[point++];
    }
}
