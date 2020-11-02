package com.netcracker.edu.Customers;

import com.netcracker.edu.Contracts.Contract;
import java.time.LocalDate;

import lombok.Setter;
import lombok.Getter;
import lombok.AccessLevel;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Customer {

    private final int id;
    private final int age;
    private int nextId = 0;
    private LocalDate dateOfBirth;
    private String firstName;
    private String secondName;
    private String middleName;
    private String passport;
    private Contract contract;

    /**
     * This class provides basic Customer functionality
     *  @param id ID field
     * @param dateOfBirth the date of birth
     * @param firstName the first name parameter
     * @param secondName the second name parameter
     * @param middleName the middle name parameter, can be null
     * @param passport the passport string
     * @param contract the contract object, inherited from {@link Contract} class
     */

    public Customer(int id,
                    LocalDate dateOfBirth,
                    String firstName,
                    String secondName,
                    String middleName,
                    String passport,
                    Contract contract) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.passport = passport;
        this.contract = contract;

        this.age = this.calculateAge();
    }

    /**
     * Calculates customer age from {@code dateOfBirth} field
     * by using {@code LocalDate.getYear()} method
     */
    private int calculateAge() {
        int currentYear = LocalDate.now().getYear();
        int dobYear = dateOfBirth.getYear();
        return (currentYear - dobYear);
    }
}

