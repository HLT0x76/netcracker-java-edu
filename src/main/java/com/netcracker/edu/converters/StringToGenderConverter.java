package com.netcracker.edu.converters;

import com.netcracker.edu.customers.Gender;
import com.opencsv.bean.AbstractBeanField;

/**
 * Converter which extends {@link AbstractBeanField} and implements
 * {@code String} to {@link Gender} conversion.
 */
public class StringToGenderConverter extends AbstractBeanField<Gender> {

  @Override
  protected Gender convert(String value) {
    switch (value) {
      case "Male":
        return Gender.MALE;
      case "Female":
        return Gender.FEMALE;
      default:
        // TODO logging
        return null;
    }
  }
}
