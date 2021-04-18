package com.netcracker.edu.customers;

import java.time.LocalDate;
import java.time.Period;

import com.netcracker.edu.repositoryHandlers.xml.utils.DateAdapter;
import com.netcracker.edu.repositoryHandlers.xml.utils.GenderAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Basic customer class implementation.
 */
@XmlRootElement(name = "customer")
public class Customer {

  @XmlAttribute
  private int id;
  private static int nextId = 0;
  private Gender gender;
  private LocalDate dateOfBirth;
  private int age;
  private String fullName;
  private String passport;

  /**
   * This class provides basic Customer functionality.
   *
   * @param dateOfBirth the date of birth
   * @param fullName full name of a Costumer
   * @param passport the passport string
   */
  public Customer(LocalDate dateOfBirth,
                  Gender gender,
                  String fullName,
                  String passport) {
    this.id = nextId++;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.fullName = fullName;
    this.passport = passport;
    this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
  }

    public Customer(Integer id,
                    LocalDate dateOfBirth,
                    Gender gender,
                    String fullName,
                    String passport) {
    this.id = id;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.fullName = fullName;
    this.passport = passport;
    this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
  }

  public Customer() {
  }

  public Gender getGender() {
    return gender;
  }

  @XmlElement
  @XmlJavaTypeAdapter(GenderAdapter.class)
  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  @XmlElement
  @XmlJavaTypeAdapter(DateAdapter.class)
  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPassport() {
    return passport;
  }

  public void setPassport(String passport) {
    this.passport = passport;
  }

  public int getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}

