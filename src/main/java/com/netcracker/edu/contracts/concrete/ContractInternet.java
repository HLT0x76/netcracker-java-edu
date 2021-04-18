package com.netcracker.edu.contracts.concrete;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.customers.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;

/**
 * Internet contract (extends {@link Contract}).
 */
@Getter
@XmlRootElement(name = "contractInternet")
@XmlType(propOrder = {
        "id",
        "creationDate",
        "expirationDate",
        "contractOwner",
        "internetSpeed"
})
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

  public ContractInternet(Integer id,
                          LocalDate creationDate,
                          LocalDate expirationDate,
                          int internetSpeed,
                          Customer contractOwner) {
    super(id, creationDate, expirationDate, contractOwner);
    this.internetSpeed = internetSpeed;
  }

  public ContractInternet() {
  }
}


