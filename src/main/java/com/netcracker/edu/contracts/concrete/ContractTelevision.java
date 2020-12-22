package com.netcracker.edu.contracts.concrete;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.customers.Customer;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * Television contract (extends {@link Contract}).
 */
@Getter
@Setter
public class ContractTelevision extends Contract {

  private String channelsPacket;

  /**
   * Television contract concrete class which extends {@link Contract}.
   *
   * @param channelsPacket Channel packet description
   */
  public ContractTelevision(
      LocalDate creationDate,
      LocalDate expirationDate,
      String channelsPacket,
      Customer contractOwner) {
    super(creationDate, expirationDate, contractOwner);
    this.channelsPacket = channelsPacket;
  }

  public ContractTelevision() {
  }
}
