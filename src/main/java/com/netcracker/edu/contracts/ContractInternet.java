package com.netcracker.edu.contracts;

import com.netcracker.edu.customers.Customer;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * Internet contract (extends {@link Contract}).
 */
@Getter
public class ContractInternet extends Contract {

  @Setter
  private int internetSpeed;

  /**
   * Internet contract concrete class which extends {@link Contract}.
   *
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
  }
}


