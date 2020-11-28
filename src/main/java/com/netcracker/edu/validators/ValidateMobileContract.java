package com.netcracker.edu.validators;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.contracts.ContractMobile;

public class ValidateMobileContract extends Validator<Contract> {

  @Override
  public ValidationReport check(Contract contract) {
    if (contract instanceof ContractMobile) {
      if (((ContractMobile) contract).getSmsAmount() < 0) {
        return new ValidationReport(
                "smsAmount can't be a negative number",
                "smsAmount",
                ValidationStatus.WARNING);
      }
      if (((ContractMobile) contract).getInternetAmount() < 0) {
        return new ValidationReport(
                "internetAmount can't be a negative number",
                "internetAmount",
                ValidationStatus.WARNING);
      }
      if (((ContractMobile) contract).getMinutesAmount() < 0) {
        return new ValidationReport(
                "minutesAmount can't be a negative number",
                "minutesAmount",
                ValidationStatus.WARNING);
      }
    }
    return checkNext(contract);
  }
}