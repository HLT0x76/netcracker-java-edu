package com.netcracker.edu.sorters.bubble;

import com.netcracker.edu.sorters.ISorter;
import java.util.Comparator;

/**
 * Implements well known "bubble sort" functionality.
 *
 * @param <T> type of stored in array values
 */
public class BubbleSorter<T> implements ISorter<T> {

  @Override
  public void sort(T[] array, Comparator<T> comparator) {
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array.length - i - 1; j++) {
        if (comparator.compare(array[j], array[j + 1]) > 0) {
          T temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;
        }
      }
    }
  }

}
