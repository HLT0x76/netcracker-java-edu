package com.netcracker.edu.contracts;

import com.netcracker.edu.customers.Customer;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * Contract abstract class, which sets
 * basic contract fields (like creation/expiration date, id, etc).
 */
@Getter
public abstract class Contract {

  private final int id;
  @Setter private static int nextId = 0;
  @Setter private LocalDate creationDate;
  @Setter private LocalDate expirationDate;
  @Setter private Customer contractOwner;

  /**
   * id field uses a nextId inner iterator
   * and will be defined at Contract constructor call.
   *
   * @param creationDate contract creation date
   * @param expirationDate contract expiration date
   * @param contractOwner {@link Customer} object
   */
  public Contract(LocalDate creationDate, LocalDate expirationDate, Customer contractOwner) {
    this.id = nextId++;
    this.creationDate = creationDate;
    this.expirationDate = expirationDate;
    this.contractOwner = contractOwner;
  }

  public Contract() {
    this.id = nextId++;
  }
}