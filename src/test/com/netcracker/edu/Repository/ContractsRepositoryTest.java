package com.netcracker.edu.Repository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

import com.netcracker.edu.Sorters.BubbleSorter;
import com.netcracker.edu.Sorters.GnomeSort;
import com.netcracker.edu.Sorters.ISorter;
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
        Optional<Contract> actual = repo.get(cid);
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
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
        assertFalse(expectedTv.isPresent());
        Optional<Contract> expectedMob = repo.get(conMobId);
        assertFalse(expectedMob.isPresent());
    }

    @Test
    public void get() {
        repo.add(conInt);
        repo.add(conTv);
        repo.add(conMob);
        Optional<Contract> actual = repo.get(conMob.getId());
        Contract expected = conMob;
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    public void sortBy() {
        repo.add(conMob);
        repo.add(conInt);
        repo.add(conTv);
        Comparator<Contract> byId =
                Comparator.comparingInt(Contract::getId);
        ISorter<Contract> bubbleSorter = new GnomeSort<>();
        repo.sortBy(bubbleSorter, byId);
        Contract[] actual = repo.getContent();
        Contract[] expected = {conInt, conTv, conMob};
        assertEquals(expected, actual);
    }
}