package com.netcracker.edu.Contracts;

import java.time.LocalDate;

import com.netcracker.edu.Customers.Customer;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.Getter;


/**
 * Television contract (extends {@link Contract})
 */
@Getter
@Setter(AccessLevel.PROTECTED)
public class ContractTelevision extends Contract {

    private String channelsPacket;

    /**
     * @param channelsPacket Channel packet description
     */
    public ContractTelevision(LocalDate creationDate,
                              LocalDate expirationDate,
                              String channelsPacket,
                              Customer contractOwner) {
        super(creationDate, expirationDate, contractOwner);
        this.channelsPacket = channelsPacket;
    }

    public ContractTelevision() {
        super();
    }
}
