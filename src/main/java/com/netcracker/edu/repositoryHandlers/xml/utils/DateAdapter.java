package com.netcracker.edu.repositoryHandlers.xml.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Local date adapter for a
 * {@link com.netcracker.edu.contracts.Contract} object marshalling.
 */
public class DateAdapter extends XmlAdapter<String, LocalDate> {
  @Override
  public LocalDate unmarshal(String s) {
    return LocalDate.parse(s, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
  }

  @Override
  public String marshal(LocalDate localDate) {
    return localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
  }
}
