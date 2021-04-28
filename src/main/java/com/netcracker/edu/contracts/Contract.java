package com.netcracker.edu.contracts;

import com.netcracker.edu.customers.Customer;
import java.time.LocalDate;

import com.netcracker.edu.repositoryHandlers.xml.utils.DateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Contract abstract class, which sets
 * basic contract fields (like creation/expiration date, id, etc).
 */
@XmlTransient
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Contract {

  @Id
  @XmlAttribute
  @Column(name = "contract_id", nullable = false)
  private int id;

  @XmlTransient
  private static int nextId = 0;

  @Column(name = "creation_date")
  private LocalDate creationDate;

  @Column(name = "expiration_date")
  private LocalDate expirationDate;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private Customer contractOwner;

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

  public Contract(int id, LocalDate creationDate, LocalDate expirationDate, Customer contractOwner) {
    this.id = id;
    this.creationDate = creationDate;
    this.expirationDate = expirationDate;
    this.contractOwner = contractOwner;
  }

  public Contract() {
    this.id = nextId++;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  @XmlElement
  @XmlJavaTypeAdapter(DateAdapter.class)
  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  @XmlElement
  @XmlJavaTypeAdapter(DateAdapter.class)
  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public Customer getContractOwner() {
    return contractOwner;
  }

  @XmlElement
  public void setContractOwner(Customer contractOwner) {
    this.contractOwner = contractOwner;
  }

  public int getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Contract)) {
      return false;
    }

    Contract c = (Contract) obj;

    return c.getExpirationDate().equals(this.getExpirationDate()) &&
            c.getCreationDate().equals(this.getCreationDate()) &&
            c.getContractOwner().getPassport().equals(this.getContractOwner().getPassport()) &&
            c.getContractOwner().getFullName().equals(this.getContractOwner().getFullName());
  }
}