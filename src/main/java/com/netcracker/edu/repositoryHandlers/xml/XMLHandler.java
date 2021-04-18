package com.netcracker.edu.repositoryHandlers.xml;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.repository.ContractsRepository;
import com.netcracker.edu.repositoryHandlers.IHandler;

import javax.management.InvalidAttributeValueException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * XMLHandler implements basic functionality of importing and exporting
 * {@link Contract} classes (such as {@link com.netcracker.edu.contracts.concrete.ContractInternet,
 * {@link com.netcracker.edu.contracts.concrete.ContractTelevision}, {@link com.netcracker.edu.contracts.concrete.ContractMobile})
 * using {@link ContractsRepository} object.
 */
public class XMLHandler implements IHandler<File> {

  private final JAXBContext context;

  /**
   * XMLHandler constructor, which defines Contract classes support.
   *
   * @throws JAXBException thrown by wrong annotations, incompatible classes and etc.
   */
  public XMLHandler() throws JAXBException {
    this.context = JAXBContext.newInstance(
            ContractsRepository.class, Contract.class);
  }

  /**
   * Exports contract repository to a path,
   * provided by {@link File} object.
   *
   * @param repository contract repository object
   * @param resource resource file
   * @throws JAXBException thrown on marshalling errors
   * @throws InvalidAttributeValueException thrown when invalid repository provided
   */
  @Override
  public void exportContractRepository(ContractsRepository repository, File resource) throws JAXBException, InvalidAttributeValueException {
    if (repository.getLength() == 0) {
      throw new InvalidAttributeValueException("Repository can't be empty");
    }
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    marshaller.marshal(repository, resource);
  }

  /**
   * Imports contracts repository from .xml file,
   * path of the file provided by {@link File} object.
   *
   * @param resource xml file
   * @return contract repository with contracts, parsed from file
   * @throws JAXBException thrown on marshalling errors
   * @throws FileNotFoundException thrown on invalid path
   */
  @Override
  public ContractsRepository importContractRepository(File resource) throws JAXBException, FileNotFoundException {
    return (ContractsRepository) context.createUnmarshaller()
            .unmarshal(new FileReader(resource));
  }
}
