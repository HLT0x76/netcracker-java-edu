package com.netcracker.edu.parsers;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.contracts.concrete.ContractMobile;
import com.netcracker.edu.customers.Gender;
import com.netcracker.edu.repository.ContractsRepository;
import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * CsvRepository class methods functionality tests.
 */
public class CsvRepositoryParserTest {

  File file;
  final String fileName = "test_csv_parser.csv";
  final int expectedLength = 9;
  final String ownerName = "Томас Харрис";
  final Gender ownerGender = Gender.MALE;
  final String ownerPassport = "60-15-42314";
  final int smsAmount = 10;
  final int minutesAmount = 1000;
  final int internetAmount = 1024;
  final int ownerAge = Period.between(
          LocalDate.of(1940, 3, 11),
          LocalDate.now()).getYears();

  @Before
  public void setUp() {
    ClassLoader classLoader = getClass().getClassLoader();
    file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
  }

  @Test
  public void parse() {
    CsvRepositoryParser parser = new CsvRepositoryParser();
    ContractsRepository repo = new ContractsRepository();
    ContractsRepository modifiedRepo = parser.parse(repo, file.getPath());

    int actualLength = modifiedRepo.getLength();
    Assert.assertEquals(expectedLength, actualLength);

    for (int i = 0; i < actualLength; i++) {
      if (modifiedRepo.get(i).isPresent()) {
        Contract expectedContract = modifiedRepo.get(i).get();
        if (expectedContract.getContractOwner().getFullName().equals(ownerName)) {
          ContractMobile con = (ContractMobile) expectedContract;
          Assert.assertEquals(
                  con.getContractOwner().getAge(), ownerAge);
          Assert.assertEquals(
                  con.getContractOwner().getGender(), ownerGender);
          Assert.assertEquals(
                  con.getContractOwner().getPassport(), ownerPassport);
          Assert.assertEquals(
                  con.getMinutesAmount(), minutesAmount);
          Assert.assertEquals(
                  con.getSmsAmount(), smsAmount);
          Assert.assertEquals(
                  con.getInternetAmount(), internetAmount);
        }
      }
    }
  }
}