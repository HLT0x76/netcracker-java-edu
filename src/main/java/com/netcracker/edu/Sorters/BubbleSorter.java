package com.netcracker.edu.Sorters;

import java.util.Comparator;

public class BubbleSorter<T> implements ISorter<T> {

    @Override
    public void sort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-i-1; j++) {
                if (comparator.compare(array[j], array[j+1]) > 0)  {
                    T temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

}
