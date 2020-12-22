package com.netcracker.edu.validators.concrete;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.contracts.concrete.ContractInternet;
import com.netcracker.edu.validators.ValidationReport;
import com.netcracker.edu.validators.ValidationStatus;
import com.netcracker.edu.validators.Validator;

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
    return new ValidationReport(ValidationStatus.OK);
  }
}
