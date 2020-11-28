package com.netcracker.edu.sorters;

import java.util.Comparator;

/**
 * Sorter interface.
 *
 * @param <T> type of values, stored in provided array
 */
public interface ISorter<T> {

  void sort(T[] objects, Comparator<T> comparator);

}
