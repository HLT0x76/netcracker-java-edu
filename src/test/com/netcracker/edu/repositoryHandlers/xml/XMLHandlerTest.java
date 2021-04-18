package com.netcracker.edu.repositoryHandlers.xml;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.parsers.CsvRepositoryParser;
import com.netcracker.edu.repository.ContractsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.management.InvalidAttributeValueException;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

public class XMLHandlerTest {

  private File csvFile;
  private File xmlFile;
  private XMLHandler xmlHandler;
  private ContractsRepository repository;
  private static final String csvFileName = "test_csv_parser.csv";
  private static final String xmlFileName = "src/main/resources/test_xml_handler.xml";

  @Before
  public void setUp() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();

    csvFile = new File(Objects.requireNonNull(classLoader.getResource(csvFileName)).getFile());
    xmlFile = new File(xmlFileName);

    CsvRepositoryParser parser = new CsvRepositoryParser();
    repository = parser.parse(new ContractsRepository(), csvFile.getPath());
  }

  @Test
  public void exportContractRepository() throws JAXBException, InvalidAttributeValueException {
    xmlHandler = new XMLHandler();
    xmlHandler.exportContractRepository(repository, xmlFile);
    Assert.assertNotEquals(0, xmlFile.length());
  }

  @Test
  public void importContractRepository() throws JAXBException, FileNotFoundException {
    xmlHandler = new XMLHandler();
    List<Contract> actual = xmlHandler.importContractRepository(xmlFile).getContractList();
    List<Contract> expected = repository.getContractList();
    Assert.assertEquals(actual.size(), expected.size());
    for(int i = 0; i < actual.size(); i++) {
      Contract eCon = expected.get(i);
      Contract aCon = actual.get(i);
      Assert.assertEquals(eCon, aCon);
    }
  }
}