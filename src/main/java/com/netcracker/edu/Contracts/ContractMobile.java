package com.netcracker.edu.Contracts;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.Getter;

/**
 * Mobile contract (extends {@link Contract})
 */
@Getter
@Setter(AccessLevel.PROTECTED)
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
                          long internetAmount) {
        super(creationDate, expirationDate);
        this.smsAmount = smsAmount;
        this.minutesAmount = minutesAmount;
        this.internetAmount = internetAmount;
    }
}
