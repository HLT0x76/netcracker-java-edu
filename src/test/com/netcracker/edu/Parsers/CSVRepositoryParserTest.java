package com.netcracker.edu.Parsers;

import com.netcracker.edu.Contracts.Contract;
import com.netcracker.edu.Contracts.ContractMobile;
import com.netcracker.edu.Customers.Gender;
import com.netcracker.edu.Repository.ContractsRepository;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import static org.junit.Assert.*;

public class CSVRepositoryParserTest {

    File file = null;
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
        CSVRepositoryParser parser = new CSVRepositoryParser();
        ContractsRepository repo = new ContractsRepository();
        ContractsRepository modifiedRepo = parser.parse(repo, file.getPath());

        int actualLength = modifiedRepo.getLength();
        assertEquals(expectedLength, actualLength);

        for (int i = 0; i < actualLength; i++) {
            if (modifiedRepo.get(i).isPresent()) {
                Contract expectedContract = modifiedRepo.get(i).get();
                if (expectedContract.getContractOwner().getFullName().equals(ownerName)) {
                    ContractMobile con = (ContractMobile) expectedContract;
                    assertEquals(con.getContractOwner().getAge(), ownerAge);
                    assertEquals(con.getContractOwner().getGender(), ownerGender);
                    assertEquals(con.getContractOwner().getPassport(), ownerPassport);
                    assertEquals(con.getMinutesAmount(), minutesAmount);
                    assertEquals(con.getSmsAmount(), smsAmount);
                    assertEquals(con.getInternetAmount(), internetAmount);
                }
            }
        }
    }
}