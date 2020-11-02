package com.netcracker.edu.Contracts;

import java.time.LocalDate;

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
                              String channelsPacket) {
        super(creationDate, expirationDate);
        this.channelsPacket = channelsPacket;
    }
}
