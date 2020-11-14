package com.netcracker.edu.Sorters;

import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;

public class BubbleSorterTest {

    Integer[] actual = {662, 42, 331, 985, 44, 1, 883};
    Integer[] expected = {1, 42, 44, 331, 662, 883, 985};

    @Test
    public void sort() {
        Comparator<Integer> comp = (Comparator.comparingInt(o -> o));
        ISorter<Integer> gnomeSort = new BubbleSorter<>();
        gnomeSort.sort(actual, comp);
        assertEquals(expected, actual);
    }
}