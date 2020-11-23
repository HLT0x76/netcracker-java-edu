package com.netcracker.edu.Customers;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Period;

import com.netcracker.edu.Contracts.ContractTelevision;
import org.junit.jupiter.api.BeforeEach;

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
        ContractTelevision con = new ContractTelevision(today, today.plusYears(1), "Ultra");
        int age = Period.between(dob, today).getYears();
        Customer c = new Customer(
                dob,
                gender,
                fullName,
                passport);

        assertEquals(age, c.getAge());
    }
}