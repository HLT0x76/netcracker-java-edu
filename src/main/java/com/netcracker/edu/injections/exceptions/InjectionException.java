package com.netcracker.edu.injections.exceptions;

import com.netcracker.edu.injections.Injector;

/**
 * Will be thrown by a
 * class, which uses {@link Injector} in its constructor.
 */
public class InjectionException extends Exception {
  public InjectionException(String s) {
    super(s);
  }
}