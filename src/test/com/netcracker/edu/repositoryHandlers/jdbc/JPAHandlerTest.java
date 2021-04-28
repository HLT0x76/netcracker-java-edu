package com.netcracker.edu.repositoryHandlers.jdbc;

import com.netcracker.edu.parsers.CsvRepositoryParser;
import com.netcracker.edu.repository.ContractsRepository;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JPAHandlerTest {

  private ContractsRepository repository;
  private JPAHandler jpaHandler;
  private static final String csvFileName = "test_csv_parser.csv";
  private static final Set<Integer> contractIds = new HashSet<>();

  @Before
  public void setUp() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();

    File csvFile = new File(Objects.requireNonNull(classLoader.getResource(csvFileName)).getFile());

    CsvRepositoryParser parser = new CsvRepositoryParser();
    repository = parser.parse(new ContractsRepository(), csvFile.getPath());
    repository.toList().forEach(contract -> contractIds.add(contract.getId()));
    assertTrue(repository.getLength() != 0);
  }

  @Test
  public void JPAHandlerContractsTest() {
    jpaHandler = new JPAHandler(EntityManagerFactoryStorage.entityManagerFactory);
    exportContractRepository();

    jpaHandler = new JPAHandler(EntityManagerFactoryStorage.entityManagerFactory);
    importContractRepository();
  }

  private void exportContractRepository() {
    jpaHandler.exportContractRepository(repository);
  }

  private void importContractRepository() {
    ContractsRepository result = jpaHandler.importContractRepository(contractIds);
    assertTrue(result.getLength() != 0);
    for (int i = 0; i < result.toList().size(); i++) {
      assertEquals(repository.get(i).get().getId(), result.get(i).get().getId());
    }
  }
}