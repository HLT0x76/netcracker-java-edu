package com.netcracker.edu.repositoryHandlers.xml.utils;

import com.netcracker.edu.customers.Gender;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Gender enum adapter for {@link com.netcracker.edu.contracts.Contract} objects marshalling.
 */
public class GenderAdapter extends XmlAdapter<String, Gender> {
  @Override
  public Gender unmarshal(String s) {
    return Gender.valueOf(s);
  }

  @Override
  public String marshal(Gender gender) {
    return gender.name();
  }
}
