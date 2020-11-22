package com.netcracker.edu.Customers;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Period;

import com.netcracker.edu.Contracts.ContractTelevision;
import org.junit.jupiter.api.BeforeEach;

public class CustomerTest {

    private int id = 0;
    private LocalDate dob = LocalDate.of(1937, 7, 18);
    private static final String fullName = "Hunter Stockton Thompson";
    private static final String passport = "6010-223415";
    private static final Gender gender = Gender.MALE;

    @BeforeEach
    public void setData() {
        this.id += 1;
        this.dob = dob.plusYears(1);
    }

    @Test
    public void getAge() {
        LocalDate today = LocalDate.now();
        ContractTelevision con = new ContractTelevision(today, today.plusYears(1), "Ultra");
        int age = Period.between(dob, today).getYears();
        Customer c = new Customer(id,
                dob,
                gender,
                fullName,
                passport,
                con);

        assertEquals(age, c.getAge());
    }
}