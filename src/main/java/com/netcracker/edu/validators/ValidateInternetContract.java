package com.netcracker.edu.validators;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.contracts.ContractInternet;

/**
 * Extends {@link Validator}, checks internetSpeed field of {@link ContractInternet}.
 */
public class ValidateInternetContract extends Validator<Contract> {
  @Override
  public ValidationReport check(Contract contract) {
    if (contract instanceof ContractInternet) {
      if (((ContractInternet) contract).getInternetSpeed() < 0) {
        return new ValidationReport(
                "internetSpeed can't be a negative number",
                "internetSpeed",
                ValidationStatus.WARNING);
      }
    }
    return checkNext(contract);
  }
}
