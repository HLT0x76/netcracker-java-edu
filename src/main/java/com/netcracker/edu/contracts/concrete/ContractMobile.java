package com.netcracker.edu.contracts.concrete;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.customers.Customer;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * Mobile contract (extends {@link Contract}).
 */
@Getter
@Setter
public class ContractMobile extends Contract {

  private long smsAmount;
  private long minutesAmount;
  private long internetAmount;

  /**
   * Mobile contract concrete class which extends {@link Contract}.
   *
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
  }

}
