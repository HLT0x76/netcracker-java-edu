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
 * Television contract (extends {@link Contract}).
 */
@Getter
@Setter
@XmlRootElement(name = "contractTV")
@XmlType(propOrder = {
        "id",
        "creationDate",
        "expirationDate",
        "contractOwner",
        "channelsPacket"
})
public class ContractTelevision extends Contract {

  private String channelsPacket;

  /**
   * Television contract concrete class which extends {@link Contract}.
   *
   * @param channelsPacket Channel packet description
   */
  public ContractTelevision(LocalDate creationDate,
                            LocalDate expirationDate,
                            String channelsPacket,
                            Customer contractOwner) {
    super(creationDate, expirationDate, contractOwner);
    this.channelsPacket = channelsPacket;
  }

  public ContractTelevision(Integer id,
                            LocalDate creationDate,
                            LocalDate expirationDate,
                            String channelsPacket,
                            Customer contractOwner) {
    super(id, creationDate, expirationDate, contractOwner);
    this.channelsPacket = channelsPacket;
  }

  public ContractTelevision() {
  }
}
