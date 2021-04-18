package com.netcracker.edu.repositoryHandlers;

import com.netcracker.edu.repository.ContractsRepository;

import javax.management.InvalidAttributeValueException;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

/**
 * Handler interface for JAXB and JDBC mappers.
 *
 * @param <T> resource type
 */
public interface IHandler<T> {
  void exportContractRepository(ContractsRepository repository, T resource) throws JAXBException, InvalidAttributeValueException;
  ContractsRepository importContractRepository(T resource) throws FileNotFoundException, JAXBException;
}
