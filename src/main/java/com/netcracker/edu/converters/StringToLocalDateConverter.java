package com.netcracker.edu.converters;

import com.opencsv.bean.AbstractBeanField;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Converter which extends {@link AbstractBeanField} and implements
 * {@code String} to {@link LocalDate} conversion.
 */
public class StringToLocalDateConverter extends AbstractBeanField<LocalDate> {

  @Override
  protected LocalDate convert(String value) {
    try {
      return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    } catch (DateTimeException e) {
      // TODO logging
      return null;
    }

  }
}
