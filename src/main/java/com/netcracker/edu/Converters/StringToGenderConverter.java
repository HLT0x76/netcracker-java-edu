package com.netcracker.edu.Converters;

import com.netcracker.edu.Customers.Gender;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;

/**
 * Converter which extends {@link AbstractBeanField} and implements
 * {@code String} to {@link Gender} conversion
 */
public class StringToGenderConverter extends AbstractBeanField<Gender> {

    @Override
    protected Gender convert(String value) throws CsvConstraintViolationException {
        switch (value) {
            case "Male":
                return Gender.MALE;
            case "Female":
                return Gender.FEMALE;
            default:
                throw new CsvConstraintViolationException();
        }
    }
}
