package com.netcracker.edu.Contracts;

import java.time.LocalDate;

import com.netcracker.edu.Customers.Customer;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.Getter;


/**
 * Internet contract (extends {@link Contract})
 */
@Getter
@Setter(AccessLevel.PROTECTED)
public class ContractInternet extends Contract {

    private int internetSpeed;

    /**
     * @param internetSpeed Contract internet speed (in MB/s)
     */
    public ContractInternet(LocalDate creationDate,
                            LocalDate expirationDate,
                            int internetSpeed,
                            Customer contractOwner) {
        super(creationDate, expirationDate, contractOwner);
        this.internetSpeed = internetSpeed;
    }

    public ContractInternet() {
        super();
    }
}


