package com.netcracker.edu.Repository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

import com.netcracker.edu.Sorters.BubbleSorter;
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
    private ContractTelevision conTv = null;
    private ContractInternet conInt = null;
    private ContractMobile conMob = null;

    @Before
    public void setUp() {
        conInt = new ContractInternet(
                creation,
                creation.plusYears(1),
                25);
        conTv = new ContractTelevision(
                creation,
                creation.plusYears(2),
                "Ultra");
        conMob = new ContractMobile(
                creation,
                creation.plusYears(3),
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
        ISorter<Contract> bubbleSorter = new BubbleSorter<>();
        repo.sortBy(bubbleSorter, byId);
        Contract[] actual = repo.getContent();
        Contract[] expected = {conInt, conTv, conMob};
        assertEquals(expected, actual);
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
        assertEquals(actual.getContent(), expected.getContent());
    }
}