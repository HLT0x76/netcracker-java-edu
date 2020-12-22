package com.netcracker.edu.repository;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.contracts.concrete.ContractInternet;
import com.netcracker.edu.contracts.concrete.ContractMobile;
import com.netcracker.edu.contracts.concrete.ContractTelevision;
import com.netcracker.edu.customers.Customer;
import com.netcracker.edu.customers.Gender;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Contract repository methods functionality tests.
 */
public class ContractsRepositoryTest {

  private ContractsRepository repo;
  private ContractTelevision conTv;
  private ContractInternet conInt;
  private ContractMobile conMob;
  private final LocalDate creation = LocalDate.now();

  /**
   * testing basic functionality (add, delete, etc.)
   */
  @Before
  public void setUp() {
    Customer owner = new Customer(
            LocalDate.of(1995, 12, 12),
            Gender.MALE,
            "Ivan Jovanovich Ivanov",
            "66-21-42314");
    conInt = new ContractInternet(
            creation,
            creation.plusYears(1),
            25,
            owner);
    conTv = new ContractTelevision(
            creation,
            creation.plusYears(2),
            "Ultra",
            owner);
    conMob = new ContractMobile(
            creation,
            creation.plusYears(3),
            10,
            300,
            2048,
            owner);
    repo = new ContractsRepository();
  }

  @Test
  public void add() {
    repo.add(conInt);
    repo.add(conTv);
    repo.add(conMob);
    repo.add(conTv);
    int cid = conMob.getId();
    Contract expected = conMob;
    Optional<Contract> actual = repo.get(cid);
    Assert.assertTrue(actual.isPresent());
    Assert.assertEquals(expected, actual.get());
  }

  @Test
  public void delete() {
    repo.add(conInt);
    repo.add(conTv);
    repo.add(conMob);
    int conTvId = conTv.getId();
    int conMobId = conTv.getId();
    repo.delete(conTvId);
    repo.delete(conMobId);
    Optional<Contract> expectedTv = repo.get(conTvId);
    Assert.assertFalse(expectedTv.isPresent());
    Optional<Contract> expectedMob = repo.get(conMobId);
    Assert.assertFalse(expectedMob.isPresent());
  }

  @Test
  public void get() {
    repo.add(conInt);
    repo.add(conTv);
    repo.add(conMob);
    Optional<Contract> actual = repo.get(conMob.getId());
    Contract expected = conMob;
    Assert.assertTrue(actual.isPresent());
    Assert.assertEquals(expected, actual.get());
  }

  @Test
  public void sortBy() {
    repo.add(conMob);
    repo.add(conInt);
    repo.add(conTv);
    Comparator<Contract> byId =
            Comparator.comparingInt(Contract::getId);
    repo.sortBy(byId);
    Contract[] actual = repo.getContent();
    Contract[] expected = {conInt, conTv, conMob};
    Assert.assertArrayEquals(expected, actual);
  }

  @Test
  public void searchBy() {
    repo.add(conInt);
    repo.add(conTv);
    repo.add(conMob);
    repo.add(conTv);
    repo.add(conInt);
    repo.add(conMob);

    ContractsRepository expected = new ContractsRepository();
    expected.add(conInt);
    expected.add(conInt);

    Predicate<Contract> expireInLessThenTwo =
            ex -> ex.getExpirationDate().getYear() - ex.getCreationDate().getYear() < 2;
    ContractsRepository actual = (ContractsRepository) repo.searchBy(expireInLessThenTwo);
    Assert.assertArrayEquals(actual.getContent(), expected.getContent());
  }
}