package com.netcracker.edu.validators.concrete;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.contracts.concrete.ContractTelevision;
import com.netcracker.edu.validators.ValidationReport;
import com.netcracker.edu.validators.ValidationStatus;
import com.netcracker.edu.validators.Validator;

/**
 * Extends {@link Validator}, checks channelPacket field of {@link ContractTelevision}.
 */
public class ValidateTelevisionContract extends Validator<Contract> {

  @Override
  public ValidationReport check(Contract contract) {
    if (contract instanceof ContractTelevision) {
      if (((ContractTelevision) contract).getChannelsPacket().equals("")) {
        return new ValidationReport(
                "channelsPacket are empty",
                "channelsPacket",
                ValidationStatus.WARNING);
      }
    }
    return new ValidationReport(ValidationStatus.OK);
  }
}
