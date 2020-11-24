package com.netcracker.edu.Parsers;

/**
 * File parser interface
 *
 * @param <T> array like object
 * @param <M> filepath as {@code String} or any other file object
 */
public interface IParser<T, M> {

    T parse(T array, M file);
}
