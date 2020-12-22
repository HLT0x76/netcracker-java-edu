package com.netcracker.edu.validators;

/**
 * Validator abstract class for a concrete classes, which
 * will extend it and perform different validations.
 *
 * @param <T> object, which fields or functions results will be checked
 */
public abstract class Validator<T> {

  public abstract ValidationReport check(T object);

}
