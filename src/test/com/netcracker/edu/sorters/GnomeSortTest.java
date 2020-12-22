package com.netcracker.edu.sorters;

import com.netcracker.edu.sorters.gnome.GnomeSort;
import java.util.Comparator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Gnome sorter functionality test.
 */
public class GnomeSortTest {

  final Integer[] actual = {662, 42, 331, 985, 44, 1, 883};
  final Integer[] expected = {1, 42, 44, 331, 662, 883, 985};

  @Test
  public void sort() {
    Comparator<Integer> comp = (Comparator.comparingInt(o -> o));
    ISorter<Integer> gnomeSort = new GnomeSort<>();
    gnomeSort.sort(actual, comp);
    Assert.assertArrayEquals(expected, actual);
  }
}