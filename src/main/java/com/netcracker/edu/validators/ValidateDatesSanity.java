package com.netcracker.edu.validators;

import com.netcracker.edu.contracts.Contract;

/**
 * Extends {@link Validator}, checks {@code creationDate} and {@code expirationDate}
 * fields of an object, which extends {@link Contract}.
 */
public class ValidateDatesSanity extends Validator<Contract> {

  @Override
  public ValidationReport check(Contract contract) {
    if (contract.getCreationDate() == null) {
      return new ValidationReport(
              "creation date is null",
              "creationDate",
              ValidationStatus.ERROR);
    }
    if (contract.getExpirationDate() == null) {
      return new ValidationReport(
              "expiration date is null",
              "expirationDate",
              ValidationStatus.ERROR);
    }
    int creationYear = contract.getCreationDate().getYear();
    int expirationYear = contract.getExpirationDate().getYear();
    if (creationYear > expirationYear) {
      return new ValidationReport(
              "creation date after expiration date",
              "creationDate & expirationDate",
              ValidationStatus.WARNING);
    }
    return checkNext(contract);
  }
}
