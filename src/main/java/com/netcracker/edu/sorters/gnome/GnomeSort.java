package com.netcracker.edu.sorters.gnome;

import com.netcracker.edu.sorters.ISorter;
import java.util.Comparator;

/**
 * Implements "gnome sort" functionality.
 *
 * @param <T> type of stored in array values
 */
public class GnomeSort<T> implements ISorter<T> {

  @Override
  public void sort(T[] array, Comparator<T> comparator) {
    int index = 0;

    while (index < array.length) {
      if (index == 0) {
        index++;
      }
      if (comparator.compare(array[index], array[index - 1]) > 0) {
        index++;
      } else {
        T temp = array[index];
        array[index] = array[index - 1];
        array[index - 1] = temp;
        index--;
      }
    }
  }

}
