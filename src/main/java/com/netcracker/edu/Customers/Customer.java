package com.netcracker.edu.Customers;

import java.time.LocalDate;
import java.time.Period;

import lombok.Setter;
import lombok.Getter;
import lombok.AccessLevel;

/**
 *
 */
@Getter
@Setter(AccessLevel.PROTECTED)
public class Customer {

    private final int id;
    private static int nextId = 0;
    private Gender gender;
    private LocalDate dateOfBirth;
    private final int age;
    private String fullName;
    private String passport;

    /**
     * This class provides basic Customer functionality
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

}

