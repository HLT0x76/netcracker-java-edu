package com.netcracker.edu.Customers;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import com.netcracker.edu.Contracts.ContractTelevision;
import org.junit.jupiter.api.BeforeEach;

public class CustomerTest {

    private int id = 0;
    private LocalDate dob = LocalDate.of(1937, 7, 18);
    private static final String firstName = "Hunter";
    private static final String secondName = "Thompson";
    private static final String middleName = "Stockton";
    private static final String passport = "6010223415";

    @BeforeEach
    public void setData() {
        this.id += 1;
        this.dob = dob.plusYears(1);


    }

    @Test
    public void getAge() {
        LocalDate now = LocalDate.now();
        ContractTelevision con = new ContractTelevision(now, now.plusYears(1), "Ultra");
        int age = now.getYear() - dob.getYear();
        Customer c = new Customer(id, dob, firstName, secondName, middleName, passport, con);

        assertEquals(age, c.getAge());
    }
}