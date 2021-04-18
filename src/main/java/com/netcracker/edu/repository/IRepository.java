package com.netcracker.edu.repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Repository interface.
 *
 * @param <T> object, which will be contained by repository
 */
public interface IRepository<T> {

  void add(T other);

  Optional<T> get(int index);

  void delete(int index);

  List<T> toList();

  void sortBy(Comparator<T> comparator);

  IRepository<T> searchBy(Predicate<T> condition);

}
