package com.netcracker.edu.Repository;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.netcracker.edu.Contracts.Contract;
import com.netcracker.edu.Contracts.ContractInternet;
import com.netcracker.edu.Contracts.ContractMobile;
import com.netcracker.edu.Contracts.ContractTelevision;

public class ContractsRepositoryTest {

    private ContractsRepository repo = null;
    private final LocalDate creation = LocalDate.now();
    private final LocalDate expiration = creation.plusYears(1);
    private ContractTelevision conTv = null;
    private ContractInternet conInt = null;
    private ContractMobile conMob = null;

    @Before
    public void setUp() {
        conInt = new ContractInternet(
                creation,
                expiration,
                25);
        conTv = new ContractTelevision(
                creation,
                expiration.plusYears(1),
                "Ultra");
        conMob = new ContractMobile(
                creation,
                expiration.plusYears(2),
                10,
                300,
                2048);
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
        Contract actual = repo.get(cid);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        repo.add(conInt);
        repo.add(conTv);
        repo.add(conMob);
        int conId = conTv.getId();
        repo.delete(conId);
        Contract expected = repo.get(conId);
        Contract actual = null;
        assertEquals(expected, actual);
    }

    @Test
    public void get() {
        repo.add(conInt);
        repo.add(conTv);
        repo.add(conMob);
        Contract expected = repo.get(conTv.getId());
        Contract actual = conTv;
        assertEquals(expected, actual);
    }

}