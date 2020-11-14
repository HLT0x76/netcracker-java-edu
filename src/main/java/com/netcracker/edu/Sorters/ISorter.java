package com.netcracker.edu.Sorters;

import java.util.Comparator;

public interface ISorter<T> {

    void sort(T[] objects, Comparator<T> comparator);

}
