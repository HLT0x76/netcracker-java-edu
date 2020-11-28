package com.netcracker.edu.customers;

import java.time.LocalDate;
import java.time.Period;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Customer class methods functionality test.
 */
public class CustomerTest {

  private LocalDate dob = LocalDate.of(1937, 7, 18);
  private static final String fullName = "Hunter Stockton Thompson";
  private static final String passport = "6010-223415";
  private static final Gender gender = Gender.MALE;

  @BeforeEach
  public void setData() {
    this.dob = dob.plusYears(1);
  }

  @Test
  public void getAge() {
    LocalDate today = LocalDate.now();
    int age = Period.between(dob, today).getYears();
    Customer c = new Customer(
            dob,
            gender,
            fullName,
            passport);
    Assert.assertEquals(age, c.getAge());
  }
}