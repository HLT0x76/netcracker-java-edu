package com.netcracker.edu.repositoryHandlers;

import com.netcracker.edu.repository.ContractsRepository;

import javax.management.InvalidAttributeValueException;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Set;

/**
 * Handler interface for JAXB and JDBC mappers.
 */
public interface IHandler {
  void exportContractRepository(ContractsRepository repository) throws JAXBException, InvalidAttributeValueException;
  ContractsRepository importContractRepository(Set<Integer> contractsIds) throws FileNotFoundException, JAXBException;
}
