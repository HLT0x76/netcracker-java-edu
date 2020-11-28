package com.netcracker.edu.validators;

/**
 * Abstract class, which implements "chain of responsibility" pattern.
 * This allows us to chain different classes,
 * which extends {@code Validator}, and perform complex validation algorithms.
 *
 * @param <T> object, which fields or functions results will be checked
 */
public abstract class Validator<T> {

  private Validator<T> next;

  public Validator<T> linkWith(Validator<T> next) {
    this.next = next;
    return next;
  }

  public abstract ValidationReport check(T object);

  protected ValidationReport checkNext(T object) {
    if (next == null) {
      return new ValidationReport(ValidationStatus.OK);
    } else {
      return next.check(object);
    }
  }
}
