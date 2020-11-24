package com.netcracker.edu.Converters;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;

import java.time.LocalDate;

/**
 * Converter which extends {@link AbstractBeanField} and implements
 * {@code String} to {@link LocalDate} conversion
 */
public class StringToLocalDateConverter extends AbstractBeanField<LocalDate> {

    @Override
    protected LocalDate convert(String value) throws CsvConstraintViolationException {
        try {
            int day = Integer.parseInt(value.split("-")[0]);
            int month = Integer.parseInt(value.split("-")[1]);
            int year = Integer.parseInt(value.split("-")[2]);
            return LocalDate.of(year, month, day);
        } catch (NumberFormatException n) {
            throw new CsvConstraintViolationException();
        }

    }
}
