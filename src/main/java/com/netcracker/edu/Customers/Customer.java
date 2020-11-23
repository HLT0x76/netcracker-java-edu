package com.netcracker.edu.Customers;

import com.netcracker.edu.Contracts.Contract;
import com.netcracker.edu.Repository.ContractsRepository;
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
    private ContractsRepository contracts;

    /**
     * This class provides basic Customer functionality
     * @param id ID field
     * @param dateOfBirth the date of birth
     * @param fullName full name of a Costumer
     * @param passport the passport string
     * @param contracts object, which extends {@link Contract} abstract or {@link ContractsRepository} itself
     */

    public Customer(int id,
                    LocalDate dateOfBirth,
                    Gender gender,
                    String fullName,
                    String passport,
                    ContractsRepository contracts) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.fullName = fullName;
        this.passport = passport;
        this.contracts = contracts;
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

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
        this.contracts = new ContractsRepository();
        this.contracts.add(contract);
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

}

