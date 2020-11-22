package com.netcracker.edu.Customers;

import com.netcracker.edu.Contracts.Contract;
import java.time.LocalDate;
import java.time.Period;

import lombok.Setter;
import lombok.Getter;
import lombok.AccessLevel;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Customer {

    private final int id;
    private Gender gender;
    private LocalDate dateOfBirth;
    private final int age;
    private String fullName;
    private String passport;
    private Contract contract;

    /**
     * This class provides basic Customer functionality
     * @param id ID field
     * @param dateOfBirth the date of birth
     * @param fullName full name of a Costumer
     * @param passport the passport string
     * @param contract the contract object, inherited from {@link Contract} class
     */

    public Customer(int id,
                    LocalDate dateOfBirth,
                    Gender gender,
                    String fullName,
                    String passport,
                    Contract contract) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.fullName = fullName;
        this.passport = passport;
        this.contract = contract;
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

}

