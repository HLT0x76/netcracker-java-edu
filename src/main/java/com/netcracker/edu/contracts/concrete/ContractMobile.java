package com.netcracker.edu.contracts.concrete;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.customers.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;

/**
 * Mobile contract (extends {@link Contract}).
 */
@Getter
@Setter
@XmlRootElement(name = "contractMobile")
@XmlType(propOrder = {
        "id",
        "creationDate",
        "expirationDate",
        "contractOwner",
        "smsAmount",
        "minutesAmount",
        "internetAmount"
})
@Entity
@Table(name = "CONTRACTS_MOBILE")
public class ContractMobile extends Contract {

  @Column(name = "sms_amount")
  private long smsAmount;

  @Column(name = "minutes_amount")
  private long minutesAmount;

  @Column(name = "internet_amount")
  private long internetAmount;

  /**
   * Mobile contract concrete class which extends {@link Contract}.
   *
   * @param smsAmount      SMS amount for contract
   * @param minutesAmount  Minutes of mobile connection amount
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

  public ContractMobile(Integer id,
                        LocalDate creationDate,
                        LocalDate expirationDate,
                        long smsAmount,
                        long minutesAmount,
                        long internetAmount,
                        Customer contractOwner) {
    super(id, creationDate, expirationDate, contractOwner);
    this.smsAmount = smsAmount;
    this.minutesAmount = minutesAmount;
    this.internetAmount = internetAmount;
  }

  public ContractMobile() {
  }

}
