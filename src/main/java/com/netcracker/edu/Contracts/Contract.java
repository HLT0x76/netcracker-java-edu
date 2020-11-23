package com.netcracker.edu.Contracts;

import java.time.LocalDate;

import com.netcracker.edu.Customers.Customer;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public abstract class Contract {

    private int id;
    private static int nextId = 0;
    private LocalDate creationDate;
    private LocalDate expirationDate;
    private Customer contractOwner;

    /**
     * id field a nextId inner iterator
     * @param creationDate creation date
     * @param expirationDate expiration date
     * @param contractOwner {@link Customer} object
     */
    public Contract(LocalDate creationDate, LocalDate expirationDate, Customer contractOwner)
    {
        this.id = nextId++;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.contractOwner = contractOwner;
    }

    public Contract() {

    }
}