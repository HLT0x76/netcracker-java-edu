package com.netcracker.edu.Contracts;

import java.time.LocalDate;

import com.netcracker.edu.Customers.Customer;
import lombok.Setter;
import lombok.Getter;

/**
 * Mobile contract (extends {@link Contract})
 */
@Getter
@Setter
public class ContractMobile extends Contract {

    private long smsAmount;
    private long minutesAmount;
    private long internetAmount;

    /**
     * @param smsAmount SMS amount for contract
     * @param minutesAmount Minutes of mobile connection amount
     * @param internetAmount Internet amount (in MBs)
     */
    public ContractMobile(LocalDate creationDate,
                          LocalDate expirationDate,
                          long smsAmount,
                          long minutesAmount,
                          long internetAmount,
                          Customer contractOwner) {
        super(creationDate, expirationDate, contractOwner);
        this.smsAmount = smsAmount;
        this.minutesAmount = minutesAmount;
        this.internetAmount = internetAmount;
    }

    public ContractMobile() {
        super();
    }

}
