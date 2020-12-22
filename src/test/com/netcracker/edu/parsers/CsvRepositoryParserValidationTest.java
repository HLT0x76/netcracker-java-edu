package com.netcracker.edu.parsers;

import com.netcracker.edu.repository.ContractsRepository;
import java.io.File;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Validators classes functionality tests.
 */
public class CsvRepositoryParserValidationTest {

  File file;
  final String fileName = "test_csv_parser_validation.csv";

  @Before
  public void setUp() {
    ClassLoader classLoader = getClass().getClassLoader();
    file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
  }

  @Test
  public void parse() {
    CsvRepositoryParser parser = new CsvRepositoryParser();
    ContractsRepository repo = parser.parse(new ContractsRepository(), file.getPath());

    Assert.assertEquals(0, repo.getLength());
  }
}