package com.netcracker.edu.Repository;

import com.netcracker.edu.Sorters.ISorter;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface IRepository<T> {

    void add(T other);

    Optional<T> get(int index);

    void delete(int index);

    List<T> toList();

    void sortBy(ISorter<T> sorter, Comparator<T> comparator);

    IRepository<T> searchBy(Predicate<T> condition);

}
