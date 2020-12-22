package com.netcracker.edu.converters;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.contracts.concrete.ContractInternet;
import com.netcracker.edu.contracts.concrete.ContractMobile;
import com.netcracker.edu.contracts.concrete.ContractTelevision;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;

/**
 * Converter which extends {@link AbstractBeanField} and returns
 * new one of the contract classes
 * ({@link ContractMobile}, {@link ContractTelevision}, {@link ContractInternet})
 * object according to the provided {@code String} value.
 */
public class StringToContractConverter extends AbstractBeanField<Contract> {

  @Override
  protected Contract convert(String value) throws CsvConstraintViolationException {
    switch (value) {
      case "Mobile":
        return new ContractMobile();
      case "Internet":
        return new ContractInternet();
      case "Television":
        return new ContractTelevision();
      default:
        String errMsg = "Unknown contract type: " + value;
        throw new CsvConstraintViolationException(value, errMsg);
    }
  }
}
