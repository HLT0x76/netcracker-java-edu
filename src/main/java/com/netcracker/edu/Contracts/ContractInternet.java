package com.netcracker.edu.Contracts;

import java.time.LocalDate;

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
                            int internetSpeed) {
        super(creationDate, expirationDate);
        this.internetSpeed = internetSpeed;
    }
}


