package com.netcracker.edu.validators.concrete;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.customers.Customer;
import com.netcracker.edu.validators.ValidationReport;
import com.netcracker.edu.validators.ValidationStatus;
import com.netcracker.edu.validators.Validator;
import java.time.LocalDate;

/**
 * Extends {@link Validator}, checks owner field of an object,
 * which extends {@link Contract}.
 */
public class ValidateOwner extends Validator<Contract> {

  @Override
  public ValidationReport check(Contract contract) {
    Customer owner = contract.getContractOwner();
    if (owner == null) {
      return new ValidationReport(
              "contract owner is null",
              "contractOwner",
              ValidationStatus.ERROR);
    }
    if (owner.getDateOfBirth().getYear() > LocalDate.now().getYear()) {
      return new ValidationReport(
              "contract owner date of birth is invalid",
              "contractOwner.dateOfBirth",
              ValidationStatus.WARNING);
    }
    String passportString = owner.getPassport();
    if (passportString.isEmpty() || !passportString.matches("[0-9]{2}-[0-9]{2}-[0-9]{5}")) {
      return new ValidationReport(
              "contract owner passport string is invalid",
              "contractOwner.passport",
              ValidationStatus.WARNING);
    }
    return new ValidationReport(ValidationStatus.OK);
  }
}
