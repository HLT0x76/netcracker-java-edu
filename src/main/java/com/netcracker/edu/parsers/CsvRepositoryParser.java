package com.netcracker.edu.parsers;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.customers.Customer;
import com.netcracker.edu.injections.Injector;
import com.netcracker.edu.injections.annotations.CustomInjection;
import com.netcracker.edu.injections.annotations.PackageConfig;
import com.netcracker.edu.injections.exceptions.InjectionException;
import com.netcracker.edu.repository.ContractsRepository;
import com.netcracker.edu.validators.ValidationReport;
import com.netcracker.edu.validators.ValidationStatus;
import com.netcracker.edu.validators.Validator;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Implements {@link IParser} interface
 * and provides csv parsing functionality by {@code parse} method.
 */
public class CsvRepositoryParser implements IParser<ContractsRepository, String> {

  private static Logger LOGGER = LogManager.getLogger(CsvRepositoryParser.class);

  @CustomInjection
  @PackageConfig(packages = "com.netcracker.edu.validators.concrete")
  private List<Validator<Contract>> validators = new ArrayList<>();

  /**
   * Constructor with injection (see {@link Injector}).
   */
  public CsvRepositoryParser() {
    try {
      Injector.inject(this);
    } catch (InjectionException e) {
      e.printStackTrace();
    }
  }

  /**
   * Uses {@link CsvToBeanBuilder} to build a valid {@link com.opencsv.bean.CsvToBean} parser
   * for processing of a provided csv file and populating {@link RepositoryCsv} bean.
   * Implements additional control over an already existing {@link Customer} objects.
   *
   * @param repository {@link ContractsRepository} object for parsed data
   * @param fileName   filepath to csv file as string
   * @return populated {@link ContractsRepository} object
   */
  @Override
  public ContractsRepository parse(ContractsRepository repository, String fileName) {
    try {
      CsvToBeanBuilder<RepositoryCsv> beanBuilder = new CsvToBeanBuilder<>(
              new FileReader(fileName));

      List<RepositoryCsv> list = beanBuilder
              .withIgnoreQuotations(true)
              .withType(RepositoryCsv.class)
              .build()
              .parse();

      for (RepositoryCsv csvBean : list) {
        Contract contract = csvBean.getContract();
        String parsedCustomerPassport = csvBean.getPassport();

        int i = 0;
        while (repository.get(i).isPresent()) {
          Customer existingCustomer = repository.get(i).get().getContractOwner();
          if (existingCustomer.getPassport().equals(parsedCustomerPassport)) {
            contract.setContractOwner(existingCustomer);
            break;
          }
          i++;
        }

        if (Objects.isNull(contract.getContractOwner())) {
          contract.setContractOwner(csvBean.getCustomer());
        }

        if (!validators.isEmpty()) {
          List<ValidationReport> reports = new ArrayList<>();
          for (Validator<Contract> validator : validators) {
            reports.add(validator.check(contract));
          }
          if (reports.stream().allMatch((s) -> s.getStatus().equals(ValidationStatus.OK))) {
            repository.add(contract);
          } else {
            for (ValidationReport report : reports) {
              if (!report.getStatus().equals(ValidationStatus.OK)) {
                String msg = String.format(
                        "[%s] %s: %s",
                        report.getStatus(),
                        report.getFailedField(),
                        report.getInfoMessage());
                LOGGER.info(msg);
              }
            }
          }
        } else {
          repository.add(contract);
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return repository;
  }
}

